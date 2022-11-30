package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Article {
	
	private int noArticle;
	private String nom;
	private String description;
	private LocalDateTime debutEnchere;
	private LocalDateTime finEnchere;
	private int prix;
	private int categorie;
	private int idVendeur;
	private int statut;
	private Retrait retrait;
	private String pseudoVendeur;
	
	
	public int getNoArticle() {
		return noArticle;
	}
	
	public void setNoArticle(int id) {
		this.noArticle = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getDebutEnchere() {
		return debutEnchere;
	}
	
	public void setDebutEnchere(LocalDateTime debutEnchere) {
		this.debutEnchere = debutEnchere;
	}
	
	public LocalDateTime getFinEnchere() {
		return finEnchere;
	}
	
	public void setFinEnchere(LocalDateTime finEnchere) {
		this.finEnchere = finEnchere;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getCategorie() {
		return categorie;
	}
	
	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}
	
	public int getIdVendeur() {
		return idVendeur;
	}
	
	public void setIdVendeur(int idVendeur) {
		this.idVendeur = idVendeur;
	}
	
	public int getStatut() {
		return statut;
	}
	
	public void setStatut(int statut) {
		this.statut = statut;
	}
	
	public Retrait getRetrait() {
		return retrait;
	}
	
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	
	public String getPseudoVendeur() {
		return pseudoVendeur;
	}
	
	public void setPseudoVendeur(String pseudo) {
		this.pseudoVendeur = pseudo;
	}
	
	
	public Article() {
		
	}
	
	public Article(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, int prix, int categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	public Article(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, 
			int prix, int categorie, int idVendeur) {
		this(nom, description, debutEnchere, finEnchere, prix, categorie);
		this.idVendeur = idVendeur;
	}
	
	public Article(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, 
			int prix, int categorie, int idVendeur, int statut) {
		this(nom, description, debutEnchere, finEnchere, prix, categorie, idVendeur);
		this.statut = statut;
	}
	
	public Article(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, 
					int prix, int categorie, int idVendeur, int statut, Retrait retrait) {
		this(nom, description, debutEnchere, finEnchere, prix, categorie, idVendeur, statut);
		this.retrait = retrait;
	}

	
	
}
