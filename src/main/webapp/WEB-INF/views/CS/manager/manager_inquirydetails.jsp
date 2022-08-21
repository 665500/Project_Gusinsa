<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의하기 상세</title>
<link rel="stylesheet" href="resources/css/cs/manager.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager2.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="manager_main">
<%@include file="../cs_nav/manager_nav.jsp" %>
	<div class="manager_board">
		<div class="manager_main_title">
			<h3>1:1 문의하기 상세</h3>
		</div>
		
		<div>
			<table class="manager_detail_table">
				<tr>
					<th>번호</th>
					<td>${manager_inquiry.inum}</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${manager_inquiry.mid}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${manager_inquiry.mname}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${manager_inquiry.ititle}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${manager_inquiry.icontent}</td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td>${manager_inquiry.mphone}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${manager_inquiry.memail}</td>
				</tr>
				<tr>
					<th>문의분류</th>
					<td>${manager_inquiry.iselect1}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><a href="Inquirydownload?p=resources/upload/&i=${manager_inquiry.ifile}
							">${manager_inquiry.ifile}</a></td>
				</tr>
				<tr>
					<th>답변내용</th>
					<td>
						<form action="manager_ireply" method="post">
							<input type="hidden" name="inum" value="${manager_inquiry.inum}"/>
							<textarea class="reply_input" name="ireply" cols="30" rows="10"  placeholder="답변을 입력해주세요.">${manager_inquiry.ireply }</textarea>								
							<input type="submit" value="저장" />
						</form>
					</td>
				</tr>
				<tr>
					<th>상태</th>
					<td>
						<form action="istate" method="post">
							<input type="hidden" name="inum" value="${manager_inquiry.inum}"/>
								<select name="istate">
									<option value="확인중">확인중</option>
									<option value="답변완료">답변완료</option>
								</select>
							<input type="submit" value="저장" />
						</form>
					</td>	
				</tr>
			</table>
				<div class="page_align">
					<a href="manager_inquiry">목록보기</a> &nbsp;&nbsp;
					<a href="inquirydelete?inum=${manager_inquiry.inum }">삭제</a>
				</div>
		</div>
	</div>
</div> 
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>