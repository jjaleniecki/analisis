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

            /*
            estas operaciones se agregan para agregar carga computacional
            a cada iteración del thread, haciendo que la paralelización sea más notable
            ya que el overhead causado por toda la operación de creacion de hilos
            
            */
            int dummy = arr[i];
            for (int k = 0; k < 300; k++) {
                dummy = (dummy * 1664525 + 1013904223); 
                dummy ^= (dummy >>> 13);
            }

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
