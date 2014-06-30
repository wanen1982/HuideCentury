<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${siteUrl}/css/main.css"/>
    <title>北京友达天下网络科技有限公司</title>
</head>
<body>
    <div class="header">
        <div class="wrapper">
            <div class="hd-case group">
                <ul class="nav-list">
                    <li class="lv1 fst">
                        <div class="nav-d">
                            <a href="${siteUrl}">网站首页</a>
                        </div>
                    </li>
                    <c:forEach items="${navigationList}" var="navi">
                        <li class="lv1 <c:if test="${fn:length(navi.childCategoryList) >0}">child</c:if>">
                        <div class="nav-d">
                            <a href="/navi/${navi.catId}">${navi.showName}</a>
                        </div>
                        <c:if test="${fn:length(navi.childCategoryList) > 0}">
                            <div class="cnav-case">
                                <ul class="cnav">
                                    <c:forEach items="${navi.childCategoryList}" var="category">
                                        <li>
                                            <a href="/c/${category.catId}">${category.catName}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
            <div class="logo">
                <a href="${siteUrl}">
                    <img src="${siteUrl}/img/logo.jpg"/>
                </a>
            </div>
            <div class="hd-widget"></div>
        </div>
    </div>
</div>
<div class="content">
    <div class="wrapper group">
        <div class="top-ad group">
            <img src="${siteUrl}/img/top_ad.jpg"/>
        </div>
        <div class="col-main">
            <div class="top-title group">
                <div class="sub-nav">
                    <a href="${siteUrl}">首页</a>
                    <span class="split">-</span>
                    <c:if test="${!empty parentCat}">
                        <a href="/navi/${parentCat.catId}">${parentCat.catName}</a>
                        <span class="split">-</span>
                    </c:if>
                    <span>${currentCat.catName}</span>
                </div>
                <div class="sub-title">${currentCat.catName}</div>
            </div>
            <div class="main-content">${pageContent}</div>
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
                            <li <c:if test="${cat.catId == currentCat.catId}">class="selected"</c:if>>
                            <a href="/c/${cat.catId}">${cat.catName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <img src="${siteUrl}/img/hotline.jpg"/>
    </div>
</div>
</div>
<div class="siteinfo">
<div class="siteinfo-inner">
    <div class="wrapper">
        <div class="logov">
            <span>CopyRight</span>
            <span>&copy2013</span>
            <span>北京友达天下网络科技有限公司</span>
            <span>京ICP备12345678号</span>
        </div>
    </div>
</div>
</div>
</body>
</html>