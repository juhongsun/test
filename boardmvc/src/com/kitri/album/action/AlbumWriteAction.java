package com.kitri.album.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.AlbumDto;
import com.kitri.member.model.MemberDto;

public class AlbumWriteAction implements Action {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String path = "/index.jsp";
		
		if(memberDto != null) {
			AlbumDto albumDto = new AlbumDto();
			albumDto.setId(memberDto.getId());
			albumDto.setName(memberDto.getName());
			albumDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			albumDto.setSubject(request.getParameter("subject"));
			albumDto.setContent(request.getParameter("content"));
			albumDto.setBcode(Integer.parseInt(request.getParameter("bcode")));
			System.out.println("제목 : " + request.getParameter("subject"));
			System.out.println("사진이름 : " + request.getParameter("picture"));
			int seq = 0;
			if(seq != 0) {
				path = "/reboard/writeOk.jsp?seq=" + seq;
			} else {
				path = "/reboard/writeFail.jsp";
			}
		}
		return path;
	}

}
