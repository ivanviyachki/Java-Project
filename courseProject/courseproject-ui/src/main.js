import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { BTable, BPagination, BFormCheckbox, BForm, BButton } from 'bootstrap-vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.component('b-button', BButton)
Vue.component('b-form', BForm)
Vue.component('b-formcheckbox', BFormCheckbox)
Vue.component('b-pagination', BPagination)
Vue.component('b-table', BTable)
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')
