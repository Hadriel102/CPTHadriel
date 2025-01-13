import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTHadriel{
	public static void main(String[] args){
		Console con = new Console("Blackjack", 1280, 720);
		BufferedImage imgBlackJack = con.loadImage("Blackjack3.jpg");
		
		// Draw image
		con.drawImage(imgBlackJack, 0, 0);
		
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
		// Special username
		if(strName.equalsIgnoreCase("statitan")){
			intMoney = intMoney + 500;
		}
		System.out.println("User's money: "+intMoney);
		// Input
		System.out.println("The username is: "+ strName);
		
		// Loop gameplay until money reaches 0
		while(intMoney > 0){
			con.print("Enter bet amount: ");
			intBet = con.readInt();
			System.out.println("User bet: "+ intBet);
			intMoney = intMoney - intBet;
			System.out.println("User's money: "+intMoney);
			con.clear();
			
			
			
			// Shuffle deck
			if(intBet > 0){
				intDeck = hadrieltoolsCPT.shuffleDeck();
				System.out.println("Deck is shuffled");
				// Deal cards 
				hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
				hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
				hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount++);
				
				
				// Print hands
				con.println("Dealer's hand: ");
				hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
				con.println("");
				con.println("Player's hand: ");
				hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
				
				// Ask user hit or stand
				con.println("");
				con.println("Hit or Stand: ");
				strOption = con.readLine();
				con.clear();
				
				// Check if user hits or stands
				while(strOption.equalsIgnoreCase("hit")){
					hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount++);
					con.println("Player's new card: ");
					hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
					if(hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount) > 21){
						con.println("Player busts!");
						break;
					}
					con.println("");
					con.println("Hit or stand: ");
					strOption = con.readLine();
					con.clear();
				}
					
				if(strOption.equalsIgnoreCase("stand")){
					while(hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) < 17){
						hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount++);
					}
					con.println("Dealer's hand: ");
					hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
					if(hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) > 21){
						con.println("Dealer busts!");
					}
				}
			}
			
			if(intMoney > 0){
				con.print("Would you like to play again? (y/n): ");
				strOption = con.readLine();
				if(!strOption.equalsIgnoreCase("y")){
					break;
				}
			}else{
				con.println("You are out of money! Game over.");
			}
		}
		
		con.println("Thanks for playing! Final money: "+intMoney);
	}
}

