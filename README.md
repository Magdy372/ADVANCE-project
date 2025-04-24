# ADVANCE Project

## Overview
 a Spring Boot e-commerce application for jewelry and precious metals. The application provides a complete shopping experience with user authentication, product browsing, wishlist management, and order processing.

## Features
- User authentication and authorization
- Admin dashboard for product and user management
- Product catalog with search and filtering
- Shopping cart functionality
- Wishlist management
- Order processing and tracking
- Responsive design for mobile and desktop

## Technology Stack
- **Backend**: Java with Spring Boot
- **Frontend**: Bootstrap, Thymeleaf
- **Database**: MySQL
- **Containerization**: Docker


## Project Structure
The project follows a standard Spring Boot application structure:
- src/main/java/com/adv/adv/ - Java source files
  - controller/ - MVC controllers
  - model/ - Entity classes
  - repository/ - Data access interfaces
  - service/ - Business logic
  - validation/ - Input validation
- src/main/resources/ - Configuration and static resources
  - static/ - CSS, JavaScript, and images
  - templates/ - Thymeleaf templates
  - application.properties - Application configuration
## Screenshots

### Homepage
![Homepage](adv\screenshots\home.jpeg)  
*Showcases new arrivals and featured products with a clean, modern design.*

### Login Page
![Login Page](adv\screenshots/login.jpeg)  
*User authentication page with email and password fields.*

### Product Catalog
![Product Catalog](screenshots/shop.jpeg)
*Product browsing with filters for category, color, and material.*


### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/ADVANCE-project.git
   cd ADVANCE-project
   ```
2. **Update the database connection details in `application.properties`**:
   - Set the database URL: `spring.datasource.url=jdbc:mysql://localhost:3306/your_database`
   - Configure username: `spring.datasource.username=your_username`
   - Configure password: `spring.datasource.password=your_password`
3. **Run the Application**
- Using Maven:
  ```
  mvn spring-boot:run
  ```
Access the application

- Open a web browser and navigate to http://localhost:8080
