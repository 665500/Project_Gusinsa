package com.tech.project_shopping_mall;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tech.project_shopping_mall.dao.MainPageDao;
import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.dto.MainGalleryDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
   
		// 추가한거
		System.out.println("main!!");
		MainPageDao mpDao = sqlSession.getMapper(MainPageDao.class);
		
		ArrayList<Infodto> dto = mpDao.recent_products();
		MainGalleryDto mendto = mpDao.best_men();
		MainGalleryDto womendto = mpDao.best_women();
		String keyword = request.getParameter("keyword");// 검색창에 입력한 값 가져오기
		
//		int score = Integer.parseInt(request.getParameter("score"));
		System.out.println("keyword : " +keyword);
		model.addAttribute("recent", dto);
		model.addAttribute("search", mpDao.search(keyword));
		model.addAttribute("mendto",mendto);
		model.addAttribute("womendto",womendto);
		
		System.out.println("search : " + mpDao );
		
		
		return "/MainPage/Main";
	}
	
}
