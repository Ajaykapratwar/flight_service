# Flight Booking Service

This repository contains a full-stack flight booking application featuring a Spring Boot backend and a React/Vite frontend.

## Project Structure

The project is divided into two main components:

- **`backend/flight-service`**: A Java Spring Boot REST API for managing flights.
- **`frontend/flight-app`**: A React single-page application (SPA) built with Vite and Bootstrap for the user interface.

## Features

- **List all flights**
- **Add a new flight**
- **Search flights** by:
  - Flight Code
  - Carrier Name
  - Route (Source to Destination)
  - Price Range
- **Delete flights**

## Getting Started

### Prerequisites
- **Java 17+**
- **Maven**
- **Node.js** (v18+ recommended)
- **MySQL** Server

### Database Setup
1. Ensure your MySQL server is running on `localhost:3306`.
2. The application uses the database `flights` with username `root` and password `root`. Update `application.properties` in the backend if your credentials differ.
3. The database and tables will be created automatically on the first run by Hibernate.

### Running the Backend

Navigate to the backend directory:
```bash
cd backend/flight-service
```

Compile and run the Spring Boot application:
```bash
mvn clean install -DskipTests
mvn spring-boot:run
```
The backend API will run on `http://localhost:8080`.

### Running the Frontend

Navigate to the frontend directory:
```bash
cd frontend/flight-app
```

Install dependencies and start the development server:
```bash
npm install
npm run dev
```
The frontend will be available at `http://localhost:5173` (or the port specified in your terminal).
