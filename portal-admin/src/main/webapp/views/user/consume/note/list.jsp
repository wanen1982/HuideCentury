<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>管理员管理</title>
   <link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
  <link href="${adminUrl}/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="${adminUrl}/css/g-system.css" rel="stylesheet" type="text/css" />
  
   <link rel="stylesheet" href="${adminUrl}/lib/jquery-ui-1.9.0/themes/base/jquery.ui.all.css">
	<script src="${adminUrl}/lib/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
	<script src="${adminUrl}/lib/jquery-ui-1.9.0/ui/jquery.ui.core.js"></script>
	<script src="${adminUrl}/lib/jquery-ui-1.9.0/ui/jquery.ui.widget.js"></script>
	<script src="${adminUrl}/lib/jquery-ui-1.9.0/ui/jquery.ui.datepicker.js"></script>
	<script src="${adminUrl}/lib/jquery-ui-1.9.0/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript" src="${adminUrl}/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${adminUrl}/lib/bootstrap/js/jqBootstrapValidation.js"></script>
	<script type="text/javascript" src="${adminUrl}/js/youda.utils.js"></script>	
	<script type="text/javascript"  src="${adminUrl}/lib/artDialog/dialog.js"></script>
  <style type="text/css">
body{ padding-left: 10px; }

.btn-group>.btn, .btn-group>.dropdown-menu, .btn-group>.popover { font-size: 12px; }
.table th, .table td { vertical-align: middle; }
input[type="text"] { height: 16px; }

.search-filter { margin: 10px 20px 10px 20px; }

.search-filter label { display: inline; font-size: 12px; margin-right: 10px; }
select, input[type="file"] { height: 28px; line-height: 30px; font-size: 12px; }
.emptyBtn{display:inline-block;width:64px;height:26px;}
.news_title{width:260px;overflow: hidden; white-space: nowrap; text-overflow: ellipsis;display: block;}
.pagination{text-align:center;margin-top:0;}
.pagination a{color:#08c;}
</style>
</head>
<body>
	<div class="content-menu ib-a blue line-x">
    <a href="/user/student/list/1" > <em>学生管理</em></a>
    <span>|</span>
    <a href="javascript:void(0);" class="on"> <em>消费明细</em></a>
  </div>
  <div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
  <div class="bk10"></div>
  <form class="form-inline" role="form" action="/consume/note/list/${pageInfo.stuId }/1" method="post">
	    <select id="type" name="type" class="form-control">
	    	<option value="">请选择消费类型</option>
	    	<c:forEach var="type" items="${consumeTypes}">
	    		<option value="${type}" <c:if test="${(!empty pageInfo.type)&& pageInfo.type==type}">selected="selected"</c:if>>
	    			<c:choose>
	    				<c:when test="${type=='CZ' }">充值</c:when>
	    				<c:when test="${type=='XF' }">消费</c:when>
	    				<c:otherwise>退款</c:otherwise>
	    			</c:choose>
	    		</option>
	    	</c:forEach>
	    </select>
	  
	    <input type="text" class="form-control" id="createTimeBegin" name="createTimeBegin" placeholder="发生日期起始" readonly="readonly" value="${createTimeBegin }">
	    <input type="text" class="form-control" id="createTimeEnd" name="createTimeEnd" placeholder="发生日期截止" readonly="readonly" value="${createTimeEnd }">
	    <button type="submit" class="btn btn-primary">查询</button>
	    <button type="button" class="btn btn-primary" id="exportBut">导出当前学生EXCEL</button>
	    <button type="button" class="btn btn-primary" id="exportAllBut">导出全部学生EXCEL</button>
  </form>
  <form class="form-inline" id="form2" action="/consume/note/export/excel/${pageInfo.stuId }" method="post">
  	<input type="hidden" id="createTimeBegin_form2" name="createTimeBegin" value=""/>
  	<input type="hidden" id="createTimeEnd_form2" name="createTimeEnd" value=""/>
  </form>
  <form class="form-inline" id="form3" action="/consume/note/export/excel/all" method="post">
  	<input type="hidden" id="createTimeBegin_form3" name="createTimeBegin" value=""/>
  	<input type="hidden" id="createTimeEnd_form3" name="createTimeEnd" value=""/>
  </form>
  <div class="main-content">
    <div class="pad-10">
      <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="90px">ID</th>
            <th width="120px">消费类型</th>
            <th width="120px">课程类型</th>
            <th width="120px">课程</th>
            <th width="120px">学生姓名</th>
            <th width="120px">发生额</th>
            <th width="120px">发生时间</th>
            <th width="120px">备注</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${consumeNoteList}" var="consumeNoteItem">
            <tr>
              <td>${consumeNoteItem.id}</td>
              <td>${consumeNoteItem.typeName}</td>
              <td>${consumeNoteItem.catName}</td>
              <td>${consumeNoteItem.courseName}</td>
              <td>${consumeNoteItem.studentName}</td>
              <td>${consumeNoteItem.cost}</td>
              <td>${consumeNoteItem.showCreateTime}</td>
              <td>${consumeNoteItem.remark}</td>
            </tr>
          </c:forEach>
        </tbody>
     </table>
     <c:if test="${pageInfo.totalPage >0 }">
     <div class="btn-toolbar">
         <div class="btn-group">
           <a class="btn btn-small btn-primary" href="/consume/note/list/${pageInfo.stuId }/1"> |&lt;&lt;首页</a>
           <a class="btn btn-small btn-primary" href="/consume/note/list/${pageInfo.stuId }/${pageIndex-1 }"> &lt;&lt;上一页</a>
           <a class="btn btn-small btn-primary" href="/consume/note/list/${pageInfo.stuId }/${pageIndex+1 }"> 下一页&gt;&gt;</a>
           <a class="btn btn-small btn-primary" href="/consume/note/list/${pageInfo.stuId }/${pageInfo.totalPage}" > 末页&gt;&gt;|</a>
         </div>
       </div>
       </c:if>
    </div>
  </div>
<script type="text/javascript">
$(function(){
	$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	$( "#createTimeBegin" ).datepicker({
		showOn: "button",
		buttonImage: "${adminUrl}/lib/jquery-ui-1.9.0/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true
	});
	$( "#createTimeEnd" ).datepicker({
		showOn: "button",
		buttonImage: "${adminUrl}/lib/jquery-ui-1.9.0/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true
	});
   $("#exportBut").live("click",function(e){
	  if($( "#createTimeBegin" ).val()==""){
		  alert("请选发生日期起!",1,"提示");
		  return ;
	  } 
	  if($( "#createTimeEnd" ).val()==""){
		  alert("请选发生日期止!",1,"提示");
		  return ;
	  } 
	  $("#createTimeBegin_form2").val($( "#createTimeBegin" ).val());
	  $("#createTimeEnd_form2").val($( "#createTimeEnd" ).val());
	  $("#form2").trigger("submit");
   });
   $("#exportAllBut").live("click",function(e){
	  if($( "#createTimeBegin" ).val()==""){
		  alert("请选发生日期起!",1,"提示");
		  return ;
	  } 
	  if($( "#createTimeEnd" ).val()==""){
		  alert("请选发生日期止!",1,"提示");
		  return ;
	  } 
	  $("#createTimeBegin_form3").val($( "#createTimeBegin" ).val());
	  $("#createTimeEnd_form3").val($( "#createTimeEnd" ).val());
	  $("#form3").trigger("submit");
   });
});
</script>
</body>
</html>