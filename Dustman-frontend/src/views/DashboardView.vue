<template>
    <div class="h-screen w-screen">
        <div v-if="role === 'admin'" class="h-full">admin</div>
        <div v-if="role === 'user'" class="h-full"><UserDashboard /></div>
        <div v-if="role === 'shopkeeper'" class="h-full">shopkeeper</div>
    </div>
</template>
<script lang="ts" setup>
import UserDashboard from "@/components/dashboard/UserDashboard.vue";
import { useSampleStore } from "@/stores/sample";
import { ArrowBigDown } from "lucide-vue-next";
const sampleStore = useSampleStore();
const role = sampleStore.getUserInfo.role;
</script>
