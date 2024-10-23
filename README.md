# CLIPBOAR - Clipboard Sharing App

CLIPBOAR is a Java-based clipboard-sharing application built with Spring Boot. It enables users to share clipboard content across networks with features like content expiration and secure share code generation. MongoDB is used for data storage, providing a flexible schema for clipboard data.

## Features

- **Network-based Clipboard Sharing**: Share clipboard data with other users over the network.
- **Content Expiration**: Clipboard entries automatically expire after a user-defined duration.
- **Secure Share Code Generation**: Create unique codes for secure sharing of clipboard content.
- **MongoDB Integration**: Stores clipboard content and metadata such as expiration time.

## Technologies

- **Java**: Core language for the application's logic.
- **Spring Boot**: Provides the backend framework, handling web, data, and application logic.
- **MongoDB**: NoSQL database used to store clipboard data with flexible schema support.
- **Maven**: Project management and dependency handling.

## Prerequisites

- **Java 17+**: Ensure you have Java Development Kit (JDK) 17 or higher installed.
- **MongoDB**: Make sure MongoDB is installed and running on your machine or accessible via cloud.
- **Maven**: To handle dependencies and building the project.

---

## Getting Started

### 1. Clone the repository:
```bash
git clone https://github.com/your-username/clipboar.git
cd clipboar/clipboar-api
```

### 2. Configure MongoDB:

Update application.properties to include your MongoDB URI. Example for application.properties:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/clipboar
```

### 3. Build the project:

If Maven is installed on your machine, run the following command:
```bash
mvn clean install
```

Otherwise, you can use the provided Maven wrapper:
```bash
./mvnw clean install
```

### 4. Run the application:

To run the application using Maven:
```bash
mvn spring-boot:run
```

Or, if you don't have Maven installed, you can use the Maven wrapper:
```bash
./mvnw spring-boot:run
```

### 5. Access the API:

The API will be available at http://localhost:8080. You can use tools like Postman or curl to test the endpoints.

---

## Endpoints

### 1. Create Clipboard

**Endpoint**:  
`POST /api/clipboard`

**Request Body**:
```json
{
    "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
    "settings": {
        "networkVisible": "false",
        "expirationTime": 30000
    }
}
```

### 2. Get Network-Shared Clipboards

**Endpoint**:  
`GET /api/clipboard/network`

### 3. Get Clipboard by Sharing Code

**Endpoint**:  
`GET /api/clipboard/sharingCode/{sharingCode}`

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for more details.
