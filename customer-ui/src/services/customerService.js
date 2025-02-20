import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v1/customers';

const customerService = {
  createCustomer: async (customerData) => {
    try {
      const response = await axios.post(API_BASE_URL, customerData);
      return response.data;
    } catch (error) {
      throw error.response.data;
    }
  },

  getAllCustomers: async () => {
    try {
      const response = await axios.get(API_BASE_URL);
      return response.data;
    } catch (error) {
      throw error.response.data;
    }
  }
};

export default customerService;