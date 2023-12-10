-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 09, 2023 lúc 03:18 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `laptopstore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `SoLuong`, `DonGia`) VALUES
('HD1', 'SP1', 1, 25),
('HD2', 'SP4', 1, 26),
('HD3', 'SP4', 1, 26),
('HD3', 'SP5', 1, 20),
('HD4', 'SP2', 1, 14),
('HD4', 'SP6', 1, 17),
('HD5', 'SP4', 1, 26),
('HD5', 'SP7', 1, 20),
('HD5', 'SP8', 1, 24),
('HD6', 'SP8', 1, 24),
('HD7', 'SP1', 1, 25),
('HD7', 'SP2', 1, 14),
('HD7', 'SP7', 1, 20),
('HD8', 'SP6', 1, 17),
('HD8', 'SP7', 1, 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL,
  `DonGia` float UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`) VALUES
('PN1', 'SP1', 1, 25),
('PN2', 'SP4', 1, 26),
('PN2', 'SP6', 1, 17);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaKH` varchar(10) NOT NULL,
  `MaKM` varchar(10) NOT NULL,
  `NgayLap` date NOT NULL,
  `GioLap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `GioLap`, `TongTien`) VALUES
('HD1', 'NV2', 'KH1', 'KM1', '2023-11-07', '16:51:00', 25),
('HD2', 'NV2', 'KH1', 'KM4', '2023-11-26', '00:37:01', 26),
('HD3', 'NV3', 'KH1', 'KM1', '2023-12-03', '21:16:35', 46),
('HD4', 'NV3', 'KH1', 'KM1', '2023-12-04', '10:07:57', 31),
('HD5', 'NV3', 'KH2', 'KM1', '2023-12-04', '10:08:37', 70),
('HD6', 'NV3', 'KH2', 'KM1', '2023-12-04', '10:09:41', 24),
('HD7', 'NV3', 'KH1', 'KM1', '2023-12-04', '10:57:54', 59),
('HD8', 'NV3', 'KH1', 'KM1', '2023-12-04', '10:58:19', 37);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `TenKH` varchar(50) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `TrangThai`) VALUES
('KH1', 'Nguyễn Văn A', 'Hồ Chí Minh', '09342421234', 0),
('KH2', 'Trần Minh Anh', 'HCM', '0965844442', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` varchar(10) NOT NULL,
  `TenKM` varchar(100) NOT NULL,
  `DieuKienKM` float NOT NULL,
  `PhanTramKM` float NOT NULL,
  `NgayBD` date DEFAULT NULL,
  `NgayKT` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES
('KM1', 'Không khuyến mãi', 0, 0, '2023-11-07', '2024-11-30'),
('KM2', 'Giảm giá nhân ngày 30/4', 5, 5, '2023-04-27', '2023-05-02'),
('KM3', 'Khuyến mãi khách hàng thân thiết', 25, 5, '2023-11-01', '2023-11-30'),
('KM4', 'Giảm giá tết', 15, 5, '2023-11-15', '2023-11-26'),
('KM5', 'old promo', 5, 5, '2023-12-03', '2023-12-31');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLSP` varchar(10) NOT NULL,
  `TenLSP` varchar(70) NOT NULL,
  `Mota` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLSP`, `TenLSP`, `Mota`) VALUES
('LSP1', 'Apple', 'Các sản phẩm của Apple'),
('LSP2', 'Acer', 'May tinh tu Acer'),
('LSP3', 'Dell', 'San pham sieu ben tu Dell'),
('LSP4', 'Lenovo', 'Các sản phẩm tuyệt đẹp đến từ Lenovo'),
('LSP5', 'HP', 'Các sản phẩm đến từ thương hiệu HP'),
('LSP6', 'MSI', 'Các loại máy tính từ MSI'),
('LSP7', 'Razer X', 'Máy tình từ Razer'),
('LSP8', 'SamSung', 'Máy tính thiết kế đẹp từ Samsung'),
('LSP9', 'Asus', 'asus');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(10) NOT NULL,
  `TenNCC` varchar(70) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `Fax` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`, `Fax`) VALUES
