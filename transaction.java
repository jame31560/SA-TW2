import java.util.Scanner;
class Controller{
    DBMgr dbmgr = new DBMgr();
    public void makeTransaction(Payer payer, Payee payee){
        boolean v = dbmgr.verifyQRCode(payer.qrcode);
            if(v){
                double a = payee.enterAmount();
				if(payer.confirmAmount(a)){
					payer.deductMoney(a);
					payee.addMoney(a);
					System.out.println("Transaction success.");
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
class User{
	protected String name;
    protected String id;
    User(String n, String id){
        setName(n);
        setId(id);
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
}
class Payer extends User{
    private double balance;
	public QRCode qrcode;
	private boolean isAvailable;
	Payer(String n, String id, double b){
		super(n, id);
		this.balance = b;
		qrcode = new QRCode(n, "qr01", "abcde");
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
	private double balance;
	Payee(String n, String id, double b){
		super(n, id);
		this.balance = b;
	}
	public double enterAmount(){
	    Scanner scanner = new Scanner(System.in);
		System.out.println("Fill in amount:");
		double amount = scanner.nextDouble();
		return amount;
	}
	public void addMoney(double a){
		balance += a;
		System.out.println("payer add money: " + a + " balance: " + balance);
	}
}
public class transaction{
    public static void main(String args[]) {
        Controller c = new Controller();
        Payer payer = new Payer("Amy", "B10623020", 1000);
		Payee payee = new Payee("jay", "B10623019", 1000);
        c.makeTransaction(payer, payee);
    }
}
