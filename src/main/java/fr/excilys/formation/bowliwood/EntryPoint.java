package fr.excilys.formation.bowliwood;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
	int nbChoisi = 0;
	// scanner nb joueur
	Scanner sc = new Scanner(System.in);
	System.out.println("Choisir un nombre de joueurs :");
	boolean scError = true;
	while (scError) {
	    if (sc.hasNextInt()) {
		nbChoisi = sc.nextInt();
		scError = false;
	    } else {
		System.err.println("Attention, saisir un entier svp.");
		System.out.println("Choisir un nombre de joueurs :");
		sc.next();
		continue;
	    }
	}
	System.out.println("Nombre de joueur choisi : " + nbChoisi);

	//String[] listeJoueur = new String[nbChoisi];
	for (int i = 0; i < nbChoisi; i++) {
	    // Sprint 7 : personnalisation...
	    // int j=i+1;
	    // System.out.println("Choisir le nom du joueur "+ j +" :");
	    // listeJoueur[i] = sc.nextLine();

	    //listeJoueur[i] = "Joueur " + (i + 1) + " :";
	    //System.out.println(listeJoueur[i]);
	}
    }

}
