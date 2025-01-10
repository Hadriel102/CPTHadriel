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
		int intPlayerCardCount = 0;
		int intDealerCardCount = 0;
		String strOption = "";
		
		// Array for deck
		intDeck = new int[52][3];
		
		// Array for play
		intPlayer = new int[5][2];
		intDealer = new int[5][2];
		
		// Assigning Values
		for(intCount = 0; intCount < 52; intCount++){
			intDeck[intCount][0] = (intCount % 13) + 1;
			if(intDeck[intCount][0] > 10){
				intDeck[intCount][0] = 10;
			}
			intDeck[intCount][1] = (intCount / 13) + 1;
			intDeck[intCount][2] = (int)(Math.random() * 100 + 1);
		}	
		
		// Input
		con.print("Enter your username: ");
		strName = con.readLine();
		con.print("Enter bet amount: ");
		intBet = con.readInt();
		intMoney = intMoney - intBet;
		con.clear();
		
		// Special username
		if(strName.equalsIgnoreCase("statitan")){
			intMoney = intMoney + 500;
		}
		
		// Shuffle deck
		if(intBet > 0){
			intDeck = hadrieltoolsCPT.shuffleDeck();
			System.out.println("Deck is shuffled");
			// Deal cards 
			hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
			hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
			hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount++);
			
			// Print hands
			con.println("Player's hand: ");
			hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
			con.println("Dealer's hand: ");
			hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
			
			// Ask user hit or stand
			con.println("Hit or Stand: ");
			strOption = con.readLine();
			
			if(strOption.equalsIgnoreCase("hit")){
				hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
				con.println("Player's new card: ");
				hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
			}else if(strOption.equalsIgnoreCase("stand")){
				hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount++);
				con.println("Dealer's hand: ");
				hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
			}
			
			
			
	
		}
	
	}
}
