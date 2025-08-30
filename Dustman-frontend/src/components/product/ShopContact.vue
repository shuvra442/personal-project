<template>
  <v-container fluid class="pa-6 bg-grey-lighten-4">

    <!-- HEADER SECTION (Cover + Shop Info) -->
    <v-sheet class="rounded-lg mb-6 overflow-hidden border-md elevation-3">
      <v-img :src="images.cover" height="240" cover>
        <div class="d-flex align-end h-100 pa-4 bg-gradient-to-t from-black/70">
          <v-avatar size="100" class="mr-4 elevation-4  border-3 border-white">
            <v-img :src="images.avatar" />
          </v-avatar>
          <div>
            <h2 class="text-h5 font-weight-bold text-white">{{ shop.name }}</h2>
            <p class="text-body-2 text-grey-lighten-3">
              <v-icon size="18" color="red" class="mr-1">mdi-map-marker</v-icon>
              {{ shop.address }}
            </p>
          </div>
        </div>
      </v-img>
    </v-sheet>

    <!-- SHOP STATS -->
    <v-row class="mb-6">
      <!--  Rating -->
      <v-col cols="12" md="3">
        <v-hover v-slot="{ isHovering, props }">
          <v-sheet
            v-bind="props"
            class="pa-5 rounded-xl text-center elevation-2 transition-all duration-300"
            :class="isHovering ? 'shadow-lg scale-105' : ''"
          >
            <v-icon color="amber" size="32" class="mb-2">mdi-star</v-icon>
            <h3 class="text-h6 font-weight-bold">{{ animatedRating.toFixed(1) }} ★</h3>
            <p class="text-caption text-grey">Rating</p>
          </v-sheet>
        </v-hover>
      </v-col>

      <!--  Orders -->
      <v-col cols="12" md="3">
        <v-hover v-slot="{ isHovering, props }">
          <v-sheet
            v-bind="props"
            class="pa-5 rounded-xl text-center elevation-2 transition-all duration-300"
            :class="isHovering ? 'shadow-lg scale-105' : ''"
          >
            <v-icon color="blue" size="32" class="mb-2">mdi-cart-check</v-icon>
            <h3 class="text-h6 font-weight-bold">{{ animatedOrders }}</h3>
            <p class="text-caption text-grey">Orders Completed</p>
          </v-sheet>
        </v-hover>
      </v-col>

      <!--  Availability -->
      <v-col cols="12" md="3">
        <v-hover v-slot="{ isHovering, props }">
          <v-sheet
            v-bind="props"
            class="pa-5 rounded-xl text-center elevation-2 transition-all duration-300"
            :class="isHovering ? 'shadow-lg scale-105' : ''"
          >
            <v-icon color="deep-purple" size="32" class="mb-2">mdi-clock-outline</v-icon>
            <h3 class="text-h6 font-weight-bold">24/7</h3>
            <p class="text-caption text-grey">Availability</p>
          </v-sheet>
        </v-hover>
      </v-col>

      <!--  Verified -->
      <v-col cols="12" md="3">
        <v-hover v-slot="{ isHovering, props }">
          <v-sheet
            v-bind="props"
            class="pa-5 rounded-xl text-center elevation-2 transition-all duration-300"
            :class="isHovering ? 'shadow-lg scale-105' : ''"
          >
            <v-icon color="green" size="32" class="mb-2">mdi-shield-check</v-icon>
            <h3 class="text-h6 font-weight-bold">Trusted</h3>
            <p class="text-caption text-grey">Verified Shop</p>
          </v-sheet>
        </v-hover>
      </v-col>
    </v-row>

    <!-- 🔹 CONTACT & LOCATION -->
    <v-row class=" " dense>
      <!--  Contact Card -->
      <v-col cols="12" md="6">
        <v-card class="pa-5 rounded-xl elevation-3 h-100">
          <div class="bg-green-darken-3 rounded-lg pa-3 mb-4 text-center">
            <h3 class="text-h6 font-weight-bold text-white">
              <v-icon class="mr-1">mdi-phone</v-icon> Contact With Shop
            </h3>
          </div>

          <!-- Contact Info -->
          <div class="mb-4">
            <p class="text-body-2 mb-2 d-flex align-center">
              <v-icon color="blue-darken-2" class="mr-2">mdi-email</v-icon>
              <strong>Email:</strong>&nbsp; {{ shop.email }}
            </p>
            <p class="text-body-2 mb-4 d-flex align-center">
              <v-icon color="deep-orange" class="mr-2">mdi-phone</v-icon>
              <strong>Phone:</strong>&nbsp; {{ shop.phone }}
            </p>

            <!-- Quick Action Buttons -->
            <v-row dense>
              <v-col cols="12" sm="6">
                <v-btn
                  block
                  class="text-white"
                  prepend-icon="mdi-phone"
                  :href="'tel:' + shop.phone.replace(/\\s+/g, '')"
                  style="background: linear-gradient(90deg,#00c853,#64dd17); font-weight:600;"
                >
                  Call
                </v-btn>
              </v-col>
              <v-col cols="12" sm="6">
                <v-btn
                  block
                  class="text-white"
                  prepend-icon="mdi-gmail"
                  :href="`https://mail.google.com/mail/?view=cm&fs=1&to=${shop.email}&su=Shop%20Inquiry&body=Hello%20${shop.name},`"
                  target="_blank"
                  style="background: linear-gradient(90deg,#d32f2f,#f44336); font-weight:600;"
                >
                  Gmail
                </v-btn>
              </v-col>
            </v-row>
          </div>

          <!-- Message Form -->
          <div class="mt-4">
            <h3 class="text-subtitle-1 font-weight-bold mb-3 text-center">
              <v-icon color="green-darken-2" class="mr-1">mdi-pencil</v-icon>
              Send Quick Message
            </h3>
            <v-row dense>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="form.name"
                  label="Your Name"
                  prepend-inner-icon="mdi-account"
                  variant="outlined"
                  density="comfortable"
                />
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="form.phone"
                  label="Phone Number"
                  prepend-inner-icon="mdi-phone"
                  variant="outlined"
                  density="comfortable"
                />
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="form.address"
                  label="Address"
                  prepend-inner-icon="mdi-map-marker"
                  variant="outlined"
                  density="comfortable"
                />
              </v-col>
              <v-col cols="12">
                <v-textarea
                  v-model="form.details"
                  label="Details / Problem"
                  prepend-inner-icon="mdi-message-text"
                  rows="1"
                  variant="outlined"
                />
              </v-col>
            </v-row>

            <!-- Submit Options -->
            <v-row dense>
              <v-col cols="12" sm="6">
                <v-btn
                  block
                  class="mt-3 text-white"
                  prepend-icon="mdi-send"
                  style="background: linear-gradient(90deg,#2e7d32,#66bb6a); font-weight:600;"
                  @click="sendViaGmail"
                >
                  Send via Gmail
                </v-btn>
              </v-col>
              <v-col cols="12" sm="6">
                <v-btn
                  block
                  class="mt-3 text-white"
                  prepend-icon="mdi-whatsapp"
                  style="background: linear-gradient(90deg,#25d366,#128c7e); font-weight:600;"
                  @click="sendViaWhatsApp"
                >
                  Send via WhatsApp
                </v-btn>
              </v-col>
            </v-row>
          </div>
        </v-card>
      </v-col>

      <!-- Location Card -->
      <v-col cols="12" md="6">
        <v-card class="pa-4 rounded-xl elevation-3 h-100">
          <div class="bg-green-darken-3 rounded-lg pa-3 mb-4 text-center">
            <h3 class="text-h6 font-weight-bold text-white">
              <v-icon class="mr-1">mdi-map-marker</v-icon> Shop Location
            </h3>
          </div>
          <v-img
            :src="images.location"
            cover
            class="rounded-lg mb-3 transition-transform duration-500 hover:scale-105"
          />
          <p class="text-body-2 text-center font-weight-medium mb-3 d-flex align-center justify-center">
            <v-icon color="red" class="mr-2">mdi-map-marker</v-icon>
            {{ shop.address }}
          </p>
          <div class="text-center">
            <v-btn
              color="green-darken-2"
              class="text-white"
              prepend-icon="mdi-google-maps"
              :href="mapLink"
              target="_blank"
            >
              View on Google Maps
            </v-btn>
          </div>
        </v-card>
      </v-col>
    </v-row>

    <!-- SIMILAR SHOPS -->
    <div class="bg-white pa-6 rounded-lg mt-5 border-md">
      <h3 class="text-h6 font-weight-bold mb-3">Similar Shops</h3>
      <v-row class="mt-2">
        <v-col
          v-for="(item, i) in products"
          :key="i"
          cols="12"
          sm="6"
          md="4"
          lg="3"
        >
          <v-hover v-slot="{ isHovering, props }">
            <v-card
              v-bind="props"
              class="rounded-xl elevation-2 transition-all duration-300 cursor-pointer"
              :class="isHovering ? 'shadow-lg -translate-y-1 scale-105' : ''"
            >
              <v-img :src="item.image" height="200" cover class="rounded-t-xl" />
              <v-card-text>
                <p class="text-body-1 font-weight-bold mb-1">{{ item.name }}</p>
                <p class="text-body-2 text-grey mb-1">₹{{ item.price }}</p>
                <v-rating
                  :model-value="item.rating"
                  density="compact"
                  size="14"
                  readonly
                  color="amber"
                />
              </v-card-text>
            </v-card>
          </v-hover>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";

