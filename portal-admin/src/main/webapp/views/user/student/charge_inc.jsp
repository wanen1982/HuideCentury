<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>添加管理员</title>
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
.required{color: #d80000;padding: 0 5px;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="/user/student/list/1"> <em>学生管理</em>
		</a>
		<span>|</span>
		<a href="javascript:void(0);" class="on"> <em>充值</em></a>
	</div>
	<form id="form1" class="form-horizontal" action="/user/student/charge" method="post">
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">充值操作</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<div class="control-group">
						<label class="control-label">姓名:</label>
						<div class="controls">
							<input type="hidden" name="studentId" id="studentId" value="${student.id}"/>
							${student.nickname}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="required">*</span>充值金额:</label>
						<div class="controls">
							<input type="text" class="form-control" id="chargeCost" name="chargeCost"
								placeholder="请输入充值金额" value="0.0" required="required"  maxlength="8"
								data-validation-callback-callback="checkChargeCost">
						</div>
					</div>
					<div class="control-group">
						<div class="saveInfo"><button id="submitBtn" class="btn btn-primary" type="submit"><i class="icon-check icon-white"></i> 确认充值</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
<%@include file="/views/inc/footer.jsp"%>
<script type="text/javascript" src="${adminUrl}/js/youda.utils.js"></script>
<script type="text/javascript">
function checkChargeCost($el, value, callback){
	callback({
      value: value,
      valid: /^[0-9]+(\.[0-9]+){0,1}$/.test(value),
      message: "必须输入数字"
    });
}
$(function(){
	$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
		submitSuccess : function($form, event) {
			event.preventDefault();
			$.ajax({
				dataType : "json",
				type : "POST",
				url : "/user/student/charge",
				success : function(data, textStatus) {
					if(data.success){
						alert(data.message, 2, "提示", function(){
							self.location="/user/student/list/1";
						});
					}else{
						alert(data.message, 1, "提示");
					}
				},
				data : {
					studentId:$("#studentId").val(),
					chargeCost : $("#chargeCost").val()
				}
			});
		}
	});
});
</script>
</body>
</html>
