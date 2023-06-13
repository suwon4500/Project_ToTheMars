package ToTheMars;

import java.util.Random;

public class PriceMovement{
    private Random r = new Random();
    private double movementRate;
    private double movementPercent;
    private String temp;
    public double priceMove(Coin obj) {
        movementPercent=obj.Price;
        movementRate = (r.nextDouble(-0.1,0.1));
        obj.Price = (int)(obj.Price + (obj.Price * movementRate));
        movementPercent=(obj.Price-movementPercent)/movementPercent*100;
        String temp = String.format("%.2f", movementPercent);
        System.out.println("코인 가격 : "+obj.Price);
        System.out.println("변동률 : "+temp+"%");
        return movementPercent;
    }
}