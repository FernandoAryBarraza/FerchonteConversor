import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LogicaCalculo extends JFrame {
    //private JPanel pantalla;
    private JLabel titulo, advertencia, advertencia1, advertencia2, Resultado;
    private JComboBox<Moneda> cbMoneda01;
    private JTextField ValorInicial;
    private JComboBox<Moneda> cbMoneda02;
    private JButton btnCanviar, btnLimpiar;
    private JPanel pantalla;

    public LogicaCalculo(){

        Container container = getContentPane();
        setLayout(null);
        conversorGrafica(container);
        accionesDelConversor();
        cbMoneda01.setModel(new DefaultComboBoxModel<>(Moneda.values()));
        cbMoneda02.setModel(new DefaultComboBoxModel<>(Moneda.values()));
    }

    double valorImput = 0.00;
    public enum Moneda{
        peso_arg(0.0035),
        euro(1.10),
        libra(1.27),
        dolar(1),
        sol(0.27),
        real(0.20),
        peso_chi(0.0012);

        private final double valorCambio;

        Moneda(double valorCambio) {
            this.valorCambio = valorCambio;
        }

        public double getValorCambio() {
            return valorCambio;
        }
    }
    private void conversorGrafica(Container container){
        pantalla = new JPanel();
        pantalla.setBounds(10, 10, 580, 580);
        titulo = new JLabel("CONVERSOR");
        advertencia = new JLabel("Seleccione la moneda de origen");
        advertencia1 = new JLabel("Ingrese la cantidad a convertir");
        advertencia2 = new JLabel("Seleccione la moneda final");
        cbMoneda01 = new JComboBox<Moneda>();
        ValorInicial = new JTextField();
        cbMoneda02 = new JComboBox<Moneda>();
        btnCanviar = new JButton("Canviar");
        Resultado = new JLabel();
        btnLimpiar = new JButton("Limpiar");

        titulo.setBounds(172,8,500,25);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        advertencia.setBounds(35,35,200,20);
        advertencia1.setBounds(45,100,200,20);
        advertencia2.setBounds(35,170,200,20);
        cbMoneda01.setBounds(25,55,200,25);
        ValorInicial.setBounds(45,125,200,25);
        cbMoneda02.setBounds(25,190,200,25);
        btnCanviar.setBounds(350, 130, 80, 25);
        Resultado.setBounds(190,275,450,35);
        Resultado.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnLimpiar.setBounds(220,350,80,25);

        container.add(titulo);
        container.add(advertencia);
        container.add(advertencia1);
        container.add(advertencia2);
        container.add(cbMoneda01);
        container.add(ValorInicial);
        container.add(cbMoneda02);
        container.add(btnCanviar);
        container.add(Resultado);
        container.add(btnLimpiar);

        setSize(500, 450);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void accionesDelConversor(){

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionLimpiar();
            }
        });
        btnCanviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Convertir();
            }
        });

    }

    public void Convertir() {

        if(Validar(ValorInicial.getText())) {
            Moneda moneda1 = (Moneda) cbMoneda01.getSelectedItem();
            Moneda moneda2 = (Moneda) cbMoneda02.getSelectedItem();
            double valor = Double.parseDouble(ValorInicial.getText());
            double res = (moneda1.getValorCambio() / moneda2.getValorCambio()) * valor;
            Resultado.setText(Redondear(res));
        }
    }

    public String Redondear(double valor) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(valor);
    }
    public boolean Validar(String texto) {
        try {
            double x = Double.parseDouble(texto);
            if(x > 0);
            valorImput = x;
            return true;
        }catch(NumberFormatException e) {
            ValorInicial.setText("Solamente números !!");
            return false;
        }
    }
    private void accionLimpiar(){
        this.ValorInicial.setText("");
        this.Resultado.setText("");
        this.cbMoneda01.setSelectedIndex(0);
        this.cbMoneda02.setSelectedIndex(0);
    }

}
