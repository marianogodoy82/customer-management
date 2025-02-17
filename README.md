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
├── Dockerfile               # Configuración de imagen Docker para la aplicación
├── application.yml          # Configuración de Spring Boot
├── application-docker.yml   # Configuración de Spring Boot para entorno Docker
├── README.md                # Documentación
```

## ⚙️ Configuración y Ejecución con Docker Compose
### 🔹 **1. Clonar el Repositorio**
```bash
git clone https://github.com/tu-repo/customer-management.git
cd customer-management
```

### 🔹 **2. Construir la Imagen Docker de la Aplicación**
```bash
docker build -t customer-management-app .
```

### 🔹 **3. Iniciar la Aplicación con Docker Compose**
Ejecuta el siguiente comando para levantar todos los servicios:
```bash
docker-compose up -d --build
```
Este comando iniciará:
- **PostgreSQL** (`db`)
- **Kafka y Zookeeper** (`kafka` y `zookeeper`)
- **La aplicación Spring Boot** (`app`)

### 🔹 **4. Verificar los Contenedores en Ejecución**
```bash
docker ps
```
Deberías ver algo similar a:
```
CONTAINER ID   IMAGE                                STATUS          PORTS                    
12345678       customer-management-app              Up 10 minutes  0.0.0.0:8080->8080/tcp    
87654321       postgres:latest                      Up 10 minutes  0.0.0.0:5432->5432/tcp    
65432109       confluentinc/cp-kafka:latest         Up 10 minutes  0.0.0.0:9092->9092/tcp    
09876543       confluentinc/cp-zookeeper:latest     Up 10 minutes  0.0.0.0:2181->2181/tcp  
3cae49b8       mongo:6.0                            Up 10 minutes  0.0.0.0:2181->2181/tcp    
```

### 🔹 **5. Ver Logs de la Aplicación**
Para ver los logs de la aplicación Spring Boot:
```bash
docker logs customer-management-app -f
```
Para ver los logs de Kafka:
```bash
docker logs kafka -f
```

### 🔹 **6. Acceder a la API**
Una vez que todos los contenedores están corriendo, accede a la API en:
```
http://localhost:8080/swagger-ui.html
```

### 🔹 **7. Detener la Aplicación**
Para detener todos los contenedores:
```bash
docker-compose down
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

