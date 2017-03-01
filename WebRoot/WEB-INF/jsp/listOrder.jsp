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
<script type="text/javascript">
function searchOrder(page){
	var date_end = $("#date_end").attr("value");
	var jsonObj=$("#myForm").serializeJson();
	var jsonStr=JSON.stringify(jsonObj);
	$.ajax({
		type:"POST",
		url:"<%=basePath%>order/listOrderAjax.action",
		async:false,
		data: {"parm":jsonStr,"date_end":date_end,"page":page},
	    dataType:"json",
		success: function(data){
		 	var list = data.list;
		  	var totalPage = data.totalPage; 
		  	var allRow = data.allRow;
		  	var currentPage = data.currentPage;
			var info="";
			var queryPage="";
			for(var i=0;i<list.length;i++){
				//直接修改DOM ：自由度比较高  。维护差
				var row=list[i];
				info+="<tr>";
				info+="<td><input name=\"grgzpt\" type=\"radio\" value=\""+row.id+"\"  class=\"dskg\" /></td>";
				info+="<td>"+row.sysNo+"</td>";
				info+="<td>"+row.orderName+"</td>";
				info+="<td>"+row.date+"</td>";
				info+="<td>"+row.gsmc+"</td>";
				info+="<td>"+row.orderApplyMan+"</td>";
				info+="<td>"+row.orderDept+"</td>";
				info+="<td>"+row.developMan+"</td>";
				info+="<td class=\"xmzt\">";
				info+=""+row.status_name+"";
				info+="</td>";
				info+="</tr>";
			}
			if(totalPage == 1){
				queryPage += "首页";
			}else if(totalPage > 1){
				if(currentPage == 1){
					queryPage += "首页";
	  				queryPage += "<a onclick=searchOrder("+ (parseInt(currentPage)+1) +") href=#>下一页</a>";
					queryPage += "<a onclick=searchOrder("+ totalPage +") href=#>尾页</a>";  				
				} 
 				if(currentPage != 1 && currentPage < totalPage){
					queryPage += "<a onclick=searchOrder(1) href=#} >首页</a>";
					queryPage += "<a onclick=searchOrder("+ (parseInt(currentPage)-1) +") href=#>上一页</a>";
	  				queryPage += "<a onclick=searchOrder("+ (parseInt(currentPage)+1) +") href=#>下一页</a>";
					queryPage += "<a onclick=searchOrder("+ totalPage +") href=#>尾页</a>";  	
				} 
 				if(currentPage == totalPage){
					queryPage += "<a onclick=searchOrder(1) href=#} >首页</a>";
					queryPage += "<a onclick=searchOrder("+ (parseInt(currentPage)-1) +") href=#>上一页</a>尾页";
				}  
			} 


			
			$("#tb_order").html(info);
			$("#allRow").html(allRow);
			$("#totalPage").html(totalPage);
			$("#currentPage").html(currentPage);
			$("#queryPage").html(queryPage);
		}  
	});
} 


