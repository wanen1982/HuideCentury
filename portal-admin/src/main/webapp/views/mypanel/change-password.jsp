<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>密码修改</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/table_form.css"/>
	<style type="text/css">
body{ padding-left: 10px; }
.form-horizontal .control-label { padding: 5px 0 5px 0; color: #777}
.form-horizontal .control-group { margin-bottom: 10px; border-bottom: 1px solid #eee; }
.info{display:block; padding-top: 4px; font-size: 14px;}
.col-tab {width: 60%;}
.saveInfo{margin: 10px 0 0 97px;}
label, input, button, select, textarea { font-size: 12px; font-weight: normal; line-height: 20px; }
select, input[type="file"]{ height: 26px; line-height: 30px; }
input[type="text"]{ height:16px; font-size:12px; }
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
		<a href="javascript:void(0);" class="on"> <em>修改密码</em>
		</a>
	</div>
	<div class="main-content">
		<div class="pad-10">
			<c:if test="${!empty errorMSG}"><div class="explain-col">温馨提示: ${errorMSG}</div></c:if>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">修改信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
					<form id="changePassword" class="form-horizontal" action="/mypanel/changePassword" method="post" enctype="multipart/form-data">
						<div class="control-group">
						    <label class="control-label">修改密码为:</label>
						    <div class="controls">
						      <input type="password" name="password" id="pwd1" maxlength="30" />
						      <p class="help-block"></p>
						    </div>
						</div>
						<div class="control-group">
						    <label class="control-label">确认密码:</label>
						    <div class="controls">
						      <input type="password" name="re-password" id="rePwd" maxlength="30" />
						      <p class="help-block"></p>
						    </div>
						</div>
					</form>
				</div>
				<div class="saveInfo"><button id="saveBtn" class="btn btn-primary" type="submit"><i class="icon-check icon-white"></i> 保存</button></div>
			</div>
		</div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">
$(function(){
	$("#pwd1").live("keydown",function(e){
		$(".help-block").empty();
	});
	$("#saveBtn").live("click", function(){
		$(".help-block").empty();
		var pwd1 = $.trim($("#pwd1").val());
		var rePwd = $.trim($("#rePwd").val());
		if(pwd1==""){
			$("#pwd1").next(".help-block").append("请输入密码");
			return ;
		}
		if(pwd1.length < 6){
			$("#pwd1").next(".help-block").append("密码不能少于6位");
			return ;
		}
		if(pwdLevel(pwd1)!=2){
			$("#pwd1").next(".help-block").append("密码必须由数字和字符串组成");
			return ;
		}
		if(rePwd!=pwd1){
			$("#rePwd").next(".help-block").append("确认密码必须与修改密码相同");
			return ;
		}
		$("#changePassword").submit();
	});
});
function pwdLevel(str){
    var a=0,b=0;
	for(var i=0;i<str.length;i++){
		if(str.charAt(i).charCodeAt(0)>=48 && str.charAt(i).charCodeAt(0) <=57){//数字
			a = 1;
		}else if( (str.charAt(i).charCodeAt(0)>=65 && str.charAt(i).charCodeAt(0)<=90) || (str.charAt(i).charCodeAt(0)>=97 && str.charAt(i).charCodeAt(0)<=122) ){//字母
			b = 1;
		}
	}
	return (a+b);
};

</script>
</body>
	</html>