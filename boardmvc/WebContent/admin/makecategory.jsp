<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.kitri.admin.board.model.CategoryDto"%>
<%
String root = request.getContextPath();

List<CategoryDto> list = (List<CategoryDto>) request.getAttribute("categorylist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h5>기존 카테고리목록</h5>
<ul>
<%
for(CategoryDto categoryDto : list) {
%>
	<li><%=categoryDto.getCname()%></li>
<%	
}
%>
</ul>
<form method="get" action="<%=root%>/boardadmin">
<input type="hidden" name="act" value="makecategory">
카테고리이름
<input type="text" name="cname" id="cname">
<input type="submit" value="카테고리생성">
</form>
</center>
</body>
</html>