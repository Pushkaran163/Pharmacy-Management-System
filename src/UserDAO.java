// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class UserDAO{
//     public boolean RegisterUser(String email, String password, String name){
//         String query = "INSERT INTO users(email, password, name) VALUES(?,?,?)";
//         try(Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)){
//                 ps.setString(1, email);
//                 ps.setString(2, password);
//                 ps.setString(3, name);
//                 int rows = ps.executeUpdate();
//                 return rows > 0;
//         }catch(SQLException e){
//             e.printStackTrace();
//             return false;
//         }
//     }
//     public boolean authenticateUser(String email, String password){
//         String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//         try(Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(query)){
//                 ps.setString(1, email);
//                 ps.setString(2, password);
//                 ResultSet rs = ps.executeQuery();
//                 return rs.next();
//         }catch(SQLException e){
//             e.printStackTrace();
//             return false;
//         }
//     }
// }

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class UserDAO {

//     // Method to register a new user
//     public boolean registerUser(String email, String password, String name) {
//         String query = "INSERT INTO users(email, password, name) VALUES (?, ?, ?)";
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement ps = conn.prepareStatement(query)) {
//             ps.setString(1, email);
//             ps.setString(2, password); // Note: Consider hashing the password before storing
//             ps.setString(3, name);
//             int rows = ps.executeUpdate();
//             return rows > 0;
//         } catch (SQLException e) {
//             e.printStackTrace(); // Handle the exception properly (e.g., log it)
//             return false;
//         }
//     }

//     // Method to authenticate a user
//     public boolean authenticateUser(String email, String password) {
//         String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement ps = conn.prepareStatement(query)) {
//             ps.setString(1, email);
//             ps.setString(2, password); // Note: Consider hashing the password before comparing
//             try (ResultSet rs = ps.executeQuery()) {
//                 return rs.next(); // Returns true if there's a matching user
//             }
//         } catch (SQLException e) {
//             e.printStackTrace(); // Handle the exception properly (e.g., log it)
//             return false;
//         }
//     }
// }





// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class UserDAO {
//     public boolean registerUser(String email, String password, String name) {
//         String query = "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setString(1, email);
//             stmt.setString(2, password);
//             stmt.setString(3, name);
//             int rows = stmt.executeUpdate();
//             return rows > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean authenticateUser(String email, String password) {
//         String query = "SELECT * FROM users WHERE email = ? AND password = ?";
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setString(1, email);
//             stmt.setString(2, password);
//             ResultSet rs = stmt.executeQuery();
//             return rs.next();
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
// }




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean registerUser(String email, String password, String name) {
        String query = "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}