<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/8/25
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="inc.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <pg:pager url="${param['url']}"
              items="${param['count']}"
              maxIndexPages="5"
              export="currentPageNumber=pageNumber"
              maxPageItems="${param['pagesize']}">
        <tr>
            <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>${vo.count}</strong> 条记录，当前第<strong>${vo.currentPage}</strong> 页，共 <strong>${vo.pageCount}</strong> 页</span></div></td>

            <td width="67%" align="right" vAlign="center" noWrap>
                <pg:first>
                    <a href="${pageUrl}">首页</a>
                </pg:first>
                <pg:prev>
                    <a href="${pageUrl}">上页</a>
                </pg:prev>
                <pg:pages>
                    <c:if test="${pageNumber eq currentPageNumber}">
                        ${pageNumber}
                    </c:if>
                    <c:if test="${not (pageNumber eq currentPageNumber)}">
                        <a href="${pageUrl}">${pageNumber}</a>
                    </c:if>
                </pg:pages>
                <pg:next>
                    <a href="${pageUrl}">下页</a>
                </pg:next>
                <pg:last>
                    <a href="${pageUrl}">尾页</a>
                </pg:last>
            </td>
        </tr>
    </pg:pager>
</table>

