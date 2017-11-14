package td2.exo2;

public class Main {

	public static void main(String[] args) {
		Client A = new Client("GAIGA", "Damien", 0);
		Client B = new Client("CUNY", "Aurélie", 0);
		
		Clientele clientele = new Clientele();
		
		
		

		clientele.addClient(A);
		clientele.addClient(B);
		
		clientele.affiche();
		
		clientele.addCA(0, 999);
		clientele.addCA(1, 5000);
		
		clientele.affiche();
		
		
		Console.startConsole();
		
		
		//A = new ClientPrivilegie(A);
		
		//System.out.println(A);
	}

}
