<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의 소리 관리</title>
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
			<h3>고객의 소리 관리</h3>
		</div>
		
		<div>
			<table class="manager_table">
				<tr>
					<th class="m_num">번호</th>
					<th class="m_id">아이디</th>
					<th class="m_select">문의유형</th>
					<th class="m_title">제목</th>
					<th class="m_date">날짜</th>
				</tr>
				
				<c:forEach items="${CommuMembers}" var="cm" begin="0" end="10">
					<tr onClick="location.href='manager_commudetails?cnum=${cm.cnum}'">
						<td>${cm.cnum}</td>
						<td>${cm.mid}</td>
						<td>${cm.cselect1}</td>
						<td class="m_title">${cm.ctitle}</td>
						<td class="m_date">${cm.cdate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
			<form action="CommuMembers" method="post" class="page_align">
				<c:if test="${searchVO.totPage>1 }">
			      <c:if test="${ searchVO.page>1}">
			     	 <a href="manager_commu?page=1">[처음]</a>
			     	 <a href="manager_commu?page=${searchVO.page-1 }">[이전]</a>
			      </c:if>
      			<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
			         <c:choose>
			            <c:when test="${i eq searchVO.page }">
			               <span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			            </c:when>
			            <c:otherwise>
			               <a href="manager_commu?page=${i }" style="text-decoration: none">${i }</a>  &nbsp;
			      		</c:otherwise>
			         </c:choose>
      			</c:forEach>
					<c:if test="${ searchVO.totPage>searchVO.page}">
				      <a href="manager_commu?page=${searchVO.page+1 }">[다음]</a>
				      <a href="manager_commu?page=${searchVO.totPage }">[마지막]</a>
				    </c:if>
   				</c:if>
			</form>
	</div>
</div> 
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>