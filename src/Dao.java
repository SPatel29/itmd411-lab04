import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void insertRecords(BankRecords[] records) {
        try {
            System.out.println("Inserting records into table...");
            stmt = conn.connect().createStatement();
            String sql = "INSERT INTO s_pate_tab ( " +
                    "id, " +
                    "income, " +
                    "pep ) VALUES (" +
                    "?, ?, ?)";
            PreparedStatement stPrepare = conn.connect().prepareStatement(sql);
            for (int i = 0; i < records.length; i += 1) { // each element in records array is a Records object
                stPrepare.setString(1, records[i].get_id()); // first column is id
                stPrepare.setDouble(2, records[i].get_income()); // second column is income
                stPrepare.setString(3, records[i].get_prep()); // third column is pep
                stPrepare.executeUpdate();
            }
            System.out.println("Successfully added all 600 entries into table");
            conn.connect().close();
            stPrepare.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet retrieveRecords() {
        ResultSet rs = null;
        try {
            System.out.println("\nAttemping to retrieve all records in database");
            stmt = conn.connect().createStatement();
            String sql = "SELECT id, income, pep from s_pate_tab order by pep desc";
            rs = stmt.executeQuery(sql);
            System.out.println("\nAll records have been fetched and stored in the Result Set");
            conn.connect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
