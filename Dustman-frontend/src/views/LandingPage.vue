<template>

  <!-- Navbar -->
  <nav class="flex items-center justify-between p-4 flex-wrap shadow-md sticky top-0 z-50"
    style="background-color: #b2d1e6;">
    <div class="flex items-center gap-2 ml-24 cursor-pointer">
      <router-view :to="{ name: '/' }">
        <img class="h-6 w-6" src="../assets/dust-logo.png" alt="dust" />
        <span class="text-gray-600 font-bold">Dustman</span>
      </router-view>
    </div>

    <div class="flex items-center gap-4 w-full md:w-auto">
      <span class="nav-title">Home</span>
      <span class="nav-title">About</span>
      <span class="nav-title">Contact Us</span>
      <button class="custom-button" @click="showWindowForLogin">
        Login
      </button>
    </div>
  </nav>

  <!-- add -->
  <section class="min-h-screen flex items-center justify-center px-8 overflow-hidden"
    style="background-color: #b2d1e6;">
    <div class="container mx-auto flex flex-col md:flex-row items-center justify-between gap-10 py-16">

      <!-- Left Text Section -->
      <div class="md:w-1/2 space-y-2" data-aos="fade-right">
        <h1 class="text-4xl font-bold text-gray-800 leading-tight">
          Turn Waste into Worth
        </h1>
        <p class="text-lg text-gray-600">
          Book your dust pickup slot and help build a cleaner, greener future.
          Dust sellers receive quality waste with zero hassle!
        </p>
        <ul class="space-y-2 text-gray-700">
          <li>✅ Book a Slot Easily</li>
          <li>✅ Connect with Dust Sellers</li>
          <li>✅ Eco-Friendly Disposal</li>
        </ul>
        <div class="flex gap-4">
          <button class="custom-button px-6">
            Book Now
          </button>
          <button class="bg-gray-200 text-gray-800 px-6 py-2 rounded-lg hover:bg-gray-300">
            Learn More
          </button>
        </div>
      </div>

      <!-- Right Image Section -->
      <div class="md:w-1/2" data-aos="fade-left">
        <img src="../assets/dustImage.png" alt="Dust pickup illustration" class="w-full rounded-xl" />
      </div>
    </div>
  </section>

  <LoginCommon
  :show-currencypdf-modals="showLogin"
  @close-popup-modal="closeLogin"
  />

</template>

<script lang="ts">
import LoginCommon from '@/components/common/LoginCommon.vue'
import { reactive, toRefs } from 'vue'

export default {

  components: {
    LoginCommon
  },

  setup() {
    const state = reactive({
      showLogin : false,
      closeLogin: false
    })
    const showWindowForLogin = () => {
        state.showLogin = true // Show the popup if toggle is ON
    }

    // Function to handle the "Close" (X) button event from the popup
    const closeLogin = (accept: Event) => {
      state.showLogin = false
      if (accept) {
        // state.currencyChecked = true // If "I Accept" is clicked, keep the toggle ON
      } else {
        // state.currencyChecked = false // If close (X) is clicked, turn the toggle OFF
      }
    }

    return {
      ...toRefs(state),
      showWindowForLogin,
      closeLogin
    }
  }
}

</script>

<style scoped></style>
