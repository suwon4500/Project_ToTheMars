package ToTheMars;

abstract class User {
	protected String id;
	protected String passwd;
	public int wallet;
	String[] coin_wallet;
}
class MainUser extends User{
	public MainUser(){
		id="";
		passwd="";
		wallet=50000000;
		coin_wallet=new String[5];
	}
}

