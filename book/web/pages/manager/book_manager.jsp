<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function() {

			$("a.deleteClass").click(function () {
				var del = $(this).parents("tr");
				var text = del.find("td:first").text();
				if (!confirm("你确定要删除 " + text + "吗？")) {
					return false;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>${item.price}</td>
						<td>${item.author}</td>
						<td>${item.sales}</td>
						<td>${item.stock}</td>
						<td><a href="manager/bookServlet?action=getBook&id=${item.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
						<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${item.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
					</tr>
			</c:forEach>

			<%--
			<tr>
				<td>时间简史</td>
				<td>20.00</td>
				<td>霍金</td>
				<td>200</td>
				<td>400</td>
				<td><a href="book_edit.jsp">修改</a></td>
				<td><a href="#">删除</a></td>
			</tr>	
			
			<tr>
				<td>时间简史</td>
				<td>20.00</td>
				<td>霍金</td>
				<td>200</td>
				<td>400</td>
				<td><a href="book_edit.jsp">修改</a></td>
				<td><a href="#">删除</a></td>
			</tr>	
			
			<tr>
				<td>时间简史</td>
				<td>20.00</td>
				<td>霍金</td>
				<td>200</td>
				<td>400</td>
				<td><a href="book_edit.jsp">修改</a></td>
				<td><a href="#">删除</a></td>
			</tr>	
			
			<tr>
				<td>时间简史</td>
				<td>20.00</td>
				<td>霍金</td>
				<td>200</td>
				<td>400</td>
				<td><a href="book_edit.jsp">修改</a></td>
				<td><a href="#">删除</a></td>
			</tr>	--%>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageNo}">添加图书</a></td>
			</tr>	
		</table>

		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>