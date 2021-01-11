package com.view;


import java.util.List;
import java.util.Scanner;

import com.dto.ProductDto;


public class ProductView {//VIEW 클래스, 최종 출력만 담당

	private Scanner scan = new Scanner(System.in);

	public void ttl () {//타이틀 출력 메소드
		System.out.println("========제품 관리 프로그램========");
	}

	public int menu () { //메인메뉴 출력 메소드
		System.out.println("메뉴>>");
		System.out.println("0.프로그램 종료");
		System.out.println("1.제품 추가 등록");
		System.out.println("2.제품 조회");
		System.out.println("3.제품 검색");
		System.out.println("4.제품 수정");
		System.out.println("5.제품 삭제");
		int menu = inputNumber("메뉴 선택 : ");
		return menu;
	}

	public int inputNumber(String str) { //메뉴 번호 입력 메소드
		int num = -1;
		System.out.print(str);
		while(true) {
			try {
				num = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				msgPrint(2);
			}			
		}
		return num;
	}

	// 제품 등록 메소드
	public void inputProduct(ProductDto product) {

		System.out.println("\n*** 제품 추가 등록 ***");                        
		System.out.println("=============================");
		System.out.print("제품유형 : ");
		product.setType(scan.nextLine());
		System.out.print("제품명 : ");
		product.setName(scan.nextLine());
		System.out.print("가격 : ");
		while(true) {

			try {
				product.setPrice(Integer.parseInt(scan.nextLine()));
				break;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
		}
		System.out.print("제품코드 : ");
		product.setCode(scan.nextLine());
		System.out.print("수량 : ");
		while(true) {
			try {
				product.setAmount(Integer.parseInt(scan.nextLine()));
				break;
			}catch(NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
		}

	}

	//제품 전체조회 메소드
	public void printList(List<ProductDto> selectList) {	      
		System.out.println("*** 전체 제품 보기 ***");

		for(ProductDto member : selectList) {
			System.out.println(member);     
		}
		System.out.println("=======================");
	}

	public void printProduct(ProductDto product) {	      

		System.out.println("*** 검색 제품 보기 ***");

		if (product != null) {	//검색 결과가 있으면 != null
			System.out.println(product);	    	  
		} else {	//null일 때, 검색 결과 대상이 없는 것으로 간주
			msgPrint(6);
		}
		System.out.println("-------------------");      
	}


	//제품 검색
	public String inputSerchID(ProductDto product) {
		String serchCode = null;

		System.out.println("*** 제품 검색 ***");
		System.out.println("====================");
		System.out.print("검색할 제품 코드 : ");		
		serchCode = scan.nextLine();	
		return serchCode;
	}

	//제품 수정 - 재고 수량만 수정
	public void updateInput(ProductDto product) {
		System.out.println("*** 수량 정보 수정  ***");
		System.out.println("====================");
		System.out.println(product);
		System.out.print("수정하겠습니까(y/n)? ");
		String str = scan.nextLine();

		if(!str.toUpperCase().equals("Y")) {
			return; // 여기서 메소드 종료(수정 안함)
		}
		System.out.println("수정사항을 입력하세요.(없으면 enter)");

		while(true) {//나이는 숫자가 들어올때까지 반복
			System.out.print("AMOUNT : ");
			str = scan.nextLine();
			if((!str.equals(""))) {
				try {
					product.setAmount(Integer.parseInt(str));
					break;// 숫자가 입력되면 while 종료.
				} catch (NumberFormatException e) {
					//e.printStackTrace();
					msgPrint(2);
				}
			} else {//수정된 나이값이 입력 안되도 while 종료
				break;
			}			
		}		
	}

	//제품 삭제 메소드
	public String deleteData() {
		String delCd = null;

		System.out.println("*** 제품 삭제 ***");
		System.out.println("====================");
		System.out.print("삭제할 코드 : ");
		delCd = scan.nextLine();

		return delCd;


	}

	//서브메뉴 출력
	public int subMenu() {
		int subMenu = -1;
		System.out.println("1) 가전제품");
		System.out.println("2) 생활필수품");
		System.out.println("3) 식품");
		System.out.println("0) 상위메뉴로 돌아가기");
		subMenu  = inputNumber("메뉴 선택 : ");
		return subMenu;
	}

	//서브메뉴 선택 메소드
	public int menuChoice() {
		System.out.println("================");
		System.out.println("1.제품 전체 조회");
		System.out.println("2.가전제품 조회");
		System.out.println("3.생활필수품 조회");
		System.out.println("4.식품 조회");
		System.out.println("0.상위메뉴로 돌아가기");
		int mc = inputNumber("메뉴 선택 : ");	//메뉴 선택값 리턴받아와 대입
		return mc;	//해당값 리턴
	}


	//출력메세지 
	public void msgPrint (int msg) {
		//메시지 스위치, 파라미터 값에 따라 출력할 메시지 결정
		switch (msg) {
		case 0 : //프로그램 종료 메시지
			System.out.println("프로그램 종료");
			break;
		case 1 : //이전 메뉴 돌아가기
			System.out.println("이전 메뉴로 돌아갑니다");
			break;
		case 2 ://번호 입력 오류
			System.out.println("잘못입력하셨습니다.");
			break;
		case 3 ://데이터 없음 메세지
			System.out.println("저장된 데이터가 없습니다.");
			break;
		case 4 ://저장 완료 메세지
			System.out.println("저장 완료");
			break;
		case 5 ://삭제 완료 메시지 
			System.out.println("삭제 완료");
			break;
		case 6 ://검색결과 없음
			System.out.println("검색 결과가 없습니다.");
			break;
		case 7 : //저장 실패
			System.out.println("저장 실패");
			break;
		case 8 : //삭제 실패
			System.out.println("삭제 실패");
			break;
		}//switch 끝


	}//메시지 스위치 끝
}