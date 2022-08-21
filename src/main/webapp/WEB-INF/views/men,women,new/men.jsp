<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>men</title>
       <link rel="stylesheet" href="resources/css/men,women.css">
</head>

<body>
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
            <td><a href="datedesc" id="tb1">신상품순</a>&nbsp;|&nbsp;</td>  
            <td><a href="Pricelistasc" id="tb1">낮은가격순</a>&nbsp;|&nbsp;</td>  
            <td><a href="Pricelistdesc" id="tb1">높은가격순<a href="">${p_name }</a><a href="">${Men.p_name }</a></a></td>  
        </tr>
        
    </table>
    <table >
        <tr>
            <td class="category">MEN</td>
        </tr>
    </table>
    <table class="category_bt">
        <tr>
            <td><a href="./menouter">OUTER</a></td>
        </tr>
        <tr>
            <td><a href="/mentop">TOP</a></td>
        </tr>
        <tr>
            <td><a href="menbottom">BOTTOM</a></td>
        </tr>
        <tr>
            <td><a href="menacc">ACCESSORIES</a></td>
        </tr>
        <tr>
            <td><a href="menlife">LIFE</a></td>
        </tr>
    </table>

    <table class="t2">
        <tr>
     	<c:forEach items="${men }" var="dto" varStatus="status">
     	<c:if test="${status.index%4==0 }">
          </tr><tr>
          </c:if> 
            <td class="td1">
                <a href="BuyPage?p_code=${dto.p_code }">
                    <img src="resources/upload/p_img/${dto.p_img}" width="250" height="350"><p class="p1">${dto.p_name }</p></a>
                <a href="BuyPage?p_code=${dto.p_code }"><p class="p1">${dto.p_price } /  ${dto.p_code }</p></a>
            </td>
          
         </c:forEach>
        </tr>
    </table>

   
   <div class="paging" style="padding-left: 800px; padding-bottom: 50px;">
    <form action="men" method="post">
	<c:if test="${searchVO.totPage>1 }"> <!-- 토탈 페이지가 1보다 크면 -->
		<c:if test="${searchVO.page>1 }">
			<a href="men?page=1">[처음]</a>
			<a href="men?page=${searchVO.page-1 }">[이전]</a>
		</c:if>
	
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">  <!-- i eq searchVO.page 내가 클릭한 페이지가 같을 때 -->
				<span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			</c:when>
			 <c:otherwise>
				<a href="men?page=${i }" style="text-decoration: none">${i }</a>&nbsp;
			</c:otherwise> 
		
		</c:choose>
	</c:forEach>
		<c:if test="${searchVO.totPage>searchVO.page }"> <!-- 마지막 페이지가 아닐때 -->
			<a href="men?page=${searchVO.page+1 }">></a>
			<a href="men?page=${searchVO.totPage }">>></a>
		</c:if>
	</c:if>
    </div>
  
  
  
   <%@include file="../../../../resources/common/footer.jsp" %>
   
</body>



</html>