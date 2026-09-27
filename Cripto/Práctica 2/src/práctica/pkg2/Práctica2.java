/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package práctica.pkg2;

/**
 *
 * @author Guada
 */
public class Práctica2 {
    
    public static int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1) return 0;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0) x1 += m0;

        return x1;
    }
    
    

    
    public static String procesarCifrado(int n, int a, int b) throws Exception {
        if (a >= n || b >= n || a <= 0 || b < 0) {
            throw new Exception("Alfa y beta deben ser menores que n y positivos.");
        }

        if (gcd(a, n) != 1) {
            throw new Exception("Alfa (" + a + ") no tiene inverso multiplicativo módulo " + n + ".");
        }

        int a_inv = modInverse(a, n);
        int minus_b_mod = (n - (b % n)) % n;
        long dk2_const = ((long) a_inv * minus_b_mod) % n;

        return String.format(
            "\n\nn=%d  "+ "a=%d  "+"b=%d  \n\n\n\n"+
            "Función de cifrado:      EK     C=%dp+%d mod %d\n\n" +
            "Funciones de descifrado: DK1    p=%d[C+%d] mod %d\n" +
            "                         DK2    p=%dC+%d mod %d",
            n, a, b, a, b, n, a_inv, minus_b_mod, n, a_inv, dk2_const, n
        );
    }
    
}
