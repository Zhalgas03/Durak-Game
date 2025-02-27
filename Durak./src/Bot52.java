public class Bot52 extends GameParticipant<Integer> {// The Player class extends the GameParticipant class
    @Override// Override the abstract playCard method from GameParticipant
    public void playCard(Integer card) {
    String x = null;
    String p = Variables.getBot().remove(0);//bot throw the smallest card
    Variables.getBot().trimToSize();


    //splits bots card
    String[] words = p.split("\\s+");
    boolean match = false;
    String w = words[1];
    String n = words[0];
    int n1 = compare(n);//rank into int
    int j,nv;
        for (j = 0; j < Variables.dList().size(); j++) {
            String element = Variables.dList().get(j);
            if (element.contains(w)) {
                String[] Bwords = element.split("\\s+");
                String v = Bwords[0];
                nv = compare(v);
                if (nv > n1) {
                    match = true;
                    x=element;
                    break;
                }
            }
        }
        //checks if bot has trump and can beat card
        if (!match) {
            for (j = 0; j < Variables.dList().size(); j++) {
                String element = Variables.dList().get(j);
                if (element.contains(Variables.getTrump()) && !p.contains(Variables.getTrump())) {
                    match = true;
                    x=element;
                    break;
                }
            }
        }


    if (Game52.deck.size() > 0 && Variables.getBot().size() < 6) {
        give(Game52.deck, Variables.getBot());
    }

    if (match) {//if player can beat bots card
        Variables.dList().remove(String.valueOf(x));
        Variables.dList().trimToSize();
        if (Variables.dList().size() < 6 && Game52.deck.size() > 0) {//gives card to player
            Card card1 = Game52.deck.drawCard();
            Variables.dList().add(String.valueOf(card1));
        }

        Game52.setTurn(card+1);
    } else {//othewise takes card and bot moves again
        Variables.dList().add(p);
        Game52.setTurn(card-1);
    }

    //sorts cards
    Variables.getList().sort(new CardComparator());
    Variables.getBot().sort(new CardComparator());
    Variables.dList().sort(new CardComparator());

    endGame(Variables.getList(), Variables.getBot(), Variables.dList());



}

}

