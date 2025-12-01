package tp_final;
import java.util.Random;

public class BusquedaParalela {
    final static int ARR_SIZE = 100000000;
    // final static int CANT_THREADS = ARR_SIZE/25000000;
    final static int CANT_THREADS = 10;
    public static void main(String[] args) {
        Random rand = new Random();
        double time = System.nanoTime();
        int arr[] = new int[ARR_SIZE];
        int obj = 1000;

        int posObj = rand.nextInt(ARR_SIZE);

        //relleno el arreglo con rand entre 0-9 y a la posObj le pongo 100 (buscado)
        for (int i = 0; i < arr.length; i++) {
            if(i == posObj){
                arr[i] = 1000;
            }else{
                arr[i] = rand.nextInt(10);
            }
        }

        int pos = busqueda(arr, obj);
        String out = "";
        out = (pos == -1) ? " no encontrado" : " encontrado en pos: " + pos;

        System.out.println("Cantidad de hilos: " + CANT_THREADS);
        System.out.println("Tiempo de ejecucion: " + ((System.nanoTime() - time) / 1000000) + "ms");
        System.out.println("Elemento " + obj + out);
    }

    public static int busqueda(int[] arr, int obj){
        //int pos = -1;
        Busqueda[] hilosBusqueda = new Busqueda[CANT_THREADS];
        ThreadFlag tf = new ThreadFlag();

        for (int i = 0; i < CANT_THREADS; i++) {
            int min = i*(ARR_SIZE/CANT_THREADS);
            int max = ((i + 1) * (ARR_SIZE / CANT_THREADS) - 1);
            hilosBusqueda[i] = new Busqueda(arr, obj, min, max, tf);
            System.out.println("Min: " + min + " Max: " + max);
            hilosBusqueda[i].start();
        }

        // for (int i = 0; i < CANT_THREADS; i++) {
        //     try {
        //         hilosBusqueda[i].join();
        //         int parcial = hilosBusqueda[i].getPos();
        //         //System.out.println("PAR: " + parcial +  " POS: " + pos);
        //         if (parcial != -1) {
        //             pos = parcial;
        //             for(int j = 0; i<j-1; j++){
        //                 hilosBusqueda[i].interrupt();
        //             }
        //             break;
        //         }
                
        //     } catch (Exception e) {
        //     }            
        // }

        for (Busqueda busqueda : hilosBusqueda) {
            try {
                busqueda.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return tf.posicion;
    }
}