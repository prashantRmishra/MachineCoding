
# [FlipMed Problem Statement](problemStatement.md)

## UML Diagram
![Class Diagram](./images/classDiagram.png)
# FlipMed Appointment Management System

## Overview
FlipMed is a Java-based medical appointment management system that allows patients to book appointments with doctors, manage slot availability, and implement a waitlist mechanism.

## Project Structure
The project is organized into several key packages:
- `companySpecific.flipkart.flipmed.model`: Contains domain models
- `companySpecific.flipkart.flipmed.manager`: Manages user and appointment operations
- `companySpecific.flipkart.flipmed.strategy`: Implements doctor search strategies

## Key Features
1. **User Management**
   - Register patients and doctors
   - Support for different user types (patient, doctor)

2. **Appointment Booking**
   - Book appointments with available doctors
   - Automatic waitlist management for fully booked slots
   - Cancel and reschedule appointments

3. **Slot Management**
   - Doctors can mark their availability
   - Slots are 30 minutes long
   - Supports multiple slots per doctor

4. **Doctor Search**
   - Search doctors by speciality
   - Flexible enquiry strategy (can be extended)

## Architecture Highlights
- Strategy Pattern: Used for doctor search (can easily add new search strategies)
- Concurrent-safe: Uses `synchronized` methods and `AtomicBoolean` for thread safety
- Flexible design with separation of concerns

## Models
- `User`: Base class for patients and doctors
- `Patient`: Represents patient users
- `Doctor`: Represents doctor users with speciality and slots
- `Appointment`: Represents a booked appointment
- `Slot`: Represents a time slot with booking status

## Key Classes
- `Controller`: Central coordination of user and appointment operations
- `UserManager`: Manages user registration and availability
- `AppointmentManager`: Handles appointment creation, removal, and waitlist

## How to Use
```java
// Create managers and controller
AppointmentManager appointmentManager = new AppointmentManager();
EnquiryStrategy strategy = new SpecialityStrategy();
UserManager userManager = new UserManager(strategy);
Controller controller = new Controller(userManager, appointmentManager);

// Add a doctor
Doctor d1 = new Doctor("Dr. Smith", UserType.doctor, Speciality.cardiologist);
controller.addDoctor(d1);

// Mark doctor availability
controller.markDocAvailability(d1, "9:30-10:00,12:30-13:00");

// Add a patient
Patient p = new Patient("John Doe", UserType.patient);
controller.addPatient(p);

// Book an appointment
controller.addAppointment(p, d1, "12:30");
```

## Waitlist Mechanism
- When a slot is fully booked, patients are added to a waitlist
- When an appointment is cancelled, the next waitlisted patient is automatically scheduled

## Design Patterns Used
- Strategy Pattern: For doctor search strategy
- Singleton-like: Utility class for generating unique IDs
- Factory-like: User creation with different types

## Concurrency Considerations
- `synchronized` methods prevent race conditions
- `AtomicBoolean` used for thread-safe slot and waitlist status

## Potential Improvements
- Add more advanced scheduling algorithms
- Implement persistent storage
- Create a web or mobile interface
- Add more detailed error handling

## Technologies
- Java 11+
- Concurrent programming
- Strategy design pattern

## Limitations
- In-memory storage (no database)
- Limited to 30-minute slots
- Basic error handling

## License
Open-source project for learning and demonstration purposes.