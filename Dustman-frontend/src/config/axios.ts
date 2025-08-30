import { useLoginRegStore } from '@/stores/login/LoginRegStore';
import axios from 'axios'

const apiClient = axios.create({
  baseURL: "http://localhost:9000",
  // baseURL: import.meta.env.VITE_ENDPOINT_URL,
  withCredentials: true,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})


apiClient.interceptors.request.use((config) => {
  const store = useLoginRegStore();
  if (store.token) {
    config.headers.Authorization = `Bearer ${store.token}`;
  }
  return config;
});


export default apiClient
