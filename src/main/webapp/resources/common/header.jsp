<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
     <style>


        html,body{
            margin: 0;
            padding: 0;
        }
        header{
                width: 100%;
                height: 180px;
                position: fixed;
                background:white;
                z-index: 5;
            }
        .header_left{ 
        margin-top: 14px;
        }
   
        
        #high_con,#con{ text-decoration: none;   /* a태그 밑줄제거 */
              color: black;       /*메뉴 a태그 id*/
        }
        #high_con:hover{
            font-weight: bold;
        }
        
        /* #con{
            background-color: #a8a8a8;
        } */
        .BRAND_Second li,.WOMEN_Second li,.Man_Second li{
            background-color: rgb(207, 188, 188); border: none; /*메뉴 하위메뉴 배경색상 지정*/
        }
        
        #con img{
            text-align: center;
            margin-top: 20px;
            width: 80%;
        }
        #con:link{ color : black}
        #con:hover{font-weight: bold; text-decoration: underline; }
        input:-ms-input-placeholder{color:#a8a8a8;}
        input::-webkit-input-placeholder{color:#a8a8a8;}
        input::-moz-placeholder{color:#a8a8a8;}
        #product_menu_list{font-size: 24px;display: inline-block; left: 16.5%;  z-index: 1;} /* 메뉴 카테고리 좌우 이동*/
        .first_hearder{ height: 30px; width: 360px; border: 1px solid #0b0c0e; margin-top: 5px;}
        #fisrst_search{ font-size: 16px; width: 300px; height: 25px; padding: 0px; border: 0px; outline: none; float: left; }
        #button_search{ width: 40px; height: 99%; border: 0px; background: #020202; outline: none; float: right; color: #ffffff}
        #header_gita li,.first_hearder{list-style-type: none;float: right; margin-right: 30px;}  /*로그인 장바구니 목록 설정*/
        #sidelist{ margin-right: 0;}
        .header_img{ width: 40px; height: 2px;   margin-bottom: 3px;}
        #header_right_img{ 
            float: left; width: 180px; height: 40px; margin-left: 20px;
        }
        .header_sess{
         float: left; width: 500px; height: 40px; margin-left: 20px;
        }
        #hearder_color{ background: #020202; background-color: #020202; width: 100%;height: 50px;margin-bottom: 30px;float: right;text-align: center;}
        /* body{width: 0 auto;text-align: center;} */
        /* ----------------------------------------- 헤더메뉴 ----------------------------------------- */
        nav{  width: 100%; height: 180px;
        background-color:white;}
        
        /*menu_list 상위 메뉴*/
        #menu_list{
            width: 100%;
            height: 200px;
          
        }
        /* ----------------------------------------- 메뉴 MEN ----------------------------------------- */
        .header_menu_Man li{ float: left; padding: 0 50px; line-height: 36px; position: relative; }
        .Man_Second{ margin-bottom: 20px; position:absolute;  top:100%; left: 0%; right: 200%; display: none;   /* 중분류 될 리스트를 숨김 */}
        .Man_Second li{width: 300px; height: 40px; padding-bottom: 7px;  } /* 크기 및 이동조절*/
        .header_menu_Man:hover .Man_Second{display: block;}
        /* ----------------------------------------- MEN 끝 및 브랜드 시작 ----------------------------------------- */
        .header_menu_BRAND li{ float: left; padding: 0 50px; line-height: 36px; position: relative;}
        .BRAND_Second{ margin-bottom:20px; position: absolute; top:100%; left: 0; right: 400%; display: none; /* 중분류 될 리스트를 숨김 */}
        .BRAND_Second li{ width: 300px; height: 40px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
        .header_menu_BRAND:hover .BRAND_Second{ display: block; }
        /* ----------------------------------------- 브랜드 three ----------------------------------------- */
        .BRAND_Three{   position:absolute; bottom: 100%; top: -1px; left: 69.5%; text-align: center; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
        .BRAND_Three li{ width: 300px; height: 200px;  }  /*중분류 리스트 사이즈*/
        .Restock:hover .BRAND_Three{ display: block; }
         /*------------------------------------ 안헷갈리게 ViewallBrands_Three------------------------------------------------------------------------------*/
         .ViewallBrands_Three{ margin-bottom:10px; position:absolute; top: 0px;  left: 69.5%; text-align: center; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */}
        .ViewallBrands_Three li{ width: 300px; height: 200px; }  /*중분류 리스트 사이즈*/
        .ViewallBrands:hover .ViewallBrands_Three{ display: block; }
        /* ----------------------------------------- 브랜드 끝 ----------------------------------------- */
        /*------------------------------------ 안헷갈리게 MAN_Three 중분류들 ------------------------------------------------------------------------------*/
        .OUTER_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: 0px;  left: 69.5%;  /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
        .OUTER_Second li{ width: 300px; height: 40px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
        .OUTER:hover .OUTER_Second{ display: block; }
        .TOP_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: -1px;  left: 69.5%; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
        .TOP_Second li{ width: 300px; height: 40px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
        .TOP:hover .TOP_Second{ display: block; }
        
        .LIFE_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: -1px;  left: 69.5%; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
        .LIFE_Second li{ width: 300px; height: 40px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
        .LIFE:hover .LIFE_Second{ display: block; }
        /*------------------------------------ 안헷갈리게 MAN_Three 끝 및 WOMEN_SECOND 시작------------------------------------------------------------------------------*/
        .header_menu_WOMEN #product_menu_list{  float: left;  padding: 0 50px;  line-height: 36px; position: relative; }
        .WOMEN_Second{ margin-bottom: 10px; position: absolute; top:95%; left: 0; right: 500%; display: none;    /* 중분류 될 리스트를 숨김 */ }
        .WOMEN_Second #product_menu_list{ width: 350px; height: 40px; padding-bottom: 7px;  left: 18.6%; } /*우먼 2단계리스트 크기 좌우 조절*/
        .header_menu_WOMEN:hover .WOMEN_Second{ display: block; }
        /* ----------------------------------------- 세일----------------------------------------- */
        .header_menu_SALE li{  float: left; padding: 0 50px; line-height: 36px; position: relative; }
        .SALE_Second{ margin-bottom: 10px; position: absolute; top:140%; left: 0; right: 500%; display: none;    /* 중분류 될 리스트를 숨김 */}
        .SALE_Second li{ width: 500px; height: 60px; padding-top: 15px; }
        .header_menu_SALE:hover .SALE_Second{ display: block;}
        /* ----------------------------------------- NEW ARRIVAL ----------------------------------------- */
        .header_menu_NEWARRIVAL li{  float: left; padding: 0 50px; line-height: 36px; position: relative; }
        .SALE_Second{ margin-bottom: 10px; position: absolute; top:140%; left: 0; right: 500%; display: none;    /* 중분류 될 리스트를 숨김 */}
        .SALE_Second li{ width: 500px; height: 60px; padding-top: 15px; }
        .header_menu_NEWARRIVAL:hover .SALE_Second{ display: block;}
        /* ----------------------------------------- 유저리뷰 ----------------------------------------- */
        .header_menu_Userrivw li{  float: left; padding: 0 50px; line-height: 36px; position: relative; }
        .SALE_Second{ margin-bottom: 10px; position: absolute; top:140%; left: 0; right: 500%; display: none;    /* 중분류 될 리스트를 숨김 */}
        .SALE_Second li{ width: 500px; height: 60px; padding-top: 15px; }
        .header_menu_Userrivw:hover .SALE_Second{ display: block;}
        .side_list{
            background:url("../headerimg/목록.png");
            border : 0;
            outline: 0;
            margin-right: 100px;
        
        }
            /*사이드메뉴 추가 스타일*/
            .sidebar{
        width: 500px;
        height: 100%;
        background: rgb(63, 62, 62);
        position: fixed;  /*고정 드래그하면 사이드메뉴도 드래그되어버려 fixed 고정*/
        top: 0;
        right: -500px;  /*화면밖으로 내보내기위해 -값*/
        z-index: 1;   /*포지션값의 우선순위*/
        transition: all .35s;
        opacity: 0.5;
        
        }
        
        #menuicon:checked+label+div{
        right: 0;
        }
        #menuicon{
        float: right;
        display: none;
        }
        #menuicon +label{
        display: block;
        width: 50px;
        height: 30px;
        position: relative;
        right: 0;
        top: calc(50% - 25px);
        transition: all .0s;
        cursor: pointer;
        margin-top: 8px;
        
        } 
        #menuicon + label span{
        display: block;
        position: absolute;
        width: 100%;
        height: 4px;
        border-radius: 30px;
        background: #000;
        transition: all 1s;
        }
        
        input[id="menuicon"]:checked +label span:nth-child(1){
        top: 50%;
        transform: translateY(-50%) rotate(45deg);
        
        }
        input[id="menuicon"]:checked +label span:nth-child(2){
        opacity: 0;
        }
        input[id="menuicon"]:checked +label span:nth-child(3){
        bottom: 50%;
        transform: translateY(50%) rotate(-45deg);
        }
        #menuicon + label span:nth-child(1){
        top: 0;
        }
        #menuicon + label span:nth-child(2){
        top: 50%;
        transform: translateY(-50%);
        }
        #menuicon + label span:nth-child(3){
        bottom: 0;
        }
        
        #menuicon:checked+label+div{
        right: 0;
        }
        #menuicon:checked+label{
        z-index: 2;
        right: 500px;
        }
        .sidebar_ul{
        margin-top: 30%;
        
        }
        .sidebar_ul #sidebar_li{
        margin-bottom: 10%;
        font-size: 18px;
        text-align: right;
        margin-right: 70%;  
        height: 30px;
        line-height: 30px;
        list-style:none;
        }
        
        #sidebar_li_a:hover{
        font-weight: bold;
        }
        #sidebar_li:hover{
        font-weight: bold;
        }
        
        #sidebar_li_a{
        text-decoration:none
        }

        #header_color{
            background: rgb(255, 255, 255);
            width: 100%;
            height: 250px;
        }
    </style>
    
    <script>