('NCC1', 'Cty Samsung', 'TP HCM', '0123456789', '4598-8789-8789-7897'),
('NCC2', 'Cty Công Nghệ New Tech', 'Hà Nội', '012072545', '3133-1333-1211-4441'),
('NCC3', 'Cty Laptop Anh Nguyên', 'TP HCM', '0703192738', '2364-2974-2384-2394'),
('NCC4', 'Cty Viễn Thông Thành Đạt', 'TP HCM', '0501239237', '9823-6738-6739-6766'),
('NCC5', 'Cty New World', 'Bình Dương', '0222729221', '1111-7224-1123-1112'),
('NCC6', 'Cty Advance', 'Cần Thơ', '092323123', '1123-9112-5866-2234'),
('NCC7', 'Cty Thành Đạt', 'Đà Nẵng', '0989140736', '1271-1333-1111-4441'),
('NCC8', 'Cty Toàn Phát', 'TP HCM', '01206324234', '5343-2452-1666-2343');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `TenNV` text NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `NgaySinh`, `DiaChi`, `SDT`, `TrangThai`) VALUES
('NV1', 'Phan Trí Dũng', '1978-04-05', 'Đà Nẵng', '0145647854', 0),
('NV2', 'Nguyễn Văn A', '1989-06-15', 'Kiên Giang', '0981578293', 0),
('NV3', 'Trần Đức Thiện', '2000-11-24', 'Hà Nội', '0918958837', 0),
('NV4', 'Nguyễn Việt Anh ', '1990-11-09', 'Cần Thơ', '0911662462', 0),
('NV5', 'Dương Anh Huy', '1999-10-02', 'Cần Thơ', '0934523452', 1),
('NV6', 'Nguyễn Văn C', '2002-12-11', 'HCM', '0911111111111', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) NOT NULL,
  `TenQuyen` varchar(20) NOT NULL,
  `ChiTietQuyen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
('Q1', 'Manager', 'qlSanPham qlLoaiSanPham qlHoaDon qlKhuyenMai qlNhanVien qlKhachHang qlPhieuNhap qlNCC'),
('Q2', 'Nhân viên Bán hàng', 'qlBanHang xemHoaDon qlKhachHang'),
('Q3', 'Nhân viên nhập hàng', 'qlNhapHang qlPhieuNhap qlNCC'),
('Q4', 'Admin', 'qlTaiKhoan qlQuyen');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` varchar(10) NOT NULL,
  `MaNCC` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `MaNCC`, `MaNV`, `NgayNhap`, `GioNhap`, `TongTien`) VALUES
('PN1', 'NCC2', 'NV2', '2023-11-07', '14:56:20', 25),
('PN2', 'NCC2', 'NV1', '2023-12-03', '20:52:30', 43);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `MaLSP` varchar(30) NOT NULL,
  `TenSP` varchar(70) NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(10) UNSIGNED NOT NULL DEFAULT 1,
  `HinhAnh` varchar(200) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES
('SP1', 'LSP1', 'MacBook Air M2', 25, 21, 'air m2.jpg', 0),
('SP2', 'LSP2', 'Acer aspire 7', 14, 20, 'Acer aspire 7.jpg', 0),
('SP3', 'LSP2', 'Acer Nitro 5', 24, 16, 'Acer Nitro 5.jpg', 0),
('SP4', 'LSP9', 'Asus ROG G15', 26, 15, 'Asus ROG G15.jpg', 0),
('SP5', 'LSP9', 'Asus ROG G15Asus vivobook 15', 20, 17, 'Asus vivobook 15.png', 0),
('SP6', 'LSP3', 'Dell Latitude 3420', 17, 29, 'Dell Latitude 3420.jpg', 0),
('SP7', 'LSP8', 'SamSung galaxy book', 20, 27, 'galaxy book.jpg', 0),
('SP8', 'LSP5', 'HP pavilion 15', 24, 28, 'HP pavilion 15.jpg', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTaiKhoan` varchar(50) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaQuyen` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`TenTaiKhoan`, `MatKhau`, `MaNV`, `MaQuyen`) VALUES
('admin', 'admin', 'NV2', 'Q4'),
('Huy', '1234', 'NV5', 'Q1'),
('NhanVien', 'nv', 'NV3', 'Q2'),
('nhaphang', 'nhaphang', 'NV1', 'Q3'),
('QuanLy', 'quanly', 'NV4', 'Q1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MaSP` (`MaSP`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `MaSP` (`MaSP`),
  ADD KEY `MaPN` (`MaPN`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaKM` (`MaKM`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLSP`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNCC` (`MaNCC`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `LoaiSP` (`MaLSP`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTaiKhoan`),
  ADD KEY `MaQuyen` (`MaQuyen`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`) ON UPDATE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON UPDATE CASCADE,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `khuyenmai_ibfk_1` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLSP`) REFERENCES `loaisanpham` (`MaLSP`) ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `taikhoan_ibfk_3` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
