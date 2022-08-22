<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/mainbody.css" />
<title>GUSINSA_Main</title>
<header>
    
</header>
</head>

<style>
 #photo{
  position: absolute;
  top: 60%; left: 50%;
  transform: translate(-50%, -50%);

  max-width: 70%;
  max-height: 70%;
  border: 10px solid white;
} 
</style>	

<body>
<%@include file="../../../../resources/common/header.jsp" %>   
 <!--메인포스터-->
     <div class="slidesposter">
        <div class="slideshow-container">
        
            <div class="mySlides fade">
              <div class="numbertext">1 / 3</div>
              <img src="resources/headerimg/sale1.png" class="img_s"  >
              <div class="text">겨울 특가할인</div>
            </div>
            
            <div class="mySlides fade">
              <div class="numbertext">2 / 3</div>
              <img src="resources/headerimg/sale2.png" class="img_s" >
              <div class="text">봄이벤트</div>
            </div>
            
            <div class="mySlides fade">
              <div class="numbertext">3 / 3</div>
              <img src="resources/headerimg/sale3.png" class="img_s" >
              <div class="text">봄이벤트2</div>
            </div>
            
            </div>
            <br>
            
            <div style="text-align:center">
              <span class="dot"></span> 
              <span class="dot"></span> 
              <span class="dot"></span> 
            </div>
        </div>
            

            <script>
            var slideIndex = 0;
            showSlides();
            
            function showSlides() {
              var i;
              var slides = document.getElementsByClassName("mySlides");
              var dots = document.getElementsByClassName("dot");
              for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";  
              }
              slideIndex++;
              if (slideIndex > slides.length) {slideIndex = 1}    
              for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
              }
              slides[slideIndex-1].style.display = "block";  
              dots[slideIndex-1].className += " active";
              setTimeout(showSlides, 3000); // Change image every 2 seconds
            }
            </script> 
        <div class="Hot_keyword">
            <h1>HOT WEEKLY KEYWORD</h1>
           <ul class="keyword_ul">
           		<c:forEach items="${search }" var="dto">
                <li><a href="search?keyword=${dto.keyword }" style="color: black;">${dto.keyword }</a></li>
                </c:forEach>
           </ul>
        </div>


<!-- 최근 게시물-->

<div class="base_product" _sel="./(#3057)">
    <h1>RECENT PRODUCTS</h1>
    
    <c:forEach items="${recent }" var="dto">
    <!--옷 1-->
    <a href="BuyPage?p_code=${dto.p_code }" style="color: black;">
        <div class="bp_inner">
            <div class="img">
                <img class="don_lazy b-loaded" src="resources/upload/p_img/${dto.p_img }" style="width: 250px; height: 300px;">
            </div>
            
        </div>
        <div class="spec" is-color-for-list="true" is-size-for-list="true">
            
            <div class="name" ><span>${dto.p_name }</span></div>
            <div class="price_info" discount="true">
                <div class="price o_price"><span class="line">${dto.p_price }</span></div>
            </div>
        </div>
    </a>
	</c:forEach>
</div>

