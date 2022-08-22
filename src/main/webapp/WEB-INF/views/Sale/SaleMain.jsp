<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

</head>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
    <table class="tb1">
        <tr class="tr1">
            <td>인기상품순&nbsp;|&nbsp;</td>
            <td>신상품순&nbsp;|&nbsp;</td>  
            <td>낮은가격순&nbsp;|&nbsp;</td>  
            <td>높은가격순</td>  
        </tr>
        
    </table>
    <br><br><br><br><br>
    <table class="t2" align="center">
    <tr>
    <c:forEach items="${SaleMain }" var="dto" varStatus ="status">
	    <c:if test="${status.index%4==0 }"></tr>             
	        <tr class="tr1">
	    </c:if>
           
            <td class="td1">
                <a href="SaleBuyPage?&p_code=${dto.p_code}&sprice=${dto.sprice }">
                    <img src="resources/upload/p_img/${dto.p_img}" width="300" height="350"><p class="p1">${dto.p_name } <br /> ${dto.sprice } </p>
                </a>    
            </td>
            
         </c:forEach>
        </tr>
       
    </table>
       

    
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>