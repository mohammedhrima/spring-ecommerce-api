# spring-ecommerce-api 🛒

A **Spring Boot** e-commerce REST API. The current scope is the **product catalog** — full CRUD
for products with categories — backed by an embedded H2 database, with **Swagger UI** docs and a
small **built-in test page**. (Cart, orders and users are planned next.)

## Tech stack

Java 21 · Spring Boot 3.5 · Spring Data JPA · H2 (file) · springdoc-openapi (Swagger UI) · Lombok · Maven

## Features

- Product CRUD: create, list, get, update, delete (`/api/products`)
- Filter products by `category`, `brand` or `name` (query params)
- Categories are **created automatically** when a product references a new one
- Embedded **H2** database (file-based) with the H2 console enabled
- Interactive API docs via **Swagger UI**
- A built-in static **test UI** to exercise the API without any extra tooling

## Getting started

```sh
./mvnw spring-boot:run
```

Then open:

| URL | What |
|-----|------|
| http://localhost:8080/ | Test UI (add / list / delete products) |
| http://localhost:8080/swagger-ui.html | Swagger UI |
| http://localhost:8080/h2-console | H2 database console |

H2 console connection: JDBC URL `jdbc:h2:file:./data/dream-shop`, user `admin`, password `admin`.

## API

Base path: `/api/products`

| Method | Route | Description |
|--------|-------|-------------|
| GET | `/api/products` | List products (optional `?category=`, `?brand=`, `?name=`) |
| GET | `/api/products/{id}` | Get one product (404 if missing) |
| POST | `/api/products` | Create a product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

Create payload:

```json
{ "name": "Pro Cue", "brand": "Aramith", "price": 80.00, "inventory": 10, "description": "Tournament cue", "category": "Cues" }
```

## Project structure

```
src/main/java/com/app/shopping/
├── ShoppingApplication.java
├── controller/ProductController.java
├── dto/ProductRequest.java
├── model/{Product,Category,Image}.java
├── repository/{ProductRepository,CategoryRepository}.java
├── service/product/{IProductService,ProductService}.java
└── exceptions/ProductNotFoundException.java
src/main/resources/
├── application.properties
└── static/index.html        # test UI
```
