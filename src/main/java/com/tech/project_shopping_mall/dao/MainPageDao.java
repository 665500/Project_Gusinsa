package com.tech.project_shopping_mall.dao;

import java.util.ArrayList;

import com.tech.project_shopping_mall.dto.Infodto;
import com.tech.project_shopping_mall.dto.MainGalleryDto;
import com.tech.project_shopping_mall.dto.MainPageSearchDto;
import com.tech.project_shopping_mall.dto.MainPageSearch_ProductsDto;
import com.tech.project_shopping_mall.dto.ReviewDto;
import com.tech.project_shopping_mall.dto.StorageDto;

public interface MainPageDao {
	// 검색해서 다시 받아오는게 2개 이상이라 리스트 형태로 받아야함
	public ArrayList<MainPageSearchDto> search (String keyword);
	public ArrayList<MainPageSearchDto> searchtest (String keyword);
	public void  searchcount(String keyword);
	
	
	
	public ArrayList<Infodto> recent_products();
	
	public ArrayList<Infodto> SearchProduct(int rowStart, int rowEnd,String keyword);
	public ArrayList<Infodto> testSearchProduct(String keyword);
	
	public void search_write(String search_word);
	public void modify_search(String sbid,String bname,
			String btitle,String bcontent);
	public int selectBoardTotCount_search(String keyword);
	
	
	//리뷰
	   public ArrayList<ReviewDto> review_list(int rowStart, int rowEnd);
	   public void review_write(int pcode, String mid, String r_starpoint
	   ,String r_title, String r_contents, String fname, int ocode);
	   public ReviewDto review_view(String r_num);
	   public void review_modify(String r_num,String r_starpoint,
	         String r_title,String r_contents);
	   public void review_delete(String r_num);
	   
	   public void download(String path, String fname, String r_num);
	   
	   public double staravg(int pcode);
	   public int selectReviewTotCount();
	
	
	public ArrayList<StorageDto> storage(int pcode);
	
	public Infodto search_prouct_detail(int pcode);
	public ArrayList<ReviewDto> product_review(int pcode, int rowStart, int rowEnd);
	
	public MainGalleryDto best_men();
	public MainGalleryDto best_women();



	
}
