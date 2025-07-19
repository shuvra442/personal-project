<template>
    <div
        v-motion
        :initial="{ opacity: 0, x: 20 }"
        :enter="{ opacity: 1, x: 0 }"
        :transition="{ duration: 400 }"
        class="fixed inset-0 z-50 bg-black/20 backdrop-blur-md flex justify-center items-center"
    >
        <div
            class="bg-white rounded-xl shadow-xl w-full max-w-lg p-6 text-gray-800 relative"
        >
            <!-- Close Button -->
            <button
                @click="activateEditButton"
                class="absolute top-4 right-4 text-gray-600 hover:text-red-500 cursor-pointer"
            >
                <X />
            </button>

            <!-- Title -->
            <h2 class="text-2xl font-bold mb-6 text-center">Edit Profile</h2>

            <!-- Profile Image Upload -->
            <!-- <div class="flex justify-center mb-6">
                <div class="relative h-24 w-24">
                    <input
                        type="file"
                        name="photo"
                        id="photo"
                        class="absolute inset-0 z-10 opacity-0 cursor-pointer"
                    />
                     <img
                        :src="previewImage || userInfo.profile"
                        alt="Profile"
                        class="h-24 w-24 rounded-full object-cover border-4 border-emerald-500 z-0"
                    /> 
                    <input
                        type="file"
                        name="photo"
                        id="photo"
                        accept="image/*"
                        @change="onFileChange"
                        class="absolute inset-0 z-10 opacity-0 cursor-pointer"
                    />
                </div>
            </div> -->
            <!-- Profile Image Upload -->
            <div class="flex justify-center mb-6">
                <div class="relative h-24 w-24">
                    <input
                        type="file"
                        name="photo"
                        id="photo"
                        accept="image/*"
                        @change="onFileChange"
                        class="absolute inset-0 z-10 opacity-0 cursor-pointer"
                    />
                    <img
                        :src="previewImage || userInfo.profile"
                        alt="Profile"
                        class="h-24 w-24 rounded-full object-cover border-4 border-emerald-500 z-0"
                    />
                </div>
            </div>

            <!-- Form Fields -->
            <form class="space-y-4" @submit.prevent="saveChanges">
                <div>
                    <label
                        for="name"
                        class="block text-sm font-medium text-gray-600"
                    >
                        Full Name
                    </label>
                    <input
                        id="name"
                        type="text"
                        v-model="formField.name"
                        class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-emerald-500"
                    />
                </div>

                <div>
                    <label
                        for="address"
                        class="block text-sm font-medium text-gray-600"
                    >
                        Address
                    </label>
                    <div class="relative">
                        <input
                            id="address"
                            type="text"
                            v-model="formField.address"
                            placeholder="Search address"
                            class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-emerald-500"
                            @focus="handleFocus"
                            @blur="handleBlur"
                        />

                        <!-- <ul
                            v-if="isFocused && suggestions.length > 0"
                            class="absolute z-50 w-full bg-white border border-gray-300 rounded-md mt-1 max-h-48 overflow-y-auto shadow-lg"
                        >
                            <li
                                v-for="suggestion in suggestions"
                                :key="suggestion.place_id"
                                @click="selectSuggestion(suggestion.formatted)"
                                class="px-4 py-2 hover:bg-emerald-100 cursor-pointer text-sm"
                            >
                                {{ suggestion.formatted }}
                            </li>
                        </ul> -->
                    </div>
                </div>

                <div>
                    <label
                        for="email"
                        class="block text-sm font-medium text-gray-600"
                    >
                        Email
                    </label>
                    <input
                        id="email"
                        type="email"
                        v-model="formField.email"
                        class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-emerald-500"
                    />
                </div>

                <div class="pt-4 flex justify-end">
                    <button
                        type="submit"
                        class="px-6 py-2 bg-emerald-600 text-white rounded-md hover:bg-emerald-700"
                    >
                        Save Changes
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
// import sampleData from "@/stores/sampleData";
import { X } from "lucide-vue-next";
import { ref, watch } from "vue";
// import type { GeoapifySuggestion } from "@/stores/sampleData";
defineProps({
    popup: Boolean,
});
const emit = defineEmits(["close"]);
// const name = ref(userInfo.name);
// const address = ref(userInfo.address);
// const email = ref(userInfo.email);
const previewImage = ref("");
// const suggestions = ref<GeoapifySuggestion[]>([]);

import { useLoinRegStore } from "@/stores/login/LoginRegStore";
const store = useLoinRegStore();
const formField = ref({
    name: userInfo.name,
    address: userInfo.address,
    email: userInfo.email,
});
const isFocused = ref(false);

const userInfo = store.getLoginData;
const handleFocus = () => {
    isFocused.value = true;
};

const handleBlur = () => {
    // Delay hiding suggestions to allow click on list item
    setTimeout(() => {
        isFocused.value = false;
    }, 150);
};

watch(
    () => formField.value.address,
    async (newVal) => {
        if (newVal.length > 2) {
            suggestions.value = await sampleData.fetchPlaceSuggestions(newVal);
        } else {
            suggestions.value = [];
        }
    }
);

const activateEditButton = () => {
    emit("close");
};
const selectSuggestion = (value: string) => {
    formField.value.address = value;
    suggestions.value = [];
};
const onFileChange = (event: Event) => {
    const target = event.target as HTMLInputElement;
    const file = target.files?.[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = () => {
            previewImage.value = reader.result as string;
        };
        reader.readAsDataURL(file);
    }
};

const saveChanges = () => {
    userInfo.name = formField.value.name;
    userInfo.address = formField.value.address;
    userInfo.email = formField.value.email;
    console.log("Changes saved:", userInfo);
    emit("close");
};
</script>
