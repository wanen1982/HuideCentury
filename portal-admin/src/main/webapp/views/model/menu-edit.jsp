<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>编辑菜单</title>
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
.naviBG{background-color:#d80000;height:50px;width:50px;display:inline-block;}
.naviBG img{height:50px;width:50px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="/model/menu/manage"> <em>菜单管理</em>
		</a>
		<span>|</span>
		<a href="javascript:;" class="on"> <em>编辑菜单项</em>
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
					<form id="addMenu" class="form-horizontal" action="/model/menu/save" method="post" enctype="multipart/form-data">
						<input type="hidden" name="field" value="${modelField.fieldId}" />
						<div class="control-group">
						    <label class="control-label">导航显示名称:</label>
						    <div class="controls">
						      <input type="text" id="showName" name="showName" maxlength="10" value="${modelField.showName}"/>
						    </div>
						</div>
						<div class="control-group" id="CatLink" <c:if test="${modelField.catId == 0}">style="display:none"</c:if>>
							<label class="control-label">请选择关联栏目:</label>
							<input type="hidden" value="${modelField.catId}" id="catLinkDefautValue"/>
							<div class="controls">
								<select class="span2" name="categoryId" id="categoryId" tag="${index.index}">
									<option value="0" >=请选择栏目=</option>
									<c:forEach var="select" items="${selectList}">
										<c:if test="${select.level <= 0}">
											<option value="${select.catId}" <c:if test="${modelField.catId == select.catId}">selected</c:if>>${select.catName}</option>
										</c:if>
										<c:if test="${select.level > 0}">
											<option value="${select.catId}" <c:if test="${modelField.catId == select.catId}">selected</c:if>>
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
								<span class="help-inline"><div id="catnameTip" class="onShow">请选择栏目名称</div></span>
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
	$("#addMenu").submit();
});
</script>
</body>
	</html>