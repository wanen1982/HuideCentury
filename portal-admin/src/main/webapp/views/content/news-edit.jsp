<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
	<link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/g-system.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/table_form.css"/>
	<link rel="stylesheet" type="text/css" href="${adminUrl}/css/uploadify.css"/>
	<style type="text/css">
       body { background: #e2e9ea; }
       .red{color: #FF0000}
       label, input, button, select, textarea { font-size: 12px; }
       .addContent {width: 1023px;}
       .fixed-bottom .fixed-but {position: relative;}
       input[type="text"],select[class*="span"]{margin-top: 6px;}
       select[class*="span"]{height: 26px;}
       .thumbList img{width:171px; height:113px;padding-left: 5px; margin-right: 10px;}
       .table_form th {width: 85px; height: 60px;}
	</style>
</head>
<body>
	<form name="myform" id="myform" action="/content/news/save" method="post" enctype="multipart/form-data">
		<input type="hidden" name="newsId" value="${news.newsId}" />
		<div class="addContent">
			<div class="crumbs">当前位置：课程管理  &gt; 内容管理 &gt; 课程内容编辑</div>
			<div class="col-auto">
				<div class="col-1">
					<div class="content pad-6">
						<div id="error" class="red"></div>
						<table width="100%" cellspacing="0" class="table_form">
							<tbody>
								<tr>
									<th> <font color="red">*</font>
										栏目:
									</th>
									<td>
										<select class="span2" name="catid" id="catId">
											<c:forEach var="category" items="${newsCategoryList}">
												<option value="${category.catId}" <c:if test="${news.catId == category.catId}">selected</c:if>>${category.catName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 每周:</th>
									<td>
										<select id="weekIndex" name="weekIndex"  class="span2">
											<c:forEach begin="0" end="6" varStatus="index">
												<option value="${index.index }" <c:if test="${index.index==news.weekIndex }">selected="selected"</c:if>>
													<c:if test="${index.index==0 }">星期天</c:if> 
													<c:if test="${index.index==1 }">星期一</c:if> 
													<c:if test="${index.index==2 }">星期二</c:if> 
													<c:if test="${index.index==3 }">星期三</c:if> 
													<c:if test="${index.index==4 }">星期四</c:if> 
													<c:if test="${index.index==5 }">星期五</c:if> 
													<c:if test="${index.index==6 }">星期六</c:if> 
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 上课时间起:</th>
									<td>
										<input type="hidden" id="beginTime" name="beginTime"/>
										<select name="startHour" id="startHour"  class="span2"></select>：
										<select name="startMinute"  id="startMinute" class="span2"></select>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 上课时间止:</th>
									<td>
										<input type="hidden" id="endTime" name="endTime"/>
										<select name="endHour" id="endHour"  class="span2"></select>：
										<select name="endMinute"  id="endMinute" class="span2"></select>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 默认价格:</th>
									<td>
										<input type="text" name="defaultCost" id="defaultCost" value="${(empty news.defaultCost)?0.0:news.defaultCost }" maxlength="10">
								    </td>
								</tr>
								<tr>
									<th><font color="red">*</font> 课程类型:</th>
									<td>
										<select name="courseType" id="courseType">
											<option value="">--请选择--</option>
											<c:forEach var="typeKey" items="${courseTypeKeySet}">
												<c:if test="${typeKey > 1}">
													<option value="${typeKey}" <c:if test="${typeKey==news.courseType }">selected="selected"</c:if>>${courseTypeMap[typeKey]}</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 授课老师:</th>
									<td>
										<select name="teaId" id="teaId" required>
											<option value="">--请选择--</option>
										</select>
									</td>
								</tr>
								<tr>
									<th> <font color="red">*</font>
										标题:
									</th>
									<td>
										<input type="text" style="width:400px;" name="info[title]" id="title" value="${news.title}">			
										最多输入80个字符
										<div id="titleTip" style="display: none;"></div>
									</td>
								</tr>
								<tr>
									<th><font color="red">*</font> 来源:</th>
									<td>
										<input type="text" name="info[source]" id="source" value="${news.source}"></td>
								</tr>
								<tr>
									<th><font color="red">*</font> 网编:</th>
									<td>
										<input type="text" name="info[editor]" id="editor" value="${news.editorName}"></td>
								</tr>
								<tr>
									<th>
										<font color="red">*</font>
										内容:
									</th>
									<td>
										<script type="text/plain" id="content_editer"></script>
										<textarea name="info[content]" id="content" class="textItem" style="display:none;width:900px;height:246px;">${news.content}</textarea>
									</td>
								</tr>
								<tr id="thumbOption">
									<th>
										<font color="red">*</font>
										新闻缩略图:
									</th>
									<td>
										<ul class="thumbList inline">
											<li>您可以设置自己上传的新闻图片作为新闻缩略图。</li>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			</div>

		<div class="fixed-bottom">
			<div class="fixed-but text-c">
				<div class="button">
					<input value="保存后自动关闭" type="button" name="dosubmit" class="news-add cu" style="width:145px;"></div>
				<div class="button">
					<input value="关闭(X)" type="button" name="close" onclick="javascript:window.close();" class="cu" style="width:70px;"></div>
			</div>
		</div>
	</form>
	<%@include file="../inc/footer.jsp"%>
<script src="${adminUrl}/lib/ueditor/editor_config.js"></script>
<script src="${adminUrl}/lib/ueditor/editor_all.js"></script>
<script>
	function updateThumbList($thumbList){
		
		var content = "";
		$thumbList.each(function(){
			var data = $(this).attr("data");
			if(typeof(data) == 'undefined'){
				return;
			}
			var src = $(this).attr("src");
			content = content + '<li><input type="radio" name="thumb" value="' + data + '"><img src="' + src + '"></li>';
		});
		if(content.length <= 0){
			content="<li>您可以设置自己上传的新闻图片作为新闻缩略图。</li>";
			$(".thumbList").html(content);
			return;
		}
		$(".thumbList").html(content);
		$(".thumbList li:first input").attr("checked","checked");
	}
	$(document).ready(function() {
		//init Editor 
		//some config
		$.extend(window.UEDITOR_CONFIG,{
			UEDITOR_HOME_URL:"${adminUrl}/lib/ueditor/"
			//image upload url
			,focus:false
			,initialContent:$("#content").val()
			,imageUrl:"${adminUrl}/Editer/imgUpload/${news.newsId}"
			,imagePath:""
			,imageManagerUrl:"${adminUrl}/Editer/imgView/"
			,initialFrameWidth:914,
			initialFrameHeight:380
		});
		
		var content_editer = UE.getEditor('content_editer');
		content_editer.addListener("contentChange",function(){
			updateThumbList($(this.getContent()).find("img"));
		}) ;

		content_editer.ready(function(){
		    updateThumbList($(this.getContent()).find("img"));
		});
		
		//初始化上课和下课时间
		var beginTimeEdit = "${news.beginTime}".split(":");
		var endTimeEdit = "${news.endTime}".split(":");
		for(var i=0; i<24; i++){
			var hour = "00"+i;
			hour = hour.substring(hour.length-2);
			var startHourSelect = beginTimeEdit[0]== hour?"selected='selected'":"";
			var endHourSelect = endTimeEdit[0]==hour?"selected='selected'":"";
			$("#startHour").append("<option value='"+hour+"' "+startHourSelect+">"+hour+"</option>");
			$("#endHour").append("<option value='"+hour+"' "+endHourSelect+">"+hour+"</option>");
		}
		for(var i=0; i<60; i++){
			var minutes = "00"+i;
			minutes = minutes.substring(minutes.length-2);
			var startMinuteSelect = beginTimeEdit[1]==minutes?"selected='selected'":"";
			var endMinuteSelect = endTimeEdit[1]==minutes?"selected='selected'":"";
			$("#startMinute").append("<option value='"+minutes+"' "+startMinuteSelect+">"+minutes+"</option>");
			$("#endMinute").append("<option value='"+minutes+"' "+endMinuteSelect+">"+minutes+"</option>");
		}
		//初始化连动菜单,课程类型与老师
		$("#courseType").live("change",function(index,ele){
			var courseType = $(this).val();
			$.ajax({
				url:"/content/news/getTeachers/"+courseType,
				dataType : "json",
				type : "GET",
				success : function(data, textStatus) {
					if(!data.success){
						alert(data.message, 1, "提示");
						return;
					}
					$("#teaId").empty();
					$("#teaId").append("<option value=\"\">--请选择--</option>");
					var editTeaId = "${news.teaId}";//服务器端选中的id
					for(var i=0; i<data.reultBeans.length; i++){
						var selected = editTeaId==data.reultBeans[i].id?"selected=\"selected\"":"";
						$("#teaId").append("<option value=\""+data.reultBeans[i].id+"\" "+selected+">"+data.reultBeans[i].name+"--"+data.reultBeans[i].sex+"</option>");
					}
				}
			});
		});
		$("#courseType").trigger("change");//触发一次初始化
	}).on("click",".fixed-but .button .news-add",function(){
			var content_editer = UE.getEditor('content_editer');
			$("#content").val(content_editer.getContent());
			$("#error").html("").hide();
			var error ="" ;
			var isError = false ;
			if($("#catId").val() <= 0){
				isError =true ; 
				error =" <p>请选择新闻所属的栏目。</p>";
			}
			else if($("#weekIndex").val()==""){
				isError =true ; 
				error ="<p>请选择每周列表。</p>";
			}
			else if(!/^[0-9]+(\.[0-9]+){0,1}$/.test($("#defaultCost").val())){
				isError =true ; 
				error ="<p>默认价格必须输入数字。</p>";
			}
			else if($("#courseType").val()==""){
				isError =true ; 
				error ="<p>请选择课程类型。</p>";
			}
			else if($("#teaId").val()==""){
				isError =true ; 
				error ="<p>请选择授课老师。</p>";
			}
			else if(!$("#title").val()){
				isError =true ; 
				error =" <p>标题不能为空</p>";
			} 
			else if(!$("#source").val()){
				isError =true ; 
				error =" <p>来源不能为空</p>";
			} 
			else if(!$("#editor").val()){
				isError =true ; 
				error =" <p>网络编辑不能为空</p>";
			}
			else if(!$("#content").val()){
				isError =true ; 
				error =" <p>内容不能为空</p>";
			} 

			if(isError){
				alert(error,1);
				return ; 
			}else{
				$("#beginTime").val($("#startHour").val()+":"+$("#startMinute").val());
				$("#endTime").val($("#endHour").val()+":"+$("#endMinute").val());
				$("#myform").submit();
			}
			
	});
	
</script>
</body>
</html>