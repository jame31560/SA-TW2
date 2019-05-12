import java.util.Scanner;

public class User {
    private String ID;
    private String password;
    private String name;
    private int blance;
    private String phone;
    private String QRCodeID;
    private boolean isAvailable;

    User (String ID, String password, String name, int blance, String phone) {
        this.setID(ID);
        this.setPassword(password);
        this.setName(name);
        this.setBlance(blance);
        this.setPhone(phone);
        this.setQRCodeID(ID + phone);
        this.setAvailable(true);
    }

    public int confirmAmount(int amount) {
        if (this.blance < amount) {
            System.out.println("\nPayer's balance is not enough.");
            return 1;

        } else {
            Scanner scanner = new Scanner(System.in);
            String confirm = "";

            while (true) {
                System.out.println("\nPayer confirm the amount " + amount +" is that correct?[y/n]");
                confirm = scanner.nextLine().toLowerCase();

                if (confirm.equals("y")) {
                    System.out.println("Confirm success.");
                    return 0;
                } else if (confirm.equals("n")) {
                    System.out.println("Confirm fail.");
                    return 2;
                } else {
                    System.out.println("Your input seems not 'y' or 'n'.");
                }
            }    
        }
    }

    public void deductMoney(int amount) {
        this.blance -= amount;
    }

    public void addMoney(int amount) {
        this.blance += amount;
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

    public int getBlance() {
        return blance;
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

    public void setBlance(int blance) {
        this.blance = blance;
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