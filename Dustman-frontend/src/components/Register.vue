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
          <v-card
            class="pa-6 rounded-lg"
            max-width="800"
            elevation="10"
            style="width: 100%; backdrop-filter: blur(10px); background-color: rgba(255,255,255,0.85);"
          >
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
                  <v-text-field v-model="user.address" label="Address" prepend-inner-icon="mdi-map-marker" variant="outlined" density="compact" />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.password" label="Password" prepend-inner-icon="mdi-lock" type="password" variant="outlined" density="compact" required />
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field v-model="user.confirmPassword" label="Confirm Password" prepend-inner-icon="mdi-lock" type="password" variant="outlined" density="compact" required />
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
                <v-btn
                  variant="text"
                  size="small"
                  class="text-green-darken-4 pa-0 text-decoration-underline cursor-pointer"
                  @click="$router.push('/login')"
                  to="/login"
                >
                  Sign in
                </v-btn>
              </div>
            </v-form>
          </v-card>
        </v-container>
      </v-img>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, ref } from "vue";
import { useRouter } from "vue-router";
import { useLoinRegStore } from "@/stores/login/LoginRegStore";
import type { regType } from "@/stores/login/LoginReg";

export default defineComponent({
  name: "RegisterPage",

  setup() {
    const router = useRouter();
    const store = useLoinRegStore();
    const formRef = ref();

    const state = reactive({
      user: {
        name: "",
        email: "",
        phoneNo: "",
        address: "",
        password: "",
        confirmPassword: "",
        userImage: null as File | null,
      } as regType & { confirmPassword?: string },
      loading: false,
    });

    const handleRegister = async () => {
      const form = formRef.value;
      const isValid = await form?.validate();

      if (!isValid) {
        console.warn("Form validation failed");
        return;
      }

      if (state.user.password !== state.user.confirmPassword) {
        alert("Passwords do not match!");
        return;
      }

      state.loading = true;

      const regPayload: regType = {
        name: state.user.name,
        email: state.user.email,
        phoneNo: state.user.phoneNo,
        address: state.user.address,
        password: state.user.password,
        userImage: state.user.userImage,
      };

      await store.fetchRegData(regPayload);

      state.loading = false;
      router.push("/login"); // redirect to login after register
    };

    return {
      ...toRefs(state),
      formRef,
      handleRegister,
    };
  },
});
</script>
