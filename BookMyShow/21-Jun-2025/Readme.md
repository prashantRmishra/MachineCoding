
### 💡 Problem Statement: Design a Scalable Movie Ticket Booking System (BookMyShow Clone)

#### 🎯 Objective:

Design the **Low-Level Design (LLD)** of an online movie ticket booking system like **BookMyShow**, which allows users to browse movies, check showtimes, select seats, and book tickets in real-time.

---

### 📌 Functional Requirements:

1. **User Management:**

   * Users can register, log in, and manage their profile.
   * Different roles: **Admin**, **Customer**, and **Theater Owner**.

2. **Movies & Shows:**

   * Admins can add/update/delete movies.
   * Theater owners can add screens and schedule shows for movies (with date, time, and screen).

3. **Theater & Screens:**

   * A theater can have multiple screens.
   * Each screen has a fixed seating layout (rows × columns), which can vary per screen.

4. **Seat Management:**

   * Users should be able to view available seats for a show in real-time.
   * Seats can be in different classes (e.g., Premium, Gold, Silver) with different prices.

5. **Booking Tickets:**

   * Users can select seats and make a booking.
   * Handle **concurrent seat selection** to avoid double-booking.
   * After selecting seats, hold them for a short time (e.g., 2 minutes) until payment is completed.

6. **Payments:**

   * Integrate a basic payment flow (mocked, no need for actual gateway).
   * Confirm booking only after payment success.

7. **Booking History:**

   * Users should be able to view their past bookings.
   * Admins/Theater Owners should see show-wise booking reports.

---

### 🔐 Non-Functional Requirements:

* Ensure **thread-safety** for seat booking.
* Handle **concurrent requests** for the same show.
* Use **object-oriented design principles** and clean **SOLID architecture**.
* Keep code **extensible and modular** for adding more event types (e.g., concerts, sports).
* Assume **in-memory storage** or use simple repositories (no DB integration unless required).

---

### 🧱 Suggested Class Diagrams/Components:

* `User`, `Admin`, `Customer`, `TheaterOwner`
* `Movie`, `Show`, `Screen`, `Theater`, `Seat`
* `Booking`, `Payment`, `Ticket`
* `BookingService`, `SeatLockService`, `TheaterService`, `ShowService`, `MovieService`

---

### ⚠️ Concurrency Focus (Key for LLD):

Design `SeatLockService` to temporarily lock seats when a user selects them. Other users shouldn’t be able to select the same seat during the lock period.

---

### 🧪 Optional Extensions (Only if time permits):

* Support for **searching movies by location/date/language**.
* Notifications (on email/SMS after successful booking).
* Seat recommendations (best available).

