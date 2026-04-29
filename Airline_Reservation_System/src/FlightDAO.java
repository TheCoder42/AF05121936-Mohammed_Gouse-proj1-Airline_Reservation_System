import java.sql.*;

public class FlightDAO {

    public void addFlight(Flight f) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO flights(flight_name, source, destination, seats_available, price) VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, f.name);
            ps.setString(2, f.source);
            ps.setString(3, f.destination);
            ps.setInt(4, f.seats);
            ps.setDouble(5, f.price);

            ps.executeUpdate();
            System.out.println("✅ Flight Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewFlights() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM flights");

            while (rs.next()) {
                System.out.println(rs.getInt("flight_id") + " | "
                        + rs.getString("flight_name") + " | "
                        + rs.getString("source") + " -> "
                        + rs.getString("destination") + " | Seats: "
                        + rs.getInt("seats_available") + " | ₹"
                        + rs.getDouble("price"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchFlight(String src, String dest) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM flights WHERE source=? AND destination=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, src);
            ps.setString(2, dest);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Flight ID: " + rs.getInt("flight_id")
                        + " | " + rs.getString("flight_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}