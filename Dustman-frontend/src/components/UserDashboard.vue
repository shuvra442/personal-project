<template>
    <v-container fluid>
        <span>User dashboard</span>
    </v-container>
</template>


<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue';


export default defineComponent({
    
    setup() {
        const state = reactive({
    
        })
    
        return {
            ...toRefs(state),
        }
    }
})

</script>