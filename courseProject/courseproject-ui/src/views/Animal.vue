<template>
<div class="animal">
  <h3>{{message}}</h3>
  <div style="padding-bottom: 20px; float: right; margin-right: 20px">
  <b-button v-on:click="searchAnimals" variant="outline-success">Search</b-button>
  <span style="margin-left: 10px"></span>
  <b-button @click="$router.push('AnimalTab')" variant="outline-success">Add new</b-button>
  </div>
  <b-table
    id="animalTable"
    striped
    head-variant=dark
    table-variant=warning
    hover
    :fields="fields"
    :items="animals"
    :current-page="currentPage"
    :per-page="0"
  >

    <template slot="top-row" slot-scope="{ fields }">
      <td v-for="(field, index) in fields" :key="field.id">
        <div v-if="index === fields.length - 1 || index === fields.length - 2 || index === fields.length - 3">
        </div>
        <div v-else>
          <input v-model="filters[field.key]">
        </div>
      </td>
    </template>

    <template v-slot:cell(actions)="row">
      <b-button @click="$router.push({name: 'AnimalTab', params: { id:row.item.id }})" variant="outline-success">Edit</b-button> |
      <b-button variant="danger" v-on:click="deleteAnimal(row.item.id)">Delete</b-button>
    </template>
  </b-table>

  <b-pagination
  @input="searchAnimals"
  v-model="currentPage"
  :total-rows="rows"
  :per-page="perPage"
  aria-controls="animalTable">
  </b-pagination>
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
        { key: 'name', label: 'Name' },
        { key: 'type', label: 'Animal Type' },
        { key: 'serial_n', label: 'Serial Number' },
        { key: 'zoo.name', label: 'Zoo' },
        { key: 'actions', label: 'Actions' }
      ],
      filters: {
        name: '',
        type: '',
        serial_n: ''
      }
    }
  },
  mounted () {
    this.searchAnimals()
  },
  methods: {

    getAllAnimals () {
      AnimalService.getAnimalsPage().then(
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
      AnimalService.getAnimalsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          // console.log('filters: ' + this.filters.name + 'currPage: ' + this.currentPage)
          this.animals = response.data.animals
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
.animal{
  height: 100%;
}
</style>
