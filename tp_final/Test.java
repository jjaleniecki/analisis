package tp_final;
import java.util.*;
import java.util.concurrent.*;

public class Test {
    final static int ARR_SIZE = 10000000;
    final static int CANT_THREADS = ARR_SIZE/2500000;
    public static void main(String[] args) {
        double time = System.nanoTime();
        Random rand = new Random();
        long suma = 0;
        int[] arr = new int[ARR_SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(CANT_THREADS);
        //ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
        System.out.println("Hilos: " + CANT_THREADS + " numeros: " + ARR_SIZE);
        List <Callable<Integer>> sumas = new ArrayList<>();
        List <Future<Integer>> futurasSumas = new ArrayList<>();
        
        try {
            for (int i = 0; i < CANT_THREADS; i++) {
                Suma sumita = new Suma(arr, i*CANT_THREADS, i*(ARR_SIZE/CANT_THREADS));
                sumas.add(sumita);
            }
            futurasSumas = executor.invokeAll(sumas);
            for (int i = 0; i < futurasSumas.size(); i++) {
                int val = futurasSumas.get(i).get();
                suma += val;
                //System.out.println("Suma del " + i + "° hilo: " + val);
            }
        } catch (Exception e) {
                // TODO: handle exception
        }
        executor.shutdown();
        System.out.println("Tiempo de ejecucion: " + ((System.nanoTime()-time)/1000000) + "ms");
        String out = "";
        out = (suma == 0) ? "Overflow por longitud" : "Valor de la suma: " + suma ;
        System.out.println(out);
    }
    
}
