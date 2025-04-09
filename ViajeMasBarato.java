import java.util.Arrays;

public class ViajeMasBarato {
    public static void main(String[] args) {
        // Número de embarcaderos
        int n = 5;

        // Matriz de tarifas directas T
        double[][] T = new double[n][n];
        
        // Inicializar con infinito
        for (int i = 0; i < n; i++) {
            Arrays.fill(T[i], Double.POSITIVE_INFINITY);
            T[i][i] = 0; // Costo de viajar al mismo embarcadero es 0
        }
        
        // Establecer costos directos (solo para i < j, ya que no se puede ir río arriba)
        T[0][1] = 10; T[0][2] = 25; T[0][3] = 40; T[0][4] = 45;
        T[1][2] = 8;  T[1][3] = 15; T[1][4] = 20;
        T[2][3] = 6;  T[2][4] = 18;
        T[3][4] = 7;

        System.out.println("ENTRADA: Matriz de tarifas directas (T):");
        imprimirMatriz(T);
        double[][] C = calcularCostosMinimos(n, T);

        System.out.println("\nSALIDA: Matriz de costos mínimos (C):");
        imprimirMatriz(C);
        mostrarDiferenciasImportantes(T, C);
    }

    public static double[][] calcularCostosMinimos(int n, double[][] T) {
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(T[i], 0, C[i], 0, n);
        }

        // Programación dinámica para calcular costos mínimos
        for (int l = 2; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                for (int k = i + 1; k < j; k++) {
                    // Verificar si hacer escala en k mejora el costo
                    if (C[i][k] != Double.POSITIVE_INFINITY && 
                        C[k][j] != Double.POSITIVE_INFINITY &&
                        C[i][k] + C[k][j] < C[i][j]) {
                        C[i][j] = C[i][k] + C[k][j];
                    }
                }
            }
        }
        return C;
    }

    public static void imprimirMatriz(double[][] matriz) {
        System.out.println("┌─────┬─────┬─────┬─────┬─────┬─────┐");
        System.out.println("│     │  0  │  1  │  2  │  3  │  4  │");
        System.out.println("├─────┼─────┼─────┼─────┼─────┼─────┤");
        
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("│  " + i + "  │");
            for (double valor : matriz[i]) {
                if (valor == Double.POSITIVE_INFINITY) {
                    System.out.print("  ∞  │");
                } else {
                    System.out.printf("%5.1f│", valor);
                }
            }
            System.out.println();
            if (i < matriz.length - 1) {
                System.out.println("├─────┼─────┼─────┼─────┼─────┼─────┤");
            }
        }
        System.out.println("└─────┴─────┴─────┴─────┴─────┴─────┘");
    }
    
    public static void mostrarDiferenciasImportantes(double[][] T, double[][] C) {
        System.out.println("\nCAMBIOS IMPORTANTES (donde C[i][j] ≠ T[i][j]):");
        for (int i = 0; i < T.length; i++) {
            for (int j = i+1; j < T[i].length; j++) {
                if (T[i][j] != C[i][j] && 
                    T[i][j] != Double.POSITIVE_INFINITY && 
                    C[i][j] != Double.POSITIVE_INFINITY) {
                    System.out.printf("• Ruta %d→%d: Directo = %.1f, Con escalas = %.1f (Ahorro: %.1f)\n", 
                                     i, j, T[i][j], C[i][j], T[i][j] - C[i][j]);
                }
            }
        }
    }
}
