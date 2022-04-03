import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
    // Declare DB objects
    DBConnect conn = null;
    Statement stmt = null;

    // constructor
    public Dao() {
        conn = new DBConnect(); // create db object instance
    }

    public void createTable() {
        try {
            System.out.println("Connecting to a selected database to create Table...");
            System.out.println("Connected database successfully...");

            // execute create query
            System.out.println("Creating table in given database...");

            stmt = conn.connect().createStatement();

            String sql = "CREATE TABLE s_pate_tab " +
                    "(pid INTEGER not NULL AUTO_INCREMENT, " +
                    " id VARCHAR(10), " +
                    " income numeric(8,2), " +
                    " pep VARCHAR(4), " +
                    " PRIMARY KEY ( pid ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in the given database");
            conn.connect().close();
        } catch (SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
        }
    }
}
