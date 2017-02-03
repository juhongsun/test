var root = "/boardmvc";
var control;

function moveWrite() {
	document.getElementById("act").value = "mvwrite";
	document.getElementById("pg").value = 1;
	document.getElementById("key").value = "";
	document.getElementById("word").value = "";
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}

function viewArticle(seq) {
	document.getElementById("act").value = "viewarticle";
	document.getElementById("seq").value = seq;
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}

function listArticle(pg) {
	document.getElementById("act").value = "listarticle";
	document.getElementById("pg").value = pg;
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}

function firstListArticle() {
	document.getElementById("act").value = "listarticle";
	document.getElementById("pg").value = 1;
	document.getElementById("key").value = "";
	document.getElementById("word").value = "";
	document.getElementById("commonform").action = root + control;
	document.getElementById("commonform").submit();
}