//跳转填写需求分析
function to_add_anaylze(){
	//收集选中的order_id
	var order_id=$("input[type='radio']:checked").attr("value");
	//获取选中的order_id
	window.location.href="<%=basePath%>anaylze/to_add_anaylze.action?order_id="+order_id;
	
}
//跳转需求详情
function to_add_anaylze(){
	//收集选中的order_id
	var order_id=$("input[type='radio']:checked").attr("value");
	//获取选中的order_id
	window.location.href="<%=basePath%>anaylze/to_add_anaylze.action?order_id="+order_id;
	
}
//跳转填写需求确认
function addConfirm(){
	//收集选中的order_id
	var order_id=$("input[type='radio']:checked").attr("value");
	//获取选中的order_id
	window.location.href="<%=basePath%>confirm/to_add_confirm.action?order_id="+order_id;
	
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
	<div class="gr_search pb10 mb10 w100 left">
			<form id="myForm" name="myForm">
			<p>	需求编号： <input name="sysNo" type="text" class="input1" />
				需求名称： <input name="orderName" id="orderName" type="text" class="input1" />                                   
				提出人： <input  name="orderApplyMan" id="orderApplyMan" type="text" class="input1" />                        
				提出单位： <select name="orderDept" type="text" class="input2" >
					<option value="" selected>请选择</option>
				</select> </p>   
			<p>	项目经理： <input name="developMan" type="text" class="input1" />                       
				执行厂商： <select name="company_id" class="input1" />  
					<option value="0" selected>请选择</option>
					<option value="1">富士通</option>
					<option value="2">诺基亚</option>
				</select>                                  
				计划上线时间：<input type="text" name="date" id="date" class="input3" onclick="J.calendar.get();"/>
				<img src="<%=basePath%>images/time.gif" onclick="J.calendar.get({id:'date'});" /> 至 
				<input id="date_end" type="text" class="input3" onclick="J.calendar.get();" /> 
				<img src="<%=basePath%>images/time.gif" onclick="J.calendar.get({id:'date_end'});" /> 
				<input name="" type="button" value="查询" onclick="searchOrder(1)"  class="btn_gr1 ml10"/></p> 				 
			</form>
			
		</div>
		<div class="ge_btn mb10  w100 left" style=" margin-bottom:0px;">
			<div class="btn_gr2"><a href="">查看详情</a></div>
			<div class="xxq_btn btn_none dis_none">
				<div class="btn_gr2"><a href="xjxq.html">编辑需求</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_xfcs');">下发厂商</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_qxxq');">取消需求</a></div>
			</div>
			<div class="xqfx_btn btn_none dis_none">
				<div class="btn_gr2"><a href="javascript:to_add_anaylze()">填写需求分析</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_xqqr');">提交需求分析</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sqzzxq');" >申请终止需求</a></div>
				<div class="btn_gr2"><a href="zzxqspjg.html">查看终止审批结果</a></div>
			</div>
			<div class="xqqr_btn btn_none dis_none">
				<div class="btn_gr2"><a href="javascript:addConfirm()">填写需求确认</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sh');">提交审核</a></div>
				<div class="btn_gr2"><a href="ckshyj.html">查看审核意见</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sqzzxq');" >申请终止需求</a></div>
				<div class="btn_gr2"><a href="zzxqspjg.html">查看终止审批结果</a></div>
			</div>
			<div class="xqdsh_btn btn_none dis_none">
				<div class="btn_gr2"><a href="wtkf.html">审核</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sqzzxq');" >终止需求</a></div>
			</div>
			<div class="zxkf_btn btn_none dis_none">
				<div class="btn_gr2"><a href="wckf.html">完成开发</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_ys');">提交验收</a></div>
				<div class="btn_gr2"><a href="ckysjg.html">查看验收结果</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sqzzxq');" >申请终止需求</a></div>
				<div class="btn_gr2"><a href="zzxqspjg.html">查看终止审批结果</a></div>
			</div>
			<div class="xqys_btn btn_none dis_none">
				<div class="btn_gr2"><a href="xqys.html">需求验收</a></div>
			</div>
			<div class="sxsq_btn btn_none dis_none">
				<div class="btn_gr2"><a href="sxsq.html">填写上线申请</a></div>
				<div class="btn_gr2"><a class="hand"  onclick="show('cover1','pop_sxsq');">提交上线申请</a></div>
				<div class="btn_gr2"><a href="zzxqspjg.html">查看审批结果</a></div>
			</div>
			<div class="sxsp_btn btn_none dis_none">
				<div class="btn_gr2"><a href="sxsp.html">上线审批</a></div>
			</div>
			<div class="sxgj_btn btn_none dis_none">
				<div class="btn_gr2"><a href="sxgj.html">上线割接</a></div>
			</div>
			<div class="hpg_btn btn_none dis_none">
				<div class="btn_gr2"><a href="hpg.html">后评估</a></div>
			</div>
			<div class="zzdsh_btn btn_none dis_none">
				<div class="btn_gr2"><a href="xqzzsp.html">终止待审核</a></div>
			</div>
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
			<c:forEach items="${pageBean.list }" var="ord">
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

		<div class="page line30">
			<span id="queryPage" class="right">    	        
			<c:if test="${orderList.totalPage == 1}">
	    		<a href="listOrder.action?page=1">首页</a>
	    	</c:if>	
	    	<c:if test="${pageBean.totalPage > 1}">
	    		<c:if test="${pageBean.currentPage == 1}">
	    			首页
	    			<a href="listOrder.action?page=${pageBean.currentPage+1 }">下一页</a>
	    			<a href="listOrder.action?page=${pageBean.totalPage }">尾页</a>
	    		</c:if>
	    		<c:if test="${pageBean.currentPage != 1 && pageBean.currentPage != pageBean.totalPage }">
					<a href="listOrder.action?page=1">首页</a>
					<a href="listOrder.action?page=${pageBean.currentPage-1 }">上一页</a>
	    			<a href="listOrder.action?page=${pageBean.currentPage+1 }">下一页</a>
	    			<a href="listOrder.action?page=${pageBean.totalPage }">尾页</a> 	    		
	    		</c:if>
	    		<c:if test="${pageBean.currentPage == pageBean.totalPage }">
					<a href="listOrder.action?page=1">首页</a>
					<a href="listOrder.action?page=${pageBean.currentPage-1 }">上一页</a>
	    			尾页		
	    		</c:if>	    	
	    	</c:if>	</span>
	   <span class="left">共<span id="allRow" class="red">${pageBean.allRow}</span>条记录      
	    		共<span id="totalPage">${pageBean.totalPage}</span>页   
		       	    
	         	当前第<span id="currentPage">${pageBean.currentPage}</span>页<br></span>
		</div>
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