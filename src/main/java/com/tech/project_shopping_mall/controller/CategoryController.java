package com.tech.project_shopping_mall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.project_shopping_mall.dao.PDao;
import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.vopage.SearchVO;




@Controller
public class CategoryController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/men")
	public String Men(HttpServletRequest request,Model model,SearchVO searchVO) {
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
	
	
}