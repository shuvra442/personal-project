import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/homePage.vue'
import LandinPage from '@/views/LandingPage.vue'
import LogIn from '@/components/LogIn.vue'
import Register from '@/components/Register.vue'
import Payment from '@/components/payment/Payment.vue'
import Bookings from '@/views/Bookings.vue'
import Orders from '@/views/Orders.vue'
import Profiles from '@/views/Profiles.vue'
import Settings from '@/components/Settings.vue'
import ShopOwnerDahsboard from '@/components/ShopOwnerDahsboard.vue'
import UserDashboard from '@/components/UserDashboard.vue'
import HelpAndSupport from '@/components/HelpAndSupport.vue'

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
      path: '/payments',
      name: 'payment',
      component: Payment,
    },
    {
      path: '/booking',
      name: 'Bookings',
      component: Bookings,
    },
    {
      path: '/orders',
      name: 'Orders',
      component: Orders,
    },
    {
      path: '/profile',
      name: 'Profiles',
      component: Profiles,
    },
    {
      path: '/setting',
      name: 'Settings',
      component: Settings,
    },
    {
      path: '/shpOwnrDhsboard',
      name: 'ShopOwnerDahsboard',
      component: ShopOwnerDahsboard,
    },
    {
      path: '/userDashboard',
      name: 'UserDashboard',
      component: UserDashboard,
    },
    {
      path: '/help',
      name: 'HelpAndSupport',
      component: HelpAndSupport,
    },

  ],
})

export default router
