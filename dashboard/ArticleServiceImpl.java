package com.squapl.sa.service.UserServiceImpl;


import java.security.Principal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.squapl.sa.domain.Article;
import com.squapl.sa.jparepository.ArticleRepository;
import com.squapl.sa.service.ArticleService;
import com.squapl.sa.service.UserService;



@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);
	 
	@Autowired
	private ArticleRepository articleDao;
	
	 public List<Article> findArticleList(Principal principal) {
	        List<Article> articleList = articleDao.findAll();
	        
	        return articleList;
	    }

	    public Article saveArticle(Article article) {
	        System.out.println("article saved :"+article.getTitle());
	        return articleDao.save(article);
	    }

	    public Article findArticleByTitle(String articleTitle) {
	        return articleDao.findByTitle(articleTitle);
	    }

	    public void deleteArticleByTitle(String articleTitle) {
	        articleDao.deleteByTitle(articleTitle);
	    }
	    
	   
		@Override
		public boolean checkArticleExists(String title) {
			if(null != findArticleByTitle(title)){
				System.out.println("title exists" + title);
				return true;
			}
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Article createArticle(Article article) {
			return articleDao.save(article);
		}

		@Override
		public List<Article> findAll() {
List<Article> articleList = articleDao.findAll();
	        
	        return articleList;
		}
		
		@Override
		public List<Article> findByStatus(String status) {

			List <Article> article = articleDao.findByStatus(status);
			return article;
		}

		@Override
		public List<Article> findArticleList() {
List<Article> articleList = articleDao.findAll();
	        
	        return articleList;
		}

		
   
    

}
