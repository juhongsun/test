<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.util.*, com.kitri.member.model.MemberDto"%>
<%
String root = request.getContextPath();

int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
int pg = NumberCheck.nullToOne(request.getParameter("pg"));
String key = StringCheck.nulltoBlank(request.getParameter("key"));
String word = Encoder.isoToUtf(request.getParameter("word"));

MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
%>
<script type="text/javascript" src="<%=root%>/js/board.js"></script>
<form name="commonform" id="commonform" method="get" action="">
	<input type="hidden" name="act" id="act" value="">
	<input type="hidden" name="bcode" id="bcode" value="<%=bcode%>">
	<input type="hidden" name="pg" id="pg" value="<%=pg%>">
	<input type="hidden" name="key" id="key" value="<%=key%>">
	<input type="hidden" name="word" id="word" value="<%=word%>">
	<input type="hidden" name="seq" id="seq" value="">
</form>