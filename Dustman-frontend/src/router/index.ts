import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LandingPage from '@/views/LandingPage.vue'
import HomeVieww from '@/views/HomeVieww.vue'
import DashboardView from '@/views/DashboardView.vue'
import ProfileView from '@/views/ProfileView.vue'
import PaymentView from '@/views/PaymentView.vue'
import SettingView from '@/views/SettingView.vue'
import OrderView from '@/views/BookingView.vue'
import BookingView from '@/views/BookingView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landingPage',
      component: LandingPage,
    },
    {
      path: '/home',
      name: 'HomePage',
      component: HomeView,
    },
    {
      path: '/homee',
      name: 'HomePagee',
      component: HomeVieww,
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
      // meta:{requiresAuth :true}
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView,
      // meta:{requiresAuth :true}
    },
    {
      path: '/payment',
      name: 'payment',
      component: PaymentView,
      // meta:{requiresAuth :true}
    },
    {
      path: '/setting',
      name: 'setting',
      component: SettingView,
      // meta:{requiresAuth :true}
    },
    {
      path: '/booking',
      name: 'booking',
      component: BookingView,
      // meta:{requiresAuth :true}
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: LandingPage,
    }
    
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue'),
    // },
  ],
})

export default router
