import './style.css'
import { createApp} from 'vue'
import { createPinia } from 'pinia'
import { MotionPlugin } from '@vueuse/motion'

import VueToast from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-sugar.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

// const pinia = createPinia()
// pinia.use(piniaPluginPersistedstate)

// app.use(pinia)

import App from './App.vue'
import router from './router'
import  'aos/dist/aos.css';
import AOS from 'aos'
import { useLoinRegStore } from './stores/login/LoginRegStore'
// import { useSampleStore } from './stores/sample'

const app = createApp(App)
const pinia = createPinia()
app.use(createPinia())
app.use(router)
app.use(VueToast)
app.use(MotionPlugin)
pinia.use(piniaPluginPersistedstate)

app.use(pinia)
router.beforeEach((to,from,next)=>{
  const dataStore=useLoinRegStore();
  if (to.meta.requiresAuth && !dataStore.getIsLogin) {

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
