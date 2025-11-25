package tp_final;
import java.util.*;
import java.util.concurrent.*;

public class Test {
    final static int ARR_SIZE = 1000;
    final static int CANT_THREADS = ARR_SIZE/250;
    public static void main(String[] args) {
        Random rand = new Random();
        int suma = 0;
        int[] arr = new int[ARR_SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(CANT_THREADS);
        //ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        List <Callable<Integer>> sumas = new ArrayList<>();
        List <Future<Integer>> futurasSumas = new ArrayList<>();
        
        try {
            for (int i = 0; i < CANT_THREADS; i++) {
                Suma sumita = new Suma(arr, i*CANT_THREADS, i*(ARR_SIZE/CANT_THREADS));
                sumas.add(sumita);
                System.out.println("A");
            }
            futurasSumas = executor.invokeAll(sumas);
            for (int i = 0; i < futurasSumas.size(); i++) {
                suma = futurasSumas.get(i).get();
                
            }
        } catch (Exception e) {
                // TODO: handle exception
        }
        System.out.println(suma);
    }
    
}
