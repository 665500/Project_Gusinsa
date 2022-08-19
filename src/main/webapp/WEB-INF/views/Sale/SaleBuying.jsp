<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head> 
<body onload="init();">
<%@include file="../../../../resources/common/header.jsp"%>
<style>

   .option_color{
     position:relative;
     left:5px;
     width:  150px;
     height: 40px;
      position:relative;
      
      
   }
   .option_size{
     position:relative;
     left:15px;
     width:  150px;
     height: 40px;
   }


    .a1{
        color:darkgray;
        
    }
    .a1:hover{
        color: gray;
    }
    .div1{
        margin-left: 100px;
        padding-top: 300px;
    }
    .div2{
        margin-top: 100px;
        margin-left: 100px;
    
    }
    .div3,.div4{
        float: left;
        margin-left: 200px;
    }
    .figcap1{
        font-weight: bold ;
        text-align: right;
        font-size: 40px;
    }
    .hr1{
        border: 0;
        height: 3px;
        background: black;
        width: 100%;
    }
    .figcap2{
        font-size: 20px;
    }

    .h2-1 {
       color: red;
    }
    .tb1{
        border-style: none;
        width: 100%;
    }
    .td1{
        font-size: 130%;
        font-weight: bold;
        height: 20px;
        padding-left: 15px;
        background-color: #ccc;
    }
    .td2{
        font-size: 130%;
        padding-left: 15px;
        padding-top: 15px;
    }
    .form1{
        text-decoration: line-through;
    }
    .form1,.form2,.form3{
        float: left;
    }
    .input1{
        width: 30px;
    }
    .form3{
        padding-left: 70px ;
        padding-right: 10px;
    }
    p {
        margin: 0 0 20px;
        line-height: 1.5;}
    .main {
        min-width: 300px;
        max-width: 800px;
        padding: 50px;
        background: #ffffff;
        padding-left: 9%;
        
        }
    section {
        display: none;
        padding: 20px 0 0;
        border-top: 1px solid #ddd;}
    /*라디오버튼 숨김*/
      #tab1,#tab2,#tab3,#tab4 {
          display: none;}
    label {
        display: inline-block;
        margin: 0 0 -1px;
        padding: 15px 25px;
        font-weight: 600;
        text-align: center;
        color: #bbb;
        border: 1px solid transparent;}
    label:hover {
        color: #2e9cdf;
        cursor: pointer;}
    /*input 클릭시, label 스타일*/
    input:checked + label {
          color: #555;
          border: 1px solid #ddd;
          border-top: 2px solid black;
          border-bottom: 1px solid #ffffff;}
    #tab1:checked ~ #content1,
    #tab2:checked ~ #content2,
    #tab3:checked ~ #content3,
    #tab4:checked ~ #content4 {
        display: block;}
    .content2{
        font-weight: bold;
        font-size: 100%;
    }
    .content3{
        font-weight: bold;
        font-size: 100%;
    }
    .btn1{
        color: black;
        width: 50%;
        height: 40px
    }
    .Ja1{
        text-decoration-line: none;
    }
    .btn2{
        color: white;
        background-color: black;
        width: 50%;
        height: 40px
    }
    .btn1,.btn2{
        float: left;
    }
    .div5{
        min-width: 320px;
        max-width: 800px;
        padding: 50px;
        background: #ffffff;
        padding-left: 9%;
    }
    .figcap3{
        font-weight: bold;
        font-size: 150%;
    }
    .tb2{
        border-style: none;
        width: 100%;
        height: 200px;
        border-collapse: collapse;
    }
    .td3{
        font-weight: bold;
        width: 25%;
    }
    .div6{
        padding-left: 9%;
    }
    
</style>

<script>

</script>


<script language="JavaScript">

var sell_price;
var amount;


function init () {
    sell_price = document.form.sell_price.value;
    amount = document.form.amount.value;
    document.form.sum.value = sell_price;
    change();
}

function add () {
    hm = document.form.amount;
    hm.value ++ ;

    var sum_ = parseInt(hm.value) * sell_price;
document.getElementById("my_sum").innerHTML=sum_;
}

function del () {
    hm = document.form.amount;
        if (hm.value > 1) {
            hm.value -- ;
            var sum_ = parseInt(hm.value) * sell_price;
document.getElementById("my_sum").innerHTML=sum_;
        }
}

function change () {
    hm = document.form.amount;

        if (hm.value < 0) {
            hm.value = 0;
        }
    var sum_ = parseInt(hm.value) * sell_price;
document.getElementById("my_sum").innerHTML=sum_;
} 

