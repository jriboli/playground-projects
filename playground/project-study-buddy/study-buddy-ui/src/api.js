import axios from "axios";

// Set the base URL for the API
const api = axios.create({
  baseURL: "http://127.0.0.1:8000", // Replace with your FastAPI server URL
});

// Function to get a user's profile using the JWT token
export const getUser = (token) => {
  return api.get("/users/me", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

// Example function to get all cheat sheets
// export const getCheatSheets = () => api.get("/cheatsheets");
export const getCheatSheets = async () => {
    try {
      const response = await api.get("/cheatsheets");
      return response.data;
    } catch (error) {
      console.error("Error fetching cheat sheets:", error);
      throw new Error("Failed to fetch cheat sheets. Please try again later.");
    }
  };
  
export const createCheatSheet = (data) => api.post("/cheatsheets", data);

export default api;
