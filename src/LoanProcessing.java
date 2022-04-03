public class LoanProcessing {
    public static void main(String[] args) {
        Dao dao = new Dao();
        //dao.createTable();
        BankRecords bank = new BankRecords();
        bank.read_data();
        bank.process_data();
        //dao.insertRecords(BankRecords.records);
    }
}
