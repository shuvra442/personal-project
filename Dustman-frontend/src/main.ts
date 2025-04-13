import './style.css'
import { createApp} from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import  'aos/dist/aos.css';
import AOS from 'aos'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

AOS.init({
    duration: 1000, // animation duration in ms
    once: true      // only animate once
  })
