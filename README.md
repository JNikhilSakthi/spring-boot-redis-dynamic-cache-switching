# 🚀 Spring Boot Redis Dynamic Cache Switching  
> **A high-performance caching solution that dynamically switches between Redis and Database in real-time!**

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)](https://spring.io/projects/spring-boot)
[![Redis](https://img.shields.io/badge/Redis-7.0-red)](https://redis.io/)
[![License](https://img.shields.io/badge/License-MIT-brightgreen)](LICENSE)
[![Author](https://img.shields.io/badge/Author-JNikhilSakthi-blue)](https://github.com/JNikhilSakthi)

## 📖 Overview  
This project provides **dynamic caching** in **Spring Boot**, ensuring **seamless failover** between **Redis and Database** while handling failures gracefully.

🔹 **Uses Redis as primary cache** (for speed) 🚀  
🔹 **Falls back to Database** if Redis is down ⚡  
🔹 **Automatically switches back** to Redis when available 🔄  
🔹 **Prevents Redis connection timeouts & failures** ❌  
🔹 **Ensures stable performance** with **optimized Lettuce pooling**  

---

## 🛠️ Tech Stack  
- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **Redis (Lettuce Client)**  
- **MySQL (or any database of choice)**  
- **Docker (for Redis testing)**  

---

## 🚀 Features  
✅ **Failsafe Caching** – Application **never crashes** due to Redis failures  
✅ **Dynamic Failover** – Seamless switching between Redis and Database  
✅ **Fast Cache Recovery** – Redis cache automatically resumes when available  
✅ **Optimized Connection Handling** – Prevents excessive reconnection attempts  
✅ **Graceful Error Handling** – Handles `SocketException`, `RedisConnectionFailureException`, etc.  

---

## 🔧 Installation & Setup  

### **1️⃣ Clone the Repository**
    
    git clone https://github.com/JNikhilSakthi/spring-boot-redis-dynamic-cache-switching.git
    cd spring-boot-redis-dynamic-cache-switching

##  🔧 Configuration  
### 2️⃣ Configure `application.properties`  
Modify the `src/main/resources/application.properties` file with your **MySQL database credentials** and Redis settings:  

    
    # Server Config
    server.port=9090
    
    # MySQL Database Config
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database?useSSL=false&serverTimezone=UTC
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    
    # Redis Configuration
    spring.redis.host=localhost
    spring.redis.port=6379
    spring.redis.timeout=5000
    spring.redis.lettuce.pool.max-active=10
    spring.redis.lettuce.pool.max-idle=5
    spring.redis.lettuce.pool.min-idle=2
    spring.redis.lettuce.pool.max-wait=2000

### 3️⃣ Build & Run the Application 

    
    mvn clean install 
    mvn spring-boot:run
    

### 🔥 How It Works

1. **Check Redis First**: The system checks Redis before fetching from the database.
2. **If Cache Hit**: Data is served from Redis (fast, ~3ms).
3. **If Redis is Down**: Data is fetched from the database, and Redis is skipped automatically.
4. **When Redis is Back**: The system detects Redis availability and resumes caching.

## 🤝 Contributing

Want to contribute? Follow these steps:

#### 1️⃣ Fork the repository
#### 2️⃣ Create a new branch 
    git checkout -b feature-branch
#### 3️⃣ Commit your changes 
    git commit -m "Added new feature"
#### 4️⃣ Push to your branch 
    git push origin feature-branch
#### 5️⃣ Open a Pull Request 🎉




# 📜 License

This project is licensed under the **MIT License** – use it freely!

## 📬 Connect with Me

Let's connect and collaborate! 🚀

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://www.linkedin.com/in/jsakthinikhil/)  
[![Gmail](https://img.shields.io/badge/Gmail-Contact-red)](mailto:jsakthinikhil@gmail.com)  
[![Hotmail](https://img.shields.io/badge/Hotmail-Contact-blue)](mailto:jsakthinikhil@hotmail.com)  

[![License](https://img.shields.io/badge/License-MIT-brightgreen)](LICENSE) [![Author](https://img.shields.io/badge/Author-JNikhilSakthi-blue)](https://github.com/JNikhilSakthi)



⭐ **If you found this project helpful, give it a star!** ⭐🔥

