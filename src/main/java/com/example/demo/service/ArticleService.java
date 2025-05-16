package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public int writeArticle(String title, String content) {
		return this.articleDao.writeArticle(title, content);
	}

	public List<Article> getArticles() {
		return this.articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}



	public void deleteArticle(int id) {
		this.articleDao.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String content) {
		this.articleDao.modifyArticle(title, content);
		
	}
}



