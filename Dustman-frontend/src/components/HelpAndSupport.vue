<template>
    <v-app>
        <v-main>
            <v-container fluid class="pa-0">
                <v-row
                    align="center"
                    justify="center"
                    class="purple-gradient pa-10"
                >
                    <!-- Left Content -->
                    <v-col
                        cols="12"
                        md="6"
                        class="text-center text-md-left white--text"
                    >
                        <h2 class="font-weight-bold mb-2">
                            Pitney Bowes Support
                        </h2>
                        <p class="subtitle-1">What can we help you with?</p>

                        <!-- Search bar -->
                        <v-text-field
                            solo
                            rounded
                            flat
                            placeholder="Search support for keywords, error codes etc"
                            append-inner-icon="mdi-magnify"
                            class="mt-6"
                        ></v-text-field>
                    </v-col>

                    <!-- Right Illustration -->
                    <v-col cols="12" md="6" class="text-center">
                        <v-img
                            src="https://img.freepik.com/free-vector/workplace-concept-illustration_114360-8462.jpg"
                            max-width="350"
                            class="mx-auto"
                        ></v-img>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
export default {
    name: "SupportSection",
};
</script>

<style scoped>
.purple-gradient {
    background: linear-gradient(135deg, #7b42f6, #b01eff);
    min-height: 60vh;
}
</style>
DELETE FROM `dustman`.`user` WHERE (`user_id` = '4');
