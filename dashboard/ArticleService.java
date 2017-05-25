package com.squapl.sa.service;

import java.security.Principal;
import java.util.List;

import com.squapl.sa.domain.Article;

public interface ArticleService {

	Article createArticle(Article article);

    List<Article> findAll();

    
  
    List<Article> findByStatus(String status);

	List<Article> findArticleList();

	boolean checkArticleExists(String title);

	Article findArticleByTitle(String articleTitle);

	List<Article> findArticleList(Principal principal);

	void deleteArticleByTitle(String articleTitle);

 //    List<Article> findByStatusLanguage(String language, String status);
    }
