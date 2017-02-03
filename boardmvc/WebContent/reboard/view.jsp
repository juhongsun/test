<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kitri.board.model.ReboardDto"%>
<%@ include file="/common/public.jsp" %>
<%
ReboardDto reboardDto = (ReboardDto) request.getAttribute("article");
if(reboardDto != null) {
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>글보기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=root%>/css/skin_purple.css" type="text/css">
<script type="text/javascript">
control = "/reboard";
function moveReply(seq) {
	document.getElementById("act").value = "mvreply";
	document.getElementById("pg").value = "<%=pg%>";
	document.getElementById("key").value = "<%=key%>";
	document.getElementById("word").value = "<%=word%>";
	document.getElementById("seq").value = seq;
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}
function moveModify(seq) {
	document.getElementById("act").value = "mvmodify";
	document.getElementById("pg").value = "<%=pg%>";
	document.getElementById("key").value = "<%=key%>";
	document.getElementById("word").value = "<%=word%>";
	document.getElementById("seq").value = seq;
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}
function deleteArticle(seq) {
	if(confirm("정말로 삭제하시겠습니까??")) {
		document.getElementById("deleteform").action = root + control;
		document.getElementById("deleteform").submit();
	}
}
</script>
</head>

<body>
<!-- title -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="<%=root%>/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<form name="deleteform" id="deleteform" method="post" action="">
	<input type="hidden" name="act" value="deletearticle">
	<input type="hidden" name="bcode" value="<%=bcode%>">
	<input type="hidden" name="pg" value="<%=pg%>">
	<input type="hidden" name="key" value="<%=key%>">
	<input type="hidden" name="word" value="<%=word%>">
	<input type="hidden" name="seq" value="<%=reboardDto.getSeq()%>">
	<input type="hidden" name="reply" value="<%=reboardDto.getReply()%>">
	<input type="hidden" name="pseq" value="<%=reboardDto.getPseq()%>">
	<tr>
		<td valign="bottom" nowrap>
			<a href="javascript:moveWrite();"><img
				src="<%=root%>/img/board/btn_write_01.gif" width="64" height="22"
				border="0" align="absmiddle" alt="글쓰기"></a>
			<a href="javascript:moveReply('<%=reboardDto.getSeq()%>');"><img
				src="<%=root%>/img/board/btn_reply.gif" width="40" height="22"
				border="0" align="absmiddle" alt="답글"></a>
<%
if(memberDto.getId().equals(reboardDto.getId())) {
%>				
			<a href="javascript:moveModify('<%=reboardDto.getSeq()%>');"><img
				src="<%=root%>/img/board/btn_modify.gif"
				border="0" align="absmiddle" alt="글수정"></a>
			<a href="javascript:deleteArticle('<%=reboardDto.getSeq()%>');"><img
				src="<%=root%>/img/board/btn_delete.gif"
				border="0" align="absmiddle" alt="글삭제"></a>
<%
}
%>		
		</td>
		<td valign="bottom" width="100%" style="padding-left: 4px"></td>
		<td align="right" nowrap valign="bottom"><a
			href="javascript:firstListArticle();">최신목록</a> <font color="#c5c5c5">|</font>
		<a href="javascript:listArticle('<%=pg%>');">목록</a> <font color="#c5c5c5">|</font>

		<a href="javascript:goBbsRead();"><img
			src="<%=root%>/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="<%=root%>/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="5" cellspacing="0" width="100%">
	<tr>
		<td class="bg_board_title_02" colspan="2" height="2"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="28">
		<td class="bg_board_title" colspan="2" style="padding-left: 14px">
		<b><font class="text"> <%=reboardDto.getSubject()%> </font></b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="26">
		<td width="100%" style="padding-left: 14px"><font class="stext">번호
		:</font> <font class="text_commentnum"><%=reboardDto.getSeq()%></font> &nbsp; <font
			class="stext">글쓴이 :</font> <a href="javascript:;"
			onClick="showSideView();" class="link_board_02"><%=reboardDto.getName()%></a><br>
		</td>
		<td style="padding-right: 14px" nowrap class="stext">조회 : <font
			class="text_commentnum"><%=reboardDto.getHit()%></font> &nbsp; 스크랩 : <font
			class="text_commentnum">0</font> &nbsp; 날짜 : <font
			class="text_commentnum"><%=reboardDto.getLogtime()%></font></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="15" cellspacing="0" width="100%">
	<tr valign="top">
		<td bgcolor="#ffffff" width="100%" class="text"
			style="padding-bottom: 8px; line-height: 1.3" id="clix_content">



		<P><%=reboardDto.getContent()%></P>



		</td>
		<td nowrap valign="top" align="right" style="padding-left: 0px">

		</td>
	</tr>
</table>

<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<!-- 하단 페이징 -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
	<tr valign="top">
		<td nowrap><a href="javascript:moveWrite();"><img
			src="<%=root%>/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"></a> <a
			href="javascript:moveReply('<%=reboardDto.getSeq()%>');"><img
			src="<%=root%>/img/board/btn_reply.gif" width="40" height="22"
			border="0" align="absmiddle" alt="답글"></a></td>
		<td style="padding-left: 4px" width="100%"><a href=""
			target="new"><img src="<%=root%>/img/board/btn_print.gif"
			width="30" height="18" border="0" align="absmiddle" alt="인쇄"></a></td>

		<td align="right" nowrap><a href="javascript:firstListArticle();">최신목록</a>
		<font color="#c5c5c5">|</font> <a href="javascript:listArticle('<%=pg%>');">목록</a>
		<font color="#c5c5c5">|</font> <a href="javascript:goBbsRead();"><img
			src="<%=root%>/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="<%=root%>/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
</table>
<br>
</body>
</html>
<%
} else {
%>
<script>
alert("글이 삭제되었거나 존재하지 않는 글번호입니다.");
//document.location.href = "<%=root%>/index.jsp";
firstListArticle();
</script>
<%
}
%>