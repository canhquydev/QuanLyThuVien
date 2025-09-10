use master
create database QLTV
use QLTV

create table SACH (
    MaSach nchar(10) primary key,
    TenSach nvarchar(100),
    TacGia nvarchar(100),
	TheLoai nvarchar(100),
    NhaXuatBan nvarchar(100),
    NamXuatBan int,
    SoLuong int,
	TinhTrang int
)


create table NGUOIMUON (
    MaNguoiMuon nchar(10) primary key,
    HoTen nvarchar(100),
	GioiTinh nvarchar(100),
    DiaChi nvarchar(100),	
    SoDienThoai nvarchar(10),
	Email nvarchar(100)
)


create table PHIEUMUON (
    MaPhieuMuon nchar(10) primary key,
    MaSach nchar(10),
    MaNguoiMuon nchar(10),
	SoLuong int,
    NgayMuon date,
    NgayTra date,
    TinhTrang nvarchar(100),
	SoLanGiaHan int,
    foreign key (MaSach) references SACH(MaSach),
    foreign key (MaNguoiMuon) references NGUOIMUON(MaNguoiMuon)
)

insert into SACH (MaSach, TenSach, TacGia, TheLoai, NhaXuatBan, NamXuatBan, SoLuong, TinhTrang)
values
('MS1', N'Kỹ thuật lập trình C/C++', N'Phạm Văn Ất', N'Giáo trình', N'NXB Khoa học và Kỹ thuật', 2020, 10, 1),
('MS2', N'Cấu trúc dữ liệu và giải thuật', N'Lê Minh Hoàng', N'Giáo trình', N'NXB Đại học Quốc gia Hà Nội', 2018, 8, 1),
('MS3', N'Toán rời rạc', N'Nguyễn Đức Nghĩa, Nguyễn Tô Thành', N'Giáo trình', N'NXB Khoa học và Kỹ thuật', 2019, 5, 1),
('MS4', N'Mạng máy tính', N'Kiến trúc sư mạng & Nhóm quản trị', N'Giáo trình', N'NXB Bách khoa Hà Nội', 2021, 7, 1),
('MS5', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Nguyễn Thị Hồng', N'Giáo trình', N'NXB Thống kê', 2022, 6, 1),
('MS6', N'Lập trình Java căn bản', N'Dương Hữu Thành', N'Tài liệu học tập', N'NXB Tổng hợp TP.HCM', 2020, 9, 1),
('MS7', N'Lập trình Python', N'Võ Duy Tuấn', N'Tài liệu học tập', N'NXB Trẻ', 2023, 12, 1),
('MS8', N'Trí tuệ nhân tạo', N'Stuart Russell, Peter Norvig ', N'Sách tham khảo', N'NXB Thế giới', 2019, 4, 1),
('MS9', N'Thiết kế Web', N'Lê Quốc Bảo', N'Tài liệu học tập', N'NXB Lao Động', 2021, 5, 1),
('MS10', N'An toàn và bảo mật', N'Vũ Thị Hương, Nguyễn Văn Hùng', N'Giáo trình', N'NXB Bách khoa Hà Nội', 2022, 3, 1),
('MS11', N'Lập trình hướng đối tượng', N'Bjarne Stroustrup', N'Sách tham khảo', N'NXB Khoa học và Kỹ thuật', 2017, 3, 0),
('MS12', N'Dế Mèn phiêu lưu ký', N'Tô Hoài', N'Truyện dài', N'NXB Kim Đồng', 1941, 15, 1),
('MS13', N'Số đỏ', N'Vũ Trọng Phụng', N'Tiểu thuyết', N'NXB Văn học', 1936, 10, 1),
('MS14', N'Tôi thấy hoa vàng trên cỏ xanh', N'Nguyễn Nhật Ánh', N'Truyện dài', N'NXB Trẻ', 2010, 20, 1),
('MS15', N'Đắc nhân tâm', N'Dale Carnegie ', N'Sách kỹ năng', N'NXB Tổng hợp TP.HCM', 1936, 30, 1),
('MS16', N'Nhà giả kim', N'Paulo Coelho', N'Tiểu thuyết', N'NXB Văn học', 1988, 18, 1),
('MS17', N'Doraemon - Tập 1', N'Fujiko F. Fujio', N'Truyện tranh', N'NXB Kim Đồng', 1969, 25, 1),
('MS18', N'Muôn kiếp nhân sinh', N'Nguyên Phong', N'Tâm linh, Triết học', N'NXB Tổng hợp TP.HCM', 2020, 15, 1),
('MS19', N'Lược sử loài người', N'Yuval Noah Harari ', N'Lịch sử, Khoa học', N'NXB Tri thức', 2011, 12, 1),
('MS20', N'Bảy viên ngọc rồng - Tập 1', N'Akira Toriyama', N'Truyện tranh', N'NXB Kim Đồng', 1984, 22, 1),
('MS21', N'Không gia đình', N'Hector Malot', N'Tiểu thuyết', N'NXB Văn học', 1878, 9, 1)

insert into NGUOIMUON (MaNguoiMuon, HoTen, GioiTinh, DiaChi, SoDienThoai, Email)
values
('NM1', N'Nguyễn Thị Lan', N'Nữ', N'123 Lê Lợi, TP.HCM', '0912345678', 'lan@gmail.com'),
('NM2', N'Lê Văn Tùng', N'Nam', N'456 Trần Phú, Hà Nội', '0987654321', 'tung@gmail.com'),
('NM3', N'Trần Thị Hoa', N'Nữ', N'789 Nguyễn Huệ, Đà Nẵng', '0911122233', 'hoa@gmail.com'),
('NM4', N'Phạm Văn Minh', N'Nam', N'12 Lý Thường Kiệt, Huế', '0933445566', 'minh@gmail.com'),
('NM5', N'Đỗ Thị Hồng', N'Nữ', N'35 Hai Bà Trưng, TP.HCM', '0977888999', 'hong@gmail.com'),
('NM6', N'Ngô Văn Tuấn', N'Nam', N'67 Quang Trung, Hà Nội', '0966112233', 'tuan@gmail.com'),
('NM7', N'Vũ Thị Mai', N'Nữ', N'89 Nguyễn Trãi, Cần Thơ', '0944556677', 'mai@gmail.com'),
('NM8', N'Trịnh Văn Hòa', N'Nam', N'101 Trường Chinh, TP.HCM', '0933221100', 'hoa_tv@gmail.com'),
('NM9', N'Lý Thị Tuyết', N'Nữ', N'23 Phan Bội Châu, Đà Lạt', '0922334455', 'tuyet@gmail.com'),
('NM10', N'Hồ Văn Quang', N'Nam', N'99 Pasteur, TP.HCM', '0911778899', 'quang@gmail.com')


insert into PHIEUMUON (MaPhieuMuon, MaSach, MaNguoiMuon, SoLuong, NgayMuon, NgayTra, TinhTrang, SoLanGiaHan)
values
('PM1', 'MS1', 'NM1', 1, '2025-05-25', '2025-06-15', N'Đang mượn', 0),
('PM2', 'MS3', 'NM2', 1, '2025-05-18', '2025-06-18', N'Đang mượn', 1),
('PM3', 'MS12', 'NM3', 2, '2025-06-08', '2025-06-22', N'Đang mượn', 0),
('PM4', 'MS14', 'NM4', 1, '2025-06-08', '2025-06-22', N'Đang mượn', 0),
('PM5', 'MS5', 'NM5', 1, '2025-05-10', '2025-06-01', N'Đã hết hạn', 0),
('PM6', 'MS6', 'NM6', 1, '2025-04-20', '2025-05-20', N'Đã hết hạn', 2),
('PM7', 'MS7', 'NM7', 1, '2025-05-15', '2025-05-29', N'Đã trả', 0),
('PM8', 'MS8', 'NM8', 1, '2025-05-01', '2025-06-05', N'Đã trả', 1),
('PM9', 'MS17', 'NM9', 3, '2025-06-01', '2025-06-29', N'Đang mượn', 0),
('PM10', 'MS20', 'NM10', 2, '2025-05-28', '2025-06-11', N'Đang mượn', 0)

select * from SACH
select * from PHIEUMUON
select * from NGUOIMUON
--delete from PHIEUMUON
--delete from SACH
--delete from NGUOIMUON

--BACKUP DATABASE QLTV TO DISK = 'D:\backup.bak';

--RESTORE DATABASE QLTV FROM DISK = 'D:\backup.bak' WITH REPLACE