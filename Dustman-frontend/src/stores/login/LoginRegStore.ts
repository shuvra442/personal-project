import { AxiosResponse } from "axios";
import { defineStore } from 'pinia'
import type { loginType, regType } from "./LoginReg";
import { postLoginData, postRegData } from "@/service/Login/LoginData";

export const useLoinRegStore = defineStore('loginRegStore', {
  state: () => ({
    login: [] as loginType[] | null,
    reg: [] as regType[] | null,
    isLoading: false,
    isLogin: false
  }),

  getters: {
    getLoginData: (state) => state.login,
    getIsLogin: (state) => state.isLogin,
    getRegData: (state) => state.reg
  },

  actions: {
    // 🔹 Login Action
    async fetchLoginData(payload: loginType) {
      this.isLoading = true
      return await postLoginData(payload)
        .then((response: AxiosResponse) => {
          this.isLoading = false
          this.login = response.data
          this.isLogin = true
        })
        .catch((error) => {
          this.isLoading = false
          console.error("Login error:", error)
        })
    },

    // 🔹 Register Action
    async fetchRegData(payload: regType) {
      this.isLoading = true
      return await postRegData(payload)
        .then((response: AxiosResponse) => {
          this.isLoading = false
          this.reg = response.data
          console.log("Registered user:", this.reg)
        })
        .catch((error) => {
          this.isLoading = false
          console.error("Register error:", error)
        })
    }
  },

  persist: true,
})
