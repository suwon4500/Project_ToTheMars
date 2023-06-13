package ToTheMars;
import java.util.List;
import java.util.Scanner;

public class Log_in {
    int flag=1;
    public void Log_In(List<MainUser> UserList){
        flag=1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("아이디 입력 : ");
        String id = scanner.next();
        System.out.print("비밀번호 입력 : ");
        String pw = scanner.next();
        for (MainUser mainUser : UserList) {
            if (mainUser.id.equals(id) && mainUser.passwd.equals(pw)) {
                System.out.println("로그인 완료");
                System.out.println("반갑습니다. " + mainUser.id + "님!");
                flag = 0;
                break;
            }else if (!mainUser.passwd.equals(pw)){
                System.out.println("잘못된 비밀번호입니다");
            }else if (!mainUser.id.equals(id)){
                System.out.println("존재하지 않는 아이디입니다.");
            }else {
                System.out.println("로그인 실패");
            }
        }
    }
}
