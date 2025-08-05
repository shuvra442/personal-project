<template>
    <div class="h-full w-full flex flex-col md:flex-row p-4">
        <!-- Left Section - Image -->
        <div class="md:w-1/2 flex items-center justify-center p-6">
            <img
                :src="data.shopImage"
                alt="shop image"
                class="rounded-xl shadow-lg w-64 h-64 object-cover"
            />
        </div>

        <!-- Right Section - Details -->
        <div
            class="md:w-1/2 w-full h-full flex items-center p-6 justify-center flex-col gap-5 rounded-xl shadow-lg"
        >
            <!-- Shop Name -->
            <h2 class="text-3xl font-bold text-gray-800">
                {{ data.shopName }}
            </h2>

            <!-- Online Status -->
            <div class="flex items-center gap-2">
                <span
                    v-if="data.onlineStatus"
                    class="bg-green-500 text-white px-3 py-1 rounded-full text-sm"
                >
                    Online
                </span>
                <span
                    v-else
                    class="bg-gray-400 text-white px-3 py-1 rounded-full text-sm"
                >
                    Offline
                </span>
            </div>

            <!-- Shop Info -->
            <div class="space-y-1 text-gray-600">
                <p>{{ data.shopAdd }}</p>
                <p class="text-blue-500 underline">{{ data.shopEmail }}</p>
            </div>

            <!-- Garbage Info -->
            <div class="text-lg text-gray-700 space-y-1">
                <p>
                    Garbage Available:
                    <span class="font-semibold text-black"
                        >{{ data.garbageAmt }} KG</span
                    >
                </p>
                <div class="flex items-center gap-1 font-medium">
                    <IndianRupee :size="18" class="text-green-700" />
                    <span class="text-black">{{ data.garbagePrice }}/KG</span>
                </div>
            </div>

            <!-- Quantity Control -->
            <div class="flex items-center gap-3 mt-2">
                <button
                    @click="decrement"
                    class="bg-red-500 hover:bg-red-600 text-white p-2 rounded-full"
                >
                    <Minus class="text-black" />
                </button>

                <input
                    class="w-16 text-center border border-gray-400 rounded py-1 px-2 text-sm"
                    v-model.number="quantity"
                    type="number"
                    min="1"
                    :max="parseInt(data.garbageAmt)"
                    @change="validateQuantity"
                />

                <button @click="increment">
                    <Plus class="text-black" />
                </button>
            </div>

            <!-- Price Display -->
            <div class="text-xl font-bold text-gray-900">
                Total: ₹ {{ price }}
            </div>

            <!-- Order Button -->
            <v-btn @click="makePayment" :disabled="loading">
                <template v-if="loading">
                    <Loader class="animate-spin text-black"
                /></template>
                <template v-else class="flex gap-2">
                    <ShoppingCart class="w-5 h-5" /> <span>Place Order</span>
                </template>
            </v-btn>

            <!-- <v-btn @click="makePayment" :disabled="loading" v-if="loading">
                <ShoppingCart class="w-5 h-5" /> Place Order
            </v-btn> -->
        </div>
    </div>
</template>

<script lang="ts">
import APIstore from "@/stores/login/APIstore";
import {
    IndianRupee,
    ShoppingCart,
    Plus,
    Minus,
    Loader,
} from "lucide-vue-next";
import { computed, defineComponent, reactive, ref, toRefs } from "vue";

interface Payment {
    amount: number;
    items: string;
}

export default defineComponent({
    name: "App",
    components: {
        IndianRupee,
        ShoppingCart,
        Plus,
        Minus,
        Loader,
    },
    setup() {
        const state = reactive({
            data: {
                shopImage:
                    "https://imgs.search.brave.com/sWtfHNeytLqNDHoYR_2uthRTZ-1Kr8skdmwf11JF5EY/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tYXJr/ZXRwbGFjZS5jYW52/YS5jb20vRUFHUmU4/d2wtMWsvMi8wLzE2/MDB3L2NhbnZhLWNv/bG9yZnVsLWFic3Ry/YWN0LWdyYWRpZW50/LW9ubGluZS1zaG9w/LWZyZWUtbG9nby1O/a2ltV1piWEZjTS5q/cGc",
                onlineStatus: true,
                shopName: "ABCXYZ",
                garbageAmt: "5",
                garbagePrice: "20",
                shopAdd: "Mechogram",
                shopEmail: "shop@gmail.com",
            },
            quantity: 1,
        });
        let loading = ref(false);
        let amount = ref();
        let order_id = ref();
        let name = ref();
        let email = ref();
        let contact = ref();

        // const quantity = ref(1);
        const price = computed(() => {
            const pricePerKg = parseFloat(state.data.garbagePrice) || 0;
            return state.quantity * pricePerKg;
        });

        const increment = () => {
            if (state.quantity < parseInt(state.data.garbageAmt)) {
                state.quantity++;
            }
            // price = parseInt(state.data.garbagePrice) * state.quantity;
        };

        const decrement = () => {
            if (state.quantity > 1) {
                state.quantity--;
            }
            // price.value = parseInt(state.data.garbagePrice) * state.quantity;
        };

        const validateQuantity = () => {
            if (state.quantity < 1) state.quantity = 1;
            if (state.quantity > parseInt(state.data.garbageAmt)) {
                state.quantity = parseInt(state.data.garbageAmt);
            }
        };

        const makePayment = async () => {
            loading.value = true;
            const paymentDetails: Payment = {
                amount: price.value,
                items: state.data.shopName,
            };
            let response;
            try {
                response = await APIstore.makePayment(paymentDetails);
                amount.value = response.amount;
                order_id.value = response.id;
            } catch (error) {
                console.error("Payment failed:", error);
            } finally {
                loading.value = false;
            }
            try {
                const response = await APIstore.userDetails();
                name.value = response.name;
                email.value = response.email;
                contact.value = response.phoneNo;
            } catch (error) {}

            const options = {
                key: import.meta.env.VITE_RAZORPAY_KEY,
                amount: amount.value * 100, // Razorpay needs amount in paise
                currency: "INR",
                name: "Dustman",
                description: `${state.quantity} KG`,
                order_id: order_id.value,
                handler: async function (response) {
                    // ✅ Send payment verification data to your backend here
                    console.log("Payment success", response);
                    await APIstore.verifyPayment({
                        payment_id: response.razorpay_payment_id,
                        order_id: response.razorpay_order_id,
                        signature: response.razorpay_signature,
                    });
                },
                prefill: {
                    name: name.value,
                    email: email.value,
                    contact: contact.value,
                },
                notes: {
                    app_name: "Dustman App",
                    app_id: "DUST-APP-01",
                    shop_name: state.data.shopName, // Optional extra note
                },
                theme: {
                    color: "#3399cc",
                },
            };

            const rzp = new Razorpay(options);
            rzp.open();
        };

        return {
            ...toRefs(state),
            increment,
            decrement,
            validateQuantity,
            makePayment,
            price,
            loading,
            // quantity,
        };
    },
});
</script>