function move() {
   let amount = document.getElementById("amount").value;
   let sell_price = document.getElementById("sell_price").value;
   let mid = '<%=(String)session.getAttribute("mid")%>';
   let sum = sell_price * amount;
    let sping = 2500;
    let option_size = document.getElementById("option_size").value;
    if (sum > 50000){
       sping = 0;
    }
    
    let sumsping= sum+sping;
    
    
   if (mid=='null') {
      location.href ="Orderbeforelogin?p_code=${indto.p_code }"+"&amount="+ amount +"&sell_price="+sum+"&sping="+sping+"&sumsping="+sumsping+"&p_size="+option_size; 
   }  
   else{
      location.href ="Orderafterlogin?p_code=${indto.p_code }"+"&amount="+ amount +"&sell_price="+sum+"&sping="+sping+"&sumsping="+sumsping+"&p_size="+option_size;
   }
       
}   

function cart() {
   let amount = document.getElementById("amount").value;
   let sell_price = document.getElementById("sell_price").value;
   let psize = document.getElementById("option_size").value; 
   let sum = sell_price * amount;
   

   // 스페이스바 주의 null값 우려
   location.href ="buyingmypageinsert?p_code=${indto.p_code }"+"&amount="+ amount +"&sell_price="+sum +"&p_price=" +sell_price+"&p_size="+psize; 

}
</script>
    <br>
    <hr>
    <br>
<!--     
    <div class="div1">
        <a href="" class="a1">수납/생활</a> > <a href="" class="a1">생활용품/문구</a> > <a href="" class="a1">원목선반/테이블/의자</a>
    </div> -->
    <div class="div2">
        <figure>
            <h1>${indto.p_name }</h1>
        </figure>
    </div>
    <br>
    <div class="div3">
        <figure>
            <img src="resources/upload/p_img/${indto.p_img}" id="image1" 
            width="600" height="550" style="border: 1px solid #ccc;">
            <figcaption> </figcaption>
        </figure>
    </div>
    <div class="div4" style="position: relative; left: 100px">
        <figure class="fig1">
            <h2 class="h2-1">41%</h2>
            <figcaption class="figcap1"> ${indto.p_price } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;♡</figcaption> <br>
            <hr class="hr1"> <br>
            <figcaption class="figcap2">
                제품번호 : ${indto.p_code }<br>
                배송정보 : 10만원 이상 구매시 무료
            </figcaption> 
            <br> <br>
            <figcaption class="">
                <table border="1px" class="tb1">
                    <tr>
                        <td class="td1">상품 옵션</td>
                    </tr>

            
               <br />
                    
                    </tr>
                    <tr id="option_size" >
                    size  <select name="option_size" class="option_size" id ="option_size">
                    <option value="">선택</option>
                    <c:forEach var="strgdto" items="${strgdto }">
                    <option value="${strgdto.p_size }">${strgdto.p_size }</option>
                    </c:forEach>
                     </select>
                    </tr>   
               
               
               
                    <tr>
                        <td class="td2">단품 <br>
                           <form class="form1">47,900원</form>
                            <form class="form2">금액 : <div id="my_sum" name = "my_sum"></div>원</form>                       
                            <form class="form3" name = "form" method = "get"> 
                                <input type="text" id = "amount" name="amount" value="1" size="3" onchange="change();"> 
                        <input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>
                        <input type="hidden" name="sum" size="11" readonly>
                        수량 : <input type=hidden id = "sell_price" name="sell_price" value="${indto.p_price }">
                            </form> 
                            <br>
                            
                          
                        </td>
                        
                    </tr>
           
                    
                    
                </table>
            </figcaption>
            <br>
            

            <figcaption>
               <button type="button" class="btn1" onclick="cart()">장바구니 </button>
                <button type="button" class="btn2" onclick="move()" >구매하기</button>
            </figcaption>
        </figure>
    </div>
    <br> <br>
    <div class="main" >
        <input id="tab1" type="radio" name="tabs" checked> 
        <label for="tab1">상품정보</label>
    
        <input id="tab2" type="radio" name="tabs">
        <label for="tab2">상품리뷰</label>
    
        <input id="tab3" type="radio" name="tabs">
        <label for="tab3">배송/교환/환불안내</label>
     
        <section id="content1">
            <p>
                <img src="resources/upload/p_img/${indto.p_img2}" alt="rengoku2"
                width="700" height="900">
            </p>
            <p>
                <img src="resources/upload/p_img/${indto.p_img3}" alt="rengoku2"
               width="700" height="900">
            </p>
            <p>
                <img src="resources/upload/p_img/${indto.p_img4}" alt="rengoku2"
               width="700" height="900">
            </p>
            <p>
                <img src="resources/upload/p_img/${indto.p_img5}" alt="rengoku2"
               width="700" height="900">
            </p>

        </section>
    
        <section id="content2" class="content2">
        
        <!-- 여기서 부터 리뷰 붙여봄  -->
        <style>   
        
        .star-rating {
  position:relative;
  left:300px;
  border:solid 1px #ccc;
  display:flex;
  flex-direction: row-reverse;
  font-size:1.5em;
  justify-content:space-around;
  padding:0.2em;
  text-align:center;
  width:2em;
}

.star-rating input {
  display:none;
}

.star-rating label {
  color:#ccc;
  cursor:pointer;
}

