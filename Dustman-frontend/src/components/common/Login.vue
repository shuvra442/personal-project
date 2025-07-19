<template>
    <div>
        <!-- Modal Overlay -->
        <div
            v-if="showCurrencypdfModals"
            class="fixed top-0 left-0 right-0 bottom-0 bg-opacity-50 flex items-center justify-center z-50 backdrop-blur-sm"
            @click.self="closePopup(false)"
        >
            <!-- Modal Content -->
            <div
                class="bg-white rounded-xl overflow-hidden shadow-xl w-full max-w-4xl h-[600px] flex flex-col"
            >
                <!-- Modal Header -->
                <div class="flex justify-between items-center p-6 border-b">
                    <h2 class="text-2xl font-bold text-gray-800">
                        {{ isLoginMode ? "Welcome Back" : "Create Account" }}
                    </h2>
                    <button
                        class="p-1 rounded-full hover:bg-gray-100 transition-colors"
                        @click="closePopup(false)"
                    >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            class="w-5 h-5"
                        >
                            <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M6 18L18 6M6 6l12 12"
                            />
                        </svg>
                    </button>
                </div>

                <!-- Form Container -->
                <div class="flex flex-1 overflow-hidden">
                    <!-- Left Side (Form) -->
                    <div class="w-full md:w-1/2 p-8 overflow-hidden">
                        <transition
                            :name="
                                isLoginMode
                                    ? 'form-slide-left'
                                    : 'form-slide-right'
                            "
                            mode="out-in"
                        >
                            <!-- Login Form -->
                            <div
                                v-if="isLoginMode"
                                key="login"
                                class="h-full flex flex-col justify-center"
                            >
                                <div class="space-y-6">
                                    <div class="relative">
                                        <input
                                            v-model="loginData.username"
                                            type="text"
                                            placeholder="Username or Email"
                                            class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all"
                                        />
                                        <span
                                            class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"
                                        ></span>
                                    </div>

                                    <div class="relative">
                                        <input
                                            v-model="loginData.password"
                                            type="password"
                                            placeholder="Password"
                                            class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all"
                                        />
                                        <span
                                            class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"
                                        ></span>
                                    </div>

                                    <button
                                        class="w-full py-3 px-6 bg-[#6AB092] text-white rounded-lg font-medium flex items-center justify-center hover:bg-[#5a9a80] hover:-translate-y-0.5 active:translate-y-0 transition-all shadow-md hover:shadow-lg"
                                        @click="handleLogin"
                                    >
                                        <img
                                            src="../../assets/loader.svg"
                                            alt="loading"
                                            v-if="loginProcess"
                                        />
                                        <span v-if="!loginProcess"
                                            >Sign In</span
                                        >
                                        <ArrowRight
                                            v-if="!registerProcess"
                                            class="w-5 h-5 ml-2 transition-transform duration-300 group-hover:translate-x-1"
                                        />
                                    </button>

                                    <div
                                        class="relative flex items-center py-4"
                                    >
                                        <div
                                            class="flex-grow border-t border-gray-200"
                                        ></div>
                                        <span
                                            class="flex-shrink mx-4 text-gray-500 text-sm"
                                            >or continue with</span
                                        >
                                        <div
                                            class="flex-grow border-t border-gray-200"
                                        ></div>
                                    </div>

                                    <button
                                        class="w-full py-2 px-4 border border-gray-200 rounded-lg flex items-center justify-center hover:bg-gray-50 transition-colors"
                                    >
                                        <img
                                            src="@/assets/google.png"
                                            alt="Google"
                                            class="w-5 h-5 mr-2"
                                        />
                                        Google
                                    </button>

                                    <p class="text-center text-gray-600 mt-4">
                                        Don't have an account?
                                        <a
                                            @click="toggleMode"
                                            class="text-[#6AB092] font-medium cursor-pointer hover:text-[#5a9a80] transition-colors"
                                            >Sign up</a
                                        >
                                    </p>
                                </div>
                            </div>

                            <!-- Register Form -->
                            <div
                                v-else
                                key="register"
                                class="h-full flex flex-col justify-center"
                            >
                                <div class="space-y-6">
                                    <div class="relative">
                                        <input
                                            v-model="registerData.fullName"
                                            type="text"
                                            placeholder="Full Name"
                                            class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all"
                                        />
                                        <span
                                            class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"
                                        ></span>
                                    </div>

                                    <div class="relative">
                                        <input
                                            v-model="registerData.email"
                                            type="email"
                                            placeholder="Email"
                                            class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all"
                                        />
                                        <span
                                            class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"
                                        ></span>
                                    </div>

                                    <div class="relative">
                                        <input
                                            v-model="registerData.password"
                                            type="password"
                                            placeholder="Password"
                                            class="w-full px-4 py-3 bg-gray-50 rounded-lg border border-gray-200 focus:outline-none focus:border-[#6AB092] focus:ring-2 focus:ring-[#6AB092]/20 transition-all"
                                        />
                                        <span
                                            class="absolute bottom-0 left-0 h-0.5 bg-[#6AB092] w-0 transition-all duration-300"
                                        ></span>
                                    </div>

                                    <button
                                        class="w-full py-3 px-6 bg-[#6AB092] text-white rounded-lg font-medium flex items-center justify-center hover:bg-[#5a9a80] hover:-translate-y-0.5 active:translate-y-0 transition-all shadow-md hover:shadow-lg"
                                        @click="handleRegister"
                                    >
                                        <img
                                            src="../../assets/loader.svg"
                                            alt="loader"
                                            v-if="registerProcess"
                                        />

                                        <span v-if="!registerProcess"
                                            >Create Account</span
                                        >
                                        <ArrowRight
                                            v-if="!registerProcess"
                                            class="w-5 h-5 ml-2 transition-transform duration-300 group-hover:translate-x-1"
                                        />
                                    </button>

                                    <div
                                        class="relative flex items-center py-4"
                                    >
                                        <div
                                            class="flex-grow border-t border-gray-200"
                                        ></div>
                                        <span
                                            class="flex-shrink mx-4 text-gray-500 text-sm"
                                            >or sign up with</span
                                        >
                                        <div
                                            class="flex-grow border-t border-gray-200"
                                        ></div>
                                    </div>

                                    <button
                                        class="w-full py-2 px-4 border border-gray-200 rounded-lg flex items-center justify-center hover:bg-gray-50 transition-colors"
                                    >
                                        <img
                                            src="@/assets/google.png"
                                            alt="Google"
                                            class="w-5 h-5 mr-2"
                                        />
                                        Google
                                    </button>

                                    <p class="text-center text-gray-600 mt-4">
                                        Already have an account?
                                        <a
                                            @click="toggleMode"
                                            class="text-[#6AB092] font-medium cursor-pointer hover:text-[#5a9a80] transition-colors"
                                            >Sign in</a
                                        >
                                    </p>
                                </div>
                            </div>
                        </transition>
                    </div>

                    <!-- Right Side -->
                    <div
                        class="hidden md:flex w-1/2 bg-white p-8 flex-col justify-center items-center overflow-hidden"
                    >
                        <transition
                            :name="
                                isLoginMode
                                    ? 'image-slide-right'
                                    : 'image-slide-left'
                            "
                            mode="out-in"
                        >
                            <div
                                v-if="isLoginMode"
                                key="login-visual"
                                class="text-center w-full h-full flex flex-col justify-center"
                            >
                                <img
                                    src="@/assets/login-image.png"
                                    alt="Login"
                                    class="w-64 h-64 mx-auto mb-8 transition-transform duration-500 hover:scale-105"
                                />
                                <h3
                                    class="text-2xl font-bold text-gray-800 mb-2"
                                >
                                    Welcome Back!
                                </h3>
                                <p class="text-gray-600">
                                    Sign in to access your personalized
                                    dashboard
                                </p>
                            </div>
                            <div
                                v-else
                                key="register-visual"
                                class="text-center w-full h-full flex flex-col justify-center"
                            >
                                <img
                                    src="@/assets/login-image.png"
                                    alt="Register"
                                    class="w-64 h-64 mx-auto mb-8 transition-transform duration-500 hover:scale-105"
                                />
                                <h3
                                    class="text-2xl font-bold text-gray-800 mb-2"
                                >
                                    Join Us Today!
                                </h3>
                                <p class="text-gray-600">
                                    Create an account to get started
                                </p>
                            </div>
                        </transition>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
