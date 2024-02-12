import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadingBarGUI extends JFrame {
    // Ruta de la carpeta de los archivos que vamos a eliminar
    public static final String RUTA_CARPETA = "archivos";

    // Constructor
    public LoadingBarGUI(){
        // Establecer el título de la GUI
        super("Eliminar Archivos");

        // Establecer el tamaño de la GUI
        setSize(563, 392);

        // Establecer la operación de cierre para salir al cerrar y finalizar el proceso después de cerrar la GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Establecer la ubicación de la GUI en el centro
        setLocationRelativeTo(null);

        agregarComponentesGUI();
    }

    private void agregarComponentesGUI(){
        // Crear el botón de eliminar
        JButton botonEliminar = new JButton("Eliminar Archivos");

        // Cambiar la fuente del botón
        botonEliminar.setFont(new Font("Diálogo", Font.BOLD, 48));

        // Realizar acción cuando se hace clic en el botón
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Almacenar la referencia de la carpeta
                File carpeta = new File(RUTA_CARPETA);

                // Verificar si la variable carpeta realmente contiene una referencia a una carpeta
                if(carpeta.isDirectory()){
                    // Almacenar todos los archivos en un array
                    File[] archivos = carpeta.listFiles();

                    // Solo eliminar archivos si hay archivos en la carpeta
                    if(archivos.length > 0){
                        eliminarArchivos(archivos);
                    }else{
                        mostrarDialogoResultado("No hay archivos para eliminar");
                    }
                }
            }
        });

        // Agregar al GUI
        add(botonEliminar, BorderLayout.CENTER);
    }

    private void mostrarDialogoResultado(String mensaje){
        JDialog dialogoResultado = new JDialog(this, "Resultado", true);
        dialogoResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogoResultado.setSize(300, 150);
        dialogoResultado.setLocationRelativeTo(this);

        // Etiqueta de mensaje
        JLabel etiquetaMensaje = new JLabel(mensaje);
        etiquetaMensaje.setFont(new Font("Diálogo", Font.BOLD, 26));
        etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        dialogoResultado.add(etiquetaMensaje, BorderLayout.CENTER);

        // Botón de confirmar
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setFont(new Font("Diálogo", Font.BOLD, 18));
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el diálogo después de hacer clic en el botón de confirmar
                dialogoResultado.dispose();
            }
        });

        dialogoResultado.add(botonConfirmar, BorderLayout.SOUTH);
        dialogoResultado.setVisible(true);
    }

    private void eliminarArchivos(File[] archivos){
        // Crear un diálogo donde se almacenará la barra de progreso
        JDialog dialogoCarga = new JDialog(this, "Eliminando Archivos", true);
        dialogoCarga.setSize(300, 100);
        dialogoCarga.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogoCarga.setLocationRelativeTo(this);

        // Barra de progreso
        JProgressBar barraProgreso = new JProgressBar();
        barraProgreso.setFont(new Font("Diálogo", Font.BOLD, 18));

        // Cambiar el color de la barra de progreso a verde
        barraProgreso.setForeground(Color.decode("#2c8558"));

        // Inicializar el valor de la barra de progreso al 0%
        barraProgreso.setValue(10);

        // Hilo para eliminar arcwhivos
        Thread hiloEliminarArchivos = new Thread(new Runnable() {
            @Override
            public void run() {
                // Calcular el número de archivos en el directorio 'archivos'
                int totalArchivos = archivos.length;

                // Contar el número de archivos eliminados
                int archivosEliminados = 0;

                // Llevar un seguimiento del progreso
                int progreso;

                for(File archivo: archivos){
                    // Eliminar archivo
                    if(archivo.delete()){
                        archivosEliminados++;

                        // Calcular el progreso
                        progreso = (int)((((double) archivosEliminados / totalArchivos) * 100));

                        try{
                            Thread.sleep(60);
                        }catch(InterruptedException interrupcion){
                            interrupcion.printStackTrace();
                        }

                        // Actualizar la barra de progreso
                        barraProgreso.setValue(progreso);
                    }
                }

                // Cerrar el diálogo de carga cuando el progreso esté completo
                if(dialogoCarga.isVisible()){
                    dialogoCarga.dispose();
                }

                // Mostrar el diálogo de resultado
                mostrarDialogoResultado("Archivos Eliminados");
            }
        });

        // Iniciar el hilo para eliminar archivos
        hiloEliminarArchivos.start();

        // Agregar el símbolo de porcentaje
        barraProgreso.setStringPainted(true);

        dialogoCarga.add(barraProgreso, BorderLayout.CENTER);
        dialogoCarga.setVisible(true);
    }
}
