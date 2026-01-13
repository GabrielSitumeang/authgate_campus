## Editions

### Community Edition (Free)
For evaluation, learning, and small demos.
❗ This edition does NOT include:
- Refresh Token
- Logout Invalidation
- Advanced Authorization

📩 Contact: your@email.com


# 🚀 AuthGate Campus

**AuthGate Campus** adalah *Starter Kit Autentikasi dan Otorisasi* berbasis **Spring Boot dan JSON Web Token (JWT)**  
yang dirancang untuk mendukung **aplikasi kampus, tugas akhir, dan project backend modern**.

Project ini mengimplementasikan **JWT Access Token**, **Refresh Token**, **Role-Based Access Control**, serta **Permission-Based Authorization** dengan arsitektur yang rapi dan mudah dikembangkan.

---

## 📌 Latar Belakang

Dalam pengembangan aplikasi modern berbasis REST API, sistem autentikasi dan otorisasi menjadi komponen yang sangat penting.  
Banyak aplikasi kampus masih menggunakan pendekatan *stateful session* yang kurang efisien dan sulit diskalakan.

Oleh karena itu, AuthGate Campus dikembangkan sebagai solusi autentikasi **stateless** berbasis JWT yang:
- aman
- fleksibel
- mudah diintegrasikan
- sesuai dengan praktik industri

---

## 🎯 Tujuan Pengembangan

1. Mengimplementasikan sistem autentikasi berbasis JWT.
2. Menerapkan mekanisme **Access Token** dan **Refresh Token**.
3. Mengelola hak akses pengguna berdasarkan **Role** dan **Permission**.
4. Menyediakan API yang terdokumentasi dengan baik menggunakan Swagger.
5. Menjadi *starter project* yang siap digunakan untuk Tugas Akhir atau project lanjutan.

---

## ✨ Fitur Utama

- 🔐 **JWT Authentication**
  - Access Token (short-lived)
  - Refresh Token (long-lived)

- 🔁 **Refresh Token Management**
  - Disimpan di database
  - Diverifikasi dan dapat di-*invalidate*

- 🚪 **Logout Aman**
  - Refresh token dihapus saat logout

- 🧑‍⚖️ **Role-Based Access Control**
  - ADMIN
  - DOSEN
  - MAHASISWA

- 🧩 **Permission-Based Authorization (Advanced)**
  - PROFILE_READ
  - PROFILE_WRITE
  - USER_MANAGE
  - dll

- 📄 **Swagger OpenAPI**
  - Testing API langsung tanpa frontend

- 🗄️ **Database Relasional**
  - PostgreSQL
  - JPA & Hibernate

- ⚙️ **Stateless & Production Ready**

---

## 🛠️ Teknologi yang Digunakan

| Teknologi | Versi |
|---------|------|
| Java | 21 |
| Spring Boot | 3.5.x |
| Spring Security | 6.x |
| JPA / Hibernate | 6.x |
| Database | PostgreSQL |
| API Docs | Swagger OpenAPI |

---

## 🧱 Arsitektur Sistem

AuthGate Campus menggunakan arsitektur **Layered Architecture**:

- **Controller** → menangani request API
- **Service** → business logic
- **Repository** → akses database
- **Security Layer** → JWT Filter & Authorization
- **Entity** → representasi tabel database

Pendekatan ini memudahkan:
- pengujian
- pengembangan lanjutan
- pemeliharaan sistem

---

## 🗄️ Struktur Database

Tabel utama yang digunakan:

- `users`
- `roles`
- `permissions`
- `user_roles`
- `role_permissions`
- `profiles`
- `refresh_tokens`

Diagram ERD dan flow autentikasi tersedia pada folder `docs/`.

---

## 🔄 Alur Autentikasi (JWT Flow)

1. User melakukan **login**
2. Sistem mengembalikan:
   - Access Token
   - Refresh Token
3. Access Token digunakan untuk mengakses API protected
4. Jika Access Token expired:
   - Client memanggil endpoint refresh token
5. Logout:
   - Refresh token dihapus dari database

---

## 🔐 Mekanisme Otorisasi

### Role
Digunakan untuk pengelompokan user:
- ADMIN
- DOSEN
- MAHASISWA

### Permission
Digunakan untuk kontrol akses yang lebih detail:
- PROFILE_READ
- PROFILE_WRITE
- USER_MANAGE

Contoh penggunaan di controller:
```java
@PreAuthorize("hasAuthority('PROFILE_READ')")
@GetMapping("/profile")
public ProfileDto getProfile() {
    return profileService.getProfile();
}
