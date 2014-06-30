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
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="javascript:;" class="on"> <em>导航管理</em>
		</a>
		<c:if test="${fn:length(navigationList) < model.maxCategorynums}">
		<span>|</span>
		<a href="/model/menu/add"> <em>添加导航项</em>
		</a>
		</c:if>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col">
				温馨提示：当前导航菜单项最多允许添加${model.maxCategorynums}个栏目。
			</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">首页导航</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<table class="table table-hover">
						<thead>
							<tr>
								<th width="50px">序号</th>
								<th width="120px">导航显示名称</th>
								<th width="120px">关联栏目名称</th>
								<th width="190px">排序</th>
								<th>管理操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${navigationList}" var="item" varStatus="index">
								<tr>
									<td>${index.count}</td>
									<td>${item.showName}</td>
									<td>${item.linkCategory.catName}</td>
									<td>
										<div class="btn-group">
											
											<a class="btn btn-small <c:if test="${index.count == 1}">disabled</c:if>" href="/model/menu/moveUp/${item.fieldId}"> <i class="icon-arrow-up"></i> 上移</a>
											<a class="btn btn-small <c:if test="${index.count == fn:length(navigationList)}">disabled</c:if>" href="/model/menu/moveDown/${item.fieldId}"> <i class="icon-arrow-down"></i> 下移</a>
										</div>
									</td>
									<td>
										<div class="btn-group">
											<c:if test="${item.editable == 0}"><span class="emptyBtn">&nbsp;</span></c:if>
											<c:if test="${item.editable == 1}">
											<a class="btn btn-small btn-info"  href="/model/menu/edit/${item.fieldId}"> <i class="icon-trash icon-white"></i> 编辑
											</a>
											</c:if>
											<c:if test="${item.locked == 1}"><span class="emptyBtn">&nbsp;</span></c:if>
											<c:if test="${item.locked == 0}">
											<a class="btn btn-small btn-danger action-del" href="#" data="/model/menu/del/${item.fieldId}"> <i class="icon-edit icon-white"></i> 删除
											</a>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="div_setting_2" class="contentList pad-10 hidden"></div>
				<div id="div_setting_3" class="contentList pad-10 hidden"></div>
				<div id="div_setting_4" class="contentList pad-10 hidden"></div>
				<div id="div_setting_5" class="contentList pad-10 hidden"></div>
				<div id="div_setting_6" class="contentList pad-10 hidden"></div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
$(".action-del").live("click",function(e){
	e.preventDefault();
	var href = $(this).attr("data");
	console.log(href);
	alert("确认删除该导航栏目？", 2, "删除栏目", function(){window.location.href = href;});
});
$(".disabled").live("click",function(e){e.preventDefault();});
</script>
</body>
	</html>