<template>
    <div class="h-full relative scroll-smooth">
        <PageLayout />
    </div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs, onMounted } from "vue";
import PageLayout from "@/views/PageLayout.vue";
import { useToast } from "vue-toast-notification";

export default defineComponent({
    name: "App",
    directives: {},
    components: {
        PageLayout,
    },
    setup() {
        const state = reactive({});

        onMounted(() => {});

        const toast = useToast();
        return {
            ...toRefs(state),
        };
    },
});
</script>
