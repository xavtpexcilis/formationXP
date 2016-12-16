package fr.excilys.formation.bowliwood;

import java.util.Scanner;

/**
 * @author user
 *
 */
public class BoardCount {
    private final int nbJoueur;
    static final int TOURS = 10;
    static final int QUILLES = 10;
    private Integer[][] val;
    private final Player[] tabJoueur;

    /**
     * @param nbJoueur
     */
    BoardCount(int nbJoueur) {
	this.nbJoueur = nbJoueur;
	this.val = new Integer[2 * nbJoueur][2 * TOURS + 2];
	this.tabJoueur = new Player[nbJoueur];
	for (int i = 0; i < nbJoueur; i++) {
	    int j = i + 1;
	    this.tabJoueur[i] = new Player("Joueur" + j, j, new char[TOURS + 1], 0);
	} // nb_tour+1 11ème stricke + total; // total
    }

    /**
     * @return
     */
    public int getNbJoueurs() {
	return this.nbJoueur;
    }

    /**
     * @return
     */
    public Player[] getTabJoueur() {
	return this.tabJoueur;
    }

    public Player getJoueur(final int i) {
	return this.tabJoueur[i];
    }

    /*
     * public void setTabJoueur(Player[] joueur) { this.tabJoueur = joueur; }
     */

    /**
     * @param numLancer
     * @param maxQuille
     * @return
     */
    public int lancerNum(final int numLancer, final int maxQuille) {
	int val = 0;
	Scanner sc = new Scanner(System.in);
	System.out.println("Entrer résultat lancer " + numLancer + " :");
	//pour utiliser les random et jouer une partie complète commenter de ici...
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
	//jusque ici
	//décommenter la ligne ci-dessous.
	// val = (int) (Math.random() * (maxQuille + 1));
	System.out.println(val);
	// do not close the scanner sc.
	// sc.close();
	return val;
    }

    /**
     * @param tour
     * @param joueurTab
     * @return
     */
    public int typeResultat(final int tour, final Player joueurTab) {
	System.out.println(joueurTab.getPseudo() + " à vous de jouer!");
	int val = lancerNum(1, QUILLES);
	int val2 = 0;
	int result;
	this.val[(2 * joueurTab.getNum() - 2)][2 * tour - 2] = val;
	if (val == QUILLES) {
	    System.out.println("Strike!");
	    joueurTab.setValTab(tour, 'x');
	    this.val[(2 * joueurTab.getNum() - 2)][2 * tour - 1] = 0;
	    this.val[(2 * joueurTab.getNum() - 1)][2 * tour - 1] = 2;
	} else {
	    int inter = QUILLES - val;
	    val2 = lancerNum(2, inter);
	    this.val[(2 * joueurTab.getNum() - 2)][2 * tour - 1] = val2;
	    if (val2 == inter) {
		System.out.println("Spare!");
		joueurTab.setValTab(tour, '/');
		this.val[(2 * joueurTab.getNum() - 1)][2 * tour - 1] = 1;
	    } else {
		joueurTab.setValTab(tour, 'n');
		this.val[(2 * joueurTab.getNum() - 1)][2 * tour - 1] = 0;
	    }
	}
	result = val + val2;
	return result;
    }

    /**
     * @param joueurTab
     */
    public void tourOnze(final Player joueurTab) {
	int val = 0;
	if (joueurTab.getValTab(TOURS) == 'x' || joueurTab.getValTab(TOURS) == '/') {
	    System.out.println(joueurTab.getPseudo() + " à vous de jouer pour un troisième lancer BONUS!");
	    val = lancerNum(1, QUILLES);
	    this.val[(2 * joueurTab.getNum() - 2)][2 * TOURS] = val;
	}
	System.out.println("Tir Bonus Tour 11 " + this.val[(2 * joueurTab.getNum() - 2)][2 * TOURS]);
	if (this.val[(2 * joueurTab.getNum() - 2)][2 * TOURS] != null) {
	    joueurTab.setScore(joueurTab.getScore() + val);
	}
    }

    /**
     * 
     */
    public void fullGame() {
	Player winner = this.tabJoueur[0];
	for (int i = 1; i <= TOURS; i++) {
	    System.out.println();
	    System.out.println();
	    System.out.println("Tour " + i + " :");
	    for (int j = 0; j < this.nbJoueur; j++) {
		int k = typeResultat(i, this.tabJoueur[j]);
		if (i == TOURS) {
		    tourOnze(this.tabJoueur[j]);
		}
		this.tabJoueur[j].setScore(this.tabJoueur[j].getScore() + k);
		System.out.println(this.tabJoueur[j].getPseudo() + " a " + this.tabJoueur[j].getScore() + " pts.");
		if (j > 0) {
		    if (this.tabJoueur[j].getScore() > winner.getScore()) {
			winner = this.tabJoueur[j];
			System.out.println(this.tabJoueur[j].getPseudo() + " mène!");
		    } else {
			System.out.println(winner.getPseudo() + " mène!");
		    }
		}
	    }
	}
	calcBonus();

	System.out.println(winner.getPseudo() + " gagne la partie avec " + winner.getScore() + " pts!");
    }

