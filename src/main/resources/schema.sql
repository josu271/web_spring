-- ======================================
-- TABLA: Categorias
-- ======================================
CREATE TABLE IF NOT EXISTS Categorias (
    ID_Categoria INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(100),
    Estado BOOLEAN NOT NULL,
    Status VARCHAR(20) DEFAULT 'ACTIVO'
);

-- ======================================
-- TABLA: Producto (Stock eliminado)
-- ======================================
CREATE TABLE IF NOT EXISTS Producto (
    ID_Producto INT PRIMARY KEY AUTO_INCREMENT,
    ID_Categoria INT NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(100),
    Tipo_Producto VARCHAR(50),
    Precio DECIMAL(10,2) NOT NULL,
    Status VARCHAR(20) DEFAULT 'ACTIVO',
    CONSTRAINT FK_Producto_Categoria FOREIGN KEY (ID_Categoria)
        REFERENCES Categorias(ID_Categoria)
);

-- ======================================
-- TABLA: Cliente
-- ======================================
CREATE TABLE IF NOT EXISTS Cliente (
    DNI_Cliente INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Direccion VARCHAR(100),
    Correo VARCHAR(50),
    Celular VARCHAR(20),
    Status VARCHAR(20) DEFAULT 'ACTIVO'
);

-- ======================================
-- TABLA: Empleado
-- ======================================
CREATE TABLE IF NOT EXISTS Empleado (
    DNI_Empleado INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Direccion VARCHAR(100),
    Correo VARCHAR(50),
    Celular VARCHAR(20),
    Cargo VARCHAR(20),
    Contra VARCHAR(100) NOT NULL,
    Status VARCHAR(20) DEFAULT 'ACTIVO'
);

-- ======================================
-- TABLA: Ventas
-- ======================================
CREATE TABLE IF NOT EXISTS Ventas (
    ID_Ventas INT PRIMARY KEY AUTO_INCREMENT,
    DNI_Cliente INT NOT NULL,
    DNI_Empleado INT NOT NULL,
    Fecha_Venta TIMESTAMP,
    Metodo_Pago VARCHAR(20),
    Total DECIMAL(10,2) NOT NULL,
    Status VARCHAR(20) DEFAULT 'COMPLETADA',
    CONSTRAINT FK_Ventas_Cliente FOREIGN KEY (DNI_Cliente)
        REFERENCES Cliente(DNI_Cliente),
    CONSTRAINT FK_Ventas_Empleado FOREIGN KEY (DNI_Empleado)
        REFERENCES Empleado(DNI_Empleado)
);

-- ======================================
-- TABLA: DetalleVenta
-- ======================================
CREATE TABLE IF NOT EXISTS DetalleVenta (
    ID_Detalle INT PRIMARY KEY AUTO_INCREMENT,
    ID_Producto INT NOT NULL,
    ID_Ventas INT NOT NULL,
    Cantidad INT NOT NULL,
    Precio_Unitario DECIMAL(10,2) NOT NULL,
    Subtotal DECIMAL(10,2) NOT NULL,
    Status VARCHAR(20) DEFAULT 'ACTIVO',
    CONSTRAINT FK_DetalleVenta_Producto FOREIGN KEY (ID_Producto)
        REFERENCES Producto(ID_Producto),
    CONSTRAINT FK_DetalleVenta_Venta FOREIGN KEY (ID_Ventas)
        REFERENCES Ventas(ID_Ventas)
);

-- ======================================
-- TABLA: Cita_Tecnica
-- ======================================
CREATE TABLE IF NOT EXISTS Cita_Tecnica (
    ID_CitaTecnica INT PRIMARY KEY AUTO_INCREMENT,
    DNI_Cliente INT NOT NULL,
    DNI_Empleado INT NOT NULL,
    Servicio VARCHAR(50),
    Estado VARCHAR(15),
    Descripcion VARCHAR(150),
    Fecha_Programada TIMESTAMP,
    Status VARCHAR(20) DEFAULT 'ACTIVO',
    CONSTRAINT FK_Cita_Cliente FOREIGN KEY (DNI_Cliente)
        REFERENCES Cliente(DNI_Cliente),
    CONSTRAINT FK_Cita_Empleado FOREIGN KEY (DNI_Empleado)
        REFERENCES Empleado(DNI_Empleado)
);
