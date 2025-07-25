<!-- <template>
  <v-container class="fill-height d-flex justify-center align-center">
    <v-card max-width="480" class="pa-6 elevation-4 rounded-lg">
      <v-card-title class="justify-center">
        <span class="text-h5 font-weight-bold text-success">Create Your Dustman Account</span>
      </v-card-title>

      <v-card-text>
        <v-form @submit.prevent="registerUser" ref="form" class="mt-4">
          <v-text-field
            v-model="user.name"
            label="Full Name"
            variant="outlined"
            color="success"
            prepend-inner-icon="mdi-account"
            required
          ></v-text-field>

          <v-text-field
            v-model="user.email"
            label="Email"
            variant="outlined"
            color="success"
            type="email"
            prepend-inner-icon="mdi-email"
            required
          ></v-text-field>

          <v-text-field
            v-model="user.password"
            label="Password"
            variant="outlined"
            color="success"
            type="password"
            prepend-inner-icon="mdi-lock"
            required
          ></v-text-field>

          <v-btn
            type="submit"
            class="mt-4"
            color="success"
            size="large"
            block
            elevation="2"
          >
            Create Account
          </v-btn>

          <v-divider class="my-6"></v-divider>

          <v-btn
            disabled
            class="mb-2"
            block
            variant="outlined"
          >
            <v-img
              src="@/assets/google.png"
              alt="Google"
              width="20"
              height="20"
              class="mr-2"
            />
            Sign up with Google
          </v-btn>

          <div class="text-center mt-4">
            <span>Already have an account?</span>
            <span
              class="text-success font-weight-medium cursor-pointer"
              to="/login" @click="$router.push('/login')" style="text-decoration: underline;"
            >
              Sign in
            </span>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from 'vue';

const user = ref({
  name: '',
  email: '',
  password: ''
});

const form = ref(null);

const registerUser = () => {
  if (form.value.validate()) {
    console.log('Registering user:', user.value);
    // Add your API call logic here (Axios/fetch)
  }
};
</script> -->


<template>
  <v-app>
    <v-main class="overflow-hidden">
      <v-img
        src="https://img.freepik.com/free-photo/plant-twigs-leaves-near-laptop_23-2147931802.jpg?semt=ais_hybrid&w=740"
        cover
        class="h-screen"
        gradient="to bottom right, rgba(0,0,0,0.6), rgba(0,0,0,0.6)"
      >
        <v-container class="fill-height d-flex align-center justify-center">
          <v-card class="pa-6 rounded-lg" max-width="800" elevation="10" style="width: 100%; backdrop-filter: blur(10px); background-color: rgba(255,255,255,0.85);">
            <h2 class="text-h5 font-weight-bold mb-1 text-center text-green-darken-4">
              Create Your Dustman Account
            </h2>
            <p class="text-center text-subtitle-2 mb-4 text-grey-darken-4">
              Register to join the Dustman community
            </p>

            <v-form ref="formRef" @submit.prevent="handleRegister">
              <v-row dense>
                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.name" label="Full Name" prepend-inner-icon="mdi-account" variant="outlined" density="compact" required />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.email" label="Email" prepend-inner-icon="mdi-email" type="email" variant="outlined" density="compact" required />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.phoneNo" label="Phone Number" prepend-inner-icon="mdi-phone" type="tel" variant="outlined" density="compact" />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.password" label="Password" prepend-inner-icon="mdi-lock" type="password" variant="outlined" density="compact" required />
                </v-col>

                <v-col cols="12" >
                  <v-text-field v-model="user.address" label="Address" prepend-inner-icon="mdi-map-marker" variant="outlined" density="compact" />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-select v-model="user.role" :items="roles" label="Role" prepend-inner-icon="mdi-account-badge" variant="outlined" density="compact" required />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-file-input v-model="user.userImage" label="Profile Image" prepend-inner-icon="mdi-image" append-icon="" accept="image/*" variant="outlined" density="compact" />
                </v-col>
              </v-row>

              <v-btn type="submit" color="green-darken-3" class="mt-4" block :loading="loading">
                Create Account
              </v-btn>

              <div class="text-center my-3 text-caption">— Or sign up with —</div>

              <div class="d-flex justify-center" style="gap: 0.75rem;">
                <v-btn variant="outlined" color="red" prepend-icon="mdi-google" class="text-caption">Google</v-btn>
                <v-btn variant="outlined" color="blue" prepend-icon="mdi-facebook" class="text-caption">Facebook</v-btn>
              </div>

              <div class="mt-4 text-caption text-center">
                Already have an account?
                <v-btn variant="text" size="small" class="text-green-darken-4 pa-0 text-decoration-underline cursor-pointer" @click="$router.push('/login')" to="/login">Sign in</v-btn>
              </div>
            </v-form>
          </v-card>
        </v-container>
      </v-img>
    </v-main>
  </v-app>
</template>


<script setup>
import { ref } from 'vue';

const formRef = ref(null);
const loading = ref(false);

const roles = ['USER', 'ADMIN'];

const user = ref({
  name: '',
  email: '',
  password: '',
  phoneNo: '',
  address: '',
  role: '',
  userImage: null,
});

const handleRegister = () => {
  if (!formRef.value.validate()) return;
  loading.value = true;

  const formData = new FormData();
  Object.keys(user.value).forEach(key => {
    formData.append(key, user.value[key]);
  });

  setTimeout(() => {
    console.log('Form submitted:', Object.fromEntries(formData));
    loading.value = false;
  }, 1500);
};
</script>

