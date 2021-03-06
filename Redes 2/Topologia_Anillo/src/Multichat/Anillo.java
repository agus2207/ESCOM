/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat;

/**
 *
 * @author HP
 */
public class Anillo {
    // Puntero que indica el inicio de la lista o conocida tambien
    // como cabeza de la lista.
    private Nodo inicio;
    // Puntero que indica el final de la lista o el ultimo nodo.
    private Nodo ultimo;
    // Variable para registrar el tamaño de la lista.
    private int tamanio;
    /**
     * Constructor por defecto.
     */
    public void Lista(){
        inicio = null;
        ultimo = null;
        tamanio = 0;
    }
    /**
     * Consulta si la lista esta vacia.
     * @return true si el primer nodo (inicio), no apunta a otro nodo.
     */
    public boolean esVacia(){
        return inicio == null;
    }
    /**
     * Consulta cuantos elementos (nodos) tiene la lista.
     * @return numero entero entre [0,n] donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getTamanio(){
        return tamanio;
    }
    /**
     * Agrega un nuevo nodo al final de la lista circular.
     * @param valor a agregar.
     */
    public void agregarAlFinal(String valor){
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // De igual forma el ultimo nodo sera el nuevo.
            ultimo = nuevo;
            // Y el puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
        // Caso contrario el nodo se agrega al final de la lista.
        } else{
            // Apuntamos con el ultimo nodo de la lista al nuevo.
            ultimo.setSiguiente(nuevo);
            // Apuntamos con el nuevo nodo al inicio de la lista.
            nuevo.setSiguiente(inicio);
            // Como ahora como el nuevo nodo es el ultimo se actualiza
            // la variable ultimo.
            ultimo = nuevo;
        }
        // Incrementa el contador de tamaño de la lista
        tamanio++;
    }
    /**
     * Agrega un nuevo nodo al inicio de la lista circular.
     * @param valor a agregar.
     */   
    public void agregarAlInicio(String valor){
        // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
        // Consulta si la lista esta vacia.
        if (esVacia()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // De igual forma el ultimo nodo sera el nuevo.
            ultimo = nuevo;  
            // Y el puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
        // Caso contrario va agregando los nodos al inicio de la lista.
        } else{
            // Une el nuevo nodo con la lista existente.
            nuevo.setSiguiente(inicio);
            // Renombra al nuevo nodo como el inicio de la lista.
            inicio = nuevo;
            // El puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
        }
        // Incrementa el contador de tamaño de la lista.
        tamanio++;
    }
 
    /**
     * Inserta un nuevo nodo despues en una posición determinada.
     * @param posicion en la cual se va a insertar el nuevo nodo.
     * @param valor valor del nuevo nodo de la lista.
     */
    public void insrtarPorPosicion(int posicion,String valor){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y <= que el numero de elementos del la lista.
         if (esVacia() && posicion==0) {
              // Define un nuevo nodo.
        Nodo nuevo = new Nodo();
        // Agrega al valor al nodo.
        nuevo.setValor(valor);
            // Inicializa la lista agregando como inicio al nuevo nodo.
            inicio = nuevo;
            // De igual forma el ultimo nodo sera el nuevo.
            ultimo = nuevo;  
            // Y el puntero del ultimo debe apuntar al primero.
            ultimo.setSiguiente(inicio);
        // Caso contrario va agregando los nodos al inicio de la lista.
        } 
        if(posicion>=0 && posicion<=tamanio){
            Nodo nuevo = new Nodo();
            nuevo.setValor(valor);
            // Consulta si el nuevo nodo a ingresar va al inicio de la lista.
            if(posicion == 0){
                // Une el nuevo nodo con la lista existente.
                nuevo.setSiguiente(inicio);
                // Renombra al nuevo nodo como el inicio de la lista.
                inicio = nuevo;
                // El puntero del ultimo debe apuntar al primero.
                ultimo.setSiguiente(inicio);
                tamanio++;
            }
            else{
                // Si el nodo a inserta va al final de la lista.
                if(posicion == tamanio){
                    // Apuntamos con el ultimo nodo de la lista al nuevo.
                    ultimo.setSiguiente(nuevo);
                    // Apuntamos con el nuevo nodo al inicio de la lista.
                    nuevo.setSiguiente(inicio);
                    // Como ahora como el nuevo nodo es el ultimo se actualiza
                    // la variable ultimo.
                    ultimo = nuevo;     
                }
                else{
                    // Si el nodo a insertar va en el medio de la lista.
                    Nodo aux = inicio;
                    // Recorre la lista hasta llegar al nodo anterior
                    // a la posicion en la cual se insertara el nuevo nodo.
                    for (int i = 0; i < (posicion-1); i++) {
                        aux = aux.getSiguiente();
                    }
                    // Guarda el nodo siguiente al nodo en la posición
                    // en la cual va a insertar el nevo nodo.
                    Nodo siguiente = aux.getSiguiente();
                    // Inserta el nuevo nodo en la posición indicada.
                    aux.setSiguiente(nuevo);
                    // Une el nuevo nodo con el resto de la lista.
                    nuevo.setSiguiente(siguiente);
                }
            }
            // Incrementa el contador de tamaño de la lista.
            tamanio++;
        }
    }
    /**
     * Obtiene el valor de un nodo en una determinada posición.
     * @param posicion del nodo que se desea obtener su valor.
     * @return un numero entero entre [0,n-1] n = numero de nodos de la lista.
     * @throws Exception
     */
    public String getValor(int posicion) throws Exception{
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion<tamanio){
            // Consulta si la posicion es el inicio de la lista.
            if (posicion == 0) {
                // Retorna el valor del inicio de la lista.
                return inicio.getValor();
            }else{
                // Crea una copia de la lista.
                Nodo aux = inicio;
                // Recorre la lista hasta la posición ingresada.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                // Retorna el valor del nodo.
                return aux.getValor();
            }
        // Crea una excepción de Posicion inexistente en la lista.
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    /**
     * Busca si existe un valor en la lista.
     * @param referencia valor a buscar.
     * @return true si existe el valor en la lista.
     */
 
   
    /**
     * Actualiza el valor de un nodo que se encuentre en la lista ubicado
     * por un valor de referencia.
     * @param referencia valor del nodo el cual se desea actualizar.
     * @param valor nuevo valor para el nodo.
     */
  
  
    
    
    public void listar(){
        // Verifica si la lista contiene elementoa.
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
        
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do{
                // Imprime en pantalla el valor del nodo.
                System.out.print("\n"+"[ " + aux.getValor() + " ]");
                // Avanza al siguiente nodo.
                aux = aux.getSiguiente();
                // Incrementa el contador de la posión.
                i++;
            }while(aux != inicio);
        }
    }
}