import { LoginResponse, loginType, regType, User } from "@/stores/login/LoginReg";
import { AxiosResponse } from "axios";
import apiClient from "../../config/axios";

export function postLoginData(payload: loginType): Promise<AxiosResponse<LoginResponse>> {
    return apiClient.post(`/login`, payload);
}

// Reg
export function postRegData(payload: regType): Promise<AxiosResponse<User>> {
  const formData = new FormData();

  // append JSON part
  formData.append(
    "user",
    new Blob([JSON.stringify({
      name: payload.name,
      email: payload.email,
      password: payload.password,
      phoneNo: payload.phoneNo,
      address: payload.address,
    })], { type: "application/json" })
  );

  // append file part (only if selected)
  if (payload.userImage) {
    formData.append("file", payload.userImage);
  }

  return apiClient.post(`/register`, formData, {
    headers: { "Content-Type": "multipart/form-data" }
  });
}