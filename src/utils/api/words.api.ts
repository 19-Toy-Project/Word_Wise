import { AxiosInstance } from "axios";

class WordAPI {
  private client: AxiosInstance;
  constructor(client: AxiosInstance) {
    this.client = client;
  }
  async getWords() {}
}
export default WordAPI;
