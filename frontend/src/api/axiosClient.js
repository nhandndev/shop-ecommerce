import axios from 'axios';

const axiosClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/v1',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request Interceptor: Attach token if available
axiosClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response Interceptor: Handle 401 globally
axiosClient.interceptors.response.use(
  (response) => {
    // Return data directly if successful, depending on your backend response structure
    return response.data;
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      // Token expired or invalid
      console.warn('Unauthorized! Token invalid or expired. Redirecting to login.');
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      
      // Force reload or redirect. Using window.location to ensure full state reset.
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
