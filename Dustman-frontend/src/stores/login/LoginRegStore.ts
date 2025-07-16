import { AxiosResponse } from "axios";
import { defineStore } from 'pinia'
import type { loginType, regType} from "./LoginReg";
import { postLoginData } from "@/service/Login/LoginData";

export const useLoinRegStore = defineStore('loginRegStore',{
  state: () => ({

    login: [] as loginType[] | null,
    reg : [] as regType[] | null,
    isLoading: false,

  }),

  getters: {

    postLoginState:(state)=> state.login,
  
  },
  actions: {
  
    fetchLoginData(payload: loginType) {
        this.isLoading = true
        console.log("The data is :: ",payload.email, payload.password)
        return postLoginData(payload)
        .then((response: AxiosResponse)=> {
            this.isLoading = false
            this.login = response.data
            console.log("The data is :::", this.login)
        })
        .catch((error)=> {
            console.log("The error is :", error)
        })

    }
  },
})
