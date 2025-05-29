
package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.dto.Reply;

@Service
public class ReplyService {

	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void writeReply(int memberId, int relId, String relTypeCode, String content) {
		this.replyDao.writeReply(memberId, relId, relTypeCode, content);
		
	}

	public List<Reply> getReplies(String relTypeCode, int relId) {
		return this.replyDao.getReplies(relTypeCode, relId);
	}

//	public void writeArticle(String title, String content, int loginedMemberId, int boardId) {
//		this.articleDao.writeArticle(title, content, loginedMemberId, boardId);
//	}
//
//	public List<Article> getArticles(int boardId, int articlesInPage, int limitFrom, String searchType, String searchKeyword) {
//		return this.articleDao.getArticles(boardId, articlesInPage, limitFrom, searchType, searchKeyword);
//	}
//
//	public Article getArticleById(int id) {
//		return this.articleDao.getArticleById(id);
//	}
//
//	public void modifyArticle(int id, String title, String content) {
//		this.articleDao.modifyArticle(id, title, content);
//	}
//
//	public void deleteArticle(int id) {
//		this.articleDao.deleteArticle(id);
//	}
//
//	public int getLastArticleId() {
//		return this.articleDao.getLastArticleId();
//	}
//
//	public int getArticlesCnt(int boardId, String searchType, String searchKeyword) {
//		return this.articleDao.getArticlesCnt(boardId, searchType, searchKeyword);
//	}
//
//	public void increaseViews(int id) {
//		this.articleDao.increaseViews(id);
//	}
}
