package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatBLL {
	// codes dispo entre 20000 et 29999
	

public static final int IDENTIFIANT_KO = 20000;

public static final int EMAIL_INVALID = 20001;

public static final int PSEUDO_INVALID = 20002;

public static final int EMAIL_KO = 20003;

public static final int PSEUDO_KO = 20004;



//-- Erreurs liées aux articles - 20100 à 20199 --

	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_NOM_ERREUR = 20100;
	
	/**
	 * Echec la description de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR = 20101;
	
	/**
	 * Echec la date de début d'enchère est antérieure à ce jour
	 */
	public static final int REGLE_ARTICLE_DATE_DEBUT_ENCHERE_ERREUR = 20102;
	
	/**
	 * Echec la date de fin d'enchère est antérieure à la date de début de l'enchère
	 */
	public static final int REGLE_ARTICLE_DATE_FIN_ENCHERE_ERREUR = 20103;
	
	/**
	 * Echec l'identifiant du vendeur est null
	 */
	public static final int REGLE_ARTICLE_ID_VENDEUR_ERREUR = 20104;
	
	/**
	 * Echec la rue ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_RUE_ERREUR = 20105;
	
	/**
	 * Echec le code postal ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_CODE_POSTAL_ERREUR = 20106;
	
	/**
	 * Echec la ville ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_VILLE_ERREUR = 20107;
}












