package com.kitri.admin.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.service.BoardAdminServiceImpl;

public class BoardTypeMakeAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setBname(request.getParameter("bname"));
		boardListDto.setCcode(Integer.parseInt(request.getParameter("ccode")));
		boardListDto.setBtype(Integer.parseInt(request.getParameter("btype")));
		BoardAdminServiceImpl.getBoardAdminService().makeBoard(boardListDto);
		return "/admin/index.jsp";
	}

}
