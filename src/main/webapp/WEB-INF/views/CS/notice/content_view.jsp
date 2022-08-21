<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/cs/board.css?after" />
<link rel="stylesheet" href="resources/css/cs/main.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="cs_main">
<%@include file="../cs_nav/cs_left_nav.jsp" %>
	<div class="cs_main_board">
	<div class="line" ></div>
		<h3>공지사항</h3>
		<form action="content_update" method="post">
			<input type="hidden" name="nnum" value="${content_view.nnum}" />
				<table class="noitce_table_content_view">
					<tr>
						<th>${content_view.ntitle }</th>
						<td class="notice_view_date">${content_view.ndate }</td>
					</tr>
					<tr>
						<td colspan="2"><pre>${content_view.ncontent }</pre></td>
					</tr>
				</table>
				<div class="paging2">
					<a href="noticeboard">목록</a> &nbsp;&nbsp;
					<c:if test="${ mgrade eq '관리자' }"> 
						<input type="submit" value="수정" class="modify_btn" /> &nbsp;&nbsp;
						<a href="delete?nnum=${content_view.nnum}">삭제</a> &nbsp;&nbsp;
					</c:if>
				</div>
		</form>
	</div>
</div>
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>