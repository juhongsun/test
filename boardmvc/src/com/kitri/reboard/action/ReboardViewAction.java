package com.kitri.reboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.common.service.CommonServiceImp;

public class ReboardViewAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		CommonServiceImp.getCommonService().updateHit(seq);
		ReboardDto reboardDto = ReboardServiceImpl.getReboardService().getArticle(seq);
		request.setAttribute("article", reboardDto);
		return "/reboard/view.jsp";
	}

}
