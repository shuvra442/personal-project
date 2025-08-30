import { AxiosResponse } from "axios";
import { defineStore } from 'pinia'
import type { LoginResponse, loginType, regType, User } from "./LoginReg";
import { postLoginData, postRegData } from "@/service/Login/LoginData";
import apiClient from "@/config/axios";

export const useLoginRegStore = defineStore("loginRegStore", {
  state: () => ({
    user: null as User | null,
    token: null as string | null,
    isLoading: false,
    isLogin: false,
  }),

  getters: {
    getUser: (state) => state.user,
    getToken: (state) => state.token,
    getIsLogin: (state) => state.isLogin,
    getRole: (state) => state.user?.role || null,
  },

  actions: {
    async fetchLoginData(payload: loginType) {
      this.isLoading = true;
      try {
        const response: AxiosResponse<LoginResponse> = await postLoginData(payload);
        this.token = response.data.token;
        this.user = response.data.user;
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

    async handleGoogleLoginCallback(token: string) {
      this.token = token;
      this.isLogin = true;
    },

    async logout() {
      try {
        await apiClient.post("/logout"); // call backend to clear cookie
      } catch (error) {
        console.error("Logout error:", error);
      } finally {
        this.user = null;
        this.token = null;
        this.isLogin = false;
      }
    },
  },

  persist: true,
});
