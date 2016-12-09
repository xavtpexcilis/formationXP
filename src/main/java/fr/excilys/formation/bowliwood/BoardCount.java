package fr.excilys.formation.bowliwood;

import java.util.Scanner;

public class BoardCount {
    private int nbJoueur;
    static final int TOURS = 10;
    static final int QUILLES = 10;
    private Integer[][] val;
    Player[] tabJoueur;

    BoardCount(int nbJoueur, int nbTour) {
	this.nbJoueur = nbJoueur;
	this.val = new Integer[(2 * this.nbJoueur) - 1][2 * TOURS + 1];
	for (int i = 0; i < nbJoueur; i++) {
	    int j = i + 1;
	    tabJoueur[i] = new Player("Joueur" + j, j, new char[TOURS]);
	}
	// nb_tour+1 11ème stricke + total; // total
    }

    BoardCount(int nbJoueur) {
	this.nbJoueur = nbJoueur;
	this.val = new Integer[(2 * this.nbJoueur) - 1][2 * TOURS + 1];
    }

    public int LancerNum(int numLancer, int maxQuille) {
	int val = 0;
	Scanner sc = new Scanner(System.in);
	System.out.println("Entrer résultat lancer " + numLancer + " :");

	boolean scError = true;
	while (scError) {
	    if (sc.hasNextInt()) {
		val = sc.nextInt();
		scError = false;
		if (val < 0 || val > maxQuille) {
		    scError = true;
		    System.err.println("Attention, saisir un entier entre 0 et " + maxQuille + " svp.");
		    System.out.println("Entrer résultat lancer " + numLancer + " :");
		}
	    } else {
		System.err.println("Attention, saisir un entier entre 0 et " + maxQuille + " svp.");
		System.out.println("Entrer résultat lancer " + numLancer + " :");
		sc.next();
		continue;
	    }
	}
	return val;
    }

    public int TypeResultat(int tour, Player joueurTab) {
	int val = LancerNum(1, QUILLES);
	int val2 = 0;
	int result;
	if (val == QUILLES) {
	    System.out.println("Strike!");
	    joueurTab.setValTab(tour, 'x');
	    //this.val[(2*(joueurTab.getNum()+1))]
	} else {
	    int inter = QUILLES - val;
	    val2 = LancerNum(2, inter);
	    if (val2 == inter) {
		System.out.println("Spare!");
		joueurTab.setValTab(tour, '/');
	    } else {
		joueurTab.setValTab(tour, 'n');
	    }
	}
	result = val + val2;
	return result;
    }

    public void CalcPts(int tour, Player joueurTab) {
	int val = TypeResultat(tour, joueurTab);
	if (tour > 1) {
	    switch (joueurTab.getValTab(tour - 2)) {
	    case 'x':
		int res = this.val[(2*(joueurTab.getNum()+1))-1][2*(tour-2+1)-1];
		
		break;
	    case '/':
		break;
	    case 'n':
		break;
	    default:
		break;
	    }
	}

    }

    public static void main(String[] args) {
	// BoardCount test = new BoardCount(2);
	// test.CalcPts();
    }

}
