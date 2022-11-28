package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public void insert (Article article)throws BusinessException;
	public void select (Article article )throws BusinessException;
	public List<Article> findAll()throws BusinessException;
}
