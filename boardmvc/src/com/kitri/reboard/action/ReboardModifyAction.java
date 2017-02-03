package com.kitri.reboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardModifyAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String path = "/index.jsp";
		
		if(memberDto != null) {
			ReboardDto reboardDto = new ReboardDto();
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setSeq(Integer.parseInt(request.getParameter("seq")));
			
			int seq = ReboardServiceImpl.getReboardService().modifyArticle(reboardDto);
			if(seq != 0) {
				path = "/reboard/writeOk.jsp?seq=" + seq;
			} else {
				path = "/reboard/writeFail.jsp";
			}
		}
		return path;
	}
	
}
