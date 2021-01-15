<template>
<div class="animal">
  <button v-on:click="searchAnimals">Търсене</button>
  <router-link :to="{ name: 'AnimalTab' }">Въведи</router-link>
  <b-table
    id="animalTable"
    striped
    hover
    :items="animals"
    :fields="fields"
    :current-page="currentPage"
    per-page="0"
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

  <b-pagination
    @input="getAllAnimals"
    v-model="currentPage"
    :total-rows="rows"
    :per-page="perPage"
    aria-controls="animalTable"
    ></b-pagination>
</div>
</template>

<script>
import AnimalService from '../services/animal-service'

export default {
  name: 'Animal',
  data () {
    return {
      currentPage: 1,
      rows: '',
      perPage: 1,
      animals: '',
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
      AnimalService.getAllAnimalsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          // console.log(response)
          this.animals = response.data
          this.rows = response.data.totalItems
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
          this.searchAnimals()
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
