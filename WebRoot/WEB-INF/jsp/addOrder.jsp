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
<script src="<%=basePath%>js/json.js" type="text/javascript"></script>
<script src="<%=basePath%>js/json-bean.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<script  type="text/javascript">
var attach_ids="";

//添加需求工单
function addOrder(){
	//获取表单信息
	var jsonObj=$("#myForm").serializeJson();
	var jsonStr=JSON.stringify(jsonObj);
	$.ajax({
			type:"POST",
			url:"<%=basePath%>order/add_order_ajax.action",
			async:false,
			data: {"parm":jsonStr,"attach_ids":attach_ids},
			dataType:"json",
			success: function(data){
				var res=data.result;
				var order_id=data.order_id;
				if(res=="success"){
					//order_id回填
					$("#order_id").attr("value",order_id);
					show('cover1','pop_sure');
					//页面跳转
				}
				else if(res=="fail"){
					alert("保存失败:()");
				}
			}
		});
	
}

//上传附件
function uploadFile(){
	jQuery.ajaxFileUpload({
		url : '<%=basePath%>order/orderAttach.action',
		secureuri:false,
        fileElementId:'upload',                        //文件选择框的id属性
        dataType: 'json',             
		error: function (e){
		 	console.log(e); 		
			alert('上传的文件出错!');
			return;
		},
		success:function(data,status) {
			if('success'==data.result){
				alert("上传成功");
				var attach_id=data.attach_id;
				var attachName=data.attachName;
				var url=data.url;
				//修改attach_ids
				attach_ids=attach_ids+attach_id+",";
				//前端增加文件列表
				$("#div_file_list").append("<div><a href='#'>"+attachName+"</a> <span><a href='#'>删除</a></span></div>");
			}else{
				if('fail'==data.resultssss){
					alert("上传失败");
				}
				else{
					alert('上传文件出错!');
				}
			}
		}
	});	
	
}

//下发厂商
function sendCompany(){
	var order_id=$("#order_id").attr("value");
	var status="1";
	var jsonStr="{\"id\":\""+order_id+"\",\"status\":\""+status+"\"}";
	$.ajax({
			type:"POST",
			url:"<%=basePath%>order/update_order_ajax.action",
			async:false,
			data: {"parm":jsonStr},
			dataType:"json",
			success: function(data){
				var res=data.result;
				if(res=="success"){
					show('cover1','pop_msxf');
				}
				else if(res=="fail"){
					alert("保存失败:()");
				}
			}
		});	
}
</script> 
</head>

<body>
<jsp:include page="topTip.jsp"/>
<!--main 开始-->
<div class="main1">
	<div class="gr_left">
		<iframe src="<%=basePath %>order/left.action" scrolling="no" allowtransparency="true" width="151" height="600" frameborder="0"></iframe>
	</div>
	<div class="gr_right p10">
		<div class="home_tit left red1 fontB"> <div class="btn_gr2 mt8" style="float:right"><a class="hand" href="">返回个人工作台</a></div><span class="ml20">新建需求</span> </div>   
		<p class="w100 line30 orange3 font12 left">温馨提示：标识*为必填项，填写完信息后请提交。上传的文件类型只能为doc,docx,xls,xlsx,ppt,pptx,txt,jpg,bmp,gif,png,rar,zip格式，单个文件大小为5M以内
