# Shopping Cart Backend 🛒

A complete e-commerce shopping cart backend built with Spring Boot. Manage products, shopping carts, orders, and user transactions with a robust RESTful API.

## 🎯 What Does It Do?

This shopping cart backend provides all the functionality needed for an e-commerce platform:

- **Product Management**: Create, read, update, and delete products in your catalog
- **Shopping Cart**: Add items to cart, update quantities, remove items, and calculate totals
- **User Management**: Handle user registration, authentication, and profiles
- **Order Processing**: Convert carts to orders, track order status, and manage order history
- **Inventory Control**: Automatically update stock levels when orders are placed
- **Price Calculations**: Handle subtotals, taxes, discounts, and shipping costs
- **Order History**: View past orders and order details

## 👤 Who Is It For?

- E-commerce developers building online stores
- Backend developers learning Spring Boot
- Students creating full-stack shopping applications
- Startups building their first online store
- Teams needing a customizable shopping cart solution

## 🚀 How to Use

### Prerequisites

- Java 24 or higher
- Maven 3.6 or higher
- MySQL database (or use H2 for development)
- API testing tool (Postman, Insomnia, or curl)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd Shopping-Cart-Backend
```

2. Configure the database:

Edit `src/main/resources/application.properties`:

```properties
# For MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/shopping_cart
spring.datasource.username=your_username
spring.datasource.password=your_password

# For H2 (development)
spring.datasource.url=jdbc:h2:mem:shopping_cart
spring.h2.console.enabled=true
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

### API Endpoints

#### Product Endpoints

```http
GET    /api/products              # Get all products
GET    /api/products/{id}         # Get product by ID
POST   /api/products              # Create new product
PUT    /api/products/{id}         # Update product
DELETE /api/products/{id}         # Delete product
GET    /api/products/category/{category}  # Get products by category
```

#### Shopping Cart Endpoints

```http
GET    /api/cart/{userId}         # Get user's cart
POST   /api/cart/add              # Add item to cart
PUT    /api/cart/update           # Update cart item quantity
DELETE /api/cart/remove/{itemId}  # Remove item from cart
DELETE /api/cart/clear/{userId}   # Clear entire cart
GET    /api/cart/total/{userId}   # Get cart total
```

#### Order Endpoints

```http
POST   /api/orders/checkout       # Create order from cart
GET    /api/orders/{orderId}      # Get order details
GET    /api/orders/user/{userId}  # Get user's order history
PUT    /api/orders/{orderId}/status  # Update order status
GET    /api/orders/{orderId}/items   # Get order items
```

#### User Endpoints

```http
POST   /api/users/register        # Register new user
POST   /api/users/login           # User login
GET    /api/users/{userId}        # Get user profile
PUT    /api/users/{userId}        # Update user profile
```

### Example Usage

#### 1. Add Product to Cart

```bash
curl -X POST http://localhost:8080/api/cart/add \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "productId": 5,
    "quantity": 2
  }'
```

**Response:**
```json
{
  "id": 1,
  "userId": 1,
  "items": [
    {
      "productId": 5,
      "productName": "Wireless Mouse",
      "quantity": 2,
      "price": 29.99,
      "subtotal": 59.98
    }
  ],
  "total": 59.98
}
```

#### 2. View Cart

```bash
curl http://localhost:8080/api/cart/1
```

#### 3. Update Cart Item Quantity

```bash
curl -X PUT http://localhost:8080/api/cart/update \
  -H "Content-Type: application/json" \
  -d '{
    "cartItemId": 1,
    "quantity": 3
  }'
```

#### 4. Checkout (Create Order)

```bash
curl -X POST http://localhost:8080/api/orders/checkout \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "shippingAddress": "123 Main St, City, State 12345",
    "paymentMethod": "credit_card"
  }'
```

