<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>123幼儿园-Admin管理后台</title>
<link rel="stylesheet" href="${urlAdmin}/css/login/template.css" type="text/css" />
<link rel="stylesheet" href="${urlAdmin}/lib/bootstrap/css/bootstrap.min.css" />
<style>
.btn { border-color: #c5c5c5; border-color: rgba(0,0,0,0.15) rgba(0,0,0,0.15) rgba(0,0,0,0.25); }
.btn-primary { color: #fff; text-shadow: 0 -1px 0 rgba(0,0,0,0.25); background-color: #1d6cb0; background-image: -moz-linear-gradient(top,#2384d3,#15497c); background-image: -webkit-gradient(linear,0 0,0 100%,from(#2384d3),to(#15497c)); background-image: -webkit-linear-gradient(top,#2384d3,#15497c); background-image: -o-linear-gradient(top,#2384d3,#15497c); background-image: linear-gradient(to bottom,#2384d3,#15497c); background-repeat: repeat-x; filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff2384d3', endColorstr='#ff15497c', GradientType=0); border-color: #15497c #15497c #0a223b; border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25); *background-color: #15497c; filter: progid:DXImageTransform.Microsoft.gradient(enabled = false); }
.btn-primary:hover,
.btn-primary:active,
.btn-primary.active,
.btn-primary.disabled,
.btn-primary[disabled] { color: #fff; background-color: #15497c; *background-color: #113c66; }
.btn-large { padding: 9px 14px; font-size: 17.5px; -webkit-border-radius: 6px; -moz-border-radius: 6px; border-radius: 6px; }
</style>
</head>
<body class="site com_login view-login layout-default task- itemid- ">
<c:if test="${!empty topWindowLogin}">
<script type="text/javascript">
window.top.location.href='/login';
</script>
</c:if>
<!-- Container -->
<div class="container">
  <div id="content">
    <!-- Begin Content -->
    <div id="element-box" class="login well"> <img src="${adminUrl}/images/admin/login-logo.png" alt="123幼儿园LOGO" />123幼儿园
      <hr />
      <div id="system-message-container"> </div>
      <form action="/login" method="post" id="login-form" class="form-inline">
        <fieldset class="loginform">
        <div class="control-group">
          <div class="controls">
            <div class="input-prepend input-append"> <span class="add-on"> <i class="icon-user" title="User Name"></i>
              <label for="mod-login-username" class="element-invisible"> User Name </label>
              </span>
              <input name="username" tabindex="1" id="mod-login-username" type="text" class="input-medium" placeholder="User Name" size="15"/>
            </div>
          </div>
        </div>
        <div class="control-group">
          <div class="controls">
            <div class="input-prepend input-append"> <span class="add-on"> <i class="icon-lock" title="Password"></i>
              <label for="mod-login-password" class="element-invisible"> Password </label>
              </span>
              <input name="password" tabindex="2" id="mod-login-password" type="password" class="input-medium" placeholder="Password" size="15"/>
       </div>
          </div>
        </div>
        <div class="control-group">
          <div class="controls">
            <div class="btn-group pull-left">
              <button id="login-submit" tabindex="3" class="btn btn-primary btn-large">登 录</button>
            </div>
          </div>
        </div>
        </fieldset>
      </form>
    </div>
    <noscript>
    Warning! JavaScript must be enabled for proper operation of the Administrator backend.
    </noscript>
    <!-- End Content -->
  </div>
</div>
<div class="navbar navbar-fixed-bottom hidden-phone">
  <p class="pull-right"> Copyright&copy;2013 123幼儿园 版权所有</p>
  <a href="${siteUrl}" class="pull-left"><i class="icon-share icon-white"></i> 123幼儿园首页</a>
</div>
<%@include file="./inc/footer.jsp" %>
<script>
$(document).on("click","#login-submit",function(){
  $("#login-form").submit();
});
</script>
</body>
</html>
