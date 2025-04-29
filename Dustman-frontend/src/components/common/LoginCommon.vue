<template>
  <div>
    <div v-if="showCurrencypdfModals" class="flex justify-center">
      <div class="z-50 fixed items-center inset-0 p-4 flex justify-center bg-white-50 bg-opacity-50">
        <div class="bg-white p-8 w-full lg:w-6/12 rounded-lg dark:bg-black-400">
          <div class="flex justify-between items-center border-b mb-4">
            <h2 class="text-xl font-bold mb-2">{{ isLoginMode ? 'LOGIN' : 'REGISTER' }}</h2>
            <div>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="h-5 w-5 cursor-pointer"
                @click.stop="closePopup(false)"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
              </svg>
            </div>
          </div>
          <!-- Wrapper for Left and Right Sections -->
          <div class="flex flex-col md:flex-row relative overflow-hidden min-h-[400px]">
            <!-- Left: Login/Register Form -->
            <div class="w-full md:w-1/2 p-8 z-10">
              <p class="text-sm text-gray-500 mb-6">How to get started with Dustman?</p>

              <!-- Form Transition Wrapper -->
              <transition name="form-fade" mode="out-in">
                <!-- Login Form -->
                <div v-if="isLoginMode" key="login-form" class="form-container">
                  <input
                    v-model="username"
                    type="text"
                    placeholder="Username"
                    class="w-full px-4 py-2 mb-4 bg-gray-100 rounded"
                  />
                  <input
                    v-model="password"
                    type="password"
                    placeholder="Password"
                    class="w-full px-4 py-2 mb-6 bg-gray-100 rounded"
                  />
                  <button
                    class="bg-purple-600 text-white px-6 py-2 rounded w-full mb-4 hover:bg-purple-700 cursor-pointer"
                  >
                    Login Now
                  </button>
                  <div class="text-center text-gray-500 mb-2">Login with Others</div>
                  <button
                    class="w-full flex items-center justify-center border px-4 py-2 rounded-2xl mb-2 cursor-pointer"
                  >
                    <img class="h-6 w-6 mr-3" src="/src/assets/google.png" alt="" />
                    Login with Google
                  </button>
                  <span class="text-slate-700">
                    Don't have an account?
                    <span
                      class="mr-3 text-purple-500 hover:text-purple-800 cursor-pointer border-b"
                      @click="toggleMode"
                    >
                      Register.
                    </span>
                  </span>
                </div>

                <!-- Register Form -->
                <div v-else key="register-form" class="form-container">
                  <!-- <input
                    v-model="registerData.fullName"
                    type="text"
                    placeholder="Full Name"
                    class="w-full px-4 py-2 mb-4 bg-gray-100 rounded"
                  />
                  <input
                    v-model="registerData.email"
                    type="email"
                    placeholder="Email"
                    class="w-full px-4 py-2 mb-4 bg-gray-100 rounded"
                  />
                  <input
                    v-model="registerData.password"
                    type="password"
                    placeholder="Password"
                    class="w-full px-4 py-2 mb-6 bg-gray-100 rounded"
                  />
                  <button
                    class="bg-purple-600 text-white px-6 py-2 rounded w-full mb-4 hover:bg-purple-700 cursor-pointer"
                  >
                    Register Now
                  </button> -->
                  <span class="text-slate-700">
                    Already have an account?
                    <span
                      class="mr-3 text-purple-500 hover:text-purple-800 cursor-pointer border-b"
                      @click="toggleMode"
                    >
                      Login.
                    </span>
                  </span>
                </div>
              </transition>

              <!-- Animated Image -->
              <transition name="image-slide">
                <img
                  v-if="!isLoginMode"
                  src="/src/assets/login-image.png"
                  alt="login visual"
                  class="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-full max-w-md opacity-50 rounded-lg"
                />
              </transition>
            </div>
            <!-- Right: Image or Register Form -->
            <div class="hidden md:flex md:w-1/2 p-8 z-10 relative">
              <transition name="image-slide">
                <img
                  v-if="isLoginMode"
                  src="/src/assets/login-image.png"
                  alt="login visual"
                  class="w-full max-w-md rounded-lg"
                />
              </transition>
              <transition name="form-fade">
                <div v-if="!isLoginMode" class="absolute inset-0 p-8 flex flex-col justify-center">
                  <input
                    v-model="registerData.fullName"
                    type="text"
                    placeholder="Full Name"
                    class="w-full px-4 py-2 mb-4 bg-gray-100 rounded"
                  />
                  <input
                    v-model="registerData.email"
                    type="email"
                    placeholder="Email"
                    class="w-full px-4 py-2 mb-4 bg-gray-100 rounded"
                  />
                  <input
                    v-model="registerData.password"
                    type="password"
                    placeholder="Password"
                    class="w-full px-4 py-2 mb-6 bg-gray-100 rounded"
                  />
                  <button
                    class="bg-purple-600 text-white px-6 py-2 rounded w-full mb-4 hover:bg-purple-700 cursor-pointer"
                  >
                    Register Now
                  </button>
                </div>
              </transition>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { reactive, toRefs, defineComponent } from 'vue';

export default defineComponent({
  props: {
    showCurrencypdfModals: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['closePopupModal'],
  setup(props, { emit }) {
    const state = reactive({
      username: '',
      password: '',
      isLoginMode: true,
      registerData: {
        fullName: '',
        email: '',
        password: '',
      },
    });
    

    // Function to handle closing the popup
    const closePopup = (accepted: boolean) => {
      emit('closePopupModal', accepted);
    };

    // Function to toggle between login and register modes
    const toggleMode = () => {
      state.isLoginMode = !state.isLoginMode;
    };

    return {
      ...toRefs(state),
      closePopup,
      toggleMode,
    };
  },
});
</script>

<style scoped>
.image-slide-enter-active,
.image-slide-leave-active {
  transition: all 0.5s ease-in-out;
}

.image-slide-enter-from,
.image-slide-leave-to {
  opacity: 0;
  transform: translateX(50%);
}

.form-fade-enter-active,
.form-fade-leave-active {
  transition: opacity 0.4s ease;
}
.form-fade-enter-from,
.form-fade-leave-to {
  opacity: 0;
}
/* Ensure form container is positioned correctly */
.form-container {
  position: relative;
  z-index: 10; /* Ensure form is above the image */
}

/* Ensure image is behind the form */
img {
  z-index: 0;
}
</style>