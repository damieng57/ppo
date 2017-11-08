package td2;

public class Main {

	public static void main(String[] args) {
		// Générer un portefeuille
		Portefeuille porteFeuilleDePatrick = new Portefeuille();
		
		// Générer des devises
		Devise maDevise1 = new Devise(NomDevises.EURO, 50);
		Devise maDevise2 = new Devise(NomDevises.DOLLAR, 250);
		Devise maDevise3 = new Devise(NomDevises.LIVRE, 500);
		
		// Ajout des devises
		porteFeuilleDePatrick.ajouterDevise(maDevise1);
		System.out.println(maDevise1.toString()+" Ajout de la devise € au portefeuille");
		porteFeuilleDePatrick.ajouterDevise(maDevise2);
		System.out.println(maDevise2.toString()+" Ajout de la devise $ au portefeuille");
		
		// On essaie de payer plus que ce que l'on a dans le porte feuille
		porteFeuilleDePatrick.payerAvecDevise(maDevise2, 350);
		
		// On ajoute pour payer
		porteFeuilleDePatrick.encaisserAvecDevise(maDevise2, 350);
		
		// On paye avec ce que l'on a dans le porte feuille
		porteFeuilleDePatrick.payerAvecDevise(maDevise2, 100);
		
		System.out.println("************");
		porteFeuilleDePatrick.afficher();
		
		// On tente de payer avec une devise que l'on à pas
		porteFeuilleDePatrick.payerAvecDevise(maDevise3, 200);
		
		// On recupère une devise que l'on à pas, elle s'ajoute au porte-feuille
		porteFeuilleDePatrick.encaisserAvecDevise(maDevise3, 300);
		
		System.out.println("************");
		porteFeuilleDePatrick.afficher();

	}

}
