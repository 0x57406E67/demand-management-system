<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="<%=basePath%>js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/json.js" type="text/javascript"></script>
<script src="<%=basePath%>js/json-bean.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/regist.css" />
<!--<script type="text/javascript" src="${pageContext.request.contextPath }/js/utils.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/js/regUser.js"></script>-->
<script type="text/javascript">
	function regist(){
		console.log("begin");
		//获取表单信息
		var jsonObj=$("#myForm").serializeJson();
		var jsonStr=JSON.stringify(jsonObj);
		$.ajax({
		type:"POST",
		url:"<%=basePath%>user/registUser.action",
		async:false,
		data:{"parm":jsonStr} ,
		dataType:"json",
		success: function(data){
		var res=data.result;
		if(res=="success"){
		window.location="<%=basePath%>login.jsp";
		alert("恭喜你，注册成功:)");
					}
					else if(res=="fail"){
						alert("注册失败:()");
					}
				}
			});
		
	}
	//检查用户名是否存在
	function isUsernameLegal(){
		var username = document.getElementById("login_name").value;
		var usernull = document.getElementById("usernull");
		//使用Ajax检查用户是否存在		
		if(username == null || username == ""){
			usernull.innerHTML = "<font color=\"red\">用户名不能为空！</font>"
			return false;
		}
		isUserExsit();
		return true;
	}
	//检查密码内容位数
	function isPasswordLegal(){
		var password = document.getElementById("login_password").value;
		var pwdnull = document.getElementById("nullpassword");
		
		if(password == null || password == ""){
			pwdnull.innerHTML = "<font color=\"red\">密码不能为空！</font>"
			return false;
		}else if(password.length < 3){
			pwdnull.innerHTML = "<font color=\"red\">密码长度小于3位！！</font>"
			return false;
		}else
			pwdnull.innerHTML = ""
		return true;
	}
	//检查密码是否一致
	function isRepasswordLegal(){
		var repassword = document.getElementById("rePassword").value;
		var password = document.getElementById("login_password").value;
		var pwdnull = document.getElementById("nullrePassword");
		
		if(repassword == null || repassword == ""){
			pwdnull.innerHTML = "<font color=\"red\">确认密码不能为空！</font>"
			return false;
		}else if(repassword != password){
			pwdnull.innerHTML = "<font color=\"red\">两次密码输入不一致！</font>"
			return false;
		}else
			pwdnull.innerHTML = ""
		return true;
	}
	
	
</script>
</head>
<body>
	 <div id="regist">
	 
	 <form method="post" id="myForm">
	 <table>
		<tr><td><h1>注册</h1></td></tr>
				<tr>
					<td colspan="2"><input class="input-text" required="required"
						placeholder="请输入用户名" type="text" id="login_name" name="login_name"
						maxlength="10" onblur="isUsernameLegal()" /></td><td width=380px>
					<span><font color=\"red\">*</font></span><span
						id="usernull"></span><span id="cname"></span></td>
				</tr>
				<tr>
					<td colspan="2"><input class="input-text" required="required"
						placeholder="请输入密码" type="password" id="login_password" name="login_password"
						maxlength="15" onblur="isPasswordLegal()" /></td><td width=380px>
					    <span><font color=\"red\">*</font></span><span
						id="nullpassword"><font color=\"green\">密码至少3位</font></span><span
						id="simplepassword"></span></td>
				</tr>
				<tr>
					<td colspan="2"><input class="input-text" required="required"
						placeholder="确认密码" type="password" id="rePassword"
						name="rePassword" maxlength="15" onblur="isRepasswordLegal()" /></td><td width=380px>
					<span><font color=\"red\">*</font></span><span
						id="nullrePassword"></span><span id="uneq"></span></td>
				</tr>
			</table> 
            <button class="but" type="button" onclick="regist()">注册</button>
		</form>
	</div>
</body>
</html>