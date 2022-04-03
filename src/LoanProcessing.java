import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LoanProcessing extends BankRecords {
    public static void main(String[] args) {
        Dao dao = new Dao();
        // dao.createTable();
        BankRecords bank = new BankRecords();
        bank.read_data();
        bank.process_data();
        String[][] rowData = new String[600][3];    //600 sub arrays and 3 elements in each sub array
        String[] colNames = { "ID", "INCOME:", "PEP:" };
        int count = 0;
        // dao.insertRecords(BankRecords.records);
        ResultSet rs = dao.retrieveRecords();
        System.out.println("\nPrinting out entries to console: \n\n");
        try {
            System.out.printf("%4s\t\t%4s\t\t%6s\n", "ID:", "INCOME:", "PEP:");
            System.out.println("=====================================================");
            while (rs.next()) {
                System.out.printf("%4s\t\t%4d\t\t%6s\n", rs.getString(1), rs.getInt(2),
                        rs.getString(3)); // get the first col, second col and then third column
                rowData[count][0] = rs.getString(1);
                rowData[count][1] = String.valueOf(rs.getInt(2));
                rowData[count][2] = rs.getString(3);
                count += 1;
            }
            System.out.println("\n\nAll 600 entries have been succesfully completed\n");
            System.out.println("The output has been sorted by PREP in DESCENDING order \n");
            rs.close();

            //JTable GUI Code below:
            JFrame frame = new JFrame();
            JTable myTable = new JTable(rowData, colNames);
            JScrollPane scrollPane = new JScrollPane(myTable);
            frame.add(scrollPane);
            frame.setSize(300, 400);
            frame.setVisible(true); 
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //need this otherwise program won't terminate
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
