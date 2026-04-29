import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlightDAO flightDAO = new FlightDAO();
        PassengerDAO passengerDAO = new PassengerDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("\n--- Airline Reservation System ---");
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Search Flight");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View Bookings");
            System.out.println("7. Exit");

            System.out.print("-> Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    System.out.print("Flight Name: ");
                    String name = sc.nextLine();
                    System.out.print("Source: ");
                    String src = sc.nextLine();
                    System.out.print("Destination: ");
                    String dest = sc.nextLine();
                    System.out.print("Seats: ");
                    int seats = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    flightDAO.addFlight(new Flight(name, src, dest, seats, price));
                    break;

                case 2:
                    flightDAO.viewFlights();
                    break;

                case 3:
                    System.out.print("Source: ");
                    src = sc.nextLine();
                    System.out.print("Destination: ");
                    dest = sc.nextLine();
                    flightDAO.searchFlight(src, dest);
                    break;

                case 4:
                    flightDAO.viewFlights();
                    System.out.print("Enter Flight ID: ");
                    int flightId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();

                    int pid = passengerDAO.addPassenger(new Passenger(pname, age, gender));
                    bookingDAO.bookTicket(pid, flightId);
                    break;

                case 5:
                    System.out.print("Enter Booking ID: ");
                    int bid = sc.nextInt();
                    bookingDAO.cancelTicket(bid);
                    break;

                case 6:
                    bookingDAO.viewBookings();
                    break;

                case 7:
                    System.out.println("👋 Exiting...");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}