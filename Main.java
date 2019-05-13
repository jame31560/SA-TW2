import java.io.Console;

public class Main {
	public static void main(String[] args) {
		Console console = System.console();
		Controller c = new Controller();
		String command;

		console.printf("Hello, wellcome to use paymet system.");	
		console.printf("Please select what do you want to do.\n");
		command = console.readLine("1) Login\n2) Regist\n3) Quit\n\n>>> ");

		while (!command.equals("3")) {
			switch(command) { 
				case "1": 
					console.printf("\nLogin"); 
					c.login();
					System.out.print("Please select what do you want to do.\n");
					command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n\n>>> ");

					while (!command.equals("4")) {
						switch(command) {
							case "1":
								console.printf("\nMake Transaction");
								c.scanQRCode();
								c.makeTransaction();

								System.out.print("Please select what do you want to do.\n");
								break;
							case "2":
								console.printf("\nShow Info"); 

								System.out.print("Please select what do you want to do.\n");
								break;
							case "3":
								console.printf("\nChange Info"); 

								System.out.print("Please select what do you want to do.\n");
								break;
							default:
								console.printf("\nYour input is not any option.\nPlease select again.");
						}
						command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n\n>>> ");
					}
					c.logout();
					console.printf("Please select what do you want to do.\n");
					break;
				case "2": 
					console.printf("\nRegist"); 
					console.printf("Please select what do you want to do.\n");
					break;
				default: 
					console.printf("\nYour input is not any option.\nPlease select again.");
			}
			command = console.readLine("1) Login\n2) Regist\n3) Quit\n\n>>> ");
		}
	}
}