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
```sh
git clone https://github.com/JNikhilSakthi/spring-boot-redis-dynamic-cache-switching.git
cd spring-boot-redis-dynamic-cache-switching
