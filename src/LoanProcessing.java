import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanProcessing extends BankRecords {
    public static void main(String[] args) {
        Dao dao = new Dao();
        // dao.createTable();
        BankRecords bank = new BankRecords();
        bank.read_data();
        bank.process_data();
        // dao.insertRecords(BankRecords.records);
        ResultSet rs = dao.retrieveRecords();
        try {
            System.out.printf("%4s\t\t%4s\t\t%6s\n", "ID:", "INCOME:", "PEP:");
            System.out.println("=====================================================");
            while (rs.next()) {
                System.out.printf("%4s\t\t%4d\t\t%6s\n", rs.getString(1), rs.getInt(2),
                        rs.getString(3));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
