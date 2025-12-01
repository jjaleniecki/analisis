package tp_final;

public class ThreadFlag {
    //necesario que sea una clase porque se almacena en heap y permite compartirse entre hilos
    public volatile boolean encontrado = false;
    public volatile int posicion = -1;
}
