<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
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
			<h3>회원 관리</h3>
		</div>
		
		<div>
			<table class="manager_table">
				<tr>
					<th>회원번호</th>
					<th>아이디</th>
				
					<th>성별</th>
					<th>생일</th>
					<th>핸드폰번호</th>
					
					<th>이메일</th>
					<th>회원등급</th>
				</tr>
				<tr>	
					<c:forEach items="${MMembers }" var="dto">
						<tr onClick="location.href='manager_membersdetails?mno=${dto.mno}'">
							<td>${dto.mno }</td>
							<td>${dto.mid }</td>
						
							<td>${dto.mgender }</td>
							<td>${dto.mbirth }</td>
							<td>${dto.mphone }</td>
							
							<td>${dto.memail }</td>
							<td>${dto.mgrade }</td>
						</tr>
					</c:forEach>
				</tr>
			</table>
				
			<form action="manager_members" method="post">
				<c:if test="${searchVO.totPage>1 }">
			      <c:if test="${ searchVO.page>1}">
			     	 <a href="manager_members?page=1">[처음]</a>
			     	 <a href="manager_members?page=${searchVO.page-1 }">[이전]</a>
			      </c:if>
      			<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
			         <c:choose>
			            <c:when test="${i eq searchVO.page }">
			               <span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			            </c:when>
			            <c:otherwise>
			               <a href="manager_members?page=${i }" style="text-decoration: none">${i }</a>  &nbsp;
			      		</c:otherwise>
			         </c:choose>
      			</c:forEach>
					<c:if test="${ searchVO.totPage>searchVO.page}">
				      <a href="manager_members?page=${searchVO.page+1 }">[다음]</a>
				      <a href="manager_members?page=${searchVO.totPage }">[마지막]</a>
				    </c:if>
   				</c:if>
   				
   				<div>
					<c:choose>
						<c:when test="${mid }">
							<input type="checkbox" name="searchType" value="mid" checked="checked" />				
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="searchType" value="mid" checked="checked"/>	
						</c:otherwise>
					</c:choose>
					아이디
					<c:choose>
						<c:when test="${mname }">
							<input type="checkbox" name="searchType" value="mname" checked="checked" />	
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="searchType" value="mname" checked="checked"/>	
						</c:otherwise>
					</c:choose>
					 이름
					<input type="text" name="sk" style="width:150px;" maxlength="50" value="" />
					<input type="submit" value="검색" />
				</div>
			</form>
		</div>
	</div> 
</div>
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>