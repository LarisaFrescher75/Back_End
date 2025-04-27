public class Account {


    private final String iban;
    private final String owner;
    private double balance;

    public Account(String iban, String owner, double balance) {
        this.iban = iban;
        this.owner = owner;

        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (getBalance() >= amount) {
            balance -= amount;
            return  true;
        }
        return  false;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }


}


