

package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.Board;

@Mapper
public interface BoardDao {

	@Select("""
			SELECT *
			FROM board
			WHERE id =#{boardId}
			""")

	public Board getBoard(int boardId);
	
	
}
