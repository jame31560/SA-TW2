import java.util.Scanner;

class DBmanager {
	String[] QRcodeIDList = { "123", "456", "789" };

	boolean verifyQRcode(QRcode a) {
		for(String each : QRcodeIDList) {
			if (a.QRcodeID.equals(each)) {
				return true;
			}
		}

		return false;
	}
}

class QRcode {
	String QRcodeID;
	QRcode(String id) {
		QRcodeID = id;
	}

	String getQRcodeID() {
		return this.QRcodeID;
	}
}

class Controller {
	DBmanager db = new DBmanager();

	QRcode requestQRcode() {
		QRcode a;
		String QRcodeID;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Fill in the QRcode ID:");
			
			QRcodeID = scanner.nextLine();
			
			a = new QRcode(QRcodeID);
			if(db.verifyQRcode(a)) {
				scanner.close();
				return a;
			} else {
				System.out.println("Wrong QRcode ID.");
			}
		}

	}
}

public class SysMain {
	public static void main(String[] args) {
		Controller c = new Controller();
		c.requestQRcode();
	}
}