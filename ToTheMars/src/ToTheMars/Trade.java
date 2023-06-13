package ToTheMars;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trade {
	MainUser user = new MainUser();
	List<Coin> count = new ArrayList<>(); // 코인 매수갯수 배열
	List<Coin> decount = new ArrayList<>(); // 코인 매도갯수 배열
	public Trade() {        
        System.out.println("현재 잔액 : "+user.wallet);
        List<Coin> CoinList = new ArrayList<>();
        CoinList.add(new Bitcoin());
        CoinList.add(new Ethereum());
        CoinList.add(new Doge());
        CoinList.add(new Luna());
        CoinList.add(new Wemix());

        
        int flag=choice(); //flag== 사용자가 선택한 코인
        //배열에 코인 받고 count로 코인지갑에 몇개 있는지 추가
        user.coin_wallet[flag]=CoinList.get(flag).Name;
        System.out.println(user.coin_wallet[flag]+"값"+user.coin_wallet[flag]);
        
        Trade_Coin(CoinList.get(flag),CoinList,flag); //매수매도


	}
	int choice() {
		Scanner choise_coin=new Scanner(System.in);
		System.out.println("거래할 코인을 선택해주세요" +
				"1.BTC 2.ETH 3.DOGE 4.LUNA 5.WEMIX");
		int flag =choise_coin.nextInt();
		return flag-1;
	}
	
	//매수 롱
	void Trade_Coin(Coin obj,List<Coin> CoinList,int flag) {
		ShowPrice showprice=new ShowPrice();
		showprice.ShowPrice_Method(obj); //선택한 호가창 출력
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("1.매수 2.매도 3.숏 4.레버리지(x2) 5.종료");
			int buysell=scanner.nextInt();

			if(buysell==1) {
				System.out.println("매수할 코인 갯수:");
				int amount=scanner.nextInt();
				user.wallet=user.wallet-(obj.Price*amount);
				if(user.wallet<0) {
					user.wallet=user.wallet+(obj.Price*amount);
					System.out.println("금액이 부족합니다.");
					continue;
				}
				//가지고 있는 코인 모두 출력
				System.out.print("현재 가지고 있는 코인:");
				for (String item : user.coin_wallet) {
				    if (item != null) {
				        System.out.println(item);
				    }
				}
				//매수한  코인 갯수 출력
				int count=count(amount); 
		        System.out.println("구입한 코인 갯수:"+count);
				System.out.println("매수 후 금액:"+user.wallet);
				
			}
			else if(buysell==2) {
				System.out.println("매도할 코인 갯수:");
				int amount=scanner.nextInt();
				if(count.size()<1) {
					System.out.println("매도할 코인이 없습니다.");
					continue;
				}
				user.wallet=user.wallet+(obj.Price*amount);
				System.out.println("매도 후 금액:"+user.wallet); 
				int decount=decount(amount); 
//				System.out.println("count-decount:"+(count.size()-decount));
			}
			//숏
			else if(buysell==3) {
				shorts(CoinList.get(flag), CoinList,flag);
			}
			//레버리지(x2)
			else if(buysell==4) {
				leverage(CoinList.get(flag), CoinList,flag);
			}
			else { //종료
				return;
			}
		} //while문 닫는 블럭

	}

	public int count(int amount) {
		for(int i=0;i<amount;i++) { //구입한 갯수만큼 반복
        count.add(new Bitcoin());//ex:BTC를 샀기 때문에 BTC추가
		}
        return count.size();
	}

	public int decount(int amount) {
		for(int i=0;i<amount;i++) {
        decount.add(new Bitcoin()); //BTC를 샀기 때문에 BTC추가
		}
        return decount.size();
	}

	//롱에서 끌어다 쓸 shorts 클래스 
	void shorts(Coin obj, List<Coin> CoinList,int flag) {
        //변동률 가져오기
        PriceMovement priceMovement = new PriceMovement();
        System.out.print(obj.Name); 
		Coin BTC=new Bitcoin();
		//rate: 병동률
		double rate = Double.parseDouble(String.format("%.2f", priceMovement.priceMove(BTC))); 
		if(rate<0) { //변동값:BTC.Price

			user.wallet=user.wallet-(int)((rate*BTC.Price)/100);
			System.out.println("wallet:"+user.wallet);
			
		}
		else if(rate>0) {
			user.wallet=user.wallet-(int)((rate*BTC.Price)/100);
			System.out.println("wallet:"+user.wallet);
		}
	}

	//레버리지 클래스
	void leverage(Coin obj, List<Coin> CoinList,int flag) {
        //변동률 가져오기
        PriceMovement priceMovement = new PriceMovement();
        System.out.print(obj.Name); 
		Coin BTC=new Bitcoin();
		//rate: 병동률
		double rate = Double.parseDouble(String.format("%.2f", priceMovement.priceMove(BTC))); 
		if(rate<0) { //변동값:BTC.Price
			user.wallet=user.wallet+((int)((rate*BTC.Price)/100)*2);
			System.out.println("wallet:"+user.wallet);
		}
		else if(rate>0) {
			user.wallet=user.wallet+((int)((rate*BTC.Price)/100)*2);
			System.out.println("wallet:"+user.wallet);
		}
	}
	
}
