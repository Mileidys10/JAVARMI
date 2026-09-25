package calculo.vistas;

import java.awt.Color;
import java.awt.Font;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import calculo.modelo.CalculoCosto;
import net.IRemotaCalculoCosto;

public class VentanaPrincipal extends JFrame {

    private String ipServidor = "localhost";
    private int puerto = 1099;
    private String nombreServicio = "CalculoCostoCombustibleServicio";
    private IRemotaCalculoCosto calculoCostoRemoto;

    private JLabel lblTitulo;
    private JTabbedPane jTabbedPane1;

    private JPanel panelConexion;
    private JLabel lblIP;
    private JTextField campoIPServidor;
    private JLabel lblPuerto;
    private JTextField campoPuertoServidor;
    private JLabel lblEstado;
    private JLabel txtEstado;
    private JButton btnIniciar;

    private JPanel panelCalculo;
    private JLabel lblDistancia;
    private JTextField campoDistancia;
    private JLabel lblRendimiento;
    private JTextField campoRendimiento;
    private JLabel lblPrecio;
    private JTextField campoPrecioCombustible;
    private JButton btnCalcular;

    private JLabel lblLitros;
    private JLabel txtLitros;
    private JLabel lblResultado;
    private JLabel txtResultado;
    private JLabel txtMensaje;

    public VentanaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("CLIENTE ESTIMAR COSTO (SIN LIBRERIA)");
        jTabbedPane1 = new JTabbedPane();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cliente RMI Estandar");
        setResizable(false);

        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        panelConexion = new JPanel();
        panelConexion.setLayout(null);

        lblIP = new JLabel("DIRECCION IP:");
        lblIP.setBounds(40, 30, 130, 25);
        panelConexion.add(lblIP);

        campoIPServidor = new JTextField("localhost");
        campoIPServidor.setBounds(180, 30, 180, 25);
        panelConexion.add(campoIPServidor);

        lblPuerto = new JLabel("PUERTO RMI:");
        lblPuerto.setBounds(40, 70, 130, 25);
        panelConexion.add(lblPuerto);

        campoPuertoServidor = new JTextField("1099");
        campoPuertoServidor.setBounds(180, 70, 180, 25);
        panelConexion.add(campoPuertoServidor);

        lblEstado = new JLabel("ESTADO:");
        lblEstado.setBounds(40, 115, 130, 25);
        panelConexion.add(lblEstado);

        txtEstado = new JLabel("Desconectado");
        txtEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtEstado.setForeground(new Color(220, 20, 60));
        txtEstado.setBounds(180, 115, 180, 25);
        panelConexion.add(txtEstado);

        btnIniciar = new JButton("Conectar");
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.setBounds(140, 165, 150, 35);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        panelConexion.add(btnIniciar);

        jTabbedPane1.addTab("CONEXION", panelConexion);

        panelCalculo = new JPanel();
        panelCalculo.setLayout(null);

        lblDistancia = new JLabel("DISTANCIA (km):");
        lblDistancia.setBounds(30, 20, 160, 25);
        panelCalculo.add(lblDistancia);

        campoDistancia = new JTextField();
        campoDistancia.setBounds(190, 20, 120, 25);
        panelCalculo.add(campoDistancia);

        lblRendimiento = new JLabel("RENDIMIENTO (km/L):");
        lblRendimiento.setBounds(30, 55, 160, 25);
        panelCalculo.add(lblRendimiento);

        campoRendimiento = new JTextField();
        campoRendimiento.setBounds(190, 55, 120, 25);
        panelCalculo.add(campoRendimiento);

        lblPrecio = new JLabel("PRECIO COMBUSTIBLE ($):");
        lblPrecio.setBounds(30, 90, 160, 25);
        panelCalculo.add(lblPrecio);

        campoPrecioCombustible = new JTextField();
        campoPrecioCombustible.setBounds(190, 90, 120, 25);
        panelCalculo.add(campoPrecioCombustible);

        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCalcular.setForeground(new Color(0, 128, 0));
        btnCalcular.setBounds(330, 20, 130, 95);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        panelCalculo.add(btnCalcular);

        lblLitros = new JLabel("LITROS NECESARIOS:");
        lblLitros.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLitros.setBounds(30, 130, 160, 25);
        panelCalculo.add(lblLitros);

        txtLitros = new JLabel("0.00 L");
        txtLitros.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtLitros.setForeground(new Color(200, 0, 0));
        txtLitros.setBounds(190, 130, 260, 25);
        panelCalculo.add(txtLitros);

        lblResultado = new JLabel("COSTO ESTIMADO:");
        lblResultado.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResultado.setBounds(30, 160, 160, 25);
        panelCalculo.add(lblResultado);

        txtResultado = new JLabel("$ 0.00");
        txtResultado.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtResultado.setForeground(new Color(0, 102, 204));
        txtResultado.setBounds(190, 160, 260, 25);
        panelCalculo.add(txtResultado);

        txtMensaje = new JLabel("");
        txtMensaje.setFont(new Font("Tahoma", Font.ITALIC, 11));
        txtMensaje.setForeground(Color.DARK_GRAY);
        txtMensaje.setBounds(30, 195, 430, 25);
        panelCalculo.add(txtMensaje);

        jTabbedPane1.addTab("CALCULAR COSTO", panelCalculo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (btnIniciar.getText().equalsIgnoreCase("Conectar")) {
                puerto = Integer.parseInt(campoPuertoServidor.getText().trim());
                ipServidor = campoIPServidor.getText().trim();

                Registry registro = LocateRegistry.getRegistry(ipServidor, puerto);
                calculoCostoRemoto = (IRemotaCalculoCosto) registro.lookup(nombreServicio);

                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("Conectado");
                txtEstado.setForeground(new Color(0, 153, 51));
                JOptionPane.showMessageDialog(this, "Conexion RMI exitosa con el servicio " + nombreServicio + " en " + ipServidor + ":" + puerto, "Conexion", JOptionPane.INFORMATION_MESSAGE);
            } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
                calculoCostoRemoto = null;
                btnIniciar.setText("Conectar");
                txtEstado.setText("Desconectado");
                btnIniciar.setForeground(new Color(0, 153, 51));
                txtEstado.setForeground(new Color(220, 20, 60));
                JOptionPane.showMessageDialog(this, "Desconectado del servidor RMI.", "Conexion", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No fue posible conectar con el servicio RMI: " + ex.getMessage(), "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {
        if (calculoCostoRemoto == null) {
            JOptionPane.showMessageDialog(this, "Primero debe conectarse al servidor en la pestana CONEXION.", "Sin Conexion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            float distancia = Float.parseFloat(campoDistancia.getText().trim());
            float rendimiento = Float.parseFloat(campoRendimiento.getText().trim());
            float precio = Float.parseFloat(campoPrecioCombustible.getText().trim());

            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CalculoCosto calculo = new CalculoCosto(distancia, rendimiento, precio);
                        CalculoCosto.Costo costo = calculoCostoRemoto.calcularCosto(calculo);

                        txtLitros.setText(String.format("%.2f L", costo.litrosNecesarios));
                        txtResultado.setText(String.format("$ %,.2f", costo.resultado));
                        txtMensaje.setText(costo.mensaje);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Error en invocacion RMI: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });
            hilo.start();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Ingrese numeros validos para la distancia, rendimiento y precio.", "Datos Invalidos", JOptionPane.WARNING_MESSAGE);
        }
    }
}
