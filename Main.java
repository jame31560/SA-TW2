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
					console.printf("\nPlease select what do you want to do.\n");
					break;
				case "2": 
					console.printf("\nRegist\n"); 
					console.printf("\nPlease select what do you want to do.\n");
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
		System.out.print("\nPlease select what do you want to do.\n");
		command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n>>> ");
		while (!command.equals("4")) {
			switch(command) {
				case "1":
					console.printf("\nMake Transaction\n");
					makeTransaction();
					System.out.print("\nPlease select what do you want to do.\n");
					break;
				case "2":
					console.printf("\nShow Info\n"); 
					showInfo();
					System.out.print("\nPlease select what do you want to do.\n");
					break;
				case "3":
					console.printf("\nChange Info\n"); 

					System.out.print("\nPlease select what do you want to do.\n");
					break;
				default:
					console.printf("\nYour input is not any option.\n");
					console.printf("Please select again.\n");
			}
			command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n>>> ");
		}
	}

	public static void login() {
		String username = console.readLine("Username\n>>> ");
		String password = String.valueOf(console.readPassword("Password\n>>> "));
		while(!c.login(username, password)) {
            console.printf("\nWrong username or password, please login again.\n");
            username = console.readLine("Username\n>>> ");
            password = String.valueOf(console.readPassword("Password\n>>> "));
		}
		console.printf("\nLogin success.\n");
		console.printf("\nLogin success.\n");
		console.printf("Hello %s\n", c.getUserName());
	}

	public static void logout() {
		while(!c.logout()) {
            console.printf("\nLogout fail.\n");
		}
		console.printf("\nLogout success.\n");
	}

	public static void makeTransaction() {
		String QRCodeID;
		User payer = null;
		boolean flg = true;
		boolean confirm = true;
		int amount = -1;
		String[] result;

        while (flg) {
            QRCodeID = console.readLine("\nFill in the QRcode ID of the scanner result\n>>> ");
            if(QRCodeID.equals(c.getUserQRCodeID())) {
				console.printf("\nYou can not make transaction with yourself.\n");
				console.printf("Please fill in again.\n");
            } else if (c.getPayer(QRCodeID) != null){
				payer = c.getPayer(QRCodeID);
                flg = false;
            } else {
                console.printf("\nWrong QRcode ID.\nPlease fill in again.\n");
            }
		}
		
		while(confirm) {
			try {
				amount = Integer.valueOf(console.readLine("\nFill in the amount\n>>> "));
				if (amount <= 0) {
					console.printf("\nPlease fill in a integer greater than 0.\n");
				} else {
					confirm = false;
				}
			} catch (Exception e) {
				console.printf("\nThe input seems not a number.\nPlease fill in again.\n");
			}
		}

		result = c.makeTransaction(payer, amount);

		if (result[0].equals("Fail")) {
			console.printf("\nTransaction fail.\n");
			console.printf("%s\n", result[1]);
		} else {
			console.printf("\nTransaction success.\n");
			console.printf("Transaction amount: %,d\n", amount);
			showInfo();
		}
	}

	public static void showInfo() {
        console.printf("Name: %s\n", c.getUserName());
        console.printf("Username: %s\n", c.getUserID());
        console.printf("Phone: %s\n", c.getUserPhone());
        console.printf("Blance: NT$ %,d\n", c.getUserBlance());
	}
}