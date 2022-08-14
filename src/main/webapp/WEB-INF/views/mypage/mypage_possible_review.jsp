<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>주문배송 조회</title>
</head>
<style>
.cs_left_title{
    /* position: absolute;
    top: 100px;
    left: 70px; */
    color: #000;
    font-size: 30px;
    white-space: nowrap;
    font-weight: bold;
    border-bottom: 3px solid #000;
    width: 250px;
    margin: 20px 22px 0 0;
    padding-bottom: 20px;
   
}

.cs_left_nav{
    width: 250px;
    grid-area: nav;
    /* background-color: lightblue; */
    
}

.cs_left_list{
    border-bottom: 1px solid #e5e5e5;
    height: 60px;
    margin: 11px 0 0 0;
    font-size: 15px;

}

.cs_left_contact{
    line-height: 6%;
}

ul{
    list-style: none;
    -webkit-padding-start: 0;
}

.cs_main_board{
    grid-area: board;
}

/* .cs_table_notice{
    width: 70%;
    border-collapse: collapse;
   
} */

.cs_main{
    display: grid;
    grid-template-columns: 1fr 300px 1200px 1fr;
    grid-template-areas: " . nav board . ";
}

.cs_table_title{
    font-size: 22px;
}

.cs_table_bestfaq{
    border-collapse: collapse;
    display: block;
    width: 100%;
}

.cs_table_row1{
    width: 30%;
}

.cs_table_notice{
    display: block;
    width: 100%;
}

.line {
    font-size: 30px;
    border-bottom: 3px solid #000;
    margin: 20px 0 0 0;
    padding-bottom: 60px;
    
}

.line2 {
    font-size: 30px;
    border-bottom: 1px solid #b2b2b2;
    margin: 20px 0 0 0;
    padding-bottom: 60px;
    
}

th{
    border-bottom: 2px solid #000;
    text-align: left;
}

td{
    margin: 11px 0 0 0;
    font-size: 15px;
    /* height: 60px; */
}


.write_button {
	text-align: right;
}

.notice_search td {
	border-bottom: none;
}

.noticeboard_date {
	text-align: center;
}
.search_textarea {
	text-align: right;
}

.notice_search input {
	width: 300px;
	height: 32px;
	font-size: 15px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}

.notice_blank {
	width: 100%;
	height: 82px;
	border-bottom: 3px solid #000;
	
}

.notice_search {
	margin-bottom: 40px;
}


.noitce_table_content_view td{
	padding-left: 35px;
}
.noitce_table_content_view th{
	font-size: 20px;
	background-color: #F7F7F7;
	padding-left: 35px;
}
.notice_view_date {
	font-size: 15px;
	background-color: #F7F7F7;
/* 	border-bottom: 1px solid #000; */
	
}
.noticeboard_page{
	text-align: center;
}

.cs_main_board h3{
	margin-bottom: 50px;
}

.point {
    display: inline-block;
    margin-left: 10px;
    color: #ff3e2f;
    font-size: 17px;
    vertical-align: middle;
}

@charset "UTF-8";

table {
	border-collapse: collapse;
    width: 100%;
	border: none;
}

tr, td, th {
	border: none; 
}

th {
	/* border-bottom: 2px solid #000;  */
	height: 50px;
}

td {
/* 	border-bottom: 1px solid #e5e5e5; */
	height: 50px;
}

.noticeboard_title {
	width: 68%;
	text-align: left;
}

.noticeboard_td_date {
	text-align: center;
}

a {
	text-decoration: none;
	color: inherit;
}


#mypage_inquiry{
font-weight: bold;
}

a:hover{
font-weight: bold;
}

.paging{
    width : 100%;
    /* float:right; */
    margin-bottom : 50px;
     text-align: center;
}
#page{
       padding-bottom: 50px;
       text-align: center;
}

</style>

