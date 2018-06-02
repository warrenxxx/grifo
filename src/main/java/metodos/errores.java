/**
 * The errores class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :1/06/2018
 */
package metodos;

public class errores {

    public static double errorAbsoluto(double yr,double ya){
        return Math.abs(yr-ya);
    }
    public static double errorRelativo(double yr,double ya){
        return errorAbsoluto(yr,ya)/yr;
    }
    public static double errorRelativoPorcentual(double yr,double ya){
        return errorAbsoluto(yr,ya)*100;
    }

    public static double epsilonMaquina(){
        double a=1;
        while(a+1>1){
            a=a/2;
        }
        return a*2;
    }

    public static double fx(double x){
        return x;
    }

    public static void main(String args[]){
        System.out.println(epsilonMaquina());
    }
}
