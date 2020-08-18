import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.security.SecureRandom;

public class App {
    public static void main(String[] args) {
        final String url = "jdbc:postgresql://localhost/mydb";
        final String user = "john";
        final String password = "h74AX9ws";
        Connection c = null;
        Statement stmt = null;
        String depts[] = {"Human Resources", "IT", "Sales", "Warehouse", "Management", "Financial"};
        String firstNames[] = {"Mark", "Joan", "Kelly", "Henry", "Dagny", "Eugene"};
        String lastNames[] = {"Hamilton", "Spencer", "Gerk", "Rearden", "Taggart", "Levy"};
        SecureRandom rand = new SecureRandom();

        try {
            c = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            stmt = c.createStatement();

            String sql = "CREATE TABLE COMPANY " +
                    "(ID    INT," +
                    " NAME           VARCHAR(20), " +
                    " DEPT            VARCHAR(50), " +
                    " EID              INT, " +
                    " SALARY         INT)";
            stmt.executeUpdate(sql);

            for (int i = 0; i < 1000; i++) {
                sql = String.format("INSERT INTO COMPANY (ID, NAME, DEPT, EID, SALARY) VALUES (%d, '%s %s', '%s', %d, %d);", i+1, firstNames[rand.nextInt(6)],
                                        lastNames[rand.nextInt(6)], depts[rand.nextInt(6)], i, (2000 + rand.nextInt(5000)));
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
