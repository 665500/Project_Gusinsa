<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
            <link rel="stylesheet" href="resources/css/men,women.only.css">
			<%@include file="../../../../resources/common/header.jsp" %>  


     

    
    

    


    <table class="guide">
        <tr>
            <td><a href="">HOME</a>&nbsp;></td>
            <td><a href="">MEN</a></td>
        </tr>
    </table>
    <table class="tb1" style=" position: relative; left: 350px;">
        <tr class="tr1">
            <td><a href="" id="tb1">인기상품순</a>&nbsp;|&nbsp;</td>
            <td><a href="" id="tb1">신상품순</a>&nbsp;|&nbsp;</td>  
            <td><a href="" id="tb1">낮은가격순</a>&nbsp;|&nbsp;</td>  
            <td><a href="" id="tb1">높은가격순</a></td>  
        </tr>
        
    </table>
    <table >
        <tr>
            <td class="category">BOTTOM</td>
        </tr>
    </table>
 
 <table class="t2">
        <tr>
          <c:forEach items="${menbottom }" var="dto" varStatus ="status">
          <c:if test="${status.index%4==0 }">
          </tr><tr>
          </c:if>  
               
                    <td class="td1">
                        <a href="BuyPage?p_code=${dto.p_code }">
                            <img src="${dto.p_img}" width="250" height="350" style="margin-right: 50px;"><p class="p1">${dto.p_name }</p></a>
                        <a href="BuyPage?p_code=${dto.p_code }"><p class="p1">${dto.p_price }/${dto.p_code }</p></a>
                    </td>
                  
            
     </c:forEach>
            
        </tr>
        
    </table>
    
    
    <div class="paging" style="padding-left: 800px; padding-bottom: 50px;">
    <form action="menbottom" method="post">
	<c:if test="${searchVO.totPage>1 }"> <!-- 토탈 페이지가 1보다 크면 -->
		<c:if test="${searchVO.page>1 }">
			<a href="menbottom?page=1">[처음]</a>
			<a href="menbottom?page=${searchVO.page-1 }">[이전]</a>
		</c:if>
	
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">  <!-- i eq searchVO.page 내가 클릭한 페이지가 같을 때 -->
				<span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			</c:when>
			 <c:otherwise>
				<a href="menbottom?page=${i }" style="text-decoration: none">${i }</a>&nbsp;
			</c:otherwise> 
		
		</c:choose>
	</c:forEach>
		<c:if test="${searchVO.totPage>searchVO.page }"> <!-- 마지막 페이지가 아닐때 -->
			<a href="menbottom?page=${searchVO.page+1 }">></a>
			<a href="menbottom?page=${searchVO.totPage }">>></a>
		</c:if>
	</c:if>
    </div>
    
    <div id='wrap'>
        <section id="wrap_section">
            
        </section>
        <footer>
            <div id="footer_addr">
    
          <p>
            <table>
            <tr>
                <td>상호명 : (주)구신사</td>
                <td>조장 : 이종현</td>
                <td>주소 : 서울시 구로구 </td>
            </tr>
            <tr>
                <td colspan="3">이메일 : dlwhdgus@gmail.com</td>
                <td></td>
                <td></td>
            </tr>
            </table>
          </p>
        </div>
    
          <div class="footer_div">
            <table>
                <tr>
                    <td>고객문의 대표전화</td>
                    <td>고객센터 문의전화</td>
                </tr>
                <tr>
                    <td>1588-1588</td>
                    <td>1588-3407</td>
                </tr>
                <tr>
                    <td>평일 09:00 ~ 18:00 <br>(주말 및 공휴일 휴무)</td>
                    <td><a href="#">구신사 상품 문의접수</a></td>
                </tr>
            </table>
          </div>
      </footer>
    </div>  
</body>
</html>