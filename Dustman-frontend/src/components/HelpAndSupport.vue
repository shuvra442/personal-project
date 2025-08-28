<template>
  <v-app>
    <div class="min-h-screen flex flex-col bg-gray-50">

      <!--  Main Content -->
      <v-main class="flex-1">
        <!-- Header -->
        <header
          class="bg-gradient-to-r from-purple-500 to-purple-700 text-white text-center py-16 relative"
        >
          <h1 class="text-3xl font-bold">Welcome to Dustman Support</h1>
          <p class="text-lg mt-2">What can we help you with?</p>

          <!-- Search bar -->
          <div class="mt-6 flex justify-center">
            <div class="w-full max-w-xl">
              <v-text-field
                v-model="search"
                placeholder="Search support for keywords, error codes etc"
                hide-details
                variant="outlined"
                density="comfortable"
                prepend-inner-icon="mdi-magnify"
                class="bg-white rounded-lg shadow-sm"
              />
            </div>
          </div>

          <!-- Illustration -->
          <div class="absolute right-12 top-6 hidden md:block">
            <img
              src="C:\Users\dalai\OneDrive\Desktop\DustMan\personal-project\Dustman-frontend\src\assets\images-removebg-preview.png"
              alt="person"
              class="w-36"
            />
          </div>
        </header>

        <!--  Categories -->
        <section class="py-10">
          <div class="text-center mb-8">
            <h2 class="text-h6 font-weight-bold text-grey-darken-3">
              Category Wise Support
            </h2>
          </div>

          <v-container>
            <v-row justify="center" align="stretch">
              <v-col
                v-for="(category, i) in filteredCategories"
                :key="i"
                cols="12"
                sm="6"
                md="3"
              >
                <v-card
                  class="pa-6 d-flex flex-column align-center justify-center rounded-lg hover-scale"
                  elevation="6"
                >
                  <component
                    :is="category.icon"
                    class="w-12 h-12 text-blue-600"
                  />
                  <div class="mt-3 font-weight-medium text-subtitle-1">
                    {{ category.title }}
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </section>

        <!--  Contact Form -->
        <section id="contact" class="py-16 bg-white">
          <v-container>
            <h2 class="text-center text-2xl font-bold mb-2">Contact Us</h2>
            <p class="text-center text-gray-600 mb-8">
              Please take a moment to get in touch, we will get back to you shortly.
            </p>

            <v-row justify="center">
              <v-col cols="12" md="8">
                <!-- Rectangular form -->
                <v-card elevation="2" class="p-8 rectangular-form">
                  <v-form @submit.prevent="submitForm">
                    <v-row>
                      <!-- Left column -->
                      <v-col cols="12" md="6">
                        <v-text-field
                          v-model="form.name"
                          label="Your Name"
                          variant="outlined"
                          density="comfortable"
                          class="mb-4"
                          required
                        />
                        <v-text-field
                          v-model="form.email"
                          label="Email Address"
                          type="email"
                          variant="outlined"
                          density="comfortable"
                          class="mb-4"
                          required
                        />
                        <v-text-field
                          v-model="form.phone"
                          label="Phone Number"
                          variant="outlined"
                          density="comfortable"
                          class="mb-4"
                        />
                        <v-select
                          v-model="form.help"
                          :items="['Billing', 'Delivery', 'Technical Issue', 'Other']"
                          label="How can we help you?"
                          variant="outlined"
                          density="comfortable"
                          class="mb-4"
                        />
                      </v-col>

                      <!-- Right column -->
                      <v-col cols="12" md="6">
                        <v-textarea
                          v-model="form.message"
                          label="Message"
                          rows="8"
                          variant="outlined"
                          density="comfortable"
                          class="mb-4"
                          required
                        />
                        <v-checkbox
                          v-model="form.subscribe"
                          label="Join our mailing list?"
                          class="mb-4"
                        />
                      </v-col>
                    </v-row>

                    <!-- Submit button -->
                    <div class="flex justify-center mt-4">
                      <v-btn
                        type="submit"
                        class="send-btn"
                        color="pink-darken-1"
                        width="200"
                        height="45"
                      >
                        Send Message
                      </v-btn>
                    </div>
                  </v-form>
                </v-card>
              </v-col>
            </v-row>
          </v-container>

          <!-- Snackbar -->
          <v-snackbar
            v-model="snackbar"
            color="green-darken-2"
            timeout="3000"
            rounded="pill"
            location="bottom"
          >
             Message sent successfully!
          </v-snackbar>
        </section>
      </v-main>

      <!--  Footer -->
      <footer class="bg-gray-800 text-white py-4 text-center">
        © 2025 Dustman. All rights reserved.
        <div class="mt-2">
          📞 <a href="tel:+911234567890" class="text-blue-400 hover:underline">
            +91 12345 67890
          </a>
        </div>
      </footer>
    </div>
  </v-app>
</template>

<script setup>
import { ref, computed } from "vue";
import { Mail, Cog, Monitor, User } from "lucide-vue-next";

const search = ref("");
const drawer = ref(false);

// Categories
const categories = [
  { title: "Meters & Postage", icon: Mail },
  { title: "Equipment", icon: Cog },
  { title: "Software & Data", icon: Monitor },
  { title: "Account", icon: User },
  { title: "Billing", icon: User },
  { title: "Delivery", icon: Cog },
  { title: "Tracking", icon: Mail },
  { title: "Reports", icon: Monitor },
];

// Filtered categories
const filteredCategories = computed(() => {
  if (!search.value) return categories;
  return categories.filter((c) =>
    c.title.toLowerCase().includes(search.value.toLowerCase())
  );
});

// Form data
const form = ref({
  name: "",
  email: "",
  phone: "",
  help: "",
  message: "",
  subscribe: false,
});

const snackbar = ref(false);

const submitForm = () => {
  if (!form.value.name || !form.value.email || !form.value.message) {
    alert(" Please fill all required fields.");
    return;
  }
  if (!form.value.email.toLowerCase().endsWith("@gmail.com")) {
    alert(" Only Gmail addresses are allowed.");
    return;
  }
  snackbar.value = true;

  // Reset form
  form.value = { name: "", email: "", phone: "", help: "", message: "", subscribe: false };
};
</script>

<style scoped>
.v-field {
  border-radius: 0.75rem !important;
}

/* Hover effect for category cards */
.hover-scale {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}
.hover-scale:hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

/* Rectangular form */
.rectangular-form {
  background: #f9fafc; /* light gray background */
  border: 2px solid #d1d5db;
  border-radius: 0px !important; /* makes it rectangular */
}

/* Send button */
.send-btn {
  font-weight: 600;
  font-size: 14px;
  border-radius: 6px;
  transition: all 0.3s ease;
}
.send-btn:hover {
  background-color: #ec407a !important; /* darker pink */
  transform: scale(1.02);
}
</style>
