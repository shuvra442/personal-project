import './style.css'
import { createApp} from 'vue'
import { createPinia } from 'pinia'
import { MotionPlugin } from '@vueuse/motion'

import VueToast from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-sugar.css'


import App from './App.vue'
import router from './router'
import  'aos/dist/aos.css';
import AOS from 'aos'
import { useSampleStore } from './stores/sample'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VueToast)
app.use(MotionPlugin)

router.beforeEach((to,from,next)=>{
  const dataStore=useSampleStore();
  if (to.meta.requiresAuth && !dataStore.getLoginState) {

    next({path:"/"})
    // next()
  }
  else{
    next()
  }
})

app.mount('#app')

AOS.init({
    duration: 1000, // animation duration in ms
    once: true      // only animate once
  })
