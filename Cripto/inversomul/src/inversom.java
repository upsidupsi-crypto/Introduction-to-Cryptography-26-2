import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class inversom {
    public static int invert(int alpha, int n) {
        for (int i=1;i<n;i++) {
            if ((alpha*i)%n==1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingresa el valor de \u03B1: ");
        int alpha = Integer.parseInt(br.readLine());
        System.out.print("Ingresa el valor del modulo n: ");
        int n = Integer.parseInt(br.readLine());
        int resultado = invert(alpha, n);
        if (resultado != -1) {
            System.out.println("El inverso multiplicativo de " + alpha + " mod " + n + " es: " + resultado);
        } else {
            System.out.println("No existe el inverso multiplicativo para los datos ingresados.");
        }
    }
}