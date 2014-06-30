<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${cmsUrl}/style/system.css" />
	<title>新闻正文</title>
</head>
<body>
	<div class="web_mainer wnormal">
    	<div class="webr-outer group">
            <div class="webl">
                <div class="weblocal">
                    <div class="webctxt">
                        <a href="javascript:void(0);">首页</a>
                        <c:forEach items="${crumbList}" var="crumb">
                            <span>&gt;</span>
                            <a href="javascript:void(0);">${crumb.categoryName}</a>
                        </c:forEach>
                        &gt;  正文
                    </div>
                </div>
                <div class="cwarp">
                    <div class="title">
                        <h2>${news.title}</h2>
                    </div>
                    <div class="subtitle">
                        <h3></h3>
                    </div>
                    <div class="info">
                        时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${news.publishTime}" type="both"/>&nbsp;&nbsp;&nbsp;&nbsp;作者：${(empty news.editorName)?'':news.editorName}&nbsp;&nbsp;&nbsp;&nbsp;来源：${news.source}
                    </div>
                    <div class="content">
                        ${news.content}
                        <br>
                        <br>
                        <!-- span style="font-family: 微软雅黑">
                            <span style="font-size: 12px">〔此文转自${news.source}〕</span>
                        </span -->
                    </div>
                    <div class="webeditor">〔网络编辑│${news.editorName}〕</div>
                </div>
            </div>
        </div>
	</div>
</html>