import java.util.*;

public class Functions {

    //Method to turn rank into int
    public static int compare(String a) {
        return switch (a) {
            case "TWO" -> 2;
            case "THREE" -> 3;
            case "FOUR" -> 4;
            case "FIVE" -> 5;
            case "SIX" -> 6;
            case "SEVEN" -> 7;
            case "EIGHT" -> 8;
            case "NINE" -> 9;
            case "TEN" -> 10;
            case "JACK" -> 11;
            case "QUEEN" -> 12;
            case "KING" -> 13;
            case "ACE" -> 14;
            default -> 0;
        };
    }

    //Method to end game when one of lists is empty
    public static void endGame(ArrayList<?> playerList, ArrayList<?> botList, ArrayList<?> durak) {

        if(playerList.isEmpty()){
            Game52.who(1);
            new CustomDialogExample(2);

        }
        else if(botList.isEmpty() || durak.isEmpty()){
            Game52.who(2);
            new CustomDialogExample(2);

        }


    }

    public static void endGame(ArrayList<?> playerList, ArrayList<?> botList) {

        if(playerList.isEmpty()){
            MainBeta2.who(true);
            new CustomDialogExample(1);

        }
        else if(botList.isEmpty()){
            MainBeta2.who(false);
            new CustomDialogExample(1);

        }


    }



    public static void give(Deck deck, List<String> list) {

        Card card = deck.drawCard();
        list.add(String.valueOf(card));


    }

    //Method to sort cards
    public static class CardComparator implements Comparator<String> {
        @Override
        public int compare(String card1, String card2) {//Checks which card is trumps
            boolean isTrump1 = card1.contains(Variables.getTrump());
            boolean isTrump2 = card2.contains(Variables.getTrump());

            if (isTrump1 && isTrump2) {
                return compareRank(card1, card2);
            } else if (isTrump1) {
                return 1;
            } else if (isTrump2) {
                return -1;
            } else {
                return compareRank(card1, card2);
            }
        }

        private int compareRank(String card1, String card2) {//Compares ranks
            //splits the cards into rank and suit
            String[] parts1 = card1.split("\\s+");
            String[] parts2 = card2.split("\\s+");
            String rank1 = parts1[0];
            String rank2 = parts2[0];
            String suit1 = parts1[1];
            String suit2 = parts2[1];

            //comparing ranks
            int rankComparison = Integer.compare(rankValue(rank1), rankValue(rank2));
            if (rankComparison != 0) {
                return rankComparison;
            } else {
                return suit1.compareTo(suit2);
            }
        }

        private int rankValue(String rank) {//Method to turn rank into int
            return switch (rank) {
                case "TWO" -> 2;
                case "THREE" -> 3;
                case "FOUR" -> 4;
                case "FIVE" -> 5;
                case "SIX" -> 6;
                case "SEVEN" -> 7;
                case "EIGHT" -> 8;
                case "NINE" -> 9;
                case "TEN" -> 10;
                case "JACK" -> 11;
                case "QUEEN" -> 12;
                case "KING" -> 13;
                case "ACE" -> 14;
                default -> 0;
            };
        }
    }



    public static void refresh(int a){//Refreshes images
        Game52.updateImages();
        Game52.waitForNextClick();
}

    public static void refresh(){//Refreshes images
        MainBeta2.updateImages();
        MainBeta2.waitForNextClick();
    }
}
