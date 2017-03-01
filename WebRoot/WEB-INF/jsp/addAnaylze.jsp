<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="<%=basePath%>js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<script type="text/javascript">
//添加需求分析
function addAnaylze(){
	//获取表单字段
	var order_id=$("#order_id").val();
	var opinion=$("#opinion").val();
	//上传文件是否有选择
	var upload=$("#upload").val();
	if(upload=="" || upload==undefined){
		alert("需求分析文档尚未选择");
		return false;
		
	}
	jQuery.ajaxFileUpload({
		url : '<%=basePath%>anaylze/add_anaylze.action?order_id='+order_id+'&opinion='+opinion,
		secureuri:false,
        fileElementId:'upload',                        //文件选择框的id属性
        dataType: 'json',             
		error: function (e){
			alert(3);//<pre></pre>
			alert(e);
		 	console.log(e); 		
			alert('上传的文件出错!');
			return;
		},
		success:function(data,status) {
			alert("1");
			if('success'==data.result){
				alert("上传成功");
				show('cover1','pop_sure');
			}else{
				if('fail'==data.result){
					alert("上传失败");
				}
				else{
					alert('上传文件出错!');
				}
			}
		}
	});	
	
}
//提交需求分析
function submitConfirm(){
	var order_id=$("#order_id").val();
	$.ajax({
		type:"POST",
		url:"<%=basePath%>anaylze/submit_confirm.action",
		async:false,
		data: {"order_id":order_id},
		dataType:"json",
		success: function(data){
			var res=data.result;
			if(res=="success"){
				show('cover1','pop_msxf');
			}
			else if(res=="fail"){
				alert("提交失败:()");
			}
		}
	});
	
}
</script>
</head>

<body>
<input type="hidden" id="order_id" name="order_id" value="${order.id }"/>
<jsp:include page="topTip.jsp"/>
<!--main 开始-->
<div class="main1">
	<div class="gr_left">
		<iframe src="<%=basePath %>order/left.action" scrolling="no" allowtransparency="true" width="151" height="600" frameborder="0"></iframe>
	</div>
	<div class="gr_right  p10">
		<div class="home_tit left red1 fontB"> <div class="btn_gr2 mt8" style="float:right"><a class="hand" href="grgzt.html">返回个人工作台</a></div><span class="ml20">需求分析</span> </div>   
		<p class="w100 left line30 orange3 font12">温馨提示：标识*为必填项，填写完信息后请提交。上传的文件类型只能为doc,docx,xls,xlsx,ppt,pptx,txt,jpg,bmp,gif,png,rar,zip格式，单个文件大小为5M以内
</p>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="grgz_tab left">
		<tr>
			<th scope="row"> <span class="orange3">*</span> 需求分析文档：</th>
			<td ><input id="upload" type="file" name="upload" class="input70"/></td>
			</tr>
		<tr>
			<th scope="row">需求分析意见/说明</th>
			<td ><textarea id="opinion" name="opinion" cols="" rows="" style="height:80px; line-height:20px;" class="input90"></textarea></td>
			</tr>
	</table>
	<div class="w100 left">
	<div class="btn_gr2 mt8" style=" width:100px;  margin:7px auto 0; float:none"><a class="hand" onclick="addAnaylze()">提交需求分析</a></div>
	</div>
	<div class="box1_tit w100 left varcx_tab mt25">
				<ul id="tabnav">
					<li class="on" onclick="nTabs(this,'0','on','');">需求基本信息</li>
				</ul>
			</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="grgz_tab left" style="border-top:0px;">
		<tr>
			<th scope="row">需求名称：</th>
			<td colspan="3">工单增加应用系统及任务类别 </td>
			</tr>
		<tr>
			<th scope="row">系统编号：</th>
			<td colspan="3">2014-0526-02847</td>
			</tr>
		<tr>
			<th scope="row">需求大小：</th>
			<td colspan="3">
				<p>  大 （90人天以上）</p>
			</td>
			</tr>
		<tr>
			<th scope="row"> OA工单号：</th>
			<td>00000603 </td>
			<th scope="row"> 归属系统：</th>
			<td>统一信息平台</td>
		</tr>
		<tr>
			<th scope="row"> 需求部门/单位：</th>
			<td>人力资源部</td>
			<th scope="row"> 需求提出人：</th>
			<td>孙玉峰</td>
		</tr>
		<tr>
			<th scope="row"> 开发负责人：</th>
			<td>李莉香</td>
			<th scope="row"> 执行厂商：</th>
			<td>炎黄新星</td>
		</tr>
		<tr>
			<th scope="row"> 需求描述：</th>
			<td colspan="3">
			工单增加应用系统及任务类别 
			</td>
			</tr>
		<tr>
			<th scope="row">相关附件：<br /><input name="" type="button" value="下载全部"  class="btn_gr1 ml10"/></th>
			<td colspan="3">
			<p>附件01.doc   <a href="#">下载</a></p>
			<p>附件02.doc   <a href="#">下载</a></p>
			<p>附件03.doc   <a href="#">下载</a></p>

			</td>
			</tr>
	</table>

</div>
<!--main 结束-->
<script type="text/javascript" src="<%=basePath %>js/public.js"></script>
<!--提示弹出层 开始-->
	<div id="cover1"></div>		
	<!--保存需求 弹出框 开始-->
	<div class="pop_410" id="pop_sure">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_sure');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">保存成功！是否马上提交需求确认？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_sure');"><a class="hand"  onclick="submitConfirm()">提交需求确认</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_sure');"><a href="#">暂不提交</a></span>
			</div>
		</div>
	</div>
	<!--保存需求 弹出框 结束-->
	
	<!--下发成功 弹出框 开始-->
	<div class="pop_410" id="pop_msxf">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_msxf');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">提交成功！</div>
			<div class="mt20 mb10" style="margin-left:170px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_msxf');"><a href="grgzt.html">确认</a></span>
			</div>
		</div>
	</div>
	<!--下发成功 弹出框 结束-->
	
<!--提示弹出层 结束-->
</body>
</html>
