import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    private JPanel JPanelLogin;
    private JTextField textUsuario;
    private JPasswordField textPass;
    private JButton accesoButton;
    private JButton salirButton;

    private int intentos = 0;

    public Login() {

        setTitle("LOGIN");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(JPanelLogin);

        accesoButton.addActionListener(e -> login());
        salirButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void login() {

        String usuario = textUsuario.getText();
        String pass = new String(textPass.getPassword());

        if (usuario.isBlank() || pass.isBlank()) {
            JOptionPane.showMessageDialog(this, "Complete los campos");
            return;
        }

        try (Connection con = Conexion.getConexion()) {

            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND activo = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);

                if (usuario.equalsIgnoreCase("admin")) {
                    new Adminusuarios();
                } else {
                    new BancaForm(usuario);
                }

                dispose();

            } else {

                intentos++;
                JOptionPane.showMessageDialog(this,
                        "Credenciales incorrectas. Intento " + intentos + " de 3");

                if (intentos >= 3) {
                    JOptionPane.showMessageDialog(this, "Acceso bloqueado");
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en login");
        }
    }

}
