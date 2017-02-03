package com.kitri.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.board.model.AlbumDto;
import com.kitri.member.model.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/picup")
public class PictureUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String saveDirectory;
	private int maxSize;
	private String encoding;
	
	public void init(ServletConfig config) {
		saveDirectory = config.getServletContext().getRealPath("/upload");
		System.out.println("저장경로 : " + saveDirectory);
		maxSize = 3 * 1024 * 1024;
		encoding = "UTF-8";
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String path = "/index.jsp";
		
		if(memberDto != null) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String today = df.format(new Date());
			String todayFolder = saveDirectory + File.separator + today;
			File folder = new File(todayFolder);
			if(!folder.exists())
				folder.mkdirs();
			MultipartRequest multi = new MultipartRequest(request, todayFolder, maxSize, encoding, new DefaultFileRenamePolicy());
			
			AlbumDto albumDto = new AlbumDto();
			albumDto.setId(memberDto.getId());
			albumDto.setName(memberDto.getName());
			albumDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			albumDto.setSubject(multi.getParameter("subject"));
			albumDto.setContent(multi.getParameter("content"));
			albumDto.setBcode(Integer.parseInt(multi.getParameter("bcode")));
			albumDto.setSaveFolder(today);
			albumDto.setOrignPicture(multi.getOriginalFileName("picture"));
			albumDto.setSavePicture(multi.getFilesystemName("picture"));
			albumDto.setType(0);
			int seq = 0;
			if(seq != 0) {
				path = "/reboard/writeOk.jsp?seq=" + seq;
			} else {
				path = "/reboard/writeFail.jsp";
			}	
		}
	}

}
