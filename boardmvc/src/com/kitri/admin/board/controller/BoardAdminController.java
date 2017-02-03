package com.kitri.admin.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.BoardConstance;
import com.kitri.util.PageMove;

@WebServlet("/boardadmin")
public class BoardAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		String path = "/index.jsp";
		if("boardlist".equals(act)) {
			path = BoardActionFactory.getBoardMenuAction().action(request, response);
			PageMove.forward(request, response, path);
		} else if("mvadmin".equals(act)) {
			path = "/admin/index.jsp";
			PageMove.redirect(response, root + path);
		} else if("mvcategory".equals(act)) {
			path = BoardActionFactory.getCategoryListAction().action(request, response);
			PageMove.forward(request, response, path);
		} else if("makecategory".equals(act)) {
			path = BoardActionFactory.getCategoryMakeAction().action(request, response);
			PageMove.redirect(response, root + path);
		} else if("mvboard".equals(act)) {
			path = BoardActionFactory.getBoardTypeListAction().action(request, response);
			PageMove.forward(request, response, path);
		} else if("makeboard".equals(act)) {
			path = BoardActionFactory.getBoardTypeMakeAction().action(request, response);
			PageMove.redirect(response, root + path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(BoardConstance.ENCODING);
		doGet(request, response);
	}

}
