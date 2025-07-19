import { createRouter, createWebHistory } from 'vue-router'
import LogIne from '@/components/common/Log-ine.vue'
import PageLayout from '@/views/PageLayout.vue'
import NewVuetiFy from '@/views/NewVuetiFy.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/login',
      name: 'LoginPage',
      component: LogIne,
    },

    {
      path: '/',
      name: 'layout',
      component: PageLayout,
    },
    {
      path: '/about',
      name: 'layout',
      component: NewVuetiFy,
    },

  ],
})

export default router
