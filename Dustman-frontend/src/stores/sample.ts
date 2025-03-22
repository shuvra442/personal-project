import { AxiosResponse } from "axios";
import type { sampleType } from "./sampleType";
import { defineStore } from 'pinia'
import { getSampleData } from "@/service/sampleService";


export const useSampleStore = defineStore('sampleStore',{
  state: () => ({
    sampeData : [],
    sampleLoading: false as boolean,
    error: null as string | null
  }),

  getters: {
    getSampleData: (state) => state.sampeData
  },
  actions: {
    fetchSampleData() {
        this.sampleLoading = true
        return getSampleData()
        .then((response: AxiosResponse)=> {
            this.sampeData = response.data
            console.log("The data is :::", this.sampeData)
        })
        .catch((error)=> {
            console.log("The error is :", error)
        })

    }
  }
  
})
