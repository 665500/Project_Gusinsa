package com.tech.project_shopping_mall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.project_shopping_mall.dao.BrandDao;
import com.tech.project_shopping_mall.dao.MainPageDao;
import com.tech.project_shopping_mall.dao.PDao;
import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.dto.MembersDto;
import com.tech.project_shopping_mall.dto.NorderinfoDto;
import com.tech.project_shopping_mall.dto.StorageDto;
import com.tech.project_shopping_mall.vopage.SearchVO;
import com.tech.project_shopping_mall.vopage.SearchVO_product;




@Controller
public class CategoryController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/men")
	public String Men(HttpServletRequest request,Model model,SearchVO_product searchVO) {
		System.out.println("넘어왔따");
		PDao dao=sqlSession.getMapper(PDao.class);
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");		
		String p_img=request.getParameter("p_img");
		

		String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
		System.out.println("pagggge1 :"+strPage);
		//null검사
		if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
			strPage="1";
		System.out.println("pagggge2 :"+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
		
		//조건에 따른 갯수 구하기
		/*
		 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
		 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
		 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
		 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
		 */
		
		int total=dao.selectBoardTotCount();
		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());

		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
		
		
		
		ArrayList<Infodto> dto=dao.men(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
        model.addAttribute("men",dto);
		return "men,women,new/men";
	}

	@RequestMapping("/Orderafterlogin")
	public String Orderafterlogin(Model model, HttpServletRequest request) {
		PDao dao=sqlSession.getMapper(PDao.class);
		HttpSession session = request.getSession();
		
		String mid = (String )session.getAttribute("mid");
		
		
		
		MembersDto dto= dao.Orderafterlogin(mid); //
		model.addAttribute("Orderafterlogin",dto);
		
		
		int pcode = Integer.parseInt(request.getParameter("p_code"));
		ArrayList<StorageDto> strgdto= dao.storage(pcode);
//		
		String p_size = request.getParameter("p_size");
		int sell_price = Integer.parseInt(request.getParameter("sell_price"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int sping =Integer.parseInt(request.getParameter("sping"));
		int sumsping =Integer.parseInt(request.getParameter("sumsping"));
//		System.out.println("pcode : "+pcode);
//		System.out.println("amount : "+amount);
//		System.out.println("sumprice : "+sell_price);
		
//		System.out.println("amount : "+amount);
		MainPageDao Mdao = sqlSession.getMapper(MainPageDao.class);
		Infodto indto = Mdao.search_prouct_detail(pcode);
		
		// pcode를 통해 해당 상품조회
		model.addAttribute("p_size",p_size);
		model.addAttribute("strgdto",strgdto);
		model.addAttribute("members",dto);
		model.addAttribute("sumsping",sumsping);
		model.addAttribute("sping",sping);
		model.addAttribute("indto",indto);
		model.addAttribute("amount",amount); // 상품의 수량 전달
		model.addAttribute("sumprice",sell_price);
		return "men,women,new/Orderafterlogin";
	}
	
	

	
	@RequestMapping("/Orderbeforelogin")
	public String Orderbeforelogin(HttpServletRequest request,Model model,SearchVO searchVO)  {
		
		/*
		 * HttpSession session = request.getSession();
		 * 
		 * 
		 * String mid = (String) session.getAttribute("mid");
		 * 
		 * System.out.println("mid : "+ mid);
		 */
		
		 // 비회원일때는 값처리 하고 리턴을 다른곳 비회원전용
		PDao dao=sqlSession.getMapper(PDao.class);
			int pcode = Integer.parseInt(request.getParameter("p_code"));
			//String pcode = request.getParameter("p_code");

			MainPageDao Mdao = sqlSession.getMapper(MainPageDao.class); 
	        Infodto indto = Mdao.search_prouct_detail(pcode);
			System.out.println("indto : " + indto);
			
			ArrayList<StorageDto> strgdto= dao.storage(pcode);
		
//			
			int sell_price = Integer.parseInt(request.getParameter("sell_price"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int sping =Integer.parseInt(request.getParameter("sping"));
			int sumsping =Integer.parseInt(request.getParameter("sumsping"));
			String p_name = request.getParameter("p_name");
			String n_ordername=request.getParameter("n_ordername");
			String n_number1=request.getParameter("n_number1");
			String n_email=request.getParameter("n_email");
			String n_name=request.getParameter("n_name");
			String n_addr1=request.getParameter("n_addr1");
			String n_addr2=request.getParameter("n_addr2");
			String n_addr3=request.getParameter("n_addr3");
			String n_addr4=request.getParameter("n_addr4");
			String n_number2=request.getParameter("n_number2");
			String n_request=request.getParameter("n_request");
			String p_size = request.getParameter("p_size");
			
//			System.out.println("pcode : "+pcode);
//			System.out.println("amount : "+amount);
//			System.out.println("sumprice : "+sell_price);
			
//			System.out.println("amount : "+amount);
			
			
			
			// pcode를 통해 해당 상품조회
			model.addAttribute("indto",indto);
			model.addAttribute("sping",sping);
			model.addAttribute("strgdto",strgdto);
			model.addAttribute("sumsping",sumsping);
			model.addAttribute("amount",amount); // 상품의 수량 전달
			model.addAttribute("sumprice",sell_price);
			model.addAttribute("n_ordername",n_ordername);
			model.addAttribute("n_email",n_email);
			model.addAttribute("n_name",n_name);
			model.addAttribute("n_addr1",n_addr1); // 상품의 수량 전달
			model.addAttribute("n_addr2",n_addr2);
			model.addAttribute("n_addr3",n_addr3);
			model.addAttribute("n_addr4",n_addr4);
			model.addAttribute("n_number1",n_number1);
			model.addAttribute("n_number2",n_number2);
			model.addAttribute("n_request",n_request);
			model.addAttribute("p_name", p_name);
			model.addAttribute("p_code", pcode);
			model.addAttribute("p_size",p_size);
		
			 
			
			System.out.println("n_request"+n_request);
		
		return "men,women,new/Orderbeforelogin";
		
	}

		
	  @RequestMapping("Norderwrite")
	  public String Norderwrite(HttpServletRequest request,Model model,SearchVO searchVO) {
		     System.out.println("지나가기");
			PDao dao=sqlSession.getMapper(PDao.class);
			System.out.println("지나가기=================================================");
			String n_ordername=request.getParameter("n_ordername");
			String n_number1=request.getParameter("n_number1");
			String n_email=request.getParameter("n_email");
			String n_name=request.getParameter("n_name");
			String n_addr1=request.getParameter("n_addr1");
			String n_addr2=request.getParameter("n_addr2");
			String n_addr3=request.getParameter("n_addr3");
			String n_addr4=request.getParameter("n_addr4");
			String n_number2=request.getParameter("n_number2");
			String n_request=request.getParameter("n_request");
			String p_name = request.getParameter("p_name");
			int sprice = Integer.parseInt(request.getParameter("sumprice"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int sumsping = Integer.parseInt(request.getParameter("sumsping"));
			
			/*
			 * int sprice = Integer.parseInt(request.getParameter("sumprice")); int amount =
			 * Integer.parseInt(request.getParameter("amount")); int sumsping =
			 * Integer.parseInt(request.getParameter("sumsping")); int p_code =
			 * Integer.parseInt(request.getParameter("p_code"));
			 */
			/* int p_code = Integer.parseInt(request.getParameter("p_code")); */
			/*
			 * int pcode = Integer.parseInt(request.getParameter("p_code"));
			 */
			
			
			System.out.println("n_ordername : "+n_ordername );
			System.out.println("n_number1      제발 "+n_number1);
			System.out.println("n_email : "+n_email);
			System.out.println("n_name : "+n_name);
			System.out.println("n_addr1 : "+n_addr1);
			System.out.println("n_addr2"+n_addr2);
			System.out.println("n_addr3"+n_addr3);
			System.out.println("n_addr4"+n_addr4);
			System.out.println("n_number2"+n_number2);
			System.out.println("n_request"+n_request);
			System.out.println("p_name : "+p_name);
//			System.out.println("amount : "+amount);
			
			
		
			// pcode를 통해 해당 상품조회
			/* model.addAttribute("p_code", p_code); */
			model.addAttribute("n_ordername",n_ordername);
			model.addAttribute("n_email",n_email);
			model.addAttribute("n_name",n_name);
			model.addAttribute("n_addr1",n_addr1); // 상품의 수량 전달
			model.addAttribute("n_addr2",n_addr2);
			model.addAttribute("n_addr3",n_addr3);
			model.addAttribute("n_addr4",n_addr4);
			model.addAttribute("n_number1",n_number1);
			model.addAttribute("n_number2",n_number2);
			model.addAttribute("n_request",n_request);
			model.addAttribute("p_name", p_name);
			model.addAttribute("sumsping", sumsping);
			model.addAttribute("sprice", sprice);
			model.addAttribute("amount", amount);
	  return "Pay/Nomemberpay"; 
	  
	  }
//	  }
//	  @RequestMapping("/Orderwrite")
//	  public String Orderwrite(@ModelAttribute NorderinfoDto dto) {
//		  System.out.println("지나가기2");
//		  PDao dao=sqlSession.getMapper(PDao.class);
//		  
//		  dao.Norderwrite(dto.getN_name(), dto.getN_number(), dto.getN_email(), dto.getN_addr(), dto.getN_request());
//		  /*
//		   * dao.Norderwrite(dto);
//		   */	  
//		  System.out.println("ddd "+dto);
//		  
//		  return "redirect:/pay"; 
//	  }
	 
	@RequestMapping("/agreement")
	public String agreement(HttpServletRequest request,Model model,SearchVO searchVO) {
		PDao dao=sqlSession.getMapper(PDao.class);
		
		return "men,women,new/agreement";
	}
	@RequestMapping("/agreement2")
	public String agreement2(HttpServletRequest request,Model model,SearchVO searchVO) {
		PDao dao=sqlSession.getMapper(PDao.class);
		
		return "men,women,new/agreement2";
	}
	
	@RequestMapping("/women")
	public String women(HttpServletRequest request,Model model,SearchVO_product searchVO) {
		System.out.println("넘어왔따");
		PDao dao=sqlSession.getMapper(PDao.class);
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");		
		String p_img=request.getParameter("p_img");
		
		String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
		System.out.println("pagggge1 :"+strPage);
		//null검사
		if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
			strPage="1";
		System.out.println("pagggge2 :"+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
		
		//조건에 따른 갯수 구하기
		/*
		 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
		 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
		 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
		 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
		 */
		
		int total=dao.selectBoardTotCount();
		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
		
		
		
		ArrayList<Infodto> dto=dao.women(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		model.addAttribute("women",dto);
		return "men,women,new/women";
	}
	@RequestMapping("/menouter")
	public String menouter(HttpServletRequest request,Model model,SearchVO_product searchVO) {
		System.out.println("넘어왔따");
		PDao dao=sqlSession.getMapper(PDao.class);
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");		
		String p_img=request.getParameter("p_img");
		
		String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
		System.out.println("pagggge1 :"+strPage);
		//null검사
		if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
			strPage="1";
		System.out.println("pagggge2 :"+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
		
		//조건에 따른 갯수 구하기
		/*
		 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
		 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
		 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
		 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
		 */
		
		int total=dao.selectBoardTotCount();
		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
		
		
		
		ArrayList<Infodto> dto=dao.menouter(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		model.addAttribute("menouter",dto);
		return "men,women,new/menouter";
	}
	@RequestMapping("/mentop")
	public String mentop(HttpServletRequest request,Model model,SearchVO_product searchVO) {
		System.out.println("넘어왔따");
		PDao dao=sqlSession.getMapper(PDao.class);
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");		
		String p_img=request.getParameter("p_img");
		
		String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
		System.out.println("pagggge1 :"+strPage);
		//null검사
		if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
			strPage="1";
		System.out.println("pagggge2 :"+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
		
		//조건에 따른 갯수 구하기
		/*
		 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
		 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
		 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
		 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
		 */
		
		int total=dao.selectBoardTotCount();
		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
		
		
		
		ArrayList<Infodto> dto=dao.mentop(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		model.addAttribute("mentop",dto);
		return "men,women,new/mentop";
	}
	@RequestMapping("/menbottom")
	public String menbottom(HttpServletRequest request,Model model,SearchVO_product searchVO) {
		System.out.println("넘어왔따");
		PDao dao=sqlSession.getMapper(PDao.class);
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");		
		String p_img=request.getParameter("p_img");
		
		String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
		System.out.println("pagggge1 :"+strPage);
		//null검사
		if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
			strPage="1";
		System.out.println("pagggge2 :"+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
		
		//조건에 따른 갯수 구하기
		/*
		 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
		 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
		 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
		 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
		 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
		 */
		
		int total=dao.selectBoardTotCount();
		searchVO.pageCalculate(total);
		
		//계산된 내용 출력
		System.out.println("totRow : "+total);
		System.out.println("clickPage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTot : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
		
		
		
		ArrayList<Infodto> dto=dao.menbottom(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		model.addAttribute("menbottom",dto);
		return "men,women,new/menbottom";
	}
		@RequestMapping("/menacc")
		public String menwomenacc(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomenacc(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomenacc",dto);
			return "men,women,new/menacc";
	}
		@RequestMapping("/menlife")
		public String menwomenlife(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomenlife(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomenlife",dto);
			return "men,women,new/menlife";
		}
		@RequestMapping("/menjacket")
		public String menjacket(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menjacket(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menjacket",dto);
			return "men,women,new/menjacket";
		}
		@RequestMapping("/mencoat")
		public String mencoat(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.mencoat(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("mencoat",dto);
			return "men,women,new/mencoat";
		}
		@RequestMapping("/menblazer")
		public String menblazer(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menblazer(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menblazer",dto);
			return "men,women,new/menblazer";
		}
		@RequestMapping("/menjumper")
		public String menjumper(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menjumper(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menjumper",dto);
			return "men,women,new/menjumper";
		}
		@RequestMapping("/menwomenbeauty")
		public String menwomenbeauty(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomenbeauty(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomenbeauty",dto);
			return "men,women,new/menwomenbeauty";
		}
		@RequestMapping("/menwomendigital")
		public String menwomendigital(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomendigital(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomendigital",dto);
			return "men,women,new/menwomendigital";
		}
		@RequestMapping("/menwomenhome")
		public String menwomenhome(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomenhome(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomenhome",dto);
			return "men,women,new/menwomenhome";
		}
		@RequestMapping("/menwomenpaper")
		public String menwomenpaper(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menwomenpaper(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menwomenpaper",dto);
			return "men,women,new/menwomenpaper";
		}
		@RequestMapping("/menshirtslong")
		public String menshirtslong(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menteeshort(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menshirtslong",dto);
			return "men,women,new/menshirtslong";
		}
		@RequestMapping("/menshirtshort")
		public String menshirtshort(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menshirtshort(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menshirtshort",dto);
			return "men,women,new/menshirtshort";
		}
		
		@RequestMapping("/menknit")
		public String menknit(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menknit(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menknit",dto);
			return "men,women,new/menknit";
		}
		@RequestMapping("/menvest")
		public String menvest(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menvest(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menvest",dto);
			return "men,women,new/menvest";
		}
		@RequestMapping("/menhoodie")
		public String menhoodie(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menhoodie(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menhoodie",dto);
			return "men,women,new/menhoodie";
		}
		@RequestMapping("/mensweatshirt")
		public String mensweatshirt(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.mensweatshirt(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("mensweatshirt",dto);
			return "men,women,new/mensweatshirt";
		}
		@RequestMapping("/mensleeveless")
		public String mensleeveless(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.mensleeveless(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("mensleeveless",dto);
			return "men,women,new/mensleeveless";
		}
		@RequestMapping("/menteelong")
		public String menteelong(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menteelong(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menteelong",dto);
			return "men,women,new/menteelong";
		}
		@RequestMapping("/menteeshort")
		public String menteeshort(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.menteeshort(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menteeshort",dto);
			return "men,women,new/menteeshort";
		}
		
		//women 
		
		
		
		
		
		
		
		
		@RequestMapping("/womenshirtslong")
		public String womenshirtslong(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenshirtslong(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("menshirtslong",dto);
			return "men,women,new/womenshirtslong";
		}
		@RequestMapping("/womenshirtshort")
		public String womenshirtshort(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenshirtshort(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenshirtshort",dto);
			return "men,women,new/womenshirtshort";
		}
		
		@RequestMapping("/womenknit")
		public String womenknit(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenknit(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenknit",dto);
			return "men,women,new/womenknit";
		}
		@RequestMapping("/womenvest")
		public String womenvest(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenvest(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenvest",dto);
			return "men,women,new/womenvest";
		}
		@RequestMapping("/womenhoodie")
		public String womenhoodie(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenhoodie(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenhoodie",dto);
			return "men,women,new/womenhoodie";
		}
		@RequestMapping("/womensweatshirt")
		public String womensweatshirt(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womensweatshirt(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womensweatshirt",dto);
			return "men,women,new/womensweatshirt";
		}
		@RequestMapping("/womensleeveless")
		public String womensleeveless(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womensleeveless(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womensleeveless",dto);
			return "men,women,new/womensleeveless";
		}
		@RequestMapping("/womenteelong")
		public String womenteelong(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenteelong(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenteelong",dto);
			return "men,women,new/womenteelong";
		}
		@RequestMapping("/womenteeshort")
		public String womenteeshort(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenteeshort(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenteeshort",dto);
			return "men,women,new/womenteeshort";
		}
		@RequestMapping("/womenouter")
		public String womenouter(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenouter(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenouter",dto);
			return "men,women,new/womenouter";
		}
		@RequestMapping("/womenjacket")
		public String womenjacket(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenjacket(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenjacket",dto);
			return "men,women,new/womenjacket";
		}
		@RequestMapping("/womencoat")
		public String womencoat(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womencoat(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womencoat",dto);
			return "men,women,new/womencoat";
		}
		@RequestMapping("/womenblazer")
		public String womenblazer(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenblazer(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenblazer",dto);
			return "men,women,new/womenblazer";
		}
		@RequestMapping("/womenjumper")
		public String womenjumper(HttpServletRequest request,Model model,SearchVO_product searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//		int total=dao.selectBoardTotCount();
//		System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDㄴto> list=dao.list(rowStart,rowEnd);*/
//		ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.womenjumper(rowStart, rowEnd,"1");
//		model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("womenjumper",dto);
			return "men,women,new/womenjumper";
		}
	
		
		@RequestMapping("/newarrival")
		public String newarrival(HttpServletRequest request,Model model,SearchVO searchVO) {
			System.out.println("넘어왔따");
			PDao dao=sqlSession.getMapper(PDao.class);
			String p_name=request.getParameter("p_name");
			String p_price=request.getParameter("p_price");		
			String p_img=request.getParameter("p_img");
			
			
			String strPage=request.getParameter("page"); //HttpServletRequest request, 가져오기
			System.out.println("pagggge1 :"+strPage);
			//null검사
			if(strPage==null)//처음 리스트에서 list페이지로 넘어갈 때, null임.
				strPage="1";
			System.out.println("pagggge2 :"+strPage);
			int page=Integer.parseInt(strPage);
			searchVO.setPage(page);
			
			//total 글의 갯수 구하기
//			int total=dao.selectBoardTotCount();
//			System.out.println("totalrow : "+total);
			
			//조건에 따른 갯수 구하기
			/*
			 * int total=0; if (p_name.equals("btitle") && bcontent.equals("")) {
			 * total=dao.selectBoardTotCount1(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("bcontent")) { total=dao.selectBoardTotCount2(searchKeyword);
			 * }else if (btitle.equals("btitle") && bcontent.equals("bcontent")) {
			 * total=dao.selectBoardTotCount3(searchKeyword); }else if (btitle.equals("") &&
			 * bcontent.equals("")) { total=dao.selectBoardTotCount4(searchKeyword); }
			 */
			
			int total=dao.selectBoardTotCount();
			searchVO.pageCalculate(total);
			
			//계산된 내용 출력
			System.out.println("totRow : "+total);
			System.out.println("clickPage : "+strPage);
			System.out.println("pageStart : "+searchVO.getPageStart());
			System.out.println("pageEnd : "+searchVO.getPageEnd());
			System.out.println("pageTot : "+searchVO.getTotPage());
			System.out.println("rowStart : "+searchVO.getRowStart());
			System.out.println("rowEnd : "+searchVO.getRowEnd());
			
			int rowStart=searchVO.getRowStart();
			int rowEnd=searchVO.getRowEnd();
			
			/*ArrayList<BoardDto> list=dao.list(rowStart,rowEnd);*/
//			ArrayList<BoardDto> list=null;
			
			
			
			ArrayList<Infodto> dto=dao.newarrival(rowStart, rowEnd,"1");
//			model.addAttribute("list",list);
			model.addAttribute("totRowcnt",total);
			model.addAttribute("searchVO",searchVO);
			model.addAttribute("newarrival",dto);
			return "men,women,new/newarrival";
		}
}
