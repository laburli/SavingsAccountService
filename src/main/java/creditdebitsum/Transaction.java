package creditdebitsum;

public class Transaction {private int Customerid;
    private String transactiontype;
    private int amount;


    Transaction(int Customerid, String transactiontype, int amount) {
        this.Customerid = Customerid;
        this.transactiontype = transactiontype;
        this.amount = amount;
    }

    public int getAccountno() {
        return Customerid;
    }

    public void setAccountno(int Customerid) {
        this.Customerid = Customerid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }
}


