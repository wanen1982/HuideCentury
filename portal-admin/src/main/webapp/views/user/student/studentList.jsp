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
    <a href="javascript:void(0);" class="on"> <em>学生管理</em></a>
    <span>|</span>
    <a id="ofCourse" href="/user/student/add"> <em>添加学生</em></a>
  </div>
  <div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
  <div class="bk10"></div>
  <form class="form-inline" role="form" action="/user/student/list/1" method="post">
	  
	    <input type="text" class="form-control" id="studyNo" name="studyNo" placeholder="输入学号" value="${(empty pageInfo.studyNo)?'':pageInfo.studyNo}">
	  
	    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="输入学生姓名" value="${(empty pageInfo.nickname)?'':pageInfo.nickname}">
	  
	    <select id="sex" name="sex" class="form-control">
	    	<option value="">请选择性别</option>
	    	<option value="男" <c:if test="${(!empty pageInfo.sex)&& pageInfo.sex=='男'}">selected="selected"</c:if>>男</option>
	    	<option value="女" <c:if test="${(!empty pageInfo.sex)&& pageInfo.sex=='女'}">selected="selected"</c:if>>女</option>
	    </select>
	  
	    <input type="text" class="form-control" id="regTimeBegin" name="regTimeBegin" placeholder="创建日期起始" readonly="readonly" value="${(empty regTimeBegin)?'':regTimeBegin}">
	  
	    <input type="text" class="form-control" id="regTimeEnd" name="regTimeEnd" placeholder="创建日期截止" readonly="readonly" value="${(empty regTimeEnd)?'':regTimeEnd}">
	  
	  <button type="submit" class="btn btn-primary">查询</button>
  </form>
  <div class="main-content">
    <div class="pad-10">
      <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="120px">姓名</th>
            <th width="90px">学号</th>
            <th width="120px">性别</th>
            <th width="120px">生日</th>
            <th width="120px">手机</th>
            <th width="120px">账户余额</th>
            <th width="90px">操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${stuList}" var="stuInfo">
            <tr>
              <td>${stuInfo.nickname}</td>
              <td>${stuInfo.studyNo}</td>
              <td>${stuInfo.sex}</td>
              <td>${stuInfo.birthday}</td>
              <td>${stuInfo.mobilephone}</td>
              <td>${stuInfo.balance}</td>
              <td>
              	<div class="btn-toolbar">
                  <div class="btn-group">
                    <a class="btn btn-small btn-primary" href="/user/student/edit/${stuInfo.id}"> <i class="icon-edit icon-white"></i>查看&amp;修改</a>
                    <a class="btn btn-small btn-danger action-del" href="javascript:void(0);" data="/user/student/del/${stuInfo.id}"> <i class="icon-trash icon-white"></i> 删除</a>
                    <a class="btn btn-small btn-primary"  href="/user/student/charge/${stuInfo.id}"> <i class="icon-edit icon-white"></i> 充值</a>
                    <a class="btn btn-small btn-primary"  href="/user/student/consume/${stuInfo.id}" > <i class="icon-edit icon-white"></i> 消费</a>
                    <a class="btn btn-small btn-primary"  href="/user/student/moneyBack/${stuInfo.id}" > <i class="icon-edit icon-white"></i> 退款</a>
                    <a class="btn btn-small btn-primary"  href="/consume/note/list/${stuInfo.id}/1" > <i class="icon-edit icon-white"></i> 查看费用明细</a>
                  </div>
                </div>
              </td>
            </tr>
          </c:forEach>
        </tbody>
     </table>
     <c:if test="${pageInfo.totalPage >0 }">
     <div class="btn-toolbar">
         <div class="btn-group">
           <a class="btn btn-small btn-primary" href="/user/student/list/1"> |&lt;&lt;首页</a>
           <a class="btn btn-small btn-primary" href="/user/student/list/${pageIndex-1 }"> &lt;&lt;上一页</a>
           <a class="btn btn-small btn-primary" href="/user/student/list/${pageIndex+1 }"> 下一页&gt;&gt;</a>
           <a class="btn btn-small btn-primary" href="/user/student/list/${pageInfo.totalPage}" > 末页&gt;&gt;|</a>
         </div>
       </div>
       </c:if>
    </div>
  </div>
<script type="text/javascript">
$(function(){
	$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	$( "#regTimeBegin" ).datepicker({
		showOn: "button",
		buttonImage: "${adminUrl}/lib/jquery-ui-1.9.0/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true
	});
	$( "#regTimeEnd" ).datepicker({
		showOn: "button",
		buttonImage: "${adminUrl}/lib/jquery-ui-1.9.0/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true
	});
   $(".action-del").bind("click",function(e){
	  e.preventDefault();
	  var href = $(this).attr("data");
	  alert("确认删除该学生信息吗？", 2, "删除", function(){window.location.href = href;});
	});
   
});

</script>
</body>
</html>