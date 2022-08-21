<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의 소리 상세</title>
<link rel="stylesheet" href="resources/css/cs/manager.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager2.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="manager_main">
<%@include file="../cs_nav/manager_nav.jsp" %>  
	<div class="manager_board">
		<div class="manager_main_title">
			<h3>고객의 소리 상세</h3>
		</div>
		
		<div>
			<table class="manager_detail_table">
				<tr>
					<th>번호</th>
					<td>${manager_commu.cnum}</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${manager_commu.mid}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${manager_commu.mname}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${manager_commu.ctitle}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${manager_commu.ccontent}</td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td>${manager_commu.mphone}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${manager_commu.memail}</td>
				</tr>
				<tr>
					<th>문의분류</th>
					<td>${manager_commu.cselect1}</td>
				</tr>
				<tr>
					<th>구입처</th>
					<td>${manager_commu.cselect2}</td>
				</tr>
				<tr>
					<th>답변방법</th>
					<td>${manager_commu.cselect3}</td>
				</tr>
				<tr>
					<th>SMS수신여부</th>
					<td>${manager_commu.cselect4}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><a href="Commudownload?p=resources/upload/&c=${manager_commu.cfile}
							">${manager_commu.cfile}</a></td>
				</tr>
				<tr>
					<th>답변내용</th>
					<td>
						<form action="manager_creply" method="post">
							<input type="hidden" name="cnum" value="${manager_commu.cnum}"/>
							<textarea class="reply_input" name="creply" cols="30" rows="10" placeholder="답변을 입력해주세요." >${manager_commu.creply }</textarea>
							<input type="submit" value="저장" />
						</form>
					</td>
				</tr>
				<tr>
					<th>상태</th>
					<td>
						<form action="cstate" method="post">
							<input type="hidden" name="cnum" value="${manager_commu.cnum}"/>
								<select name="cstate">
									<option value="확인중">확인중</option>
									<option value="답변완료">답변완료</option>
								</select>
							<input type="submit" value="저장" />
						</form>
					</td>	
				</tr>		
			</table>
				<div class="page_align">
					<a href="manager_commu">목록보기</a> &nbsp;&nbsp;
					<a href="commudelete?cnum=${manager_commu.cnum }">삭제</a>
				</div>
		</div>
	</div>
</div>
<%@include file="../../../../resources/common/footer.jsp" %> 
</body>
</html>