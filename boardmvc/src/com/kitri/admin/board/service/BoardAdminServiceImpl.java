package com.kitri.admin.board.service;

import java.util.List;

import com.kitri.admin.board.dao.BoardAdminDaoImpl;
import com.kitri.admin.board.model.*;

public class BoardAdminServiceImpl implements BoardAdminService {
	
	private static BoardAdminService boardAdminService;
	
	static {
		boardAdminService = new BoardAdminServiceImpl();
	}
	
	private BoardAdminServiceImpl() {}

	public static BoardAdminService getBoardAdminService() {
		return boardAdminService;
	}

	@Override
	public List<BoardListDto> getBoardList() {
		return BoardAdminDaoImpl.getBoardAdminDao().getBoardList();
	}

	@Override
	public List<CategoryDto> getCategory() {
		return BoardAdminDaoImpl.getBoardAdminDao().getCategory();
	}

	@Override
	public int makeCategory(String cname) {
		return BoardAdminDaoImpl.getBoardAdminDao().makeCategory(cname);
	}

	@Override
	public List<BoardTypeDto> getBoardType() {
		return BoardAdminDaoImpl.getBoardAdminDao().getBoardType();
	}

	@Override
	public int makeBoard(BoardListDto boardListDto) {
		return BoardAdminDaoImpl.getBoardAdminDao().makeBoard(boardListDto);
	}

}
