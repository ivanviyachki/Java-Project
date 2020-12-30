import axios from 'axios'

const API_URL = 'http://localhost:8080/animal/'

class AnimalService {
  getAllAnimals () {
    return axios.get(API_URL + 'all')
  }
}

export default new AnimalService()
