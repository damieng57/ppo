/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Damien
 */
// Classe en réponse à l'exercice 8
public class Saisie {

	public static int saisieEntier() {

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

	public static float saisieReel() {

		// Prise en charge d'un réel
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				Float reel = sc.nextFloat();
				return reel;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}

	public static String saisieChaine() {

		// Prise en charge d'une chaîne
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				String chaine = sc.next();
				return chaine;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}

	public static int saisieEntier(String texte) {

		System.out.println(texte);
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

	public static float saisieReel(String texte) {

		System.out.println(texte);
		// Prise en charge d'un réel
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				Float reel = sc.nextFloat();
				return reel;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}

	public static String saisieChaine(String texte) {

		System.out.println(texte);
		// Prise en charge d'une chaîne
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				String chaine = sc.next();
				return chaine;
			} catch (InputMismatchException ex) {
				System.out.println(ex);
			}
		}

	}
}
