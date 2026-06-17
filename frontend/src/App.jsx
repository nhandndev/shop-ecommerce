import React, { useContext } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, AuthContext } from './context/AuthContext';
import Login from './pages/Login';
import Register from './pages/Register';

// A simple protected route wrapper
const ProtectedRoute = ({ children }) => {
  const { token, isLoading } = useContext(AuthContext);
  if (isLoading) return <div>Đang tải...</div>;
  if (!token) return <Navigate to="/login" replace />;
  return children;
};

// A dummy Home component for testing
const Home = () => {
  const { user, logoutUser } = useContext(AuthContext);
  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Trang chủ Thương mại Điện tử</h1>
      <div className="bg-white p-6 rounded-lg shadow-md max-w-md">
        <p className="mb-4">Chào mừng, <span className="font-semibold text-blue-600">{user?.username || 'Người dùng'}</span>!</p>
        <button 
          onClick={logoutUser}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
        >
          Đăng xuất
        </button>
      </div>
    </div>
  );
};

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route 
            path="/" 
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            } 
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
