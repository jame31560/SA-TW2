import java.io.Console;

public class Main {
	public static void main(String[] args) {
		Console console = System.console();
		Controller c = new Controller();
		String command;

		console.printf("Hello, wellcome to use paymet system.\n");	
		console.printf("\nPlease select what do you want to do.\n");
		command = console.readLine("1) Login\n2) Regist\n3) Quit\n>>> ");

		while (!command.equals("3")) {
			switch(command) { 
				case "1": 
					console.printf("\nLogin\n"); 
					c.login();
					System.out.print("\nPlease select what do you want to do.\n");
					command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n>>> ");

					while (!command.equals("4")) {
						switch(command) {
							case "1":
								console.printf("\nMake Transaction\n");
								c.scanQRCode();
								c.makeTransaction();
								System.out.print("\nPlease select what do you want to do.\n");
								break;
							case "2":
								console.printf("\nShow Info\n"); 
								c.showInfo();
								System.out.print("\nPlease select what do you want to do.\n");
								break;
							case "3":
								console.printf("\nChange Info\n"); 

								System.out.print("\nPlease select what do you want to do.\n");
								break;
							default:
								console.printf("\nYour input is not any option.\nPlease select again.\n");
						}
						command = console.readLine("1) Make Transaction\n2) Show Info\n3) Change Info\n4) Logout\n>>> ");
					}
					c.logout();
					console.printf("\nPlease select what do you want to do.\n");
					break;
				case "2": 
					console.printf("\nRegist\n"); 
					console.printf("\nPlease select what do you want to do.\n");
					break;
				default: 
					console.printf("\nYour input is not any option.\nPlease select again.\n");
			}
			command = console.readLine("1) Login\n2) Regist\n3) Quit\n>>> ");
		}
	}
}