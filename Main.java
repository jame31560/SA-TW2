import java.io.Console;

public class Main {
	static Console console = System.console();
	static Controller c = new Controller();
	public static void main(String[] args) {
		String command;
		console.printf("Hello, wellcome to use paymet system.\n");	
		console.printf("\nPlease select what do you want to do.\n");
		command = console.readLine("1) Login\n2) Regist\n3) Quit\n>>> ");
		while (!command.equals("3")) {
			switch(command) { 
				case "1": 
					console.printf("\nLogin\n"); 
					login();
					loginMenu();
					logout();
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				case "2": 
					console.printf("\nRegist\n");
					regist();
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				default: 
					console.printf("\nYour input is not any option.\n");
					console.printf("Please select again.\n");
			}
			command = console.readLine("1) Login\n2) Regist\n3) Quit\n>>> ");
		}
	}

	public static void loginMenu() {
		String command;
		console.printf("\nPlease select what do you want to do.\n");
		command = console.readLine("1) Make Transaction\n"
			+ "2) Show Info\n"
			+ "3) Change Password\n"
			+ "4) Show QRCode\n"
			+ "5) Show Transaction History\n"
			+ "6) Logout\n"
			+ ">>> ");
		while (!command.equals("6")) {
			switch(command) {
				case "1":
					console.printf("\nMake Transaction\n");
					makeTransaction();
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				case "2":
					console.printf("\nShow Info\n"); 
					console.printf(c.getUserInfo());
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				case "3":
					console.printf("\nChange Password\n"); 
					changePassword();
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				case "4":
					console.printf("\nShow QRCode\n"); 
					showQRCode(c.getQRCodeID());
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				case "5":
					console.printf("\nShow Transaction History\n");
					showTransactionHistory(); 
					console.printf(
						"\nPlease select what do you want to do.\n");
					break;
				default:
					console.printf("\nYour input is not any option.\n");
					console.printf("Please select again.\n");
			}
			command = console.readLine("1) Make Transaction\n"
				+ "2) Show Info\n"
				+ "3) Change Password\n"
				+ "4) Show QRCode\n"
				+ "5) Show Transaction History\n"
				+ "6) Logout\n>>> ");
		}
	}

	public static void showQRCode(String QRCodeID) {
		console.printf(QRCodeID);
	}

	public static void login() {
		String username = console.readLine("Username\n>>> ");
		String password = String.valueOf(
			console.readPassword("Password\n>>> "));
		while(!c.login(username, password)) {
            console.printf(
				"\nWrong username or password, please login again.\n");
            username = console.readLine("Username\n>>> ");
            password = String.valueOf(
				console.readPassword("Password\n>>> "));
		}
		console.printf("\nLogin success.\n");
		console.printf("\nLogin success.\n");
		console.printf("Hello %s\n", c.getName());
	}

	public static void logout() {
		while(!c.logout()) {
            console.printf("\nLogout fail.\n");
		}
		console.printf("\nLogout success.\n");
	}

	public static void makeTransaction() {
		String QRCodeID;
		String payerID = null;
		boolean flg = true;
		boolean confirm = true;
		int amount = -1;

        while (flg) {
            QRCodeID = console.readLine(
				"\nFill in the QRcode ID of the scanner result\n>>> ");
            if(QRCodeID.equals(c.getQRCodeID())) {
				console.printf(
					"\nYou can not make transaction with yourself.\n");
				console.printf("Please fill in again.\n");
            } else if (c.getPayer(QRCodeID) != null){
				payerID = c.getPayer(QRCodeID);
                flg = false;
            } else {
				console.printf("\nWrong QRcode ID.\n");
				console.printf("Please fill in again.\n");
            }
		}
		
		while(confirm) {
			try {
				amount = Integer.valueOf(
					console.readLine("\nFill in the amount\n>>> "));
				if (amount <= 0) {
					console.printf(
						"\nPlease fill in an integer greater than 0.\n");
				} else {
					confirm = false;
				}
			} catch (Exception e) {
				console.printf("\nThe input seems not a number.\n");
				console.printf("Please fill in again.\n");
			}
		}

		Transaction transaction = c.makeTransaction(payerID, amount);
		String msg = "\nTransaction ";
		if (transaction.getStatus()) {
            msg += "success.\n";
            msg += String.format("Transaction amount: %,d\n",
                transaction.getAmount());
            msg += ("Payer: " +  transaction.getPayerID() + "\n");
		} else {
			msg += "fail.\n";
            msg += ("Reason: " + transaction.getReason() + "\n");
            msg += String.format("Transaction amount: %,d\n",
                transaction.getAmount());
            msg += ("Payer: " +  transaction.getPayerID() + "\n");
		}

		console.printf(msg);

	}

	public static void changePassword() {
		String password;
		String newPassword;
		String confirmPassword;
		String[] result = {""};
		while (result[0].equals("") || result[0].equals("Fail")) {
			if (!result[0].equals("")) {
				console.printf("\nPassword Change Fail\n");
				console.printf("%s\n", result[1]);	
			}
			password = String.valueOf(
				console.readPassword("Recent password >>> "));
			newPassword = String.valueOf(
				console.readPassword("New password >>> "));
			confirmPassword = String.valueOf(
				console.readPassword("Confirm password >>> "));
			result = c.changePassword(password, newPassword, confirmPassword);
		}
		console.printf("\nPassword Change Success\n");
	}

	public static void regist() {
		String username;
		String password;
		String confirmPassword;
		String name;
		String phone;
		String[] result = {""};
		
		while (result[0].equals("") || result[0].equals("Fail")) {
			if (!result[0].equals("")) {
				console.printf("\nRegist Fail\n");
				console.printf("%s\n", result[1]);	
			}
			username = console.readLine("Username >>> ");
			password = String.valueOf(console.readPassword("Password >>> "));
			confirmPassword = String.valueOf(
				console.readPassword("Confirm Password >>> "));
			name = console.readLine("Name >>> ");
			phone = console.readLine("Phone >>> ");
			result = c.regist(username,
				password,
				confirmPassword,
				name,
				phone,
				0);
		}
		console.printf("\nRegist Success\n");
	}

	public static void showTransactionHistory() {
		String[][] historyList = c.getUserHistory();
		for(int i = 0; i < historyList.length; i++) {
			console.printf("\nTransaction No.: %s\n"
				+ "Amount: %s\n"
				+ "Payee : %s | Payer: %s\n",
				historyList[i][0],
				historyList[i][1],
				historyList[i][5],
				historyList[i][6]);
			if (historyList[i][3].equals("0")) {
				console.printf("Status: Success\n");
			} else {
				console.printf("Status: Fail\nReason: %s\n", 
					historyList[i][4]);
			}
			console.printf("Time  : %s\n", historyList[i][2]);
		}
	}
}
