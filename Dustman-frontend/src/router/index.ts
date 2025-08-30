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
import ShopDetails from '@/components/product/ShopDetails.vue'
import shopContact from '@/components/product/shopContact.vue'
import { useLoginRegStore } from '@/stores/login/LoginRegStore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "LogIn",
      component: LogIn,
    },
    {
      path: "/oauth2/callback",
      component: () => import("@/components/OAuth2Callback.vue"),
    },
    {
      path: "/register",
      name: "Register",
      component: Register,
    },
    {
      path: "/",
      name: "LandingPage",
      component: LandinPage,
    },
    {
      path: "/home",
      name: "HomePage",
      component: HomePage,
      meta: { requiresAuth: true },
    },

    {
      path: "/payments",
      name: "payment",
      component: Payment,
      meta: { requiresAuth: true },
    },
    {
      path: "/booking",
      name: "Bookings",
      component: Bookings,
      meta: { requiresAuth: true },
    },
    {
      path: "/orders",
      name: "Orders",
      component: Orders,
      meta: { requiresAuth: true },
    },
    {
      path: "/profile",
      name: "Profiles",
      component: Profiles,
      meta: { requiresAuth: true },
    },
    {
      path: "/setting",
      name: "Settings",
      component: Settings,
      meta: { requiresAuth: true },
    },
    {
      path: "/shpOwnrDhsboard",
      name: "ShopOwnerDahsboard",
      component: ShopOwnerDahsboard,
      meta: { requiresAuth: true },
    },
    {
      path: "/userDashboard",
      name: "UserDashboard",
      component: UserDashboard,
      meta: { requiresAuth: true },
    },
    {
      path: "/help",
      name: "HelpAndSupport",
      component: HelpAndSupport,
    },
    {
      path: '/shopDetails',
      name: 'ShopDetails',
      component: ShopDetails,
    },
    {
      path: '/shopContact',
      name: 'ShopContact',
      component: shopContact,
    },

  ],
});

router.beforeEach((to, from, next) => {
  const store = useLoginRegStore();

  // Skip check for login, register, oauth2/callback, landing
  if (
    to.path === "/login" ||
    to.path === "/register" ||
    to.path === "/oauth2/callback" ||
    to.path === "/"
  ) {
    return next();
  }

  // If route requires auth but no token
  if (to.meta.requiresAuth && !store.getIsLogin) {
    return next("/login");
  }
  next();
});

export default router
