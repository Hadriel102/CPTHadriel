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
		intHand[intCardCount][0] = intDeck[intCardCount][0];
		intHand[intCardCount][1] = intDeck[intCardCount][1];
	}
    
    public static void printHand(Console con, int[][] intHand, int intCardCount){
		for(int intCount = 0; intCount < intCardCount; intCount++){
			con.println("Card "+(intCount + 1) + ": Value = " + intHand[intCount][0] + ", Suit = " + intHand[intCount][1]);
		}
	}
    
    
    
    
}
