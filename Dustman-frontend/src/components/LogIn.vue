<template>
  <v-app class="h-screen overflow-hidden">
    <v-main class="h-100">
      <v-container fluid class="pa-0 ma-0 fill-height">
        <v-row no-gutters class="fill-height">
          <!-- Left Image Section (Hidden on XS) -->
          <v-col cols="12" md="6" class="d-none d-md-flex">
            <v-img
              src="https://static.vecteezy.com/system/resources/thumbnails/066/074/019/small_2x/a-laptop-computer-sitting-on-a-desk-next-to-a-plant-photo.jpg"
              cover
              class="w-100"
            />
          </v-col>

          <!-- Right Login Section -->
          <v-col
            cols="12"
            md="6"
            class="d-flex flex-column justify-center align-center px-4"
          >
            <v-card
              class="pa-4 rounded-lg elevation-4"
              max-width="400"
              width="100%"
            >
              <!-- Lock Icon -->
              <v-card-title class="d-flex justify-center">
                <v-avatar color="primary" size="48">
                  <v-icon size="26" color="white">mdi-lock</v-icon>
                </v-avatar>
              </v-card-title>

              <!-- Welcome Message -->
              <v-card-title class="text-center text-h6 font-weight-bold">
                Welcome Back!
              </v-card-title>
              <v-card-subtitle class="text-center mb-3 text-body-2 text-grey-darken-4">
                Log into your account
              </v-card-subtitle>

              <!-- Login Form -->
              <v-form ref="fromRef" @submit.prevent="handleLogin">
                <v-text-field
                  v-model="email"
                  label="Email"
                  type="email"
                  variant="outlined"
                  density="compact"
                  class="mb-3"
                  required
                />
                <v-text-field
                  v-model="password"
                  label="Password"
                  type="password"
                  variant="outlined"
                  density="compact"
                  class="mb-1"
                  required
                />

                <!-- Remember + Forgot -->
                <div class="d-flex justify-space-between align-center mb-3">
                  <v-checkbox
                    v-model="remember"
                    label="Remember me"
                    hide-details
                    density="compact"
                    class="ma-0 pa-0"
                  />
                  <v-btn variant="text"  class="pa-0 text-caption text-light-blue-darken-4 font-medium text-2xl"
                  to="/forgetPassword" >
                    Forgot Password
                  </v-btn>
                </div>

                <!-- Submit -->
                <v-btn
                  type="submit"
                  block
                  color="green-darken-3"
                  class="mt-1 text-2xl"
                  :loading="loading"
                >
                  Log In
                </v-btn>

                <!-- Divider -->
                <div class="text-center my-3 text-caption  text-gray-900 ">
                  — Or continue with —
                </div>

                <!-- Social Logins -->
                <div class="d-flex justify-center" style="gap: 0.75rem;">
                  <v-btn
                    variant="outlined"
                    color="red"
                    prepend-icon="mdi-google"
                    class="text-caption"
                  >
                    Google
                  </v-btn>
                  <v-btn
                    variant="outlined"
                    color="blue"
                    prepend-icon="mdi-facebook"
                    class="text-caption"
                  >
                    Facebook
                  </v-btn>
                </div>
              </v-form>
            </v-card>

            <!-- Sign Up -->
            <div class="mt-3 text-caption text-center">
              Don't have an account?
              <v-btn
                variant="text"
                size="small"
                class="text-primary text-decoration-underline pa-0" 
                to="/register"
              >
                Sign up
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useLoinRegStore } from '@/stores/login/LoginRegStore'
import { loginType } from '@/stores/login/LoginReg'

export default defineComponent({
  name: 'LoginPage',

  setup() {
    const router = useRouter()
    const store = useLoinRegStore()
    const formRef = ref() 

    const state = reactive({
      email: '',
      password: '',
      remember: false,
      loading: false
    })

    
    const handleLogin = async () => {
      const form = formRef.value
      const isValid = await form?.validate()

      if (!isValid.valid) {
        console.warn('Form validation failed')
        return
      }

      state.loading = true

      const loginPayload: loginType = {
        email: state.email,
        password: state.password
      }

      console.log('Logging in as:', loginPayload.email)

      setTimeout(() => {
        store.fetchLoginData(loginPayload)
        state.loading = false
        router.push('/home')
      }, 1000)
    }

    return {
      ...toRefs(state),
      formRef,     
      handleLogin
    }
  }
})
</script>

