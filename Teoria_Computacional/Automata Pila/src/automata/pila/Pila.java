package automata.pila;
import java.util.ArrayList;

public class Pila {

    private ArrayList<Pila> pila;
    private int top = 0;
    private char z = 'Z';

    public void P11_Stack() {
        pila = new ArrayList<>();
        initStack();
    }

    public void initStack() {
        pila.add(new aut(z));
    }

    public void push(char caracter) {
        pila.add(new aut(caracter));
    }

    public char pop() {
        char aux = ' ';
        if (pila.isEmpty()) {
            System.out.println("There's nothing");
            System.exit(0);
        } else {
            aux = pila.get(pila.size() - 1).getElement();
            pila.remove(pila.size() - 1);
        }
        return aux;
    }

    public char top() {
        return pila.get(pila.size() - 1).getElement();
    }

    public boolean isEmpty() {
        return (pila.get(pila.size() - 1).getElement() == 'Z');
    }

    public void setTop(char character) {
        this.z = character;
    }
}
