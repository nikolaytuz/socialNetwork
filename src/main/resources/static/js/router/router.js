import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from 'pages/Login.vue'
import Registration from 'pages/Registration.vue'
import Profile from 'pages/Profile.vue'
import Messages from 'pages/Messages.vue'
import Lenta from 'pages/Lenta.vue'
import Friends from 'pages/Friends.vue'
import Setings from 'pages/Setings.vue'
import ChangePass from 'pages/ChangePass.vue'

Vue.use(VueRouter)


const routes = [
  { path: '/login', component: Login },
  { path: '/changepass', component: ChangePass },
  { path: '/registration', component: Registration },
  { path: '/lenta', component: Lenta },
  { path: '/friends', component: Friends },
  { path: '/messages/:id?', component: Messages },
  { path: '/setting', component: Setings },
  { path: '/:id?', component: Profile },
]

export default new VueRouter({
  routes
}) 