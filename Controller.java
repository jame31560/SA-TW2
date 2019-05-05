import java.util.Scanner;

public class Controller {
    DBMgr dbMgr = new DBMgr();

    public User requestPayer() {

        String QRCodeID;
        Scanner scanner = new Scanner(System.in);
        while (true) {
			System.out.println("\nFill in the QRcode ID:");
			
			QRCodeID = scanner.nextLine();
			
			if(dbMgr.verifyQRCode(QRCodeID)) {
				return dbMgr.getUser(QRCodeID);
			} else {
				System.out.println("\nWrong QRcode ID.");
			}
		}
	}
	
	public void makeTransaction(User payee, User payer) {
		System.out.println("\nFill in the amount:");
		Scanner scanner = new Scanner(System.in);
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

		System.out.println("\nPayer's info:\npayer:" + payer.getId() + "\nblance: " + payer.getBlance());
		System.out.println("\nPayee's info:\npayee:" + payee.getId() + "\nblance: " + payee.getBlance());
	}
}