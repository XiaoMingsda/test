<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 写base标签，永远固定相对路径跳转的结果 -->
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
	<script type="text/javascript">
		$(function () {
			$("#sub_btn").click(function (event) {
				// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
				var usernameText = $("#username").val();
				var usernamePatt = /^\w{5,12}$/;
				if (!usernamePatt.test(usernameText)) {
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
				var passwordText = $("#password").val();
				var passwordPatt = /^\w{5,12}$/;
				if (!passwordPatt.test(passwordText)) {
					$("span.errorMsg").text("密码不合法");
					return false;
				}

				// 验证确认密码：和密码相同
				var repwdText = $("#repwd").val();
				if (repwdText != passwordText) {
					$("span.errorMsg").text("确认密码和密码不一致！");
					return false;
				}

				// 邮箱验证：xxxx@xxx.com
				var emailText = $("#email").val();
				var emailPatt = /^([a-zA-Z0-9_.-]+)@([da-z.-]+).([a-z.]{2,6})$/;
				if (!emailPatt.test(emailText)) {
					$("span.errorMsg").text("邮箱格式不合法");
					return false;
				}

				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
				var codeText = $("#code").val();
				//去掉验证码前后空格
				codeText = $.trim(codeText);
				//alert(codeText);
				if (codeText == null || codeText == "") {
					$("span.errorMsg").text("验证码不能为空");
					return false;
				}
				$("span.errorMsg").empty();
			});

			//给验证码的图片绑定单击事件
			$("#code_img").click(function() {
				//在事件响应的function函数中有一个this对象。这个this对象，是当前正在响应事件的dom对象
				//src属性表示验证码img标签的 图片路径。它可读，可写
				//由于IE、火狐浏览器存在缓存机制，验证码图片只能刷新一次
				//解决方法是在请求参数那里加一个时间戳
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

			$("#username").blur(function () {

				//获取用户名
				var username = this.value;
				$.getJSON("${pageContext.getAttribute("basePath")}userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
					//console.log(data);//{"existsUsername": true}
					if (data.existsUsername) {
						$("span.errorMsg").text("用户名已存在！");
					} else {
						$("span.errorMsg").text("用户名可用！");
					}
				})

			})
		});
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
									value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>