package com.dto;

public class ProductDto {
	
	private String code;
	private String name;
	private int price;
	private String type;
	private int amount;
	
	private static ProductDto pdto = null;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;	
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public static ProductDto getInstance() {
		if(pdto == null) {
			pdto = new ProductDto();
		}
		return pdto;
	}
	
	//toString, 인스턴스 자체를 출력지시할 시 대신 실행됨
	//메소드를 작성 하지 않는 경우 인스턴스의 코드 주소만 출력됨
	@Override
	public String toString() {
		String str = "=======================\n"
				+ "제품코드 : " + code + "\n"
				+ "제품명 : " + name + "\n"
				+ "가격 : " + price + "원\n"
				+ "제품 유형 : " + type + "\n"
				+ "수량 : " + amount + "개";				
		return str;
	}

}
