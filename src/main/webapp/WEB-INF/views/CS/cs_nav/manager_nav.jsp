<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
<link rel="stylesheet" href="resources/manager_nav.css?after" />
</head>
<body>
<div class="manager_nav">
	<div class="manager_title">
		<h2><a href="manager_inquiry.html">관리자 페이지</a></h2>
	</div>
	<ul>
		<li class="manager_left_list"><a href="manager_inquiry">
		1:1 문의하기 관리
		</a></li>
		<li class="manager_left_list"><a href="manager_commu">
		고객의 소리 관리
		</a></li>
		<li class="manager_left_list"><a href="manager_enter">
		입점 테이블 관리
		</a></li>
		<li class="manager_left_list"><a href="manager_members">
		회원 테이블 관리
		</a></li>
	</ul>
</div>          
</body>
</html>