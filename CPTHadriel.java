import arc.*;

public class CPTHadriel{
	public static void main(String[] args){
		Console con = new Console("Blackjack", 1280, 720);
		
		// Variables
		int intDeck[][];
		int intPlayer[][];
		int intDealer[][];
		String strName;
		int intMoney = 1000;
		int intBet;
		int intCount;
		
		// Array for deck
		intDeck = new int[52][3];
		
		// Input
		con.print("Enter your username: ");
		strName = con.readLine();
		con.print("Enter bet amount: ");
		intBet = con.readInt();
		
		if(intBet > 0){
			hadrieltoolsCPT.shuffleDeck();
		}
		
		// Array for play
		intPlayer = new int[5][2];
		intDealer = new int[5][2];
		
		
		

	}
}
