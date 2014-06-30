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

<script type="text/javascript">
$(function(){
	<c:if test="${errorMsg != ''}">
	  alert('${errorMsg }', 1, "提示", function(){
							self.location="/consume/note/list/${studentId}/1";
						});
	</c:if>
});
</script>
</body>
</html>