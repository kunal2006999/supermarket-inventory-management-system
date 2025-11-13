# 🛒 Supermarket Inventory Management System

A complete backend solution for managing supermarket operations — including billing, product management, stock tracking, and automated reporting — built with **Spring Boot**, **Hibernate (JPA)**, and **MySQL**.

---

## 🚀 Features

### 🧾 Billing
- **generateBill** – Creates a new customer bill with purchased items.  
- **getBillById** – Retrieves a specific bill by its unique ID.  
- **getAllBills** – Lists all generated bills in the system.

### 📦 Product Management
- **addProduct** – Adds a new product to the inventory.  
- **updateProduct** – Updates existing product details.  
- **deleteProduct** – Removes a product from the catalog.  
- **getProduct / getAllProduct** – Retrieves one or all products.  
- **searchProducts** – Searches products by name or category.  
- **getLowStockProduct** – Fetches products below the minimum stock threshold.

### 📊 Reports & Analytics
- **getDailyReport(LocalDate date)** – Generates a daily summary of sales, including:
  - Total revenue  
  - Total transactions  
  - Total items sold  
  - Average bill amount  
  - Highest bill amount of the day  
- **getMonthlyReport(LocalDate month)** – Aggregates monthly metrics to visualize store performance.

### 📈 Stock Management
- **addStock** – Increases product stock quantity.  
- **removeStock** – Deducts sold or damaged stock.  
- **getStockHistory** – Tracks changes in stock levels for audit and analysis.

### 👥 User Management
- **signupUser** – Registers new users into the system.  
- **loginUser** – Authenticates users (no external authentication service used).

---

## ⚙️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend Framework | Spring Boot |
| ORM | Hibernate / JPA |
| Database | MySQL |
| Build Tool | Maven |
| Language | Java 17+ |
| JSON Serialization | Jackson |
| IDE | IntelliJ IDEA / VS Code / Eclipse |

---

## 🧠 Key Highlights
- **Clean layered architecture (Controller → Service → Repository)**  
- **Entity relationships:** `BillEntity`, `ProductEntity`, `SaleItemEntity`, `StockEntity`, `UserEntity`  
- **Automatic daily/monthly reporting** using real-time sales data  
- **Error handling & data validation** with custom exceptions  

---

## 📊 Future Enhancements
- Add **JWT-based authentication** and role-based access control (Admin / Staff)  
- Integrate **RESTful frontend dashboard** for visual analytics  
- Export reports in **PDF / Excel** formats  
- Implement **email notifications** for low-stock alerts  

---

## 🧰 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/supermarket-inventory.git
