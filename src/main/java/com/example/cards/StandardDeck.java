package com.example.cards;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StandardDeck implements Deck{

    List<Card> cards;

    public StandardDeck() {
        this.cards = newDeck();
    }

    private List<Card> newDeck() {
        List<Card> newDeck = new ArrayList<>();

        Arrays.stream(Suit.values()).forEach(suit -> {
            Arrays.stream(FaceValue.values())
                    .map(faceValue -> new Card(suit, faceValue))
                    .forEach(newDeck::add);

        });

        return newDeck;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public void cut(int index) {
        List<Card> top = this.cards.subList(0, index);
        List<Card> bottom = this.cards.subList(index, this.cards.size());

        this.cards.clear();

        this.cards.addAll(bottom);
        this.cards.addAll(top);
    }

    @Override
    public Card deal() {
        return this.cards.remove(0);
    }

    @Override
    public Card turnOver() {
        return this.cards.get(0);
    }

    @Override
    public int search(Card card) {
        return this.cards.indexOf(card);
    }

    @Override
    public void newOrder(Deck cards) {
        Collections.sort(this.cards);
    }

    @Override
    public int size() {
        return this.cards.size();
    }

    @Override
    public int newSize() throws OperationNotSupportedException {
        return Deck.super.newSize();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StandardDeck {");
        sb.append("cards=").append(cards);
        sb.append('}');
        return sb.toString();
    }
}
