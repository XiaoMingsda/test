<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/4/24
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>
    <%--	页码输出的开始		--%>
    <c:choose>
        <%--	情况1	总页码小于等于5，页码范围是：1-总页码	--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--	情况2    总页码数大于5			--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--	当前页码为前3页					--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--	当前页码为后3页					--%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal - 2 && requestScope.page.pageNo <= requestScope.page.pageTotal}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${ i == requestScope.page.pageNo }">
            【${requestScope.page.pageNo}】
        </c:if>
        <c:if test="${ i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--    页码输出的结束                    --%>

    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定" />
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                //JavaScript语言中提供了一个location地址栏对象
                //它有一个属性叫href，它可以获取浏览器地址栏中的地址
                //href属性可读可写
                //alert(location.href);//出现的形式如 http://localhost:8080/book/${requestScope.page.url}
                location.href = "${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
