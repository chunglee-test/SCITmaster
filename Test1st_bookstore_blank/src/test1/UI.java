package test1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <pre>
 * 서점 관리 프로그램의 사용자 화면(User Interface) 클래스
 * 사용자로부터 처리와 관련된 입력을 받고, Manager 클래스를 통해 처리된 결과를 보여준다.
 * </pre>
 */
public class UI {
	//요청과 관련된 처리를 하기 위해 생성한 Manager 클래스의 객체
	private Manager manager;
	
	//키보드로부터 정보를 입력받기 위해 생성한 Scanner 클래스의 객체
	private Scanner sc;
	
	/**
	 * 생성자.
	 * 
	 */
	public UI() {
		manager = new Manager();
		sc = new Scanner(System.in);
	}

	/**
	 * UI를 시작한다
	 */
	public void start() {
		// 프로그램 종료 전까지 계속 반복한다
		boolean canContinue = true;
		
		while (canContinue) {
			System.out.println("┌──────────────────────────────┐");
			System.out.println("│      서점 관리 시스템     	       │");
			System.out.println("└──────────────────────────────┘");
			System.out.println("┌──────────────────────────────┐");
			System.out.println("│ 1.등록                    	       │");	
			System.out.println("│ 2.찾기                    	       │");	
			System.out.println("│ 3.삭제                    	       │");	
			System.out.println("│ 4.전체출력                	       │");	
			System.out.println("│ 9.종료                    	       │");
			System.out.println("└──────────────────────────────┘");
			
			int option = getNextInt("메뉴 번호를 선택하세요=> ");
			
			switch (option) {
				case 1:	// 등록
					insert();
					break;
					
				case 2:	// 검색
					select();
					break;
					
				case 3:	// 삭제
					delete();
					break;
					
				case 4:	// 전체 출력
					print();
					break;
					
				case 9:	// 종료
					canContinue = false;
					System.out.println("프로그램을 종료합니다.");
					break;
	
				default:
					System.out.println("[에러] 잘못된 입력입니다");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 세 종류의 책 정보를 입력 받아 알맞는 객체를 생성하고
	 * 이를 매니저를 통해 보존한다
	 */
	private void insert() {
		// 종료 전까지 계속 반복한다
		boolean canContinue = true;
		
		while (canContinue) {
			System.out.println("┌──────────────────────────────┐");
			System.out.println("│        서점 관리 등록	       │");
			System.out.println("└──────────────────────────────┘");
			System.out.println("┌──────────────────────────────┐");
			System.out.println("│ 1.책                      	       │");	
			System.out.println("│ 2.전차잭                  	       │");	
			System.out.println("│ 3.잡지                    	       │");	
			System.out.println("│ 9.상위메뉴                	       │");
			System.out.println("└──────────────────────────────┘");
			
			int option = getNextInt("메뉴 번호를 선택하세요=> ");
			
			// 9번이면 종료한다
			if (option == 9) {
				canContinue = false;
				continue;
			}
			
			// 1 ~ 3번 사이가 아니면 다시 입력 받는다
			if (option < 1 || 3 < option) {
				System.out.println("[에러] 잘못 입력하셨습니다");
				continue;
			}
			
			// 등록을 시작한다
			Publication pub = null;
			
			//CODE HERE
			int productId = getNextInt("제품번호: ");
			System.out.print("제목: ");
			String title = sc.next();
			System.out.print("출판사: ");
			String company = sc.next();
			int price = getNextInt("가격: ");
			
			switch (option) {
				case 1:	// 책
					//CODE HERE
					System.out.print("저자: ");
					String author = sc.next();
					
					pub = new Book(productId, title, company, price, author);
					
					break;
					
				case 2:	// 전자책
					//CODE HERE
					System.out.print("포맷: ");
					String format = sc.next();
					int filesize = getNextInt("용량: ");
					
					pub = new Ebook(productId, title, company, price, format, filesize);
					break;
					
				case 3:	// 잡지
					//CODE HERE
					int year = getNextInt("발행년도: ");
					int month = getNextInt("발행월: ");
					
					pub = new Magazine(productId, title, company, price, year, month);
					
					break;
			}
			
			//CODE HERE
			boolean isInserted = manager.insert(pub);
			
			if (isInserted) {
				System.out.println("[알림] 등록을 완료하였습니다.");
			} else {
				System.out.println("[알림] 등록을 실패하였습니다.");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 제품번호를 입력 받아 해당 번호와 일치하는 제품번호를 가지고 있는 정보를 출력한다
	 */
	private void select() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│             검색        	       │");
		System.out.println("└──────────────────────────────┘");
		
		// 제품 번호를 입력 받는다
		int productId = getNextInt("제품번호 입력>> ");
		
		// 검색하여 정보를 찾는다
		//CODE HERE
		Publication selected = manager.select(productId);
		
		if (selected == null) {
			System.out.println("[알림] 해당 제품을 찾지 못했습니다.");
		} else {
			System.out.println(selected);
		}
	}

	/**
	 * 제품번호를 입력 받아 해당 번호와 일치하는 제품번호를 가지고 있는 정보를 삭제한다
	 */
	private void delete() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│             삭제        	       │");
		System.out.println("└──────────────────────────────┘");
		
		// 제품 번호를 입력 받는다
		int productId = getNextInt("제품번호 입력>> ");
		
		// 삭제하고 삭제 여부를 확인한다
		//CODE HERE
		boolean isDeleted = manager.delete(productId);
		
		if (isDeleted) {
			System.out.println("[알림] 삭제를 완료하였습니다.");
		} else {
			System.out.println("[알림] 삭제를 실패하였습니다.");
		}
	}

	/**
	 * 매니저로부터 리스트 정보를 가지고 와서 모든 정보를 출력한다
	 */
	private void print() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│          전체 출력       	       │");
		System.out.println("└──────────────────────────────┘");
		
		// 리스트를 가져온다
		//CODE HERE
		ArrayList<Publication> pubList = manager.getPubList();
		
		// 한 건도 존재하지 않으면 유저에게 알리고 메소드 종료
		//CODE HERE
		if (pubList.isEmpty()) {
			System.out.println("[알림] 저장된 정보가 한 건도 존재하지 않습니다.");
			return;
		}
		
		// 전체 출력한다
		//CODE HERE
		for (Publication pub : pubList) {
			System.out.println(pub);
		}
	}
	
	/**
	 * 잘 처리된 숫자를 입력 받는다
	 * @return 0이 아닌 숫자
	 */
	private int getNextInt(String message) {
		int option = 0;
		
		do {
			try {
				System.out.print(message);
				option = sc.nextInt();
			} catch (Exception e) {
				System.out.println("[에러] 잘못 입력하였습니다");
				sc.nextLine();
			}
		} while(option == 0);
		
		return option;
	}
}

