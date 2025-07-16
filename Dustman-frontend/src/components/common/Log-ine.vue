<template>
    <div class="flex justify-center items-center w-full">
        <div class="bg-white rounded-xl overflow-hidden shadow-xl w-full max-w-4xl h-[600px] flex flex-col">

            <!-- Form Container -->
            <div class="flex flex-1 overflow-hidden">
                <!-- Left Side (Form) -->
                <div class="w-full md:w-1/2 p-8 overflow-hidden">
                    <!-- Login Form -->
                    <div v-if="isLogin" class="h-full flex flex-col justify-center">
                        <span class="text-2xl font-bold text-center mb-4 text-[#6AB092]"> Good to see you again! </span>
                        <div class="space-y-6 mt-3">
                            <div class="relative">
                                <input v-model="email" type="text" placeholder="Email (abc10@gmail.com)"
                                    class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all" />
                                <span
                                    class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"></span>
                            </div>

                            <div class="relative">
                                <input v-model="password" type="password" placeholder="Password (xxxxxx)"
                                    class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all" />
                                <span
                                    class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"></span>
                            </div>

                            <button class="w-full py-3 px-6 bg-[#6AB092] text-white rounded-lg font-medium flex items-center justify-center hover:bg-[#5a9a80] hover:-translate-y-0.5 active:translate-y-0 transition-all shadow-md hover:shadow-lg"
                             @click="handellogin()"
                            >
                                Sign in
                            </button>

                            <div class="relative flex items-center py-4">
                                <div class="flex-grow border-t border-gray-200"></div>
                                <span class="flex-shrink mx-4 text-gray-500 text-sm">or continue with</span>
                                <div class="flex-grow border-t border-gray-200"></div>
                            </div>

                            <button
                                class="w-full py-2 px-4 border border-gray-200 rounded-lg flex items-center justify-center hover:bg-gray-50 transition-colors disabled cursor-not-allowed">
                                <img src="@/assets/google.png" alt="Google" class="w-5 h-5 mr-2" />
                                Google
                            </button>

                            <p class="text-center text-gray-600 mt-4">
                                Don't have an account?
                                <span
                                    class="text-[#6AB092] font-medium cursor-pointer hover:text-[#5a9a80] transition-colors"
                                    @click="isLogin = !isLogin">
                                    Sign up
                                </span>
                            </p>
                        </div>
                    </div>

                    <div v-else>
                        <span class="text-2xl font-bold text-center mb-6 text-[#6AB092]">Create Your Dustman
                            Account</span>
                        <div class="space-y-6 mt-4">
                            <div class="relative mt-4">
                                <input v-model="name" type="text" placeholder="Full Name"
                                    class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all" />
                                
                            </div>

                            <div class="relative">
                                <input v-model="regEmail"  type="email" placeholder="Email"
                                    class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all" />
                                <span
                                    class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"></span>
                            </div>

                            <div class="relative">
                                <input v-model="regPass" type="password" placeholder="Password"
                                    class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all" />
                                <span
                                    class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"></span>
                            </div>

                            <button
                                class="w-full py-3 px-6 bg-[#6AB092] text-white rounded-lg font-medium flex items-center justify-center hover:bg-[#5a9a80] hover:-translate-y-0.5 active:translate-y-0 transition-all shadow-md hover:shadow-lg">
                                Create Account
                            </button>

                            <div class="relative flex items-center py-4">
                                <div class="flex-grow border-t border-gray-200"></div>
                                <span class="flex-shrink mx-4 text-gray-500 text-sm">or sign up with</span>
                                <div class="flex-grow border-t border-gray-200"></div>
                            </div>

                            <button
                                class="w-full py-2 px-4 border border-gray-200 rounded-lg flex items-center justify-center hover:bg-gray-50 transition-colors cursor-not-allowed disabled">
                                <img src="@/assets/google.png" alt="Google" class="w-5 h-5 mr-2" />
                                Google
                            </button>

                            <div class="text-center text-gray-600 mt-4">
                                Already have an account?
                                <span
                                    class="text-[#6AB092] font-medium cursor-pointer hover:text-[#5a9a80] transition-colors"
                                    @click="isLogin = !isLogin">Sign in</span>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- Right Side -->
                <div class="hidden md:flex w-1/2 bg-white p-8 flex-col justify-center items-center overflow-hidden">
                    <div key="login-visual" class="text-center w-full h-full flex flex-col justify-center">
                        <img src="@/assets/login-image.png" alt="Login"
                            class="w-64 h-64 mx-auto mb-8 transition-transform duration-500 hover:scale-105" />
                        <h3 class="text-2xl font-bold text-gray-800 mb-2">
                            Welcome Back!
                        </h3>
                        <p class="text-gray-600">
                            Sign in to access your personalized
                            dashboard
                        </p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { loginType } from '@/stores/login/LoginReg';
import { useLoinRegStore } from '@/stores/login/LoginRegStore';
import { defineComponent, reactive, toRefs } from 'vue';
import { useRouter } from 'vue-router';


export default defineComponent({
    components: {

    },

    setup() {

        const store = useLoinRegStore()
        const router = useRouter()

        const state = reactive({
            isLogin: true,

            // Login
            email: '',
            password: '',

            // reg
            name: '',
            regEmail: '',
            regPass: '',
        })

        const handellogin = () => {
            const logInPayload: loginType = {
                email: state.email,
                password: state.password
            };
            console.log("called handeller --> ", logInPayload.email, logInPayload.password);
            store.fetchLoginData(logInPayload);
            router.push("/home")
        };


        return {
            ...toRefs(state),
            store,
            handellogin
        }
    }

})

</script>