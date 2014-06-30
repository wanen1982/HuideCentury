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
		<a href="/user/teacher/list/1"> <em>老师管理</em>
		</a>
		<span>|</span>
		<a href="javascript:void(0);" class="on"> <em>编辑老师</em></a>
	</div>
	<form id="addAdminUser" class="form-horizontal" action="/user/teacher/edit/${teacherInfo.id}" method="post" enctype="multipart/form-data">
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
							<label class="control-label"><span class="required">*</span>姓名:</label>
							<div class="controls">
								<input type="text" id="name" name="name" value="${teacherInfo.name }" maxlength="20" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>年龄:</label>
							<div class="controls">
								<input type="number" id="age" name="age" value="${teacherInfo.age }" maxlength="20" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>学历:</label>
							<div class="controls">
								<input type="text" id="educated" name="educated" value="${teacherInfo.educated }" maxlength="20" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>课程类型:</label>
							<div class="controls">
								<select class="form-control" name="courseType" id="courseType">
									<c:forEach items="${userRoleKeySet}"  var="userRoleKey">
										<c:if test="${userRoleKey > 1}">
										<option value="${userRoleKey}" <c:if test="${userRoleKey==teacherInfo.courseType}">selected="selected"</c:if>>${userRoleMap[userRoleKey] }</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">描述：</label>
							<div class="controls">
								<textarea id="personalDesc" name="personalDesc" rows="4" style="width: 30%" maxlength="400">${(!empty teacherInfo.personalDesc)?teacherInfo.personalDesc:''}</textarea>
								<span id="ta_count_view">
								<span id="hob_taCountView">
								<c:if test="${!empty teacherInfo.personalDesc}">${fn:length(teacherInfo.personalDesc)}</c:if>
								</span>
								</span>/400
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">照片:</label>
							<div class="controls">
								<input type="file" id="photoUrl" name="photo_url" value="" maxlength="100" />
								<c:if test="${!empty teacherInfo.photoUrl}">
									<img src="${teacherInfo.photoUrl}" alt="已上传的照片" class="img-responsive" style="width: 60px;height: 60px;">
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" "><span class="required">*</span>性别：</label>
							<div class="controls" >
								<input type="radio" name="sex" id="sex_man" value="男" <c:if test="${teacherInfo.sex=='男' }">checked="checked"</c:if>/>男
								<input type="radio" name="sex" id="sex_woman" value="女" <c:if test="${teacherInfo.sex=='女' }">checked="checked"</c:if>/>女
								<p class="help-block"></p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" ><span class="required">*</span>籍贯：</label>
							<div class="controls">
								<input type="hidden" id="homeland" name="homeland"/>
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
							<label class="control-label"><span class="required">*</span>手机:</label>
							<div class="controls">
								<input type="text" id="mobilephone" name="mobilephone" value="${teacherInfo.mobilephone}" maxlength="11" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="required">*</span>电子邮箱:</label>
							<div class="controls">
								<input type="email" id="email" name="email" value="${teacherInfo.email}" maxlength="20" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">爱好：</label>
							<div class="controls">
								<textarea id="hobby" name="hobby" rows="4" style="width: 30%" maxlength="400">${(!empty teacherInfo.hobby)?teacherInfo.hobby:''}</textarea>
								<span id="hob_taCountView">
								<c:if test="${!empty teacherInfo.hobby}">${fn:length(teacherInfo.hobby)}</c:if>
								</span>/400
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
<%-- <script type="text/javascript" src="${adminUrl}/lib/ajaxfileupload.js></script> --%>
<script type="text/javascript">
_initCity();
$("#prov").live("change",function(e){
	_initCity();
	$("#city").attr("_city",$("#city option:selected").val());
});
/*初始化下拉列表*/
var homeland = "${teacherInfo.homeland}".split(",");
$("#prov option").each(function(index,val){
	if($(val).text()==homeland[0]){
		$(val).attr({"selected":"selected"});
		$("#city").attr("_city",homeland[1]);
		_initCity();
		return false;
	}
});

/*结束初始化*/

$("#personalDesc").live("keyup",function(e){
	var count = $("#personalDesc").val().length;
	if(count > 400){
		$("#ta_count_view").css({"color":"red"});
	}else{
		$("#ta_count_view").css({"color":"black"});
	}
	$("#ta_count_view").html(count);
});
$("#hobby").live("keyup",function(e){
	var count = $("#hobby").val().length;
	if(count > 400){
		$("#hob_taCountView").css({"color":"red"});
	}else{
		$("#hob_taCountView").css({"color":"black"});
	}
	$("#hob_taCountView").html(count);
});
$(function(){
	$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
		submitSuccess : function($form, event) {
			$("#homeland").val($.trim($("#prov option:selected").text()+","+$("#city").val()));
		}
	});
	<c:if test="${addFlag==true}">
	alert("保存成功", 2, "信息", function(){window.location.href = "/user/teacher/list/1";});
	</c:if>
});

// function ajaxFileUpload() {  
//     $.ajaxFileUpload( {  
//         url : 'fileUpload.action',//用于文件上传的服务器端请求地址  
//         secureuri : false,//一般设置为false  
//         fileElementId : 'File',//文件上传控件的id属性  
//         dataType : 'json',//返回值类型 一般设置为json  
//         success : function(data, status) //服务器成功响应处理函数  
//         {  
//             alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量  
//             if (typeof (data.error) != 'undefined') {  
//                 if (data.error != '') {  
//                     alert(data.error);  
//                 } else {  
//                     alert(data.message);  
//                 }  
//             }  
//         },  
//         error : function(data, status, e)//服务器响应失败处理函数  
//         {
//             alert(e);  
//         }
//     })  
//     return false;  
// }  
</script>
</body>
</html>