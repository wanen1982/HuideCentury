<%@ page pageEncoding= "UTF-8" %>
<%@ page contentType= "text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<div class="inner-case">
	<div class="title group">
		<a class="more" href="/navi/46">more+</a>
		<ul class="tab-list">
			<li class="selected">解决方案</li>
		</ul>
	</div>
	<div class="ncontent">
		<div class="tab-content selected">
			<c:forEach items="${solutionList}" var="solution" end="4">
				<div>
					<a href="/c/${solution.catId}">${solution.catName}</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>