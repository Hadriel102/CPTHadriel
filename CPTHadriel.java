import arc.*;

public class CPTHadriel{
	public static void main(String[] args){
		Console con = new Console();
		
		// Variables
		int intDeck[][];
		int intPlayer[][];
		int intDealer[][];
		int intValue;
		int intSuite;
		int intCount;
		String strName;
		int intMoney = 1000;
		int intBet;
		int intRand = (int)(Math.random() * 100 + 1);
		
		// Array for deck
		intDeck = new int[52][3];
		
		// Assigning Values
		for(intCount = 0; intCount <= 52; intCount++){
			intDeck[intCount][0] = intValue; 
		}
		for(intCount = 0; intCount <= 52; intCount++){
			intDeck[intCount][1] = intSuite;
		}
		for(intCount = 0; intCount <= 52; intCount++){
			intDeck[intCount][2] = intRand;
		}
		
		// Input
		con.print("Enter your username: ");
		strName = con.readLine();
		con.print("Enter bet amount: ");
		intBet = con.readInt();
		
		// Array for play
		intPlayer = new int[5][2];
		intDealer = new int[5][2];
		
		
	}
}
