                                           <!-- 1st fromate use here -->
<!-- <template>
  <v-container class="pa-10" fluid>
    <v-row>
      <v-col
        v-for="(shop, index) in shops"
        :key="index"
        cols="12"
        md="3"
        class="pa-2"
      >
        <v-card class="bg-amber-100 rounded-lg overflow-hidden">
          <v-img
            :src="shop.image"
            height="200"
            cover
          ></v-img>

          <v-card-text>
            <div class="d-flex align-center justify-space-between mb-2">
              <span class="text-h6 font-weight-bold">{{ shop.name }}</span>
              <v-avatar size="14" :color="shop.isActive ? 'green' : 'grey'"></v-avatar>
            </div>

            <div class="text-caption text-grey-darken-1 mb-3">
              {{ shop.address }}
            </div>

           
            <v-hover v-slot="{ isHovering, props }">
              <v-btn
                v-bind="props"
                class="mx-auto d-block text-capitalize"
                :color="isHovering ? 'primary' : 'indigo-lighten-4'"
                :variant="isHovering ? 'elevated' : 'flat'"
                @click="showMore(shop)"
              >
                <span :class="isHovering ? 'text-white' : 'text-indigo-darken-2'">
                  Show More
                </span>
              </v-btn>
            </v-hover>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
const shops = [
  {
    name: "Shop Name 1",
    address: "Shop Address 1",
    isActive: true,
    image:
      "https://img.freepik.com/free-vector/shop-with-sign-we-are-open_52683-38687.jpg?semt=ais_hybrid&w=740",
  },
  {
    name: "Shop Name 2",
    address: "Shop Address 2",
    isActive: false,
    image:
      "https://static.vecteezy.com/system/resources/previews/026/172/293/non_2x/cartoon-style-of-cafe-front-shop-view-isolated-on-white-background-with-green-tree-and-bushes-vector.jpg",
  },
  {
    name: "Shop Name 3",
    address: "Shop Address 3",
    isActive: true,
    image:
      "https://img.freepik.com/free-vector/shop-with-sign-open-design_23-2148544029.jpg?semt=ais_hybrid&w=740",
  },
  {
    name: "Shop Name 4",
    address: "Shop Address 4",
    isActive: false,
    image:
      "https://static.vecteezy.com/system/resources/previews/035/859/178/non_2x/empty-store-front-with-window-and-door-glass-showcase-small-european-style-shop-exterior-commercial-property-market-or-supermarket-city-park-street-lamp-and-trees-flat-illustration-vector.jpg",
  },
];

function showMore(shop) {
  alert(`Showing more for: ${shop.name}`);
}
</script> -->


                                       <!-- 2nd Formate use here  -->

                                       
<!-- <template>
  <v-container class="pa-10" fluid>
    <v-row>
      <v-col
        v-for="(shop, index) in visibleShops"
        :key="index"
        cols="12"
        md="3"
        class="pa-2"
      >
        <v-card class="bg-amber-100 hover-card">
          <v-img :src="shop.image" height="200" cover></v-img>

          <v-card-text>
            <div class="d-flex align-center justify-space-between mb-2">
              <span class="text-h6 font-weight-bold">{{ shop.name }}</span>
              <v-avatar size="14" :color="shop.isActive ? 'green' : 'grey'"></v-avatar>
            </div>
            <div class="text-caption text-grey-darken-1">{{ shop.address }}</div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-row justify="center" v-if="visibleShops.length < shops.length">
      <v-btn color="deep-purple-accent-4" variant="elevated" @click="showMore">
        Show More
      </v-btn>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, computed } from 'vue'

const baseImages = [
  "https://img.freepik.com/free-vector/shop-with-sign-we-are-open_52683-38687.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/026/172/293/non_2x/cartoon-style-of-cafe-front-shop-view-isolated-on-white-background-with-green-tree-and-bushes-vector.jpg",
  "https://img.freepik.com/free-vector/shop-with-sign-open-design_23-2148544029.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/035/859/178/non_2x/empty-store-front-with-window-and-door-glass-showcase-small-european-style-shop-exterior-commercial-property-market-or-supermarket-city-park-street-lamp-and-trees-flat-illustration-vector.jpg"
]

const shops = ref([])

for (let i = 1; i <= 24; i++) {
  shops.value.push({
    name: `Shop Name ${i}`,
    address: `Shop Address ${i}`,
    isActive: i % 2 === 0,
    image: baseImages[i % baseImages.length]
  })
}

const visibleCount = ref(12)
const visibleShops = computed(() => shops.value.slice(0, visibleCount.value))

const showMore = () => {
  visibleCount.value += 4
}
</script>

<style scoped>
.hover-card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.hover-card:hover {
  transform: scale(1.03);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}
</style> -->



                                      <!-- 3rd Formate use here -->

<template>
  <v-container class="pa-10" fluid>
    <v-row>
      <v-col
        v-for="(shop, index) in visibleShops"
        :key="index"
        cols="12"
        md="3"
        class="pa-2"
      >
        <router-link :to="`/shop/${index}`" style="text-decoration: none;">
          <v-card class="bg-amber-100 hover-card" hover>
            <v-img :src="shop.image" height="200" cover></v-img>

            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-2">
                <span class="text-h6 font-weight-bold">{{ shop.name }}</span>
                <v-avatar size="14" :color="shop.isActive ? 'green' : 'grey'"></v-avatar>
              </div>
              <div class="text-caption text-grey-darken-1">{{ shop.address }}</div>
            </v-card-text>
          </v-card>
        </router-link>
      </v-col>
    </v-row>

    <v-row justify="center" v-if="visibleShops.length < shops.length">
      <v-btn color="deep-purple-accent-4" variant="elevated" @click="showMore">
        Show More
      </v-btn>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, computed } from 'vue'

const baseImages = [
  "https://img.freepik.com/free-vector/shop-with-sign-we-are-open_52683-38687.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/026/172/293/non_2x/cartoon-style-of-cafe-front-shop-view-isolated-on-white-background-with-green-tree-and-bushes-vector.jpg",
  "https://img.freepik.com/free-vector/shop-with-sign-open-design_23-2148544029.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/035/859/178/non_2x/empty-store-front-with-window-and-door-glass-showcase-small-european-style-shop-exterior-commercial-property-market-or-supermarket-city-park-street-lamp-and-trees-flat-illustration-vector.jpg"
]

const shops = ref([])

for (let i = 1; i <= 24; i++) {
  shops.value.push({
    name: `Shop Name ${i}`,
    address: `Shop Address ${i}`,
    isActive: i % 2 === 0,
    image: baseImages[i % baseImages.length]
  })
}

const visibleCount = ref(12)
const visibleShops = computed(() => shops.value.slice(0, visibleCount.value))

const showMore = () => {
  visibleCount.value += 4
}
</script>

<style scoped>
.hover-card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.hover-card:hover {
  transform: scale(1.03);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}
</style>
