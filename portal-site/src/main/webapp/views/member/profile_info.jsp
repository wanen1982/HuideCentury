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
<script type="text/javascript" src="${siteUrl}/js/member/city.js"></script>
<script type="text/javascript" src="${siteUrl}/js/member/birthday.js"></script>
<script type="text/javascript">
	function refresh(obj) {
		obj.src = "/member/verifyCode?" + Math.random();
	}
	$(document).ready(function() {
		_initCity();
		$("#prov").bind("change",function(e){
			_initCity();
			$("#city").attr("_city",$("#city option:selected").val());
		});
		$().ininBirthday($("#bir_yyyy"), $("#bir_month"), $("#bir_day"));
		/**
		初始化控件值
		*/
		if("${sessionUserInfo.sex}"=="男"){
			$("#sex_man").attr({"checked":"checked"});
		}else{
			$("#sex_woman").attr({"checked":"checked"});
		}
		var homeland = "${sessionUserInfo.homeland}".split(",");
		$("#prov option").each(function(index,val){
			if($(val).text()==homeland[0]){
				$(val).attr({"selected":"selected"});
				$("#city").attr("_city",homeland[1]);
				_initCity();
				return false;
			}
		});
		var birthday = "${sessionUserInfo.birthday}".split("-");
		$("#bir_yyyy option").each(function(index,el){
			if($(el).val()==birthday[0]){
				$(el).attr({"selected":"selected"});
				$(el).trigger("change");
				return false;
			}
		});
		$("#bir_month option").each(function(index,el){
			if($(el).val()==birthday[1]){
				$(el).attr({"selected":"selected"});
				$(el).trigger("change");
				return false;
			}
		});
		$("#bir_day option").each(function(index,el){
			if($(el).val()==birthday[2]){
				$(el).attr({"selected":"selected"});
				return false;
			}
		});
		$("#personalDesc").val("${sessionUserInfo.personalDesc}");
		/**end*/
		
		$("#personalDesc").bind("keyup",function(e){
			var count = $("#personalDesc").val().length;
			if(count > 400){
				$("#ta_count_view").css({"color":"red"});
			}else{
				$("#ta_count_view").css({"color":"black"});
			}
			$("#ta_count_view").html(count);
		});
		$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
			submitSuccess : function($form, event) {
				event.preventDefault();
				$.ajax({
					dataType : "json",
					type : "POST",
					url : "/member/completeInfo",
					success : function(data, textStatus) {
						$("#myModal .modal-body").html(data.message);
						$('#myModal').modal();
					},
					data : {
						id : $.trim($("#memberId").val()),
						sex : $(":radio[name=sex]:checked").val(),
						homeland : $.trim($("#prov option:selected").text()+","+$("#city").val()),
						birthday : $("#bir_yyyy").val()+"-"+$("#bir_month").val()+"-"+$("#bir_day").val(),
						personalDesc:$.trim($("#personalDesc").val())
					}
				});
			}
		});
		//返回首页
		$("#goIndexBut").bind("click",function(e){
			self.location = "/";
		});
	});
	
</script>
</head>
<body>
<div style="width: 100%;text-align:left;">
	<form class="form-horizontal" role="form" method="post" id="form1"
		name="form1">
		<input type="hidden" id="memberId" value="${sessionUserInfo.id}">
		<h2 align="center">资料完善</h2>
		<div class="control-group">
			<label class="control-label" style="margin-left: 40%;">性别：</label>
			<div class="controls" style="margin-left: 40%;">
				<input type="radio" name="sex" id="sex_man" checked="checked"value="男" />男
				<input type="radio" name="sex" id="sex_woman" value="女"/>女
				<p class="help-block"></p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="margin-left: 40%;">所在地：</label>
			<div class="controls" style="margin-left: 40%;">
				<select id="prov" name="province" required="required">
					<option value="">请选择</option>
					<option value="1">北京市</option>
					<option value="2">天津市</option>
					<option value="3">河北省</option>
					<option value="4">山西省</option>
					<option value="5">辽宁省</option>
					<option value="6">吉林省</option>
					<option value="7">上海市</option>
					<option value="8">江苏省</option>
					<option value="9">浙江省</option>
					<option value="10">安徽省</option>
					<option value="11">福建省</option>
					<option value="12">江西省</option>
					<option value="13">山东省</option>
					<option value="14">河南省</option>
					<option value="15">内蒙古</option>
					<option value="16">黑龙江省</option>
					<option value="17">湖北省</option>
					<option value="18">湖南省</option>
					<option value="19">广东省</option>
					<option value="20">广西</option>
					<option value="21">海南省</option>
					<option value="22">四川省</option>
					<option value="23">重庆市</option>
					<option value="24">台湾省</option>
					<option value="25">贵州省</option>
					<option value="26">云南省</option>
					<option value="27">西藏</option>
					<option value="28">陕西省</option>
					<option value="29">甘肃省</option>
					<option value="30">青海省</option>
					<option value="31">宁夏</option>
					<option value="32">新疆</option>
					<option value="33">香港特别行政区</option>
					<option value="34">澳门特别行政区</option>
					<option value="35">其他</option>
				</select> 
				<select id="city" name="city" _city="1" required="required">
				</select>
				<p class="help-block"></p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="margin-left: 40%;">生日：</label>
			<div class="controls"  style="margin-left: 40%;">
				<select id="bir_yyyy" required="required">
					<option value="">请选择</option>
				</select>
				 <select id="bir_month" required="required">
					<option value="">请选择</option>
				</select>
				<select id="bir_day" required="required">
					<option value="">请选择</option>
				</select>
				<p class="help-block"></p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="margin-left: 40%;">个人简介：</label>
			<div class="controls" style="margin-left: 40%;">
				<textarea id="personalDesc" name="personalDesc" rows="4" style="width: 30%" maxlength="400"></textarea>
				<span id="ta_count_view">0</span>/400
				<p class="help-block"></p>
			</div>
		</div>
		<div class="control-group">
			<div class="controls"  style="margin-left: 40%;">
				<button class="btn btn-primary" type="submit" id="updateMemberInfoBut"
					name="updateMemberInfoBut" style="margin-right: 15px;">保&nbsp;存</button>
				<button class="btn btn-primary" type="button" id="goIndexBut"
					name="goIndexBut" style="margin-left: 15px;">返&nbsp;回</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="/views/bootstrap/modal.jsp"/>
</body>
</html>