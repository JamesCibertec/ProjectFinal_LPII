CREATE DATABASE IF NOT EXISTS dbfredyqueen2025;
USE dbfredyqueen2025;

-- Tabla de Roles
CREATE TABLE rol (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  estado BOOLEAN DEFAULT TRUE
);

INSERT INTO rol (nombre, estado) VALUES
('Administrador', TRUE),
('Cliente', TRUE),
('Recepcion', TRUE);


-- Tabla de Personas
CREATE TABLE persona (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  dni VARCHAR(10) NOT NULL UNIQUE,
  telefono VARCHAR(20),
  direccion VARCHAR(150),
  estado BOOLEAN DEFAULT TRUE
);

INSERT INTO persona (nombres, apellidos, dni, telefono, direccion, estado) VALUES
('Carlos', 'Ruiz', '12345678', '987654321', 'Av. Perú 123', TRUE),
('Ana', 'Gonzales', '87654321', '987111222', 'Jr. Lima 456', TRUE),
('Luis', 'Salas', '11223344', '912345678', 'Calle Arequipa 789', TRUE),
('Carlos', 'Ruiz Paredes', '12345677', '987654321', 'Av. Siempre Viva 123', TRUE),
('María', 'Torres Delgado', '23456789', '987123456', 'Jr. Los Pinos 456', TRUE),
('Luis', 'García Ramírez', '34567890', '986543210', 'Calle Falsa 789', TRUE),
('Ana', 'Fernández Soto', '45678901', '985432109', 'Av. Principal 321', TRUE),
('Jorge', 'Vega Morales', '56789012', '984321098', 'Jr. Las Flores 102', TRUE),
('Lucía', 'Reyes Quispe', '67890123', '983210987', 'Calle Los Sauces 232', TRUE),
('Pedro', 'Cruz Mendoza', '78901234', '982109876', 'Av. Lima 505', TRUE),
('Carla', 'López Ríos', '89012345', '981098765', 'Jr. Arequipa 202', TRUE),
('Diego', 'Núñez Castro', '90123456', '980987654', 'Calle Real 808', TRUE),
('Sandra', 'Mendoza Pérez', '01234567', '979876543', 'Av. La Paz 121', TRUE);

-- Tabla de Usuarios
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY, -- igual al ID de la persona
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    id_rol BIGINT NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_usuario_persona FOREIGN KEY (id) REFERENCES persona(id),
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id)
);

INSERT INTO usuario (id, nombre_usuario, contrasena, id_rol, estado) VALUES
(1, 'carlos_admin', 'admin123', 1, true),   -- Admin
(2, 'lucia_cliente', 'cliente123', 2, true),-- Cliente
(3, 'pedro_op', 'operador123', 3, true);    -- Operador


-- Tabla de Clientes
CREATE TABLE cliente (
  id BIGINT PRIMARY KEY,  -- el mismo ID que en persona
  CONSTRAINT fk_cliente_persona FOREIGN KEY (id) REFERENCES persona(id)
);

INSERT INTO cliente (id) VALUES 
(1), 
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10);

-- Tabla de Tipos de Pago
CREATE TABLE tipo_pago (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  descripcion VARCHAR(50),
  estado BOOLEAN DEFAULT TRUE
);

INSERT INTO tipo_pago (descripcion, estado) VALUES
('Efectivo', TRUE),
('Yape', TRUE),
('Plin', TRUE);



-- Tabla de Canchas
CREATE TABLE cancha (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,
  estado BOOLEAN DEFAULT TRUE
);

INSERT INTO cancha (nombre, descripcion, estado) VALUES
('Cancha 1', 'Fútbol', TRUE),
('Cancha 2', 'Fútbol', TRUE),
('Cancha 3', 'Fútbol', TRUE),
('Cancha 4', 'Fútbol', TRUE),
('Cancha 5', 'Fútbol', TRUE),
('Cancha 6', 'Fútbol', TRUE),
('Cancha 7', 'Fútbol', TRUE),
('Cancha 8', 'Vóley', TRUE),
('Cancha 9', 'Vóley', TRUE),
('Cancha 10', 'Vóley', TRUE);


-- Tabla de Reservas
CREATE TABLE reservas (
  id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_cliente BIGINT,
  id_cancha BIGINT,
  fecha DATE  NOT NULL,
  hora_inicio TIME  NOT NULL,
  hora_fin TIME  NOT NULL,
  estado VARCHAR(20) DEFAULT 'Reservado', -- Puede ser Reservado, Cancelado, Alquilado
  tipo_pago_id BIGINT,   -- Opcional hasta que se confirme
  monto DECIMAL(8,2),      -- Opcional hasta que se confirme
  FOREIGN KEY (id_cliente) REFERENCES cliente(id),
  FOREIGN KEY (id_cancha) REFERENCES cancha(id),
  FOREIGN KEY (tipo_pago_id) REFERENCES tipo_pago(id)
);

INSERT INTO reservas (id_cliente, id_cancha, fecha, hora_inicio, hora_fin, estado, tipo_pago_id, monto) VALUES
(1, 1, '2025-07-10', '18:00:00', '19:00:00', 'Reservado', NULL, NULL),
(1, 2, '2025-07-11', '19:00:00', '20:00:00', 'Alquilado', 2, 50.00);

INSERT INTO reservas (id_cliente, id_cancha, fecha, hora_inicio, hora_fin, estado, tipo_pago_id, monto) VALUES
(1, 1, '2025-07-01', '09:00:00', '10:00:00', 'Reservado', NULL, NULL),
(2, 2, '2025-07-01', '10:00:00', '11:00:00', 'Alquilado', 1, 50.00),
(4, 4, '2025-07-02', '08:00:00', '09:00:00', 'Reservado', NULL, NULL),
(5, 5, '2025-07-02', '09:00:00', '10:00:00', 'Alquilado', 2, 60.00),

(1, 2, '2025-07-03', '10:00:00', '11:00:00', 'Reservado', NULL, NULL),
(2, 3, '2025-07-03', '11:00:00', '12:00:00', 'Reservado', NULL, NULL),
(3, 4, '2025-07-03', '12:00:00', '13:00:00', 'Alquilado', 3, 55.00),
(4, 5, '2025-07-04', '08:00:00', '09:00:00', 'Reservado', NULL, NULL),

(1, 3, '2025-07-05', '10:00:00', '11:00:00', 'Alquilado', 1, 50.00),
(2, 4, '2025-07-05', '11:00:00', '12:00:00', 'Reservado', NULL, NULL),
(3, 5, '2025-07-06', '12:00:00', '13:00:00', 'Reservado', NULL, NULL),
(4, 1, '2025-07-06', '13:00:00', '14:00:00', 'Alquilado', 2, 65.00),
(5, 2, '2025-07-07', '08:00:00', '09:00:00', 'Reservado', NULL, NULL),

(2, 5, '2025-07-08', '10:00:00', '11:00:00', 'Alquilado', 3, 70.00),
(3, 1, '2025-07-08', '11:00:00', '12:00:00', 'Reservado', NULL, NULL),
(4, 2, '2025-07-09', '12:00:00', '13:00:00', 'Reservado', NULL, NULL),
(5, 3, '2025-07-09', '13:00:00', '14:00:00', 'Alquilado', 1, 50.00);
