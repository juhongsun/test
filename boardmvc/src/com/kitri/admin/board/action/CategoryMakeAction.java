package com.kitri.admin.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.service.BoardAdminServiceImpl;

public class CategoryMakeAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cname = request.getParameter("cname");
		BoardAdminServiceImpl.getBoardAdminService().makeCategory(cname);
		return "/admin/index.jsp";
	}
	
}
