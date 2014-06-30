<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>菜单管理</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link href="${adminUrl}/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${adminUrl}/css/g-system.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
body{padding-left: 10px;}

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
.naviBG{background-color:#d80000;height:50px;width:50px;display:block;}
.naviBG img{height:50px;width:50px;}
.targetURL{width:200px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;display: block;}
.emptyMove{display:inline-block;width:64px;height:26px;}


.desc{display: block; width: 200px;}
.carouselImg{width: 200px; height: 64px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="javascript:;" class="on"> <em>轮播管理</em></a>
		<span>|</span>
		<a href="/model/carousel/add"> <em>添加轮播项</em></a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<!-- div class="explain-col">温馨提示：当前模板菜单项最多允许添加${model.maxCategorynums}个栏目。</div -->
		<div class="bk10"></div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="50px">序号</th>
					<th width="210px">轮播图片</th>
					<th width="120px">图片标题</th>
					<th width="180px">图片描述</th>
					<th width="180px">链接地址</th>
					<th width="150px">排序</th>
					<th>管理操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${carouselList}" var="carousel" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td><img class="carouselImg" src="${imageUrl}${carousel.picpath}"></td>
						<td>${carousel.title}</td>
						<td><span class="desc">${carousel.description}</span></td>
						<td><span class="desc">${carousel.linkUrl}</span></td>
						<td>
							<div class="btn-group">
								<a class="btn btn-small <c:if test="${index.count == 1}">disabled</c:if>" href="/model/carousel/moveUp/${carousel.id}"> <i class="icon-arrow-up"></i>
									上移
								</a>
								<a class="btn btn-small <c:if test="${index.count == fn:length(carouselList)}">disabled</c:if>" href="/model/carousel/moveDown/${carousel.id}"> <i class="icon-arrow-down"></i>
									下移
								</a>
							</div>
						</td>
						<td>
							<div class="btn-group">
								<a class="btn btn-small btn-info" href="/model/carousel/edit/${carousel.id}">
									<i class="icon-edit icon-white"></i>
									编辑
								</a>
								<a class="btn btn-small btn-danger action-del" href="#" data="/model/carousel/delete/${carousel.id}">
									<i class="icon-trash icon-white"></i>
									删除
								</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="../inc/footer.jsp"%>
	<script type="text/javascript">
$(".action-del").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	alert("确认删除该轮播图片？", 2, "删除图片", function(){window.location.href = href;});
});
$(".disabled").live("click",function(e){e.preventDefault();});
</script>
</body>
</html>