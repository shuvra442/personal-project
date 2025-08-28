<!-- <template>
    <v-container fluid>
        <span>Shop owner dashboard</span>
    </v-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";

export default defineComponent({
    setup() {
        const state = reactive({});

        return {
            ...toRefs(state),
        };
    },
});
</script> -->
<template>
    <div id="app" class="min-h-screen w-full flex">
        <!-- Main Content -->
        <main class="flex-1 overflow-y-auto px-6 py-6">
            <!-- Header -->
            <header class="flex justify-between items-center mb-8">
                <div>
                    <h1 class="text-2xl md:text-3xl font-bold text-gray-800">
                        Shopkeeper Dashboard
                    </h1>
                    <p class="text-gray-600 mt-1">
                        Manage your garbage collection requests and orders
                    </p>
                </div>
            </header>

            <!-- Summary Cards -->
            <div
                class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5 mb-8"
            >
                <!-- Pending Requests Card -->
                <div
                    class="bg-white rounded-[10px] px-5 py-5 shadow-sm card-hover"
                >
                    <div class="flex items-center justify-between">
                        <div>
                            <h2 class="text-3xl font-extrabold text-gray-800">
                                {{ summaryData.pendingRequests }}
                            </h2>
                            <p class="text-sm text-gray-500">
                                Pending Requests
                            </p>
                        </div>
                        <div
                            class="bg-orange-100 w-12 h-12 rounded-lg flex items-center justify-center"
                        >
                            <i class="fas fa-clock text-orange-600 text-xl"></i>
                        </div>
                    </div>
                </div>

                <!-- Today's Garbage Card -->
                <div
                    class="bg-white rounded-[10px] px-5 py-5 shadow-sm card-hover"
                >
                    <div class="flex items-center justify-between">
                        <div>
                            <h2 class="text-3xl font-extrabold text-gray-800">
                                {{ summaryData.todaysGarbage
                                }}<span class="text-lg">kg</span>
                            </h2>
                            <p class="text-sm text-gray-500">Today's Garbage</p>
                        </div>
                        <div
                            class="bg-green-100 w-12 h-12 rounded-lg flex items-center justify-center"
                        >
                            <i
                                class="fas fa-weight-hanging text-green-600 text-xl"
                            ></i>
                        </div>
                    </div>
                    <div class="mt-4">
                        <div
                            class="flex justify-between text-sm text-gray-500 mb-1"
                        >
                            <span>Target: 100kg</span>
                            <span
                                >{{
                                    Math.round(
                                        (summaryData.todaysGarbage / 100) * 100
                                    )
                                }}%</span
                            >
                        </div>
                        <div class="progress-bar">
                            <div
                                class="progress-fill bg-green-500"
                                :style="{
                                    width:
                                        (summaryData.todaysGarbage / 100) *
                                            100 +
                                        '%',
                                }"
                            ></div>
                        </div>
                    </div>
                </div>

                <!-- Completed Orders Card -->
                <div
                    class="bg-white rounded-[10px] px-5 py-5 shadow-sm card-hover"
                >
                    <div class="flex items-center justify-between">
                        <div>
                            <h2 class="text-3xl font-extrabold text-gray-800">
                                {{ summaryData.completedOrders }}
                            </h2>
                            <p class="text-sm text-gray-500">
                                Completed Orders
                            </p>
                        </div>
                        <div
                            class="bg-blue-100 w-12 h-12 rounded-lg flex items-center justify-center"
                        >
                            <i
                                class="fas fa-check-circle text-blue-600 text-xl"
                            ></i>
                        </div>
                    </div>
                    <div class="mt-4">
                        <div
                            class="flex justify-between text-sm text-gray-500 mb-1"
                        >
                            <span>This week</span>
                            <span>{{ summaryData.weeklyCompleted }}</span>
                        </div>
                        <div class="progress-bar">
                            <div
                                class="progress-fill bg-blue-500"
                                :style="{
                                    width:
                                        (summaryData.weeklyCompleted / 20) *
                                            100 +
                                        '%',
                                }"
                            ></div>
                        </div>
                    </div>
                </div>

                <!-- Total Earnings Card -->
                <div
                    class="bg-white rounded-[10px] px-5 py-5 shadow-sm card-hover"
                >
                    <div class="flex items-center justify-between">
                        <div>
                            <h2 class="text-3xl font-extrabold text-gray-800">
                                {{ formatCurrency(summaryData.totalEarnings) }}
                            </h2>
                            <p class="text-sm text-gray-500">Total Earnings</p>
                        </div>
                        <div
                            class="bg-purple-100 w-12 h-12 rounded-lg flex items-center justify-center"
                        >
                            <i
                                class="fa-solid fa-indian-rupee-sign text-xl text-purple-700"
                            ></i>
                        </div>
                    </div>
                    <div class="mt-4">
                        <div
                            class="flex justify-between text-sm text-gray-500 mb-1"
                        >
                            <span>This month</span>
                            <span>₹1,200</span>
                        </div>
                        <div class="progress-bar">
                            <div
                                class="progress-fill bg-purple-500"
                                :style="{ width: '70%' }"
                            ></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Tabs Navigation -->
            <div class="bg-white rounded-[10px] shadow-sm mb-6">
                <div class="flex border-b">
                    <button
                        :class="[
                            'tab-button py-4 px-6 text-gray-600',
                            activeTab === 'Pending' ? 'active' : '',
                        ]"
                        @click="activeTab = 'Pending'"
                    >
                        <i class="fas fa-inbox mr-2"></i> Collection Requests
                        <span
                            class="ml-2 bg-gray-200 text-gray-800 text-xs px-2 py-0.5 rounded-full"
                            >{{ orders.Pending.length }}</span
                        >
                    </button>
                    <button
                        :class="[
                            'tab-button py-4 px-6 text-gray-600',
                            activeTab === 'Processing' ? 'active' : '',
                        ]"
                        @click="activeTab = 'Processing'"
                    >
                        <i class="fas fa-clipboard-check mr-2"></i> Accepted
                        Orders
                        <span
                            class="ml-2 bg-gray-200 text-gray-800 text-xs px-2 py-0.5 rounded-full"
                            >{{ orders.Processing.length }}</span
                        >
                    </button>
                    <button
                        :class="[
                            'tab-button py-4 px-6 text-gray-600',
                            activeTab === 'Delivered' ? 'active' : '',
                        ]"
                        @click="activeTab = 'Delivered'"
                    >
                        <i class="fas fa-check-double mr-2"></i> Completed
                        Orders
                        <span
                            class="ml-2 bg-gray-200 text-gray-800 text-xs px-2 py-0.5 rounded-full"
                            >{{ orders.Delivered.length }}</span
                        >
                    </button>
                </div>
            </div>

            <!-- Orders/Requests Table -->
            <div class="bg-white rounded-[10px] shadow-sm overflow-hidden">
                <!-- Pending Requests -->
                <div v-if="activeTab === 'Pending'" class="px-6 py-6">
                    <h2 class="text-xl font-semibold text-gray-800 mb-5">
                        Pending Collection Requests
                    </h2>

                    <div class="overflow-x-auto">
                        <table class="min-w-full divide-y divide-gray-200">
                            <thead>
                                <tr>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Customer
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Garbage Type
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Weight (kg)
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Requested Time
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Actions
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                                <tr
                                    v-for="(order, index) in orders.Pending"
                                    :key="order.id"
                                >
                                    <td class="px-4 py-4 whitespace-nowrap">
                                        <div class="flex items-center">
                                            <div
                                                class="flex-shrink-0 h-10 w-10"
                                            >
                                                <div
                                                    class="h-10 w-10 rounded-full bg-blue-500 flex items-center justify-center text-white font-bold"
                                                >
                                                    {{
                                                        order.customer.name
                                                            .charAt(0)
                                                            .toUpperCase()
                                                    }}
                                                </div>
                                            </div>
                                            <div class="ml-4">
                                                <div
                                                    class="text-sm font-medium text-gray-900"
                                                >
                                                    {{ order.customer.name }}
                                                </div>
                                                <div
                                                    class="text-sm text-gray-500"
                                                >
                                                    {{ order.customer.address }}
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="px-4 py-4 whitespace-nowrap">
                                        <div class="text-sm text-gray-900">
                                            {{ order.garbageType }}
                                        </div>
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-500"
                                    >
                                        {{ order.weight }} kg
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-500"
                                    >
                                        {{
                                            formatDateTime(order.requestedTime)
                                        }}
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm font-medium flex"
                                    >
                                        <div
                                            @click="acceptOrder(order)"
                                            class="cursor-pointer text-green-900] hover:text-green-900 bg-green-100 px-3 py-1 rounded-md mr-2"
                                        >
                                            <span
                                                class="text-green-600 hover:text-green-900"
                                                >Accept</span
                                            >
                                        </div>
                                        <div
                                            @click="rejectOrder(order)"
                                            class="bg-red-100 px-3 py-1 rounded-md cursor-pointer"
                                        >
                                            <span
                                                class="text-red-600 hover:text-red-900"
                                                >Reject</span
                                            >
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Accepted Orders -->
                <div v-if="activeTab === 'Processing'" class="px-6 py-6">
                    <h2 class="text-xl font-semibold text-gray-800 mb-5">
                        Accepted Orders
                    </h2>

                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div
                            v-for="(order, index) in orders.Processing"
                            :key="order.id"
                            class="border rounded-lg px-4 py-4 order-card border-l-4 border-blue-500"
                        >
                            <div class="flex justify-between items-start">
                                <div>
                                    <h3 class="font-medium text-gray-900">
                                        {{ order.customer.name }}
                                    </h3>
                                    <p class="text-sm text-gray-500">
                                        {{ order.customer.address }}
                                    </p>
                                </div>
                                <span
                                    class="status-badge bg-blue-100 text-blue-700"
                                    >{{ order.status }}</span
                                >
                            </div>

                            <div class="mt-4 grid grid-cols-2 gap-2">
                                <div>
                                    <p class="text-xs text-gray-500">
                                        Garbage Type
                                    </p>
                                    <p class="text-sm font-medium">
                                        {{ order.garbageType }}
                                    </p>
                                </div>
                                <div>
                                    <p class="text-xs text-gray-500">Weight</p>
                                    <p class="text-sm font-medium">
                                        {{ order.weight }} kg
                                    </p>
                                </div>
                                <div>
                                    <p class="text-xs text-gray-500">
                                        Estimated Value
                                    </p>
                                    <p class="text-sm font-medium">
                                        ₹{{ order.estimatedValue }}
                                    </p>
                                </div>
                                <div>
                                    <p class="text-xs text-gray-500">
                                        Requested Time
                                    </p>
                                    <p class="text-sm font-medium">
                                        {{ formatTime(order.requestedTime) }}
                                    </p>
                                </div>
                            </div>

                            <div class="mt-4 flex justify-end">
                                <button
                                    @click="markAsReceived(order)"
                                    class="btn-primary text-white px-4 py-2 rounded-md flex items-center"
                                >
                                    <i class="fas fa-check-circle mr-2"></i>
                                    Mark as Received
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Completed Orders -->
                <div v-if="activeTab === 'Delivered'" class="px-6 py-6">
                    <h2 class="text-xl font-semibold text-gray-800 mb-5">
                        Completed Orders
                    </h2>

                    <div class="overflow-x-auto">
                        <table class="min-w-full divide-y divide-gray-200">
                            <thead>
                                <tr>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Customer
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Garbage Type
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Weight
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Final Value
                                    </th>
                                    <th
                                        class="px-4 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                                    >
                                        Completed Time
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="bg-white divide-y divide-gray-200">
                                <tr
                                    v-for="(order, index) in orders.Delivered"
                                    :key="order.id"
                                >
                                    <td class="px-4 py-4 whitespace-nowrap">
                                        <div class="flex items-center">
                                            <div
                                                class="flex-shrink-0 h-10 w-10"
                                            >
                                                <div
                                                    class="h-10 w-10 rounded-full bg-green-500 flex items-center justify-center text-white font-bold"
                                                >
                                                    {{
                                                        order.customer.name
                                                            .charAt(0)
                                                            .toUpperCase()
                                                    }}
                                                </div>
                                            </div>
                                            <div class="ml-4">
                                                <div
                                                    class="text-sm font-medium text-gray-900"
                                                >
                                                    {{ order.customer.name }}
                                                </div>
                                                <div
                                                    class="text-sm text-gray-500"
                                                >
                                                    {{ order.customer.address }}
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-900"
                                    >
                                        {{ order.garbageType }}
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-500"
                                    >
                                        {{ order.weight }} kg
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-500"
                                    >
                                        ₹{{ order.finalValue }}
                                    </td>
                                    <td
                                        class="px-4 py-4 whitespace-nowrap text-sm text-gray-500"
                                    >
                                        {{
                                            formatDateTime(order.completedTime)
                                        }}
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<!-- 
<script setup lang="ts">
import { ref, reactive } from "vue";

