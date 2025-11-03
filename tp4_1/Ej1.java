public class Ej1{
    public static void main(String[] args) {
        double time = System.nanoTime();
        int n = 31;

        System.out.println("\n" + n + "° numero de fib: " + fibIterativo(n));
        System.out.println("Tiempo iterativo en ms: " + ((System.nanoTime()-time)/1000000) + "\n");

        time = System.nanoTime();
        System.out.println(n + "° numero de fib: " + fibRecursivo(n));
        System.out.println("Tiempo recursivo en ms: " + ((System.nanoTime()-time)/1000000) + "\n");
    }

    public static int fibIterativo(int n){
        int n1 = 0, n2 = 1, swap;
        for (int i = 0; i < n; i++) {
            swap = n1 + n2;
            n1 = n2;
            n2 = swap;
        }
        return n1;
    }

    public static int fibRecursivo(int n){
        if (n <= 1) return n;
        return (fibRecursivo(n-1) + fibRecursivo(n-2));
    }
}