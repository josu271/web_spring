-- ======================================
-- Categorias
-- ======================================
INSERT INTO Categorias (Nombre, Descripcion, Estado) VALUES
('Laptops', 'Portátiles de diversas marcas', true),
('Accesorios', 'Periféricos y componentes', true),
('Componentes', 'Piezas internas de PC', true),
('Servicios Técnicos', 'Reparaciones y mantenimiento', true);


-- ======================================
-- Producto
-- ======================================
INSERT INTO Producto (ID_Categoria, Nombre, Descripcion, Tipo_Producto, Precio, Stock) VALUES
(1, 'Laptop HP Pavilion', 'Laptop 15.6” i5 16GB RAM', 'Laptop', 3200.00, 10),
(1, 'Laptop Lenovo IdeaPad 3', 'Laptop Ryzen 5 8GB RAM', 'Laptop', 2800.00, 8),
(2, 'Mouse Logitech M170', 'Mouse inalámbrico USB', 'Accesorio', 60.00, 50),
(2, 'Teclado Redragon Kumara', 'Teclado mecánico gamer', 'Accesorio', 180.00, 20),
(3, 'SSD Kingston 480GB', 'Unidad de estado sólido', 'Componente', 230.00, 25),
(3, 'Memoria RAM 8GB DDR4', 'Memoria para laptops y PCs', 'Componente', 150.00, 40),
(4, 'Mantenimiento Preventivo', 'Limpieza y revisión general', 'Servicio', 120.00, 100),
(4, 'Formateo e instalación de SO', 'Windows 11 o Linux', 'Servicio', 100.00, 100);


-- ======================================
-- Cliente
-- ======================================
INSERT INTO Cliente (DNI_Cliente, Nombre, Apellido, Direccion, Correo, Celular) VALUES
(78945612, 'Carlos', 'Rojas', 'Av. Los Jardines 234', 'carlosr@gmail.com', '987654321'),
(70321458, 'Lucía', 'Torres', 'Calle Central 102', 'luciat@gmail.com', '923456789'),
(75632147, 'Miguel', 'Ramírez', 'Av. Industrial 589', 'miguelr@hotmail.com', '912345678'),
(71258963, 'Ana', 'Sánchez', 'Jr. Grau 456', 'anas@gmail.com', '987321654');

-- ======================================
-- Empleado
-- ======================================
INSERT INTO Empleado (DNI_Empleado, Nombre, Apellido, Direccion, Correo, Celular, Cargo,Contra) VALUES
(47896325, 'Jorge', 'Mendoza', 'Av. Lima 789', 'jorge.mendoza@empresa.com', '999888777', 'Administrador', '123'),
(48965231, 'Luis', 'Cáceres', 'Jr. San Martín 123', 'luis.caceres@empresa.com', '988776655', 'Vendedor', '123'),
(50231478, 'María', 'Huamán', 'Calle Primavera 456', 'maria.huaman@empresa.com', '977665544', 'Técnico', '123');

-- ======================================
-- Ventas
-- ======================================
INSERT INTO Ventas (DNI_Cliente, DNI_Empleado, Metodo_Pago, Total) VALUES
(78945612, 48965231, 'Tarjeta', 3380.00),
(70321458, 48965231, 'Efectivo', 2430.00),
(75632147, 48965231, 'Yape', 2800.00);

-- ======================================
-- DetalleVenta
-- ======================================
INSERT INTO DetalleVenta (ID_Producto, ID_Ventas, Cantidad, Precio_Unitario, Subtotal) VALUES
(1, 1, 1, 3200.00, 3200.00),
(3, 1, 3, 60.00, 180.00),
(5, 2, 1, 230.00, 230.00),
(6, 2, 1, 150.00, 150.00),
(8, 2, 1, 100.00, 100.00),
(2, 3, 1, 2800.00, 2800.00);

-- ======================================
-- Cita_Tecnica
-- ======================================
INSERT INTO Cita_Tecnica (DNI_Cliente, DNI_Empleado, Servicio, Estado, Descripcion, Fecha_Programada) VALUES
(78945612, 50231478, 'Mantenimiento Preventivo', 'Pendiente', 'Cliente solicita limpieza y revisión del equipo HP', '2025-11-03 10:00:00'),
(70321458, 50231478, 'Formateo e instalación de SO', 'Atendida', 'Se instaló Windows 11 Pro', '2025-10-25 15:00:00'),
(71258963, 50231478, 'Revisión de hardware', 'Cancelada', 'Cliente canceló la cita', '2025-10-20 09:00:00');