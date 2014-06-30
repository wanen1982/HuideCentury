<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加栏目</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/table_form.css"/>
	<style type="text/css">
body{ padding-left: 10px; }

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
.page-title{ margin: 15px 0 10px 25px; }

blockquote { border-left: 8px solid #006dcc; }
blockquote { padding: 0 0 0 15px; margin: 0 0 20px; }
h3 { font-size: 22px; }
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"] { height: 26px; line-height: 30px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.saveInfo{ margin: 10px 0 0 97px; }

input[type="text"] { height: 18px; font-size: 12px; }
.uneditable-input {font-size:12px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="/content/category/manage"> <em>管理栏目</em>
		</a>
		<span>|</span>
		<a href="/content/category/add" <c:if test="${empty edit}">class="on"</c:if>> <em>添加栏目</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" style="display:none">温馨提示:</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本选项</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="addCategoryForm" class="form-horizontal" action="/content/category/update" method="post">
						<input type="hidden" id="cid" name="cid" value="${category.catId}" />
						<div class="control-group">
							<label class="control-label">栏目类型:</label>
							<div class="controls">
								<span class="span2 uneditable-input">
									<c:choose>
										<c:when test="${category.type == 'NEWS-CATEGORY'}">内容栏目</c:when>
										<c:when test="${category.type == 'CATEGORY'}">目录栏目</c:when>
										<c:when test="${category.type == 'PAGE'}">单网页</c:when>
									</c:choose>
								</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级栏目:</label>
							<div class="controls">
								<select class="span2" name="parentCategory" id="parentCat">
									<option value="0" <c:if test="${empty category.parentId}">selected</c:if>>≡ 作为一级栏目 ≡</option>		
									<c:forEach var="select" items="${selectList}">
										<c:if test="${select.level <= 0}">
											<option value="${select.catId}" <c:if test="${category.parentId == select.catId}">selected</c:if>>${select.catName}</option>
										</c:if>
										<c:if test="${select.level > 0}">
											<option value="${select.catId}" <c:if test="${category.parentId == select.catId}">selected</c:if>>
											<c:forEach begin="2" end="${select.level}">
												&nbsp;│
											</c:forEach>
											<c:choose>
											<c:when test="${select.lastFlag == 1}">&nbsp;└ ${select.catName}</c:when>
											<c:otherwise>&nbsp;├ ${select.catName}</c:otherwise>
											</c:choose>
											</option>
										</c:if>
									</c:forEach>
									
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">栏目名称:</label>
							<div class="controls">
								<input type="text" id="catName" name="categoryName" value="${category.catName}">
								<span class="help-inline"><div id="catNameTip" class="onShow">请输入栏目名称</div></span>
								<input type="hidden" id="catNameCheck" value="1" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">英文目录:</label>
							<div class="controls">
								<input type="text" id="catPath" name="categoryPathName" value="${category.pathName}">
								<span class="help-inline"><div id="catPathTip" class="onShow">请输入英文目录</div></span>
								<input type="hidden" id="catPathCheck" value="1" />
							</div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="btn btn-primary" type="button"><i class="icon-check icon-white"></i> 保存</button></div>
				<input type="hidden" id="submitFlag" value="0" />
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
var CheckAndSubmit = function(){
	if($("#catNameCheck").val() <= 0) return;
	if($("#catPathCheck").val() <= 0) return;
	if($("#submitFlag").val() <= 0) return;
	$("#addCategoryForm").submit();
}
$("#submitBtn").live("click", function(){
	$("#submitFlag").val(1);
	CheckAndSubmit();
});
$("#catName").live("blur", function(){
	$("#catNameCheck").val(0);
	var catName = $(this).val();
	var cid = $("#cid").val();
	$.ajax({
		url:"/content/category/checkName",
		type:"POST",
		data:{catName:catName,cid:cid},
		success:function(rsp){
			if(rsp.status > 0){
				$("#catNameTip").attr("class","onError");
				$("#catNameTip").html("该栏目名称已存在！");
			}
			else{
				$("#catNameTip").attr("class","onCorrect");
				$("#catNameTip").html("该栏目名称可用。");
				$("#catNameCheck").val(1);
				CheckAndSubmit();
			}
			
		}			
	})
});
$("#catPath").live("blur", function(){
	$("#catPathCheck").val(0);
	var catPathName = $(this).val();
	var cid = $("#cid").val();
	$.ajax({
		url:"/content/category/checkPathName",
		type:"POST",
		data:{catPathName:catPathName,cid:cid},
		success:function(rsp){
			if(rsp.status > 0){
				$("#catPathTip").attr("class","onError");
				$("#catPathTip").html("该英文目录已存在！");
			}
			else{
				$("#catPathTip").attr("class","onCorrect");
				$("#catPathTip").html("该英文目录可用。");
				$("#catPathCheck").val(1);
				CheckAndSubmit();
			}
		}			
	})
});

</script>
</body>
	</html>