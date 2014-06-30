<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>管理员管理</title>
  <link rel="stylesheet" href="${adminUrl}/lib/bootstrap/css/bootstrap.min.css" />
  <link href="${adminUrl}/css/reset.css" rel="stylesheet" type="text/css" />
  <link href="${adminUrl}/css/g-system.css" rel="stylesheet" type="text/css" />
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
    <a href="javascript:void(0);" class="on"> <em>学校主任管理</em></a>
    <span>|</span>
    <a id="addSuperAdmin" href="/user/admin/add"> <em>添加学校主任</em></a>
    <span>|</span>
    <a id="ofCourse" href="/user/admin/listCourse"> <em>所属课程</em></a>
  </div>
  <div class="main-content">
    <div class="pad-10">
      <div class="bk10"></div>
      <table class="table table-condensed table-hover">
        <thead>
          <tr>
            <th width="90px">用户ID</th>
            <th width="120px">用户名</th>
            <th width="120px">所属角色</th>
            <th width="150px">最后登录IP</th>
            <th width="150px">最后登录时间</th>
            <th width="150px">E-mail</th>
            <th width="120px">真实姓名</th>
            <th width="200px">管理操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${adminList}" var="user">
            <tr>
              <td>${user.userid}</td>
              <td>${user.username}</td>
              <td><c:choose><c:when test="${user.roleid == 0}">超级管理员</c:when><c:when test="${user.roleid == 1}">网站运营</c:when></c:choose></td>
              <td>${user.lastloginip}</td>
              <td><fmt:formatDate value="${user.lastlogintime}" pattern="yyyy-MM-dd HH:mm"/></td>
              <td>${user.email}</td>
              <td>${user.realname}</td>
              <td>
                <div class="btn-toolbar">
                  <div class="btn-group">
                    <a class="btn btn-small btn-primary" href="/user/admin/edit/${user.userid}"> <i class="icon-edit icon-white"></i> 修改</a>
                    <a class="btn btn-small btn-danger action-del" href="javascript:void(0);" data="/user/admin/delete/${user.userid}"> <i class="icon-trash icon-white"></i> 删除</a>
                  </div>
                </div>
             </td>
            </tr>
          </c:forEach>
        </tbody>
     </table>
    </div>
  </div>
<%@include file="../inc/footer.jsp"%>
<script type="text/javascript">

$(".pagination li a").live("click", function(e){
  if($(this).parent().hasClass("active") || $(this).parent().hasClass("disabled")){
    return ;
  }
  var page = parseInt($(this).attr("data"));
  $("#approveList input[name='page']").val(page);
  $("#approveList").submit();
});

$(".action-del").bind("click",function(e){
  e.preventDefault();
  var href = $(this).attr("data");
  alert("确认删除该管理员信息？", 2, "删除管理员", function(){window.location.href = href;});
});
</script>
</body>
</html>