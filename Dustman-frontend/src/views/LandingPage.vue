"<template>
  <v-app>
    <!-- Header -->
    <v-app-bar app color="white" elevation="2">
      <v-container class="d-flex align-center">
        <router-link to="/" class="text-decoration-none">
          <v-btn text class="text-h5 font-weight-bold">
            <v-icon left color="success">mdi-recycle</v-icon>
            Dustman
          </v-btn>
        </router-link>
        <v-spacer></v-spacer>
        <div class="d-none d-md-flex">
          <v-btn text href="#how-it-works">How It Works</v-btn>
          <v-btn text href="#for-users">For Users</v-btn>
          <v-btn text href="#for-shops">For Shops</v-btn>
          <v-btn text href="#testimonials">Testimonials</v-btn>
          <v-btn text href="#contact">Contact</v-btn>
          <v-btn outlined color="success" class="ml-4" to="/login">Log In</v-btn>
          <v-btn color="success" class="ml-2" to="/register">Sign Up</v-btn>
        </div>
        <v-app-bar-nav-icon @click="drawer = !drawer" class="d-md-none"></v-app-bar-nav-icon>
      </v-container>
    </v-app-bar>

    <!-- Mobile Navigation -->
    <v-navigation-drawer v-model="drawer" temporary right class="d-md-none">
      <v-list>
        <v-list-item href="#how-it-works" @click="drawer = false">
          <v-list-item-title>How It Works</v-list-item-title>
        </v-list-item>
        <v-list-item href="#for-users" @click="drawer = false">
          <v-list-item-title>For Users</v-list-item-title>
        </v-list-item>
        <v-list-item href="#for-shops" @click="drawer = false">
          <v-list-item-title>For Shops</v-list-item-title>
        </v-list-item>
        <v-list-item href="#testimonials" @click="drawer = false">
          <v-list-item-title>Testimonials</v-list-item-title>
        </v-list-item>
        <v-list-item href="#contact" @click="drawer = false">
          <v-list-item-title>Contact</v-list-item-title>
        </v-list-item>
        <v-divider class="my-2"></v-divider>
        <v-list-item>
          <v-btn outlined block color="success" class="my-1" to="/login">Log In</v-btn>
        </v-list-item>
        <v-list-item>
          <v-btn block color="success" class="my-1" to="/register">Sign Up</v-btn>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- Hero Section -->
    <v-main>
      <v-container class="py-16">
        <v-row class=" flex align-center justify-between" >
          <v-col cols="12" md="6" class="pr-md-8">
            <h1 class="text-h2 font-weight-bold mb-4">
              Turn Your <span class="text-success">Waste</span> into <span class="text-success">Wealth</span>
            </h1>
            <p class="text-h6 font-weight-regular mb-6">
              Dustman connects eco-conscious individuals with local businesses that purchase recyclable materials. 
              Earn money while helping the planet by selling your recyclables to nearby shops.
            </p>
            <div>
              <v-btn color="success" size="x-large" class="mr-4 mb-4">Sell Your Waste</v-btn>
              <v-btn 
                outlined 
                color="success" 
                size="x-large" 
                class="mb-4"
                @click="goToHome"
              >
                List Your Shop
              </v-btn>
            </div>
          </v-col>
          <v-col cols="12" md="6">
            <v-img
              src="https://images.unsplash.com/photo-1603575448878-868a20723f5d?q=80&w=1000"
              alt="Recycling Marketplace"
              max-height="400"
              class="rounded-lg elevation-5"
            ></v-img>
          </v-col>
        </v-row>
      </v-container>

      <!-- Stats Section -->
      <v-container class="py-12">
        <v-row>
          <v-col v-for="stat in stats" :key="stat.title" cols="6" md="3">
            <v-card class="text-center py-6 rounded-lg" elevation="2">
              <v-icon size="60" color="success">{{ stat.icon }}</v-icon>
              <div class="text-h3 font-weight-bold mt-2">{{ stat.value }}</div>
              <div class="text-h6">{{ stat.title }}</div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <!-- How It Works -->
      <v-container id="how-it-works" class="py-16">
        <v-row class="mb-12" justify="center">
          <v-col cols="12" md="8" class="text-center">
            <h2 class="text-h3 font-weight-bold mb-4">How Dustman Works</h2>
            <p class="text-h6 text-medium-emphasis">
              Our simple process makes recycling profitable and convenient for everyone
            </p>
          </v-col>
        </v-row>
        <v-row>
          <v-col v-for="step in steps" :key="step.number" cols="12" md="3">
            <v-card class="text-center py-8 px-4 rounded-lg" elevation="2">
              <v-avatar color="success" size="60" class="mb-4">
                <span class="text-h4 text-white">{{ step.number }}</span>
              </v-avatar>
              <h3 class="text-h5 font-weight-bold mb-4">{{ step.title }}</h3>
              <p>{{ step.description }}</p>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <!-- User Types -->
      <v-container id="for-users" class="py-16" style="background-color: #f8f9fa;">
        <v-row class="mb-12" justify="center">
          <v-col cols="12" md="8" class="text-center">
            <h2 class="text-h3 font-weight-bold mb-4">Designed For Everyone</h2>
            <p class="text-h6 text-medium-emphasis">
              Whether you're looking to sell recyclables or purchase materials for your business
            </p>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6">
            <v-card class="overflow-hidden">
              <div class="py-8 text-center white--text" style="background: linear-gradient(135deg, #2ecc71 0%, #3498db 100%);">
                <v-icon size="60" class="mb-4">mdi-account</v-icon>
                <h3 class="text-h4 font-weight-bold">For Waste Sellers</h3>
                <p>Individuals & Households</p>
              </div>
              <v-card-text class="pa-6">
                <v-list>
                  <v-list-item v-for="(benefit, i) in userBenefits" :key="i">
                    <template v-slot:prepend>
                      <v-icon color="success">mdi-check-circle</v-icon>
                    </template>
                    <v-list-item-title>{{ benefit }}</v-list-item-title>
                  </v-list-item>
                </v-list>
                <v-btn color="success" block size="x-large">Start Selling</v-btn>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col cols="12" md="6">
            <v-card class="overflow-hidden">
              <div class="py-8 text-center white--text" style="background: linear-gradient(135deg, #3498db 0%, #2ecc71 100%);">
                <v-icon size="60" class="mb-4">mdi-store</v-icon>
                <h3 class="text-h4 font-weight-bold">For Shop Owners</h3>
                <p>Businesses & Recycling Centers</p>
              </div>
              <v-card-text class="pa-6">
                <v-list>
                  <v-list-item v-for="(benefit, i) in shopBenefits" :key="i">
                    <template v-slot:prepend>
                      <v-icon color="success">mdi-check-circle</v-icon>
                    </template>
                    <v-list-item-title>{{ benefit }}</v-list-item-title>
                  </v-list-item>
                </v-list>
                <v-btn 
                  color="primary" 
                  block 
                  size="x-large"
                  @click="goToHome"
                >
                  List Your Shop
                </v-btn>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <!-- Testimonials -->
      <v-container id="testimonials" class="py-16">
        <v-row class="mb-12" justify="center">
          <v-col cols="12" md="8" class="text-center">
            <h2 class="text-h3 font-weight-bold mb-4">What Our Users Say</h2>
            <p class="text-h6 text-medium-emphasis">Real stories from people using Dustman</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col v-for="testimonial in testimonials" :key="testimonial.name" cols="12" md="4">
            <v-card class="pa-6 rounded-lg" elevation="2">
              <p class="text-body-1 font-italic mb-6">{{ testimonial.text }}</p>
              <div class="d-flex align-center">
                <v-avatar color="success" size="50" class="mr-4">
                  <span class="text-white">{{ testimonial.initials }}</span>
                </v-avatar>
                <div>
                  <h4 class="text-h6 font-weight-bold">{{ testimonial.name }}</h4>
                  <p class="text-medium-emphasis">{{ testimonial.role }}</p>
                </div>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>

      <!-- CTA Section -->
     <v-container id="contact" fluid class="py-16" style="background: linear-gradient(135deg, #2ecc71 0%, #3498db 100%);" >
        <v-row justify="center" class="text-center white--text">
          <v-col cols="12" md="8">
            <h2 class="text-h3 font-weight-bold mb-4">Join the Recycling Revolution</h2>
            <p class="text-h6 mb-8">
              Whether you have recyclables to sell or you're a business looking to source materials, 
              Dustman makes the process simple, profitable, and eco-friendly.
            </p>
            <v-btn 
              color="white" 
              size="x-large" 
              class="text-success"
              @click="goToHome"
            >
              Get Started Today
            </v-btn>
          </v-col>
        </v-row>
     </v-container>


      <!-- Footer -->
      <v-footer class="bg-grey-darken-4 white--text">
        <v-container>
          <v-row>
            <v-col cols="12" md="4">
              <div class="d-flex align-center mb-4">
                <v-icon color="success" size="40" class="mr-2">mdi-recycle</v-icon>
                <span class="text-h5 font-weight-bold">Dustman</span>
              </div>
              <p class="mb-6">
                Making recycling profitable and convenient for everyone while helping businesses source quality materials sustainably.
              </p>
              <div class="d-flex">
                <v-btn icon variant="text" color="white" class="mr-2">
                  <v-icon>mdi-facebook</v-icon>
                </v-btn>
                <v-btn icon variant="text" color="white" class="mr-2">
                  <v-icon>mdi-twitter</v-icon>
                </v-btn>
                <v-btn icon variant="text" color="white" class="mr-2">
                  <v-icon>mdi-instagram</v-icon>
                </v-btn>
                <v-btn icon variant="text" color="white">
                  <v-icon>mdi-linkedin</v-icon>
                </v-btn>
              </div>
            </v-col>
            
            <v-col cols="6" md="2">
              <h3 class="text-h6 font-weight-bold mb-4">Quick Links</h3>
              <v-list class="bg-transparent" density="compact">
                <v-list-item to="/">Home</v-list-item>
                <v-list-item href="#how-it-works">How It Works</v-list-item>
                <v-list-item href="#for-users">For Users</v-list-item>
                <v-list-item href="#for-shops">For Shops</v-list-item>
                <v-list-item href="#testimonials">Testimonials</v-list-item>
              </v-list>
            </v-col>
            
            <v-col cols="6" md="2">
              <h3 class="text-h6 font-weight-bold mb-4">Resources</h3>
              <v-list class="bg-transparent" density="compact">
                <v-list-item href="#">Blog</v-list-item>
                <v-list-item href="#">Recycling Guide</v-list-item>
                <v-list-item href="#">Pricing</v-list-item>
                <v-list-item href="#">FAQ</v-list-item>
                <v-list-item href="#">Support Center</v-list-item>
              </v-list>
            </v-col>
            
            <v-col cols="12" md="4">
              <h3 class="text-h6 font-weight-bold mb-4">Contact Us</h3>
              <v-list class="bg-transparent" density="compact">
                <v-list-item>
                  <v-icon left>mdi-email</v-icon>
                  <span>contact@Dustman.com</span>
                </v-list-item>
                <v-list-item>
                  <v-icon left>mdi-phone</v-icon>
                  <span>+1 (800) 123-4567</span>
                </v-list-item>
                <v-list-item>
                  <v-icon left>mdi-map-marker</v-icon>
                  <span>Pankura, Kolkata , 700001</span>
                </v-list-item>
              </v-list>
            </v-col>
          </v-row>
          
          <v-divider class="my-6 bg-grey"></v-divider>
          
          <div class="text-center text-grey">
            <p>&copy; 2023 Dustman. All rights reserved. Making the planet greener, one transaction at a time.</p>
          </div>
        </v-container>
      </v-footer>
    </v-main>
  </v-app>