// import { login, register } from "@/service/sampleService";
// import { useSampleStore } from "@/stores/sample";
import { ArrowRight } from "lucide-vue-next";
import { defineComponent, reactive, ref, toRefs } from "vue";
import { useRouter } from "vue-router";
import { useToast } from "vue-toast-notification";
export default defineComponent({
    components: {
        ArrowRight,
    },
    props: {
        showCurrencypdfModals: {
            type: Boolean,
            default: false,
        },
    },
    emits: ["closePopupModal"],
    setup(props, { emit }) {
        const router = useRouter();

        // const store = useSampleStore();
        const toast = useToast();
        const loginProcess = ref(false);
        const registerProcess = ref(false);
        const state = reactive({
            loginData: {
                username: "",
                password: "",
            },
            isLoginMode: true,
            registerData: {
                fullName: "",
                email: "",
                password: "",
            },
        });

        const closePopup = (accepted: boolean) => {
            emit("closePopupModal", accepted);
        };

        const toggleMode = () => {
            state.isLoginMode = !state.isLoginMode;
        };

        const handleLogin = async () => {
            console.log("Login Data=>", state.loginData);
            try {
                loginProcess.value = true;
                // const response = await login(state.loginData);
                // store.setUserInfo(true, response.email, response.role);
                // console.log("store.getUserInfo=>", store.getUserInfo);
                loginProcess.value = false;
                toast.success("Login Successfully");
                router.push("/home");
            } catch {
            } finally {
                registerProcess.value = true;
            }
        };

        const handleRegister = async () => {
            console.log("Login Data=>", state.registerData);
            registerProcess.value = true;
            try {
                // await register(state.registerData);
            } catch {
            } finally {
                registerProcess.value = false;
            }
        };

        return {
            ...toRefs(state),
            closePopup,
            toggleMode,
            handleLogin,
            handleRegister,
            loginProcess,
            registerProcess,
        };
    },
});
</script>

<style scoped>
.form-slide-left-enter-active,
.form-slide-left-leave-active,
.form-slide-right-enter-active,
.form-slide-right-leave-active {
    transition: all 0.5s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.form-slide-left-enter-from {
    opacity: 0;
    transform: translateX(-100%);
}
.form-slide-left-leave-to {
    opacity: 0;
    transform: translateX(100%);
}

.form-slide-right-enter-from {
    opacity: 0;
    transform: translateX(100%);
}
.form-slide-right-leave-to {
    opacity: 0;
    transform: translateX(-100%);
}

.image-slide-right-enter-active,
.image-slide-right-leave-active,
.image-slide-left-enter-active,
.image-slide-left-leave-active {
    transition: all 0.5s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.image-slide-right-enter-from {
    opacity: 0;
    transform: translateX(100%);
}
.image-slide-right-leave-to {
    opacity: 0;
    transform: translateX(-100%);
}

.image-slide-left-enter-from {
    opacity: 0;
    transform: translateX(-100%);
}
.image-slide-left-leave-to {
    opacity: 0;
    transform: translateX(100%);
}

input:focus + span {
    width: 100%;
}
</style>
