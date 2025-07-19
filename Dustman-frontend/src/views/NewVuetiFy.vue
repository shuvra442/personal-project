<template>
    <v-container fluid>
        <v-row>
            <v-col cols="12">
                <span>Hello</span>
                <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem ipsam rerum modi quibusdam explicabo, beatae nam dolore commodi asperiores, nulla laboriosam odio placeat reprehenderit recusandae natus id pariatur quam? Quam.</span>
            </v-col>
        </v-row>
    </v-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from 'vue';


export default defineComponent({
    // eslint-disable-next-line vue/multi-word-component-names
    name:'hello',
    components: {
        
    },

    setup () {
        const state = reactive({

        })

        return{
            ...toRefs(state)
        }
    }
})
</script>