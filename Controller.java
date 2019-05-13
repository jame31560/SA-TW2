import java.util.Scanner;

public class Controller {
	DBMgr dbMgr = new DBMgr();
	User user;
	Scanner scanner = new Scanner(System.in);

	public void login() {
		System.out.print("\nUsername:");
		String username = scanner.nextLine();
		System.out.print("Password:");
		String password = scanner.nextLine();

		while(!dbMgr.verifyLogin(username, password)) {
			System.out.println("\nWrong username or password, please login again.\n");
			System.out.print("Username:");
			username = scanner.nextLine();
			System.out.print("Password:");
			password = scanner.nextLine();
		}

		
		user = dbMgr.getUserByAccountID(username);
		System.out.println("Login success.\nHello " + user.getName());
	}

	public void logout() {
		user = null;
		System.out.println("Login success.");
	}

    public User requestPayer() {

        String QRCodeID;
        while (true) {
			System.out.println("\nFill in the QRcode ID:");
			
			QRCodeID = scanner.nextLine();
			
			if(dbMgr.verifyQRCode(QRCodeID)) {
				return dbMgr.getUserByQRCodeID(QRCodeID);
			} else {
				System.out.println("\nWrong QRcode ID.");
			}
		}
	}
	
	public void makeTransaction(User payee, User payer) {
		System.out.println("\nFill in the amount:");
		int amount = scanner.nextInt();
		int confirm = payer.confirmAmount(amount);

		while (confirm == 2) {
			System.out.println("\nFill in the amount:");
			amount = scanner.nextInt();
			confirm = payer.confirmAmount(amount);
		}

		if (confirm == 0) {
			payer.deductMoney(amount);
			payee.addMoney(amount);
			System.out.println("\nTransaction success.");
		} else {
			System.out.println("\nTransaction fail.");
		}

		System.out.println("\nPayer's info:\npayer:" + payer.getID() + "\nblance: " + payer.getBlance());
		System.out.println("\nPayee's info:\npayee:" + payee.getID() + "\nblance: " + payee.getBlance());
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}