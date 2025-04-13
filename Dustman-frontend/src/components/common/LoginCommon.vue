<template>
  <div>
    <div v-if="showCurrencyPdfPopup" class="flex justify-center">
      <div class="z-50 fixed items-center inset-0 p-4 flex justify-center bg-white-50 bg-opacity-50">
        <div class="bg-white p-8 w-full lg:w-6/12 rounded-lg dark:bg-black-400">
          <div class="flex justify-between items-center border-b mb-4">
            <h2 class="text-xl font-bold mb-2">LOGIN</h2>
            <div>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                stroke="currentColor" class="h-5 w-5 cursor-pointer" @click.stop="closePopup(false)">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
            </div>
          </div>
          <!-- Left: Login Form -->
          <div class="w-full md:w-1/2 p-8">
            <p class="text-sm text-gray-500 mb-6">How to get started lorem ipsum dolor at?</p>

            <input v-model="username" type="text" placeholder="Username"
              class="w-full px-4 py-2 mb-4 bg-gray-100 rounded" />
            <input v-model="password" type="password" placeholder="Password"
              class="w-full px-4 py-2 mb-6 bg-gray-100 rounded" />
            <button class="bg-purple-600 text-white px-6 py-2 rounded w-full mb-4 hover:bg-purple-700">
              Login Now
            </button>

            <div class="text-center text-gray-500 mb-2">Login with Others</div>
            <button class="w-full flex items-center justify-center border px-4 py-2 rounded mb-2">
              <!-- <img src="https://www.svgrepo.com/show/475656/google-color.svg" class="h-5 w-5 mr-2" /> -->
              Login with Google
            </button>
            <button class="w-full flex items-center justify-center border px-4 py-2 rounded">
              <!-- <img src="https://www.svgrepo.com/show/475647/facebook-color.svg" class="h-5 w-5 mr-2" /> -->
              Login with Facebook
            </button>
          </div>
          <!-- Right: Image Section -->
          <div
            class="hidden md:flex md:w-1/2 bg-gradient-to-br from-indigo-500 to-purple-600 items-center justify-center p-6">
            <!-- <img src="../assets/login-side.png" alt="login visual" class="rounded-lg" /> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive, toRefs, defineComponent, watch } from 'vue'

export default defineComponent({
  props: {
    showCurrencypdfModals: {
      type: Boolean,
      default: false
    }
  },
  emits: ['closePopupModal'],
  setup(props, context) {
    const state = reactive({
      showCurrencyPdfPopup: false, // Tracks if the popup should be visible
      username: '',
      password: ''

    })

    // Function to handle closing the popup and emit event based on user action
    const closePopup = (accepted: boolean) => {
      state.showCurrencyPdfPopup = false
      context.emit('closePopupModal', accepted) // Emit whether user accepted or not
    }

    // Watch for changes in the showCurrencypdfModals prop to display the popup
    watch(
      () => props.showCurrencypdfModals,
      () => {
        if (props.showCurrencypdfModals) {
          state.showCurrencyPdfPopup = true // Show the popup when prop is true
        }
      }
    )

    return {
      ...toRefs(state),
      closePopup
    }
  }
})
</script>