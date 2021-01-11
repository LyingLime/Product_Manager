package com.dao;

import java.util.List;

import com.dto.*;

//DAO
//인터페이스, 메소드 선언만 하고 완성은 자식 클래스가 맡음
public interface ProductDao {	
	
	//상품 추가 등록을 위한 메소드의 선언부 정의(insert)
	public int insertProduct(ProductDto product);
	
	//전체 상품 정보 조회를 위한 메소드의 선언부 정의(select)
	public List<ProductDto> selectList(int menu);
	
	//특정 상품 정보 조회를 위한 메소드의 선언부 정의(select)
	public ProductDto selectInfo(String code);
	
	//특정 상품 수정을 위한 메소드의 선언부 정의(update)
	public int updateProduct(ProductDto product, String upCode);
	
	//상품 삭제 메소드의 선언부 정의(delete)
	public int deleteProduct(String delCode); 
	
}
