<template>
  <v-container fluid class="pa-6 bg-grey-lighten-4">
    <v-row>
      <!-- ==================== LEFT SECTION : Product Images ==================== -->
      <v-col cols="12" md="6" class="d-flex flex-column align-center">
        <!-- Main Product Image -->
        <v-img
          :src="selectedImage"
          height="400"
          width="100%"
          class="rounded-lg elevation-2 mb-4"
          cover
          @error="onImageError"
        />

        <!-- Thumbnail Images -->
        <v-row class="justify-center" dense>
          <v-col
            v-for="(img, index) in product.images"
            :key="index"
            cols="3"
            class="d-flex justify-center"
          >
            <v-img
              :src="img"
              height="80"
              width="80"
              class="rounded-lg border-sm cursor-pointer"
              cover
              @click="selectedImage = img"
              @error="onThumbError(index)"
              :class="{ 'elevation-6': selectedImage === img }"
            />
          </v-col>
        </v-row>
      </v-col>

      <!-- ==================== RIGHT SECTION : Shop Info ==================== -->
      <v-col cols="12" md="6">
        <v-sheet class="bg-white rounded-lg pa-4 elevation-2 mb-4 ">
          <!-- Shop Name + Online Status -->
          <div class="d-flex align-center mb-1">
            <h2 class="text-h5 font-weight-bold">{{ shop.name }}</h2>
            <v-chip
              :color="shop.isOnline ? 'green-darken-3' : 'grey'"
              text-color="white"
              size="small"
              class="ml-3"
            >
              {{ shop.isOnline ? 'Online' : 'Offline' }}
            </v-chip>
          </div>

          <!-- Shop Email -->
          <p class="text-body-2 text-grey mt-1.5">{{ shop.email }}</p>

          <!-- Shop Address -->
          <p class="text-body-2 mb-2 mt-2">
            <strong>Address:</strong> {{ shop.address }}
          </p>

          <!-- ==================== RATING SUMMARY (clickable) ==================== -->
          <v-row
            class="align-center mb-2 mt-3 cursor-pointer"
            @click="scrollToRatings"
          >
            <v-chip
              class="me-2 mt-1.5 ml-2 bg-green-darken-3 text-white rounded-md size-medium"
            >
              {{ shopRating }} ★
            </v-chip>
            <span class="text-grey-darken-2 font-weight-bold">
              {{ totalRatings.toLocaleString() }} Ratings & {{ shopReviews }} Reviews
            </span>
          </v-row>

          <!-- ==================== PRICE ==================== -->
          <h3 class="text-h6 text-black font-weight-bold mb-2">
            ₹{{ totalPrice }}
            <span class="text-decoration-line-through text-grey ml-2 text-body-2">₹6000</span>
            <span class="ml-2 text-green-800">20% off</span>
          </h3>
          <p class="text-body-2 mb-3">
            <strong>Base Price:</strong> ₹{{ product.price }} per 100kg
          </p>

          <!-- ==================== GARBAGE QUANTITY COUNTER ==================== -->
          <div class="d-flex align-center mb-4">
            <p class="text-body-2 font-weight-bold mr-4">Garbage Quantity (kg):</p>
            <v-btn icon variant="outlined" size="small" @click="decreaseQty">
              <v-icon>mdi-minus</v-icon>
            </v-btn>
            <v-text-field
              v-model="garbageQty"
              type="number"
              density="compact"
              hide-details
              class="mx-2"
              style="max-width:80px; text-align:center"
              @input="calculateTotal"
              min="10"
            />
            <v-btn icon variant="outlined" size="small" @click="increaseQty">
              <v-icon>mdi-plus</v-icon>
            </v-btn>
          </div>

          <!-- ==================== ACTION BUTTONS ==================== -->
          <v-row class="mt-4">
            <v-col cols="6">
              <v-btn
                block
                class="rounded-lg text-white elevation-2"
                style="background: linear-gradient(135deg, #42a5f5, #1e88e5)"
                prepend-icon="mdi-phone"
                to="/shopContact"
              >
                Contact Shop
              </v-btn>
            </v-col>
            <v-col cols="6">
              <v-btn
                block
                class="rounded-lg text-white elevation-2"
                style="background: linear-gradient(135deg, #66bb6a, #43a047)"
                prepend-icon="mdi-truck-fast"
                to="/payments"
              >
                Request Pickup
              </v-btn>
            </v-col>
          </v-row>

          <p class="text-caption text-grey mt-2">Registered on: 28/08/2025</p>
        </v-sheet>

        <!-- ==================== SHOP RATINGS & REVIEWS ==================== -->
        <v-card id="ratings-summary" class="pa-4 rounded-lg" max-width="600">
          <v-row>
            <!-- LEFT : Average Rating -->
            <v-col cols="4" class="d-flex flex-column align-center justify-center">
              <div class="text-h4 font-weight-bold">3.8 ★</div>
              <div class="text-caption text-grey">9,964 Ratings & 495 Reviews</div>
            </v-col>

            <!-- RIGHT : Rating Distribution -->
            <v-col cols="8">
              <div
                v-for="(item, i) in ratings"
                :key="i"
                class="d-flex align-center mb-1"
              >
                <span class="text-caption" style="width: 24px">{{ item.star }}★</span>
                <v-progress-linear
                  :model-value="item.percent"
                  height="6"
                  rounded
                  class="mx-2 flex-grow-1"
                  :color="item.color"
                />
                <span class="text-caption" style="width: 40px">{{ item.count }}</span>
              </div>
            </v-col>
          </v-row>
        </v-card>

        <!-- ==================== USER RATING (THIS ONE WILL HIGHLIGHT) ==================== -->
        <v-sheet
          id="rate-shop-section"
          class="bg-white rounded-lg pa-4 elevation-2 mt-4"
        >
          <h3 class="text-h6 font-weight-bold mb-2">
            Rate This Shop
          </h3>
          <v-rating
            v-model="userRating"
            color="amber"
            background-color="grey-lighten-2"
            half-increments
            size="28"
            @update:modelValue="onUserRated"
          />
          <p class="text-caption text-grey mt-1">
            You rated: {{ userRating }} ★
          </p>
        </v-sheet>
      </v-col>
    </v-row>

    <!-- ==================== RECENTLY VIEWED ==================== -->
    <v-divider class="my-8"></v-divider>
    <h4 class="text-h6 font-weight-bold mb-4">Recently Viewed</h4>
    <v-row>
      <v-col v-for="(item, i) in recentlyViewed" :key="i" cols="12" sm="4" md="3">
        <v-card
          class="rounded-lg elevation-1 transition-all duration-300 cursor-pointer hover:-translate-y-1 hover:shadow-lg"
        >
          <v-img :src="item.image" height="180" cover></v-img>
          <v-card-text>
            <p class="text-body-1 font-weight-medium mb-1">{{ item.name }}</p>
            <p class="text-body-2 text-grey">₹{{ item.price }}</p>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "ShopDetails",
  setup() {
    // ==================== PRODUCT DATA ====================
    const product = {
      name: "Recyclable Garbage",
      description: "Sell your garbage at the best market rate.",
      price: 2500, // per 100kg
      images: [
        "https://picsum.photos/600/400?random=1",
        "https://picsum.photos/600/400?random=2",
        "https://picsum.photos/600/400?random=3",
        "https://picsum.photos/600/400?random=4",
      ],
    };

    // ==================== SHOP INFO ====================
    const shop = ref({
      name: "Eco Recycle Hub",
      email: "shop@example.com",
      address: "Panskura Main Road, Panskura, West Bengal, 721139",
      isOnline: true,
    });

    // ==================== RECENTLY VIEWED ====================
    const recentlyViewed = [
      { name: "Eco Mart", price: 1500, image: "https://picsum.photos/300/200?random=5" },
      { name: "Recycle Point", price: 2200, image: "https://picsum.photos/300/200?random=6" },
      { name: "Green Bazaar", price: 1800, image: "https://picsum.photos/300/200?random=7" },
      { name: "Eco Shop", price: 2600, image: "https://picsum.photos/300/200?random=8" },
    ];

    // ==================== STATE ====================
    const selectedImage = ref(product.images[0]);
    const garbageQty = ref(100);
    const totalPrice = ref((product.price / 100) * garbageQty.value);
    const userRating = ref(0);

    // ==================== CALCULATIONS ====================
    const calculateTotal = () => {
      totalPrice.value = (product.price / 100) * garbageQty.value;
    };
    const increaseQty = () => {
      garbageQty.value += 10;
      calculateTotal();
    };
    const decreaseQty = () => {
      if (garbageQty.value > 10) {
        garbageQty.value -= 10;
        calculateTotal();
      }
    };

    // ==================== SHOP RATINGS ====================
    const shopRating = ref(3.8);
    const shopReviews = ref(495);
    const ratings = ref([
      { star: 5, count: 5211, percent: 80, color: "green" },
      { star: 4, count: 1667, percent: 32, color: "green" },
      { star: 3, count: 893, percent: 17, color: "green" },
      { star: 2, count: 645, percent: 12, color: "orange" },
      { star: 1, count: 1548, percent: 30, color: "red" },
    ]);
    const totalRatings = ratings.value.reduce((a, b) => a + b.count, 0);

    // ==================== IMAGE FALLBACK ====================
    const onImageError = () => {
      selectedImage.value =
        "https://via.placeholder.com/600x400?text=Image+Not+Found";
    };
    const onThumbError = (index: number) => () => {
      product.images[index] =
        "https://via.placeholder.com/100x100?text=No+Image";
    };

    // ==================== HIGHLIGHT HELPERS ====================
    const restartFlash = (el: HTMLElement | null) => {
      if (!el) return;
      el.classList.remove("highlight");   
      (el as HTMLElement).offsetWidth;
      el.classList.add("highlight");
    };

    const highlightRateSheet = () => {
      const el = document.getElementById("rate-shop-section");
      restartFlash(el);
    };


    const onUserRated = (val: string | number) => {
      userRating.value = Number(val); 
      highlightRateSheet();  
    };

    // ==================== SCROLL (summary row) ====================
    const scrollToRatings = () => {
      const el = document.getElementById("ratings-summary");
      if (el) {
        el.scrollIntoView({ behavior: "smooth" });
        restartFlash(el as HTMLElement);
      }
    };

    return {
      product,
      shop,
      recentlyViewed,
      selectedImage,
      garbageQty,
      totalPrice,
      userRating,
      shopRating,
      shopReviews,
      ratings,
      totalRatings,
      calculateTotal,
      increaseQty,
      decreaseQty,
      onImageError,
      onThumbError,
      onUserRated,
      scrollToRatings,
    };
  },
});
</script>

<style scoped>
/* ✨ Highlight effect for the target sheet */
.highlight {
  animation: flash 1.5s ease-in-out;
}
@keyframes flash {
  0%   { box-shadow: 0 0 10px 2px #4caf50; }
  50%  { box-shadow: 0 0 20px 6px #4caf50; }
  100% { box-shadow: none; }
}
</style>
