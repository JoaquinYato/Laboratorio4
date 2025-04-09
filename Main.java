public class Main {
    public static void main(String[] arg){
        int[] num={3,6,3,5,1,4,3,1,2};
        int k=3;

        Nummenor obj = new Nummenor(num, k);
        System.out.println("El "+k+"° menor del arreglo es: "+obj.Menor());
    }
}