package ToTheMars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PriceMovement priceMovement = new PriceMovement();
        List<MainUser> UserList = new ArrayList<>();
        Log_in log_in = new Log_in();
        Random r = new Random();
        ShowPrice showPrice = new ShowPrice();
        Add_User add_user = new Add_User();
        int flag;
        while (true){
            System.out.println("================================");
            System.out.println("1. 회원가입    2. 로그인    0. 종료");
            flag=scanner.nextInt();
            switch (flag){
                case 0:
                    break;
                case 1:
                    add_user.sign_in(UserList);
                    break;
                case 2:
                    log_in.Log_In(UserList);
                    break;
                default:
                    continue;
            }
            if (log_in.flag==1) {
                continue;
            }else{
                Trade2 trade = new Trade2();
            }
        }
    }
}