package com.kitri.admin.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.service.BoardAdminServiceImpl;

public class BoardMenuAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		List<BoardListDto> list = (List<BoardListDto>) application.getAttribute("boardmenu");
		if(list == null) {
			list = BoardAdminServiceImpl.getBoardAdminService().getBoardList();
			application.setAttribute("boardmenu", list);			
		}
		return "/admin/boardmenu.jsp";
	}

}
