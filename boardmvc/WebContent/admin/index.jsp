<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>관리자페이지</h3>
<a href="<%=root%>/boardadmin?act=mvcategory">카테고리만들기</a><br>
<a href="<%=root%>/boardadmin?act=mvboard">게시판만들기</a>
</center>
</body>
</html>