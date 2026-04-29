import java.sql.*;

public class PassengerDAO {

    public int addPassenger(Passenger p) {
        int id = 0;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO passengers(name, age, gender) VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.name);
            ps.setInt(2, p.age);
            ps.setString(3, p.gender);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            System.out.println("✅ Passenger Added ID: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}