// Define reactive state
const activeTab = ref<"Pending" | "Processing" | "Delivered">("Pending");

const summaryData = reactive({
    pendingRequests: 5,
    todaysGarbage: 75,
    completedOrders: 42,
    weeklyCompleted: 12,
    totalEarnings: 12500,
});

const orders = reactive({
    Pending: [
        {
            id: 1,
            customer: {
                name: "Amit Sharma",
                address: "Sector 15, Gandhinagar",
            },
            garbageType: "Plastic",
            weight: 12,
            requestedTime: new Date(2023, 5, 15, 10, 30),
            status: "Pending",
        },
        //...other pending orders
    ],
    Processing: [
        {
            id: 4,
            customer: {
                name: "Neha Gupta",
                address: "Sector 12, Gandhinagar",
            },
            garbageType: "Electronics",
            weight: 5,
            estimatedValue: 350,
            requestedTime: new Date(2023, 5, 15, 14, 0),
            status: "Accepted",
        },
        //...other Processing orders
    ],
    Delivered: [
        {
            id: 6,
            customer: {
                name: "Sanjay Mehta",
                address: "Sector 9, Gandhinagar",
            },
            garbageType: "Paper",
            weight: 20,
            finalValue: 300,
            completedTime: new Date(2023, 5, 14, 16, 45),
            status: "Completed",
        },
        //...other completed orders
    ],
});

