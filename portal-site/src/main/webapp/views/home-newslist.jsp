<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="inner-case">
    <div class="title group">
        <a class="more" href="/navi/42">more+</a>
        <ul class="tab-list">
            <li class="selected">公司新闻</li>
            <li>行业新闻</li>
        </ul>
    </div>
    <div class="ncontent">
        <div class="tab-content selected">
            <c:forEach items="${companyNewsList}" var="news">
                <a href="/news/${news.newsId}" class="group">
                    <span class="date-time"><fmt:formatDate value="${news.publishTime}" pattern="yyyy-MM-dd"/></span>
                    <span class="news-alt">${news.title}</span>
                </a>
            </c:forEach>
        </div>
        <div class="tab-content">
            <c:forEach items="${industryNewsList}" var="news">
                <a href="/news/${news.newsId}" class="group">
                    <span class="date-time"><fmt:formatDate value="${news.publishTime}" pattern="yyyy-MM-dd"/></span>
                    <span class="news-alt">${news.title}</span>
                </a>
            </c:forEach>
        </div>
    </div>
</div>