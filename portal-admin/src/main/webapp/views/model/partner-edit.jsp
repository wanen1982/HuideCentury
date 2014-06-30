<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>编辑合作伙伴</title>
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
select, input[type="file"]{ height: 26px; line-height: 30px; }

input[type="text"]{ height:16px; font-size:12px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.saveInfo{ margin: 10px 0 0 97px; }
.p-icon{width: 83px; height: 40px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="/model/partner/manage"> <em>合作伙伴管理</em>
		</a>
		<span>|</span>
		<a href="javascript:;" class="on"> <em>编辑合作伙伴</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<c:if test="${!empty errorMSG}"><div class="explain-col">温馨提示: ${errorMSG}</div></c:if>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本选项</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="addPartner" class="form-horizontal" action="/model/partner/update" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${partner.id}">
						<div class="control-group">
						    <label class="control-label">公司名称:</label>
						    <div class="controls">
						      <input type="text" name="name" maxlength="30" value="${partner.name}"/>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">公司图标:</label>
						    <div class="controls">
						    	<img class="p-icon" src="${imageUrl}${partner.iconPath}">
						      <input type="file" name="icon"/>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">链接地址:</label>
						    <div class="controls">
						      <input type="text" name="linkUrl" maxlength="255" value="${partner.linkUrl}"/>
						    </div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="saveBtn" class="btn btn-primary" type="button"><i class="icon-check icon-white"></i> 保存</button></div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
$("#saveBtn").live("click", function(){
	$("#addPartner").submit();
});
</script>
</body>
	</html>