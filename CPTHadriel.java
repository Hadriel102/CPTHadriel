	import arc.*;
	import java.awt.*;
	import java.awt.image.*;

	public class CPTHadriel {
		public static void main(String[] args) {
			// Create console window
			Console con = new Console("Blackjack", 1280, 720);
			BufferedImage imgBlackJack = con.loadImage("Blackjack4.jpg");
			BufferedImage imgFunnyJoke = con.loadImage("funnyjoke.jpg");
			TextOutputFile winners = new TextOutputFile("winners.txt", true);
			

			// Draw background image
			con.drawImage(imgBlackJack, 0, 0);

			// Variables
			int[][] intDeck;
			int[][] intPlayer;
			int[][] intDealer;
			String strName = "";
			int intMoney = 1000;
			int intBet = 0;
			int intCount;
			int intPlayerCardCount = 0;
			int intDealerCardCount = 0;
			String strOption = "";
			char chrChoice;
			int intPlayerValue = 0;
			int intDealerValue = 0;
			boolean blnGameWon = false; 

			// Array for deck
			intDeck = new int[52][3];

			// Main menu
			boolean blnContinue = true;
			while(blnContinue){
				hadrieltoolsCPT.drawMenu(con);
				chrChoice = con.readChar();

				// Input
				if ((chrChoice == 'p')) {
					con.clear();
					con.print("Enter your username: ");
					strName = con.readLine();
					// Special username
					if (strName.equalsIgnoreCase("statitan")) {
						intMoney = intMoney + 500;
					}
					System.out.println("User's money: " + intMoney);
					// Input
					System.out.println("The username is: " + strName);

					// Loop gameplay until money reaches 0
					while (intMoney > 0) {
						// Array for play
						intPlayer = new int[5][2];
						intDealer = new int[5][2];
						
						// Reset card counts and game status
						intPlayerCardCount = 0;
						intDealerCardCount = 0;
						blnGameWon = false;
						hadrieltoolsCPT.shuffleDeck();

						// Enter bet with error handling
						boolean blnValidBet = false;
						while(!blnValidBet){
							con.print("Enter bet amount: ");
							intBet = con.readInt();
							if(intBet > 0 && intBet <= intMoney){
								blnValidBet = true;
							}else{
								con.println("Invalid bet amount. Please enter a valid amount.");
							}
						}
						
						// Debug messages
						System.out.println("User bet: " + intBet);
						intMoney = intMoney - intBet;
						System.out.println("User's money: " + intMoney);
						con.clear();

						// Shuffle deck
						if (intBet > 0) {
							intDeck = hadrieltoolsCPT.shuffleDeck();
							System.out.println("Deck is shuffled");
							// Deal cards
							hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount);
							intPlayerCardCount++;
							hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount);
							intPlayerCardCount++;
							hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount);
							intDealerCardCount++;

							// Calculating initial hand values
							intPlayerValue = hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount);
							intDealerValue = hadrieltoolsCPT.handValue(intDealer, intDealerCardCount);

							// Print hands
							con.println("Dealer's hand: ");
							hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
							con.println("");
							con.println("Player's hand: ");
							hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);

							// Check for blackjacks
							if (hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount) == 21) {
								con.println("Blackjack! Player wins!");
								intMoney += intBet * 3;
								con.println("Your money: " + intMoney);
							} else if (hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) == 21) {
								con.println("Dealer has Blackjack! Player loses!");
								con.println("Your money: " + intMoney);
							} else if (intPlayerValue == 9 || intPlayerValue == 10 || intPlayerValue == 11) {
								con.println("Would you like to double down? (y/n): ");
								strOption = con.readLine();
								if (strOption.equalsIgnoreCase("y")) {
									intBet = intBet * 2;
									hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount);
									intPlayerCardCount++;
									con.println("Player's cards: ");
									hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
									while (hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) < 17) {
										hadrieltoolsCPT.dealCard(intDeck, intDealer, intDealerCardCount);
										intDealerCardCount++;
									}
									con.println("Dealer's hand: ");
									hadrieltoolsCPT.printHand(con, intDealer, intDealerCardCount);
									if (hadrieltoolsCPT.handValue(intDealer, intDealerCardCount) > 21) {
										con.println("Dealer busts! Player wins!");
										intMoney += intBet * 2;
										con.println("Your money: " + intMoney);
									} else {
										intPlayerValue = hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount);
										intDealerValue = hadrieltoolsCPT.handValue(intDealer, intDealerCardCount);
										if (intPlayerValue > intDealerValue) {
											con.println("Player wins!");
											intMoney += intBet * 2;
											con.println("Your money: " + intMoney);
										} else if (intPlayerValue < intDealerValue) {
											con.println("Dealer wins! Player loses!");
											con.println("Your money: " + intMoney);
										} else {
											con.println("It's a tie!");
											intMoney += intBet;
											con.println("Your money: " + intMoney);
										}
									}
								}else if(strOption.equalsIgnoreCase("n")){
									hadrieltoolsCPT.handlePlayerOptions(con, intDeck, intPlayer, intDealer, intPlayerCardCount, intDealerCardCount, intBet, intMoney);
								}else{
									con.println("Invalid option. Proceeding with regular game.");
									hadrieltoolsCPT.handlePlayerOptions(con, intDeck, intPlayer, intDealer, intPlayerCardCount, intDealerCardCount, intBet, intMoney);
								}
							}else{
								hadrieltoolsCPT.handlePlayerOptions(con, intDeck, intPlayer, intDealer, intPlayerCardCount, intDealerCardCount, intBet, intMoney);
							}
												
							// Check if player or dealer has 5 cards without busting
							blnGameWon = hadrieltoolsCPT.checkFiveCardWin(con, intPlayer, intPlayerCardCount, intPlayerValue, intDealer, intDealerCardCount, intDealerValue, intBet, intMoney);
							
							if(blnGameWon){
								// Exit the loop if the game is won
								break;
							}
						}
					
					// Ask if player wants to play again
					if (intMoney > 0 && !blnGameWon) {
						con.print("Would you like to play again? (y/n): ");
						strOption = con.readLine();
						con.clear();
						if (!strOption.equalsIgnoreCase("y")) {
							break;
						}
					} else {
						con.println("You are out of money! Game over.");
					}
					
					// Reset if player wants to play
					intPlayerCardCount = 0;
					intDealerCardCount = 0;
					hadrieltoolsCPT.shuffleDeck();
				}
				
				// Save player info to winners.txt
				winners.println(strName);
				winners.println(intMoney);
				winners.close();
			}else if(chrChoice == 'q'){
				con.clear();
				con.println("Thanks for playing! Final money: "+ intMoney); 
				blnContinue = false;
			}else if(chrChoice == 'v'){
				con.clear();
				TextInputFile winners2 = new TextInputFile("winners.txt");
				con.println("Here are the winners: ");
				while(winners2.eof() == false){
					strName = winners2.readLine();
					intMoney = winners2.readInt();
					con.println(strName);
					con.println(intMoney);
				}
				winners2.close();
				con.println("Press any key to return to the main menu... ");
				chrChoice = con.readChar();
				con.clear();
				
					
			}else if(chrChoice == 's'){
				con.clear();
				con.drawImage(imgFunnyJoke, 640, 360);
				break;
			}else{
				con.println("No high scores available");
			}		
		}
	}
}
