<template>
<div>
  <label>Name</label><br>
  <input type="text" v-model="animal.name"><br>
  <label>Type</label><br>
  <input type="text" v-model="animal.type"><br>
  <label>Serial Number</label><br>
  <input type="text" v-model="animal.serial_n"><br>
  <b-button v-on:click="saveAnimal" variant="outline-warning" style="margin-top: 20px">Save</b-button>
</div>
</template>

<script>

import AnimalService from '../services/animal-service'

export default {
  name: 'AnimalTab',
  data () {
    return {
      message: '',
      animal: {
        Id: '',
        name: '',
        type: '',
        serial_n: ''
      }
    }
  },
  beforeRouteEnter (to, from, next) {
    console.log(to.params.id)
    if (to.params.id) {
      AnimalService.getAnimalById(to.params.id).then(
        response =>
          next(vm =>
            vm.setData(response)
          )
      )
    } else {
      next()
    }
  },
  methods: {
    setData (response) {
      this.animal = response.data
    },
    saveAnimal () {
      console.log('SaveAnimal Method >>>>>>>' + this.animal.Id + this.animal.name + this.animal.serial_n)
      AnimalService.saveAnimal(this.animal).then(
        response => {
          console.log(response)
          this.animal = response.data.animal
          this.message = response.data.message
        },
        error => {
          this.message =
            error.data.message.toString() ||
            error.message() ||
            (error.response && error.response.data)
        }
      )
    }
  }
}
</script>

<style scoped>
.image-fit{
  height: 100%;
  width: 100%;
  object-fit: contain;
}
</style>
