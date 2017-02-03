<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kitri.board.model.ReboardDto, java.util.List"%>
<%@ include file="/common/public.jsp" %>
<%
List<ReboardDto> list = (List<ReboardDto>) request.getAttribute("articlelist");
if(list != null && list.size() != 0) {
	PageNavigation navigation = (PageNavigation) request.getAttribute("navigation");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>자유 게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=root%>/css/skin_purple.css" type="text/css">
<script type="text/javascript">
control = "/reboard";

function goBbsSearch() {
	if(document.searchForm.word.value == "") {
		alert("검색어를 입력하세요.");
		return;
	} else {
		document.searchForm.action = root + control;
		document.searchForm.submit();
	}
}

function goMyList(myid) {
	document.commonform.act.value = "listarticle";
	document.commonform.pg.value = 1;
	document.commonform.key.value = "id";
	document.commonform.word.value = myid;
	document.commonform.action = root + control;
	document.commonform.submit();
}
</script>
</head>

<body>
<!-- title start -->
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
<!-- title end -->

<!-- bbs start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr valign="bottom">
		<td nowrap><a href="javascript:moveWrite();"><img
			src="<%=root%>/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"></a></td>

		<td width="100%" style="padding-left: 6px" valign="bottom">새글 <b><font
			class="text_acc_02"><%=navigation.getNewArticleCount()%></font></b> / 전체 <font
			class="text_acc_02"><%=navigation.getTotalArticleCount()%></font></td>
		<td width="300" nowrap>
		<div align="right"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
</table>

<form name="listForm" method="post" style="margin: 0px">
<table width="100%" cellpadding="5" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="2" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr class="bg_board_title" align="center" height="28">
		<td nowrap><b>번호</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td></td>
		<td width="100%"><b>제목</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="120" nowrap><b>글쓴이</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td nowrap><b>조회</b></td>
		<td nowrap class="board_bar" style="padding: 0px">|</td>
		<td width="45" nowrap><b>날짜</b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>

	<!-- 공지기능 적용시 -->


	<!-- 공지기능 적용끝  -->
<%
for(ReboardDto reboardDto : list) {
%>
	<tr>
		<td align="center" class="text_gray"><%=reboardDto.getSeq()%></td>
		<td></td>
		<td nowrap class="onetext" style="padding-right: 5px"></td>
		<!--td>
     
     </td-->
		<td style="word-break: break-all;"><a href="javascript:viewArticle('<%=reboardDto.getSeq()%>');"
			class="link_board_03">
			<img src="<%=root%>/img/board/blank.gif" width="<%=reboardDto.getLev() * 10%>" height="1">
			<%=reboardDto.getSubject()%></a></td>
		<td></td>
		<td style="word-break: break-all;"><a href="javascript:;"
			onClick="showSideView();" class="link_board_04"><%=reboardDto.getName()%></a></td>
		<td></td>
		<td align="center" class="text_gray"><%=reboardDto.getHit()%></td>
		<td></td>
		<td align="center" class="text_gray"><%=reboardDto.getLogtime()%></td>
	</tr>

	<tr>
		<td bgcolor="#ededed" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
<%
}
%>

	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>
</form>
<!-- bbs end -->

<!-- 하단 페이징 -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="5"></td>
	</tr>
	<tr valign="top">
		<td nowrap><a href="javascript:moveWrite();"><img
			src="<%=root%>/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기"></a></td>
		<td width="100%" align="center"><!--PAGE--> <%=navigation.getNavigator()%></td>
		<td nowrap class="stext"><b><%=navigation.getPageNo()%></b> / <%=navigation.getTotalPageCount()%>
		pages</td>
	</tr>
</table>
<br>
<!-- 하단 페이징 -->

<!-- 검색 영역-->
<form name="searchForm" method="get" action="" style="margin: 0px">
<input type="hidden" name="act" value="listarticle">
<input type="hidden" name="pg" value="1">
<input type="hidden" name="bcode" value="<%=bcode%>">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="3" height="10"></td>
	</tr>
	<tr>
		<td width="50%"></td>
		<td nowrap><select name="key" class="inp">
			<option value="subject">글제목
			<option value="name">글쓴이
			<option value="seq">글번호
		</select>
		<span id="sear1"> <input type="text" name="word" size="22"
			class="inp" style="margin-top: -19px;"> </span>
		<a href="javascript:goBbsSearch();"><img
			src="<%=root%>/img/board/sbtn_s.gif" width="32" height="18"
			border="0" align="absmiddle" alt="검색"></a>
<%
if(memberDto != null) {
%>			
		<a href="javascript:goMyList('<%=memberDto.getId()%>');">
		<img src="<%=root%>/img/board/sbtn_mytext.gif" width="82" height="20"
			align="absmiddle" alt="내가 쓴 글 보기"></a><br>
<%
}
%>
		</td>
		<td width="50%" align="right"><a href="#"><img
			src="<%=root%>/img/board/sbtn_top.gif" width="24" height="11"
			align="absmiddle" alt="TOP"></a><br>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%
} else {
%>
<script>
alert("글이 삭제되었거나 존재하지 않는 글번호입니다.");
//document.location.href = "<%=root%>/index.jsp";
control = "/reboard";
firstListArticle();
</script>
<%
}
%>
