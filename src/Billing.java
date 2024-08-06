import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Billing {
    public void generateBill(int orderId) {
        String query = "SELECT o.id, u.name, m.name AS medicine, o.quantity, o.total_price, o.status, o.order_date " +
                       "FROM orders o " +
                       "JOIN users u ON o.user_id = u.id " +
                       "JOIN medicines m ON o.medicine_id = m.id " +
                       "WHERE o.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("id"));
                System.out.println("Customer Name: " + rs.getString("name"));
                System.out.println("Medicine: " + rs.getString("medicine"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Total Price: $" + rs.getDouble("total_price"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}