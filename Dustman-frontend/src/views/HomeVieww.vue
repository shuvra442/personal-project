<template>
    <div class="bg-white border-2 border-slate-200 m-2 mt-4 shadow-lg rounded-lg max-w-full">
      
      <!-- Header -->
      <div class="flex flex-col sm:flex-row items-center justify-between mb-4 p-2 border-b-2 border-slate-200">
        <div class="flex items-center space-x-2 mb-2 sm:mb-0">
          <button class="bg-green-500 text-white px-3 py-1 rounded cursor-pointer text-sm">B</button>
          <button class="bg-gray-100 text-gray-700 px-3 py-1 rounded cursor-pointer text-sm">S</button>
          <h2 class="font-semibold text-base sm:text-lg ml-2 sm:ml-4 truncate">NIFTY 29MAY2025 23900 PE</h2>
        </div>
  
        <!-- Center: NRML and INTRADAY -->
        <div class="bg-gray-200 p-1 rounded-sm">
          <button 
            class="px-2 sm:px-3 bg-gray-200 cursor-pointer text-sm sm:text-base"
            :class="{'bg-white text-blue-600 rounded' : selectedMarket === 'NRML'}"
            @click="selectedMarket = 'NRML'"
          >NRML</button>
          <button 
            class="px-2 sm:px-3 bg-gray-200 cursor-pointer text-sm sm:text-base"
            :class="{'bg-white text-blue-600 rounded': selectedMarket === 'INTRADAY'}"
            @click="selectedMarket = 'INTRADAY'"
          >INTRADAY</button>
        </div>
        <div class="w-0 sm:w-10"></div>
      </div>
  
      <!-- Main Content -->
      <div class="flex flex-col lg:flex-row space-y-4 lg:space-y-0 lg:space-x-4 p-2">
        <!-- Left Panel -->
        <div class="flex-1">
          <div class="flex flex-col md:flex-row space-y-4 md:space-y-0 md:space-x-4">
            <!-- Stats -->
            <div class="flex justify-between space-x-2 sm:space-x-6 p-2 w-full md:w-auto">
              <div class="space-y-4 sm:space-y-7 text-sm sm:text-base">
                <div v-for="item in leftStats" :key="item.label" class="flex justify-between font-medium">
                  <span class="text-slate-500">{{ item.label }}</span>
                  <span>{{ item.value }}</span>
                </div>
              </div>
              <div class="space-y-4 sm:space-y-7 text-sm sm:text-base">
                <div v-for="item in rightStats" :key="item.label" class="flex justify-between font-medium">
                  <span class="text-slate-500">{{ item.label }}</span>
                  <span>{{ item.value }}</span>
                </div>
              </div>
            </div>
  
            <!-- Orderbook Table -->
            <div class="flex-1 bg-gray-50 rounded-lg overflow-x-auto">
              <table class="w-full text-center text-xs sm:text-sm">
                <thead class="bg-white text-blue-700">
                  <tr>
                    <th class="py-2 px-1 sm:px-2">Bid Orders</th>
                    <th class="px-1 sm:px-2">Bid Qty</th>
                    <th class="px-1 sm:px-2">Bid Price</th>
                    <th class="px-1 sm:px-2">Ask Price</th>
                    <th class="px-1 sm:px-2">Ask Qty</th>
                    <th class="px-1 sm:px-2">Ask Orders</th>
                  </tr>
                </thead>
                <tbody class="font-medium">
                  <tr class="bg-white even:bg-gray-100" v-for="(row, index) in orderbookRows" :key="index">
                    <td class="py-2 px-1 sm:px-2 text-green-700">{{ row.bidOrders }}</td>
                    <td class="px-1 sm:px-2 text-green-700">{{ row.bidQty }}</td>
                    <td class="px-1 sm:px-2 text-green-700">{{ row.bidPrice }}</td>
                    <td class="px-1 sm:px-2 text-red-600">{{ row.askPrice }}</td>
                    <td class="px-1 sm:px-2 text-red-600">{{ row.askQty }}</td>
                    <td class="px-1 sm:px-2 text-red-600">{{ row.askOrders }}</td>
                  </tr>
                  <tr class="bg-gray-100 font-semibold">
                    <td class="text-left pl-2 sm:pl-6 py-2">Total</td>
                    <td class="text-green-700 px-1 sm:px-2">21150</td>
                    <td></td>
                    <td></td>
                    <td class="text-red-700 px-1 sm:px-2">20700</td>
                    <td></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
  
        <!-- Right Panel -->
        <div class="w-full lg:w-1/3 xl:w-1/4 border-2 border-green-600 rounded-lg bg-green-50 p-2">
          <!-- Order Type Tabs -->
          <div class="mt-2 flex justify-center mb-4">
            <div class="flex justify-center space-x-1 sm:space-x-2 bg-gray-200 w-fit rounded">
              <button class="px-2 sm:px-3 py-1 bg-gray-200 rounded cursor-pointer text-sm sm:text-base"
                :class="{ 'bg-white text-blue-500 font-semibold': selectedType === 'LIMIT' }"
                @click="selectedType = 'LIMIT'">
                LIMIT
              </button>
              <button class="px-2 sm:px-3 py-1 bg-gray-200 rounded cursor-pointer text-sm sm:text-base"
                :class="{ 'bg-white text-blue-500 font-semibold': selectedType === 'MARKET' }"
                @click="selectedType = 'MARKET'">
                MARKET
              </button>
              <button class="px-2 sm:px-3 py-1 bg-gray-200 rounded cursor-pointer text-sm sm:text-base"
                :class="{ 'bg-white text-blue-500 font-semibold': selectedType === 'SL' }" 
                @click="selectedType = 'SL'">
                SL
              </button>
            </div>
          </div>
  
          <!-- Price Input -->
          <div v-if="selectedType === 'MARKET'">
            <div class="text-center mb-4">
              <label class="block text-sm text-black font-medium">Price</label>
              <div class="mt-1 text-lg sm:text-xl font-semibold">{{ price }}</div>
              <div class="mx-auto border-b-2 border-dotted w-32 sm:w-40"></div>
            </div>
            <!-- Quantity Selector -->
            <div class="text-center mb-4">
              <label class="block text-sm text-black font-medium">Quantity</label>
              <div class="mt-2 flex items-center justify-center space-x-4 sm:space-x-10 text-lg sm:text-xl font-semibold">
                <button class="cursor-pointer text-lg" @click="decrement">-</button>
                <span>{{ quantity }}</span>
                <button class="cursor-pointer text-lg" @click="increment">+</button>
              </div>
              <div class="mx-auto border-b-2 border-slate-400 w-32 sm:w-40"></div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Footer Actions -->
      <div class="flex items-center mt-4 border-t-2 border-slate-200 p-2">
        <img src="/src/assets/deleteI.png" alt="Cancel Icon" class="ml-2 w-5 h-5 cursor-pointer" />
        <div class="flex-1 flex justify-end space-x-2">
          <button class="items-center gap-2 px-3 sm:px-4 py-1 sm:py-2 bg-gray-200 text-gray-500 rounded text-sm sm:text-base" disabled>
            CANCEL
          </button>
          <button class="px-3 sm:px-5 py-1 sm:py-2 bg-green-500 text-white rounded cursor-pointer text-sm sm:text-base">
            PLACE ORDER
          </button>
        </div>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { ref, computed, reactive, toRefs } from 'vue';
  
  export default {
  
  setup() {
    const state = reactive({
      showLogin : false,
      closeLogin: false,
      selectedType: 'MARKET',
      selectedMarket: 'NRML'
    })
    const leftStats = computed(() => [
      { label: 'LTP', value: 237.80 },
      { label: 'Open', value: 220.00 },
      { label: 'High', value: 265.00 },
      { label: 'Low', value: 211.50 },
      { label: 'Prev. Close', value: 246.95 }
    ])
    
    const rightStats = computed(() => [
      { label: 'Volume', value: 125925 },
      { label: 'LTQ', value: 75 },
      { label: 'Avg. Price', value: 229.59 },
      { label: 'LTT', value: '10:03:01' },
      { label: 'OI', value: 565200 }
    ])
    
    
    const orderbookRows = ref([
      { bidOrders: 2, bidQty: 225, bidPrice: 237.05, askPrice: 238.00, askQty: 525, askOrders: 4 },
      { bidOrders: 6, bidQty: 750, bidPrice: 237.00, askPrice: 238.10, askQty: 75, askOrders: 1 },
      { bidOrders: 1, bidQty: 75, bidPrice: 236.95, askPrice: 238.15, askQty: 150, askOrders: 2 },
      { bidOrders: 2, bidQty: 300, bidPrice: 236.80, askPrice: 238.25, askQty: 75, askOrders: 1 },
      { bidOrders: 1, bidQty: 225, bidPrice: 236.75, askPrice: 238.30, askQty: 75, askOrders: 1 }
    ]);
    
    const quantity = ref(75);
    const price = ref(0);
    
    const increment = () => { quantity.value += 1; };
    const decrement = () => { if (quantity.value > 0) quantity.value -= 1; };
  
    return {
        ...toRefs(state),
        increment,
        decrement,
        price,
        quantity,
        orderbookRows,
        leftStats,
        rightStats
      }
  }
  }
  
  </script>
  