<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>个人信息</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/table_form.css"/>
	<style type="text/css">
body{ padding-left: 10px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.info{display:block; padding-top: 6px; font-size: 12px;}
.col-tab {width: 60%;}
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"]{ height: 26px; line-height: 30px; }
input[type="text"]{ height:16px; font-size:12px; }
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="/model/menu/manage" class="on"> <em>我的个人信息</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10 form-horizontal">
						<div class="control-group">
						    <label class="control-label">用户名称:</label>
						    <div class="controls">
						      <span class="info">${user.username}</span>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">所属角色:</label>
						    <div class="controls">
						      <span class="info"><c:choose><c:when test="${user.roleid == 0}">超级管理员</c:when><c:when test="${user.roleid == 0}">网站运营</c:when></c:choose></span>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">上次登陆IP:</label>
						    <div class="controls">
						      <span class="info">${lastloginip}</span>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">上次登陆时间:</label>
						    <div class="controls">
						      <span class="info"><fmt:formatDate value="${lastlogintime}" pattern="yyyy-MM-dd HH:mm"/></span>
						    </div>
						</div>
				</div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
</body>
	</html>