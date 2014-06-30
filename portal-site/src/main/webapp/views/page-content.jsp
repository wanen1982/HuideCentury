<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
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
                                <c:if test="${!empty parentCat}">
                                    <a href="/navi/${parentCat.catId}">${parentCat.catName}</a><span class="split">-</span>
                                </c:if>
                                <span>${currentCat.catName}</span>
                            </div>
                            <div class="sub-title">
                                ${currentCat.catName}
                            </div>
                        </div>
                        <div class="main-content">
                            ${pageContent}
                        </div>
                    </div>
                    <div class="col-sub">
                    	<c:if test="${fn:length(catList) > 0}">
	                        <div class="menu">
	                           <div class="title">
								<span class="img_${parentCat.catId}"></span>
	                           </div>
	                            
	                            <c:if test="${fn:length(catList) > 0}">
	                                <ul class="menu-list">
	                                    <c:forEach items="${catList}" var="cat">
	                                        <li <c:if test="${cat.catId == currentCat.catId}">class="selected"</c:if>><a href="/c/${cat.catId}">${cat.catName}</a></li>
	                                    </c:forEach>
	                                </ul>
	                            </c:if>
	                        </div>
                        </c:if>
                        <img src="${siteUrl}/img/hotline.jpg"/>
                    </div>
                </div>
            </div>
            <%@include file="./footer.jsp"%>
        </body>
    </html>