<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입점문의 상세</title>
<link rel="stylesheet" href="resources/css/cs/enter.css?after" />
<link rel="stylesheet" href="resources/css/cs/main.css?after" />
<link rel="stylesheet" href="resources/css/cs/board.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager.css?after" />
<link rel="stylesheet" href="resources/css/cs/manager2.css?after" />
</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="manager_main">
<%@include file="../cs_nav/manager_nav.jsp" %>
	<div class="manager_board">
		<div class="manager_main_title">
			<h3>입점문의 상세</h3>
		</div>
			<div class="enter_form">
	            <div class="enter_title">
	                <h2>기본정보</h2>
	                <p><span class="point">*</span>&nbsp; 는 필수 입력 항목입니다.</p>
	            </div>
	            <table class="enter_table"> 
	                <tr>
	                    <th>
	                        사업자 등록번호 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.ereginum}</td>
	                </tr>
	                <tr>
	                    <th>
	                        상호명 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.etradename}</td>
	                </tr>
	                <tr>
	                    <th rowspan="2">
	                        주소 <span class="point">*</span>
	                    </th>
	                    	<td>
	                    		${manager_enter.eaddr1}
	                    		${manager_enter.eaddr2}
	                    		
	                    	</td>
	                </tr>
	                <tr>
	                    <td>
	                    	${manager_enter.eaddr3}
	                    	${manager_enter.eaddr4}
	                    </td>
	                </tr>
	                <tr>
	                    <th>
	                        연매출 <span class="point">*</span>
	                    </th>
	                    <td>
	                    	${manager_enter.esales}
	                    </td>
	                </tr>
	            </table>
	        </div>
	
	        <div class="enter_form">
	            <div class="enter_title">
	                <h2>파트너 신청정보</h2>
	            </div>
	            <table class="enter_table"> 
	                <tr>
	                    <th>
	                        브랜드명 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.ebname}</td>
	                </tr>
	                <tr>
	                    <th>
	                        상품소개 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.ebintro}</td>
	                </tr>
	
	                <tr>
	                    <th>
	                        홈페이지 주소 <span class="point">*</span>
	                    </th>
	                    <td>
	                    	<a href="${manager_enter.ehomepage}">${manager_enter.ehomepage}</a>	
	                    </td>
	                </tr>
	            </table>
	        </div>
	
	        <div class="enter_form">
	            <div class="enter_title">
	                <h2>영업 담당자 정보</h2>
	            </div>
	            <table class="enter_table"> 
	                <tr>
	                    <th>
	                        담당자명 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.econtactname}</td>
	                </tr>
	                <tr>
	                    <th>
	                        직급 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.eposition}</td>
	                </tr>
	                <tr>
	                    <th>
	                        전화번호 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.echargenum}</td>
	                </tr>
	                <tr>
	                    <th>
	                        팩스번호 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.efax}</td>
	                </tr>
	                <tr>
	                    <th>
	                        휴대폰번호 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.ephone}</td>
	                </tr>
	                <tr>
	                    <th>
	                        이메일 <span class="point">*</span>
	                    </th>
	                    <td>${manager_enter.eemail}</td>
	                </tr>
	            </table>
	        </div>
		<div class="manager_enter_btn">
			<a href="manager_enter">목록</a>&nbsp;&nbsp;
			<a href="enterdelete?enumber=${manager_enter.enumber}">삭제</a>
		</div>	
	</div>
</div> 
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>