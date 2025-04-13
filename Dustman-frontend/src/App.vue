<template>

  <template v-if="routeName != 'landingPage'">
    <!-- <nav class="sticky top-0 z-50 scroll-smooth">
      <Navbar />
    </nav> -->
    <h1>Hello</h1>
  </template>

  <div class="min-h-screen relative scroll-smooth">
    <router-view :key="route?.fullPath"></router-view>
  </div>
 </template>


<script lang="ts">
import { useSampleStore } from './stores/sample';
import { useRoute } from 'vue-router'
import { defineComponent, onMounted, reactive,toRefs, computed } from 'vue';


export default defineComponent({
  name: 'App',
  setup() {
    const route = useRoute()
    const sampleStore = useSampleStore()
    const state = reactive({
      routeName: computed(() => {
        return route?.name
      })
    })
    
    onMounted(()=>{
      sampleStore.fetchSampleData()
    })
    return {
      ...toRefs(state),
      sampleStore,
      route
    }
  }

  
})

</script>

<style scoped>


</style>
