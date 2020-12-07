<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/8/28
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/inc.jsp"%>
    <!-- 首页图片及首页文章标题、简介 -->
    <div style="width:100%;border:0px;text-align: left;">
        <img style="float:left;width:200px;height:200px;" src="images/earth.jpg">
        <c:forEach items="${as}" var="a">
        <h4>${a.title}</h4>
        <p>
            ${a.content}
        </p>
        </c:forEach>
    </div>