</p>
	<form id="myForm" name="myForm">
	<input type="hidden" id="order_id" name="order_id" value=""/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="grgz_tab left">
		<tr>
			<th scope="row"><span class="orange3">*</span> 需求名称：</th>
			<td colspan="3"><input name="orderName" id="orderName" type="text" class="input70" /> 100个字符内</td>
			</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> 系统编号：</th>
			<td colspan="3"><input name="sysNo" id="sysNo" type="text" class="input50" value="CMFJ20140606XQ0033" readonly="readonly"/> 编码格式：CMFJ+年月日（如20140528）+XQ+流水号（数字0001-9999）</td>
			</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> 需求大小：</th>
			<td colspan="3">
				<p><input name="orderScope" type="radio" value="1" checked="checked" /> 大 （90人天以上）</p>
				<p><input name="orderScope" type="radio" value="2" /> 中（30-90人天以内，包括90人天）</p>
				<p><input name="orderScope" type="radio" value="3" /> 小（30人天以下，包括30人天）</p>
			</td>
			</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> OA工单号：</th>
			<td><input name="oaNo" type="text" class="input90" /></td>
			<th scope="row"><span class="orange3">*</span> 归属系统：</th>
			<td>
			<select class="input90" id="sysApp" name="sysApp">
				<option value="">---请选择---</option>
				<option value="1">ERP系统</option>
				<option value="2">网络监控系统</option>
				<option value="3">OA综合系统</option>
			</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> 需求部门/单位：</th>
			<td><input id="orderDept" name="orderDept" type="text" class="input90" /></td>
			<th scope="row"><span class="orange3">*</span> 需求提出人：</th>
			<td><input name="orderApplyMan" id="orderApplyMan" type="text" class="input90" /></td>
		</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> 开发负责人：</th>
			<td><input name="developMan" id="developMan" type="text" class="input90" /></td>
			<th scope="row"><span class="orange3">*</span> 执行厂商：</th>
			<td>
			<select class="input90" id="company_id" name="company_id" >
				<option value="">---请选择---</option>
				<option value="1">福建富士通</option>
				<option value="2">福建诺基亚</option>
			</select>
			
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="orange3">*</span> 需求描述：</th>
			<td colspan="3">
			<textarea id="orderDetail" name="orderDetail" cols="" rows="" style="height:80px; line-height:20px;" class="input90"></textarea>
			</td>
			</tr>
		<tr>
			<th scope="row">相关附件：</th>
			<td colspan="3">
			<p><input name="" type="button" value="添加附件"  class="btn_gr1 mt10"/> <input type="button" value="上传" onclick="uploadFile()"/></p>
			<p>
			<input id="upload" type="file" name="upload" class="input70"/>
			
			<input name="" type="button" value="删除"  class="btn_gr3 ml10"/></p>
			<div id="div_file_list">
		
			</div>
			</td>
			</tr>
	</table>
	</form>
	<div class="clear"></div>
	<div class="btn_gr2 mt8" style=" width:100px;  margin:7px auto 0; float:none"><a class="hand" onclick="addOrder()">保存需求</a></div>
	</div>

</div>
<!--main 结束-->
<script type="text/javascript" src="<%=basePath %>js/public.js"></script>
<!--提示弹出层 开始-->
	<div id="cover1"></div>		
	<!--保存需求 弹出框 开始-->
	<div class="pop_410" id="pop_sure">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_sure');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">保存成功！要马上下发给厂商吗？</div>
			<div class="mt20 mb10" style="margin-left:120px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_sure');"><a class="hand"  onclick="sendCompany()">马上下发</a></span>
				<span class="btn_gr2" onclick="hide('cover1','pop_sure');"><a href="#">暂不下发</a></span>
			</div>
		</div>
	</div>
	<!--保存需求 弹出框 结束-->
	
	<!--下发成功 弹出框 开始-->
	<div class="pop_410" id="pop_msxf">
		<div class="pop_410_t pop_box_t"><span class=" mr15 pop_close" onclick="hide('cover1','pop_msxf');"></span>  <strong class="fl ml15">提示</strong> </div>
		<div class="pop_410_m text_c">
			<div style="padding-top:30px; padding-bottom:30px;">下发成功！</div>
			<div class="mt20 mb10" style="margin-left:170px;">
				<span class="btn_gr2 mr20" onclick="hide('cover1','pop_msxf');"><a href="<%=basePath%>order/listOrder.action">确认</a></span>
			</div>
		</div>
	</div>
	<!--下发成功 弹出框 结束-->
	
<!--提示弹出层 结束-->
</body>
</html>
