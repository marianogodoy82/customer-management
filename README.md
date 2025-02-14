# Gestor de Clientes - Event-Driven con Kafka y MongoDB

## 📌 Descripción
Este proyecto implementa un **Gestor de Clientes** basado en una arquitectura **Event-Driven** utilizando **Spring Boot, Apache Kafka y MongoDB**. La aplicación permite **crear clientes**, calcular **métricas en tiempo real** (promedio de edad, desviación estándar) y estimar **la esperanza de vida** de los clientes.

## 🚀 Tecnologías Utilizadas
- **Java 21**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Data MongoDB, Spring Kafka)
- **Apache Kafka** (para mensajería de eventos)
- **MongoDB** (para almacenamiento de métricas)
- **PostgreSQL** (para almacenamiento de clientes)
- **Docker y Docker Compose** (para entornos de despliegue)
- **Swagger/OpenAPI** (para documentación de API)

## 📂 Estructura del Proyecto
```
├── src/main/java/com/empresa/gestionclientes/
│   ├── controller/          # Controladores REST
│   ├── event/               # Eventos Kafka
│   ├── model/               # Modelos de Cliente y Métricas
│   ├── repository/          # Repositorios MongoDB y PostgreSQL
│   ├── service/             # Lógica de negocio
│   ├── config/              # Configuración de Kafka y MongoDB
│   ├── security/            # Seguridad con JWT (si se usa)
│   ├── CustomerManagementApplication.java # Clase principal
│
├── docker-compose.yml       # Configuración de Docker para Kafka y MongoDB
├── application.yml          # Configuración de Spring Boot
├── README.md                # Documentación
```

## ⚙️ Configuración y Ejecución
### 🔹 **1. Clonar el Repositorio**
```bash
git clone https://github.com/marianogodoy82/customer-management.git
cd customer-management
```

### 🔹 **2. Iniciar Dependencias con Docker**
Ejecuta Kafka, Zookeeper y MongoDB con:
```bash
docker-compose up -d
```

### 🔹 **3. Configurar la Base de Datos (PostgreSQL y MongoDB)**
- **PostgreSQL:** Asegúrate de tener una base de datos `clientes_db` creada.
- **MongoDB:** La colección `clienteMetricas` se creará automáticamente.

### 🔹 **4. Ejecutar la Aplicación**
```bash
mvn spring-boot:run
```

## 📌 Endpoints Disponibles
### **🔹 Gestión de Clientes**
| Método | Endpoint                 | Descripción |
|--------|--------------------------|-------------|
| `POST` | `/customer`               | Crea un nuevo cliente |
| `GET`  | `/customer`               | Lista todos los clientes |
| `GET`  | `/customer/{id}`          | Obtiene un cliente por ID |

### **🔹 Estadísticas y Métricas**
| Método | Endpoint                         | Descripción |
|--------|----------------------------------|-------------|
| `GET`  | `/customer/statistics`           | Obtiene las métricas de clientes |
| `GET`  | `/customer/{id}/life-expectancy` | Calcula la esperanza de vida de un cliente |

### **🔹 Autenticación (Si aplica JWT)**
| Método | Endpoint       | Descripción             |
|--------|----------------|-------------------------|
| `POST` | `/auth/login`  | Obtiene un token JWT    |
| `POST` | `/auth/signup` | Resgistra nuevo usuario |

## 🛠️ Desarrollo y Contribución
Si deseas contribuir:
1. **Haz un Fork** del repositorio.
2. **Crea una rama** con tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. **Realiza un Pull Request** para revisión.

## 📜 Licencia
Este proyecto está bajo la licencia **MIT**.

