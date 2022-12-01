package fr.eni.encheres.servlet;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	/**
	 * Format id liste course incorrect
	 */
	public static final int FORMAT_ID_LISTE_ERREUR=30000;
	/**
	 * Format id liste course incorrect
	 */
	public static final Integer NOM_ARTICLE_OBLIGATOIRE = 30001;
	public static final Integer NOM_LISTE_OBLIGATOIRE = 30002;
	public static final Integer FORMAT_ID_ARTICLE_ERREUR = 30003;
	
	
	
	// -- Erreurs liées aux articles - 30100 à 30199 --
	
	/**
	 * Format de l'id article incorrect
	 */
	public static final Integer FORMAT_NO_ARTICLE_ERREUR = 30108;
	
	/**
	 * Nom de l'article vaut null
	 */
	public static final Integer NOM_D_ARTICLE_OBLIGATOIRE = 30100;
	
	/**
	 * Description de l'article vaut null
	 */
	public static final Integer DESCRIPTION_ARTICLE_OBLIGATOIRE = 30101;
	
	/**
	 * Date début d'enchère est null
	 */
	public static final Integer DATE_DEBUT_ENCHERE_OBLIGATOIRE = 30102;
	
	/**
	 * Date fin d'enchère est null
	 */
	public static final Integer DATE_FIN_ENCHERE_OBLIGATOIRE = 30103;
	
	/**
	 * Identifiant utilisateur incorrect
	 */
	public static final Integer FORMAT_ID_USER = 30104;
	
	/**
	 * Rue vaut null
	 */
	public static final Integer RUE_OBLIGATOIRE = 30105;
	
	/**
	 * Code postal vaut null
	 */
	public static final Integer CODE_POSTAL_OBLIGATOIRE = 30106;
	
	/**
	 * Ville vaut null
	 */
	public static final Integer VILLE_OBLIGATOIRE = 30107;
	
}