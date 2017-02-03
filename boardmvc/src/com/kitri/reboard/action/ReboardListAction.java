package com.kitri.reboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.common.service.CommonServiceImp;
import com.kitri.util.*;

public class ReboardListAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = StringCheck.nulltoBlank(request.getParameter("key"));
		String word = StringCheck.nulltoBlank(request.getParameter("word"));
		List<ReboardDto> list = ReboardServiceImpl.getReboardService().listArticle(bcode, pg, key, word);
		PageNavigation navigation = CommonServiceImp.getCommonService().getPageNavigation(bcode, pg, key, word);
		navigation.setRoot(request.getContextPath());
		navigation.makeNavigator();
		
		request.setAttribute("articlelist", list);
		request.setAttribute("navigation", navigation);
		return "/reboard/list.jsp";
	}

}
