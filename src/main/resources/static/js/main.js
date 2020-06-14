import Vue from 'vue'
import App from 'page/app.vue'
import router from 'router/router'
import { connect } from './util/socet'

connect()

window.axios = require('axios');

let token = document.head.querySelector('meta[name="csrf-token"]');
if (token) {
    window.axios.defaults.headers.common['X-CSRF-TOKEN'] = token.content;
} else {
    console.error('CSRF token not found');
}
new Vue({
  el: '#app',
  router,
  render: a => a(App)
})
