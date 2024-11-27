package presentacion;
import javax.swing.*;
import java.awt.*;

public class Dibujar_Figura extends JFrame {
    private JTextField textR, textG, textB;
    private JButton btnCuadrado, btnCirculo;
    private JPanel canvas;
    private String figura = ""; 
    private Color colorFigura; 

    public Dibujar_Figura() {
        setTitle("Parcial de Santiago Matijasevic");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2));
        //Campos para las entradas de R, G y B
        controlPanel.add(new JLabel("R:"));
        textR = new JTextField("100");
        controlPanel.add(textR);
        controlPanel.add(new JLabel("G:"));
        textG = new JTextField("100");
        controlPanel.add(textG);
        controlPanel.add(new JLabel("B:"));
        textB = new JTextField("100");
        controlPanel.add(textB);
        //Botones para elegir cual forma se quiere dibujar
        btnCuadrado = new JButton("Cuadrado");
        btnCirculo = new JButton("Circulo");
        controlPanel.add(btnCuadrado);
        controlPanel.add(btnCirculo);

        add(controlPanel, BorderLayout.WEST);

        //Panel de dibujo
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);

                if (figura.equals("Cuadrado") && colorFigura != null) {
                    g.setColor(colorFigura);
                    g.fillRect(50, 50, 200, 200);
                } else if (figura.equals("Circulo") && colorFigura != null) {
                    g.setColor(colorFigura);
                    g.fillOval(50, 50, 200, 200);
                }
            }
        };
        add(canvas, BorderLayout.CENTER);

        //Acciones de los botones "Cuadrado" y "Circulo"
        btnCuadrado.addActionListener(e -> {
            figura = "Cuadrado";
            actualizarColor();
        });

        btnCirculo.addActionListener(e -> {
            figura = "Circulo";
            actualizarColor();
        });
    }
    //Actualizamos el color que se ingresa segun el numero que eligio en el rango 
    private void actualizarColor() {
        try {
            int r = Integer.parseInt(textR.getText());
            int g = Integer.parseInt(textG.getText());
            int b = Integer.parseInt(textB.getText());
            if (validarRGB(r, g, b)) {
                colorFigura = new Color(r, g, b);
                canvas.repaint(); 
            } else {
                mostrarError();
            }
        } catch (NumberFormatException ex) {
            mostrarError();
        }
    }
    //Validamos si la entrada hecha por el usuario se encuentra dentro de los parametros
    private boolean validarRGB(int r, int g, int b) {
        return (r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255);
    }
    //En el caso en el que se elija un numero fuera del rango, se retorna un error
    private void mostrarError() {
        JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos entre 0 y 255.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Dibujar_Figura  frame = new Dibujar_Figura ();
            frame.setVisible(true);
        });
    }
}










