package td2.exo2;

public class Main {

	public static void main(String[] args) {
		Client A = new Client("GAIGA", "Damien", 0);
		Client B = new Client("CUNY", "Aurélie", 0);
		Client C = new Client("TOTO", "Truc", 0);
		Client D = new Client("TATA", "machin", 0);

		Clientele clientele = new Clientele();

		clientele.addClient(A);
		clientele.addClient(B);
		clientele.addClient(C);
		clientele.addClient(D);

		clientele.affiche();

		clientele.addCA(0, 999);
		clientele.addCA(1, 5000);
		clientele.addCA(2, 2000);
		clientele.addCA(3, 500);

		clientele.affiche();

		//Console.startConsole();
		//A = new ClientPrivilegie(A);
		//System.out.println(A);
	}

}
