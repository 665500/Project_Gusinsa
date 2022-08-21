<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 상세</title>
<link rel="stylesheet" href="resources/css/cs/manager.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager2.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="manager_main">
<%@include file="../cs_nav/manager_nav.jsp" %>
	<div class="manager_board">
		<div class="manager_main_title">
			<h3>회원 관리 상세</h3>
		</div>
	
		<div>
			<table class="manager_detail_table">
				<tr>
					<th>번호</th>
					<td>${manager_members.mno }</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${manager_members.mid }</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${manager_members.mname }</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>${manager_members.mgender }</td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td>${manager_members.mphone }</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>${manager_members.maddr_one }</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${manager_members.maddr_two }</td>
				</tr>
				<tr>
					<th>참고항목</th>
					<td>${manager_members.maddr_three }</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>${manager_members.maddr_four }</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${manager_members.memail }</td>
				</tr>
				<tr>
					<th>회원등급</th>
					<td>
						<form action="mgrade" method="post">
							<input type="hidden" name="mno" value="${manager_members.mno}"/>
								<select name="mgrade">
									<option value="관리자">관리자</option>
									<option value="VVIP">VVIP</option>
									<option value="VIP">VIP</option>
									<option value="PLATINUM">PLATINUM</option>
									<option value="GOLD">GOLD</option>
									<option value="SILVER">SILVER</option>
								</select>
							<input type="submit" value="저장" />
						</form>
					</td>				
					<%-- <td>${manager_members.mgrade }</td> --%>
				</tr>
			</table>
			<div class="page_align">
				<a href="manager_members">목록보기</a> &nbsp;&nbsp;
				<a href="membersdelete?mno=${manager_members.mno }">삭제</a>
			</div>
		</div>
	</div>
</div>
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>