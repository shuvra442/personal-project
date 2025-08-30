<template>
  <v-container fluid class="pa-4 bg-blue-grey-lighten-5" >
    <!-- Navbar -->
    <v-sheet class="bg-white rounded-lg pa-4 mb-4">
      <v-row class="flex-nowrap justify-center overflow-x-auto" align="center" no-gutters
        style="max-width: 100vw; overflow-y: hidden">
        <v-col v-for="(item, index) in navData" :key="index" cols="4" sm="2" md="1" class="text-center flex-shrink-0">
          <v-img :src="getImageUrl(item.id)" alt="nav icon" width="52" height="52" class="mx-auto mb-2" contain />
          <div class="text-caption font-weight-medium text-truncate">
            {{ item.text }}
          </div>
        </v-col>
      </v-row>
    </v-sheet>

    <!-- Banner Carousel -->
    <v-sheet class="bg-white rounded-lg pa-4 mb-5 h-auto">
      <v-row class="w-100 d-flex flex-column justify-center align-center" no-gutters>
        <v-carousel v-model="currentSlide" cycle show-arrows="hover" hide-delimiters :interval="carouselInterval"
          height="230" class="elevation-0 rounded-lg" transition="fade-transition" reverse-transition="fade-transition">
          <v-carousel-item v-for="banner in bannerData" :key="banner.id">
            <v-img :src="getImageUrl(banner.id)" cover class="w-100 h-100 rounded-lg" />
          </v-carousel-item>
        </v-carousel>

        <!-- Custom dot indicators -->
        <v-row class="d-flex justify-center align-center mt-3" no-gutters>
          <div v-for="(banner, index) in bannerData" :key="'dot-' + banner.id"
            class="mx-1 rounded-pill overflow-hidden cursor-pointer"
            :class="currentSlide === index ? 'w-12 bg-grey' : 'w-3 bg-grey'" @click="goToSlide(index)">
            <v-progress-linear v-if="currentSlide === index" :model-value="progress" color="blue" height="3" rounded />
            <div v-else class="h-1"></div>
          </div>
        </v-row>
      </v-row>
    </v-sheet>

    <!-- Shops Near You -->
    <v-sheet class="bg-white rounded-lg pa-4 mb-4">
      <v-row>
        <v-col cols="12" class="d-flex justify-space-between align-center mb-2">
          <h2 class="text-h6 font-weight-bold">Shops Near You</h2>
          <span class="text-blue-darken-2 text-caption font-weight-medium cursor-pointer">
            View All
          </span>
        </v-col>
        <v-col cols="12">
          <v-slide-group show-arrows class="w-100">
            <v-slide-item v-for="shop in shopData" :key="shop.id">
              <div class="mx-3 text-center rounded-lg cursor-pointer"
                style="width:180px; padding:12px; transition:all 0.3s ease;" @mouseover="hovered = shop.id"
                @mouseleave="hovered = null" :style="hovered === shop.id
                  ? 'transform: translateY(-4px); box-shadow: 0 4px 12px rgba(0,0,0,0.15); background-color: #fff;'
                  : ''">
                <v-img :src="getImageUrl(shop.id)" height="140" contain class="mb-2" />
                <div class="text-body-2 font-weight-bold text-truncate">{{ shop.name }}</div>
                <div class="text-caption text-grey">{{ shop.address }}</div>
              </div>
            </v-slide-item>
          </v-slide-group>
        </v-col>
      </v-row>
    </v-sheet>

    <!-- Midsection Banner -->
    <v-sheet class="bg-white rounded-lg pa-4 mb-4">
  <v-row>
    <v-col
      v-for="mid in midData"
      :key="mid.id"
      cols="12"
      md="4"
      class="pa-2"
    >
    <div class="rounded-lg cursor-pointer " 
        style="transition: all 0.3s ease;"
        @mouseover="hovered = mid.id"
        @mouseleave="hovered = null"
        :style="hovered === mid.id
          ? { transform: 'translateY(-4px)', boxShadow: '0 4px 12px rgba(0,0,0,0.15)' }
          : {}"
    >
      <v-img
        :src="getImageUrl(mid.id)"
        alt="mid section"
        height="220"
        cover
        class="rounded-lg"
      />
    </div>
    </v-col>
  </v-row>
    </v-sheet>


    <!-- Furniture Section -->
    <v-sheet class="bg-white rounded-lg pa-4 mb-4">
      <v-row>
        <v-col cols="12" class="d-flex justify-space-between align-center mb-2">
          <h2 class="text-h6 font-weight-bold">Furniture</h2>
          <v-btn variant="text" class="text-blue-darken-2 text-caption font-weight-medium">
            View All
          </v-btn>
        </v-col>
        <v-col cols="12">
          <v-slide-group show-arrows class="w-100">
            <v-slide-item v-for="item in furnitureData" :key="item.id">
              <v-card class="mx-2 pa-2 rounded-lg" width="220" elevation="1">
                <v-img :src="getImageUrl(item.id)" height="200" contain />
                <v-card-title class="text-caption font-weight-medium text-center mt-1">
                  {{ item.title.shortTitle }}
                </v-card-title>
                <v-card-subtitle class="text-body-2 text-green-darken-2 text-center">
                  {{ item.discount }}
                </v-card-subtitle>
                <v-card-text class="text-caption text-grey text-center">
                  {{ item.tagline }}
                </v-card-text>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-col>
      </v-row>
    </v-sheet>
  </v-container>
