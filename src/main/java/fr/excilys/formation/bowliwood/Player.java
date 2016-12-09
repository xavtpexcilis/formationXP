package fr.excilys.formation.bowliwood;

public class Player {
    private String pseudo;
    private int num;
    public char[] tab;

    public Player(String pseudo, int num, char[] tab) {
	this.pseudo = pseudo;
	this.num = num;
	this.tab = tab;
    }
    
    public void setPseudo(String nom){
	this.pseudo = pseudo;
    }
    
    public String getPseudo(){
	return this.pseudo;
    }
    
    public void setNum(int num){
	this.num = num;
    }
    
    public int getNum(){
	return this.num;
    }
    
    public void setValTab(int i, char c){
	this.tab[i]=c;
    }
    
    public char getValTab(int i){
	return this.tab[i];
    }
    
    public void setTab(char[] tab){
	this.tab = tab;
    }
    
    public char[] getTab(){
	return this.tab;
    }
}
