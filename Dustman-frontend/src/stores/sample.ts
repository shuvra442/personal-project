import { AxiosResponse } from "axios";
import type { sampleType } from "./sampleType";
import { defineStore } from 'pinia'
import { getSampleData } from "@/service/sampleService";


export const useSampleStore = defineStore('sampleStore',{
  state: () => ({
    sampeData : [],
    sampleLoading: false as boolean,
    error: null as string | null,
    userInfo:{
      login:true as boolean,
      userName: "" as string,
      role:"user" as string,
    },

  }),

  getters: {
    getSampleData: (state) => state.sampeData,
    getLoginState:(state)=> state.userInfo.login,
    getUserInfo:(state)=> state.userInfo
  },
  actions: {
    setUserInfo(login: boolean, userName: string,role:string) {
      this.userInfo.login = login
      this.userInfo.userName = userName
      this.userInfo.role = role
    },
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
