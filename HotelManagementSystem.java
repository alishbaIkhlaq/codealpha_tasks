package com.mycompany.hotelmanagementsystem;

import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + category + "] - $" + price + " - " + (isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    int reservationId;
    String guestName;
    Room room;
    boolean paymentCompleted;

    public Reservation(int reservationId, String guestName, Room room) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        this.paymentCompleted = false;
    }

    public void completePayment() {
        this.paymentCompleted = true;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName + ", " + room.toString() + ", Payment: " + (paymentCompleted ? "Completed" : "Pending");
    }
}

public class HotelManagementSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static int reservationCounter = 1;

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Complete Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation(scanner);
                case 3 -> viewReservations();
                case 4 -> completePayment(scanner);
                case 5 -> {
                    System.out.println("Exiting system. Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Fixed method placement inside the class
    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Deluxe", 150.0));
        rooms.add(new Room(103, "Suite", 200.0));
        rooms.add(new Room(104, "Standard", 100.0));
        rooms.add(new Room(105, "Suite", 200.0));
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();

        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                room.bookRoom();
                Reservation reservation = new Reservation(reservationCounter++, guestName, room);
                reservations.add(reservation);
                System.out.println("Reservation successful! Your reservation ID: " + reservation.reservationId);
                return;
            }
        }
        System.out.println("Room not available or incorrect room number.");
    }

    private static void viewReservations() {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private static void completePayment(Scanner scanner) {
        System.out.print("\nEnter reservation ID to complete payment: ");
        int resId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.reservationId == resId && !reservation.paymentCompleted) {
                reservation.completePayment();
                System.out.println("Payment successful for Reservation ID: " + resId);
                return;
            }
        }
        System.out.println("Invalid reservation ID or payment already completed.");
    }
}
