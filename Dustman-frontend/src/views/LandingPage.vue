<template>
    <!-- Navbar -->
    <!-- <nav class="flex items-center justify-between p-4 flex-wrap shadow-md sticky top-0 z-50"
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
  </nav> -->

    <!-- add -->
    <section
        class="min-h-screen flex items-center justify-center px-8 overflow-hidden w-full"
        style="background-color: #b2d1e6"
    >
        <div
            class="container mx-auto flex flex-col md:flex-row items-center justify-between gap-10 py-16"
        >
            <!-- Left Text Section -->
            <div class="md:w-1/2 space-y-2" data-aos="fade-right">
                <h1 class="text-4xl font-bold text-gray-800 leading-tight">
                    Turn Waste into Worth
                </h1>
                <p class="text-lg text-gray-600">
                    Book your dust pickup slot and help build a cleaner, greener
                    future. Dust sellers receive quality waste with zero hassle!
                </p>
                <ul class="space-y-2 text-gray-700">
                    <li>✅ Book a Slot Easily</li>
                    <li>✅ Connect with Dust Sellers</li>
                    <li>✅ Eco-Friendly Disposal</li>
                </ul>
                <div class="flex gap-4">
                    <button class="custom-button px-6" @click="bookNow">
                        Book Now
                    </button>
                    <button
                        class="bg-gray-200 text-gray-800 px-6 py-2 rounded-lg hover:bg-gray-300"
                    >
                        Learn More
                    </button>
                </div>
            </div>

            <!-- Right Image Section -->
            <div class="md:w-1/2" data-aos="fade-left">
                <img
                    src="../assets/dustImage.png"
                    alt="Dust pickup illustration"
                    class="w-full rounded-xl"
                />
            </div>
        </div>
    </section>
    <div
        v-if="showModal"
        class="fixed inset-0 backdrop-blur-md bg-opacity-50 flex items-center justify-center z-50"
        @click.self="showModal = false"
    >
        <motion-div
            :initial="{ opacity: 0, scale: 0.8 }"
            :animate="{ opacity: 1, scale: 1 }"
            :transition="{ duration: 0.3 }"
            class="shadow-lg w-full max-w-full"
        >
            <Login
                :show-currencypdf-modals="true"
                @close-popup-modal="showModal = false"
            ></Login>
        </motion-div>
    </div>
    <!-- <LoginCommon
        :show-currencypdf-modals="showLogin"
        @close-popup-modal="closeLogin"
    /> -->
</template>

<script lang="ts">
// import LoginCommon from "@/components/common/LoginCommon.vue";
import Login from "@/components/common/Login.vue";
import { useLoinRegStore } from "@/stores/login/LoginRegStore";
// import { useSampleStore } from "@/stores/sample";
import { reactive, ref, toRefs } from "vue";
import { useRouter } from "vue-router";

export default {
    components: {
        // LoginCommon,
        Login,
    },

    setup() {
        const showModal = ref(false);
        const route = useRouter();
        const isLogin = useLoinRegStore().getIsLogin;
        const bookNow = () => {
            if (isLogin) {
                route.push("/dashboard");
            } else {
                showModal.value = true;
                //navigate to "/dashboard"
            }
        };
        const state = reactive({
            showLogin: false,
            closeLogin: false,
        });
        const showWindowForLogin = () => {
            state.showLogin = true; // Show the popup if toggle is ON
        };

        // Function to handle the "Close" (X) button event from the popup
        const closeLogin = (accept: Event) => {
            state.showLogin = false;
            if (accept) {
                // state.currencyChecked = true // If "I Accept" is clicked, keep the toggle ON
            } else {
                // state.currencyChecked = false // If close (X) is clicked, turn the toggle OFF
            }
        };

        return {
            ...toRefs(state),
            showWindowForLogin,
            closeLogin,
            showModal,
            bookNow,
        };
    },
};
</script>

<style scoped></style>
