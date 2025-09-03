<template>
  <v-container fluid class="pa-6 bg-grey-lighten-4">
    <!-- HEADER -->
    <div class="text-center mb-8">
      <h1 class="text-3xl font-weight-bold text-grey-darken-3 mb-2">
        Pickup Details
      </h1>
      <p class="text-grey-darken-1">
        Please provide your information for collect your waste item
      </p>
    </div>

    <v-row align="start">
      <!-- ================= LEFT SECTION ================= -->
      <v-col cols="12" md="8">
        <v-card class="pa-8 rounded-xl" max-height="90vh" style="overflow-y: auto;">
          <!-- 1. Select Pickup Address -->
          <div class="border-b border-grey-lighten-2 pb-6 mb-6">
            <h2 class="text-xl font-weight-semibold text-grey-darken-3 mb-4 d-flex align-center">
              <span
                class="bg-blue-lighten-5 text-blue-darken-2 rounded-circle d-inline-flex align-center justify-center mr-3"
                style="width: 32px; height: 32px; font-size: 14px; font-weight: bold;"
              >
                1
              </span>
              Select Pickup Address
            </h2>

            <!-- saved addresses -->
            <v-radio-group
              v-if="savedAddresses.length > 0"
              v-model="selectedAddress"
              column
              class="mt-4"
            >
              <v-radio
                v-for="(addr, index) in savedAddresses"
                :key="index"
                :value="index"
                class="mb-3"
              >
                <template #label>
                  <div class="ml-2 d-flex justify-space-between align-center">
                    <div>
                      <!-- Name + Phone -->
                      <div class="font-weight-bold">
                        {{ addr.name }} | {{ addr.phone }}
                      </div>
                      <!-- Address -->
                      <div class="text-caption text-grey-darken-1">
                        {{ addr.address }}
                        <span v-if="addr.locality">, {{ addr.locality }}</span>,
                        {{ addr.city }}, {{ addr.state }} - {{ addr.pincode }}
                      </div>
                    </div>

                    <!-- Actions -->
                    <div class="ml-4 d-flex align-center">
                      <v-btn
                        icon
                        size="small"
                        variant="text"
                        color="blue-darken-2"
                        @click.stop="editAddress(index)"
                      >
                        <v-icon>mdi-pencil</v-icon>
                      </v-btn>
                      <v-btn
                        icon
                        size="small"
                        variant="text"
                        color="red-darken-2"
                        @click.stop="deleteAddress(index)"
                      >
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </div>
                  </div>
                </template>
              </v-radio>
            </v-radio-group>

            <!-- no saved addresses -->
            <div v-else class="text-center py-10">
              <v-icon size="64" color="blue-darken-2">mdi-home-outline</v-icon>
              <p class="mt-4 text-grey-darken-2 text-subtitle-1">
                You don’t have any saved addresses yet.
              </p>
              <p class="text-caption text-grey-darken-1 mb-4">
                Add one so we can collect your waste items.
              </p>
              <v-btn
                color="blue-darken-2"
                class="mt-2 text-none font-weight-bold px-6 py-3"
                @click="showNewForm = true"
              >
                <v-icon class="mr-2" icon="mdi-plus" /> Add New Address
              </v-btn>
            </div>
          </div>

          <!-- 2. Schedule Pickup -->
          <div class="border-b border-grey-lighten-2 pb-6 mb-6">
            <h2 class="text-xl font-weight-semibold text-grey-darken-3 mb-4 d-flex align-center">
              <span
                class="bg-blue-lighten-5 text-blue-darken-2 rounded-circle d-inline-flex align-center justify-center mr-3"
                style="width: 32px; height: 32px; font-size: 14px; font-weight: bold;"
              >
                2
              </span>
              Schedule a Pickup
            </h2>

            <v-form>
              <v-select
                v-model="deliveryTime"
                :items="deliveryTimes"
                label="Preferred Pickup Time"
                variant="outlined"
                class="rounded-lg mt-2"
                hide-details
              />

              <v-textarea
                v-model="instructions"
                label="Special Instructions (Optional)"
                variant="outlined"
                class="rounded-lg mt-4"
                rows="1"
                hide-details
              />

              <v-checkbox
                v-model="notifications"
                label="Send me SMS  about my pickup"
                color="blue-darken-2"
                class="mt-4"
                hide-details
              />
            </v-form>
          </div>

          <!-- Add New Address toggle -->
          <v-btn
            v-if="savedAddresses.length > 0"
            block
            color="blue-darken-2"
            class="mt-4 py-3 text-none font-weight-bold"
            variant="tonal"
            @click="showNewForm = !showNewForm"
          >
            <v-icon icon="mdi-plus" class="mr-2" />
            {{ showNewForm ? "Cancel New Address" : "Add New Address" }}
          </v-btn>

          <!-- Add New Address Form -->
          <v-expand-transition>
            <div v-if="showNewForm" class="mt-8">
              <!-- Personal Info -->
              <div class="border-b border-grey-lighten-2 pb-6 mb-6">
                <h2 class="text-xl font-weight-semibold text-grey-darken-3 mb-4 d-flex align-center">
                  <span
                    class="bg-blue-lighten-5 text-blue-darken-2 rounded-circle d-inline-flex align-center justify-center mr-3"
                    style="width: 32px; height: 32px; font-size: 14px; font-weight: bold;"
                  >
                    3
                  </span>
                  Personal Information
                </h2>

                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="name"
                      label="Name *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="phone"
                      label="10-digit mobile number *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                </v-row>

                <v-row class="mt-2">
                  <v-col cols="12">
                    <v-text-field
                      v-model="email"
                      label="Email"
                      type="email"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                </v-row>
              </div>

              <!-- Pickup Address -->
              <div class="pb-6">
                <h2 class="text-xl font-weight-semibold text-grey-darken-3 mb-4 d-flex align-center">
                  <span
                    class="bg-blue-lighten-5 text-blue-darken-2 rounded-circle d-inline-flex align-center justify-center mr-3"
                    style="width: 32px; height: 32px; font-size: 14px; font-weight: bold;"
                  >
                    4
                  </span>
                  Pickup Address
                </h2>

                <!-- Use My Current Location Button -->
                <v-btn
                  color="blue-darken-2"
                  class="mb-4 text-none font-weight-bold"
                  @click="useCurrentLocation"
                  :loading="loadingLocation"
                >
                  <v-icon class="mr-2">mdi-crosshairs-gps</v-icon>
                  Use my current location
                </v-btn>

                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="pincode"
                      label="Pincode *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="locality"
                      label="Locality *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                </v-row>

                <v-text-field
                  v-model="address"
                  label="Address (Area and Street) *"
                  variant="outlined"
                  class="rounded-lg mt-2"
                  hide-details
                />

                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="city"
                      label="City/District/Town *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-select
                      v-model="selectedState"
                      :items="states"
                      label="State *"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                </v-row>

                <v-row class="mt-2">
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="landmark"
                      label="Landmark (Optional)"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-text-field
                      v-model="altPhone"
                      label="Alternate Phone (Optional)"
                      variant="outlined"
                      class="rounded-lg"
                      hide-details
                    />
                  </v-col>
                </v-row>

                <!-- Buttons -->
                <div class="d-flex justify-center mt-6">
                  <v-btn
                    color="blue-darken-2"
                    class="mr-3 py-3 text-none font-weight-bold px-6"
                    @click="saveAddress"
                  >
                    Confirm Pickup Details
                  </v-btn>

                  <v-btn
                    :color="isFormDirty ? 'red-darken-2' : 'grey'"
                    :variant="isFormDirty ? 'flat' : 'tonal'"
                    class="py-3 text-none font-weight-bold px-6"
                    :disabled="!isFormDirty"
                    @click="resetForm"
                  >
                    Reset
                  </v-btn>
                </div>
              </div>
            </div>
          </v-expand-transition>
        </v-card>
      </v-col>

      <!-- ================= RIGHT SECTION ================= -->
      <v-col cols="12" md="4">
        <v-card class="pa-4 rounded-lg mb-4">
          <h3 class="text-h6 font-weight-bold mb-4">💰 Price Details</h3>

          <div class="d-flex justify-space-between mb-2 mr-2">
            <span>Quantity :</span>
            <span>{{ quantity }} Kg</span>
          </div>

          <div class="d-flex justify-space-between mb-2 mr-2">
            <span>Subtotal :</span>
            <span>₹{{ subtotal }}</span>
          </div>

          <div class="d-flex justify-space-between mb-2 mr-2">
            <span>Service Fee :</span>
            <span>₹{{ serviceFee }}</span>
          </div>

          <v-divider class="my-2" />

          <div class="d-flex justify-space-between font-weight-bold mb-2">
            <span>Total Amount:</span>
            <span class="text-success">₹{{ totalAmount }}</span>
          </div>
        </v-card>

        <!-- Pay Button -->
        <div class="sticky bottom-0 bg-grey-lighten-4 pb-2">
          <v-btn
            block
            color="success"
            class="py-4 text-none font-weight-bold text-h6"
            :disabled="selectedAddress === null"
          >
            <v-icon left>mdi-lock</v-icon>
            PAY ₹{{ totalAmount }} SECURELY
          </v-btn>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs, computed } from "vue";

