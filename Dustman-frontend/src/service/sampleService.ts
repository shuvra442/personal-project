// import { useToast } from "vue-toast-notification";
// import apiClient from "../config/axios";

// import type { AxiosResponse } from "axios";
// import { useSampleStore } from "@/stores/sample";
// const toast = useToast();
// interface registerData {
//     fullName: string;
//     email: string;
//     password: string;
// }
// interface loginData {
//     username: string;
//     password: string;
// }
// export function getSampleData(): Promise<AxiosResponse<unknown>> {
//     return apiClient.get(`https://jsonplaceholder.typicode.com/users/1`);
// }

// // For User Register
// export async function register(registerData: registerData) {
//     const formdata = {name:registerData.fullName,email:registerData.email,password:registerData.password}
//     try {
//         const response = await apiClient.post("/create", formdata);

//         toast.success(`Welcome ${registerData.fullName}`);
//     } catch (error: any) {
//         toast.error(error.response.data);
//     }
// }

// // For User Login
// export async function login(loginData: loginData) {
//     const formData ={email:loginData.username,password:loginData.password}

//     try {
//         const response = await apiClient.post("/login", formData);
        
//         console.log("response=>", response);
//         return response.data;
//     } catch (error: any) {
//         if (error.status===409) {

//           toast.error(error.response.data)

//         }
//         toast.error(error.response.data);
//     }
// }
