package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ProductDto;

public class ProductDaoImpl implements ProductDao {	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "dev";
	private String password = "1234";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private static ProductDaoImpl pDao = null;
	
	private ProductDaoImpl() {		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}		
	}
	
	public static ProductDaoImpl getInstance() {
		if(pDao == null) {
			pDao = new ProductDaoImpl();
		}
		return pDao;
	}
	
	//상품 등록 INSERT 메소드
	@Override
	public int insertProduct(ProductDto product) {
		int result = 0;
		String query = "INSERT INTO PT VALUES (?, ?, ?, ?, ?)";  
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, product.getCode());
			pstmt.setString(2, product.getType());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getAmount());
			pstmt.setInt(5, product.getPrice());
			
			result = pstmt.executeUpdate();
			conn.commit();			
		} catch (SQLException e) {			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				//e1.printStackTrace();
			} finally {
				close();
			}
		}		
		return result;
	}
	
	@Override
	public List<ProductDto> selectList(int menu) {
		//상품 정보 목록 저장 List
		
		String query1 = "SELECT * FROM PT";
		String query2 = "SELECT * FROM PT WHERE P_TYPE = '가전제품'";	
		String query3 = "SELECT * FROM PT WHERE P_TYPE = '생활필수품'";	
		String query4 = "SELECT * FROM PT WHERE P_TYPE = '식품'";
		
		ArrayList<ProductDto> pList = new ArrayList<ProductDto>();
				
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			switch(menu) {
			case 1 : 
				pstmt = conn.prepareStatement(query1);
				break;
			case 2 :
				pstmt = conn.prepareStatement(query2);
				break;				
			case 3:
				pstmt = conn.prepareStatement(query3);
				break;
			case 4 : 
				pstmt = conn.prepareStatement(query4);
				break;
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDto p = new ProductDto();
				p.setCode(rs.getString(1));
				p.setType(rs.getString(2));
				p.setName(rs.getString(3));
				p.setAmount(rs.getInt(4));
				p.setPrice(rs.getInt(5));
				pList.add(p);				
			}//while			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close();
		}		
		return pList;
	}	
	
	
	@Override
	public ProductDto selectInfo(String code) {
		//코드로 DB 검색하는 쿼리문
		String query = "SELECT * FROM PT WHERE P_CODE = ?";
		ProductDto product = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setNString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = new ProductDto();
				product.setCode(rs.getString(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setType(rs.getString(4));
				product.setAmount(rs.getInt(5));					
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close();
		}		
		return product;
	}	
	
	@Override
	public int updateProduct(ProductDto product, String upCode) {
		int result = 0;
		String query = "UPDATE PT SET P_AMOUNT=? WHERE P_CODE = ?";		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(2, upCode);
			pstmt.setInt(1, product.getAmount());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				//e1.printStackTrace();
			}
		} finally {
			close();
		}		
		return result;
	}	
	
	//코드로 검색하여 상품 정보 삭제
	@Override
	public int deleteProduct(String delCode) {
		int result = 0;
		String query = "DELETE FROM PT WHERE P_CODE = ?";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delCode);
			result = pstmt.executeUpdate();
			conn.commit();
		}
		catch (SQLException e) {			
			//e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				//e1.printStackTrace();
			}
		}
		finally {
			close();
		}
		return result;
	}	
	
	//접속 해제 메소드 close
	private void close() {		
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}//try 끝
		catch (SQLException e) {			
			//e.printStackTrace();
		}//catch 끝
	}//close 메소드 끝

}//클래스 끝
