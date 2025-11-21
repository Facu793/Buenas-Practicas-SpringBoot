# 🏍️ Backend Spring Boot - CRUD con Validaciones y Arquitectura en Capas
## 📋 Descripción

Proyecto educativo que implementa una API REST completa siguiendo buenas prácticas de arquitectura en capas con Spring Boot. Incluye CRUD completo, validaciones, manejo de errores global y relaciones entre entidades.

## 🛠️ Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos relacional
- **Bean Validation** - Validaciones de datos
- **Maven** - Gestión de dependencias
- - **Postmann** - Testing de endpoint

## 🏗️ Arquitectura en Capas
El proyecto sigue el patrón **Repository → Service → Controller** para mantener una arquitectura escalable y ordenada:

```
Controller (REST) → Service (Lógica de Negocio) → Repository (Acceso a Datos) → Base de Datos
```

### Estructura del Proyecto
```
backend/
├── model/          # Entidades JPA (Moto, Cliente)
├── dto/            # Data Transfer Objects (Request/Response)
├── repository/     # Interfaces de acceso a datos
├── service/        # Lógica de negocio y validaciones
├── controller/     # Endpoints REST
└── exception/      # Manejo global de errores
```

## ✅ Ejercicios Completados

### 🔹 Ejercicio 1 - API REST Completa
- ✅ Entidad → DTO → Repository → Service → Controller
- ✅ CRUD completo (Create, Read, Update, Delete)
- ✅ Validaciones Bean Validation
- ✅ Manejo de errores custom con `@ControllerAdvice`

### 🔹 Ejercicio 2 - Relaciones entre Entidades
- ✅ Relación 1:N: Cliente ↔ Moto (Un cliente puede tener muchas motos)
- ✅ Relación bidireccional con métodos helper

### 🔹 Ejercicio 3 - Lógica de Negocio y Casos Límite
- ✅ Validación de stock (no permitir stock <= 0)
- ✅ Cálculo de precio total (precio base + IVA 21% + comisión 5%)
- ✅ Estados válidos (DISPONIBLE, VENDIDA, RESERVADA, MANTENIMIENTO)
- ✅ Validación de límite de motos por cliente (máximo 3)
- ✅ Cálculo de inversión total del cliente
- ✅ Validaciones de transiciones de estado

### 🔹 Ejercicio 4 - Patrones y Arquitectura
- ✅ Inyección de dependencias por constructor (buena práctica)
- ✅ Separación de responsabilidades por capas
- ✅ Uso correcto de anotaciones Spring (`@Service`, `@Repository`, `@RestController`)

### 🔹 Ejercicio 5 - Testeo de endpoint
- ✅ Testeo mediante App Postmann para validar Comportamiento de peticiones HTTP

## 🎯 Objetivos de Aprendizaje

- **Arquitectura en capas**: Separación clara de responsabilidades
- **Inyección de dependencias**: Uso correcto de Spring IoC
- **Validaciones**: Bean Validation y validaciones de negocio
- **Manejo de errores**: Excepciones custom y respuestas estructuradas
- **Relaciones JPA**: Implementación de relaciones 1:N y N:M
- **DTOs**: Separación entre modelo de dominio y modelo de API
- **Transacciones**: Uso de `@Transactional` para operaciones atómicas
- **Buenas prácticas**: Código limpio, mantenible y escalable

## 🚀 Cómo Ejecutar

1. **Configurar base de datos** en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/CrudValidaciones
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

2. **Ejecutar la aplicación**:
```bash
cd backend/backend
mvn spring-boot:run
```

3. **La API estará disponible en**: `http://localhost:8080/api`

## 📡 Endpoints Principales

### Motos
- `GET /api/motos` - Listar todas las motos
- `GET /api/motos/{id}` - Obtener moto por ID
- `POST /api/motos` - Crear nueva moto
- `PUT /api/motos/{id}` - Actualizar moto
- `DELETE /api/motos/{id}` - Eliminar moto

### Clientes
- `GET /api/clientes` - Listar todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID (incluye sus motos)
- `POST /api/clientes` - Crear nuevo cliente
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

## 🔒 Validaciones Implementadas

- **Bean Validation**: Campos obligatorios, tamaños, formatos
- **Validaciones de negocio**: Stock, precios, estados, límites
- **Manejo de errores**: Respuestas estructuradas con códigos HTTP apropiados

## 📚 Buenas Prácticas Aplicadas

- ✅ Inyección de dependencias por constructor
- ✅ Separación de responsabilidades (SRP)
- ✅ Uso de DTOs para desacoplar capas
- ✅ Validaciones en múltiples niveles
- ✅ Manejo centralizado de excepciones
- ✅ Transacciones para operaciones atómicas
- ✅ Código sin Lombok (getters/setters manuales)
- ✅ Enums para valores fijos (type safety)

---

**Desarrollado como proyecto educativo para aprender arquitectura Spring Boot escalable y ordenada** 🚀

