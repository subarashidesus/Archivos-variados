import java.util.Random;
import java.util.Scanner;

public class NumerosAleatorios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el tamaño del array
        System.out.print("Introduce el tamaño del array: ");
        int tamanoArray = scanner.nextInt();

        // Crear el array y llenarlo con números aleatorios entre 1 y 300
        int[] arrayOriginal = new int[tamanoArray];
        Random random = new Random();

        for (int i = 0; i < tamanoArray; i++){
            arrayOriginal[i] = random.nextInt(300) + 1;
        }

        // Mostrar el array original
        System.out.println("Array original: ");
        mostrarArray(arrayOriginal);

        // Pedir al usuario el dígito por el cual filtrar
        System.out.print("\nIntroduce el dígito por el cual filtrar: ");
        int digitoFiltro = scanner.nextInt();

        // Crear un array con los número que terminen en el dígito especificado
        int[] arrayFiltrado = filtrarPorDigito(arrayOriginal, digitoFiltro);

        // Mostrar el array filtrado
        System.out.println("Array filtrado por el dígito " + digitoFiltro + ":");
        mostrarArray(arrayFiltrado);
    }

    // Método para mostrar un array
    private static void mostrarArray(int[] array){
        for ( int num : array){
            System.out.print(num + " ");
        }
    }

    // Metodo para filtrar numeros que terminan en un dígito específico
    private static int[] filtrarPorDigito(int[] array, int digito){
        int contador = 0;

        // Contar cuantos numeros terminan en el dígito especifico
        for ( int num: array){
            if ( num % 10 == digito){
                contador ++;
            }
        }

        // Crear el nuevo array con el tamaño adecuado
        int[] arrayFiltrado = new int[contador];
        contador = 0;

        // Llenar el nuevo array con los números que terminan en el dígito
        for ( int num : array){
            if (num % 10 == digito) {

                arrayFiltrado[contador] = num;
                contador ++;
            }
        }
        return arrayFiltrado;
    }
}
