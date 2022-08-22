package com.tech.project_shopping_mall.dto;

public class MainGalleryDto {
	
	private String p_name;
	private int p_price;
	private int p_code;
	private String p_img;
	private String p_img2;
	private String p_img3;
	private String p_img4;
	private String p_img5;
	private int r_starpoint;
	private String p_gender;
	
	
	
	
	public MainGalleryDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MainGalleryDto(String p_name, int p_price, int p_code, String p_img, String p_img2, String p_img3,
			String p_img4, String p_img5, int r_starpoint, String p_gender) {
		super();
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_code = p_code;
		this.p_img = p_img;
		this.p_img2 = p_img2;
		this.p_img3 = p_img3;
		this.p_img4 = p_img4;
		this.p_img5 = p_img5;
		this.r_starpoint = r_starpoint;
		this.p_gender = p_gender;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public String getP_img() {
		return p_img;
	}
	public void setP_img(String p_img) {
		this.p_img = p_img;
	}
	public String getP_img2() {
		return p_img2;
	}
	public void setP_img2(String p_img2) {
		this.p_img2 = p_img2;
	}
	public String getP_img3() {
		return p_img3;
	}
	public void setP_img3(String p_img3) {
		this.p_img3 = p_img3;
	}
	public String getP_img4() {
		return p_img4;
	}
	public void setP_img4(String p_img4) {
		this.p_img4 = p_img4;
	}
	public String getP_img5() {
		return p_img5;
	}
	public void setP_img5(String p_img5) {
		this.p_img5 = p_img5;
	}
	public int getR_starpoint() {
		return r_starpoint;
	}
	public void setR_starpoint(int r_starpoint) {
		this.r_starpoint = r_starpoint;
	}
	public String getP_gender() {
		return p_gender;
	}
	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}
	
	
	
	
	

}
