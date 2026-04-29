import java.sql.*;

public class BookingDAO {

    public void bookTicket(int passengerId, int flightId) {
        try {
            Connection conn = DBConnection.getConnection();

            // check seats
            PreparedStatement check = conn.prepareStatement(
                    "SELECT seats_available FROM flights WHERE flight_id=?");
            check.setInt(1, flightId);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO bookings(passenger_id, flight_id, booking_date) VALUES(?,?,CURDATE())");
                ps.setInt(1, passengerId);
                ps.setInt(2, flightId);
                ps.executeUpdate();

                PreparedStatement update = conn.prepareStatement(
                        "UPDATE flights SET seats_available = seats_available - 1 WHERE flight_id=?");
                update.setInt(1, flightId);
                update.executeUpdate();

                System.out.println("✅ Ticket Booked!");

            } else {
                System.out.println("❌ No seats available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelTicket(int bookingId) {
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT flight_id FROM bookings WHERE booking_id=?");
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int flightId = rs.getInt(1);

                PreparedStatement del = conn.prepareStatement(
                        "DELETE FROM bookings WHERE booking_id=?");
                del.setInt(1, bookingId);
                del.executeUpdate();

                PreparedStatement up = conn.prepareStatement(
                        "UPDATE flights SET seats_available = seats_available + 1 WHERE flight_id=?");
                up.setInt(1, flightId);
                up.executeUpdate();

                System.out.println("✅ Ticket Cancelled!");
            } else {
                System.out.println("❌ Booking not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBookings() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT b.booking_id, p.name, f.flight_name " +
                    "FROM bookings b " +
                    "JOIN passengers p ON b.passenger_id=p.passenger_id " +
                    "JOIN flights f ON b.flight_id=f.flight_id";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt(1)
                        + " | Passenger: " + rs.getString(2)
                        + " | Flight: " + rs.getString(3));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}