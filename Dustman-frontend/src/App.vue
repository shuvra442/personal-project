<template>
    <div class="h-full relative scroll-smooth">
        <nav
            class="h-12 w-full fixed top-0 z-50 flex flex-row justify-between px-5 bg-transparent bg-opacity-70"
        >
            <div class="flex items-center justify-between px-4">
                <img
                    class="rounded-[50%] h-9"
                    src="/src/assets/dust-logo.png"
                    alt="dust"
                />
            </div>

            <!-- Desktop Navigation -->
            <div
                class="hidden md:flex backdrop-blur-3xl rounded-4xl mt-2 w-fit h-full items-center justify-between px-4 navbg"
            >
                <router-link
                    v-for="(item, index) in navItems"
                    :key="index"
                    :to="item.label === 'Login' ? '' : item.path"
                    class="text-gray-700 hover:text-blue-500 transition-colors flex items-center justify-center flex-row"
                    active-class="text-blue-500 font-medium"
                    @click.prevent="
                        item.label === 'Login'
                            ? showLoginModal()
                            : item.label === 'Logout'
                              ? handleLogout()
                              : null
                    "
                >
                    <div
                        v-motion
                        :initial="{ opacity: 0, x: 20 }"
                        :enter="{ opacity: 1, x: 0 }"
                        :transition="{ duration: 1, delay: index * 1 }"
                        class="box h-full w-fit px-2"
                    >
                        {{ item.label }}
                    </div>
                </router-link>
            </div>

            <!-- Mobile Hamburger -->
            <div class="md:hidden flex items-center">
                <button
                    class="text-gray-700 hover:text-blue-500 transition-colors cursor-pointer"
                    @click="handleMobileMenuToggle"
                >
                    <AlignLeft />
                </button>
            </div>

            <!-- Mobile Menu -->
            <div
                v-if="showMobileMenu"
                class="md:hidden absolute top-12 left-0 w-full backdrop-blur-md shadow-lg z-40 flex flex-col items-center space-y-2 py-4"
            >
                <router-link
                    v-for="(item, index) in navItems"
                    :key="index"
                    :to="item.label === 'Login' ? '' : item.path"
                    class="text-gray-700 hover:text-blue-500 transition-colors flex items-center justify-center flex-col"
                    active-class="text-blue-500 font-medium"
                    @click.prevent="
                        item.label === 'Login'
                            ? showLoginModal()
                            : item.label === 'Logout'
                              ? (handleLogout(), (showMobileMenu = false))
                              : (showMobileMenu = false)
                    "
                >
                    <motion-div
                        class="box h-full w-fit px-2"
                        :initial="{ opacity: 0, x: 20 }"
                        :animate="{ opacity: 1, x: 0 }"
                        :transition="{ duration: 0.5, delay: index * 0.1 }"
                    >
                        {{ item.label }}
                    </motion-div>
                </router-link>
            </div>
        </nav>

        <!-- Login Modal -->
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
                />
            </motion-div>
        </div>

        <!--  Sidebar -->
        <div class="flex w-screen overflow-hidden relative">
            <div
                :class="[
                    'h-screen pt-14 relative transition-all duration-300 ease-in-out shadow-md bg-gradient-to-b from-[#A3C7E1] to-[#69a9d6]',
                    openSidePanel ? 'w-60' : 'w-14',
                ]"
                v-show="activeSidepanel"
            >
                <div class="pt-4 text-white h-full flex flex-col">
                    <div class="flex flex-col items-center px-2">
                        <div
                            v-motion
                            :initial="{ opacity: 0 }"
                            :enter="{ opacity: 1 }"
                            :transition="{ duration: 0.051 }"
                            :class="[
                                'rounded-full border-2 overflow-hidden transition-all duration-300 ease-in-out flex-shrink-0',
                                openSidePanel
                                    ? 'w-20 h-20 border-[#6daedd]'
                                    : 'w-10 h-10 border-[#5ea8dd]',
                            ]"
                        >
                            <img
                                src="https://ik.imagekit.io/eur1zq65p/Contact/emoji9_cbqsta.png"
                                alt="profile"
                                class="h-full w-full object-cover"
                            />
                        </div>

                        <transition name="fade">
                            <div v-if="openSidePanel" class="mt-3 text-center">
                                <p class="font-medium text-[#193d5a] text-sm">
                                    Sourav
                                </p>
                                <p class="text-xs text-[#1b4766] mt-1">
                                    Admin User
                                </p>
                            </div>
                        </transition>
                    </div>

                    <!-- Sidebar Items -->
                    <div class="mt-8 flex-1 overflow-y-auto">
                        <div
                            v-for="(item, index) in slideItems"
                            :key="index"
                            class="flex items-center py-3 px-4 cursor-pointer group"
                            :class="[
                                openSidePanel
                                    ? 'hover:bg-[#A3C7E1]'
                                    : 'hover:bg-[#A3C7E1]',
                                activeItem === index
                                    ? 'bg-[#A3C7E1] border-l-4 rounded-l-lg border-[#2e5774]'
                                    : '',
                            ]"
                            @click="setActiveItem(index, item.path)"
                            v-motion
                            :initial="{ opacity: 0, y: 60 }"
                            :enter="{ opacity: 1, y: 0 }"
                            :transition="{
                                duration: 10,
                                delay: 1,
                            }"
                        >
                            <component
                                :is="item.icon"
                                :class="[
                                    'transition-transform duration-300',
                                    activeItem === index
                                        ? 'text-[#133e5c] scale-110'
                                        : 'text-[#133e5c]',
                                    openSidePanel ? 'mr-3' : 'mx-auto',
                                ]"
                                :size="20"
                            />
                            <transition name="slide">
                                <span
                                    v-if="openSidePanel"
                                    class="text-[#133e5c] font-medium text-sm truncate transition-opacity duration-300"
                                    :class="{
                                        'font-bold': activeItem === index,
                                    }"
                                >
                                    {{ item.name }}
                                </span>
                            </transition>
                        </div>
                    </div>

                    <!-- <div class="mt-8 flex-1 overflow-y-auto">
                        <div
                            v-for="(item, index) in slideItems"
                            :key="index"
                            v-if="loaded"
                            v-motion
                            :initial="{ opacity: 0, x: 20 }"
                            :enter="{ opacity: 1, x: 0 }"
                            :transition="{ duration: 0.5, delay: index * 14 }"
                            class="flex items-center py-3 px-4 cursor-pointer group"
                            :class="[
                                openSidePanel
                                    ? 'hover:bg-[#A3C7E1]'
                                    : 'hover:bg-[#A3C7E1]',
                                activeItem === index
                                    ? 'bg-[#A3C7E1] border-l-4 rounded-l-lg border-[#2e5774]'
                                    : '',
                            ]"
                            @click="setActiveItem(index, item.path)"
                        >
                            <component
                                :is="item.icon"
                                :class="[
                                    'transition-transform duration-300',
                                    activeItem === index
                                        ? 'text-[#133e5c] scale-110'
                                        : 'text-[#133e5c]',
                                    openSidePanel ? 'mr-3' : 'mx-auto',
                                ]"
                                :size="20"
                            />
                            <transition name="slide">
                                <span
                                    v-if="openSidePanel"
                                    class="text-[#133e5c] font-medium text-sm truncate transition-opacity duration-300"
                                    :class="{
                                        'font-bold': activeItem === index,
                                    }"
                                >
                                    {{ item.name }}
                                </span>
                            </transition>
                        </div>
                    </div> -->
                </div>

                <!-- Sidebar Toggle -->
                <button
                    class="absolute bottom-8 cursor-pointer -right-3 bg-white rounded-full p-1 shadow-lg border border-amber-300 z-10 transition-all hover:scale-110"
                    @click="openSidePanels"
                >
                    <ChevronLeft
                        v-if="openSidePanel"
                        class="text-amber-600 w-5 h-5"
                    />
                    <ChevronRight v-else class="text-amber-600 w-5 h-5" />
                </button>
            </div>

            <!-- Main View -->
            <router-view
                :key="route.fullPath"
                class="h-screen overflow-y-auto"
            />
        </div>
    </div>
