-- Авторизация
CREATE SCHEMA IF NOT EXISTS auth;
CREATE USER 'auth'@'%' IDENTIFIED BY 'auth';
GRANT ALL PRIVILEGES ON auth.* TO 'auth'@'%';
-- Доставка
CREATE SCHEMA IF NOT EXISTS delivery;
CREATE USER 'delivery'@'%' IDENTIFIED BY 'delivery';
GRANT ALL PRIVILEGES ON delivery.* TO 'delivery'@'%';
-- Программа лояльности
CREATE SCHEMA IF NOT EXISTS loyalty;
CREATE USER 'loyalty'@'%' IDENTIFIED BY 'loyalty';
GRANT ALL PRIVILEGES ON loyalty.* TO 'loyalty'@'%';
-- Меню
CREATE SCHEMA IF NOT EXISTS menu;
CREATE USER 'menu'@'%' IDENTIFIED BY 'menu';
GRANT ALL PRIVILEGES ON menu.* TO 'menu'@'%';
-- Заказы
CREATE SCHEMA IF NOT EXISTS orders;
CREATE USER 'orders'@'%' IDENTIFIED BY 'orders';
GRANT ALL PRIVILEGES ON orders.* TO 'orders'@'%';
-- Рестораны
CREATE SCHEMA IF NOT EXISTS restaurant;
CREATE USER 'restaurant'@'%' IDENTIFIED BY 'restaurant';
GRANT ALL PRIVILEGES ON restaurant.* TO 'restaurant'@'%';
-- Расписание работы сотрудников и расчет зарплаты
CREATE SCHEMA IF NOT EXISTS salary;
CREATE USER 'salary'@'%' IDENTIFIED BY 'salary';
GRANT ALL PRIVILEGES ON salary.* TO 'salary'@'%';
-- Управление сотрудниками
CREATE SCHEMA IF NOT EXISTS staff;
CREATE USER 'staff'@'%' IDENTIFIED BY 'staff';
GRANT ALL PRIVILEGES ON staff.* TO 'staff'@'%';
-- Управление складскими запасами
CREATE SCHEMA IF NOT EXISTS stock;
CREATE USER 'stock'@'%' IDENTIFIED BY 'stock';
GRANT ALL PRIVILEGES ON stock.* TO 'stock'@'%';
-- Управление заказами поставщикам
CREATE SCHEMA IF NOT EXISTS supply;
CREATE USER 'supply'@'%' IDENTIFIED BY 'supply';
GRANT ALL PRIVILEGES ON supply.* TO 'supply'@'%';