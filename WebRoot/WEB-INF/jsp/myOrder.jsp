<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理信息中心IT管理平台</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/Tstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/public.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
<script src="<%=basePath%>js/json.js" type="text/javascript"></script>
<script src="<%=basePath%>js/json-bean.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>calendar_js_css/lhgcore.js"></script>
<script type="text/javascript" src="<%=basePath %>calendar_js_css/lhgcalendar.js"></script>

</head>

<body>
<jsp:include page="topTip.jsp"/>
<!--main 开始-->
<div class="main1">
	<div class="gr_left">
		<iframe src="<%=basePath %>order/left.action" scrolling="no" allowtransparency="true" width="151" height="600" frameborder="0"></iframe>
	</div>
	<div class="gr_right p10">
		<div class="ge_btn mb10  w100 left" style=" margin-bottom:0px;">		
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="text_c gr_tab mt10 left" id="gr_tab" >
			<tr>          
				<th scope="col">&nbsp;</th>                                                                               
				<th scope="col">需求编号</th>
				<th scope="col">需求名称</th>
				<th scope="col">计划上线时间</th>
				<th scope="col">执行厂商</th>
				<th scope="col">提出人</th>
				<th scope="col">提出单位</th>
				<th scope="col">项目经理</th>
				<th scope="col">需求状态</th>	
			</tr>
			<tbody id="tb_order">
			<c:forEach items="${orderList }" var="ord">
			<tr>
				<td><input name="grgzpt" type="radio" value="${ord.id }"  class="dskg" /></td>
				<td>${ord.sysNo }</td>
				<td>${ord.orderName }</td>
				<td>${ord.date}</td>
				<td></td>
				<td>${ord.orderApplyMan }</td>
				<td>${ord.orderDept }</td>
				<td>${ord.developMan}</td>
				<td class="xmzt">${ord.status_name}</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</div>
<!--main 结束-->
<script type="text/javascript" src="<%=basePath %>js/public.js"></script>
<!--提示弹出层 开始-->
	<div id="cover1"></div>		
	<!--保存需求 弹出框 开始-->
	<div class="pop_410" id="pop_xfcs">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_xfcs');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定要下发给厂商吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_xfcs');"><a class="hand"  onclick="show('cover1','pop_msxf');">下发厂商</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_xfcs');"><a href="#">取 消</a></span>
			</div>
		</div>
	</div>
	<!--保存需求 弹出框 结束-->
	
	<!--取消需求 弹出框 开始-->
	<div class="pop_410" id="pop_qxxq">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_qxxq');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定要取消该需求吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_qxxq');"><a class="hand" >确定取消</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_qxxq');"><a href="#">暂不取消</a></span>
			</div>
		</div>
	</div>
	<!--取消需求 弹出框 结束-->
	
	<!--提交上线申请 弹出框 开始-->
	<div class="pop_410" id="pop_sxsq">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_sxsq');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定要提交上线申请吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_sxsq'); show('cover1','pop_qrcg');"><a class="hand" >提交申请</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_sxsq');"><a href="#">取消</a></span>
			</div>
		</div>
	</div>
	<!--提交上线申请 弹出框 结束-->
	
	<!--下发成功 弹出框 开始-->
	<div class="pop_410" id="pop_msxf">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_msxf');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">下发成功！</div>
			<div class="mt20 mb10" style="margin-left:170px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_msxf');"><a href="#">确认</a></span>
			</div>
		</div>
	</div>
	<!--下发成功 弹出框 结束-->
	
	<!--确认成功 弹出框 开始-->
	<div class="pop_410" id="pop_qrcg">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_qrcg');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">提交成功！</div>
			<div class="mt20 mb10" style="margin-left:170px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_qrcg');"><a href="#">确认</a></span>
			</div>
		</div>
	</div>
	<!--确认成功 弹出框 结束-->
	
	<!--需求确认 弹出框 开始-->
	<div class="pop_410" id="pop_xqqr">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_xqqr');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定提交需求确认吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_xqqr'); show('cover1','pop_qrcg')"><a class="hand" >提交确认</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_xqqr');"><a href="#">取 消</a></span>
			</div>
		</div>
	</div>
	<!--需求确认 弹出框 结束-->
	
	<!--提交审核 弹出框 开始-->
	<div class="pop_410" id="pop_sh">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_sh');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定要提交审核吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_sh'); show('cover1','pop_qrcg')"><a class="hand" >提交确认</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_sh');"><a href="#">取 消</a></span>
			</div>
		</div>
	</div>
	<!--提交审核 弹出框 结束-->
	
	<!--提交验收 弹出框 开始-->
	<div class="pop_410" id="pop_ys">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_ys');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">确定要提交需求经理验收吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_ys'); show('cover1','pop_qrcg')"><a class="hand" >提交验收</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_ys');"><a href="#">取 消</a></span>
			</div>
		</div>
	</div>
	<!--提交验收 弹出框 结束-->
	
	<!--申请终止需求 弹出框 开始-->
	<div class="pop_410" id="pop_sqzzxq">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_sqzzxq');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div>
				<p class="left ml20">请填写终止需求理由：</p>
				<textarea name="" cols="" rows="" class="input90" style="height:80px;"></textarea>
			</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_sqzzxq');"><a class="hand" >提交审核</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_sqzzxq');"><a href="#">暂不终止</a></span>
			</div>
		</div>
	</div>
	<!--申请终止需求 弹出框 结束-->
	
<!--提示弹出层 结束-->
</body>
</html>