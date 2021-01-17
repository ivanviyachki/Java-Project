<template>
<div class="animal">
  <h3>{{message}}</h3>
  <b-button v-on:click="searchAnimals" variant="outline-success">Търсене</b-button>
  <router-link :to="{ name: 'AnimalTab' }">Въведи</router-link>
  <b-table
    id="animalTable"
    striped
    hover
    :fields="fields"
    :items="animals"
  >

    <template slot="top-row" slot-scope="{ fields }">
      <td v-for="(field, index) in fields" :key="field.id">
        <div v-if="index === fields.length - 2 || index === fields.length - 1 ">
        </div>
        <div v-else>
          <input v-model="filters[field.key]">
        </div>
      </td>
    </template>

    <template v-slot:cell(actions)="row">
      <router-link :to="{ name: 'AnimalTab', params: { id:row.item.id } }">Отвори</router-link>
      <b-button variant="danger" v-on:click="deleteAnimal(row.item.id)">Изтрий</b-button>
    </template>
  </b-table>
</div>
</template>

<script>
import AnimalService from '../services/animal-service'

export default {
  name: 'Animal',
  data () {
    return {
      message: '',
      currentPage: 1,
      rows: '',
      perPage: 5,
      animals: [{
        id: '',
        name: '',
        type: '',
        serial_n: ''
      }],
      fields: [
        { key: 'name', label: 'Име' },
        { key: 'type', label: 'Вид животно' },
        { key: 'serial_n', label: 'Номер на животното' },
        { key: 'zoo.name', label: 'Зоопарк' },
        { key: 'actions', label: 'Действия' }
      ],
      filters: {
        name: '',
        type: '',
        serial_n: ''
      }
    }
  },
  mounted () {
    this.getAllAnimals()
  },
  methods: {

    getAllAnimals () {
      AnimalService.getAllAnimals().then(
        response => {
          console.log(response)
          this.animals = response.data
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message || error.toString
        }
      )
    },
    searchAnimals () {
      AnimalService.SearchAnimal(this.filters).then(
        response => {
          // console.log(response)
          this.animals = response.data
          // this.rows = response.data.totalItems
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message || error.toString
        }
      )
    },
    deleteAnimal (id) {
      AnimalService.deleteAnimal(id).then(
        response => {
          console.log(response)
          this.message = response.data
          this.getAllAnimals()
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message || error.toString
        }
      )
    }
  }
}
</script>

<style scoped>

</style>