<body>
<%@include file="../../../../resources/common/header.jsp" %> 
<div class="cs_main">
<div class="cs_left_nav">
        <h2 class="cs_left_title"><a href="mypage_orderlist">
            마이페이지
        </a></h2>
        <ul>
            <li class="cs_left_list" ><a href="mypage_user_pwcheck">
                회원정보 수정
            </a></li>
            <li class="cs_left_list"><a href="mypage_orderlist" >
                주문 내역
            </a></li>
            <li class="cs_left_list"><a href="MyCart">
                장바구니
            </a></li>
            <li class="cs_left_list"><a href="inquiry">
                1:1 문의하기
            </a></li>
            <li class="cs_left_list"><a href="mypage_inquiry">
                문의내역
            </a></li>
            <li class="cs_left_list"><a href="membership">
                멤버쉽(클럽)안내
            </a></li>
        </ul>
        <h2 class="cs_left_title"><a href="mypage_orderlist">
            상품 리뷰
        </a></h2>
        <ul>
            <li class="cs_left_list" ><a href="mypage_possible_review" id = "mypage_orderlist">
                작성 가능한 리뷰
            </a></li>
            <li class="cs_left_list"><a href="mypage_written_review">
                내가 작성한 리뷰
            </a></li>
            </ul>
        
    </div>
</body>
</html>   
    <div class="cs_main_board">
       <div class="line"></div>
       <h2>작성 가능한 리뷰</h2>
       <table>
            <tr>
                <th class="faqboard_header">주문번호</th>
                <th class="faqboard_title">주문일자</th>
                <th class="faqboard_date">상품이름</th>
                <th class="faqboard_date">색상</th>
                <th class="faqboard_date">사이즈</th>
                <th class="faqboard_date">수량</th>
                <th class="faqboard_date">결제 금액</th>
                <th class="faqboard_date">리뷰상태</th>
            </tr>
            <c:forEach items="${order }" var="list" varStatus ="status" >
             <input type="hidden" name="p_code" id="p_code" value="${list.p_code }"/>
            <tr>
                <th>${list.o_code }</th>
                <th>${list.o_date }</th>
                <th>${list.p_name }</th>
                <th>${list.p_color }</th>
                <th>${list.p_size }</th>
                <th>${list.o_count }</th>
                <th>${list.o_price }</th>
                <th><a href="${pageContext.request.contextPath }/review_write_view?o_code=${list.o_code}&p_code=${list.p_code }">작성하기</a></th>
            </tr>
           
            </c:forEach>
        </table>
<div class="paging" >
    
	<c:if test="${searchVO.totPage>1 }"> <!-- 토탈 페이지가 1보다 크면 -->
		
	
	
	 <div id = "page"></div>
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">  <!-- i eq searchVO.page 내가 클릭한 페이지가 같을 때 -->
				<span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			</c:when>
			 <c:otherwise>
				
				<a href="mypage_possible_review?page=${i }" style="text-decoration: none">${i }</a>&nbsp;
				<!-- a태그 경로 : 현재 서치페이지 i(1페이지)에  검색어, like문에 사용한 컬럼명들을 그대로 유지 -->
			</c:otherwise> 
		
		</c:choose>
	</c:forEach>
		<c:if test="${searchVO.totPage>searchVO.page }"> <!-- 마지막 페이지가 아닐때 -->
		    <a href="mypage_possible_review?page=${searchVO.page+1 }">다음</a>
			<a href="mypage_possible_review?page=${searchVO.totPage }">마지막</a></c:if>
		<c:if test="${searchVO.page>1 }">
			<a href="mypage_possible_review?page=1">[처음]</a>
			<a href="mypage_possible_review?page=${searchVO.page-1 }">[이전]</a>
			<%-- 검색값 처음 이전 다음 마지막 누르면 검색값을 잃어버리기 때문에 컨트롤러에서 선언해준 keyword = ${resk }를 묶어준다 --%>
		</c:if>
	</c:if>
	</div>
    
    </div>

</body>
</html>