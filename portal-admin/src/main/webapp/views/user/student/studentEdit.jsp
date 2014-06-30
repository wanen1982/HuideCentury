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
		<a href="javascript:void(0);" class="on"> <em>编辑学生</em></a>
	</div>
	<form id="addAdminUser" class="form-horizontal" action="/user/admin/add" method="post">
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">基本信息</li>
				</ul>
				<div id="div_setting_1" class="contentList pad-10">
						<div class="control-group">
							<label class="control-label">学号:</label>
							<div class="controls">
								${stuInfo.studyNo}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>电子邮箱:</label>
							<div class="controls">
								<input type="email" id="email" name="email" value="" maxlength="20"  readonly="readonly"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>姓名:</label>
							<div class="controls">
								<input type="text" id="nickname" name="nickname" value="" maxlength="20" readonly="readonly"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" ">性别：</label>
							<div class="controls" >
								<input type="radio" name="sex" id="sex_man" checked="checked" value="男" />男
								<input type="radio" name="sex" id="sex_woman" value="女"/>女
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >所在地：</label>
							<div class="controls">
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
							<label class="control-label" >出生日期：</label>
							<div class="controls">
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
							<label class="control-label">个人简介：</label>
							<div class="controls">
								<textarea id="personalDesc" name="personalDesc" rows="4" style="width: 30%" maxlength="400"></textarea>
								<span id="ta_count_view">0</span>/400
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>手机号码：</label>
							<div class="controls">
								<input type="text" id="mobilephone" name="mobilephone" value="" maxlength="20" required/>
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">家庭地址：</label>
							<div class="controls">
								<input type="text" id="address" name="address" value="" maxlength="100"/>
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>余额：</label>
							<div class="controls">
								${(empty stuInfo.balance)?'0':stuInfo.balance}
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">学生ID：</label>
							<div class="controls">
								<input type="text" id="studentId" name="studentId" value="${stuInfo.studentId }" maxlength="50"/>
								<p class="help-block"></p>
							</div>
						</div>
				</div>
				<div class="saveInfo"><button id="submitBtn" class="btn btn-primary" type="submit"><i class="icon-check icon-white"></i> 保存</button></div>
			</div>
		</div>
		</form>
<%@include file="/views/inc/footer.jsp"%>
<script type="text/javascript" src="${adminUrl}/js/member/city.js"></script>
<script type="text/javascript" src="${adminUrl}/js/member/birthday.js"></script>
<script type="text/javascript" src="${adminUrl}/js/youda.utils.js"></script>
<script type="text/javascript">

_initCity();
$("#prov").live("change",function(e){
	_initCity();
	$("#city").attr("_city",$("#city option:selected").val());
});
$().ininBirthday($("#bir_yyyy"), $("#bir_month"), $("#bir_day"));
/**
初始化控件值
*/
$("#email").val("${stuInfo.email}");
$("#nickname").val("${stuInfo.nickname}");
if("${stuInfo.sex}"=="男"){
	$("#sex_man").attr({"checked":"checked"});
}else{
	$("#sex_woman").attr({"checked":"checked"});
}
var homeland = "${stuInfo.homeland}".split(",");
$("#prov option").each(function(index,val){
	if($(val).text()==homeland[0]){
		$(val).attr({"selected":"selected"});
		$("#city").attr("_city",homeland[1]);
		_initCity();
		return false;
	}
});
var birthday = "${stuInfo.birthday}".split("-");
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
$("#personalDesc").val("${stuInfo.personalDesc }");
/**end*/
 
$("#personalDesc").live("keyup",function(e){
	var count = $("#personalDesc").val().length;
	if(count > 400){
		$("#ta_count_view").css({"color":"red"});
	}else{
		$("#ta_count_view").css({"color":"black"});
	}
	$("#ta_count_view").html(count);
});
$("#mobilephone").val("${(empty stuInfo.mobilephone)?'':stuInfo.mobilephone}");
$("#address").val("${(empty stuInfo.address)?'':stuInfo.address}");
$(function(){
	$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
		submitSuccess : function($form, event) {
			event.preventDefault();
			$.ajax({
				dataType : "json",
				type : "POST",
				url : "/user/student/edit",
				success : function(data, textStatus) {
					alert(data.message, 2, "提示", function(){
						self.location="/user/student/list/1";
					});
				},
				data : {
					id:"${stuInfo.id}",
					sex : $(":radio[name=sex]:checked").val(),
					homeland : $.trim($("#prov option:selected").text()+","+$("#city").val()),
					birthday : $("#bir_yyyy").val()+"-"+$("#bir_month").val()+"-"+$("#bir_day").val(),
					personalDesc:$.trim($("#personalDesc").val()),
					mobilephone:$.trim($("#mobilephone").val()),
					address:$.trim($("#address").val()),
					studentId:$.trim($("#studentId").val())
				}
			});
		}
	});
});

</script>
</body>
	</html>