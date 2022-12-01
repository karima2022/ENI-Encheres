package fr.eni.encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	public static final int SQL_EXCEPTION= 10000;
	
	public static final int INSERT_UTILISATEUR_ECHEC=10001;
	
	public static final int DELETE_UTILISATEUR_ECHEC=10002;
	
	public static final int UPDATE_UTILISATEUR_ECHEC=10003;
	
	
	// -- Erreurs liées aux articles - 10100 à 10199 --
	
		/**
		 * Echec général quand tentative d'ajouter un objet null
		 */
		public static final int INSERT_ARTICLE_NULL = 10100;
		
		/**
		 * Echec général quand erreur non gérée à l'insertion 
		 */
		public static final int INSERT_ARTICLE_ECHEC = 10101;
		
		/**
		 * Echec de la lecture d'un article
		 */
		public static final int LECTURE_ARTICLE_ECHEC = 10102;
		
		/**
		 * Article inexistant
		 */
		public static final int LECTURE_ARTICLE_INEXISTANT = 10103;
		
		/**
		 * Erreur à la suppression d'un article
		 */
		public static final int SUPPRESSION_ARTICLE_ERREUR = 10104;
		
		/**
		 * Erreur à la suppression d'un article null
		 */
		public static final int SUPPRESSION_ARTICLE_NULL = 10105;
		
		/**
		 * Echec de la lecture des articles
		 */
		public static final int LECTURE_ARTICLES_ECHEC = 10106;
}












