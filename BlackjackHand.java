/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCartas;

/**
 *
 * @author DELL
 */
public class BlackjackHand extends Hand {

    /**
     * Computes and returns the value of this hand in the game
     * of Blackjack.
     */
    public int obtenerValorBlackjack() {

        int val;      // El valor calculado para la mano.
        boolean as;  // Esto se pondrá en true si la mano contiene un as.
        int cartas;    // Numero de cartas en lamano.

        val = 0;
        as = false;
        cartas = obtenerConteoCartas();  // (metodo definido en la clase Hand.)

        for ( int i = 0;  i < cartas;  i++ ) {
            // Add the value of the i-th card in the hand.
            Card carta;    // The i-th card; 
            int valorCarta;  // El valor del blackjack de la carta i-ésimo.
            carta = obtenerCarta(i);
            valorCarta = carta.obtenerValor();  // El valor normal, 1 a 13.
            if (valorCarta > 10) {
                valorCarta = 10;   // Para una Jota, Reina, or Rey.
            }
            if (valorCarta == 1) {
                as = true;     // Hay al menos un as.
            }
            val = val + valorCarta;
        }

       // Ahora, val es el valor de la mano, contando cualquier as como 1.
        //Si hay un as, y si cambiar su valor de 1 a 11 dejaría la puntuación menor o igual a 21, entonces hazlo sumando los 10 puntos extra a val. 

        if ( as == true  &&  val + 10 <= 21 )
            val = val + 10;

        return val;

    }  // end getBlackjackValue()

} // end class BlackjackHand

