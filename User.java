import java.io.Console;

public class User {
    Console console = System.console();
    private String ID;
    private String password;
    private String name;
    private int balance;
    private String phone;
    private String QRCodeID;
    private boolean isAvailable;

    User (String ID,
            String password,
            String name,
            int balance,
            String phone) {
        this.setID(ID);
        this.setPassword(password);
        this.setName(name);
        this.setBalance(balance);
        this.setPhone(phone);
        this.setQRCodeID(ID + phone);
        this.setAvailable(true);
    }

    public void deductMoney(int amount) {
        this.balance -= amount;
    }

    public void addMoney(int amount) {
        this.balance += amount;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getQRCodeID() {
        return QRCodeID;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getIsAbailable() {
        return isAvailable;
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQRCodeID(String qRCodeID) {
        this.QRCodeID = qRCodeID;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}