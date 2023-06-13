package ToTheMars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Trade2 {
	
	Scanner choice=new Scanner(System.in);
	MainUser user = new MainUser();
	List<Coin> count = new ArrayList<>(); // 코인 매수갯수 배열
	List<Coin> decount = new ArrayList<>(); // 코인 매도갯수 배열
	List<Coin> CoinList = new ArrayList<>();
	HashMap<String, Integer> sell_dictionary = new HashMap<>();//유저가 선택한 코인 매도 딕셔너리
	public Trade2() {        
        System.out.println("현재 wallet:"+user.wallet);
        
        CoinList.add(new Bitcoin());
        CoinList.add(new Ethereum());
        CoinList.add(new Doge());
        CoinList.add(new Luna());
        CoinList.add(new Wemix());
        
        int flag=choice();
        user.coin_wallet[flag]=CoinList.get(flag).Name;
        Trade_long(CoinList.get(flag)); //shorts 실험으로 flag추가
	}
	//유저가 어떤 코인을 선택할지
	int choice() {
		System.out.println("1.BTC 2.ETH 3.DOGE 4.LUNA 5.WEMIX");
		int flag =choice.nextInt();
		return flag-1;
	}
	//롱
	void Trade_long(Coin obj) {//shorts 실험으로 flag추가
		while(true) {
			System.out.println("0.코인 재선택 1.매수 2.매도 3.숏 4.레버리지(x2) 5.종료");
			int buysell=choice.nextInt();
			//재선택
			if(buysell==0) {
			    int flag = choice();
			    obj=CoinList.get(flag);
			    user.coin_wallet[flag]=CoinList.get(flag).Name;
				
			}
			//매수
			if(buysell==1) {
				System.out.println("매수할 코인 갯수:");
				int amount=choice.nextInt();
				
				if(user.wallet-(obj.Price*amount)<100) {
					System.out.println("금액이 부족합니다.");
					continue;
				}
				user.wallet=user.wallet-(obj.Price*amount);
				System.out.println();
				int count=count(obj, amount); 
		        System.out.println("구입한 코인 갯수:"+count);
				System.out.println("매수 후 금액:"+user.wallet);
				//매도떄 쓰기위한 딕셔너리 추가 {코인이름:코인 카운트}
				sell_dictionary.put(obj.Name, count);
		        for (String key : sell_dictionary.keySet()) {
		            int val = sell_dictionary.get(key);
		            System.out.println(key + ": " + val);
		        }
			}
			//매도
			if(buysell==2) {
				System.out.println("매도할 코인 선택(비트코인, 이더리움 ,도지 ,루나 ,위믹스)");
				String choice_coin=choice.next();
				boolean containsKey = sell_dictionary.containsKey(choice_coin);
				if(containsKey==true) {
				System.out.println("매도할 코인 갯수:");
				int amount=choice.nextInt();
				if(count.size()<1) { //다릉 코인 팔때 이게 뚤림
					System.out.println("매도할 코인이 없습니다.");
					continue;
				}
		        String key = choice_coin;
		        int decreaseBy = amount;
		        if (sell_dictionary.containsKey(key)) {
		            int value = sell_dictionary.get(key);
		            value -= decreaseBy;
		            
		            if (value <= 0) {
		                sell_dictionary.remove(key);
		            } else {
		                sell_dictionary.put(key, value);
		            }
		        }

		        System.out.println("매도할 코인 재확인:");
		        int flag=choice(); //변경
		        obj = CoinList.get(flag);
		        obj.Price = CoinList.get(flag).Price;
//		        System.out.println("CoinList.get(flag).Price값 확인"+CoinList.get(flag).Price);
//		        System.out.println("obj.Price의값(업데이트되는지 확인):"+obj.Price);
				user.wallet=user.wallet+(obj.Price*amount);
				System.out.println("매도 후 금액:"+user.wallet); 
				//매도하고 난 후 코인과 코인 갯수
		        for (String key2 : sell_dictionary.keySet()) {
		            int val = sell_dictionary.get(key2);
		            System.out.println(key2 + ": " + val);
		        }
				}
		        
			
			}
			if(buysell==3) {
				//숏 완성본 1
//				System.out.println("숏 할 코인 선택");
//				int flag=choice();
//				shorts(CoinList.get(flag), CoinList,flag);
				////////////////////////////////
				//숏 완성본 2
				System.out.println("숏 할 코인 선택");
				int flag=choice();
				obj=CoinList.get(flag);
				shorts(obj,flag);
				
			}
			if(buysell==4) {
				System.out.println("레버리지 할 코인 선택");
				int flag=choice();
				obj=CoinList.get(flag);
				leverage(obj,flag);
			}
			if(buysell==5) {
				return;
			}
			else {
//				System.out.println("다시 입력 해주십시오.");
				continue;
			}
			
		}

	}
	public int count(Coin obj,int amount) {
		count.clear();
		for(int i=0;i<amount;i++) { 
        count.add(obj);
		}
		return count.size();
	}
	public int decount(Coin obj,int amount) {
		for(int i=0;i<amount;i++) {
        decount.add(obj); //BTC를 샀기 때문에 BTC추가
		}
        return decount.size();
	}
//	shorts 완성본 2
	void shorts(Coin obj,int flag) {
		PriceMovement priceMovement = new PriceMovement();
		System.out.print(obj.Name);
		Coin coin = CoinList.get(flag);
		double rate = Double.parseDouble(String.format("%.2f", priceMovement.priceMove(coin)));
	    if (rate < 0) {
        user.wallet = user.wallet - (int) ((rate * coin.Price) / 100);
        System.out.println("wallet:" + user.wallet);
    } else if (rate > 0) {
        user.wallet = user.wallet - (int) ((rate * coin.Price) / 100);
        System.out.println("wallet:" + user.wallet);
    }
		
	}
	//숏 완성본 1
//	void shorts(Coin obj, List<Coin> CoinList, int flag) {
//	    // 변동률 가져오기
//	    PriceMovement priceMovement = new PriceMovement();
//	    System.out.print(obj.Name);
//
//	    Coin coin = CoinList.get(flag);
//	    double rate = Double.parseDouble(String.format("%.2f", priceMovement.priceMove(coin)));
//
//	    if (rate < 0) {
//	        user.wallet = user.wallet - (int) ((rate * coin.Price) / 100);
//	        System.out.println("wallet:" + user.wallet);
//	    } else if (rate > 0) {
//	        user.wallet = user.wallet - (int) ((rate * coin.Price) / 100);
//	        System.out.println("wallet:" + user.wallet);
//	    }
//	}

	//leverage
	void leverage(Coin obj,int flag) {
		PriceMovement priceMovement = new PriceMovement();
		System.out.print(obj.Name);
		Coin coin = CoinList.get(flag);
		double rate = Double.parseDouble(String.format("%.2f", priceMovement.priceMove(coin)));
	    if (rate < 0) {
	    	user.wallet=user.wallet+((int)((rate*coin.Price)/100)*2);
        System.out.println("wallet:" + user.wallet);
    } else if (rate > 0) {
    	user.wallet=user.wallet+((int)((rate*coin.Price)/100)*2);
        System.out.println("wallet:" + user.wallet);
    }
		
	}
	
}
