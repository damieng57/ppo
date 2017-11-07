package td1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

/**
 *
 * @author Damien
 */
public class Bonjour {

	public static void exercice1() {

		// Réponse à l'exercice 2 du TD1
		// L'affichage nécessite lutilisation des classes String, System et PrintStream
		// présente dans le package java.lang
		System.out.printf("Bonjour !");

	}

	public static void exercice2() throws IOException {

		// Réponse à l'exercice 2 du TD1
		// Solution avec l'utilisation de la classe Scanner
		// Avantages : Simple à mettre en oeuvre
		// Inconvénients: Nécessite Java 5 minimum
		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		// Solution avec l'utilisation des classes InputStreamReader
		// et BufferedReader
		// Avantages : Utilisable sur d'anciennes versions de Java
		// Inconvénients: Complexe, obligation de gérer les erreurs
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		// Affichage du résultat
		// Incluant la réponse à l'exercice 2 du TD1
		System.out.printf("Bonjour %s %s \n", prenom, nom);

	}

	public static void exercice3() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		// Affichage du résultat
		// Réponse à l'exercice 3 du TD1
		System.out.printf("Bonjour %s %s \n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase());

	}

	public static void exercice4() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		// Réponse à l'exercice 4 du TD1
		// Contrôle la saisie et redemande si nécessaire
		while (!prenom.equals(prenom.substring(0, 1).toUpperCase() + prenom.substring(1))) {
			prenom = sc.next();
		}

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		// Réponse à l'exercice 4 du TD1
		// Contrôle la saisie et redemande si nécessaire
		while (!nom.equals(nom.toUpperCase())) {
			nom = bf.readLine();
		}

		System.out.printf("Bonjour %s %s \n", prenom, nom);

	}

	public static void exercice5() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		System.out.println("Indiquer votre année de naissance");
		//int age = 2017 - exercice5_a();
		int age = 2017 - exercice5_b();

		System.out.printf("Bonjour %s %s, tu as %d ans.\n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase(), age);

	}

	private static int exercice5_a() throws IOException {

		// Si on entre une chaîne de caractère ou un réel, on leve l'exception NumberFormatException
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		Integer entier = Integer.valueOf(bf.readLine());

		return entier;

	}

	private static int exercice5_b() {

		// Si on entre une chaîne de caractère ou un réel, on leve l'exception InputMismatchException
		Scanner sc = new Scanner(System.in);
		Integer entier = sc.nextInt();

		return entier;

	}

	public static void exercice6() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		System.out.println("Indiquer votre année de naissance");
		//int age = 2017 - exercice6_a();
		int age = 2017 - exercice6_b();

		System.out.printf("Bonjour %s %s, tu as %d ans.\n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase(), age);

	}

	private static int exercice6_a() throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);

		// On gère l'exception NumberFormatException
		while (true) {
			try {
				Integer entier = Integer.valueOf(bf.readLine());
				return entier;
			} catch (NumberFormatException ex) {
				System.out.println(ex);
			}
		}

	} 

	private static int exercice6_b() {

		// On gère l'exception InputMismatchException
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				Integer entier = sc.nextInt();
				return entier;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}

	private static double exercice7() {

		// Prise en charge d'un réel
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				double entier = sc.nextDouble();
				return entier;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}

	public static void exercice8() throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indiquer votre prénom");
		String prenom = sc.next();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		System.out.println("Indiquer votre nom");
		String nom = bf.readLine();

		System.out.println("Indiquer votre année de naissance");
		int age = 2017 - Saisie.saisieEntier();
		//int age = 2017 - exercice5_b();

		// Attention %d implique d'avoir un entier
		System.out.printf("Bonjour %s %s, tu as %d ans.\n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase(), age);

	}

	public static void exercice9() {

		String prenom = Saisie.saisieChaine("Indiquer votre prénom");
		String nom = Saisie.saisieChaine("Indiquer votre nom");
		int age = 2017 - Saisie.saisieEntier("Indiquer votre année de naissance");

		System.out.printf("Bonjour %s %s, tu as %d ans.\n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase(), age);

	}

	public static void exercice10() {

		String prenom = Saisie.saisieChaine("Indiquer votre prénom");
		String nom = Saisie.saisieChaine("Indiquer votre nom");
		int age = 2017 - Saisie.saisieEntier("Indiquer votre année de naissance");
		
		// Afficher la date
		System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.FRANCE)));

		System.out.printf("Bonjour %s %s, tu as %d ans.\n", prenom.substring(0, 1).toUpperCase() + prenom.substring(1), nom.toUpperCase(), age);

	}

}
