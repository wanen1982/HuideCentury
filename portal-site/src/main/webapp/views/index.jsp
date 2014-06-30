<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${siteUrl}/css/main.css"/>
    <jsp:include page="/views/bootstrap/inc.jsp" />
    <title>谷米互动（北京）科技发展有限公司</title>
</head>
<body>
    <%@include file="./header.jsp"%>
    <div class="content">
        <div class="wrapper">
            <!-- cars-->
            <div class="stage group">
                <div class="btn btn-left"></div>
                <div class="btn btn-right"></div>
                <div class="stage-inner">
                	<c:forEach items="${carouselList}" var="carousel">
                		<div class="scroller-item">
	                        <a href="${carousel.linkUrl}"><img src="${imageUrl}${carousel.picpath}"/></a>
	                        <div class="scroller-des">
	                            <p><a href="${carousel.linkUrl}">${carousel.description}</a></p>
	                        </div>
	                    </div>
                	</c:forEach>
                </div>
                <!-- ul class="scroller-list">
                    <li class="selected">1</li>
                    <li>2</li>
                </ul -->
            </div>
            <!--news-->
            <div class="news-group group">
                <div class="news-group-main">
                    <div class="news-group-inner">
                        <div class="inner-case">
                            <div class="title group">
                                <a class="more" href="/navi/41">more+</a>
                                <img class="abt-us" src="${siteUrl}/img/abt_us.jpg"/>                
                            </div>
                            <div class="ncontent">
                                <img src="${siteUrl}/img/s1.jpg">                
                                <p><a href="/c/56" id="aboutus"></a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="news-group-sub" id="newsList"></div>
                <div class="news-group-side" id="solution"></div>
            </div>
            <!-- friend-->
            <div class="friend-list group">
            	<div class="firend-movie">
                	<div class="movie-inner">
                    	<ul>
                    		<c:forEach items="${partnerList}" var="partner">
                    			<li><a href="${partner.linkUrl}" target="_blank"><img src="${imageUrl}${partner.iconPath}"/></a></li>
                    		</c:forEach>
                         </ul>
                     </div>
                 </div>
                 <div class="title">
                 </div>
               <div class="right-corner"></div>
       		</div>
        </div>
    </div>
<%@include file="./footer.jsp"%>
<script type="text/javascript">
$().ready(function(){
    $.ajax({
        url:"/home/aboutus",
        type:"POST",
        success:function(rsp){
            var content = $(rsp.content).text();
            $("#aboutus").text(content.substr(0, 150) + "...");
        }       
    });
    $.ajax({
        url:"/home/newslist",
        type:"POST",
        success:function(rsp){
            $("#newsList").html(rsp);
        }           
    });
    $.ajax({
        url:"/home/solution",
        type:"POST",
        success:function(rsp){
            $("#solution").html(rsp);
        }           
    });
});
</script>
</body>
</html>