var tid;
var cnt = parseInt(3000);//�ʱⰪ(�ʴ���)
function counter_init() {
	tid = setInterval("counter_run()", 1000);
}

function counter_reset() {
	clearInterval(tid);
	cnt = parseInt(3000);
	counter_init();
}

function counter_run() {
	document.all.counter.innerText = time_format(cnt);
	cnt--;
	if(cnt < 0) {
		clearInterval(tid);
		self.location = "logout.php";
	}
}
function time_format(s) {
	var nHour=0;
	var nMin=0;
	var nSec=0;
	if(s>0) {
		nMin = parseInt(s/60);
		nSec = s%60;

		if(nMin>60) {
			nHour = parseInt(nMin/60);
			nMin = nMin%60;
		}
	} 
	if(nSec<10) nSec = "0"+nSec;
	if(nMin<10) nMin = "0"+nMin;

	return ""+nHour+":"+nMin+":"+nSec;
}
</script>
<script>
counter_init();
</script>
<body>
 <header>															<!--../../../resources/common/header.jsp --> 
        <div class="header_left"><a href="/project_shopping_mall"><img src="${pageContext.request.contextPath }/resources/headerimg/GUSINSA.png" alt="" id="header_right_img" style="width: 120px; height: 50px;"></a></div>
        <div class="header_sess"><c:if test="${not empty mid }"> <span id="counter"> </span> 후 자동로그아웃  ${mid } 님 반갑습니다!<input type="button" value="연장" onclick="counter_reset()"></c:if>
        						 <c:if test="${not empty ereginum }"> <span id="counter"> </span> 후 자동로그아웃  ${ereginum  } 님 반갑습니다!<input type="button" value="연장" onclick="counter_reset()"></c:if></div>
        <div class="header_right">
        <ul id="header_gita">
            <li><input type="checkbox" class="side_list"  id="menuicon">
                <label for="menuicon">
                    <span id="menuicon_span"></span>
                    <span id="menuicon_span"></span>
                    <span id="menuicon_span"></span>
                </label>
                <div class="sidebar">
                    <ul class="sidebar_ul">
                        
                        <li id="sidebar_li"><a href="${pageContext.request.contextPath }/csmain" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>고객센터</b></a></li>
                         <c:if test="${empty mid }"> 
                        <li id="sidebar_li"><a href="${pageContext.request.contextPath }/login/loginform" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>로그인</b></a></li>
                         </c:if> 
                        <li id="sidebar_li"><a href="${pageContext.request.contextPath }/join/joinform2" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>회원가입</b></a></li>
                         <c:if test="${not empty mid }"> 
                        <li id="sidebar_li"><a href="${pageContext.request.contextPath }/mypage_orderlist" id="sidebar_li_a" style="color: rgb(255, 255, 255);" ><b>마이페이지</b></a></li>
                         </c:if>
                         <c:if test="${mgrade eq '관리자'}"> 
                         <li id="sidebar_li"><a href="${pageContext.request.contextPath }/manager_inquiry" id="sidebar_li_a" style="color: rgb(255, 255, 255);" ><b>매니저 페이지</b></a></li>
                        </c:if>
                    </ul>
                </div></li>
                <!-- /project_shopping_malltest/src/main/webapp/resources/headerimg -->
            <!-- <li><a href=#><img src="../headerimg/목록.png" style="margin-right: 80px;" alt="목록" class="header_img"></a></li> -->
            <li><a href="${pageContext.request.contextPath }/MyCart"><img src="${pageContext.request.contextPath }/resources/headerimg/장바구니.png" style="height: 47px;" alt="장바구니" class="header_img"></a></li>
            <c:if test="${empty mid && empty ereginum }"> 
            <li><a href="${pageContext.request.contextPath }/login/loginform"><img src="${pageContext.request.contextPath }/resources/headerimg/login.png" style="height: 47px;" alt="로그인" class="header_img"></a></li>
            </c:if>
            <c:if test="${not empty mid || not empty ereginum }"> 
            <li><a href="${pageContext.request.contextPath }/login/logout"><img src="${pageContext.request.contextPath }/resources/headerimg/logout.png" style="height: 47px;" alt="로그아웃" class="header_img"></a></li>
            </c:if>
            
            <%-- <li><a href="${pageContext.request.contextPath }/login/loginform"><img src="${pageContext.request.contextPath }/resources/headerimg/login.png" style="height: 47px;" alt="로그인" class="header_img"></a></li> --%>
             <!-- 로그인 안했을때 그림 로그인했을때 로그아웃 그림 필요 if문 써야함 -->

        </ul>
        </div>
        <form action="search">
        <div class="first_hearder">
            <input type="text" placeholder="검색어 입력" id="fisrst_search" name = "keyword">
            <input type="submit" id="button_search" value="검색" />
        </div>
        </form>
        <div id="hearder_color"></div>
    
        <div id ="menu_list">
            <nav>
            <ul class="header_menu_NEWARRIVAL">
                <li id="product_menu_list"><a href="${pageContext.request.contextPath }/newarrival" id="high_con">NEW ARRIVAL</a>
    
                </li>
            </ul>
            <ul class="header_menu_BRAND">
                <li id="product_menu_list"><a href="${pageContext.request.contextPath }/brand" id="high_con">BRAND</a>
                    <ul class="BRAND_Second">
                        <li class ="ViewallBrands" id="product_menu_list"><a href="brand8" id="con" >AFTER PRAY</a>
                            <ul class="ViewallBrands_Three">
                                <li id="product_menu_list"><a href="brand8" id="con">
                                    <img src="${pageContext.request.contextPath }/resources/brandimg/brand_8.jpg"   alt="">
                                </a></li>        
                                
                                
                            </ul>
                            </li>
    
                        <li class="Restock"  id="product_menu_list"><a href="brand4" id="con">BROWNYARD</a>
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand4" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_4.jpg" alt=""></a></li>        
                                
                            </ul>
                            </li>
                        <li class="Restock"  id="product_menu_list"><a href="brand9" id="con">EASTLOGUE PERMANENT</a>
                                
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand9" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_9.jpg" alt=""></a></li>        
                                
                            </ul>
                            </li>
                        <li class="Restock"  id="product_menu_list"><a href="brand2" id="con">HOMEGROWN SVPPLY</a>
                                
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand2" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_2.jpg" alt=""></a></li>        
                                
                            </ul>
                            </li>
                        <li class="Restock"  id="product_menu_list"><a href="brand7" id="con">LEARVE</a>
                                
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand7" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_7.jpg" alt=""></a></li>        
                                
                            </ul>
                            </li>
                        <li class="Restock"  id="product_menu_list"><a href="brand3" id="con">NEITHERS</a>
                                
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand3" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_3.jpg" alt=""></a></li>        
                                    
                            </ul>
                        </li>
                        <li class="Restock"  id="product_menu_list"><a href="brand1" id="con">YOUTH</a>
                                
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="brand1" id="con"><img src="${pageContext.request.contextPath }/resources/brandimg/brand_1.jpg" alt=""></a></li>        
                                    
                            </ul>
                        </li>
                        

    
                       
                    </ul>
                </li>
            </ul>
                <ul class="header_menu_Man">
                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/men" id="high_con">MEN</a>
                        <ul class="Man_Second">
                            <li class="OUTER"  id="product_menu_list"><a href="${pageContext.request.contextPath }/menouter" id="con">OUTER</a>
                                <ul class="OUTER_Second">
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menjacket" id="con">JACKET</a></li>        
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/mencoat" id="con">Coat</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menjumper" id="con">Jumper</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menblazer" id="con">Biazer</a></li> 
                                </ul></li>
                            <li class="TOP" id="product_menu_list"><a href="${pageContext.request.contextPath }/mentop" id="con">TOP</a>
                                <ul class="TOP_Second">
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menshirtslong" id="con">Shirts(LS)</a></li>        
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menshirtshort" id="con">Shirts(1/2)</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menteelong" id="con">Tee(LS)</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menteeshort" id="con">Tee(1/2)</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/mensleeveless" id="con">Sleeveless</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menknit" id="con">Knit</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menvest" id="con">Vest</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menhoodie" id="con">Hoodie</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/mensweatshirt" id="con">Sweatshirts</a></li> 
                                </ul></li>
                            <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menbottom" id="con">BOTTOM</a></li>
                            <li id="product_menu_list"><a href=# id="con">ACCESSORIES</a></li>
                            <li class="LIFE" id="product_menu_list"><a href="${pageContext.request.contextPath }/menlife" id="con">LIFE</a>
                                <ul class="LIFE_Second">
                                    <li id="product_menu_list"><a href="" id="con">HOME</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Beauty</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Paper</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Digital</a></li> 
                                </ul></li>
                        </ul>
                    </li>
                </ul>
                <ul class="header_menu_WOMEN">
                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/women" id="high_con">WOMEN</a>
                        <ul class="WOMEN_Second">
                            <li class="OUTER" id="product_menu_list"><a href="${pageContext.request.contextPath }/womenouter" id="con">OUTER</a>
                                <ul class="OUTER_Second">
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenjacket" id="con">JACKET</a></li>        
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womencoat" id="con">Coat</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenjumper" id="con">Jumper</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenblazer" id="con">Biazer</a></li> 
                                </ul></li>
                            <li class="TOP" id="product_menu_list"><a href=# id="con">TOP</a>
                                <ul class="TOP_Second">
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenshirtslong" id="con">Shirts(LS)</a></li>        
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenshirtshort" id="con">Shirts(1/2)</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenteelong" id="con">Tee(LS)</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenteeshort" id="con">Tee(1/2)</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womensleeveless" id="con">Sleeveless</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenknit" id="con">Knit</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenvest" id="con">Vest</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womenhoodie" id="con">Hoodie</a></li> 
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/womensweatshirt" id="con">Sweatshirts</a></li> 
                                </ul></li>
                            <li id="product_menu_list"><a href=# id="con">BOTTOM</a></li>
                            <li id="product_menu_list"><a href=# id="con">ACCESSORIES</a></li>
                            <li class="LIFE"  id="product_menu_list"><a href=# id="con">LIFE</a>
                                <ul class="LIFE_Second">
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menwomenhome" id="con">HOME</a></li>        
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menwomenbeauty" id="con">Beauty</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menwomenpaper" id="con">Paper</a></li>   
                                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/menwomendigital" id="con">Digital</a></li> 
                                </ul></li>
                        </ul>
                    </li>
                </ul>
                <ul class="header_menu_SALE">
                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/reviewboard_list" id="high_con">유저리뷰</a>    
                        <ul class="SALE_Second"> 
                        </ul>
                    </li>
                </ul>
                <ul class="header_menu_Userrivw">
                    <li id="product_menu_list"><a href="${pageContext.request.contextPath }/SaleMain" id="high_con">SALE</a>    
                        <ul class="Userrivw_Second"> 
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    <div id="header_color"></div>
</body>
</html>