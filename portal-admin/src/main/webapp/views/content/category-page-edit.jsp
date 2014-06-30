<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>编辑栏目</title>
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
		<a href="javascript:void(0);" class="on"> <em>编辑栏目</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">${category.catName} - 网页内容编辑</li>
				</ul>
				<form id="catPageUpdateForm" action="/content/category/page/save" method="post" enctype="multipart/form-data">
					<input type="hidden" name="cid" value="${category.catId}" />
					<input type="hidden" name="template" value="PAGE" />
					<div id="div_setting_1" class="contentList pad-10">
						<script type="text/plain" id="content_editer"></script>
						<textarea name="page_content" id="content" class="textItem" style="display:none;width:900px;height:246px;">${catPage.pageContent}</textarea>
					</div>
				</form>
				<div class="saveInfo"><button id="previewBtn" class="btn btn-primary" type="button"><i class="icon-zoom-in icon-white"></i> 预览</button> <button id="submitBtn" class="btn btn-primary" type="button"><i class="icon-check icon-white"></i> 保存</button></div>
				<input type="hidden" id="submitFlag" value="0" />
			</div>
		</div>
	</div>
<div id="preview" style="display:none">
<form id="previewForm" action="/content/category/page/preview" method="post" target="_blank">
	<input type="hidden" name="cid" value="${category.catId}" />
	<textarea id="preview_content" name="content"></textarea>
</form>
</div>
<%@include file="../inc/footer.jsp"%>
<script src="${adminUrl}/lib/ueditor/editor_config.js"></script>
<script src="${adminUrl}/lib/ueditor/editor_all_min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
		//init Editor 
		//some config
	$.extend(window.UEDITOR_CONFIG,{
		UEDITOR_HOME_URL:"/lib/ueditor/"
		//image upload url
		,focus:true
		,initialContent:$("#content").val()
		,imageUrl:"/Editer/imgUpload/category/${category.catId}"
		,imagePath:""
		,imageManagerUrl:"/Editer/imgView/"
		,initialFrameWidth:914
		,initialFrameHeight:380
	})
	var content_editer = UE.getEditor('content_editer');

	$("#submitBtn").live("click", function(){
		$("#content").val(content_editer.getContent());
		$("#catPageUpdateForm").submit();
	});
});

$("#previewBtn").live("click", function(){
	$("#preview_content").val($("#content").val());
	$("#previewForm").submit();
});
</script>
</body>
	</html>