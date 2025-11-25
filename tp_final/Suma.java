package tp_final;
import java.util.concurrent.Callable;

public class Suma implements Callable<Integer> {
    private int[] arr;
    private int min, max;

    public Suma(int[] arr, int min, int max){
        this.arr = arr;
        this.min = min;
        this.max = max;
    }

    public Integer call(){
        int i = min, suma = 0;
        while(i<max){
            suma += arr[i];
        }
        return suma;
    }
}
