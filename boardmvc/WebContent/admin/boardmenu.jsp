<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.kitri.admin.board.model.BoardListDto"%>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$("#secondpane p.menu_head").click(function()
		    {
			     $(this).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		         $(this).siblings();
			});
});
</script>
<style type="text/css">
body {
	margin: 10px auto;
	font: 75%/120% Verdana,Arial, Helvetica, sans-serif;
}
.menu_list {	
	width: 300px;
}
.menu_head {
	padding: 5px 10px;
	cursor: pointer;
	position: relative;
	margin:1px;
    font-weight:bold;
    text-align: left;
    background-color: #cccccc;
}
.menu_body {
	display:none;
	text-align: left
}
.menu_body a{
  display:block;
  color:#006699;
  background-color:#EFEFEF;
  padding-left:10px;
  font-weight:bold;
  text-decoration:none;
}
.menu_body a:hover{
  color: #000000;
  text-decoration:underline;
  }
</style>
<%
String root = request.getContextPath();

List<BoardListDto> list = (List<BoardListDto>) application.getAttribute("boardmenu");
%>
<center>
<div align="right">
<a href="<%=root%>/boardadmin?act=mvadmin">관리자</a>
</div>
<div class="menu_list" id="secondpane">
<%
int size = list.size();
int tmp = 0;
for(int i=0;i<size;i++) {
	BoardListDto boardListDto = list.get(i);
	if(tmp != boardListDto.getCcode()) {
		tmp = boardListDto.getCcode();
%>
	<p class="menu_head"><%=boardListDto.getCname()%></p>
		<div class="menu_body">
<%
	}
%>
	&nbsp;&nbsp;&nbsp;
	<a href="<%=root%>/<%=boardListDto.getController()%>?act=mvwrite&pg=1&bcode=<%=boardListDto.getBcode()%>&key=&word=">
	<img src="<%=root%>/img/board/ico-m-<%=boardListDto.getBtype()%>.gif">
	<%=boardListDto.getBname()%></a>
<%
	if(i < size - 1) {
		if(list.get(i + 1).getCcode() != tmp) {
%>	
	</div>
<%
		}
	}
}
%>
</div>
</center>