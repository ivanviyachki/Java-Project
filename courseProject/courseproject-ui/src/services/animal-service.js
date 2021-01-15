import axios from 'axios'

const API_URL = 'http://localhost:8080/animal/'

class AnimalService {
  getAllAnimals () {
    return axios.get(API_URL + 'all')
  }

  getAllAnimalsPage (filters, currentPage, perPage) {
    return axios.get(API_URL + '/search/page',
      {
        params:
         {
           name: filters.name,
           type: filters.type,
           serial_n: filters.serial_n,
           currentPage: currentPage,
           perPage: perPage
         }
      })
  }

  deleteAnimal (id) {
    return axios.delete(API_URL + '/delete',
      {
        params:
          {
            id: id
          }
      })
  }
}

export default new AnimalService()
