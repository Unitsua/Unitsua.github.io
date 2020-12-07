<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/8/28
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/inc.jsp"%>
<div id="nav">
    <div id="menu">
        <a href="#">首页</a>
        <c:forEach items="${cs}" var="c">
            <a href="topic.jsp">${c.cname}</a>
        </c:forEach>
        <a href="#">论坛</a>
    </div>
</div>