package com.service;

import java.util.List;

import com.dao.ProductDaoImpl;
import com.dto.ProductDto;

public class ProductService {
	ProductDaoImpl pDao = null;

	public ProductService() {
		pDao = ProductDaoImpl.getInstance();
	}

	//상품 추가 등록 메소드
	public int regProduct(ProductDto product) {
		int result = -1;

		result = pDao.insertProduct(product);

		if(result > 0) {
			result = 4;
		}
		else {
			result = 7;
		}
		return result;
	}//regProduct end

	//전체 상품 정보 조회 메소드	
	public List<ProductDto> productList(int menu) {

		List<ProductDto> pList = null;
		pList = pDao.selectList(menu);

		return pList;
		
		
	}//productList end

	//특정 상품 정보 조회 메소드
	public ProductDto productInfo(String pid) {
		ProductDto product = null;
		product = pDao.selectInfo(pid);
		return product;
	}//productInfo end

	//특정 상품 수정 메소드
	public int updateInfo(ProductDto product, String upCode) {
		int result = -1;
		result = pDao.updateProduct(product, upCode);

		if(result > 0) {
			result = 4;
		}
		else {
			result = 7;
		}

		return result;
	}//updateInfo end

	//상품 삭제 메소드
	public int delProduct(String delCode) {
		int result = 0;
		result = pDao.deleteProduct(delCode);

		if(result > 0) {
			result = 5;
		}
		else {
			result = 8;
		}

		return result;		
	}//delProduct end
	

}//class end
