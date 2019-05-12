public class Main {
	public static void main(String[] args) {
		Controller c = new Controller();

		c.login();
		User payee = c.getUser();

		User payer = c.requestPayer();

		while (payer.getID().equals(payee.getID())) {
			System.out.println("\nYou can not make transaction with yourself.\nPlease fill in again.\n");
			payer = c.requestPayer();
		}
		
		c.makeTransaction(payee, payer);
	}
}