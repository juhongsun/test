<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, com.kitri.admin.board.model.CategoryDto"
    import="com.kitri.admin.board.model.BoardTypeDto"
%>
<%
String root = request.getContextPath();

List<CategoryDto> clist = (List<CategoryDto>) request.getAttribute("categorylist");
List<BoardTypeDto> btlist = (List<BoardTypeDto>) request.getAttribute("btypelist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>게시판 생성</h3>
<form method="get" action="<%=root%>/boardadmin">
<input type="hidden" name="act" value="makeboard">
<select name="ccode">
<%
for(CategoryDto categoryDto : clist) {	
%>
	<option value="<%=categoryDto.getCcode()%>"><%=categoryDto.getCname()%>
<%
}
%>
</select>
<select name="btype">
<%
for(BoardTypeDto boardTypeDto : btlist) {
%>
	<option value="<%=boardTypeDto.getBtype()%>"><%=boardTypeDto.getBtypeName()%>
<%
}
%>
</select>
<br>
게시판이름 <input type="text" name="bname">
<input type="submit" value="게시판생성">
</form>
</center>
</body>
</html>