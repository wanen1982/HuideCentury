<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="courseType" value="${(empty pageInfo.courseType)?'':pageInfo.courseType}" scope="page"/>
<c:set var="sex" value="${(empty pageInfo.sex)?'':pageInfo.sex}" scope="page"/>
<!DOCTYPE html>
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
    <a href="javascript:void(0);" class="on"> <em>老师管理</em></a>
    <span>|</span>
    <a id="ofCourse" href="/user/teacher/add" > <em>添加老师</em></a>
  </div>
  <div class="main-content">
    <div class="pad-10">
      <div class="explain-col" <c:if test="${empty errorMSG}">style="display:none"</c:if>>温馨提示: ${errorMSG}</div>
      <div class="bk10"></div>
      <form class="form-inline" role="form" action="/user/teacher/list/1" method="post">
      	<input type="text" class="form-control" id="name" name="name" placeholder="输入姓名" value="${(empty pageInfo.name)?'':pageInfo.name}">
      	<input type="text" class="form-control" id="educated" name="educated" placeholder="输入学历" value="${(empty pageInfo.educated)?'':pageInfo.educated}">
	    <select class="form-control" name="courseType" id="courseType">
	    	<option value="">请选择课程类型</option>
			<c:forEach items="${userRoleKeySet}"  var="userRoleKey">
				<c:if test="${userRoleKey > 1}">
					<option value="${userRoleKey}" <c:if test="${userRoleKey==courseType}">selected="selected"</c:if>>${userRoleMap[userRoleKey] }</option>
				</c:if>
			</c:forEach>
		</select>
      	<select id="sex" class="form-control" name="sex">
	    	<option value="">请选择性别</option>
	    	<option value="男"  <c:if test="${(!empty pageInfo.sex) && pageInfo.sex=='男' }">selected="selected"</c:if>>男</option>
	    	<option value="女" <c:if test="${(!empty pageInfo.sex) && pageInfo.sex=='女' }">selected="selected"</c:if>>女</option>
	    </select>
      	<input type="text" class="form-control" id="mobilephone" name="mobilephone" placeholder="输入手机号" value="${(empty pageInfo.mobilephone)?'':pageInfo.mobilephone}">
      	<input type="text" class="form-control" id="email" name="email" placeholder="输入电子邮箱" value="${(empty pageInfo.email)?'':pageInfo.email}">
	    <button type="submit" class="btn btn-primary">查询</button>
      </form>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="120px">姓名</th>
            <th width="120px">年龄</th>
            <th width="120px">学历</th>
            <th width="120px">课程类型</th>
            <th width="120px">性别</th>
            <th width="120px">照片</th>
            <th width="120px">描术</th>
            <th width="120px">籍贯</th>
            <th width="120px">手机</th>
            <th width="120px">电子邮箱</th>
            <th width="120px">爱好</th>
            <th width="120px">操作</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${teacherList}" var="stuInfo">
            <tr>
              <td>${stuInfo.name }</td>
              <td>${stuInfo.age }</td>
              <td>${stuInfo.educated}</td>
              <td>${userRoleMap[stuInfo.courseType] }</td>
              <td>${stuInfo.sex}</td>
              <td>
              	<c:if test="${!empty stuInfo.photoUrl}">
					<img src="${stuInfo.photoUrl}" alt="已上传的照片" class="img-responsive" style="width: 50px;height:50px;">
				</c:if>
              </td>
              <td title="${stuInfo.personalDesc}">
              	<c:if test="${fn:length(stuInfo.personalDesc)>5}">
	              ${fn:substring(stuInfo.personalDesc,0,5)}...
	              </c:if>
	              <c:if test="${fn:length(stuInfo.personalDesc)<=5}">
	              ${stuInfo.personalDesc}
	              </c:if>
              </td>
              <td>${stuInfo.homeland}</td>
              <td>${stuInfo.mobilephone}</td>
              <td>${stuInfo.email}</td>
              <td title="${stuInfo.hobby}">
              	<c:if test="${fn:length(stuInfo.hobby)>5}">
	              ${fn:substring(stuInfo.hobby,0,5)}...
	              </c:if>
	              <c:if test="${fn:length(stuInfo.hobby)<=5}">
	              ${stuInfo.hobby}
	              </c:if>
              </td>
              <td>
              	 <div class="btn-group">
                    <a class="btn btn-small btn-primary" href="/user/teacher/edit/${stuInfo.id}"> <i class="icon-edit icon-white"></i> 修改</a>
                    <a class="btn btn-small btn-danger action-del" href="javascript:void(0);" data="/user/teacher/del/${stuInfo.id}"> <i class="icon-trash icon-white"></i> 删除</a>
                 </div>
              </td>
            </tr>
           </c:forEach>
        </tbody>
     </table>
     	<c:if test="${pageInfo.totalPage >0 }">
	     	<div class="btn-toolbar">
	         <div class="btn-group">
	           <a class="btn btn-small btn-primary" href="/user/teacher/list/1"> |&lt;&lt;首页</a>
	           <a class="btn btn-small btn-primary" href="/user/teacher/list/${pageIndex-1}"> &lt;&lt;上一页</a>
	           <a class="btn btn-small btn-primary" href="/user/teacher/list/${pageIndex+1}"> 下一页&gt;&gt;</a>
	           <a class="btn btn-small btn-primary" href="/user/teacher/list/${pageInfo.totalPage}" > 末页&gt;&gt;|</a>
	         </div>
	       </div>
        </c:if>     
    </div>
  </div>
<script type="text/javascript">

$(".action-del").bind("click",function(e){
  e.preventDefault();
  var href = $(this).attr("data");
  alert("确认删除该老师信息？", 2, "删除", function(){window.location.href = href;});
});
</script>
</body>
</html>