</template>

<script lang="ts">
import {
    defineComponent,
    ref,
    reactive,
    toRefs,
    computed,
    onMounted,
    watch,
} from "vue";
import { useRoute, useRouter } from "vue-router";
import { useSampleStore } from "./stores/sample";
import Login from "./components/common/Login.vue";
import {
    AlignLeft,
    ChevronLeft,
    ChevronRight,
    CircleHelpIcon,
    CircleUserRound,
    HouseIcon,
    IndianRupeeIcon,
    ListOrdered,
    Settings,
    UserPen,
    UserPlus,
} from "lucide-vue-next";
import { MotionDirective as motion } from "@vueuse/motion";
import { useToast } from "vue-toast-notification";

export default defineComponent({
    name: "App",
    directives: {
        motion: motion(),
    },
    components: {
        Login,
        AlignLeft,
        ChevronLeft,
        ChevronRight,
        CircleUserRound,
        UserPlus,
        UserPen,
    },
    setup() {
        const route = useRoute();
        const router = useRouter();
        const sampleStore = useSampleStore();

        const activeSidepanel = ref(false);
        const openSidePanel = ref(false);
        const showModal = ref(false);
        const showMobileMenu = ref(false);
        const activeItem = ref(0);

        const isLoginMode = computed(() => sampleStore.getLoginState);
        const navItems = computed(() => [
            { label: "Home", path: "/home" },
            { label: "About", path: "/about" },
            { label: "Contact Us", path: "/contact" },
            {
                label: isLoginMode.value ? "Logout" : "Login",
                path: isLoginMode.value ? "/logout" : "/",
            },
        ]);

        const state = reactive({
            // navItems: [
            //     { label: "Home", path: "/home" },
            //     { label: "About", path: "/about" },
            //     { label: "Contact Us", path: "/contact" },
            //     { label: isLoginMode.value ? "Logout" : "Login", path: "/" },
            // ],
            //             const navItems = computed(() => [
            //     { label: "Home", path: "/home" },
            //     { label: "About", path: "/about" },
            //     { label: "Contact Us", path: "/contact" },
            //     {
            //         label: isLoginMode.value ? "Logout" : "Login",
            //         path: isLoginMode.value ? "/logout" : "/",
            //     },
            // ])

            slideItems: [
                { icon: CircleUserRound, name: "Profile", path: "/profile" },
                { icon: HouseIcon, name: "Dashboard", path: "/dashboard" },
                { icon: ListOrdered, name: "Booking", path: "/booking" },
                { icon: IndianRupeeIcon, name: "Payment", path: "/payment" },
                { icon: Settings, name: "Setting", path: "/setting" },
                { icon: CircleHelpIcon, name: "Help & Support", path: "/help" },
            ],
        });

        const setActiveItem = (index: number, path: string) => {
            activeItem.value = index;
            router.push(path);
        };

        const updateActiveItemFromRoute = () => {
            const index = state.slideItems.findIndex(
                (item) => item.path === route.path
            );
            if (index !== -1) {
                activeItem.value = index;
            }
        };

        watch(
            () => route.path,
            () => {
                updateActiveItemFromRoute();
                activeSidepanel.value = route.path !== "/";
            },
            { immediate: true }
        );

        const showLoginModal = () => {
            showModal.value = true;
        };

        const handleMobileMenuToggle = () => {
            showMobileMenu.value = !showMobileMenu.value;
        };

        const handleLogin = () => {
            showModal.value = false;
        };

        const openSidePanels = () => {
            openSidePanel.value = !openSidePanel.value;
        };
        const slideAnimation = {
            initial: { opacity: 0 },
            enter: { opacity: 1, transition: { duration: 150 } },
        };

        onMounted(() => {
            sampleStore.fetchSampleData();
            updateActiveItemFromRoute();
            setTimeout(() => {
                loaded.value = true;
            }, 100); // you can tweak this delay if needed
        });

        const loaded = ref(false);
        const toast = useToast();
        const handleLogout = () => {
            sampleStore.setUserInfo(false, "", "");
            toast.success("Logout sucessfully");
            router.push("/");
        };

        return {
            ...toRefs(state),
            route,
            showModal,
            showLoginModal,
            handleLogin,
            handleMobileMenuToggle,
            showMobileMenu,
            isLoginMode,
            activeSidepanel,
            openSidePanels,
            openSidePanel,
            activeItem,
            setActiveItem,
            slideAnimation,
            loaded,
            navItems,
            handleLogout,
        };
    },
});
</script>

<style scoped></style>