</template>

<script lang="ts">
import { defineComponent, ref, watch, onMounted, onBeforeUnmount } from "vue";

export default defineComponent({
  name: "HomePage",
  setup() {
    // 🔹 Full Image Map (id → url)
    const imageMap: Record<string, string> = {
      // Navbar
      nav1: "https://rukminim1.flixcart.com/flap/128/128/image/f15c02bfeb02d15d.png?q=100",
      nav2: "https://rukminim1.flixcart.com/flap/128/128/image/29327f40e9c4d26b.png?q=100",
      nav3: "https://rukminim1.flixcart.com/flap/128/128/image/22fddf3c7da4c4f4.png?q=100",
      nav4: "https://rukminim1.flixcart.com/flap/128/128/image/82b3ca5fb2301045.png?q=100",
      nav5: "https://rukminim1.flixcart.com/flap/128/128/image/69c6589653afdb9a.png?q=100",
      nav6: "https://rukminim1.flixcart.com/flap/128/128/image/ee162bad964c46ae.png?q=100",
      nav7: "https://rukminim1.flixcart.com/flap/128/128/image/0ff199d1bd27eb98.png?q=100",
      nav8: "https://rukminim1.flixcart.com/flap/128/128/image/71050627a56b4693.png?q=100",
      nav9: "https://rukminim1.flixcart.com/flap/128/128/image/dff3f7adcf3a90c6.png?q=100",

      // Banners
      banner1: "https://rukminim1.flixcart.com/flap/3376/560/image/d117a62eb5fbb8e1.jpg?q=50",
      banner2: "https://rukminim1.flixcart.com/flap/3376/560/image/57267a180af306fe.jpg?q=50",
      banner3: "https://rukminim1.flixcart.com/flap/3376/560/image/ae9966569097a8b7.jpg?q=50",
      banner4: "https://rukminim1.flixcart.com/flap/3376/560/image/f6202f13b6f89b03.jpg?q=50",

      // Shops
      shop1: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqhCUjIT3if_jcPy9oVceFISzRI7mC1USLZQ&s",
      shop2: "https://content.jdmagicbox.com/comp/dhanbad/g1/9999px326.x326.201110000930.d8g1/catalogue/oneplus-exclusive-store-bank-more-dhanbad-vcpf9ltmas.jpg",
      shop3: "https://content.jdmagicbox.com/comp/kolkata/u4/033pxx33.xx33.211218111925.r8u4/catalogue/gadget-hub-barrackpore-kolkata-mobile-phone-dealers-bivquel05p.jpg",
      shop4: "https://content.jdmagicbox.com/comp/kolkata/s2/033pxx33.xx33.220309090944.f2s2/catalogue/hp-world-haltu-kolkata-computer-dealers-7G5l2Eu5tP-250.jpg",
      shop5: "https://i.ytimg.com/vi/2CPTwNxnPgE/maxresdefault.jpg",
      shop6: "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/ec/1c/89/diamond-plaza.jpg?w=1200&h=1200&s=1",
      shop7: "https://content.jdmagicbox.com/comp/kolkata/r2/033pxx33.xx33.140805134501.u3r2/catalogue/arnav-telecom-pvt-ltd-garia-kolkata-mobile-phone-dealers-samsung-plvuig05or.jpg",
      shop8: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQ1l-67uidODAIEmGKQeGJKkoePm2V-1xnRQ&s",

      // Mid-section
      mid1: "https://i.guim.co.uk/img/media/bb5e732bdd0ae3bafca2ca7182232a562a824dfa/0_331_6541_3924/master/6541.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=2d7e945e63c81df2a9e3fdbf154c7958",
      mid2: "https://gestalten.com/cdn/shop/products/p.204_ThinkBig-ShopSmall_gestalten2023-NoUsageWithoutCredits_e493d57b-65a7-4eb2-be43-30a01c71da13_800x.jpg?v=1681475352",
      mid3: "https://indian-retailer.s3.ap-south-1.amazonaws.com/s3fs-public/2022-03/The%20New%20Shop.jpg",
      mid4: "https://rukminim1.flixcart.com/flap/464/708/image/633789f7def60050.jpg?q=70",
      mid5: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRM55xFeISJXwRzO1LDm23-4tsLNSoEnw6asA&s",
      mid6: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBtjgc6UBvMVSoax5oheXMCmqXQ7orHPo0bg&s",

      // Furniture
      furniture1: "https://rukminim1.flixcart.com/image/300/300/ke7ff680/hammock-swing/j/f/u/q3-jkaf-y3l0-furniture-kart-original-imafux96kpy7grch.jpeg?q=70",
      furniture2: "https://rukminim1.flixcart.com/image/300/300/bean-bag/h/v/b/f8-the-furniture-store-xxxl-original-imae65d3wg7qzpkn.jpeg?q=70",
      furniture3: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGGf0jU6A1J6Hpo255vo8k4EGHE-utMIvh-w&s",
      furniture4: "https://rukminim1.flixcart.com/image/300/300/ke7ff680/hammock-swing/j/f/u/q3-jkaf-y3l0-furniture-kart-original-imafux96kpy7grch.jpeg?q=70",
      furniture5: "https://rukminim1.flixcart.com/image/300/300/bean-bag/h/v/b/f8-the-furniture-store-xxxl-original-imae65d3wg7qzpkn.jpeg?q=70",
      furniture6: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGwZGA9p3zJ0DBkjeux4L9U5M8-X31HlZKnES4T102R4zoQNzUelKJLvLBz_JeUIxCO10&usqp=CAU",
    };

    const getImageUrl = (id: string) => imageMap[id] || "";

    // 🔹 Navbar data
    const navData = [
      { id: "nav1", text: "Top Offers" },
      { id: "nav2", text: "Grocery" },
      { id: "nav3", text: "Mobile" },
      { id: "nav4", text: "Fashion" },
      { id: "nav5", text: "Electronics" },
      { id: "nav6", text: "Home" },
      { id: "nav7", text: "Appliances" },
      { id: "nav8", text: "Travel" },
      { id: "nav9", text: "Beauty, Toys & More" },
    ];

    // 🔹 Banner data
    const bannerData = [
      { id: "banner1" },
      { id: "banner2" },
      { id: "banner3" },
      { id: "banner4" },
    ];

    // 🔹 Shop data
    const shopData = ref([
      { id: "shop1", name: "Digital Electronics", address: "Park Street, Kolkata" },
      { id: "shop2", name: "OnePlus Store", address: "Salt Lake, Kolkata" },
      { id: "shop3", name: "Gadget Hub", address: "Ballygunge, Kolkata" },
      { id: "shop4", name: "Techie World", address: "New Town, Kolkata" },
      { id: "shop5", name: "Mobile Mart", address: "Howrah, Kolkata" },
      { id: "shop6", name: "ElectroShop", address: "Dumdum, Kolkata" },
      { id: "shop7", name: "Smartphone Central", address: "Garia, Kolkata" },
      { id: "shop8", name: "Computer Bazaar", address: "Mecheda, Kolkata" },
    ]);

    // 🔹 Mid Images
    const midData = [
      { id: "mid1", title: "Summer Sale" },
      { id: "mid2", title: "Best Deals" },
      { id: "mid3", title: "Top Brands" },
      { id: "mid4", title: "New Arrivals" },
      { id: "mid5", title: "Trending Now" },
      { id: "mid6", title: "Clearance" }
    ];


    // 🔹 Furniture data
    const furnitureData = [
      { id: "furniture1", title: { shortTitle: "Hammock And Swings" }, discount: "From ₹199", tagline: "Trendy Collection" },
      { id: "furniture2", title: { shortTitle: "Bean Bag Covers" }, discount: "Min 80% Off", tagline: "XL, XXL & More" },
      { id: "furniture3", title: { shortTitle: "Gaming Chair" }, discount: "Min 70% Off", tagline: "XL, XXL & More" },
      { id: "furniture4", title: { shortTitle: "Hammock And Swings" }, discount: "From ₹199", tagline: "Trendy Collection" },
      { id: "furniture5", title: { shortTitle: "Bean Bag Covers" }, discount: "Min 80% Off", tagline: "XL, XXL & More" },
      { id: "furniture6", title: { shortTitle: "Bean Bags" }, discount: "Min 70% Off", tagline: "XL, XXL & More" },
    ];

    // 🔹 Banner logic
    const currentSlide = ref(0);
    const progress = ref(0);
    const carouselInterval = 3000;
    let timer: ReturnType<typeof setInterval> | undefined;

    const startProgress = () => {
      progress.value = 0;
      if (timer) clearInterval(timer);
      const step = 100 / (carouselInterval / 20);
      timer = setInterval(() => {
        if (progress.value + step >= 100) {
          progress.value = 100;
          clearInterval(timer);
        } else {
          progress.value += step;
        }
      }, 20);
    };

    const goToSlide = (index: number) => {
      currentSlide.value = index;
      startProgress();
    };

    onMounted(() => {
      startProgress();
    });

    watch(currentSlide, () => {
      if (timer) clearInterval(timer);
      startProgress();
    });

    onBeforeUnmount(() => {
      if (timer) clearInterval(timer);
    });

    const hovered = ref<string | null>(null);

    return {
      navData,
      bannerData,
      shopData,
      furnitureData,
      midData,
      hovered,
      getImageUrl,
      currentSlide,
      progress,
      carouselInterval,
      goToSlide,
    };
  },
});
</script>
