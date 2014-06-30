<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${siteUrl}/css/main.css"/>
<title>谷米互动（北京）科技发展有限公司</title>
</head>
        <body>
            <%@include file="./header.jsp"%>
            <div class="content">
                <div class="wrapper group">
                    <div class="top-ad group">
                        <img src="${siteUrl}/img/top_ad.jpg"/>
                    </div>
                    <div class="col-main">
                        <div class="top-title group">
                            <div class="sub-nav">
                                <a href="${siteUrl}">首页</a><span class="split">-</span>
                                <a href="/navi/${parentCat.catId}">${parentCat.catName}</a><span class="split">-</span>
                                <a href="/c/${currentCat.catId}">${currentCat.catName}</a><span class="split">-</span>
                                <span>新闻正文</span>
                            </div>
                            <div class="sub-title">
                                ${currentCat.catName}
                            </div>
                        </div>
                        <div class="main-content">
                            <div class="news-title">
                                <h2>${news.title}</h2>
                            </div>
                            <div class="news-info">
                                <span>时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${news.publishTime}" type="both"/></span><span>网络编辑：${news.editorName}</span><span>来源：${news.source}</span>
                            </div>
                            <div class="news-content">
                            ${news.content}
                            </div>
                        </div>
                    </div>
                    <div class="col-sub">
                        <div class="menu">
                            <c:if test="${fn:length(catList) > 0}">
                                <div class="title">
                                    <span class="img_${parentCat.catId}"></span>
                                </div>
                            </c:if>
                            <c:if test="${fn:length(catList) > 0}">
                                <ul class="menu-list">
                                    <c:forEach items="${catList}" var="cat">
                                        <li <c:if test="${cat.catId == currentCat.catId}">class="selected"</c:if>><a href="/c/${cat.catId}">${cat.catName}</a></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                        <img src="${siteUrl}/img/hotline.jpg"/>
                    </div>
                </div>
            </div>
            <%@include file="./footer.jsp"%>
        </body>
    </html>