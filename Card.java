
package JuegoCartas;

/**
 Un objeto de tipo Carta representa una carta de juego de un
 baraja de póquer estándar, incluyendo a los comodines.  La tarjeta tiene un traje, que
 pueden ser picas, corazones, diamantes, palos o comodines.  Una espada, corazón,
 diamante, o el palo tiene uno de los 13 valores: as, 2,3,4,5,6,7,
 8,9,10, Jack, reina o rey.  Obsérvese que se considera que "as" es
 el valor más pequeño.  Un comodín también puede tener un valor asociado; 
 este valor puede ser cualquier cosa y se puede utilizar para mantener un registro de varios
 diferentes bromistas.
 */
public class Card {

    public final static int ESPADAS = 0;   // Códigos para los 4 palos, más Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int TREBOLES = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;      // Codigos para las cartas no numericas
    public final static int JOTA = 11;    //   Cards 2 through 10 have their     JACK=jota
    public final static int REINA = 12;   //   numerical values for their codes.
    public final static int REY = 13;

    /**
     * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
     * CLUBS, or JOKER.  The suit cannot be changed after the card is
     * constructed.
     */
    private final int palo; 

    /**
     El valor de la tarjeta.  Para una tarjeta normal, éste es uno de los valores del 1 al 13, 
     * con 1 representando AS.  Para un JOKER, el valor puede ser cualquier cosa.  
     * El valor no se puede cambiar después de construir la tarjeta.
     */
    private final int valor;

    /**
     Crea un Joker, con 1 como valor asociado.  (Nótese que
     * "new Card ()" equivale a "new Card (1, Card. JOKER)".
     */
    public Card() {
        palo = JOKER;
        valor = 1;
    }

    /**
     Crea una tarjeta con un palo y un valor especificados.
     Valor del valor de la nueva tarjeta.  Para una carta regular (no bufón), el valor debe estar en el rango de 1 a 13, con 1 representando un As. Puedes usar las constantes ACE, Card. JACK, Card. QUEEN y Card. KING.  Para un Joker, el valor puede ser cualquier cosa.
     @param theSuit el palo de la nueva tarjeta.  Este debe ser uno de los valores
     Tarjeta. CARTAS, CARTAS, CORAZONES, TARJETAS. DIAMANTES, CARTAS. TREBOLES, o TARJETA. JOKER.
     @throws IllegalArgumentException si los valores de los parámetros no están en los rangos permitidos
     */
    public Card(int theValue, int theSuit) {
        if (theSuit != ESPADAS && theSuit != CORAZONES && theSuit != DIAMANTES && 
                theSuit != TREBOLES && theSuit != JOKER)
            throw new IllegalArgumentException("Juego de naipes ilegal");
        if (theSuit != JOKER && (theValue < 1 || theValue > 13))
            throw new IllegalArgumentException("Valor de la carta de juego ilegal");
        valor = theValue;
        palo = theSuit;
    }

    /**
     * Returns the suit of this card.
     * @returns the suit, which is one of the constants Card.SPADES, 
     * Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER
     */
    public int obtenerPalo() {
        return palo;
    }

    /**
     Devuelve el valor de esta carta.
     Devuelve el valor, que es uno de los números del 1 al 13, incluido para
     una tarjeta normal, y que puede ser cualquier valor para un Joker.
     */
    public int obtenerValor() {
        return valor;
    }

    /**
     Devuelve una representación String del palo de la carta.
 devuelve una de las cadenas "Espadas","Corazones","Diamantes","Treboles" o "Joker". lue para un Joker.
     */
    public String obtenerPaloComoCadena() {
        switch ( palo ) {
        case ESPADAS:    return "Espadas";
        case CORAZONES:  return "Corazones";
        case DIAMANTES:  return "Diamantes";
        case TREBOLES:   return "Tréboles";
        default:         return "Joker";
        }
    }

    /**
     * Returns a String representation of the card's value.
     * @return for a regular card, one of the strings "Ace", "2",
     * "3", ..., "10", "Jack", "Queen", or "King".  For a Joker, the 
     * string is always numerical.
     */
    public String obtenerValorComoCadena() {
        if (palo == JOKER)
            return "" + valor;
        else {
            switch ( valor ) {
            case 1:   return "As";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jota";
            case 12:  return "Reina";
            default:  return "Rey";
            }
        }
    }

    /**
     * Returns a string representation of this card, including both
     * its suit and its value (except that for a Joker with value 1,
     * the return value is just "Joker").  Sample return values
     * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades",
     * "Joker", "Joker #2"
     */
    public String toString() {
        if (palo == JOKER) {
            if (valor == 1)
                return "Joker";
            else
                return "Joker #" + valor;
        }
        else
            return obtenerValorComoCadena() + " de " + obtenerPaloComoCadena();
    }


} // end class Card
