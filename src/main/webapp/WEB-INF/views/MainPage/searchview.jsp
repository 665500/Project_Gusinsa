<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<style>
    .search_page{
        width:100%;
        padding-top:50px;
        
        
    }
    #search_keyword{
        float: left;
        display: table-cell;
    vertical-align: middle;
    
    width: 340px;
    
    /* background: yellow; */
    text-align: center;   
    }
    #product_order{
        margin-top: 20px;
        width: 50%;
        height: 30px;

        float:left;
    }
    .product_search{
        margin-top: 20px;
    }
    .div_product{
        width: 50%;
        float: left;
    }
        .product_nosearch{
        margin-top: 50px;
    }
    .div_nosearch{
        margin-top: 5%;
        
        width: 50%;
        float: left; 
    }
    .paging{
    width : 100%;
    float:right;
    margin-bottom : 50px;
    
	    }
	#page{
        padding-bottom: 50px;
        text-align: center;
    }
    
    a{
    text-decoration: none;
    color: black;
    }
    
    a:hover{
    font-weight: bold;
    }
    </style>
    
<%--     <c:if test="${mid eq null }">
	<a href="../login/loginform">login</a>
</c:if>
 | 
 <c:if test="${mid ne null }">
 	<a href="../login/logout">logout</a>
 |
 </c:if> --%>
     <%-- ${keyword } --%>
<body>
<%@include file="../../../../resources/common/header.jsp" %> 
<c:if test="${not empty product }">
    <div class="search_page">
        <div id="search_keyword">
            <h3><span>home → search → ${keyword }</span></h3>
        </div>
        <div id="product_order">
            <table class="product_order_table" >
                <tr class="product_order_table_tr">
                    <td><a href="" id="tb1">인기상품순</a>&nbsp;|&nbsp;</td>
                    <td><a href="" id="tb1">신상품순</a>&nbsp;|&nbsp;</td>  
                    <td><a href="" id="tb1">낮은가격순</a>&nbsp;|&nbsp;</td>  
                    <td><a href="" id="tb1">높은가격순</a></td>
                </tr>
                
            </table>
        </div>
        <div class="div_product">
        <table class="product_search">
         <tr>
      <c:forEach items="${product }" var="list" varStatus ="status">
          <c:if test="${status.index%4==0 }"> </tr><tr>
          
          </c:if>  
               
                    <td class="td1">
                        <a href="BuyPage?p_code=${list.p_code }">
                            <img src="resources/upload/p_img/${list.p_img}" width="250" height="350" style="margin-right: 50px;"><p class="p1">${list.p_name }</p></a>
                        <a href="BuyPage?p_code=${list.p_code }"><p class="p1">${list.p_price }</p></a>
                    </td>
     </c:forEach>
     </tr>
     </table>
     
     검색된 갯수 : ${totRowcnt } <br />
    
        </div>




  <div class="paging" >
    
	<c:if test="${searchVO.totPage>1 }"> <!-- 토탈 페이지가 1보다 크면 -->
		<c:if test="${searchVO.page>1 }">
			<a href="search?page=1&keyword=${resk}">[처음]</a>
			<a href="search?page=${searchVO.page-1 }&keyword=${resk}">[이전]</a>
			<%-- 검색값 처음 이전 다음 마지막 누르면 검색값을 잃어버리기 때문에 컨트롤러에서 선언해준 keyword = ${resk }를 묶어준다 --%>
		</c:if>
	
	
	 <div id = "page">
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">  <!-- i eq searchVO.page 내가 클릭한 페이지가 같을 때 -->
				<span style="color: red; font-weight: bold;">${i }&nbsp;</span>
			</c:when>
			 <c:otherwise>
				<a href="search?page=${i }&keyword=${resk}&pname=${pname==true?'pname':''}&pclass=${pclass==true?'pclass':''}" style="text-decoration: none">${i }</a>&nbsp;
				<!-- a태그 경로 : 현재 서치페이지 i(1페이지)에  검색어, like문에 사용한 컬럼명들을 그대로 유지 -->
			</c:otherwise> 
		
		</c:choose>
	</c:forEach>
		<c:if test="${searchVO.totPage>searchVO.page }"> <!-- 마지막 페이지가 아닐때 -->
			<a href="search?page=${searchVO.page+1 }&keyword=${resk}">다음</a>
			<a href="search?page=${searchVO.totPage }&keyword=${resk}">마지막</a>
		</c:if>
	</c:if>
	
	</div>
	
    </div>
    </c:if>

   
   
    












<c:if test="${empty product }">
    <div class="search_page">
        <div id="search_keyword">
            <h3><span>home → search → ${keyword }</h3>
        </div>
        <div id="product_order">
            <table class="product_order_table" >
                <tr class="product_order_table_tr">
                    <td><a href="" id="tb1">인기상품순</a>&nbsp;|&nbsp;</td>
                    <td><a href="" id="tb1">신상품순</a>&nbsp;|&nbsp;</td>  
                    <td><a href="" id="tb1">낮은가격순</a>&nbsp;|&nbsp;</td>  
                    <td><a href="" id="tb1">높은가격순</a></td>  </span>
                </tr>
                
            </table>
        </div>
        <div class="div_nosearch">
            <table class="product_nosearch">
                <ul> <h1>${keyword }에 대한 검색결과가 없습니다.</h1>
                    <li style="margin-bottom: 30px;">검색어가 올바르게 입력되었는지 확인해 주세요.</li>
                    <li style="margin-bottom: 30px;">일반적인 검색어로 다시 검색해 주세요.</li>
                    <li style="margin-bottom: 30px;">검색어의 띄어쓰기를 다르게 해보세요.</li>
                </ul>
            </table>
        


        </div>

    </div>
</c:if>
    
</body>
</html>