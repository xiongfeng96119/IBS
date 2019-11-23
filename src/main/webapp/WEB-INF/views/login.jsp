<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
  		<meta charset="utf-8">
  		<title>用户登录</title>
  		<meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  		<meta name="author" content="Vincent Garreau" />
  		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  		<link rel="stylesheet" media="screen" href="/login/css/style.css"/>
  		<link rel="stylesheet" type="text/css" href="/login/css/reset.css"/>
  		<%@include file="/WEB-INF/views/common.jsp" %>
        <style type="text/css">
            #errorMsg{text-align: center;color: #ff5d49;height: 25px;}
        </style>
  		<script type="text/javascript">
			$(function () {
			    //判断当前窗口是不是最顶层窗口，如果不是就表示登录页面被嵌套了，需要处理
                if(window != top){
                    top.location.href = "/page/login";
                }
			    //绑定键盘事件
                $(document).keypress(function (event) {
                    if (event.keyCode == 13){
                        $("#btn-login").click();
                    }
                });
				$("#btn-login").click(function () {
					//$("#loginFrom")[0].submit();	//同步请求提交
                    $.postJSON("/page/login",$("#loginFrom").toJson(),function (data) {
                        if (data.status == 500){
                            //$.messager.alert("错误",data.msg,"error");
                            $("#errorMsg").text(data.msg);
                        }else{
                            location.href = "/page/index";
                        }
                    });
                });
            });
  		</script>
	</head>
	<body>
		<div id="particles-js">
			<form method="post" action="/page/login" id="loginFrom">
				<div class="login">
					<div class="login-top">登录</div>
					<div class="login-center clearfix">
						<div class="login-center-img"><img src="/login/img/name.png"/></div>
						<div class="login-center-input">
							<input type="text" name="username" id="username" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
							<div class="login-center-input-text">用户名</div>
						</div>
					</div>
					<div class="login-center clearfix">
						<div class="login-center-img"><img src="/login/img/password.png"/></div>
						<div class="login-center-input">
							<input type="password" name="password" id="password" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
							<div class="login-center-input-text">密码</div>
						</div>
                    </div>
                    <div class="login-center clearfix">
                        <div id="errorMsg"></div>
					</div>
					<div class="login-button" id="btn-login">登录</div>
				</div>
			</form>
			<div class="sk-rotating-plane"></div>
		</div>

		<!-- scripts -->
		<script src="/login/js/particles.min.js"></script>
		<script src="/login/js/app.js"></script>
		<script type="text/javascript">
			/* function hasClass(elem, cls) {
	  			cls = cls || '';
	  			if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  			return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
			}
	 
			function addClass(ele, cls) {
	  			if (!hasClass(ele, cls)) {
	    			ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  			}
			}
	 
			function removeClass(ele, cls) {
	  			if (hasClass(ele, cls)) {
	    			var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    			while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      				newClass = newClass.replace(' ' + cls + ' ', ' ');
	    			}
	    			ele.className = newClass.replace(/^\s+|\s+$/g, '');
  				}
			}
			document.querySelector(".login-button").onclick = function(){
				addClass(document.querySelector(".login"), "active");
				setTimeout(function(){
					addClass(document.querySelector(".sk-rotating-plane"), "active");
					document.querySelector(".login").style.display = "none";
				},800);
				setTimeout(function(){
					removeClass(document.querySelector(".login"), "active");
					removeClass(document.querySelector(".sk-rotating-plane"), "active");
					document.querySelector(".login").style.display = "block";
					alert("登录成功");
				},5000);
			} */
		</script>
	</body>
</html>