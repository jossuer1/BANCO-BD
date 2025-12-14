import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BancaForm extends JFrame {

    private JPanel panelBanco;
    private JLabel labelUsuario;
    private JLabel labelSaldo;
    private JButton btnDeposito;
    private JButton btnRetiro;
    private JButton btnTransferencia;
    private JButton btnSalir;
    private JTextArea historialArea;

    private String usuarioActual;
    private double saldo;

    public BancaForm(String usuario) {

        this.usuarioActual = usuario;

        setTitle("Banco - Operaciones");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelBanco);

        labelUsuario.setText("Usuario: " + usuario);
        cargarSaldo();

        acciones();
        setVisible(true);
    }

    private void acciones() {

        btnDeposito.addActionListener(e -> {
            String v = JOptionPane.showInputDialog(this, "Monto a depositar:");
            if (!entradaValida(v)) return;

            double monto = Double.parseDouble(v);
            saldo += monto;

            guardarSaldo();
            actualizarSaldo();
            historialArea.append("Depósito: $" + monto + "\n");
        });

        btnRetiro.addActionListener(e -> {
            String v = JOptionPane.showInputDialog(this, "Monto a retirar:");
            if (!entradaValida(v)) return;

            double monto = Double.parseDouble(v);
            if (monto > saldo) {
                mensaje("Saldo insuficiente");
                return;
            }

            saldo -= monto;
            guardarSaldo();
            actualizarSaldo();
            historialArea.append("Retiro: $" + monto + "\n");
        });

        btnTransferencia.addActionListener(e -> {

            String dest = JOptionPane.showInputDialog(this, "Destinatario:");
            if (dest == null || dest.isBlank()) return;

            String v = JOptionPane.showInputDialog(this, "Monto:");
            if (!entradaValida(v)) return;

            double monto = Double.parseDouble(v);
            if (monto > saldo) {
                mensaje("Saldo insuficiente");
                return;
            }

            saldo -= monto;
            guardarSaldo();
            actualizarSaldo();
            historialArea.append("Transferencia a " + dest + ": $" + monto + "\n");
        });

        btnSalir.addActionListener(e -> dispose());
    }

    private void cargarSaldo() {
        try (Connection con = Conexion.getConexion()) {

            String sql = "SELECT saldo FROM usuarios WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuarioActual);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
                actualizarSaldo();
            }

        } catch (Exception e) {
            mensaje("Error al cargar saldo");
        }
    }

    private void guardarSaldo() {
        try (Connection con = Conexion.getConexion()) {

            String sql = "UPDATE usuarios SET saldo = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, saldo);
            ps.setString(2, usuarioActual);

            ps.executeUpdate();

        } catch (Exception e) {
            mensaje("Error al guardar saldo");
        }
    }

    private boolean entradaValida(String valor) {
        if (valor == null || valor.isBlank()) {
            mensaje("Ingrese un valor");
            return false;
        }
        if (!valor.matches("[0-9]+(\\.[0-9]+)?")) {
            mensaje("Ingrese solo números");
            return false;
        }
        return true;
    }

    private void mensaje(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    private void actualizarSaldo() {
        labelSaldo.setText("Saldo actual: $" + saldo);
    }
}
