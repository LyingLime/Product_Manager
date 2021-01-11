package com.control;

import com.dto.ProductDto;
import com.service.ProductService;
import com.view.ProductView;

public class ProductControl {//실제 실행 제어 클래스
	//인스턴스 준비
	ProductView pv = new ProductView();	//뷰 - 입출력
	ProductService ps = new ProductService(); //서비스 - 1차적으로 줄 상대

	public void run () {
		//필드
		int menu = -1;

		//인스턴스
		ProductDto product = null;

		//임포트
		pv.ttl();	//타이틀 - 보이드

		while(true) {//while 반복 블럭	
			menu = pv.menu(); //메뉴 - INT 메뉴 선택값 리턴

			//종료 우선 실행
			if (menu == 0) {
				pv.msgPrint(0); //종료
				break;
			}//if 끝

			//메인메뉴 스위치 - 'menu' 변수를 따름
			switch (menu) {
			case 1 ://상품 데이터 등록 -> 서브메뉴 나눠짐 INSERT
				product = ProductDto.getInstance();	//싱글톤 패턴, 인스턴스 new 대신 게터로 받아오기
				pv.inputProduct(product);//해당 인스턴스에 새 값을 투입
				int result1 = ps.regProduct(product); //서비스-pdi를 통해 DB 처리후 결과값 받음
				pv.msgPrint(result1);	//받은 결과, 메시지 코드를 다시 view에 투입

				break;
			case 2 ://상품 데이터 조회
				int num = -1;
				num = pv.menuChoice();						
				pv.printList(ps.productList(num));
				break;
			case 3 ://상품 데이터 검색
				product = ProductDto.getInstance();
				String pid3 = pv.inputSerchID(product);
				product = ps.productInfo(pid3); //검색 결과를 담은 인스턴스 받아옴
				pv.printProduct(product);
				break;
			case 4 ://상품 데이터 수정 -> SELECT+UPDATE
				product = ProductDto.getInstance();
				String pid4 = pv.inputSerchID(product);
				product = ps.productInfo(pid4); //검색 결과를 담은 인스턴스 받아옴
				pv.updateInput(product);
				int result4 = ps.updateInfo(product, pid4);
				pv.msgPrint(result4);
				break;
			case 5 ://상품 데이터 삭제 -> DELETE
				String did = pv.deleteData();
				int result5 = ps.delProduct(did);
				pv.msgPrint(result5);
				break;
			default : //범위 밖의 메뉴 숫자
				pv.msgPrint(2);	//범위 밖 선택시 메시지 코드 2
			}//switch 끝

		}//while 끝

	}//run 메소드 끝

}//컨트롤 클래스 끝