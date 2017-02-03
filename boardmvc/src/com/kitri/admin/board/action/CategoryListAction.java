package com.kitri.admin.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.model.CategoryDto;
import com.kitri.admin.board.service.BoardAdminServiceImpl;

public class CategoryListAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CategoryDto> list = BoardAdminServiceImpl.getBoardAdminService().getCategory();
		request.setAttribute("categorylist", list);
		return "/admin/makecategory.jsp";
	}

}
