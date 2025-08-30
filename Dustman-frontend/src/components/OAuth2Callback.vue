<template>
  <div>Logging you in with Google...</div>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { useLoginRegStore } from "@/stores/login/LoginRegStore";

const route = useRoute();
const router = useRouter();
const store = useLoginRegStore();

const token = route.query.token as string;

if (token) {
  store.handleGoogleLoginCallback(token); // store token in Pinia
  router.push("/home"); // redirect to home
}
</script>
