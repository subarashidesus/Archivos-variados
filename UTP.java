import java.util.Scanner;

public class UTP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i = 2;

        do{

            System.out.print("Ingrese el usuario: ");
            String usuario = sc.next();

            System.out.print("Ingrese la contraseña: ");
            String contrasena = sc.next();

            if ( usuario.equalsIgnoreCase("Utp789") && (contrasena.equalsIgnoreCase("1254"))){
                System.out.println("Bienvenido");
                i = 4;
            } else if (i != 0){
                System.out.println("Contraseña o usuario incorrecto le quedan " + ( i ) + " intentos");
            }
            i--;
        } while ( i != -1 && i != 4);

        if ( i == -1){
            System.out.println("Cuenta Bloqueada");
        }
    }
}
