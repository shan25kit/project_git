package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.Board;

@Service
public class BoardService {

	private BoardDao boardrDao;

	public BoardService(BoardDao boardrDao) {
		this.boardrDao = boardrDao;
	}
	public Board getBoard(int boardId) {
		return this.boardrDao.getBoard(boardId);
	}


}