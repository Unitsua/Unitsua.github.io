<%@ taglib prefix="ftm" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/backend/common/inc.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CMS 后台管理工作平台</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 3px;
            margin-top: 0px;
            margin-right: 3px;
            margin-bottom: 0px;
        }
        .STYLE1 {
            color: #e1e2e3;
            font-size: 12px;
        }
        .STYLE6 {color: #000000; font-size: 12px; }
        .STYLE10 {color: #000000; font-size: 12px; }
        .STYLE19 {
            color: #344b50;
            font-size: 12px;
        }
        .STYLE21 {
            font-size: 12px;
            color: #3b6375;
        }
        .STYLE22 {
            font-size: 12px;
            color: #295568;
        }
        A:active,A:visited,A:link {
            COLOR: #0629FD;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        A.relatelink:active,A.relatelink:visited,A.relatelink:link {
            color: white;
            TEXT-DECORATION: none
        }

        A.relatelink:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        td {
            font-size: 12px;
            color: #003366;
            height:24px
        }

        .STYLE1 a{
            COLOR: white;
        }
        .STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
            COLOR: white;
            TEXT-DECORATION: none
        }
        .STYLE1 a:hover{
            COLOR: red;
        }
        -->
    </style>
    <script type="text/javascript" src="/backend/js/js.js" charset="utf-8"></script>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                                <td width="94%" valign="bottom"><span class="STYLE1"> 网站文章信息列表</span></td>
                            </tr>
                        </table></td>
                        <td><div align="right"><span class="STYLE1">
             &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10" /> <a href="/backend/article?method=addInput">添加</a>
             &nbsp;&nbsp;<img src="images/edit.gif" width="10" height="10" /> <a href="#">发布</a>
             &nbsp; <img src="images/del.gif" width="10" height="10" /> <a href="javascript:deleteSelected();">删除</a>    &nbsp;&nbsp;   &nbsp;
             </span><span class="STYLE1"> &nbsp;</span></div></td>
                    </tr>
                </table></td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
                <tr>
                    <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
                        <input type="checkbox" name="checkbox" id="checkbox" />
                    </div></td>
                    <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">标题</span></div></td>
                    <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">作者</span></div></td>
                    <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">来源</span></div></td>
                    <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
                    <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
                </tr>
                <%--迭代request中的频道集合--%>
                <c:forEach var="c" items="${vo.datas}">
                <tr>
                    <td height="20" bgcolor="#FFFFFF"><div align="center">
                        <input type="checkbox" name="aid" id="aid" value="${c.aid}" />
                    </div></td>
                    <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.title}</div></td>
                    <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.author}</div></td>
                    <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${c.source}</div></td>
                    <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
                        <ftm:formatDate value="${c.createTime}" pattern="yyyy-MM-dd"/>
                    </div></td>
                    <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
                        <a href="#" title="点击发布文章">发布</a> |
                        <a href="/backend/article?method=delete&cid=${c.aid}" title="点击删除文章">删除</a> |
                        <a href="/backend/article?method=updateInput&cid=${c.aid}" title="点击编辑文章">编辑</a>
                    </div></td>
                </tr>
                </c:forEach>
            </table></td>
    </tr>
    <tr>
        <td height="30">
            <jsp:include page="/backend/common/pager.jsp">
                <jsp:param name="url" value="/backend/article"/>
                <jsp:param name="count" value="${vo.count}"/>
                <jsp:param name="pagesize" value="${vo.pagesize}"/>
            </jsp:include>
        </td>
    </tr>
</table>
</body>
</html>