// Helper methods
function formatCurrency(amount: number): string {
    return "₹" + amount.toLocaleString("en-IN");
}

function formatDateTime(date: Date): string {
    return date.toLocaleString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
    });
}

function formatTime(date: Date): string {
    return date.toLocaleString("en-IN", {
        hour: "2-digit",
        minute: "2-digit",
    });
}

function acceptOrder(order: any) {
    const index = orders.Pending.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Pending.splice(index, 1);
        order.status = "Accepted";
        orders.Processing.push(order);
        summaryData.pendingRequests--;
        showNotification("Order Processing successfully!", "success");
    }
}

function rejectOrder(order: any) {
    const index = orders.Pending.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Pending.splice(index, 1);
        summaryData.pendingRequests--;
        showNotification("Order rejected.", "info");
    }
}

function markAsReceived(order: any) {
    const index = orders.Processing.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Processing.splice(index, 1);
        order.status = "Completed";
        order.completedTime = new Date();
        order.finalValue = Math.round(order.estimatedValue * 0.9);
        orders.Delivered.push(order);
        summaryData.completedOrders++;
        summaryData.todaysGarbage += order.weight;
        summaryData.totalEarnings += order.finalValue;
        showNotification("Order marked as received!", "success");
    }
}

function showNotification(message: string, type: string) {
    alert(`${type.toUpperCase()}: ${message}`);
}
</script> -->
<script setup lang="ts">
import { ref, reactive } from "vue";

