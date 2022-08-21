package com.tech.project_shopping_mall.dao;

import java.util.ArrayList;

import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.dto.MembersDto;
import com.tech.project_shopping_mall.dto.OrderinfoDto;
import com.tech.project_shopping_mall.dto.StorageDto;



public interface PDao {

	public ArrayList<Infodto> men(int rowStart, int rowEnd, String p_img);

	public int selectBoardTotCount();
	
	public void Norderwrite(String n_name, String n_number, String n_email, String n_addr, String n_request);

	public MembersDto Orderafterlogin(String mid);

	

	



	

	

	

	

	public void questwrite(String string, String o_quest);

	public void norderwrite(String n_ordername, String n_number1, String n_email, String n_name, String n_addr1,
			String n_addr2, String n_addr3, String n_addr4, String n_number2, String n_request);



	public ArrayList<StorageDto> storage(int pcode);

	public void afterpay(int p_code, String p_name, int sprice, int o_count, int o_totprice, String maddr_one,
			String maddr_two, String maddr_three, String maddr_four, String mid, String mphone, String p_img,
			String o_quest, String p_size, String p_color);

	public void afternopay(int p_code, String p_name, int sprice, int o_count, int o_totprice, String n_name,
			String n_ordername, String n_email, String n_addr1, String n_addr2, String n_addr3, String n_addr4,
			String n_number1, String n_number2, String p_img, String n_request, String p_size, String p_color);

	public ArrayList<Infodto> women(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menouter(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> mentop(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menbottom(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomenacc(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomenlife(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menjacket(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> mencoat(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menblazer(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menjumper(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomenbeauty(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomendigital(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomenhome(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menwomenpaper(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menteeshort(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menshirtshort(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menknit(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menvest(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menhoodie(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> mensweatshirt(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> mensleeveless(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> menteelong(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenshirtslong(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenshirtshort(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenknit(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenvest(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenhoodie(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womensweatshirt(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womensleeveless(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenteelong(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenteeshort(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenouter(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenjacket(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womencoat(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenblazer(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> womenjumper(int rowStart, int rowEnd, String string);

	public ArrayList<Infodto> newarrival(int rowStart, int rowEnd, String string);
}
