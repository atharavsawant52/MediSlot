# MediSlot — Hospital Appointment & Scheduling System

MediSlot is a Hospital Appointment and Scheduling System developed using Spring Boot and MySQL. It aims to manage doctor information and provide a foundation for hospital appointment management.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Postman

## Features

- User registration
- Password encryption using BCrypt
- Role-based access control (`ROLE_USER` and `ROLE_ADMIN`)
- HTTP Basic Authentication
- Add doctor
- View all doctors
- Update doctor details
- Secure doctor management endpoints

## Security

- Registered users can access permitted endpoints.
- Admin users can manage doctor records.
- Passwords are stored in encrypted form using BCrypt.

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/users/register` | Register a user |
| GET | `/doctors` | View all doctors |
| GET | `/doctors/{id}` | View a doctor by ID |
| POST | `/doctors` | Add a doctor (Admin) |
| PUT | `/doctors/{id}` | Update a doctor (Admin) |
| DELETE | `/doctors/{id}` | Delete a doctor (Admin) |

## Database

Database name: `medislot`

The application uses MySQL and Spring Data JPA to manage database entities.

## How to Run

1. Clone this repository.
2. Configure your MySQL credentials in `application.properties`.
3. Ensure the `medislot` database exists.
4. Run the application using IntelliJ IDEA or Maven.
5. Test the API endpoints using Postman.

## Project Status

The user registration, authentication, and doctor management APIs are implemented and tested using Postman. Appointment scheduling functionality is planned for further development.

## Author

Developed as an individual academic project.
