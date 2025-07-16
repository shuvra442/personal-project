import { loginType } from "@/stores/login/LoginReg";
import { AxiosResponse } from "axios";
import apiClient from "../../config/axios";

export function postLoginData(payload: loginType): Promise<AxiosResponse<loginType>> {
    return apiClient.post(`/login`, payload);
}