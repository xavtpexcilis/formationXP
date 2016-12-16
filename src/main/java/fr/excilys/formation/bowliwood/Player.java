package fr.excilys.formation.bowliwood;

/**
 * @author user
 *
 */
public class Player {
    // non final car peux être modifier lors de la personnification de la partie
    private String pseudo;
    private final int num;
    private char[] tab;
    private int score;

    /**
     * @param pseudo
     * @param num
     * @param tab
     * @param score
     */
    public Player(String pseudo, int num, char[] tab, int score) {
	this.pseudo = pseudo;
	this.num = num;
	this.tab = tab;
	this.score = score;
    }

    /**
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
    }

    /**
     * @return
     */
    public String getPseudo() {
	return this.pseudo;
    }

    /*
     * public void setNum(int num) { this.num = num; }
     */

    /**
     * @return
     */
    public int getNum() {
	return this.num;
    }

    /**
     * @param i
     * @param c
     */
    public void setValTab(int i, char c) {
	this.tab[i] = c;
    }

    /**
     * @param i
     * @return
     */
    public char getValTab(int i) {
	return this.tab[i];
    }

    /**
     * @param tab
     */
    public void setTab(char[] tab) {
	this.tab = tab;
    }

    /**
     * @return
     */
    public char[] getTab() {
	return this.tab;
    }

    /**
     * @param score
     */
    public void setScore(int score) {
	this.score = score;
    }

    /**
     * @return
     */
    public int getScore() {
	return this.score;
    }
}
