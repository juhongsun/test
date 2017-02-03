package com.kitri.reboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;

public class ReboardReplyAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String path = "/index.jsp";
		
		if(memberDto != null) {
			ReboardDto reboardDto = new ReboardDto();
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setBcode(Integer.parseInt(request.getParameter("bcode")));
			reboardDto.setRef(Integer.parseInt(request.getParameter("ref")));
			reboardDto.setLev(Integer.parseInt(request.getParameter("lev")));
			reboardDto.setStep(Integer.parseInt(request.getParameter("step")));
			reboardDto.setPseq(Integer.parseInt(request.getParameter("pseq")));
			
			int seq = ReboardServiceImpl.getReboardService().replyArticle(reboardDto);
			if(seq != 0) {
				path = "/reboard/writeOk.jsp?seq=" + seq;
			} else {
				path = "/reboard/writeFail.jsp";
			}
		}
		return path;
	}

}
