import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/api";

class httpService {
    #url = '/board';

    async save(jsonBody){
        const res = await axios.post(this.#url, jsonBody);
        return res.data;
    }

    async findAll(params){
        const res = await axios.get(this.#url, {params});
        return res.data;
    }
    
    async findOne(no){
        const res = await axios.get(`${this.#url}/${no}`);
        return res.data
    }

    async update(jsonBody){
        const res = await axios.put(this.#url, jsonBody);
        return res.data;
    }

    async delete(params){
        const res = await axios.delete(this.#url, {params});
        return res.data; 
    }
}

export default new httpService();