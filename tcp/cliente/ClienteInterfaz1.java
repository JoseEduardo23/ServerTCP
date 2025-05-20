package tcp.cliente;

import tcp.cliente.clase.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteInterfaz1 extends JFrame {

    private JTextField txtNombre;
    private JComboBox <String> comboEtapa;
    private JButton btnRegistrar;
    private JLabel lblNombre;
    private JLabel lblEtapa;

    public ClienteInterfaz1() {
        setTitle("Registro de Empleado");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        txtNombre = new JTextField(20);


        String[] etapas = {"ingreso", "salida_almuerzo", "ingreso_almuerzo", "salida"};
        comboEtapa = new JComboBox<>(etapas);


        // Panel y Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblEtapa);
        panel.add(comboEtapa);
        panel.add(new JLabel());
        panel.add(btnRegistrar);

        add(panel);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String etapa = (String) comboEtapa.getSelectedItem();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese su nombre.");
                    return;
                }

                try {
                    String mensaje = cliente.enviarNombre(nombre + "|" + etapa);
                    JOptionPane.showMessageDialog(null, "Registro exitoso:\n" + mensaje);
                    txtNombre.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar con el servidor:\n" + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteInterfaz1().setVisible(true));
    }
}