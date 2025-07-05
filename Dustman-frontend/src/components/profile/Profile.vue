<template>
    <div
        v-if="edit"
        class="fixed inset-0 z-50 flex items-center justify-center bg-black/15 backdrop-blur-md"
    >
        <EditProfile :popup="edit" @close="edit = false" />
        <!-- <EditProfile :popup="edit" @close="edit = false" /> -->
    </div>

    <div
        class="h-full bg-mint flex justify-center p-6 font-sans overflow-y-scroll"
        v-motion="{
            initial: { opacity: 0, x: 300 },
            enter: { opacity: 1, x: 0 },
            transition: { duration: 100 },
        }"
    >
        <div class="w-full max-w-2xl flex flex-col gap-12 h-fit">
            <div class="text-center">
                <h1 class="text-3xl md:text-4xl font-bold text-gray-800">
                    Dustman Profile
                </h1>
                <p class="text-gray-600 mt-2">
                    Manage your account and upgrade options
                </p>
            </div>

            <div
                class="bg-white rounded-2xl shadow-md overflow-hidden profile-card"
            >
                <div class="md:flex">
                    <div
                        class="md:w-1/3 bg-emerald-800 text-white p-6 flex items-center justify-center"
                    >
                        <button
                            class="absolute top-[-4rem] right-[-4rem] block md:hidden cursor-pointer"
                            @click="activateEditButton"
                        >
                            <Edit class="text-black" />
                        </button>
                        <!-- <button
                            class="relative top-[-5rem] right-[-20rem] cursor-pointer"
                            @click="activateEditButton"
                        >
                            <Edit class="text-black" />
                        </button> -->
                        <div class="flex flex-col items-center mb-8">
                            <div
                                class="h-24 w-24 rounded-full overflow-hidden mb-4 border-4 border-sky"
                            >
                                <img
                                    :src="userInfo.profile"
                                    alt="Profile"
                                    class="object-cover w-full h-full"
                                />
                            </div>
                            <h2 class="text-xl font-semibold">
                                {{ userInfo.name }}
                            </h2>
                            <div class="text-sage text-sm mt-1">
                                {{ userInfo.createdAt }}
                            </div>
                        </div>
                    </div>

                    <!-- Main Content -->
                    <div class="md:w-2/3 p-6 pl-14">
                        <div class="flex items-center gap-28 relative">
                            <h2 class="text-2xl font-bold text-gray-800 mb-6">
                                Profile Information
                            </h2>
                            <button
                                class="absolute top-6 right-6 hidden md:block cursor-pointer"
                                @click="activateEditButton"
                            >
                                <Edit class="text-black" />
                            </button>
                            <!-- <button
                                class="relative top-[-1.5rem] right-[-1.9rem] cursor-pointer sm:hidden"
                                @click="activateEditButton"
                            >
                                <Edit />
                            </button> -->
                        </div>
                        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                            <div>
                                <label class="block text-sm text-gray-500 mb-1"
                                    >Full Name</label
                                >
                                <div
                                    class="text-lg font-semibold text-gray-800"
                                >
                                    {{ userInfo.name }}
                                </div>
                            </div>
                            <div>
                                <label class="block text-sm text-gray-500 mb-1"
                                    >Email Address</label
                                >
                                <div class="text-lg font-medium text-gray-700">
                                    {{ userInfo.email }}
                                </div>
                            </div>
                            <div>
                                <label class="block text-sm text-gray-500 mb-1"
                                    >Phone Number</label
                                >
                                <div class="text-lg text-gray-700">
                                    {{ userInfo.mobile }}
                                </div>
                            </div>
                            <div>
                                <label class="block text-sm text-gray-500 mb-1"
                                    >Account Type</label
                                >
                                <div>
                                    <span
                                        class="inline-block px-3 py-1 bg-sage text-emerald-900 rounded-full text-sm font-medium"
                                    >
                                        Regular User
                                    </span>
                                </div>
                            </div>
                            <div class="md:col-span-2">
                                <label class="block text-sm text-gray-500 mb-1"
                                    >Address</label
                                >
                                <div class="text-lg text-gray-700">
                                    {{ userInfo.address }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import sampleData from "@/stores/sampleData";
import { Edit, X } from "lucide-vue-next";
import { ref } from "vue";
import { useToast } from "vue-toast-notification";
import EditProfile from "./EditProfile.vue";

const apiKey = import.meta.env.VITE_GEOAPIFY_API_KEY;
const toast = useToast();
const edit = ref(false);
const activateEditButton = () => {
    edit.value = !edit.value;
    toast.success("It's work");
    console.log(apiKey);
};

const fadeInTop = {
    initial: { opacity: 0 },
    enter: { opacity: 1, transition: { duration: 150 } },
};

const userInfo = sampleData.user;
</script>