interface Order {
    id: string;
    shopName: string;
    date: Date;
    weight: number;
    status: "Pending" | "Delivered" | "Processing";
}

// sample data
const sampleOrders: Order[] = [
    {
        id: "order001",
        shopName: "Grocery Mart",
        date: new Date("2025-06-01T10:00:00Z"),
        weight: 20,
        status: "Pending",
    },
    {
        id: "order002",
        shopName: "Fresh Farm",
        date: new Date("2025-06-15T14:30:00Z"),
        weight: 35,
        status: "Delivered",
    },
    {
        id: "order003",
        shopName: "Dairy Hub",
        date: new Date("2025-06-20T09:00:00Z"),
        weight: 18,
        status: "Processing", // <-- changed to match your union type
    },
];

// tabs
const activeTab = ref<"Pending" | "Processing" | "Delivered">("Pending");

// summary data
const summaryData = reactive({
    pendingRequests: 0,
    todaysGarbage: 0,
    completedOrders: 0,
    weeklyCompleted: 12,
    totalEarnings: 12500,
});

// grouped orders
const orders = reactive({
    Pending: [] as Order[],
    Processing: [] as Order[],
    Delivered: [] as Order[],
});

// initialize orders from sampleOrders
sampleOrders.forEach((order) => {
    if (order.status === "Pending") {
        orders.Pending.push(order);
        summaryData.pendingRequests++;
    } else if (order.status === "Processing") {
        orders.Processing.push(order);
    } else if (order.status === "Delivered") {
        orders.Delivered.push(order);
        summaryData.completedOrders++;
        summaryData.todaysGarbage += order.weight;
    }
});

