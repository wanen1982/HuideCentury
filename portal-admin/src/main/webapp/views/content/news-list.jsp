<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>新闻管理</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link href="${adminUrl}/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${adminUrl}/css/g-system.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
body{ padding-left: 10px; }

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
input[type="text"] { height: 16px; }

.search-filter { margin: 10px 20px 10px 20px; }

.search-filter label { display: inline; font-size: 12px; margin-right: 10px; }
select, input[type="file"] { height: 28px; line-height: 30px; font-size: 12px; }
.emptyBtn{display:inline-block;width:64px;height:26px;}
.news_title{width:260px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;display: block;}
.pagination{text-align:center;margin-top:0;}
.pagination a{color:#08c;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="javascript:;" class="on"> <em>课程管理</em>
		</a>
		<span>|</span>
		<a id="addNews" href="javascript:;" data="/content/news/add"> <em>添加课程</em>
		</a>
	</div>
    <div class="main-content">
        <div class="pad-10">
            <div class="toolbar explain-col form-horizontal">
              <form action="/content/news/list" method="post">
                <span class="search-filter">
                    <label>课程ID:</label>
                    <input class="span2" type="text" name="newsId" value="${search.newsId}"/>    
                </span>
                <span class="search-filter">
                    <label>课程标题:</label>
                    <input type="text" name="newsTitle" value="${search.newsTitle}"/>    
                </span>
                <span class="search-filter">
                    <label>课程类别:</label>
                    <select class="span2" name="catId">
                        <option value="0" <c:if test="${empty search.catId}">selected</c:if>>=选择栏目=</option>
                        <c:forEach items="${newsCategoryList}" var="category">
                          <option value="${category.catId}" <c:if test="${search.catId == category.catId}">selected</c:if>>${category.catName}</option>
                        </c:forEach>
                    </select>      
                </span>
                <span class="search-filter">
                    <label>状态:</label>
                    <select class="span2">
                    	<option value="0" selected>=所有状态=</option>
                        <option value="1">待审核</option>
                        <option value="3">审核未通过</option>
                        <option value="9">已发布</option>
                    </select>     
                </span>
                
                <button class="btn btn-small btn-primary" type="submit" id="search"> <i class="icon-search icon-white"></i>
                    查询
                </button>
              </form>
            </div>
			<div class="bk10"></div>
			<table class="table table-hover">
              <thead>
                <tr>
                  <th width="135px">课程ID</th>
                  <th width="260px">课程标题</th>
                  <th width="120px">所属栏目名</th>
                  <th width="84px">内容类型</th>
                  <th width="84px">编辑人</th>
                  <th width="84px">当前状态</th>
                  <th width="120px">创建时间</th>
                  <th width="200px">管理操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach var="news" items="${newsList}">
              		<tr>
	                	<td><a href="/content/news/preview/${news.newsId}" target="_blank">${news.newsId}</a></td>
	                	<td><a href="/content/news/preview/${news.newsId}" target="_blank"><span class="news_title" title="${news.title}">${news.title}</span></a></td>
	                	<td>${news.catName}</td>
	                	<td><c:choose><c:when test="${news.template == 'IMAGE'}">图片新闻</c:when><c:otherwise>文本新闻</c:otherwise></c:choose></td>
	                	<td>${news.editorName}</td>
	                	<td>
	                	<c:choose>
	                	<c:when test="${news.status == 0}"><span class="label label-warning">保存草稿</span></c:when>
	                	<c:when test="${news.status == 1}"><span class="label label-info">待审核</span></c:when>
	                	<c:when test="${news.status == 2}"><span class="label label-success">审核通过</span></c:when>
	                	<c:when test="${news.status == 3}"><span class="label label-important">审核未通过</span></c:when>
	                	<c:when test="${news.status == 4}"><span class="label label-warning">待修改</span></c:when>
	                	<c:when test="${news.status == 9}"><span class="label">已发布</span></c:when>
	                	</c:choose>
	                	</td>
	                  <td><fmt:formatDate value="${news.publishTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	                  <td>
		                  <div class="btn-toolbar">
	                  		<div class="btn-group">
		                        <a class="btn btn-small btn-primary action-edit" href="javascript:void(0);" data="/content/news/edit/${news.newsId}"> <i class="icon-edit icon-white"></i> 编辑</a>
			                    <a class="btn btn-small btn-danger action-del" href="javascript:void(0);" data="/content/news/delete/${news.newsId}?page=${page}"> <i class="icon-trash icon-white"></i> 删除</a>
		                        <a class="btn btn-small btn-primary" href="/consume/note/queryByNewsid/${news.newsId}/1"> <i class="icon-edit icon-white"></i>查看消费记录</a>
		                    </div>
		                   </div>
	                  </td>
	                </tr>
              	</c:forEach>
              </tbody>
            </table>
		</div>
		<c:if test="${totalPage > 1}">
      <div class="pagination">
        <ul>
          <li <c:if test="${page <= 1}">class="disabled"</c:if>><a href="javascript:void(0);" data="${page-1}">Prev</a></li>
          <c:if test="${startPage > 1}"><li><a href="javascript:void(0);" data="${page-4}">...</a></li></c:if>
          <c:forEach begin="${startPage}" end="${endPage}" varStatus="index">
          <li <c:if test="${page == index.index}">class="active"</c:if>><a href="javascript:void(0);" data="${index.index}">${index.index}</a></li>
          </c:forEach>
          <c:if test="${startPage + 7 < totalPage}">
            <li><a href="javascript:void(0);" data="${startPage + 7}">...</a></li>
            <li><a href="javascript:void(0);" data="${totalPage}">${totalPage}</a></li>
          </c:if>
          <c:if test="${startPage + 7 == totalPage}">
            <li><a href="javascript:void(0);" data="${totalPage}">${totalPage}</a></li>
          </c:if>
          <li <c:if test="${page >=  totalPage}">class="disabled"</c:if>><a href="javascript:void(0);" data="${page+1}">Next</a></li>
        </ul>
      </div>
    </c:if>
	</div>
<div style="display:nonoe">
  <form id="newsList" action="/content/news/list" method="post">
    <input type="hidden" name="newsId" value="${search.newsId}">
    <input type="hidden" name="newsTitle" value="${search.newsTitle}">
    <input type="hidden" name="catId" value="${search.catId}">
    <input type="hidden" name="newsStatus" value="${search.newsStatus}">
    <input type="hidden" name="page" value="${page}">
  </form>
</div>
<%@include file="../inc/footer.jsp"%>
<script src="${adminUrl}/lib/jquery.cookie.js"></script>
<script type="text/javascript">
function openwinx(url,name,w,h) {
    if(!w) w=screen.width-4;
    if(!h) h=screen.height-95;
    window.open(url,name,"top=0,left=0,width=" + w + ",height=" + h + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
}

$("#addNews").bind("click", function(e){
    e.preventDefault();
    var url = $(this).attr("data");
    openwinx(url, '');
});

$(".action-edit").live("click", function(e){
	e.preventDefault();
	var url = $(this).attr("data");
	openwinx(url,'');
});


$(".pagination li a").live("click", function(e){
  if($(this).parent().hasClass("active") || $(this).parent().hasClass("disabled")){
    return ;
  }
  var page = parseInt($(this).attr("data"));
  $("#newsList input[name='page']").val(page);
  $("#newsList").submit();
});

$(".action-del").live("click",function(e){
  var data = $(this).attr("data");
  alert("删除后不可恢复，是否确认删除？", 2, "删除新闻", function(){window.location.href = data;});
});

$.cookie('refersh_time', 0);
function refersh_window() {
  var refersh_time = $.cookie('refersh_time');;
  if(refersh_time==1) {
    $.removeCookie('refersh_time');
    window.location.reload();
  }
}
setInterval("refersh_window()", 3000);
</script>
</body>
</html>