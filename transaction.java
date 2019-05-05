import java.util.Scanner;
class Controller{
    DBMgr dbmgr = new DBMgr();
    public void makeTransaction(Payer payer, Payee payee){
        boolean v = dbmgr.verifyQRCode(payer.qrcode);
            if(v){
                double a = payee.enterAmount();
                TransactionHistory trans = new TransactionHistory(payer, payee, a);
				if(payer.confirmAmount(a)){
					payer.deductMoney(a);
					payee.addMoney(a);
					System.out.println("Transaction success.");
					trans.printHistory();
				}else{
					System.out.println("Balance is not enough.");
				}
            }else{
                System.out.println("Wrong QRcode ID.");
            }
    }
}
class DBMgr{
	boolean verifyQRCode(QRCode q){
		return q.getisValid();
	}
}
class QRCode{
    private String name;
	private String id;
    private String hash;
	private boolean isValid = true;
    QRCode(String n, String id, String h){
        setName(n);
        setId(id);
		setHash(h);
    }
    public void setName(String n){
        this.name = n;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
	public void setHash(String h){
        this.hash = h;
    }
    public String getHash(){
        return hash;
    }
	public boolean getisValid(){
		return isValid;
	}
}
class TransactionHistory{
    Payer payer;
    Payee payee;
    double pay;
    TransactionHistory(Payer payer, Payee payee, double a){
        this.payer = payer;
        this.payee = payee;
        this.pay = a;
    }
    void printHistory(){
        System.out.println("----TransactionHistory----");
        System.out.println(payer.getName() + " deduct $" + pay + " ,balance: " + payer.getBalance());
        System.out.println(payee.getName() + " add $" + pay + " ,balance: " + payer.getBalance());
    }
}
class User{
	protected String name;
    protected String id;
    protected double balance;
    User(String n, String id, double b){
        setName(n);
        setId(id);
        setBalance(b);
    }
    public void setName(String n){
        this.name = n;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setBalance(double b){
        this.balance = b;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public double getBalance(){
        return balance;
    }
}
class Payer extends User{
	public QRCode qrcode;
	private boolean isAvailable;
	Payer(String n, String id, double b, String qrid, String hash){
		super(n, id, b);
		qrcode = new QRCode(n, qrid, hash);
	}	
	public boolean confirmAmount(double pay){
		if(pay < balance){
			isAvailable = true;
		}else{
			isAvailable = false;
		}
		return isAvailable;
	}
	public void deductMoney(double a){
		balance -= a;
		System.out.println("payer deduct money: " + a + " balance: " + balance);
	}
}
class Payee extends User{
	Payee(String n, String id, double b){
		super(n, id, b);
	}
	public double enterAmount(){
	    Scanner scanner = new Scanner(System.in);
		System.out.println("Fill in amount:");
		double amount = scanner.nextDouble();
		return amount;
	}
	public void addMoney(double a){
		balance += a;
		System.out.println("payee add money: " + a + " balance: " + balance);
	}
}
public class transaction{
    public static void main(String args[]) {
        Controller c = new Controller();
        Payer payer = new Payer("Amy", "B10623020", 1000, "qr01", "abcde");
		Payee payee = new Payee("jay", "B10623019", 1000);
        c.makeTransaction(payer, payee);
    }
}
