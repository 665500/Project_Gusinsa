package com.tech.project_shopping_mall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.project_shopping_mall.dao.CSDao;
import com.tech.project_shopping_mall.dto.FaqDto;
import com.tech.project_shopping_mall.dto.NoticeDto;
import com.tech.project_shopping_mall.vopage.SearchVO_CS;

@Controller
public class CSController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/csmain")
	public String CSmain(Model model) {
		System.out.println("======CSMAIN=======");
		
		CSDao dao=sqlSession.getMapper(CSDao.class);
		
		ArrayList<NoticeDto> noticeboard2=dao.noticeboard2(0, null, null, null);
		model.addAttribute("noticeboard2",noticeboard2);
		
		ArrayList<FaqDto> faqboard2=dao.faqboard2();
		model.addAttribute("faqboard2",faqboard2);
		
		return "/CS/csmain";
		
	}
	
	@RequestMapping("/noticeboard")
	public String notice(HttpServletRequest request,SearchVO_CS searchVO,Model model) {
		System.out.println("======NoticeBoard=======");
		
		HttpSession session = request.getSession();
		String mid=(String)session.getAttribute("mid");
		String mgrade=(String)session.getAttribute("mgrade");

		
		CSDao dao=sqlSession.getMapper(CSDao.class);
	
		String ntitle="";
		String ncontent="";
		
		String[] brdtitle=request.getParameterValues("searchType");
		if(brdtitle!=null) {
			for(int i=0;i<brdtitle.length;i++) {
				System.out.println("brdtitle : "+brdtitle[i]);
			}
		}
		
		if (brdtitle!=null) {
			for (String val : brdtitle) {
				if (val.equals("ntitle")) {
					model.addAttribute("ntitle","true");//검색체크유지
					ntitle="ntitle";
				}else if (val.equals("ncontent")) {
					model.addAttribute("ncontent","true");//검색체크유지
					ncontent="ncontent";
				}
			}
		}	
		
		String bt=request.getParameter("ntitle");
		String bc=request.getParameter("ncontent");
		
		if(bt!=null) {
			if (bt.equals("ntitle")) {
				ntitle=bt;
				model.addAttribute("ntitle","true");//검색체크유지
			}
		}
		if(bc!=null) {
			if (bc.equals("ncontent")) {
				ncontent=bc;
				model.addAttribute("ncontent","true");//검색체크유지
			}
		}
		
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
		model.addAttribute("resk",searchKeyword);//***
		System.out.println("searchkeyword : "+searchKeyword);
		
	
		String strPage=request.getParameter("page");
		
		if(strPage==null)
			strPage="1";
		
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		int total=0;
		if (ntitle.equals("ntitle") && ncontent.equals("")) {
			total=dao.noticeboardTotCount1(searchKeyword);
		}else if (ntitle.equals("") && ncontent.equals("ncontent")) {
			total=dao.noticeboardTotCount2(searchKeyword);
		}else if (ntitle.equals("ntitle") && ncontent.equals("bcontent")) {
			total=dao.noticeboardTotCount3(searchKeyword);
		}else if (ntitle.equals("") && ncontent.equals("")) {
			total=dao.noticeboardTotCount4(searchKeyword);
		}
		
		searchVO.pageCalculate(total);
		
		int rowStart=searchVO.getRowStart();
		int rowEnd=searchVO.getRowEnd();
		
		if (ntitle.equals("ntitle") && ncontent.equals("")) {
			model.addAttribute("noticeboard",dao.noticeboard(rowStart,rowEnd,searchKeyword,"1")); 
		}else if (ntitle.equals("") && ncontent.equals("ncontent")) {
			model.addAttribute("noticeboard",dao.noticeboard(rowStart, rowEnd, searchKeyword, "2"));
		}else if (ntitle.equals("ntitle") && ncontent.equals("ncontent")) {
			model.addAttribute("noticeboard",dao.noticeboard(rowStart, rowEnd, searchKeyword, "3"));
		}else if (ntitle.equals("") && ncontent.equals("")) {
			model.addAttribute("noticeboard",dao.noticeboard(rowStart, rowEnd, searchKeyword, "4"));
		}
		
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVo",searchVO);
		
		return "/CS/notice/noticeboard";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request,
			Model model) {
		System.out.println("=======write======");
		
		String ntitle=request.getParameter("ntitle");
		String ncontent=request.getParameter("ncontent");
		
		
		CSDao dao=sqlSession.getMapper(CSDao.class);
		dao.write(ntitle, ncontent);
		
		return "redirect:noticeboard";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("=====Write_View=====");
		
		return "/CS/notice/write_view";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,
			Model model) {
		System.out.println("======content_view======");
		
		int snnum=Integer.parseInt(request.getParameter("nnum"));
		CSDao dao=sqlSession.getMapper(CSDao.class);
		
		NoticeDto dto=dao.contentView(snnum);
		model.addAttribute("content_view",dto);
		
		return "/CS/notice/content_view";
	}
	
	@RequestMapping("/content_update")
	public String content_update(HttpServletRequest request,
			Model model) {
		System.out.println("======content_update======");
		
		int snnum=Integer.parseInt(request.getParameter("nnum"));
		CSDao dao=sqlSession.getMapper(CSDao.class);
		
		NoticeDto dto=dao.contentView(snnum);
		model.addAttribute("content_view",dto);
		
		return "/CS/notice/content_update";
	}
	
	@RequestMapping(method = RequestMethod.POST, value= "/modify")
	public String modify(HttpServletRequest request,
			Model model) {
		System.out.println("======modify======");
		
		int nnum=Integer.parseInt(request.getParameter("nnum"));
		String ntitle=request.getParameter("ntitle");
		String ncontent=request.getParameter("ncontent");
		
		CSDao dao=sqlSession.getMapper(CSDao.class);
		dao.modify(nnum, ntitle, ncontent);
		
		return "redirect:noticeboard";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			Model model) {
		System.out.println("======delete======");
		
		int snnum=Integer.parseInt(request.getParameter("nnum"));
		CSDao dao=sqlSession.getMapper(CSDao.class);
		dao.delete(snnum);
		
		return "redirect:noticeboard";
	}
	
	
	@RequestMapping("/membership")
	public String membership() {
		System.out.println("=====membership=====");
		
		return "/CS/mbs/membership";
	}
}
