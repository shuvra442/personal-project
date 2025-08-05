<template>
  <v-container class="pa-10" fluid>
    <v-card class="mx-auto" max-width="600">
      <v-img :src="shop.image" height="300px" cover></v-img>

      <v-card-text>
        <h2 class="text-h5 font-weight-bold mb-2">{{ shop.name }}</h2>
        <p class="text-subtitle-1">{{ shop.address }}</p>
        <v-chip
          :color="shop.isActive ? 'green' : 'grey'"
          text-color="white"
          class="mt-3"
        >
          {{ shop.isActive ? 'Open Now' : 'Closed' }}
        </v-chip>
      </v-card-text>

      <v-card-actions>
        <v-btn color="primary" @click="$router.back()">Back</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const baseImages = [
  "https://img.freepik.com/free-vector/shop-with-sign-we-are-open_52683-38687.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/026/172/293/non_2x/cartoon-style-of-cafe-front-shop-view-isolated-on-white-background-with-green-tree-and-bushes-vector.jpg",
  "https://img.freepik.com/free-vector/shop-with-sign-open-design_23-2148544029.jpg?semt=ais_hybrid&w=740",
  "https://static.vecteezy.com/system/resources/previews/035/859/178/non_2x/empty-store-front-with-window-and-door-glass-showcase-small-european-style-shop-exterior-commercial-property-market-or-supermarket-city-park-street-lamp-and-trees-flat-illustration-vector.jpg"
]

const shops = Array.from({ length: 24 }, (_, i) => ({
  name: `Shop Name ${i + 1}`,
  address: `Shop Address ${i + 1}`,
  isActive: (i + 1) % 2 === 0,
  image: baseImages[(i + 1) % baseImages.length]
}))

const shop = ref({})

onMounted(() => {
  const id = parseInt(route.params.id)
  if (id >= 0 && id < shops.length) {
    shop.value = shops[id]
  } else {
    // Invalid shop ID, redirect back or to a 404 page
    router.push('/')
  }
})
</script>

