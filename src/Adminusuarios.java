import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Adminusuarios extends JFrame {

    // ===== COMPONENTES DEL FORM =====
    private JPanel JPanelAdmin;
    private JTable tablaUsuarios;
    private JTextField txtUser;
    private JTextField txtPass;
    private JTextField txtSaldo;
    private JButton btnCrear;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;



    // ===== CONSTRUCTOR =====
    public Adminusuarios() {

        setTitle("Banco - Admin");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(JPanelAdmin);

        acciones();                 // ← conecta botones
        cargarSeleccionTabla();     // ← selección tabla


        setVisible(true);
    }


    private void acciones() {

        btnCrear.addActionListener(e -> crearUsuario());

        btnListar.addActionListener(e -> listarUsuarios());

        btnActualizar.addActionListener(e -> actualizarUsuario());

        btnEliminar.addActionListener(e -> eliminarUsuario());
    }


    // ===== CREATE =====
    private void crearUsuario() {

        String user = txtUser.getText();
        String pass = txtPass.getText();
        double saldo;

        try {
            saldo = Double.parseDouble(txtSaldo.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Saldo inválido");
            return;
        }

        String sql = "INSERT INTO usuarios(username, password, saldo, activo) VALUES (?, ?, ?, true)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setDouble(3, saldo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario creado");
            limpiarCampos();
            listarUsuarios(); // refresca tabla

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear usuario");
        }
    }


    // ===== READ =====
    private void listarUsuarios() {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{
                "ID", "Usuario", "Saldo", "Activo"
        });

        String sql = "SELECT id, username, saldo, activo FROM usuarios";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getDouble("saldo"),
                        rs.getBoolean("activo") ? "Activo" : "Inactivo"
                });
            }

            tablaUsuarios.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar usuarios");
        }
    }

    // ===== UPDATE =====
    private void actualizarUsuario() {

        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            return;
        }

        int id = (int) tablaUsuarios.getValueAt(fila, 0);
        double saldo;

        try {
            saldo = Double.parseDouble(txtSaldo.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Saldo inválido");
            return;
        }

        String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, saldo);
            ps.setInt(2, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario actualizado");
            listarUsuarios();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
        }
    }

    // ===== DELETE (LÓGICO) =====
    private void eliminarUsuario() {

        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            return;
        }

        int id = (int) tablaUsuarios.getValueAt(fila, 0);

        String sql = "UPDATE usuarios SET activo = false WHERE id = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario desactivado");
            listarUsuarios();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
        }
    }


    // ===== CARGAR DATOS AL SELECCIONAR TABLA =====
    private void cargarSeleccionTabla() {
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {


            int fila = tablaUsuarios.getSelectedRow();
            if (fila != -1) {
                txtUser.setText(tablaUsuarios.getValueAt(fila, 1).toString());
                txtSaldo.setText(tablaUsuarios.getValueAt(fila, 2).toString());
            }
        });
    }

    // ===== LIMPIAR CAMPOS =====
    private void limpiarCampos() {
        txtUser.setText("");
        txtPass.setText("");
        txtSaldo.setText("");
    }

    // ===== GETTER DEL PANEL (PARA MOSTRAR EN FRAME) =====
    public JPanel getJPanelAdmin() {
        return JPanelAdmin;
    }


}