export default defineComponent({
  name: "shopContact",
  setup() {
    // Shop Info
    const shop = {
      name: "Green Recycle Hub",
      email: "xenodochialbhabha6@justzeus.com",
      phone: "+91 8942870528",
      address: "Panskura Main Road, Panskura, West Bengal, 721139",
      rating: 4.7,
      orders: 1200,
    };

    // Contact Form
    const form = ref({ name: "", phone: "", address: "", details: "" });

    // Gmail Send
    const sendViaGmail = () => {
      const subject = encodeURIComponent("Shop Inquiry");
      const body = encodeURIComponent(
        `Hello ${shop.name},\n\nMy Name: ${form.value.name}\nPhone: ${form.value.phone}\nAddress: ${form.value.address}\n\nMessage: ${form.value.details}`
      );
      window.open(
        `https://mail.google.com/mail/?view=cm&fs=1&to=${shop.email}&su=${subject}&body=${body}`,
        "_blank"
      );
    };

    // WhatsApp Send
    const sendViaWhatsApp = () => {
      const message = encodeURIComponent(
        `Hello ${shop.name},\n\nMy Name: ${form.value.name}\nPhone: ${form.value.phone}\nAddress: ${form.value.address}\n\nMessage: ${form.value.details}`
      );
      const phone = shop.phone.replace(/\s+/g, "");
      window.open(`https://wa.me/${phone}?text=${message}`, "_blank");
    };

    // Google Maps Link
    const mapLink = ref(
      `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(shop.address)}`
    );

    // Images
    const images = {
      cover: "https://rukminim1.flixcart.com/flap/3376/560/image/d117a62eb5fbb8e1.jpg?q=50",
      avatar: "https://picsum.photos/200/200?random=11",
      location: "https://picsum.photos/600/400?random=1",
    };

    // Similar Shops
    const products = [
      { name: "Wooden Chair", price: 1200, rating: 4, image: "https://picsum.photos/300/200?random=20" },
      { name: "Recycled Table", price: 2500, rating: 5, image: "https://picsum.photos/300/200?random=21" },
      { name: "Eco Sofa", price: 5600, rating: 4.5, image: "https://picsum.photos/300/200?random=22" },
      { name: "Plastic Bin", price: 300, rating: 3.5, image: "https://picsum.photos/300/200?random=23" },
    ];

    // Animated Stats
    const animatedRating = ref(0);
    const animatedOrders = ref(0);

    onMounted(() => {
      const ratingInterval = setInterval(() => {
        if (animatedRating.value < shop.rating) {
          animatedRating.value += 0.1;
        } else {
          animatedRating.value = shop.rating;
          clearInterval(ratingInterval);
        }
      }, 60);

      const ordersInterval = setInterval(() => {
        if (animatedOrders.value < shop.orders) {
          animatedOrders.value += Math.ceil(shop.orders / 50);
        } else {
          animatedOrders.value = shop.orders;
          clearInterval(ordersInterval);
        }
      }, 40);
    });

    return {
      shop,
      form,
      sendViaGmail,
      sendViaWhatsApp,
      mapLink,
      images,
      products,
      animatedRating,
      animatedOrders,
    };
  },
});
</script>
