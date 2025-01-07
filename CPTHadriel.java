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
			for(intValue = 0; intValue <= 13; intValue++){
				intDeck[intCount][0] = intValue; 
			}
		}
		for(intCount = 0; intCount <= 52; intCount++){
			for(intSuite = 0; intSuite <= 4; intSuite++){
				intDeck[intCount][1] = intSuite;
			}
		}
		for(intCount = 0; intCount <= 52; intCount++){
			intDeck[intCount][2] = intRand;
		}
		
		// Sort
		for (intRand = 0; intRand < 3 - 1; intRand++) {
			for (intValue = 0; intValue < 3 - 1 - intRand; intValue++) {
				// Compare the prices (removing the "$" sign)
				if(intDeck[intRand][2] > intDeck[intRand + 1][2]){
					// Take Left Item
					intValue = intDeck[intValue][0];
					intRand = intDeck[intValue][2];
					// Right Item Moves To The Left
					intDeck[intValue][0] = intDeck[intValue + 1][0];
					intDeck[intValue][2] = intDeck[intValue + 1][2];
					// Put Temporary Value On The Right
					intDeck[intValue + 1][0] = intValue;						
					intDeck[intValue + 1][2] = intRand;
				}
				con.println(intValue);
				con.println(intRand);
				con.sleep(1000);
			}
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
