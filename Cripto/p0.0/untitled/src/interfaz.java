import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class interfaz extends JFrame {
    private JTextField corrimiento;

    public interfaz() {
        setTitle("Cifrador por corrimiento");
        setSize(1240,960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel e_corrimiento = new JPanel(new FlowLayout(FlowLayout.CENTER));
        e_corrimiento.setBorder(new EmptyBorder(10,10,10,10));
        JLabel lblCorrimiento = new JLabel("Corrimiento");
        lblCorrimiento.setFont(new Font("Montserrat", Font.BOLD, 20));
        corrimiento=new JTextField(8);
        corrimiento.setFont(new Font("Montserrat",Font.PLAIN,16));
        e_corrimiento.add(lblCorrimiento);
        e_corrimiento.add(corrimiento);
        add(e_corrimiento,BorderLayout.CENTER);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton b_Cifrar = new JButton("Cifrar");
        JButton b_Descifrar = new JButton("Descifrar");
        botones.add(b_Cifrar);
        botones.add(b_Descifrar);
        add(botones,BorderLayout.SOUTH);
        b_Cifrar.addActionListener(e -> e_accion(true));
        b_Descifrar.addActionListener(e -> e_accion(false));
    }
    private void e_accion(boolean x) {
        String text = corrimiento.getText().trim();
        if(text.isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese un corrimiento");
            return;
        }
        int corrim;
        try{
            corrim = Integer.parseInt(text);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Ingrese un corrimiento válido");
        }
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle(x ? "Selecciona el archivo para Cifrar" : "Selecciona el archivo para Descifrar");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos TXT y BMP", "txt", "bmp");
        selectorArchivo.setFileFilter(filtro);

        if (selectorArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();

            try {
                Cifrador.procesarArchivo(archivoSeleccionado.getAbsolutePath(), x, corrimiento);
                JOptionPane.showMessageDialog(this,
                        "Archivo procesado con exito\nRevisa la misma carpeta del archivo original.",
                        "Proceso Terminado",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error de lectura/escritura:\n" + e.getMessage(),
                        "Error Interno",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            interfaz ventana = new interfaz();
            ventana.setVisible(true);
        });
    }
}