    /**
     * 
     */
    public void calcBonus() {
	int sommeBonus = 0;
	Player winner = this.tabJoueur[0];
	for (int j = 0; j < this.nbJoueur; j++) {
	    for (int i = 1; i <= TOURS - 1; i++) {
		switch (this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i - 1]) {
		case 2:
		    if (this.tabJoueur[j].getValTab(i + 1) == 'x') {
			this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i
				- 2] = (this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * (i + 1) - 2]
					+ this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * (i + 2) - 2]);
		    } else {
			this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i
				- 2] = (this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * (i + 1) - 2]
					+ this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * (i + 1) - 1]);
		    }
		    sommeBonus = sommeBonus + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i - 2];
		    System.out.println(
			    "CalcBonus tours " + i + " =" + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i - 2]
				    + " joueur " + this.tabJoueur[j].getNum());
		    break;
		case 1:
		    this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i
			    - 2] = this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * (i + 1) - 2];
		    sommeBonus = sommeBonus + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i - 2];
		    System.out.println(
			    "CalcBonus tours " + i + " =" + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * i - 2]
				    + " joueur " + this.tabJoueur[j].getNum());
		    break;
		case 0:
		    break;
		default:
		}
		if (this.tabJoueur[j].getScore() > winner.getScore()) {
		    winner = this.tabJoueur[j];
		    System.out.println(this.tabJoueur[j].getPseudo() + " mène!");
		} else {
		    System.out.println(winner.getPseudo() + " mène!");
		}
	    }
	    if (this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS - 1] == 2
		    || this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS - 1] == 1) {
		this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS
			- 2] = this.val[(2 * this.tabJoueur[j].getNum() - 2)][2 * TOURS];
		System.out.println(sommeBonus);
		sommeBonus = sommeBonus + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS - 2];
		System.out.println(
			"Strike:Spare tour 10 : " + this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS - 2]);
	    }
	    this.tabJoueur[j].setScore(this.tabJoueur[j].getScore() + sommeBonus);
	    sommeBonus = 0;
	    this.val[(2 * this.tabJoueur[j].getNum() - 1)][2 * TOURS] = this.tabJoueur[j].getScore();
	}
    }

    /**
     * 
     */
    public void print() {
	System.out.println(this.nbJoueur);
	System.out.println();
	System.out.println();
	System.out.print("Scores finaux ");
	System.out.print("");
	System.out.print("");
	/*
	 * for(int k = 1; k<=TOURS; k++){ System.out.print(" T"+k);
	 * System.out.print(""); }
	 */
	for (int j = 0; j < this.nbJoueur; j++) {
	    System.out.println();
	    System.out.print(this.tabJoueur[j].getPseudo());
	    System.out.print(" ");
	    System.out.print(" ");
	    /*
	     * for (int i = 1; i <= 2 * TOURS; i++) {
	     * System.out.print(this.val[(2 * this.tabJoueur[j].getNum() - 2)][2
	     * * i - 2]); System.out.print(" "); System.out.print(this.val[(2 *
	     * this.tabJoueur[j].getNum() - 2)][2 * i - 1]);
	     * System.out.print(" "); } System.out.println("ok jusque là");
	     * System.out.println(); for (int i = 1; i <= 2 * TOURS; i++) {
	     * System.out.print(this.val[(2 * this.tabJoueur[j].getNum() - 1)][2
	     * * i - 2]); System.out.print(" "); System.out.print(this.val[(2 *
	     * this.tabJoueur[j].getNum() - 1)][2 * i - 1]);
	     * System.out.print(" "); } System.out.println("ok jusque ici");
	     */
	    System.out.print(this.tabJoueur[j].getScore());
	}
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	final int NBJOUEURS = 6;
	BoardCount test = new BoardCount(NBJOUEURS);
	test.fullGame();
	test.print();
	/*
	 * int tour =1; System.out.println(test.TypeResultat(tour,
	 * test.tabJoueur[0])); System.out.println(test.TypeResultat(tour,
	 * test.tabJoueur[1])); System.out.println(test.getTabJoueur());
	 * System.out.println(test.val[(2*test.tabJoueur[0].getNum()-2)][2*tour-
	 * 2]);
	 * System.out.println(test.val[(2*test.tabJoueur[0].getNum()-2)][2*tour-
	 * 1]);
	 * System.out.println(test.val[(2*test.tabJoueur[1].getNum()-2)][2*tour-
	 * 2]);
	 * System.out.println(test.val[(2*test.tabJoueur[1].getNum()-2)][2*tour-
	 * 1]);
	 */
    }

}
