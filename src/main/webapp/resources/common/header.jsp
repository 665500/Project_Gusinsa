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
            height: 236px;
            position: fixed;
            background: white;
        }
    .header_left{ 
    margin-top: 10px;
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
        background-color: #a8a8a8; border: none; /*메뉴 하위메뉴 배경색상 지정*/
    }
    

    #con:link{ color : black}
    #con:hover{font-weight: bold; text-decoration: underline; }
    input:-ms-input-placeholder{color:#a8a8a8;}
    input::-webkit-input-placeholder{color:#a8a8a8;}
    input::-moz-placeholder{color:#a8a8a8;}
    #product_menu_list{font-size: 25px;display: inline-block; left: 16.5%;  } /* 메뉴 카테고리 좌우 이동*/
    .first_hearder{ height: 30px; width: 360px; border: 1px solid #0b0c0e; background: #ffffff; margin-top: 5px;}
    #fisrst_search{ font-size: 16px; width: 300px; height: 25px; padding: 0px; border: 0px; outline: none; float: left; }
    #button_search{ width: 40px; height: 99%; border: 0px; background: #020202; outline: none; float: right; color: #ffffff}
    #header_gita li,.first_hearder{list-style-type: none;float: right; margin-right: 30px;}  /*로그인 장바구니 목록 설정*/
    #sidelist{ margin-right: 0;}
    .header_img{ width: 40px; height: 2px;   margin-bottom: 3px;}
    #header_right_img{ 
        float: left; width: 180px; height: 40px; margin-left: 20px;
    }
    #hearder_color{ background: #020202; background-color: #020202; width: 100%;height: 50px;margin-bottom: 30px;float: right;text-align: center;}
    /* body{width: 0 auto;text-align: center;} */
    /* ----------------------------------------- 헤더메뉴 ----------------------------------------- */
    nav{  width: 100%; height: 220px;
    background-color: rgb(255, 255, 255);}
    /* ----------------------------------------- 메뉴 MEN ----------------------------------------- */
    .header_menu_Man li{ float: left; padding: 0 50px; line-height: 36px; position: relative; }
    .Man_Second{ margin-bottom: 20px; position:absolute;  top:100%; left: 0%; right: 200%; display: none;   /* 중분류 될 리스트를 숨김 */}
    .Man_Second li{width: 250px; height: 30px; padding-bottom: 7px;  } /* 크기 및 이동조절*/
    .header_menu_Man:hover .Man_Second{display: block;}
    /* ----------------------------------------- MEN 끝 및 브랜드 시작 ----------------------------------------- */
    .header_menu_BRAND li{ float: left; padding: 0 50px; line-height: 36px; position: relative;}
    .BRAND_Second{ margin-bottom:20px; position: absolute; top:100%; left: 0; right: 400%; display: none; /* 중분류 될 리스트를 숨김 */}
    .BRAND_Second li{ width: 250px; height: 30px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .header_menu_BRAND:hover .BRAND_Second{ display: block; }
    /* ----------------------------------------- 브랜드 three ----------------------------------------- */
    .BRAND_Three{  margin-bottom:10px; position:absolute; bottom: 100%; top: -1px; left: 69.5%;  /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
    .BRAND_Three li{ width: 300px; height: 30px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .Restock:hover .BRAND_Three{ display: block; }
     /*------------------------------------ 안헷갈리게 ViewallBrands_Three------------------------------------------------------------------------------*/
     .ViewallBrands_Three{ margin-bottom:10px; position:absolute; top: 0px;  left: 69.5%; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */}
    .ViewallBrands_Three li{ width: 300px; height: 28px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .ViewallBrands:hover .ViewallBrands_Three{ display: block; }
    /* ----------------------------------------- 브랜드 끝 ----------------------------------------- */
    /*------------------------------------ 안헷갈리게 MAN_Three 중분류들 ------------------------------------------------------------------------------*/
    .OUTER_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: 0px;  left: 69.5%;  /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
    .OUTER_Second li{ width: 300px; height: 30px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .OUTER:hover .OUTER_Second{ display: block; }
    .TOP_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: -1px;  left: 69.5%; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
    .TOP_Second li{ width: 300px; height: 30px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .TOP:hover .TOP_Second{ display: block; }
    
    .LIFE_Second{  margin-bottom:10px; position:absolute; bottom: 100%; top: -1px;  left: 69.5%; /* right: 500px; */ display: none; /* 중분류 될 리스트를 숨김 */ }
    .LIFE_Second li{ width: 300px; height: 30px; padding-bottom: 7px; }  /*중분류 리스트 사이즈*/
    .LIFE:hover .LIFE_Second{ display: block; }
    /*------------------------------------ 안헷갈리게 MAN_Three 끝 및 WOMEN_SECOND 시작------------------------------------------------------------------------------*/
    .header_menu_WOMEN #product_menu_list{  float: left;  padding: 0 50px;  line-height: 36px; position: relative; }
    .WOMEN_Second{ margin-bottom: 10px; position: absolute; top:95%; left: 0; right: 500%; display: none;    /* 중분류 될 리스트를 숨김 */ }
    .WOMEN_Second #product_menu_list{ width: 300px; height: 30px; padding-bottom: 7px;  left: 18.6%; } /*우먼 2단계리스트 크기 좌우 조절*/
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

/* .every{
    width: 100%;
    margin: 0 auto; 
    text-align: center;
} */
    </style>
       <header>
        <div class="header_left"><a href=#><img src="resources/headerimg/GUSINSA.png" alt="" id="header_right_img" style="width: 120px; height: 50px;"></a></div>
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
                        <li id="sidebar_li"><a href="#" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>주문/배송</b></a></li>
                        <li id="sidebar_li"><a href="#" id="sidebar_li_a" style="color: rgb(255, 255, 255);" ><b>매장안내</b></a></li>
                        <li id="sidebar_li"><a href="#" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>고객센터</b></a></li>
                        <li id="sidebar_li"><a href="#" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>로그인</b></a></li>
                        <li id="sidebar_li"><a href="#" id="sidebar_li_a" style="color: rgb(255, 255, 255);"><b>회원가입</b></a></li>
                    </ul>
                </div></li>
                <!-- /project_shopping_malltest/src/main/webapp/resources/headerimg -->
            <!-- <li><a href=#><img src="../headerimg/목록.png" style="margin-right: 80px;" alt="목록" class="header_img"></a></li> -->
            <li><a href=#><img src="resources/headerimg/장바구니.png" style="height: 47px;" alt="장바구니" class="header_img"></a></li>
            <li><a href="resources/joinform"><img src="resources/headerimg/login.png" style="height: 47px;" alt="로그인" class="header_img"></li></a>
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
                <li id="product_menu_list"><a href=# id="high_con">NEW ARRIVAL</a>
    
                </li>
            </ul>
            <ul class="header_menu_BRAND">
                <li id="product_menu_list"><a href="" id="high_con">BRAND</a>
                    <ul class="BRAND_Second">
                        <li class ="ViewallBrands" id="product_menu_list"><a href="" id="con" >View all Brands</a>
                            <ul class="ViewallBrands_Three">
                                <li id="product_menu_list"><a href="" id="con">LLUD Exclusive</a></li>        
                                <li id="product_menu_list"><a href="" id="con">AFTERPRAY</a></li>   
                                <li id="product_menu_list"><a href="" id="con">MPQ</a></li>   
                                <li id="product_menu_list"><a href="" id="con">SUJIPMIHAK</a></li>
                                <li id="product_menu_list"><a href="" id="con">WHITEBOXHGALLERY</a></li>
                            </ul>
                            </li>
    
                        <li class="Restock"  id="product_menu_list"><a href="" id="con">Restock</a>
                            <ul class="BRAND_Three">
                                <li id="product_menu_list"><a href="" id="con">CURATED PARADE</a></li>        
                                <li id="product_menu_list"><a href="" id="con">SANSAN GEAR</a></li>   
                                <li id="product_menu_list"><a href="" id="con">Hatchingroom</a></li>   
                                <li id="product_menu_list"><a href="" id="con">Saturday of us</a></li> 
                            </ul>
                            </li>
    
                       
                    </ul>
                </li>
            </ul>
                <ul class="header_menu_Man">
                    <li id="product_menu_list"><a href=# id="high_con">MEN</a>
                        <ul class="Man_Second">
                            <li class="OUTER"  id="product_menu_list"><a href=# id="con">OUTER</a>
                                <ul class="OUTER_Second">
                                    <li id="product_menu_list"><a href="" id="con">JACKET</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Coat</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Jumper</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Biazer</a></li> 
                                </ul></li>
                            <li class="TOP" id="product_menu_list"><a href=# id="con">TOP</a>
                                <ul class="TOP_Second">
                                    <li id="product_menu_list"><a href="" id="con">Shirts(LS)</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Shirts(1/2)</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Tee(LS)</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Tee(1/2)</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Sleeveless</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Knit</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Vest</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Hoodie</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Sweatshirts</a></li> 
                                </ul></li>
                            <li id="product_menu_list"><a href=# id="con">BOTTOM</a></li>
                            <li id="product_menu_list"><a href=# id="con">ACCESSORIES</a></li>
                            <li class="LIFE" id="product_menu_list"><a href=# id="con">LIFE</a>
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
                    <li id="product_menu_list"><a href=# id="high_con">WOMEN</a>
                        <ul class="WOMEN_Second">
                            <li class="OUTER" id="product_menu_list"><a href=# id="con">OUTER</a>
                                <ul class="OUTER_Second">
                                    <li id="product_menu_list"><a href="" id="con">JACKET</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Coat</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Jumper</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Biazer</a></li> 
                                </ul></li>
                            <li class="TOP" id="product_menu_list"><a href=# id="con">TOP</a>
                                <ul class="TOP_Second">
                                    <li id="product_menu_list"><a href="" id="con">Shirts(LS)</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Shirts(1/2)</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Tee(LS)</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Tee(1/2)</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Sleeveless</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Knit</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Vest</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Hoodie</a></li> 
                                    <li id="product_menu_list"><a href="" id="con">Sweatshirts</a></li> 
                                </ul></li>
                            <li id="product_menu_list"><a href=# id="con">BOTTOM</a></li>
                            <li id="product_menu_list"><a href=# id="con">ACCESSORIES</a></li>
                            <li class="LIFE"  id="product_menu_list"><a href=# id="con">LIFE</a>
                                <ul class="LIFE_Second">
                                    <li id="product_menu_list"><a href="" id="con">HOME</a></li>        
                                    <li id="product_menu_list"><a href="" id="con">Beauty</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Paper</a></li>   
                                    <li id="product_menu_list"><a href="" id="con">Digital</a></li> 
                                </ul></li>
                        </ul>
                    </li>
                </ul>
                <ul class="header_menu_SALE">
                    <li id="product_menu_list"><a href=# id="high_con">유저리뷰</a>    
                        <ul class="SALE_Second"> 
                        </ul>
                    </li>
                </ul>
                <ul class="header_menu_Userrivw">
                    <li id="product_menu_list"><a href=# id="high_con">SALE</a>    
                        <ul class="Userrivw_Second"> 
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
<body>

</body>
</html>