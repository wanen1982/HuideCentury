<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>谷米互动（北京）科技发展有限公司</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="/views/bootstrap/inc.jsp" />
<link rel="stylesheet" href="${siteUrl}/css/form.css" />
<script type="text/javascript" src="${siteUrl}/js/member/member.js"></script>
<script type="text/javascript">
function refresh(obj) {
	obj.src = "/member/verifyCode?" + Math.random();
}
$(document).ready(function() {
	$("#pwd").keydown(function(event){
		$("#pwd").countLevel($("#pwdStrong"));
	});
	$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
		submitSuccess: function($form, event) {
            event.preventDefault();
            $.ajax({
            	dataType : "json",
				type : "POST",
				url : "/member/register",
				success : function(data, textStatus) {
					$("#myModal .modal-body").html(data.message);
					$('#myModal').modal();
					$('#myModal').on('hidden.bs.modal', function (e) {
						if (data.success) {
							self.location = "/";
							return;
						}
					});
				},
				data : {
					email:$.trim($("#email").val()),
					nickname : $.trim($("#nickname").val()),
					pwd : $.trim($("#pwd").val()),
					randomCode : $.trim($("#randomCode").val())
				}
            });
        }
	});
	
});

</script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal form-register" role="form" method="post" id="form1"
			name="form1">
			<h2 class="form-register-heading" align="center">会员注册 </h2>
			<div class="control-group form-group">
				<div class="controls col-sm-10">
					<input type="email" name="email" id="email" 
					  class="form-control" placeholder="电子邮箱地址" required autofocus maxlength="100" 
					  minlength="8" data-validation-minlength-message="电子邮箱最少8位字符组成"/>
					  <p class="help-block"></p>
				</div>
			</div>
			<div class="control-group form-group">
				<div class="controls col-sm-10">
					<input type="text"  name="nickname" id="nickname" 
					  class="form-control" placeholder="会员名称" required maxlength="100" 
					  minlength="8" data-validation-minlength-message="会员名称最少8位字符组成" />
					  <p class="help-block"></p>
				</div>
			</div>
			<div class="control-group form-group">
				<div class="controls col-sm-10">
					<input type="password" name="pwd" id="pwd" class="form-control" placeholder="密码" required maxlength="50"
					minlength="8" data-validation-minlength-message="密码最少8位" />
					 <p class="help-block">
					   <div class="btn-group" id="pwdStrong">
						  <button type="button" class="btn btn-default btn-primary" disabled="disabled">弱</button>
						  <button type="button" class="btn btn-default" disabled="disabled">中</button>
						  <button type="button" class="btn btn-default" disabled="disabled">强</button>
						</div>
					 </p>
				</div>
			</div>
			<div class="control-group form-group">
				<div class="controls col-sm-10">
					<input type="password" name="pwd2" class="form-control" placeholder="确认密码" required
					data-validation-match-match="pwd" data-validation-match-message="确认密码应与密码相同"/>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group form-group">
				<div class="controls col-sm-10">
					<input type="text" name="randomCode" id="randomCode" class="form-control" placeholder="验证码" required
					maxlength="4" minlength="4" data-validation-minlength-message="验证码必须输入4位" >
					<p class="help-block"></p>
				</div>
				<label for="randomCode" class="col-sm-2 control-label" style="margin-left: -30px;">
					<img title="点击更换" onclick="javascript:refresh(this);" src="/member/verifyCode"
					class="img-thumbnail" />
				</label>
			</div>
			<div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10" align="center">
		       <button class="btn btn-primary" type="submit" id="loginBut" name="loginBut" style="margin-right: 15px;">注&nbsp;册</button>
				<button class="btn btn-primary" type="reset" id="resetBut" name="resetBut" style="margin-left: 15px;">重&nbsp;置</button>
		    </div>
		  </div>
		</form>
	</div>
	<jsp:include page="/views/bootstrap/modal.jsp"/>
</body>
</html>