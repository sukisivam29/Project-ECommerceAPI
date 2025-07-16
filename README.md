# Project-ECommerceAPI
Ecommerce API based on Java, Spring Boot, Spring Data JPA, H2 Database
# Ecommerce Backend API

A comprehensive Spring Boot backend application for ecommerce functionality with product management features.

## Features

- **Product Management**: CRUD operations for products
- **Search & Filter**: Search products by name, category, price range
- **Pagination**: Support for paginated product listings
- **Stock Management**: Track and update product inventory
- **Category Management**: Organize products by categories
- **Data Validation**: Input validation with proper error handling
- **Sample Data**: Auto-initialization with sample products

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **MySQL Database** (with H2 for development)
- **Maven**
- **Bean Validation**

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+ (or use H2 for development)

### Database Setup

#### Option 1: MySQL (Recommended for Production)
1. Create a MySQL database named `ecommerce_db`
2. Update `application.properties` with your MySQL credentials
3. The application will auto-create tables on startup

#### Option 2: H2 (Development/Testing)
1. Comment out MySQL configuration in `application.properties`
2. Uncomment H2 configuration lines
3. Access H2 console at `http://localhost:8080/h2-console`

### Running the Application

```bash
# Navigate to project directory
cd springdemo

# Run with Maven
./mvnw spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/springdemo-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Endpoints

### Dashboard
- `GET /api/dashboard/welcome` - Welcome message
- `GET /api/dashboard/stats` - Application statistics

### Products

#### Basic Operations
- `GET /api/products` - Get all active products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update existing product
- `DELETE /api/products/{id}` - Delete product (soft delete)

#### Search & Filter
- `GET /api/products/search?name={name}` - Search products by name
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/price-range?minPrice={min}&maxPrice={max}` - Filter by price
- `GET /api/products/in-stock` - Get products in stock

#### Pagination
- `GET /api/products/paginated?page={page}&size={size}&sortBy={field}&sortDir={asc|desc}`
- `GET /api/products/search/paginated?q={searchTerm}&page={page}&size={size}`

#### Categories & Stock
- `GET /api/products/categories` - Get all categories
- `PATCH /api/products/{id}/stock` - Update stock quantity

## Sample API Requests

### Create Product
```json
POST /api/products
{
    "name": "Wireless Headphones",
    "description": "High-quality wireless headphones with noise cancellation",
    "price": 199.99,
    "stockQuantity": 50,
    "category": "Electronics",
    "imageUrl": "https://example.com/headphones.jpg"
}
```

### Update Stock
```json
PATCH /api/products/1/stock
{
    "quantity": 25
}
```

### Search Products
```
GET /api/products/search?name=phone
GET /api/products/category/Electronics
GET /api/products/price-range?minPrice=100&maxPrice=500
```

## Sample Data

The application automatically initializes with sample products including:
- Electronics (iPhone, Samsung Galaxy, MacBook)
- Clothing (Denim Jacket, T-Shirt)
- Books (Spring Boot in Action, Clean Code)
- Home & Garden (Coffee Maker, Plants)
- Sports (Yoga Mat)

## Error Handling

The API includes comprehensive error handling:
- Validation errors return detailed field-specific messages
- 404 errors for non-existent resources
- 500 errors for server issues
- Proper HTTP status codes

## Configuration

Key configuration options in `application.properties`:
- Database connection settings
- JPA/Hibernate configuration
- Server port (default: 8080)
- CORS settings (currently allows all origins)

## Development

### Project Structure
```
src/main/java/com/practice/springdemo/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── exception/      # Exception handling
├── repository/     # Data repositories
└── service/        # Business logic
```

### Adding New Features
1. Create entity classes in `entity/` package
2. Add repository interfaces in `repository/`
3. Implement business logic in `service/`
4. Create REST endpoints in `controller/`
5. Add DTOs for data transfer in `dto/`

## Testing

Use tools like Postman, curl, or any REST client to test the API endpoints.

Example curl commands:
```bash
# Get all products
curl http://localhost:8080/api/products

# Create a product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Product","price":29.99,"stockQuantity":10,"category":"Test"}'

# Search products
curl "http://localhost:8080/api/products/search?name=phone"
```

## Future Enhancements

- User authentication and authorization
- Shopping cart functionality
- Order management
- Payment integration
- Product reviews and ratings
- Image upload functionality
- Advanced search with filters
- Inventory alerts
- Admin dashboard