.star-rating :checked ~ label {
  color:#f90;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  color:#fc0;
}
        div.restar-box{
         width:300px;
         height: 80px;
         border: 2px solid gray;
         text-align: center;
         }
      
      div.restar-total{
         font-size: 30px;
         font-wight: bold;
         }
              
        
        .header{border:solid 0px orange; 
               background-color:white; 
               color:black; 
               font-weight:bold; 
               text-align:center;
               width:160px;}   
         .content{border:solid 0px aqua; 
                background-color:white; 
                color:black;  
                text-align:center;
                width:800px; 
                height:120px; 
                padding:5 5 5 5;   
                display:none;   }
    </style>
    <script src='http://code.jquery.com/jquery.js'></script>
    <script type="text/javascript">   
    
    $(document).ready(function(){
       /** 초기화 처리*/
       // 첫 번재 항목이 펼쳐져 있도록 처리
       $("#acco1:eq(0)").addClass("selected"); //첫번째 항목만 selected 클래스를 적용
       $("#acco1").not(":eq(0)").find(".content").hide();//첫번째 항목 이외의 content를 숨김
       /** 링크에 대한 클릭 이벤트 처리 */    
       $("#acco1 .header").click(function(){   
             $(this).toggleClass("selected");
           //클릭된 나 자신을 제외한 나머리 링크들은 slected 클래스를 무조건 제거
             $("#acco1 .header").not(this).removeClass("selected"); 
          // 펼칠 내용 영역 선택
         var target = $(this).parents("#acco1").find(".content");
       // 나머지 내용 영역을 선택
      var other = $("#acco1 .header").not(this)
               .parents("#acco1").find(".content");
        // 애니메이션으로 열고 닫기 처리    
      target.slideToggle(300);
           other.slideUp(300);
           // 링크 페이지 이동 중지
           return false;
          });
      });
          
   </script>
   
   
<!--        <h4>상품리뷰</h4> -->
            <div class="restar-box">
                <div class="restar-total">${avg }</div>
                    <div class="restar-total-content1">최고 5점</div>
                        <div class="restar-total-content2">${totRowcnt }개 평가</div>
            </div>

   <c:forEach items="${review_list }" var="dto">
      <div>
         <span>${dto.mid }   
         </span>
         <span>
            <c:if test="${dto.r_starpoint==1 }">
            ★☆☆☆☆
            </c:if>
            <c:if test="${dto.r_starpoint==2 }">
            ★★☆☆☆
            </c:if>
            <c:if test="${dto.r_starpoint==3 }">
            ★★★☆☆
            </c:if>
            <c:if test="${dto.r_starpoint==4 }">
            ★★★★☆
            </c:if>
            <c:if test="${dto.r_starpoint==5 }">
            ★★★★★
            </c:if>
         </span>   
         <span><input type="hidden" name="r_num" value="${dto.r_num}"></span>
         <span style="float:right;"><fmt:formatDate value="${dto.r_date}" pattern = "yyyy.MM.dd"/></span>
      
         <span><input type="hidden" name="r_hit" value="${dto.r_hit }"></span>
         <div id='acco1'>
            <div class="header">${dto.r_title }</div>
            <div class="content">${dto.r_contents } <br/>
</div>
         </div>
         <span><input type="hidden" name="o_code" value="${dto.o_code}"></span>
      </div>
   </c:forEach>
         

<div style="text-align:center" >
<form action="SaleBuying" method="post">
   <c:if test="${researchVO.totPage>1 }"> <!-- 토탈 페이지가 1보다 크면 -->
      <c:if test="${researchVO.page>1 }">
         <a href="SaleBuying?page=1">&lt;&lt;</a>
         <a href="SaleBuying?page=${researchVO.page-1 }">&lt;</a>
      </c:if>
   <c:forEach begin="${researchVO.pageStart }" end="${researchVO.pageEnd }" var="i">
      <c:choose>
         <c:when test="${i eq researchVO.page }">  <!-- i eq searchVO.page 내가 클릭한 페이지가 같을 때 -->
            <span style="color: black; font-weight: bold;">${i }&nbsp;</span>
         </c:when>
         <c:otherwise>
            <a href="SaleBuying?page=${i }" style="text-decoration: none">${i }</a>&nbsp;
         </c:otherwise>
      </c:choose>
   </c:forEach>
      <c:if test="${researchVO.totPage>researchVO.page }"> <!-- 마지막 페이지가 아닐때 -->
         <a href="SaleBuying?page=${researchVO.page+1 }">&gt;</a>
         <a href="SaleBuying?page=${researchVO.totPage }">&gt;&gt;</a>
      </c:if>
   </c:if>

</form>
</div>
        </section>
    
        <section id="content3" class="content3">
            <p>
               <img src="resources/img/deli.png" alt="배송정보" width="800" height="400" /><br>
                <img src="resources/img/exc.png" alt="교환환불" width="800" height="800" />
            </p>
        </section>
      </div>
    

    <%@include file="../../../../resources/common/footer.jsp" %>
   
</body>



</html>