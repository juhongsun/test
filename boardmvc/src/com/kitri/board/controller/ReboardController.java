package com.kitri.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.*;

@WebServlet("/reboard")
public class ReboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String root = request.getContextPath();

		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = StringCheck.nulltoBlank(request.getParameter("key"));
		String word = StringCheck.nulltoBlank(request.getParameter("word"));
		
		String queryString = "bcode=" + bcode + "&pg=" + pg + "&key=" + key + "&word=" + Encoder.utfUrlFormat(word);
		System.out.println("queryString === " + queryString);

		String act = request.getParameter("act");
		String path = "/index.jsp";
		if ("mvwrite".equals(act)) {
			path = "/reboard/write.jsp?" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("writearticle".equals(act)) {
			path = BoardActionFactory.getReboardWriteAction().action(request, response);
			path += "&" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("viewarticle".equals(act)) {
			path = BoardActionFactory.getReboardViewAction().action(request, response);
			path += "?" + queryString;
			PageMove.forward(request, response, path);
		} else if ("listarticle".equals(act)) {
			path = BoardActionFactory.getReboardListAction().action(request, response);
			path += "?" + queryString;
			PageMove.forward(request, response, path);
		} else if ("mvreply".equals(act)) {
			path = BoardActionFactory.getReboardMoveReplyAction().action(request, response);
			path += "?" + queryString;
			PageMove.forward(request, response, path);
		} else if ("replyarticle".equals(act)) {
			path = BoardActionFactory.getReboardReplyAction().action(request, response);
			path += "&" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("mvmodify".equals(act)) {
			path = BoardActionFactory.getReboardMoveModifyAction().action(request, response);
			path += "?" + queryString;
			PageMove.forward(request, response, path);
		} else if ("modifyarticle".equals(act)) {
			path = BoardActionFactory.getReboardModifyAction().action(request, response);
			path += "&" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("deletearticle".equals(act)) {
			path = BoardActionFactory.getReboardDeleteAction().action(request, response);
			path += "?" + queryString;
			PageMove.forward(request, response, path);
		} else {
			PageMove.redirect(response, root + path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(BoardConstance.ENCODING);
		doGet(request, response);
	}

}