// ======================
// Helper methods
// ======================
function formatCurrency(amount: number): string {
    return "₹" + amount.toLocaleString("en-IN");
}

function formatDateTime(date: Date): string {
    return date.toLocaleString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
    });
}

function formatTime(date: Date): string {
    return date.toLocaleString("en-IN", {
        hour: "2-digit",
        minute: "2-digit",
    });
}

function acceptOrder(order: Order) {
    const index = orders.Pending.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Pending.splice(index, 1);
        order.status = "Processing";
        orders.Processing.push(order);
        summaryData.pendingRequests--;
        showNotification("Order moved to Processing!", "success");
    }
}

function rejectOrder(order: Order) {
    const index = orders.Pending.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Pending.splice(index, 1);
        summaryData.pendingRequests--;
        showNotification("Order rejected.", "info");
    }
}

function markAsReceived(order: any) {
    const index = orders.Processing.findIndex((o) => o.id === order.id);
    if (index !== -1) {
        orders.Processing.splice(index, 1);
        order.status = "Delivered";
        order.completedTime = new Date();
        order.finalValue = Math.round(order.weight * 10); // or your own logic
        orders.Delivered.push(order);

        summaryData.completedOrders++;
        summaryData.todaysGarbage += order.weight;
        summaryData.totalEarnings += order.finalValue;
        showNotification("Order marked as received!", "success");
    }
}

function showNotification(message: string, type: string) {
    alert(`${type.toUpperCase()}: ${message}`);
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap");

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Inter", sans-serif;
}

:root {
    --primary: #10b981;
    --primary-dark: #059669;
    --secondary: #3b82f6;
    --accent: #f59e0b;
    --light-bg: #f9fafb;
    --dark-text: #1f2937;
}

body {
    background-color: var(--light-bg);
    color: var(--dark-text);
}

.card-hover {
    transition: all 0.3s ease;
}

.card-hover:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.status-badge {
    font-size: 0.75rem;
    padding: 0.35rem 0.75rem;
    border-radius: 50px;
}

.progress-bar {
    height: 8px;
    border-radius: 4px;
    overflow: hidden;
    background-color: #e5e7eb;
}

.progress-fill {
    height: 100%;
    border-radius: 4px;
    transition: width 0.5s ease;
}

.order-card {
    transition: all 0.3s ease;
    border-left: 4px solid;
}

.order-card:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}

.tab-button {
    transition: all 0.2s ease;
}

.tab-button.active {
    border-bottom: 3px solid var(--primary);
    color: var(--primary);
    font-weight: 600;
}

.btn-primary {
    background-color: var(--primary);
    transition: all 0.3s ease;
}

.btn-primary:hover {
    background-color: var(--primary-dark);
    transform: translateY(-2px);
}

.btn-secondary {
    background-color: var(--secondary);
    transition: all 0.3s ease;
}

.btn-secondary:hover {
    background-color: #2563eb;
    transform: translateY(-2px);
}

.notification-dot {
    position: absolute;
    top: -5px;
    right: -5px;
    width: 18px;
    height: 18px;
    border-radius: 50%;
    background-color: #ef4444;
    color: white;
    font-size: 0.7rem;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>
