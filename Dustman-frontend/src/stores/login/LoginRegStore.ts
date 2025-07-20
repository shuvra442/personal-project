import { AxiosResponse } from "axios";
import { defineStore } from 'pinia'
import type { loginType, regType} from "./LoginReg";
import { postLoginData } from "@/service/Login/LoginData";

export const useLoinRegStore = defineStore('loginRegStore',{
  state: () => ({

    login: [] as loginType[] | null,
    reg : [] as regType[] | null,
    isLoading: false,
    isLogin:false

  }),

  getters: {

    getLoginData:(state)=> state.login,
    getIsLogin:(state)=>state.isLogin
  
  },
  actions: {
  
   async fetchLoginData(payload: loginType) {
        this.isLoading = true
        console.log("The data is :: ",payload.email, payload.password)
        return await postLoginData(payload)
        .then((response: AxiosResponse)=> {
            this.isLoading = false
            this.login = response.data
            console.log("The data is :::", this.login)
            this.isLogin=true
        })
        .catch((error)=> {
            console.log("The error is :", error)
        })

    }
  },
      persist: true,
})
