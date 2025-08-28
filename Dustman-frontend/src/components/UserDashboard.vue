<template>
    <div class="min-h-screen w-full pt-12 px-5 pb-10 bg-[#E8F5E9]">
        <!-- Welcome Header -->
        <div class="text-center md:text-left space-y-1 mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Welcome Back, User</h1>
            <p class="text-gray-600">
                Here's what's happening with your garbage collection orders
            </p>
        </div>

        <!-- Summary Cards -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-4 pb-5">
            <!-- Card 1 -->
            <div
                class="hover:scale-[101%] transition-transform duration-150 bg-white rounded-2xl flex items-center p-5 shadow"
            >
                <div
                    class="bg-[#E4F3E5] w-14 h-14 rounded-lg flex items-center justify-center"
                >
                    <Clock3 class="text-green-500 w-6 h-6" />
                </div>
                <div class="ml-4">
                    <h2 class="text-3xl font-extrabold text-gray-800">2</h2>
                    <p class="text-sm text-[#788585]">Pending Orders</p>
                </div>
            </div>

            <!-- Card 2 -->
            <div
                class="hover:scale-[101%] transition-transform duration-150 bg-white rounded-2xl flex items-center p-5 shadow"
            >
                <div
                    class="bg-[#FFF0D9] w-14 h-14 rounded-lg flex items-center justify-center"
                >
                    <CircleCheckBigIcon class="text-orange-500 w-6 h-6" />
                </div>
                <div class="ml-4">
                    <h2 class="text-3xl font-extrabold text-gray-800">2</h2>
                    <p class="text-sm text-[#788585]">Completed Orders</p>
                </div>
            </div>

            <!-- Card 3 -->
            <!-- <div
                class="hover:scale-105 transition-transform duration-150 bg-white rounded-2xl flex items-center p-5 shadow"
            >
                <div
                    class="bg-indigo-100 w-14 h-14 rounded-lg flex items-center justify-center"
                >
                    <IndianRupeeIcon class="text-indigo-600 w-6 h-6" />
                </div>
                <div class="ml-4">
                    <h2 class="text-3xl font-extrabold text-gray-800">
                        ₹2,000
                    </h2>
                    <p class="text-sm text-[#788585]">Total Earnings</p>
                </div> 
            </div> -->
        </div>

        <!-- Scheduled Order Status -->
        <div class="bg-white py-6 rounded-xl shadow-md px-4 mb-5">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">
                Scheduled Order Status
            </h2>
            <div
                v-for="(item, index) in sampleData.sampleOrder"
                :key="index"
                class="flex py-1 px-2 flex-wrap items-center justify-between gap-4 p-4 mb-3 rounded-lg border border-gray-200 hover:shadow transition"
            >
                <div>
                    <p class="font-medium text-gray-900">{{ item.shopName }}</p>
                    <p class="text-sm text-gray-500">
                        {{ formatDate(item.date) }}
                    </p>
                </div>
                <div class="text-sm text-gray-700">
                    Weight: <strong>{{ item.weight }}kg</strong>
                </div>
                <span
                    :class="[
                        'px-3 py-1 rounded-full text-xs font-semibold',
                        item.status === 'Pending'
                            ? 'bg-yellow-100 text-yellow-700'
                            : item.status === 'Delivered'
                              ? 'bg-green-100 text-green-700'
                              : 'bg-blue-100 text-blue-700',
                    ]"
                >
                    {{ item.status }}
                </span>
            </div>
        </div>

        <!-- Payment Status -->
        <div class="bg-white py-6 px-4 rounded-xl shadow-md">
            <div class="flex justify-between">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">
                    Payment Status
                </h2>
                <span v-if="sampleData.samplePayments.length > 6" class="pr-4"
                    >view all</span
                >
            </div>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-7">
                <div
                    v-for="(payment, index) in sampleData.samplePayments.slice(
                        0,
                        6
                    )"
                    :key="index"
                    class="py-4 px-4 bg-gray-50 rounded-lg border border-gray-200 shadow-sm space-y-2"
                >
                    <div class="flex justify-between text-sm text-gray-600">
                        <span>Transaction ID:</span>
                        <span class="font-medium text-gray-800">{{
                            payment.transactionID
                        }}</span>
                    </div>
                    <div class="flex justify-between text-sm text-gray-600">
                        <span>Date:</span>
                        <span>{{ formatDate(payment.date) }}</span>
                    </div>
                    <div class="flex justify-between text-sm text-gray-600">
                        <span>Amount:</span>
                        <span class="text-green-600 font-bold"
                            >₹{{ payment.amount }}</span
                        >
                    </div>
                    <div class="flex justify-between text-sm text-gray-600">
                        <span>Method:</span>
                        <span class="capitalize">{{
                            payment.paymentMethod
                        }}</span>
                    </div>
                    <div class="flex justify-between text-sm">
                        <span>Status:</span>
                        <span
                            :class="{
                                'text-green-600 font-semibold':
                                    payment.status === 'Completed',
                                'text-yellow-600 font-semibold':
                                    payment.status === 'Pending',
                                'text-red-600 font-semibold':
                                    payment.status === 'Failed',
                                'text-blue-600 font-semibold':
                                    payment.status === 'Refunded',
                            }"
                        >
                            {{ payment.status }}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import sampleData from "@/utils/sampleData";
import { CircleCheckBigIcon, Clock3, IndianRupeeIcon } from "lucide-vue-next";

function formatDate(date: Date): string {
    return new Date(date).toLocaleDateString("en-IN", {
        year: "numeric",
        month: "short",
        day: "numeric",
    });
}

export default defineComponent({
    setup() {
        return {
            sampleData,
            CircleCheckBigIcon,
            Clock3,
            IndianRupeeIcon,
            formatDate,
        };
    },
});
</script>
