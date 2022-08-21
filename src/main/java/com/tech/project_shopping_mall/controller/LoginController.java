package com.tech.project_shopping_mall.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tech.finalpj.crypt.CryptoUtil;
import com.tech.project_shopping_mall.dao.LoginMapper;
import com.tech.project_shopping_mall.dto.MembersDto;

import oracle.net.aso.r;


@Controller
@RequestMapping("/login/*")
public class LoginController {
//	BServiceInf commandInf;
	
	String key = "a1b2c3d4e5f6g7h8";
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/loginform")
	public String loginform(HttpServletRequest request, Model model) {
		System.out.println("=========pass by loginform()=============");
//		
		return "/login/loginform";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println("=========pass by login()=============");
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mgrade = request.getParameter("mgrade");
		
		LoginMapper dao=sqlSession.getMapper(LoginMapper.class);
		MembersDto rtnDto = dao.selectUserPassword(mid);
		
		String decryptPwd = CryptoUtil.decryptAES256(rtnDto.getMpw(),key);
		try {
			if(mpw.equals(decryptPwd)) {
				System.out.println("login success!!!");
				//세션에 담기
				HttpSession session=request.getSession();
				session.setAttribute("mid", rtnDto.getMid());
				session.setAttribute("mpw", rtnDto.getMpw());
				session.setAttribute("mgrade", rtnDto.getMgrade());
				System.out.println("아이디와 비밀번호가 맞았습니다");
				session.setMaxInactiveInterval(1800);
			}else {
				System.out.println("아이디와 비밀번호가 틀렸습니다");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:../";
	}
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		System.out.println("=========pass by logout()=============");
//		
		System.out.println("logout gogo!!!");
		//세션에 담기
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/login/loginform";
	}
	
	//아이디 찾기 부분
	@RequestMapping("idcheck")
	public String idcheck(@ModelAttribute MembersDto dto, HttpServletRequest request) {
		System.out.println("=========pass by loginform()=============");
		
		
		
		return "/login/idpw/idcheck";
	}
	@RequestMapping("idcheck2")
	public String idcheck2(HttpServletRequest request, Model model) {
		System.out.println("아이디 확인");		
			
		LoginMapper dao=sqlSession.getMapper(LoginMapper.class);
		String mname = request.getParameter("mname");
		String mphone = request.getParameter("mphone");
		
		System.out.println("mid 가져와야 할거다: " + mname);
		System.out.println("mid 가져와야 할거다: " + mphone);
		
		MembersDto dto = dao.findId(mname, mphone);
		
		System.out.println("dto 가져와야 할거다: " + dto);
		model.addAttribute("mid", dto);
		
		//model.addAttribute("user", dto);
		
		return "/login/idpw/idcheck2";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/PW_Check", produces="text/plane")
	public String PW_Check(@RequestBody String paramData,HttpServletRequest request) throws ParseException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		//클라이언트가 보낸 ID값
		String PW = paramData.trim();
		System.out.println("여기까지 오니 : " + PW);  // 입력한 비밀번호
		HttpSession session = request.getSession();
		String mid = (String )session.getAttribute("mid");  // 세션아이디
		
		
		
		LoginMapper dao=sqlSession.getMapper(LoginMapper.class);
		MembersDto rtnDto = dao.selectUserPassword(mid); // 아직 못한거
		
		System.out.println("rtndto : " +rtnDto); 
		String decryptPwd = CryptoUtil.decryptAES256(rtnDto.getMpw(),key);  // 비밀번호를 복호화한 거
		
		System.out.println("decryptPwd : " + decryptPwd);
		if(PW.equals(decryptPwd)) {// 비번이 맞으면 성공
			return "0";
		} else {		//없으면 아이디 존재 X
			System.out.println("null");
			return "1";
		}
		
		
	}

}


