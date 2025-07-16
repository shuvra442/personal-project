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


export default apiClient
