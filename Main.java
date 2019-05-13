import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Controller c = new Controller();

		User payee;
		User payer;
		String command;

		System.out.println("Hello, wellcome to use paymet system.");	
		System.out.println("Please select what do you want to do.\n");
		System.out.print("1) Login\n2) Regist\n3) Quit\n\n>>> ");
		command = scanner.nextLine();

		while (!command.equals("3") || command == null) {
			switch(command) { 
				case "1": 
					System.out.println("Login"); 
					c.login();
					payee = c.getUser();
					System.out.print("Please select what do you want to do.\n");
					System.out.print("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n\n>>> ");
					command = scanner.nextLine();

					while (!command.equals("4")) {
						switch(command) {
							case "1":
								System.out.println("Make Transaction"); 

								payer = c.requestPayer();
								while (payer.getID().equals(payee.getID())) {
									System.out.println("\nYou can not make transaction with yourself.\nPlease fill in again.\n");
									payer = c.requestPayer();
								}
								
								c.makeTransaction(payee, payer);

								break;
							case "2":
								System.out.println("Show Info"); 
								break;
							case "3":
								System.out.println("Change Info"); 
								break;
							default:
								System.out.println("\nYour input is not any option.\nPlease select again.");
						}
						System.out.print("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n\n>>> ");
						command = scanner.nextLine();
					}

					c.logout();


					

					System.out.println("Please select what do you want to do.\n");
					break;
				case "2": 
					System.out.println("Regist"); 
					System.out.println("Please select what do you want to do.\n");
					break;
				default: 
					System.out.println("\nYour input is not any option.\nPlease select again.");
			}
			System.out.print("1) Login\n2) Regist\n3) Quit\n\n>>> ");
			command = scanner.nextLine();
		}
		scanner.close();
	}
}