<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css"/> 
    <script type="text/javascript">
	function login(){
		console.log("begin");
		//获取表单信息
		var jsonObj=$("#myForm").serializeJson();
		var jsonStr=JSON.stringify(jsonObj);
		console.log(jsonStr);
		$.ajax({
				type:"POST",
				url:"<%=basePath%>user/loginUser.action",
				async:false,
				data:{"parm":jsonStr} ,
				dataType:"json",
				success: function(data){
					var res=data.result;
					if(res=="success"){
						alert("恭喜你，登录成功:)");
						window.location.href="<%=basePath%>user/main.action";
					}
					else if(res=="fail"){
						alert("登录失败:()");
					}
				}
			});
		
	}
	//非空检查
	function isUsernameNull(){
		var username = document.getElementById("login_name").value;
		var usernull = document.getElementById("usernull");
		
		if(username == null || username == ""){
			usernull.innerHTML = "<font color=\"red\">用户名不能为空！</font>"
			return false;
		}else
			usernull.innerHTML = ""
		return true;
	}
	function isPasswordNull(){
		var password = document.getElementById("login_password").value;
		var pwdnull = document.getElementById("pwdnull");
		
		if(password == null || password == ""){
			pwdnull.innerHTML = "<font color=\"red\">密码不能为空！</font>"
			return false;
		}else
			pwdnull.innerHTML = ""
		return true;
	}
	function check(){
		if(!isUsernameNull()){
			return false;
		}else if(!isPasswordNull()){
			return false;
		}
		return true;
	}
</script> 
</head>  
<body> 
    <div id="login">  
        <form method="post" id="myForm" onsubmit="return check()"> 
        <table>
        <tr><td><h1>登录</h1></td></tr>
        <tr>
		  <td colspan="2">
            <input type="text" required="required" placeholder="用户名" id="login_name" name="login_name" onblur="isUsernameNull()"/></td><td width=380px><span id="usernull"></span></td></tr>
           <tr><td colspan="2"> 
            <input type="password" required="required" placeholder="密码" id="login_password" name="login_password" onblur="isPasswordNull()"/></td><td width=380px><span id="pwdnull"></span></td></tr> 
         </table> 
            <button class="but" type="button" onclick="login()">登录</button> 
									<dl>
										<dt>如果没有注册账号？</dt>
										<dd>
											立即注册即可体验本系统吧！
											<a href="${ pageContext.request.contextPath }/regist.jsp">立即注册</a>
										</dd>
									</dl>						
        </form>  
    </div>  
</body>  
</html>