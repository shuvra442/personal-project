import { createRouter, createWebHistory } from 'vue-router'
import PageLayout from '@/views/PageLayout.vue'
import HomePage from '@/components/common/homePage.vue'
import LandinPage from '@/views/LandingPage.vue'
import ShopDetails from '@/views/ShopDetails.vue'
import LogIn from '@/components/LogIn.vue'
import Register from '@/components/Register.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/login',
      name: 'LogIn',
      component: LogIn,
    
    },
    {
      path:'/register',
      name: 'Register',
      component: Register,
    },
    {
      path: '/',
      name: 'LandingPage',
      component: LandinPage,
    },
    {
      path: '/home',
      name: 'HomePage',
      component: HomePage,
    },
    {
      path: '/shop/:id',
      name: 'ShopDetails',
      component: ShopDetails,
      props: true,
    },
    {
      path: '/layout',
      name: 'layout',
      component: PageLayout,
    },

  ],
})

export default router
