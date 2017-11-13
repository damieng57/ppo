package td2.exo2;

public class Main {

	public static void main(String[] args) {
		Client A = new Client("GAIGA", "Damien", 0);
		Client B = new Client("CUNY", "Aurélie", 0);

		System.out.println(A);
		System.out.println(B);
		
		A.setCaClient(10000);
		
		A = new ClientPrivilegie(A);
		
		System.out.println(A);
	}

}
