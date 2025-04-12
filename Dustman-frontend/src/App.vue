<template>
  <h3 class="text-red-700">Hello</h3>
 
  <h3>{{ sampleStore }}</h3>
  <!-- <HomeView/> -->
 </template>


<script lang="ts">
import { useSampleStore } from './stores/sample';
import HomeView from './views/HomeView.vue';

import { defineComponent, onMounted, reactive,toRefs } from 'vue';


export default defineComponent({
  name: 'App',
  // components: {
  //   // HomeView
  // },
  setup() {
    const sampleStore = useSampleStore()
    const state = reactive({})
    
    onMounted(()=>{
      sampleStore.fetchSampleData()
    })
    return {
      ...toRefs(state),
      sampleStore
    }
  }

  
})

</script>

<style scoped>

</style>
