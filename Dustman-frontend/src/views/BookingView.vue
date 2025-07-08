<script setup lang="ts">
import Booking from "@/components/booking/Booking.vue";
</script>

<template>
    <div class="about pt-12">
        <Booking />
    </div>
</template>

<style>
@media (min-width: 1024px) {
    .about {
        min-height: 100vh;
        min-width: 100vw;
        display: flex;
        justify-content: center;
        /* background: #ecb8b8; */
    }
}
</style>
