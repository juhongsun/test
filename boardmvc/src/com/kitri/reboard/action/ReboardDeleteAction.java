package com.kitri.reboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.factory.BoardActionFactory;

public class ReboardDeleteAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int reply = Integer.parseInt(request.getParameter("reply"));
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		int delete = ReboardServiceImpl.getReboardService().deleteArticle(seq, reply, pseq);
		System.out.println(delete);
		return "/index.jsp";
	}

}
