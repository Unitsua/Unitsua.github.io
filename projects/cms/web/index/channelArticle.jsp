<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/8/28
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/backend/common/inc.jsp"%>
<c:forEach items="${cas}" var="ca">
    <div class="index_topic">
        <img src="/images/jiantou.gif" style="float:left">
        <span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">${ca.key.cname}</span>
        <a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
        <c:forEach items="${ca.value}" var="a">
            <div class="index_topic_list">
                <img src="images/h_article.gif" >
                <a href="#">${a.title}</a>
                <span>[2010-07-18]</span>
            </div>
        </c:forEach>
    </div>
</c:forEach>