import { AxiosInstance } from "axios";

class UserAPI {
  private client: AxiosInstance;
  constructor(client: AxiosInstance) {
    this.client = client;
  }
  async getUser() {}
}

export default UserAPI;
