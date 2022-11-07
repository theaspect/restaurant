-- Авторизация
CREATE SCHEMA IF NOT EXISTS auth;
CREATE USER 'auth'@'localhost' IDENTIFIED BY 'auth';
GRANT ALL PRIVILEGES ON auth.* TO 'auth'@'localhost';
-- Доставка
CREATE SCHEMA IF NOT EXISTS delivery;
CREATE USER 'delivery'@'localhost' IDENTIFIED BY 'delivery';
GRANT ALL PRIVILEGES ON delivery.* TO 'delivery'@'localhost';
-- Программа лояльности
CREATE SCHEMA IF NOT EXISTS loyalty;
CREATE USER 'loyalty'@'localhost' IDENTIFIED BY 'loyalty';
GRANT ALL PRIVILEGES ON loyalty.* TO 'loyalty'@'localhost';
-- Меню
CREATE SCHEMA IF NOT EXISTS menu;
CREATE USER 'menu'@'localhost' IDENTIFIED BY 'menu';
GRANT ALL PRIVILEGES ON menu.* TO 'menu'@'localhost';
-- Заказы
CREATE SCHEMA IF NOT EXISTS orders;
CREATE USER 'orders'@'localhost' IDENTIFIED BY 'orders';
GRANT ALL PRIVILEGES ON orders.* TO 'orders'@'localhost';
-- Рестораны
CREATE SCHEMA IF NOT EXISTS restaurant;
CREATE USER 'restaurant'@'localhost' IDENTIFIED BY 'restaurant';
GRANT ALL PRIVILEGES ON restaurant.* TO 'restaurant'@'localhost';
-- Расписание работы сотрудников и расчет зарплаты
CREATE SCHEMA IF NOT EXISTS salary;
CREATE USER 'salary'@'localhost' IDENTIFIED BY 'salary';
GRANT ALL PRIVILEGES ON salary.* TO 'salary'@'localhost';
-- Управление сотрудниками
CREATE SCHEMA IF NOT EXISTS staff;
CREATE USER 'staff'@'localhost' IDENTIFIED BY 'staff';
GRANT ALL PRIVILEGES ON staff.* TO 'staff'@'localhost';
-- Управление складскими запасами
CREATE SCHEMA IF NOT EXISTS stock;
CREATE USER 'stock'@'localhost' IDENTIFIED BY 'stock';
GRANT ALL PRIVILEGES ON stock.* TO 'stock'@'localhost';
-- Управление заказами поставщикам
CREATE SCHEMA IF NOT EXISTS supply;
CREATE USER 'supply'@'localhost' IDENTIFIED BY 'supply';
GRANT ALL PRIVILEGES ON supply.* TO 'supply'@'localhost';