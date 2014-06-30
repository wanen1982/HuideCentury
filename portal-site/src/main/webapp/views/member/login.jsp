<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="/views/bootstrap/inc.jsp" />
<link rel="stylesheet" href="${siteUrl}/css/form.css" />
<title>谷米互动（北京）科技发展有限公司</title>
<script type="text/javascript">
function refresh(obj) {
	obj.src = "/member/verifyCode?" + Math.random();
}
$(document).ready(function() {
	$("#form1 input,select,textarea").not("[type=submit]").jqBootstrapValidation();
			$("#loginBut").click(
					function(e) {
						$.ajax({
									//contentType :"text/json;charset=utf-8",
									dataType : "json",
									type : "POST",
									url : "/member/login",
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
										nickname : $.trim($("#nickname")
												.val()),
										pwd : $.trim($("#pwd").val()),
										randomCode : $
												.trim($("#randomCode")
														.val())
									}
								});
					});
});
</script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal form-login" role="form" method="post" id="form1" name="form1">
			<h2 class="form-login-heading" align="center">会员登录</h2>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" value="" id="nickname" name="nickname" 
					  class="form-control" placeholder="会员名称" required autofocus>
				</div>
				<label for="nickname" class="col-sm-2 control-label"></label>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="password" id="pwd" name="pwd" class="form-control" placeholder="密码" required>
				</div>
				<label for="pwd" class="col-sm-2 control-label"></label>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					<input type="text" id="randomCode" name="randomCode" class="form-control" placeholder="验证码" required autofocus>
				</div>
				<label for="randomCode" class="col-sm-2 control-label" style="margin-left: -30px;">
					<img title="点击更换" onclick="javascript:refresh(this);" src="/member/verifyCode"
					class="img-thumbnail" />
				</label>
			</div>
			<div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10" align="center">
		        <button class="btn btn-primary" type="button" id="loginBut" style="margin-right: 15px;">登&nbsp;录</button>
				<button class="btn btn-primary" type="reset" id="resetBut" name="resetBut" style="margin-left: 15px;">重&nbsp;置</button>
		    </div>
		  </div>
		</form>
	</div>
<jsp:include page="/views/bootstrap/modal.jsp"/>
</body>
</html>