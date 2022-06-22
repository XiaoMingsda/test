<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给删除绑定单击事件
			$("a.deleteItem").click(function () {
				/*
				var deleteTr = $(this).parents("tr");
				var name= deleteTr.find("td:first").text();
				if (!confirm("你确定要将[" + name + "]从购物车删除吗？")) {
					return false;
				}
				*/
				return (confirm("你确定要删除【" + $(this).parents().parents().find("td:first").text() + "】吗？"));
			});
			//给清空购物车绑定单击事件
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？");
			});
			//给输入框绑定失去焦点事件 === onchange内容发生改变事件
			$(".updateCount").change(function () {
				//获取商品名称
				var name = $(this).parents().parents().find("td:first").text();
				//获取商品数量
				var count = $(this).val();
				if (confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗？")) {
					//发起请求。给服务器保存修改
					var bookId = $(this).attr("bookId");
					location.href = "${pageContext.getAttribute("basePath")}cartServlet?action=updateCount&count=" + count + "&id=" + bookId;
				} else {
					//defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
<%-- ${sessionScope} --%>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success__menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空! 快去浏览商品吧!</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td>
							<input class="updateCount" bookId="${item.value.id}" style="width: 80px" type="text" value="${item.value.count}" />
						</td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>