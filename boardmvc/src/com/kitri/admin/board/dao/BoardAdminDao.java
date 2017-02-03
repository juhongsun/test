package com.kitri.admin.board.dao;

import java.util.List;

import com.kitri.admin.board.model.*;

public interface BoardAdminDao {

	List<BoardListDto> getBoardList();
	List<CategoryDto> getCategory();
	int makeCategory(String cname);
	List<BoardTypeDto> getBoardType();
	int makeBoard(BoardListDto boardListDto);
	
}
