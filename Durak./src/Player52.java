public class Player52 extends GameParticipant<Integer> {// The Player class extends the GameParticipant class
    // Override the abstract playCard method from GameParticipant
    @Override
    public void playCard(Integer card) { // Implementation of the playCard method
        int index = Game52.clickedImageIndex;// Get the index of the clicked image

        //remove and store card from player list and trim to size
        String a = Variables.getList().remove(index);
        Variables.getList().trimToSize();

        //splits into rank and suit
        String[] words = a.split("\\s+");
        boolean found = false;
        String w = words[1];
        String n = words[0];
        int n1 = compare(n);//makes rank into int
        int i, nv;

        //find stronger card in bot list
        for (i = 0; i < Variables.getBot().size(); i++) {
            String element = Variables.getBot().get(i);
            if (element.contains(w)) {
                String[] Bwords = element.split("\\s+");
                String v = Bwords[0];
                nv = compare(v);
                if (nv > n1) {
                    found = true;
                    break;
                }
            }
        }
        //checks if bot has trump and can beat card
        if (!found) {
            for (i = 0; i < Variables.getBot().size(); i++) {
                String t = Variables.getBot().get(i);
                if (t.contains(Variables.getTrump()) && !a.contains(Variables.getTrump())) {
                    found = true;
                    break;
                }
            }
        }
        //gives cards after each round
        if (Game52.deck.size() > 0 && Variables.getList().size() < 6) {
            give(Game52.deck, Variables.getList());
        }

        if (found) {//if bot beat card
            Variables.getBot().remove(i);
            Variables.getBot().trimToSize();
            //adds card to bot list if bot has less than 6 cards
            if (Variables.getBot().size() < 6 && Game52.deck.size() > 0) {
                Card card1 = Game52.deck.drawCard();
                Variables.getBot().add(String.valueOf(card1));
            }
            Game52.setTurn(card + 1);


        } else {//if bot can't beat card
            Variables.getBot().add(a);

            Game52.setTurn(card + 2);
        }
        //sort cards
        Variables.getList().sort(new CardComparator());
        Variables.getBot().sort(new CardComparator());
        Variables.dList().sort(new CardComparator());
        endGame(Variables.getList(), Variables.getBot(), Variables.dList());
    }



}

