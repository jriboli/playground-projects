const API_URL = process.env.REACT_APP_STRAPI_URL;

class StrapiService {
    constructor() {
      this.baseURL = API_URL;
    }
  
    async get(endpoint, params = {}) {
      try {
        const url = new URL(`${this.baseURL}/${endpoint}`);
        Object.keys(params).forEach((key) =>
          url.searchParams.append(key, params[key])
        );
  
        const response = await fetch(url);
        if (!response.ok) throw new Error(`Error: ${response.statusText}`);
  
        const data = await response.json();
        return data;
      } catch (error) {
        console.error(`GET ${endpoint} failed:`, error);
        return null;
      }
    }
  
    async post(endpoint, body = {}, authToken = "") {
      try {
        const response = await fetch(`${this.baseURL}/${endpoint}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            ...(authToken && { Authorization: `Bearer ${authToken}` }),
          },
          body: JSON.stringify(body),
        });
  
        if (!response.ok) throw new Error(`Error: ${response.statusText}`);
  
        return await response.json();
      } catch (error) {
        console.error(`POST ${endpoint} failed:`, error);
        return null;
      }
    }
  
    async delete(endpoint, authToken = "") {
      try {
        const response = await fetch(`${this.baseURL}/${endpoint}`, {
          method: "DELETE",
          headers: {
            ...(authToken && { Authorization: `Bearer ${authToken}` }),
          },
        });
  
        if (!response.ok) throw new Error(`Error: ${response.statusText}`);
  
        return await response.json();
      } catch (error) {
        console.error(`DELETE ${endpoint} failed:`, error);
        return null;
      }
    }
  }
  
  export default new StrapiService();
  