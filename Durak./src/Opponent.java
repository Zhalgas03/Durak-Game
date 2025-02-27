public class Opponent extends GameParticipant<Integer> {// The Player class extends the GameParticipant class
    @Override// Override the abstract playCard method from GameParticipant
    public void playCard(Integer card) {
        int index = Game52.clickedImageIndex;//Get the index of the clicked image
        String p = Variables.dList().remove(0);//bot throw the smallest card
        Variables.dList().trimToSize();
        String a = Variables.getList().remove(index);
        Variables.getList().trimToSize();

        //splits bots card
        String[] words = p.split("\\s+");
        boolean match = false;
        String w = words[1];
        String n = words[0];
        int n1 = compare(n);//rank into int

        //player have trump and bot dots not
        if (a.contains(Variables.getTrump()) && !p.contains(Variables.getTrump())) {
            match = true;
        }

        //check if player has higher rank
        if (a.contains(w)) {
            String[] Bwords = a.split("\\s+");
            String v = Bwords[0];
            int nv = compare(v);
            if (nv > n1) {
                match = true;
            }
        }

        //gives card to bot
        if (Game52.deck.size() > 0 && Variables.dList().size() < 6) {
            give(Game52.deck, Variables.dList());
        }

        if (match) {//if player can beat bots card
            if (Variables.getList().size() < 6 && Game52.deck.size() > 0) {//gives card to player
                Card card1 = Game52.deck.drawCard();
                Variables.getList().add(String.valueOf(card1));
            }
            Game52.setTurn(card-2);

        } else {//othewise takes card and bot moves again
            Variables.getList().add(a);
            Variables.getList().add(p);
            if(Variables.getBot().isEmpty()) Game52.setTurn(card);
            else Game52.setTurn(card-1);
        }

        //sorts cards
        Variables.getList().sort(new CardComparator());
        Variables.getBot().sort(new CardComparator());
        Variables.dList().sort(new CardComparator());
        endGame(Variables.getList(), Variables.getBot(), Variables.dList());

    }

}

