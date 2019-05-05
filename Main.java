public class Main {
	public static void main(String[] args) {
		Controller c = new Controller();
		
        User payee = new User("B10623020", "1234", "Amy1", 1000, "09123456781");
		/* 
		The system will know who is the payee by login session,
		but we haven't creat the login function and session,
		so use the new user in here.
		*/

		User payer = c.requestPayer();

		while (payer.getId().equals(payee.getId())) {
			System.out.println("\nYou can not make transaction with yourself.\nPlease fill in again.\n");
			payer = c.requestPayer();
		}
		
		c.makeTransaction(payee, payer);
	}
}