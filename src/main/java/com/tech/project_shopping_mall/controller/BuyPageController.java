package com.tech.project_shopping_mall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.project_shopping_mall.dao.MainPageDao;
import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.dto.ReviewDto;
import com.tech.project_shopping_mall.dto.StorageDto;
import com.tech.project_shopping_mall.vopage.reSearchVO;

@Controller
public class BuyPageController {


   @Autowired
   private SqlSession sqlSession;
 
   @RequestMapping("BuyPage")
   public String BuyPage(HttpServletRequest request, reSearchVO researchVO,Model model) {
      
      MainPageDao dao = sqlSession.getMapper(MainPageDao.class);
      int pcode = Integer.parseInt(request.getParameter("p_code"));
      //String pcode = request.getParameter("p_code");

      ArrayList<StorageDto>  strgdto= dao.storage(pcode);

        Infodto indto = dao.search_prouct_detail(pcode);
      System.out.println("indto : " + indto);

      model.addAttribute("indto", indto);
      
      model.addAttribute("strgdto", strgdto);
      // 여기서부터 리뷰 

         String r_title = "";
         String r_contents = "";

         String[] retitle = request.getParameterValues("searchType");
         if (retitle != null) {
            for (int i = 0; i < retitle.length; i++) {
               System.out.println("retitle : " + retitle[i]);
            }
         }
         /* 경우의 수 4가지, 변수에 저장 */
         if (retitle != null) {
            for (String val : retitle) {
               if (val.contentEquals("r_title")) {
                  model.addAttribute("r_title", "true");// 검색체크유지
                  r_title = "r_title";
               } else if (val.contentEquals("r_contents")) {
                  model.addAttribute("r_contents", "true");// 검색체크유지
                  r_contents = "r_contents";
               }
            }
         }

         // 검색결과 유지하기
         String rt = request.getParameter("r_title");
         String rc = request.getParameter("r_contents");

         if (rt != null) {
            if (rt.equals("r_title")) {
               r_title = rt;
               model.addAttribute("r_title", "true");
            }
         }
         if (rc != null) {
            if (rc.equals("r_contents")) {
               r_contents = rc;
               model.addAttribute("r_contents", "true");
            }
         }

         /* paging */
         String strPage = request.getParameter("page"); // HttpServletRequest request, 가져오기
         System.out.println("pagggge1 :" + strPage);
         // null검사
         if (strPage == null)// 처음 리스트에서 list페이지로 넘어갈 때, null임.
            strPage = "1";
         System.out.println("pagggge2 :" + strPage);
         int page = Integer.parseInt(strPage);
         researchVO.setPage(page);

         // total 글의 갯수 구하기
         int total=dao.selectReviewTotCount();
         System.out.println("totalrow : "+total);

         researchVO.pageCalculate(total);

         // 계산된 내용 출력
         System.out.println("totRow : " + total);
         System.out.println("clickPage : " + strPage);
         System.out.println("pageStart : " + researchVO.getPageStart());
         System.out.println("pageEnd : " + researchVO.getPageEnd());
         System.out.println("pageTot : " + researchVO.getTotPage());
         System.out.println("rowStart : " + researchVO.getRowStart());
         System.out.println("rowEnd : " + researchVO.getRowEnd());

         int rowStart = researchVO.getRowStart();
         int rowEnd = researchVO.getRowEnd();

         ArrayList<ReviewDto> review_list=dao.review_list(rowStart,rowEnd);
         ArrayList<ReviewDto> product_review=dao.product_review(pcode,rowStart,rowEnd);

         model.addAttribute("review_list",product_review);
         model.addAttribute("totRowcnt", total);
         model.addAttribute("researchVO", researchVO);
         
         HttpSession session=request.getSession();
          String mid = (String)session.getAttribute("mid");
          double avg = dao.staravg(pcode); // pcode의 속한 점수 평균이 avg에 저장
          //float avg = dao.staravg(pcode); // pcode의 속한 점수 평균이 avg에 저장
         model.addAttribute("p_code", pcode);
         /*
          * int ocode = Integer.parseInt(request.getParameter("o_code"));
          * model.addAttribute("o_code", ocode);
          */
         model.addAttribute("mid", mid);
         model.addAttribute("avg",avg);
         System.out.println("mid : " + mid);
      
      
      return "/Sale/SaleBuying";
      
   }
   
   @RequestMapping("SaleBuyPage")
   public String SaleBuyPage(HttpServletRequest request, Model model) {
      
      MainPageDao dao = sqlSession.getMapper(MainPageDao.class);
      int pcode = Integer.parseInt(request.getParameter("p_code"));
      //String pcode = request.getParameter("p_code");

        Infodto indto = dao.search_prouct_detail(pcode);
      System.out.println("indto : " + indto);

      model.addAttribute("indto", indto);
      return "/Sale/SuperSale";
      
   }
}

  
   
   
   /*
    * @RequestMapping("BuyPage") public String BuyPagae(HttpServletRequest request,
    * Model model) {
    * 
    * HttpSession session = request.getSession(); String[] mid = (String[]
    * )session.getAttribute("mid");
    * 
    * if (mid.length < 0) { // 비회원일때는 값처리 하고 리턴을 다른곳 비회원전용 MainPageDao dao =
    * sqlSession.getMapper(MainPageDao.class); int pcode =
    * Integer.parseInt(request.getParameter("p_code")); //String pcode =
    * request.getParameter("p_code");
    * 
    * Infodto indto = dao.search_prouct_detail(pcode);
    * System.out.println("indto : " + indto);
    * 
    * model.addAttribute("indto", indto); return "/Sale/SaleBuying";
    * 
    * 
    * }
    * 
    * 
    * //여기는 회원전용
    * 
    * HttpSession session = request.getSession(); String mid = (String
    * )session.getAttribute("mid");
    * 
    * 
    * 
    * MainPageDao dao = sqlSession.getMapper(MainPageDao.class); int pcode =
    * Integer.parseInt(request.getParameter("p_code")); //String pcode =
    * request.getParameter("p_code");
    * 
    * Infodto indto = dao.search_prouct_detail(pcode);
    * System.out.println("indto : " + indto);
    * 
    * model.addAttribute("indto", indto); return "/Sale/SaleBuying";
    * 
    * 
    * 
    * }
    */
    