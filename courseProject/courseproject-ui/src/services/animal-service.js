import axios from 'axios'

const API_URL = 'http://localhost:8080/animal/'

class AnimalService {
  getAllAnimals () {
    return axios.get(API_URL + 'all')
  }

  SearchAnimalByName (filters) {
    console.log(API_URL + 'search')
    return axios.get(API_URL + 'searchByName',
      {
        params:
         {
           name: filters.name,
           type: filters.type,
           serial_n: filters.serial_n
         }
      })
  }
  //
  // getAllAnimalsPage (filters, currentPage, perPage) {
  //   return axios.get(API_URL + '/search/page',
  //     {
  //       params:
  //         {
  //           name: filters.name,
  //           type: filters.type,
  //           serial_n: filters.serial_n,
  //           currentPage: currentPage,
  //           perPage: perPage
  //         }
  //     })
  // }

  getAnimalById (id) {
    return axios.get(API_URL + 'search/id',
      {
        params: {
          id: id
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
  //
  // saveAnimalDB (form) {
  //   return axios.post(API_URL + '/save/animal',
  //     {
  //       name: form.name,
  //       type: form.type,
  //       serial_n: form.serial_n,
  //       id: form.id
  //     })
  // }

  saveAnimal (form) {
    console.log('saveAnimalLast' + form.name + form.type)
    return axios.post(API_URL + 'save/animal',
      {
        Id: form.id,
        name: form.name,
        type: form.type,
        serial_n: form.serial_n
      })
  }
}

export default new AnimalService()
