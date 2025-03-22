import apiClient from '../config/axios'

import type { AxiosResponse } from 'axios'

export function getSampleData(): Promise<AxiosResponse<unknown>> {
  return apiClient.get(`https://jsonplaceholder.typicode.com/users/1`)
}
