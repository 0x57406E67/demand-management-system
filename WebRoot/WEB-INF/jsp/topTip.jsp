<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="home_tit">
	<ul>
		<li class="on"><a href="">个人工作台</a></li>
		<li><a href="">已归档需求</a></li>
		<li><a href="">统计分析</a></li>
	</ul>
	<div class="btn_gr2 mt8" style="float:right"><a class="hand" href="<%=basePath %>/user/main.action">返回首页</a></div>
</div>
