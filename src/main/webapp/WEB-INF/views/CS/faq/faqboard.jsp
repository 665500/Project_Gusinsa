<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/cs/main.css?after" />
<link rel="stylesheet" href="resources/css/cs/board.css?after" />
<link rel="stylesheet" href="resources/css/cs/faq.css?after" />
</head>
<body>
<script>
	function toggleswitch(element) {
		var toggleswitch = element.nextElementSibling;
		if (toggleswitch.style.display == 'none') {
			toggleswitch.style = "display:table-row; *display:block"; // tr태그가 가지고 있는 display 속성의 기본 값이 table-row이다.
		} else {
			toggleswitch.style.display = 'none';
		}
	}
</script>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="cs_main">
<%@include file="../cs_nav/cs_left_nav.jsp" %> 
<div class=cs_main_board>
	    <div class="line"></div>
	    <div class="faq_search">
	        <h2>FAQ</h2>
	    </div>
	    
	    <table class="faq_view">
	        <tr>
	            <th class="faqboard_header">분류</th>
	            <th class="faqboard_title">제목</th>
	            <th class="faqboard_date">작성일자</th>
	        </tr>
	        <c:forEach items="${faqboard }" var="dto" begin="0" end="10">
	        
	            <tr class="faqtoggle" onclick="toggleswitch(this)" >
	                <td class="fheader">
	                   	${dto.fheader }
	                </td>
	                <td>${dto.ftitle }</td>    
	                <td>${dto.fdate }</td> 
	            </tr>
	            <tr class="faqcontent">
	            	<td colspan="3" class="faqcontent2"><pre> ${dto.fcontent }</pre>
						<c:if test="${ mgrade eq '관리자' }">	
							<a href="faqdelete?fnum=${dto.fnum}">삭제</a>
						</c:if>	
	            	</td>
	            </tr>
	        </c:forEach>
	    </table>
	    <div class="write_button">
	    	<c:if test="${ mgrade eq '관리자' }">
	        	<a href="faq_writeview">글쓰기</a>
	        </c:if>
	    </div>
	    
	    <div class="paging">
			<form action="faqboard" method="post">
				<c:if test="${searchVO.totPage>1 }">
			      <c:if test="${searchVO.page>1}">
			     	 <a href="faqboard?page=1">[처음]</a>
			     	 <a href="faqboard?page=${searchVO.page-1 }">[이전]</a>
			      </c:if>
	     			<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
				         <c:choose>
				            <c:when test="${i eq searchVO.page }">
				               <span style="color: red; font-weight: bold;">${i }&nbsp;</span>
				            </c:when>
				            <c:otherwise>
				               <a href="faqboard?page=${i }" style="text-decoration: none">${i }</a>  &nbsp;
				      		</c:otherwise>
				         </c:choose>
	     			</c:forEach>
					<c:if test="${searchVO.totPage>searchVO.page}">
				      <a href="faqboard?page=${searchVO.page+1 }">[다음]</a>
				      <a href="faqboard?page=${searchVO.totPage }">[마지막]</a>
				    </c:if>
	  			</c:if>
			</form>
		</div>
	  
	</div>
</div>   
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>