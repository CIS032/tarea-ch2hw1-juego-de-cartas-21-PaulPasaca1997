/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCartas;


/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> mano;   // Las cartas en la mano.

    /**
     * Create a hand that is initially empty.
     */
    public Hand() {
        mano = new ArrayList<Card>();
    }

    /**
     * Retire todas las cartas de la mano, dejándola vacía.
     */
    public void vacio() {
        mano.clear();
    }

    /**
     Añade una carta a la mano.  Se añade al final de la mano actual.
     @param c la tarjeta no nula a añadir.
     NullPointerException si el parámetro c es nulo.
     */
    public void agregarCarta(Card c) {
        if (c == null)
            throw new NullPointerException("No se puede añadir una carta nula a una mano.");
        mano.add(c);
    }

    /**
     * Remove a card from the hand, if present.
     * @param c the card to be removed.  If c is null or if the card is not in 
     * the hand, then nothing is done.
     */
    public void quitarCarta(Card c) {
        mano.remove(c);
    }

    /**
     Retire la tarjeta en una posición especificada de la mano.
     @param posiciona la posición de la tarjeta que se va a retirar, donde las posiciones comienzan desde cero.
     Si la posición no existe en la mano, es decir, si la posición es menor que 0 o mayor que o igual al número de cartas en la mano.
     */
    public void eliminarCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size())
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        mano.remove(posicion);
    }

    /**
     * Retorna el numero de cartas en la mano.
     */
    public int obtenerConteoCartas() {
        return mano.size();
    }

    /**
     * Gets the card in a specified position in the hand.  (Note that this card
     * is not removed from the hand!)
     * @param position the position of the card that is to be returned
     * @throws IllegalArgumentException if position does not exist in the hand
     */
    public Card obtenerCarta(int position) {
        if (position < 0 || position >= mano.size())
            throw new IllegalArgumentException("La posición no existe en la mano: "
                    + position);
        return mano.get(position);
    }

    /**
     * Sorts the cards in the hand so that cards of the same suit are
     * grouped together, and within a suit the cards are sorted by value.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void ordenarPorPalo() {
        ArrayList<Card> nuevaMano = new ArrayList<Card>();
        while (mano.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = mano.get(0);  // Minimal card.
            for (int i = 1; i < mano.size(); i++) {
                Card c1 = mano.get(i);
                if ( c1.obtenerPalo() < c.obtenerPalo() || (c1.obtenerPalo() == c.obtenerPalo() && c1.obtenerValor()< c.obtenerValor()) ) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    /**
     * Sorts the cards in the hand so that cards of the same value are
     * grouped together.  Cards with the same value are sorted by suit.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void ordenarPorValor() {
        ArrayList<Card> nuevaMano = new ArrayList<Card>();
        while (mano.size() > 0) {
            int pos = 0;  // Pisicion de la carta minima
            Card c = mano.get(0);  // Carta minima
            for (int i = 1; i < mano.size(); i++) {
                Card c1 = mano.get(i);
                if ( c1.obtenerValor()< c.obtenerValor()||
                        (c1.obtenerValor() == c.obtenerValor() && c1.obtenerPalo()< c.obtenerPalo()) ) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

}

