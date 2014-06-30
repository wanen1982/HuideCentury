<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.member{ 
margin:0 0 0 25px; padding:0;text-align:center;
position: absolute;
top: 0;
left: initial;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
});
</script>
<div class="header">
	<div class="member dropdown">
		<c:if test="${!empty sessionUserInfo}">
			<a data-toggle="dropdown" href="#">亲，[${sessionUserInfo.nickname}]<b class="caret"></b></a>
		    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
		      <li><a href="/member/profile">资料完善</a></li>
		      <li class="divider"></li>
		      <li><a href="/member/loginOut">退出</a></li>
		    </ul>
		</c:if>
		<c:if test="${empty sessionUserInfo}">
			<a href="/member/toLogin">会员登录</a>|<a href="/member/toRegister">会员注册</a>
		</c:if>
	</div>
	<div class="wrapper" style="margin-top: -20px;">
		<div class="hd-case group">
			<ul class="nav-list">
				<li class="lv1 fst">
					<div class="nav-d">
						<a href="${siteUrl}">网站首页</a>
					</div>
				</li>
				<c:forEach items="${navigationList}" var="navi">
					<li
						class="lv1 <c:if test="${fn:length(navi.childCategoryList) > 0}">child</c:if>">
						<div class="nav-d">
							<a href="/navi/${navi.catId}">${navi.showName}</a>
						</div> <c:if test="${fn:length(navi.childCategoryList) > 0}">
							<div class="cnav-case">
								<ul class="cnav">
									<c:forEach items="${navi.childCategoryList}" var="category">
										<li><a href="/c/${category.catId}">${category.catName}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<div class="logo">
				<a href="${siteUrl}"> </a>
			</div>
		</div>
	</div>
</div>