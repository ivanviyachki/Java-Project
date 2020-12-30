<template>
<div class="animal">
  <header>
    <h3>{{content}}</h3>
  </header>
</div>
</template>

<script>
import AnimalService from '../services/animal-service'

export default {
  name: 'Animal',
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    AnimalService.getAllAnimals().then(
      response => {
        console.log(response)
        this.content = response.data
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message || error.toString
      }
    )
  }
}
</script>

<style scoped>

</style>
