package Ejercicio_2;

public class DoubleLinkedList {
    static class DobleNodo<E> {
        E dato;
        DobleNodo<E> sig;
        DobleNodo<E> ant;

        public DobleNodo(E dato) {
            this.dato = dato;
            this.sig = null;
            this.ant = null;
        }
    }

    static class DoubleList<E> {
        DobleNodo<E> cab;
        DobleNodo<E> fin;

        public DoubleList() {
            this.cab = null;
            this.fin = null;
        }

        public void insertarIzquierda(E dato) {
            DobleNodo<E> nuevo = new DobleNodo<>(dato);
            if (cab == null) {
                cab = nuevo;
                fin = nuevo;
                nuevo.sig = null;
                nuevo.ant = null;
            } else {
                nuevo.sig = cab;
                cab.ant = nuevo;
                nuevo.ant = null;
                cab = nuevo;
            }
        }

        public void insertarDerecha(E dato) {
            DobleNodo<E> nuevo = new DobleNodo<>(dato);
            if (cab == null) {
                cab = nuevo;
                fin = nuevo;
                nuevo.sig = null;
                nuevo.ant = null;
            } else {
                nuevo.sig = null;
                nuevo.ant = fin;
                fin.sig = nuevo;
                fin = nuevo;
            }
        }

        public void insertarIndice(int indice, E dato) {
            DobleNodo<E> nuevo = new DobleNodo(dato);
            if (indice == 0) {
                cab = nuevo;
                fin = nuevo;
                nuevo.sig = null;
                nuevo.ant = null;
            } else {
                DobleNodo<E> prox;
                DobleNodo<E> prev;
                int i = 0;
                for (prox = cab; prox.sig != null && i < indice - 1; prox = prox.sig)
                    i++;
                prev = prox.ant;
                prev.sig = nuevo;
                nuevo.ant = prev;
                nuevo.sig = prox;
                prox.ant = nuevo;
            }
        }

        public void borrarIndice(int indice) {
            DobleNodo<E> prox;
            DobleNodo<E> prev;
            if (indice == 0) {
                if (this.size() > 1) {
                    prox = cab;
                    cab = cab.sig;
                    prox = null;
                    cab.ant = null;
                } else {
                    cab.sig = null;
                    cab.ant = null;
                    cab = null;
                }
            } else if (indice == this.size() - 1) {
                DobleNodo<E> aux = fin;
                fin = aux.ant;
                fin.sig = null;
                aux = null;
            } else {
                int i = 0;
                DobleNodo<E> aux;
                for (prox = cab; prox.sig != null && i < indice; prox = prox.sig)
                    i++;
                aux = prox.sig;
                prev = prox.ant;
                prev.sig = aux;
                aux.ant = prev;
                prox = null;
            }
        }

        public int size() {
            int contador = 1;
            DobleNodo<E> aux = cab;
            for (aux = cab; aux.sig != null; aux = aux.sig)
                contador++;
            return contador;
        }

        public E get(int indice) {
            DobleNodo<E> aux = null;
            if (indice < this.size()) {
                int i = 0;
                for (aux = cab; aux.sig != null && i < indice; aux = aux.sig)
                    i++;
                return (E) aux.dato;
            } else {
                return null;
            }
        }

        public void recorridoListaDerecha() {
            DobleNodo<E> aux = cab;
            while (aux != null) {
                System.out.print(aux.dato + "->");
                aux = aux.sig;
            }
            System.out.print("\n");
        }

        public void recorridoListaIzquierda() {
            DobleNodo<E> aux = fin;
            while (aux != null) {
                System.out.print(aux.dato + "->");
                aux = aux.ant;
            }
            System.out.print("\n");
        }

        public boolean isEmpty() {
            return cab == null;
        }
    }

    public static boolean esPalindromo(int numero) {
        int original = numero;
        int invertido = 0;

        while (numero > 0) {
            int digito = numero % 10;
            invertido = invertido * 10 + digito;
            numero /= 10;
        }

        return original == invertido;
    }

    public static void mostrarPalindromos(DoubleList<Integer> lista) {
        DobleNodo<Integer> nodo = lista.cab;

        while (nodo != null) {
            int dato = nodo.dato;
            if (esPalindromo(dato)) {
                System.out.println(dato + " es un número palíndromo.");
            }
            nodo = nodo.sig;
        }
    }

    public static void main(String[] args) {
        DoubleList<Integer> listado = new DoubleList<>();
        listado.insertarIzquierda(79797);
        listado.insertarIzquierda(2132);
        listado.insertarIzquierda(11111);
        mostrarPalindromos(listado);
    }
}