<!--사진 갤러리   -->
<div class="background_img">
    <div class="img_gallery_man">
        <div class="top1" _sel="./(#3057)">
            <!--옷 1-->
            <a href="#" style="color: black;">
                <div class="bp_inner">
                    <div class="img">
                        
                        <img class="top1_img" src="resources/upload/p_img/${mendto.p_img }">
                    </div>
                    
                </div>
                <div class="spec" is-color-for-list="true" is-size-for-list="true">
                    
                    <div class="name">${mendto.p_name }</div>
                    <div class="price_info" discount="true">
                        <div class="price o_price">${mendto.p_price }<span class="line"></span></div>
                    </div>
                </div>
            </a></div>
    <ul id="gallery">
        <li>
          <a href="resources/upload/p_img/${mendto.p_img2 }">
              <img src="resources/upload/p_img/${mendto.p_img2 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${mendto.p_img3 }">
              <img src="resources/upload/p_img/${mendto.p_img3 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${mendto.p_img4 }">
              <img src="resources/upload/p_img/${mendto.p_img4 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${mendto.p_img5 }">
              <img src="resources/upload/p_img/${mendto.p_img5 }" alt="Thumbnail">
          </a>
        </li>

      </ul>
       
      <div id="overlay">
          <img src="resources/headerimg/gimg_m2.png" alt="photo" id="photo">
      </div>

    </div>
   <!-- 베스트 여자-->
    <div class="img_gallery_wo">
        <div class="top1" _sel="./(#3057)">
            <!--옷 1-->
            <a href="#" style="color: black;">
                <div class="bp_inner">
                    <div class="img">
                        <img class="top1_img" src="resources/upload/p_img/${womendto.p_img }">
                    </div>
                    
                </div>
                <div class="spec" is-color-for-list="true" is-size-for-list="true">
                    
                    <div class="name">${womendto.p_name }</div>
                    <div class="price_info" discount="true">
                        <div class="price o_price">${womendto.p_price }<span class="line"></span></div>
                    </div></div></a></div>
    <ul id="gallery">
        <li>
          <a href="resources/upload/p_img/${womendto.p_img2 }">
              <img src="resources/upload/p_img/${womendto.p_img2 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${womendto.p_img3 }">
              <img src="resources/upload/p_img/${womendto.p_img3 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${womendto.p_img4 }">
              <img src="resources/upload/p_img/${womendto.p_img4 }" alt="Thumbnail">
          </a>
        </li>
        <li>
          <a href="resources/upload/p_img/${womendto.p_img5 }">
              <img src="resources/upload/p_img/${womendto.p_img5 }" alt="Thumbnail">
          </a>
        </li>

      </ul>
       
      <div id="overlay">
          <img src="resources/headerimg/PRO1.png" alt="photo" id="photo">
      </div>

    </div>

</div>
<!--인기있는 브랜드 -->
<div class="child-page-listing">

    <h1>Popular Brands</h1>
  
    <div class="grid-container">
  
      <article id="3685" class="location-listing">
  
        <a class="location-title" href="brand1">
            Youth           </a>
  
        <div class="location-image">
          <a href="brand1">
              <img width="300" height="169" src="resources/brandimg/brand_1.jpg" alt="san francisco">    </a>
  
        </div>
  
      </article>
  
      <article id="3688" class="location-listing">
  
        <a class="location-title" href="brand2">
           HOMEGROWN SVPPLY&CO           </a>
  
        <div class="location-image">
          <a href="brand2">
              <img width="300" height="169" src="resources/brandimg/brand_2.jpg" alt="london">  </a>
  
        </div>
  
      </article>
  
      <article id="3691" class="location-listing">
  
        <a class="location-title" href="brand3">
          NEITHERS      </a>
  
        <div class="location-image">
          <a href="brand3">
              <img width="300" height="169" src="resources/brandimg/brand_3.jpg" alt="new york">  </a>
  
        </div>
  
      </article>
  
      <article id="3694" class="location-listing">
  
        <a class="location-title" href="brand4">
          BROWNYARD           </a>
  
        <div class="location-image">
          <a href="brand4">
              <img width="300" height="169" src="resources/brandimg/brand_4.jpg" alt="cape town">  </a>
  
        </div>
  
      </article>
  
      <article id="3697" class="location-listing">
  
        <a class="location-title" href="brand5">
            FREITAG           </a>
  
        <div class="location-image">
          <a href="brand5">
              <img width="300" height="169" src="resources/brandimg/brand_5.jpg" alt="beijing">    </a>
  
        </div>
  
      </article>
  

  
    </div>
    <!-- end grid container -->
  
  </div>
    <!-- end grid container -->
  
  </div>

      <script>
        var overlay = document.getElementById("overlay");
        var photo = document.getElementById("photo");
        var Thumbnails = document.querySelectorAll("#gallery > li > a");
     
        for(var i=0; i<Thumbnails.length; i++){
            Thumbnails[i].addEventListener("click", function(e){
            e.preventDefault();
            photo.src = this.href;
            overlay.style.display = "block";
        });
     
        overlay.addEventListener("click", function(){
            this.removeAttribute("style");
        });
        }
    </script>

              



<%@include file="../../../../resources/common/footer.jsp" %>
</div>  

</body>
</html>