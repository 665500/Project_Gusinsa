package com.tech.project_shopping_mall.dto;

import java.sql.Timestamp;

public class Infodto {
private String p_code;
private String ocode;
private String p_name;
private int p_price;
private int p_class;
private String p_content;
private String p_img;
private String p_img2; 
private String p_img3; 
private String p_img4;
private String p_img5; 
private int buycount; 
Timestamp data;

public Infodto() {
	// TODO Auto-generated constructor stub
}
public Infodto(String p_code,String ocode,String p_name,
		int p_price,int p_class,String p_content,String p_img,String p_img2,String p_img3,String p_img4,String p_img5,int buycount,Timestamp data) {
	this.p_code=p_code;
	this.ocode=ocode;
	this.p_name=p_name;
	this.p_price=p_price;
	this.p_class=p_class;
	this.p_content=p_content;
	this.p_img=p_img;
	this.p_img2= p_img2;
	this.p_img3= p_img3;
	this.p_img4= p_img4;
	this.p_img5= p_img5;
	this.buycount = buycount;
	this.data=data;
}
public String getP_code() {
	return p_code;
}
public void setP_code(String p_code) {
	this.p_code = p_code;
}
public String getOcode() {
	return ocode;
}
public void setOcode(String ocode) {
	this.ocode = ocode;
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
public int getP_class() {
	return p_class;
}
public void setP_class(int p_class) {
	this.p_class = p_class;
}
public String getP_content() {
	return p_content;
}
public void setP_content(String p_content) {
	this.p_content = p_content;
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
public int getBuycount() {
	return buycount;
}
public void setBuycount(int buycount) {
	this.buycount = buycount;
}
public Timestamp getData() {
	return data;
}
public void setData(Timestamp data) {
	this.data = data;
}




}