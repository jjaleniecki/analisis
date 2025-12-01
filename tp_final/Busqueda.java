package tp_final;

public class Busqueda extends Thread {
    private int[] arr;
    private int obj, min, max, pos = -1;
    private ThreadFlag flag;

    public Busqueda(int[] arr, int obj, int min, int max, ThreadFlag flag){
        this.arr = arr;
        this.obj = obj;
        this.min = min;
        this.max = max;
        this.flag = flag;
    }

    public void run(){
        for (int i = min; i < max; i++) {
            if(flag.encontrado) return;

            if(arr[i] == obj){
                flag.encontrado = true;
                flag.posicion = i;
                return;
            }
        }
    }

    public int getPos(){
        return this.pos;
    }
}
