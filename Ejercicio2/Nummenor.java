import java.util.Random;

public class Nummenor {
    private int[] array;
    int k;

    public Nummenor(int[] array, int k){
        this.array=array;
        this.k=k;
    }

    public int Menor(){
        return MenorAux(array, 0, array.length-1, k-1);
    }

    public int MenorAux(int[] array,int ini, int fin, int k) {

        if (ini == fin) {
            return array[ini];
        }
        int temp = particion(array, ini, fin);

        if (k == temp) {
            return array[k];
        } else if (k < temp) {
            return MenorAux(array, ini, temp - 1, k);
        } else {
            return MenorAux(array, temp + 1, fin, k);
        }
    }

    public static int particion(int[] array, int ini, int fin){
        int temp=new Random().nextInt(fin-ini+1)+ini;
        int aux=array[temp];
        intercambiar(array, temp, fin);

        int alm=ini;
        for(int i=ini; i<fin; i++){
            if(array[i]<aux){
                intercambiar(array, alm, i);
                alm++;
            }
        }
        intercambiar(array, alm, fin);
        return alm;
    }

    private static void intercambiar(int[] array, int i, int j){
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }
}
