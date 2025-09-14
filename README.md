# Food Order Application

A simple food ordering application built with Spring Boot and Thymeleaf.

## Features

- **Menu Display**: View available food items with categories and search functionality
- **Order Management**: Add items to cart and place orders
- **Order History**: View all placed orders
- **Responsive Design**: Modern UI with Bootstrap

## Technology Stack

- **Backend**: Spring Boot 3.5.5
- **Frontend**: Thymeleaf templates with Bootstrap 5
- **Database**: H2 in-memory database
- **Java**: Java 21

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Open your browser and go to `http://localhost:8080`

### Accessing the Database

The application uses H2 in-memory database. You can access the H2 console at:
`http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:foodorderdb`
- **Username**: `sa`
- **Password**: `password`

## Application Structure

- **Entities**: `Food`, `Order`, `OrderItem`
- **Repositories**: Data access layer for each entity
- **Services**: Business logic for food and order management
- **Controllers**: Web controllers for handling HTTP requests
- **Templates**: Thymeleaf templates for the UI

## Sample Data

The application automatically loads sample food items including:
- Appetizers (Caesar Salad, Buffalo Wings, Mozzarella Sticks)
- Main Courses (Grilled Salmon, Beef Burger, Chicken Parmesan, etc.)
- Desserts (Chocolate Cake, Tiramisu, Ice Cream Sundae)
- Beverages (Fresh Orange Juice, Iced Coffee, Coca Cola)

## API Endpoints

- `GET /` - Redirects to menu
- `GET /menu` - Display food menu
- `GET /checkout` - Order checkout page
- `POST /place-order` - Place a new order
- `GET /order-confirmation/{id}` - Order confirmation page
- `GET /orders` - View all orders

## Development

The application is configured for development with:
- H2 console enabled
- SQL logging enabled
- Thymeleaf cache disabled
