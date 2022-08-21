<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입점문의 관리</title>
<link rel="stylesheet" href="resources/css/cs/manager.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager2.css?after" />
<link rel="stylesheet" href="resources/css/cs/board.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="manager_main">
<%@include file="../cs_nav/manager_nav.jsp" %>
	<div class="manager_board">
		<div class="manager_main_title">
			<h3>입점문의 관리</h3>
		</div>
		
		<div>
			<table class="manager_table">
				<tr>
					<th>번호</th>
					<th>상호명</th>
					<th>브랜드명</th>
				</tr>
				
				<c:forEach items="${EnterBoard}" var="dto" begin="0" end="10">
					<tr onClick="location.href='manager_enterdetails?enumber=${dto.enumber }'">
						<td>${dto.enumber }</td>
						<td>${dto.etradename }</td>
						<td>${dto.ebname }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
			<div class="paging">
				<form action="EnterBoard" method="post">
					<c:if test="${searchVO.totPage>1 }">
				      <c:if test="${ searchVO.page>1}">
				     	 <a href="manager_enter?page=1">[처음]</a>
				     	 <a href="manager_enter?page=${searchVO.page-1 }">[이전]</a>
				      </c:if>
	      			<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
				         <c:choose>
				            <c:when test="${i eq searchVO.page }">
				               <span style="color: red; font-weight: bold;">${i }&nbsp;</span>
				            </c:when>
				            <c:otherwise>
				               <a href="manager_enter?page=${i }" style="text-decoration: none">${i }</a>  &nbsp;
				      		</c:otherwise>
				         </c:choose>
	      			</c:forEach>
						<c:if test="${ searchVO.totPage>searchVO.page}">
					      <a href="manager_enter?page=${searchVO.page+1 }">[다음]</a>
					      <a href="manager_enter?page=${searchVO.totPage }">[마지막]</a>
					    </c:if>
	   				</c:if>
				</form>
			</div>
	</div>
</div> 
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>