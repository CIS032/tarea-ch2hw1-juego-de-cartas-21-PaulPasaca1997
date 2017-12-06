/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCartas;

/**
 *  An object of type Deck represents a deck of playing cards.  The deck
 *  is a regular poker deck that contains 52 regular cards and that can
 *  also optionally include two Jokers.
 */
public class Deck {

    /**
     * An array of 52 or 54 cards.  A 54-card deck contains two Jokers,
     * in addition to the 52 cards of a regular poker deck.
     */
    private Card[] mazo;

    /**
     * Keeps track of the number of cards that have been dealt from
     * the deck so far.
     */
    private int cartasUsadas;

    /**
     * Constructs a regular 52-card poker deck.  Initially, the cards
     * are in a sorted order.  The shuffle() method can be called to
     * randomize the order.  (Note that "new Deck()" is equivalent
     * to "new Deck(false)".)
     */
    public Deck() {
        this(false);  // Just call the other constructor in this class.
    }

    /**
     * Constructs a poker deck of playing cards, The deck contains
     * the usual 52 cards and can optionally contain two Jokers
     * in addition, for a total of 54 cards.   Initially the cards
     * are in a sorted order.  The shuffle() method can be called to
     * randomize the order.
     * @param includeJokers if true, two Jokers are included in the deck; if false,
     * there are no Jokers in the deck.
     */
    public Deck(boolean incluyeComodines) {
        if (incluyeComodines)
            mazo = new Card[54];
        else
            mazo = new Card[52];
        int cardCt = 0; // How many cards have been created so far.
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 1; value <= 13; value++ ) {
                mazo[cardCt] = new Card(value,suit);
                cardCt++;
            }
        }
        if (incluyeComodines) {
            mazo[52] = new Card(1,Card.JOKER);
            mazo[53] = new Card(2,Card.JOKER);
        }
        cartasUsadas = 0;
    }

    /**
     * Vuelva a colocar todas las cartas usadas en el mazo (si las hay) y barajee el mazo en un orden aleatorio.
     */
    public void barajar() {
        for ( int i = mazo.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = mazo[i];
            mazo[i] = mazo[rand];
            mazo[rand] = temp;
        }
        cartasUsadas= 0;
    }

    /**
     * As cards are dealt from the deck, the number of cards left
     * decreases.  This function returns the number of cards that
     * are still left in the deck.  The return value would be
     * 52 or 54 (depending on whether the deck includes Jokers)
     * when the deck is first created or after the deck has been
     * shuffled.  It decreases by 1 each time the dealCard() method
     * is called.
     */
    public int cartasUsadas() {
        return mazo.length - cartasUsadas;
    }

    /**
     Elimina la siguiente carta del mazo y devuélvela.  Es ilegal llamar a este método si no hay más cartas en el mazo. 
     * Puede comprobar el número de tarjetas restantes llamando a la función cardsLeft ().
     Devolver la carta que se retira del mazo.
     @throws IlegalEstadoIlegalExcepción si no quedan cartas en la baraja
     */
    public Card cartaReparto() {
        if (cartasUsadas == mazo.length)
            throw new IllegalStateException("No quedan cartas en la baraja.");
        cartasUsadas++;
        return mazo[cartasUsadas - 1];
        // Programming note:  Cards are not literally removed from the array
        // that represents the deck.  We just keep track of how many cards
        // have been used.
    }

    /**
     * Test whether the deck contains Jokers.
     * @return true, if this is a 54-card deck containing two jokers, or false if
     * this is a 52 card deck that contains no jokers.
     */
    public boolean tieneJokers() {
        return (mazo.length == 54);
    }

} // end class Deck
