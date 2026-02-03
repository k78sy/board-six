import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/api";

class httpService {
    #url = '/board';

    async save(jsonBody){
        const res = await axios.post(this.#url, jsonBody);
        return res.data;
    }

}

export default new httpService();