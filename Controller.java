import java.io.Console;

public class Controller {
	DBMgr dbMgr = new DBMgr();
	User user;
	User payer;
	Console console = System.console();

	public void login() {
		String username = console.readLine("Username\n>>> ");
		String password = String.valueOf(console.readPassword("Password\n>>> "));
		while(!dbMgr.verifyLogin(username, password)) {
			console.printf("\nWrong username or password, please login again.\n");
			username = console.readLine("Username\n>>> ");
			password = String.valueOf(console.readPassword("Password\n>>> "));
		}
		user = dbMgr.getUserByAccountID(username);
		console.printf("\nLogin success.\nHello " + user.getName() + "\n");
	}

	public void logout() {
		user = null;
		console.printf("\nLogout success.\n");
	}

    public void scanQRCode() {
		String QRCodeID;
		boolean flg = true;

        while (flg) {
			QRCodeID = console.readLine("\nFill in the QRcode ID of the scanner result\n>>> ");
			if(QRCodeID.equals(user.getQRCodeID())) {
				console.printf("\nYou can not make transaction with yourself.\nPlease fill in again.\n");
			} else if (dbMgr.verifyQRCode(QRCodeID)){
				payer = dbMgr.getUserByQRCodeID(QRCodeID);
				flg = false;
			} else {
				console.printf("\nWrong QRcode ID.\nPlease fill in again.\n");
			}
		}
	}
	
	public void makeTransaction() {
		int amount = 0;
		int confirm = -1;

		while (confirm == 2 || confirm == -1) {
			try {
				amount = Integer.valueOf(console.readLine("\nFill in the amount\n>>> "));
				if (amount <= 0) {
					console.printf("\nPlease fill in a integer greater than 0.\n");
				} else {
					confirm = payer.confirmAmount(amount);
				}
			} catch (Exception e) {
				console.printf("\nThe input seems not a number.\nPlease fill in again.\n");
			}
		}

		if (confirm == 0) {
			payer.deductMoney(amount);
			user.addMoney(amount);
			console.printf("\nTransaction success.\n");
		} else {
			console.printf("\nTransaction fail.\n");
		}

		console.printf("\nPayer's info:\npayer:" + payer.getID() + "\nblance: " + payer.getBlance() + "\n");
		console.printf("\nPayee's info:\npayee:" + user.getID() + "\nblance: " + user.getBlance() + "\n");
	}

	public void showInfo() {
		console.printf("Name: %s\n", user.getName());
		console.printf("Username: %s\n", user.getID());
		console.printf("Phone: %s\n", user.getPhone());
		console.printf("Blance: NT$ %,d\n", user.getBlance());
	}


	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}