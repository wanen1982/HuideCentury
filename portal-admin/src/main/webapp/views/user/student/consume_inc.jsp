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
		<a href="javascript:void(0);" class="on"> <em>消费</em></a>
	</div>
	<form id="form1" class="form-horizontal" action="/user/student/charge" method="post">
	<div class="main-content">
		<div class="pad-10">
			<div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
			<div class="bk10"></div>
			<div class="col-tab">
				<ul class="tabBut cu-li">
					<li id="tab_setting_1" class="on">消费操作</li>
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
						<label class="control-label"><span class="required">*</span>课程类型:</label>
						<div class="controls">
							<select class="form-control" name="roleid" id="catId_consume" required>
								<option value="">请选择课程类型</option>
								<c:forEach items="${courseCategoryList}" var="courseCategory" >
									<option value="${courseCategory.catId}">${courseCategory.catName }</option>
								</c:forEach>
							</select>
						</div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="required">*</span>课程:</label>
						<div class="controls">
							<select class="form-control" name="roleid" id="newsId_consume" required>
								<option value="">请选择课程</option>
							</select>
						</div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<label class="control-label" >每周<span id="weekIndex_consume"></span>:</label>
						<div class="controls"></div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<label class="control-label" >老师:</label>
						<input type="hidden" id="teaId_consume" value="">
						<div class="controls"  id="teaName_consume"></div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="required">*</span>消费金额:</label>
						<div class="controls">
							<input type="number" class="form-control" id="consumeCost" name="consumeCost"
								placeholder="请输入消费金额" value="0.0" required="required" maxlength="8" readonly="readonly">
						</div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<label class="control-label" >备注:</label>
						<div class="controls" >
							<input type="text" name="remark_consume" id="remark_consume" maxlength="150" value=""/>
						</div>
						<p class="help-block"></p>
					</div>
					<div class="control-group">
						<div class="saveInfo"><button id="submitBtn" class="btn btn-primary" type="submit"><i class="icon-check icon-white"></i> 消费</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
<%@include file="/views/inc/footer.jsp"%>
<script type="text/javascript" src="${adminUrl}/js/youda.utils.js"></script>
<script type="text/javascript">
$(function(){
	//bootstrap validation init
	$("input,select,textarea").not("[type=submit]").jqBootstrapValidation({
		submitSuccess : function($form, event) {
			event.preventDefault();
			$.ajax({
				   url:"/user/student/consume",
				   type:"post",
				   dataType : "json",
				   data:{
					   "catId":$("#catId_consume").val(),
					   "studentId":$("#studentId").val(),
					   "consumeCost":$("#consumeCost").val(),
					   "newsId":$("#newsId_consume").val(),
					   "remark":$("#remark_consume").val()
				   },
				   success:function(data,textStatus){
					   if(data.success){
							alert(data.message, 2, "提示", function(){
								self.location="/user/student/list/1";
							});
						}else{
							alert(data.message, 1, "提示");
						}
				   }
			 });
		}
	});
	
	//类型绑定事件
	$("#catId_consume").live("change",function(e){
		   if($(this).val()==""){
			   return ;
		   }
		   $.ajax({
			   url:"/user/student/getCourseList/"+$(this).val(),
			   type:"get",
			   dataType : "json",
			   success:function(data,textStatus){
				   if(data.success){
						$("#newsId_consume").empty();
						$("#newsId_consume").append('<option value="">请选择课程</option>');
					   for(var i=0; i<data.bean.length; i++){
						    var courseData = "{\"beginTime\":\""+data.bean[i].news.beginTime+"\","+
						    	 "\"endTime\":\""+data.bean[i].news.endTime+"\","+
						    	 "\"weekIndex\":\""+data.bean[i].news.weekIndex+"\","+
						    	 "\"defaultCost\":\""+data.bean[i].news.defaultCost+"\","+
						    	 "\"teaId\":\""+data.bean[i].news.teaId+"\","+
						    	 "\"teaName\":\""+data.bean[i].teaName+"\"}";
							$("#newsId_consume").append("<option value=\""+data.bean[i].news.newsId+"\" data='"+courseData+"'>"+data.bean[i].news.title+"</option>");
					   }
				   }
			   }
		   });	   
	});
	//课程绑定事件
   $("#newsId_consume").live("change",function(e){
	   var course = $.parseJSON($(this).find("option:selected").attr("data"));
	   if(!course)return ;
	   var weekNam = "一";
	   switch(course.weekIndex){
		   	case "0": weekNam = "天"; break;
		   	case "1": weekNam = "一"; break;
		   	case "2": weekNam = "二"; break;
		   	case "3": weekNam = "三"; break;
		   	case "4": weekNam = "四"; break;
		   	case "5": weekNam = "五"; break;
		   	case "6": weekNam = "六"; break;
		   default:
			   weekNam = "天";
	   }
	   $("#weekIndex_consume").text(weekNam);
	   $("#weekIndex_consume").parent().next().text(course.beginTime+"--"+course.endTime);
	   $("#consumeCost").val(course.defaultCost);
	   $("#teaName_consume").text(course.teaName);
	   $("#teaId_consume").text(course.teaId);
   });
});//end jQuery init


</script>
</body>
</html>