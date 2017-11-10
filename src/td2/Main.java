package td2;

import td2.Exceptions.NoMoneyException;

public class Main {

	public static void main(String[] args) {
		// Générer un portefeuille
		Portefeuille porteFeuilleDePatrick = new Portefeuille();
		
		// Générer des devises
		Devise maDevise1 = new Devise(NomDevises.EURO, 50);
		Devise maDevise2 = new Devise(NomDevises.DOLLAR, 150);
		
		// Ajout des devises
		porteFeuilleDePatrick.ajouterDevise(maDevise1);
		System.out.println(maDevise1.toString()+" - Ajout de 50€ au portefeuille\n");
		porteFeuilleDePatrick.ajouterDevise(maDevise2);
		System.out.println(maDevise2.toString()+" - Ajout de 150$ au portefeuille\n");
		
		// On essaie d'ajouter un devise déjà présente
		Devise maDevise3 = new Devise(NomDevises.DOLLAR, 100);
		porteFeuilleDePatrick.ajouterDevise(maDevise3);
		System.out.println(maDevise3.toString()+" - Ajout de 100$ au portefeuille\n");
		
		// On essaie de payer plus que ce que l'on a dans le porte feuille
		System.out.println("On veut utiliser 350$ mais nous n'avons que 250$ au portefeuille");
		try {
			porteFeuilleDePatrick.sortirDeviseDuPortefeuille(NomDevises.DOLLAR, 350);
		} catch (NoMoneyException ex) {
			
		}
		
		// On ajoute pour payer
		System.out.println("On ajoute 350$ au portefeuille, nous avons maintenant 600$\n");
		porteFeuilleDePatrick.mettreDeviseDansPortefeuille(NomDevises.DOLLAR, 350);
		
		// On paye avec ce que l'on a dans le porte feuille
		System.out.println("On dépense 100$, il reste 500$ au portefeuille\n");
		try {
			porteFeuilleDePatrick.sortirDeviseDuPortefeuille(NomDevises.DOLLAR, 100);
		} catch (NoMoneyException ex) {
			
		}
		
		// On tente de payer avec une devise que l'on à pas
		System.out.println("On veut payer 200£, mais nous n'avons pas de livre dans le portefeuille\n");
		try {
			porteFeuilleDePatrick.sortirDeviseDuPortefeuille(NomDevises.LIVRE, 200);
		} catch (NoMoneyException ex) {
			System.out.println("La devise n'est pas présente au portefeuille");
		}
		
		// On recupère une devise que l'on à pas, elle s'ajoute au porte-feuille
		System.out.println("On ajoute 300£ au portefeuille\n");
		porteFeuilleDePatrick.mettreDeviseDansPortefeuille(NomDevises.LIVRE, 300);
		
		System.out.println("Nous avons maintenant dans le portefeuille\n");
		porteFeuilleDePatrick.afficher();
		
		System.out.println("Il y a donc "+porteFeuilleDePatrick.nbreDevise()+" devise(s)");
		
		System.out.println(porteFeuilleDePatrick.montantDevise(NomDevises.YUAN));

	}

}
