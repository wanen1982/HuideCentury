<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>栏目管理</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link href="${adminUrl}/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${adminUrl}/css/g-system.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
body{padding-left: 10px;}

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
.nextLevel{padding-left:15px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="javascript:;" class="on"> <em>管理栏目</em>
		</a>
		<span>|</span>
		<a href="/content/category/add"> <em>添加栏目</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" style="display:none">
				温馨提示：当前模板菜单项需要选择6个栏目进行填充。
			</div>
			<div class="bk10"></div>
			<table class="table table-hover table-condensed">
              <thead>
                <tr>
                  <th width="50px">栏目ID</th>
                  <th width="350px">栏目名称</th>
                  <th width="150px">栏目类型</th>
                  <th width="160px">排序</th>
                  <th>管理操作</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach var="category" items="${categoryLlist}">
              		<tr>
						<td>${category.catId}</td>
						<td>
							<c:if test="${category.level <= 0}">
								${category.catName}
							</c:if>
							<c:if test="${category.level > 0}">
								<c:forEach begin="2" end="${category.level}">
									<span class="nextLevel">│</span>
								</c:forEach>
								<c:choose>
								<c:when test="${category.lastFlag == 1}"><span class="nextLevel">└─ ${category.catName}</span></c:when>
								<c:otherwise><span class="nextLevel">├─ ${category.catName}</span></c:otherwise>
								</c:choose>
							</c:if>
						</td>
						<td>
						<c:choose>
						<c:when test="${category.type == 'NEWS-CATEGORY'}"><span class="label label-success">内容栏目</span></c:when>
						<c:when test="${category.type == 'CATEGORY'}"><span class="label label-warning">目录栏目</span></c:when>
						<c:when test="${category.type == 'PAGE'}"><span class="label label-info">单网页</span></c:when>
						<c:when test="${category.type == 'TOUR'}"><span class="label label-info">旅游页</span></c:when>
						<c:otherwise></c:otherwise></c:choose>
						</td>
						<td>
							<div class="btn-group">
								<a class="btn btn-small <c:if test="${category.startFlag == 1}">disabled</c:if>" href="/content/category/moveup/${category.catId}"> <i class="icon-arrow-up"></i> 上移</a>
								<a class="btn btn-small <c:if test="${category.lastFlag == 1}">disabled</c:if>" href="/content/category/movedown/${category.catId}"> <i class="icon-arrow-down"></i> 下移</a>
							</div>
						</td>
						<td>
							<a class="btn btn-small btn-success <c:if test="${category.type == 'PAGE' || category.level > 0}">disabled</c:if>" href="/content/category/add?cid=${category.catId}"><i class="icon-plus-sign icon-white"></i> 添加子栏目</a>
							<a class="btn btn-small btn-info" href="/content/category<c:if test="${category.type == 'PAGE'}">/page</c:if><c:if test="${category.type == 'TOUR'}">/tour</c:if>/edit?cid=${category.catId}"><i class="icon-edit icon-white"></i> 编辑</a>
							<c:if test="${category.needApprove == 0}">
							<a class="btn btn-small btn-danger delete" href="javascript:void(0);" data="/content/category/del/${category.catId}"><i class="icon-trash icon-white"></i> 删除</a>
							</c:if>
						</td>
					</tr>
              	</c:forEach>
              </tbody>
            </table>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
var delFunction = function(url){
	window.location.href = url;
}
$(".delete").bind("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该栏目及其所有子栏目？", 2, "删除栏目", function(){window.location.href = href;});
});

$(".disabled").bind("click",function(e){e.preventDefault();});
</script>
</body>
	</html>