import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)
Vue.config.productionTip = false
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8080/api'
Vue.prototype.$axios = axios

import App from './App'
import router from './router'


new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