</template>


<script lang="ts">
import { name } from "@vue/eslint-config-prettier/skip-formatting";
import { defineComponent, reactive, ref, toRefs } from "vue";
import { useRouter } from "vue-router";


export default defineComponent({
    name: "Landing",
    directives: {},
    components: {},
    
    setup() {
        const router = useRouter();

        const goToHome = () => {
            router.push("/home");
        };

        const drawer = ref(false);
        const state = reactive({
            stats: [
                {
                    icon: "mdi-account-group",
                    value: "15,000+",
                    title: "Active Users",
                },
                { icon: "mdi-store", value: "850+", title: "Partner Shops" },
                {
                    icon: "mdi-recycle",
                    value: "120+ Tons",
                    title: "Recycled Monthly",
                },
                { icon: "mdi-wallet", value: "$250K+", title: "Paid to Users" },
            ],
            states: [
                {
                    number: "1",
                    title: "List or Find",
                    description:
                        "Shops list materials they buy. Users find nearby shops accepting their recyclables.",
                },
                {
                    number: "2",
                    title: "Schedule Pickup",
                    description:
                        "Arrange a convenient time for drop-off or request pickup from shops that offer it.",
                },
                {
                    number: "3",
                    title: "Get Paid",
                    description:
                        "After verification, receive instant payment through your preferred method.",
                },
                {
                    number: "4",
                    title: "Track Impact",
                    description:
                        "Monitor your recycling contributions and earnings through your dashboard.",
                },
            ],
            userBenefits: [
                "Earn money from your recyclable waste",
                "Find the best prices from local shops",
                "Schedule convenient drop-off times",
                "Track your environmental impact",
                "Get paid instantly via multiple methods",
            ],
            shopBenefits: [
                "Source quality recyclable materials",
                "Connect with local suppliers",
                "Set your own prices and requirements",
                "Manage inventory efficiently",
                "Grow your business sustainably",
            ],
            steps: [
                {
                    number: "1",
                    title: "List or Find",
                    description:
                        "Shops list materials they buy. Users find nearby shops accepting their recyclables.",
                },
                {
                    number: "2",
                    title: "Schedule Pickup",
                    description:
                        "Arrange a convenient time for drop-off or request pickup from shops that offer it.",
                },
                {
                    number: "3",
                    title: "Get Paid",
                    description:
                        "After verification, receive instant payment through your preferred method.",
                },
                {
                    number: "4",
                    title: "Track Impact",
                    description:
                        "Monitor your recycling contributions and earnings through your dashboard.",
                },
            ],

            testimonials: [
                {
                    text: "Dustman has completely changed how I handle waste. I'm earning about $50 extra each month just from recycling materials I used to throw away!",
                    initials: "SR",
                    name: "Sarah Johnson",
                    role: "User for 8 months",
                },
                {
                    text: "As a small recycling center, finding consistent suppliers was challenging. Dustman connected us with dozens of reliable local sources within weeks.",
                    initials: "MK",
                    name: "Michael Kim",
                    role: "Shop Owner",
                },
                {
                    text: "The app makes everything so simple. I can find nearby shops, check their prices, and schedule drop-offs all in one place. Plus, getting paid instantly is fantastic.",
                    initials: "DP",
                    name: "David Park",
                    role: "User for 1 year",
                },
            ],
        });
        return {
            ...toRefs(state),
            goToHome,
            drawer,
            
        };
    },
});
</script>