export default defineComponent({
  name: "Payment",
  setup() {
    const state = reactive({
      // UI
      showNewForm: false,
      savedAddresses: [] as Array<any>,
      selectedAddress: null as number | null,
      loadingLocation: false,

      // Form fields
      name: "",
      phone: "",
      email: "",
      pincode: "",
      locality: "",
      address: "",
      city: "",
      selectedState: "",
      landmark: "",
      altPhone: "",

      // Pickup prefs
      deliveryTime: "",
      instructions: "",
      notifications: false,

      // Dropdowns
      states: ["West Bengal", "Maharashtra", "Delhi", "Karnataka", "Other"],
      deliveryTimes: [
        "Any time",
        "Morning (6 AM - 9 AM)",
        "Day (9 AM - 12 PM)",
        "Afternoon (12 PM - 5 PM)",
        "Evening (5 PM - 8 PM)",
      ],

      // Price details
      quantity: 5, // in Kg
      subtotal: 75, // ₹
      serviceFee: 10, // ₹
    });

    const isFormDirty = computed(() => {
      return (
        state.name !== "" ||
        state.phone !== "" ||
        state.email !== "" ||
        state.pincode !== "" ||
        state.locality !== "" ||
        state.address !== "" ||
        state.city !== "" ||
        state.selectedState !== "" ||
        state.landmark !== "" ||
        state.altPhone !== ""
      );
    });

    const saveAddress = () => {
      if (
        !state.name ||
        !state.phone ||
        !state.pincode ||
        !state.address ||
        !state.city ||
        !state.selectedState
      ) {
        window.alert("Please fill in required fields.");
        return;
      }
      const newAddr = {
        name: state.name,
        phone: state.phone,
        email: state.email,
        address: state.address,
        locality: state.locality,
        pincode: state.pincode,
        city: state.city,
        state: state.selectedState,
        landmark: state.landmark,
        altPhone: state.altPhone,
      };
      state.savedAddresses.push(newAddr);
      state.selectedAddress = state.savedAddresses.length - 1;
      resetForm();
      state.showNewForm = false;
    };

    const resetForm = () => {
      state.name = "";
      state.phone = "";
      state.email = "";
      state.pincode = "";
      state.locality = "";
      state.address = "";
      state.city = "";
      state.selectedState = "";
      state.landmark = "";
      state.altPhone = "";
      state.deliveryTime = "";
      state.instructions = "";
      state.notifications = false;
    };

    // ================= USE CURRENT LOCATION =================
    const useCurrentLocation = () => {
      if (!navigator.geolocation) {
        alert("Geolocation is not supported by your browser.");
        return;
      }
      state.loadingLocation = true;

      navigator.geolocation.getCurrentPosition(
        async (pos) => {
          const { latitude, longitude } = pos.coords;

          try {
            const response = await fetch(
              `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`
            );
            const data = await response.json();

            if (data && data.address) {
              state.pincode = data.address.postcode || "";
              state.locality =
                data.address.suburb || data.address.neighbourhood || "";
              state.address = data.display_name || "";
              state.city =
                data.address.city ||
                data.address.town ||
                data.address.village ||
                "";
              state.selectedState = data.address.state || "";
            }
          } catch (err) {
            console.error("Error fetching location:", err);
            alert("Unable to fetch location details.");
          } finally {
            state.loadingLocation = false;
          }
        },
        (err) => {
          console.error("Geolocation error:", err);
          alert("Unable to fetch your current location.");
          state.loadingLocation = false;
        }
      );
    };

    const editAddress = (index: number) => {
      const addr = state.savedAddresses[index];
      state.name = addr.name;
      state.phone = addr.phone;
      state.email = addr.email || "";
      state.address = addr.address;
      state.locality = addr.locality || "";
      state.pincode = addr.pincode;
      state.city = addr.city;
      state.selectedState = addr.state;
      state.landmark = addr.landmark || "";
      state.altPhone = addr.altPhone || "";

      state.showNewForm = true;
      state.savedAddresses.splice(index, 1);
      state.selectedAddress = null;
    };

    const deleteAddress = (index: number) => {
      if (confirm("Are you sure you want to delete this address?")) {
        state.savedAddresses.splice(index, 1);
        if (state.selectedAddress === index) {
          state.selectedAddress = null;
        }
      }
    };

    const totalAmount = computed(() => state.subtotal + state.serviceFee);

    return {
      ...toRefs(state),
      isFormDirty,
      saveAddress,
      resetForm,
      useCurrentLocation,
      totalAmount,
      editAddress,
      deleteAddress,
    };
  },
});
</script>
