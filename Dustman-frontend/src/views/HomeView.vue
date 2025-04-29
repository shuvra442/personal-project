<template>
  <span>Hello</span>
</template>

<script lang="ts">
import { reactive, toRefs } from 'vue';


export default {
 
  setup() {
    const state = reactive({

    })

    return {
      ...toRefs(state)
      
    }

   
  }

}

</script>

<style scoped>
</style>