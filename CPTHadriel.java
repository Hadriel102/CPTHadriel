import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTHadriel {
    public static void main(String[] args) {
        Console con = new Console("Blackjack", 1280, 720);
        BufferedImage imgBlackJack = con.loadImage("Blackjack4.jpg");

        // Draw image
        con.drawImage(imgBlackJack, 0, 0);

        // Variables
        int[][] intDeck;
        int[][] intPlayer;
        int[][] intDealer;
        String strName;
        int intMoney = 1000;
        int intBet;
        int intCount;
        int intPlayerCardCount = 0;
        int intDealerCardCount = 0;
        String strOption = "";
        String strLine1 = "Enter P to play";
        String strLine2 = "(v)iew high scores";
        String strLine3 = "(q)uit";
        char chrChoice;
        int intPlayerValue = 0;
        int intDealerValue = 0;

        // Array for deck
        intDeck = new int[52][3];

        // Main menu
        con.drawString(strLine1, 450, 360);
        con.drawString(strLine2, 450, 380);
        con.drawString(strLine3, 450, 400);
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

                // Enter bet
                con.print("Enter bet amount: ");
                intBet = con.readInt();
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
                        }
                    } else {
                        // Ask user hit or stand
                        con.println("");
                        con.println("Hit or Stand: ");
                        strOption = con.readLine();
                        con.clear();

                        // If user hits
                        while (strOption.equalsIgnoreCase("hit")) {
                            hadrieltoolsCPT.dealCard(intDeck, intPlayer, intPlayerCardCount);
                            intPlayerCardCount++;
                            con.println("Player's new card: ");
                            hadrieltoolsCPT.printHand(con, intPlayer, intPlayerCardCount);
                            if (hadrieltoolsCPT.handValue(intPlayer, intPlayerCardCount) > 21) {
                                con.println("Player busts! You lose!");
                                con.println("Your money: " + intMoney);
                                break;
                            }
                            con.println("");
                            con.println("Hit or stand: ");
                            strOption = con.readLine();
                            con.clear();
                        }

                        // If user stands
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
                        }
                    }
                }

                // Ask if player wants to play again
                if (intMoney > 0) {
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
        }

        con.println("Thanks for playing! Final money: " + intMoney);
    }
}
