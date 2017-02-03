package com.kitri.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.factory.BoardActionFactory;
import com.kitri.util.*;

@WebServlet("/album")
public class AlbumController extends HttpServlet {
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
			path = "/album/write.jsp?" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("writearticle".equals(act)) {
			path = BoardActionFactory.getAlbumWriteAction().action(request, response);
			path += "&" + queryString;
			PageMove.redirect(response, root + path);
		} else if ("".equals(act)) {

		} else if ("".equals(act)) {

		} else if ("".equals(act)) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(BoardConstance.ENCODING);
		doGet(request, response);
	}

}
