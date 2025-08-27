import { AxiosResponse } from "axios";
import { defineStore } from 'pinia'
import type { loginType, regType, User } from "./LoginReg";
import { postLoginData, postRegData } from "@/service/Login/LoginData";

export const useLoginRegStore = defineStore("loginRegStore", {
  state: () => ({
    user: null as User | null,
    isLoading: false,
    isLogin: false,
  }),

  getters: {
    getUser: (state) => state.user,
    getIsLogin: (state) => state.isLogin,
    getRole: (state) => state.user?.role || null,
  },

  actions: {
    async fetchLoginData(payload: loginType) {
      this.isLoading = true;
      try {
        const response: AxiosResponse<User> = await postLoginData(payload);
        this.user = response.data;
        this.isLogin = true;
      } catch (error) {
        console.error("Login error:", error);
      } finally {
        this.isLoading = false;
      }
    },

    async fetchRegData(payload: regType) {
      this.isLoading = true;
      try {
        const response: AxiosResponse<User> = await postRegData(payload);
        this.user = response.data;
      } catch (error) {
        console.error("Register error:", error);
      } finally {
        this.isLoading = false;
      }
    },

    logout() {
      this.user = null;
      this.isLogin = false;
    },
  },

  persist: true,
});
