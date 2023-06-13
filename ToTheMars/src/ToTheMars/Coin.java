package ToTheMars;

public abstract class Coin {
    //ToTheMars.Bitcoin,ToTheMars.Ethereum,ToTheMars.Doge,ToTheMars.Luna,ToTheMars.Wemix
    public String Name;
    public int Price;
}

class Bitcoin extends Coin{
    Bitcoin(){
        Name="비트코인";
        Price=35000000;
    }
}

class Ethereum extends Coin{
    Ethereum(){
        Name="이더리움";
        Price=2500000;
    }
}

class Doge extends Coin{
    Doge(){
        Name="도지";
        Price=100;
    }
}
class Luna extends Coin{
    Luna(){
        Name="루나";
        Price=100000;
    }
}

class Wemix extends Coin{
    Wemix(){
        Name="위믹스";
        Price=100000;
    }
}



