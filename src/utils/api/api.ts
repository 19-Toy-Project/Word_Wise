import axios, { AxiosInstance } from "axios";
import UserAPI from "./user.api";
import WordAPI from "./words.api";

export const BASE_URL = "";

class API {
  #baseURL = BASE_URL;
  #client: AxiosInstance;
  user;
  word;

  constructor() {
    this.#client = axios.create({ baseURL: this.#baseURL });
    this.user = new UserAPI(this.#client);
    this.word = new WordAPI(this.#client);
  }
}
const api = new API();

export default api;
