import arc.*;

public class hadrieltoolsCPT {
	public static void main(String[] args){
		
	}
	
    public static int[][] shuffleDeck() {

        // Variables
        int intDeck[][];
        int intCount;

        // Array for deck
        intDeck = new int[52][3];

        // Assigning values to cards
        for (intCount = 0; intCount < 52; intCount++) {
            intDeck[intCount][0] = (intCount % 13) + 1;
            if (intDeck[intCount][0] > 10) {
                intDeck[intCount][0] = 10;
            }
            intDeck[intCount][1] = (intCount / 13) + 1;
            intDeck[intCount][2] = (int) (Math.random() * 100 + 1);
        }

        // Create the variables for the sorting
        int intTempVal;
        int intTempSuite;
        int intTempRand;
        int intRow;
        int intRow2;

        // Loop every single card so it uses bubble sorting and sorts the random numbers from lowest to highest
        for (intRow2 = 0; intRow2 < 52 - 1; intRow2++) {
            for (intRow = 0; intRow < 52 - 1 - intRow2; intRow++) {
                if (intDeck[intRow][2] > intDeck[intRow + 1][2]) {
                    // Take the left item
                    intTempVal = intDeck[intRow][0];
                    intTempSuite = intDeck[intRow][1];
                    intTempRand = intDeck[intRow][2];
                    // Right item moves to the left
                    intDeck[intRow][0] = intDeck[intRow + 1][0];
                    intDeck[intRow][1] = intDeck[intRow + 1][1];
                    intDeck[intRow][2] = intDeck[intRow + 1][2];
                    // Put temporary value on the right
                    intDeck[intRow + 1][0] = intTempVal;
                    intDeck[intRow + 1][1] = intTempSuite;
                    intDeck[intRow + 1][2] = intTempRand;
                }
            }
        }

        return intDeck;
    }
    
    public static void dealCard(int[][] intDeck, int[][] intHand, int intCardCount){
		// Randomly pick a card from the deck
		int intCardIndex = (int)(Math.random() * 52);
		
		// Make sure the card has not been dealt by checking if it's random number is not zero
		while(intDeck[intCardIndex][2] == 0){
			intCardIndex = (int)(Math.random() * 52);
		}
		
		// Add card to player or dealer's hand
		intHand[intCardCount][0] = intDeck[intCardIndex][0];
		intHand[intCardCount][1] = intDeck[intCardIndex][1];
		
		// Mark the card as dealt by setting it to zero
		intDeck[intCardIndex][2] = 0; 
		
	}
    
    public static void printHand(Console con, int[][] intHand, int intCardCount){
		for(int intCount = 0; intCount < intCardCount; intCount++){
			con.println("Card "+(intCount + 1) + ": Value = " + intHand[intCount][0] + ", Suit = " + intHand[intCount][1]);
		}
	}
	
	public static int handValue(int[][] intHand, int intCardCount){
		int intTotal = 0;
		int intAces = 0;
		
		for(int intCount = 0; intCount < intCardCount; intCount++){
			int intCardValue = intHand[intCount][0];
			if(intCardValue == 1){
				intAces = intAces + 1;
				intTotal += 11;
			}else{
				intTotal += intCardValue;
			}
		}
		while(intTotal > 21 && intAces > 0){
			intTotal -= 10;
			intAces = intAces - 1;
		}
	return intTotal;
	}
	
	public static void drawMenu(Console con){
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
		con.println("");
        con.println("                                   Enter P to play");
        con.println("                                   (v)iew high scores");
        con.println("                                   (q)uit");
	}
	
	public static void handlePlayerOptions(Console con, int[][] intDeck, int[][] intPlayer, int[][] intDealer, int intPlayerCardCount, int intDealerCardCount, int intBet, int intMoney) {
		String strOption;
		int intPlayerValue = hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount);
		int intDealerValue = 0;
		boolean blnGameWon = false;

		con.println("Hit or stand: ");
		strOption = con.readLine();
		con.clear();

		while (strOption.equalsIgnoreCase("hit")) {
			hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount);
			intPlayerCardCount++;
			con.println("Player's new card: ");
			hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);

			if (hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount) > 21) {
				con.println("Player busts! You lose!");
				con.println("Your money: " + intMoney);
				return;
			}
			
			// Check for 5 card win after player's hit
			blnGameWon = hadrieltoolsCPT.checkFiveCardWin(con, intPlayer, intPlayerCardCount, intPlayerValue, intDealer, intDealerCardCount, intDealerValue, intBet, intMoney);
			if(blnGameWon){
				return;
			}
			
			con.println("Hit or stand: ");
			strOption = con.readLine();
			con.clear();
		}
		
		// Dealer's turn if player stands
		if (strOption.equalsIgnoreCase("stand")) {
			while (hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) < 17) {
				hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount);
				intDealerCardCount++;
			}
			con.println("Dealer's hand: ");
			hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
			
			if (hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) > 21) {
				con.println("Dealer busts! Player wins!");
				intMoney += intBet * 2;
			} else {
				intPlayerValue = hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount);
				intDealerValue = hadrieltoolsCPT.handValue(intDealer, intDealerCardCount);
				if (intPlayerValue > intDealerValue) {
					con.println("Player wins!");
					intMoney += intBet * 2;
				} else if (intPlayerValue < intDealerValue) {
					con.println("Dealer wins! Player loses!");
				} else {
					con.println("It's a tie!");
					intMoney += intBet;
				}
			}
			con.println("Your money: " + intMoney);
			
			// Check for 5 card win after dealer's turn
			blnGameWon = hadrieltoolsCPT.checkFiveCardWin(con, intPlayer, intPlayerCardCount, intPlayerValue, intDealer, intDealerCardCount, intDealerValue, intBet, intMoney);
			if(blnGameWon){
				return;
			}
		}
	}
	
	public static boolean checkFiveCardWin(Console con, int[][] intPlayer, int intPlayerCardCount, int intPlayerValue, int[][] intDealer, int intDealerCardCount, int intDealerValue, int intBet, int intMoney){
		if(intPlayerCardCount == 5 && intPlayerValue <= 21){
			con.println("Player wins with 5 cards without busting!");
			intMoney += intBet * 3;
			con.println("Your money: "+intMoney);
			return true;
		}else if(intDealerCardCount == 5 && intDealerValue <= 21){
			con.println("Dealer wins with 5 cards without busting! Player loses!");
			con.println("Your money: "+ intMoney);
			return true;
		}
		return false;
	}

	
	
	
	
    
    
    
    
}
