import React, { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import { LogOut, ShoppingCart, Search, Clock, ChevronRight } from 'lucide-react';

// --- MOCK DATA ---
const MOCK_BANNERS = [
  { imageUrl: 'https://images.unsplash.com/photo-1607082348824-0a96f2a4b9da?w=1200&h=400&fit=crop', targetUrl: '/sale1' },
  { imageUrl: 'https://images.unsplash.com/photo-1607083206869-4c7672e72a8a?w=1200&h=400&fit=crop', targetUrl: '/sale2' },
];

const MOCK_CATEGORIES = [
  { id: 1, name: 'Thời Trang Nam', imageUrl: 'https://images.unsplash.com/photo-1490578474895-699cd4e2cf59?w=100&h=100&fit=crop' },
  { id: 2, name: 'Điện Thoại & Phụ Kiện', imageUrl: 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=100&h=100&fit=crop' },
  { id: 3, name: 'Thiết Bị Điện Tử', imageUrl: 'https://images.unsplash.com/photo-1498049794561-7780e7231661?w=100&h=100&fit=crop' },
  { id: 4, name: 'Máy Tính & Laptop', imageUrl: 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=100&h=100&fit=crop' },
  { id: 5, name: 'Mẹ & Bé', imageUrl: 'https://images.unsplash.com/photo-1519689680058-324335c77eba?w=100&h=100&fit=crop' },
  { id: 6, name: 'Nhà Cửa & Đời Sống', imageUrl: 'https://images.unsplash.com/photo-1513694203232-719a280e022f?w=100&h=100&fit=crop' },
  { id: 7, name: 'Mỹ Phẩm', imageUrl: 'https://images.unsplash.com/photo-1596462502278-27bf85033e5a?w=100&h=100&fit=crop' },
  { id: 8, name: 'Đồng Hồ', imageUrl: 'https://images.unsplash.com/photo-1524805444758-089113d48a6d?w=100&h=100&fit=crop' },
];

const MOCK_FLASH_SALE = {
  flashSaleId: 1,
  startTime: '2026-06-25T00:00:00',
  endTime: '2026-06-25T12:00:00',
  state: 'CAMPAIGN IS STARTING',
  isPurchase: true,
  items: [
    { productId: 101, productTitle: 'Tai nghe Bluetooth 5.0 không dây', image: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300&h=300&fit=crop', variantId: 1, variantName: 'Đen', originPrice: 500000, flashSalePrice: 199000, flashSaleStock: 100, flashSaleSold: 45 },
    { productId: 102, productTitle: 'Đồng hồ thông minh theo dõi sức khỏe', image: 'https://images.unsplash.com/photo-1546868871-7041f2a55e12?w=300&h=300&fit=crop', variantId: 2, variantName: 'Bạc', originPrice: 800000, flashSalePrice: 499000, flashSaleStock: 50, flashSaleSold: 49 },
    { productId: 103, productTitle: 'Sạc dự phòng 10000mAh sạc nhanh', image: 'https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?w=300&h=300&fit=crop', variantId: 3, variantName: 'Trắng', originPrice: 300000, flashSalePrice: 150000, flashSaleStock: 200, flashSaleSold: 120 },
    { productId: 104, productTitle: 'Bàn phím cơ không dây TKL', image: 'https://images.unsplash.com/photo-1595225476474-87563907a212?w=300&h=300&fit=crop', variantId: 4, variantName: 'Xám', originPrice: 1200000, flashSalePrice: 799000, flashSaleStock: 30, flashSaleSold: 5 },
  ]
};

const MOCK_RECOMMEND_GRID = [
  { id: 201, title: 'Áo Khoác Nam Thể Thao Mùa Đông Siêu Ấm', mainImage: 'https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=400&h=400&fit=crop', minPrice: 250000, maxPrice: 350000 },
  { id: 202, title: 'Giày Sneaker Thể Thao Năng Động Đi Học', mainImage: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400&h=400&fit=crop', minPrice: 500000, maxPrice: 650000 },
  { id: 203, title: 'Balo Laptop Chống Nước Ngăn Chứa Rộng Rãi', mainImage: 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=400&h=400&fit=crop', minPrice: 199000, maxPrice: 250000 },
  { id: 204, title: 'Bình Giữ Nhiệt Inox 304 Cấu Trúc Đa Lớp', mainImage: 'https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=400&h=400&fit=crop', minPrice: 99000, maxPrice: 150000 },
  { id: 205, title: 'Kính Mát Thời Trang Gọng Vuông Cổ Điển', mainImage: 'https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=400&h=400&fit=crop', minPrice: 75000, maxPrice: 99000 },
  { id: 206, title: 'Loa Bluetooth Mini Bass Mạnh Mẽ', mainImage: 'https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400&h=400&fit=crop', minPrice: 350000, maxPrice: 450000 },
];

export default function Home() {
  const { user, logoutUser } = useContext(AuthContext);

  // Format currency
  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
  };

  return (
    <div className="min-h-screen bg-gray-50 pb-10">
      {/* Header */}
      <header className="bg-white shadow-sm sticky top-0 z-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center h-16">
            <div className="flex items-center space-x-8">
              <h1 className="text-2xl font-bold text-orange-500 tracking-tight">SHOP<span className="text-gray-900">ECO</span></h1>
              <div className="hidden md:flex relative w-96">
                <input 
                  type="text" 
                  placeholder="Tìm kiếm sản phẩm, thương hiệu..." 
                  className="w-full pl-4 pr-10 py-2 rounded-full border border-gray-300 focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent"
                />
                <button className="absolute right-0 top-0 mt-2 mr-3 text-gray-500 hover:text-orange-500">
                  <Search size={20} />
                </button>
              </div>
            </div>
            
            <div className="flex items-center space-x-6">
              <div className="relative text-gray-600 hover:text-orange-500 cursor-pointer transition">
                <ShoppingCart size={24} />
                <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center font-bold">3</span>
              </div>
              <div className="h-8 w-px bg-gray-300"></div>
              <div className="flex items-center space-x-3 group cursor-pointer relative">
                <div className="h-9 w-9 rounded-full bg-orange-100 flex items-center justify-center text-orange-600 font-bold text-lg">
                  {user?.username?.charAt(0).toUpperCase() || 'U'}
                </div>
                <div className="hidden md:block">
                  <p className="text-sm font-medium text-gray-700 leading-tight">Chào, {user?.username || 'Bạn'}</p>
                  <p className="text-xs text-gray-500 leading-tight">Tài khoản</p>
                </div>
                {/* Dropdown on hover */}
                <div className="absolute right-0 top-10 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-10 hidden group-hover:block border border-gray-100">
                  <button 
                    onClick={logoutUser}
                    className="flex items-center w-full px-4 py-2 text-sm text-red-600 hover:bg-gray-100"
                  >
                    <LogOut className="mr-2" /> Đăng xuất
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 mt-6 space-y-8">
        
        {/* Banner Carousel Section */}
        <section className="rounded-xl overflow-hidden shadow-sm">
          <div className="relative w-full h-48 md:h-80 bg-gray-200 flex overflow-x-auto snap-x snap-mandatory hide-scrollbar">
            {MOCK_BANNERS.map((banner, index) => (
              <img 
                key={index} 
                src={banner.imageUrl} 
                alt={`Banner ${index}`} 
                className="w-full h-full object-cover snap-center shrink-0" 
              />
            ))}
          </div>
        </section>

        {/* Quick Categories Section */}
        <section className="bg-white p-6 rounded-xl shadow-sm">
          <h2 className="text-xl font-bold text-gray-800 mb-6">Danh Mục Nổi Bật</h2>
          <div className="grid grid-cols-4 md:grid-cols-8 gap-4">
            {MOCK_CATEGORIES.map(category => (
              <div key={category.id} className="flex flex-col items-center cursor-pointer group">
                <div className="w-16 h-16 rounded-full overflow-hidden mb-3 border-2 border-transparent group-hover:border-orange-500 transition shadow-sm bg-gray-50">
                  <img src={category.imageUrl} alt={category.name} className="w-full h-full object-cover group-hover:scale-110 transition duration-300" />
                </div>
                <span className="text-xs text-center text-gray-600 font-medium group-hover:text-orange-500">{category.name}</span>
              </div>
            ))}
          </div>
        </section>

        {/* Flash Sale Section */}
        <section className="bg-white rounded-xl shadow-sm overflow-hidden border border-red-100">
          <div className="bg-gradient-to-r from-red-500 to-orange-500 p-4 flex justify-between items-center text-white">
            <div className="flex items-center space-x-3">
              <Clock size={24} className="animate-pulse" />
              <h2 className="text-xl md:text-2xl font-bold italic uppercase tracking-wider">Flash Sale</h2>
              <div className="hidden md:flex space-x-1 items-center bg-black bg-opacity-20 px-3 py-1 rounded-full text-sm font-mono">
                <span className="bg-black text-white px-2 py-0.5 rounded">12</span>:
                <span className="bg-black text-white px-2 py-0.5 rounded">34</span>:
                <span className="bg-black text-white px-2 py-0.5 rounded">56</span>
              </div>
            </div>
            <button className="text-sm font-medium hover:underline flex items-center">
              Xem tất cả <ChevronRight />
            </button>
          </div>
          
          <div className="p-6">
            <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
              {MOCK_FLASH_SALE.items.map(item => {
                const percentSold = Math.round((item.flashSaleSold / item.flashSaleStock) * 100);
                const isAlmostSoldOut = percentSold > 80;
                
                return (
                  <div key={item.productId} className="group cursor-pointer">
                    <div className="relative rounded-lg overflow-hidden mb-3 border border-gray-100 shadow-sm aspect-square">
                      <div className="absolute top-2 right-2 bg-yellow-400 text-yellow-900 text-xs font-bold px-2 py-1 rounded-sm z-10">
                        -{Math.round((1 - item.flashSalePrice/item.originPrice) * 100)}%
                      </div>
                      <img src={item.image} alt={item.productTitle} className="w-full h-full object-cover group-hover:scale-105 transition duration-300" />
                    </div>
                    <h3 className="text-sm font-medium text-gray-800 line-clamp-2 mb-1 group-hover:text-orange-500">{item.productTitle}</h3>
                    <div className="flex items-end justify-between mb-2">
                      <span className="text-lg font-bold text-red-500">{formatCurrency(item.flashSalePrice)}</span>
                      <span className="text-xs text-gray-400 line-through">{formatCurrency(item.originPrice)}</span>
                    </div>
                    <div className="w-full bg-gray-200 rounded-full h-3.5 relative overflow-hidden">
                      <div 
                        className={`h-full ${isAlmostSoldOut ? 'bg-red-500' : 'bg-orange-500'}`} 
                        style={{ width: `${percentSold}%` }}
                      ></div>
                      <span className="absolute inset-0 flex items-center justify-center text-[10px] font-bold text-white shadow-sm drop-shadow-md">
                        {isAlmostSoldOut ? 'Sắp hết' : `Đã bán ${item.flashSaleSold}`}
                      </span>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </section>

        {/* Recommended Grid Section */}
        <section>
          <div className="flex items-center justify-between mb-6">
            <h2 className="text-xl font-bold text-gray-800 border-l-4 border-orange-500 pl-3">Gợi Ý Hôm Nay</h2>
          </div>
          <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-4">
            {MOCK_RECOMMEND_GRID.map(product => (
              <div key={product.id} className="bg-white rounded-lg p-3 shadow-sm hover:shadow-md transition border border-gray-100 cursor-pointer group">
                <div className="relative aspect-square rounded-md overflow-hidden mb-3">
                  <img src={product.mainImage} alt={product.title} className="w-full h-full object-cover group-hover:scale-105 transition duration-500" />
                  <div className="absolute inset-0 bg-black bg-opacity-0 group-hover:bg-opacity-10 transition duration-300"></div>
                </div>
                <h3 className="text-sm font-medium text-gray-700 line-clamp-2 min-h-[40px] mb-2">{product.title}</h3>
                <div className="flex flex-col">
                  {product.minPrice === product.maxPrice ? (
                    <span className="text-base font-bold text-orange-500">{formatCurrency(product.minPrice)}</span>
                  ) : (
                    <span className="text-base font-bold text-orange-500">{formatCurrency(product.minPrice)} - {formatCurrency(product.maxPrice)}</span>
                  )}
                  <span className="text-xs text-gray-400 mt-1">Đã bán 1.2k</span>
                </div>
              </div>
            ))}
          </div>
          <div className="mt-8 flex justify-center">
            <button className="px-6 py-2 border-2 border-orange-500 text-orange-500 font-medium rounded-md hover:bg-orange-50 transition w-full md:w-auto">
              Xem Thêm Sản Phẩm
            </button>
          </div>
        </section>
        
      </main>

      {/* Footer */}
      <footer className="bg-white mt-16 border-t border-gray-200 pt-10 pb-6">
        <div className="max-w-7xl mx-auto px-4 text-center">
          <p className="text-gray-500 text-sm">© 2026 Shop Ecommerce. Thực hiện giao diện mẫu bằng React & Tailwind CSS.</p>
        </div>
      </footer>

      <style jsx="true">{`
        .hide-scrollbar::-webkit-scrollbar {
          display: none;
        }
        .hide-scrollbar {
          -ms-overflow-style: none;
          scrollbar-width: none;
        }
      `}</style>
    </div>
  );
}
