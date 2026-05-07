# 💊 İlaç Takip Sistemi | Medicine Tracking System

### [TR] Proje Hakkında
Bu proje, kullanıcıların ilaç kullanımlarını düzenli bir şekilde takip etmelerini, stok miktarlarını yönetmelerini ve kullanım saatlerini dijital ortamda saklamalarını sağlayan **Spring Boot** tabanlı bir **RESTful** web uygulamasıdır.

### [EN] About the Project
This project is a **Spring Boot** based **RESTful** web application that allows users to systematically track their medication usage, manage stock levels, and store dosage schedules in a digital environment.

---

## 🚀 Özellikler | Features

| TR | EN |
| :--- | :--- |
| **🔐 Kullanıcı ve Rol Yönetimi:** RBAC tabanlı yetkilendirme. | **🔐 User & Role Management:** RBAC-based authorization. |
| **🔍 Barkod Entegrasyonu:** Hızlı kayıt ve sorgulama. | **🔍 Barcode Integration:** Fast registration and querying. |
| **📂 Kategorizasyon:** Türlerine göre sınıflandırma. | **📂 Categorization:** Classification by medication types. |
| **💊 İlaç Envanteri:** Doz ve hatırlatıcı yönetimi. | **💊 Inventory Management:** Dose and reminder management. |
| **📦 Stok Takibi:** Anlık envanter yönetimi. | **📦 Stock Tracking:** Real-time inventory management. |
| **🔄 Tam CRUD Desteği:** Kayıt yönetimi. | **🔄 Full CRUD Support:** Record management. |

---

## 🛠️ Teknolojiler | Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Database:** MySQL
* **Security:** Role-Based Access Control (RBAC) - Basic Auth
* **Build Tool:** Maven
* **Architecture:** Layered Architecture (Katmanlı Mimari)

---

## 📊 Veritabanı Tasarımı | Database Design (EER Diagram)

Projenin veritabanı mimarisi ve tablolar arası ilişkileri aşağıdaki diyagramda gösterilmiştir:
*The database architecture and table relationships are shown in the diagram below:*

![EER Diagram](eer_diagram.png)

---

## 🧪 API Testleri | API Testing (Postman)

Uygulamanın tüm fonksiyonları Postman üzerinden test edilmiş ve uç noktalar (endpoints) doğrulanmıştır. Testleri gerçekleştirmek için kök dizindeki `ilac-takip-sistemi.postman_collection.json` dosyasını kullanabilirsiniz.
*All application functions have been tested via Postman and endpoints are verified. You can use the collection file in the root directory.*

### 🔑 Temel Endpoint Listesi | Key Endpoints

| Metot | Endpoint | Yetki (Auth) | Açıklama (TR) | Description (EN) |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/users/register` | Public | Yeni kullanıcı kaydı. | New user registration. |
| **POST** | `/api/users/login` | Public | Giriş ve kimlik doğrulama. | Login and authentication. |
| **GET** | `/api/user/medicines/list` | User | İlaçlarımı listele. | List my medicines. |
| **POST** | `/api/user/medicines` | User | Yeni ilaç ekle. | Add new medicine. |
| **GET** | `/api/admin/medicines/list` | Admin | Tüm ilaçları gör. | See all medicines. |
| **PUT** | `/api/admin/medicines/{id}` | Admin | Tam güncelleme. | Full update. |
| **PATCH** | `/api/admin/medicines/{id}` | Admin | Kısmi güncelleme. | Partial update. |
| **DELETE** | `/api/user/medicines/{id}` | User | Kaydı sil. | Delete record. |
| **GET** | `/actuator/health` | User/Admin | Sistem sağlığı. | System health status. |

> **Note:** Testler sırasında `base_url` değişkeninin `http://localhost:8070` olduğundan emin olun.
> **Not:** Testler sırasında `base_url` değişkeninin `http://localhost:8070` olduğundan ve isteklerde **Basic Auth** bilgilerinin tanımlı olduğundan emin olun.
>
> ## Geliştiriciler | Contributors

* *Şevval Çarıkcı* - [GitHub](https://github.com/sevval-carikci)
* *Berivan Karaman* - [GitHub](https://github.com/berivankaraman42-bilp)