**Response:**
```json
{
  "orderId": 101,
  "userId": 1,
  "orderDate": "2024-03-06T10:30:00",
  "status": "PENDING",
  "items": [...],
  "subtotal": 89.97,
  "tax": 7.20,
  "shipping": 5.99,
  "total": 103.16
}
```

#### 5. View Order History

```bash
curl http://localhost:8080/api/orders/user/1
```

## 🛠️ Technical Stack

- **Spring Boot 3.5.3**: Modern Java framework
- **Spring Web**: RESTful web services
- **Spring Data JPA**: Database access and ORM
- **Spring Validation**: Input validation
- **Lombok**: Reduce boilerplate code
- **MySQL**: Production database
- **H2**: Development/testing database
- **Maven**: Build and dependency management
- **Java 24**: Programming language

## 📁 Project Architecture

```
Shopping-Cart-Backend/
├── src/
│   ├── main/
│   │   ├── java/com/app/shoppingcart/
│   │   │   ├── model/
│   │   │   │   ├── Product.java
│   │   │   │   ├── Cart.java
│   │   │   │   ├── CartItem.java
│   │   │   │   ├── Order.java
│   │   │   │   ├── OrderItem.java
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   ├── ProductRepository.java
│   │   │   │   ├── CartRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ProductService.java
│   │   │   │   ├── CartService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   └── UserService.java
│   │   │   ├── controller/
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── CartController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   └── UserController.java
│   │   │   ├── dto/
│   │   │   │   └── (Data Transfer Objects)
│   │   │   └── exception/
│   │   │       └── (Custom Exceptions)
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (sample data)
│   └── test/
└── pom.xml
```

## 📊 Data Models

### Product
- id, name, description, price, quantity, category, imageUrl, createdAt

### Cart
- id, userId, items (List<CartItem>), total, createdAt, updatedAt

### CartItem
- id, cartId, productId, quantity, price, subtotal

### Order
- id, userId, orderDate, status, items, subtotal, tax, shipping, total, shippingAddress

### OrderItem
- id, orderId, productId, quantity, price, subtotal

### User
- id, username, email, password, firstName, lastName, address, phone

## 🔧 Configuration

### Database Setup

**MySQL:**
```sql
CREATE DATABASE shopping_cart;
USE shopping_cart;
```

**Application Properties:**
```properties
# Server
server.port=8080

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/shopping_cart
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Validation
spring.jpa.properties.hibernate.validator.apply_to_ddl=true
```

## 🧪 Testing

```bash
mvn test
```

Includes:
- Unit tests for services
- Integration tests for controllers
- Repository tests
- End-to-end shopping flow tests

## 🔐 Security Features

- Password encryption (BCrypt)
- Input validation
- SQL injection prevention
- CORS configuration
- Session management

## 📈 Business Logic

### Cart Management
1. User adds product to cart
2. System checks product availability
3. Cart item created or quantity updated
4. Cart total recalculated
5. Inventory reserved (optional)

### Order Processing
1. User initiates checkout
2. System validates cart and inventory
3. Order created from cart items
4. Inventory decremented
5. Cart cleared
6. Order confirmation sent

### Inventory Management
- Real-time stock tracking
- Automatic updates on order placement
- Low stock alerts
- Out-of-stock prevention

## 🚀 Deployment

### Build for Production

```bash
mvn clean package
java -jar target/shopping-cart-0.0.1-SNAPSHOT.jar
```

### Docker Deployment

```dockerfile
FROM openjdk:24-jdk-slim
COPY target/shopping-cart-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📈 Future Enhancements

- [ ] Payment gateway integration (Stripe, PayPal)
- [ ] Coupon and discount codes
- [ ] Wishlist functionality
- [ ] Product reviews and ratings
- [ ] Email notifications
- [ ] Admin dashboard
- [ ] Analytics and reporting
- [ ] Multi-currency support
- [ ] Shipping provider integration
- [ ] Advanced search and filters

## 🤝 Contributing

Contributions welcome! Great project for learning e-commerce backend development.

## 📄 License

This project is open source and available for educational purposes.
