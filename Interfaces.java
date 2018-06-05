/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileDescriptor;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nosferato
 */
public class Interfaces extends JFrame {

    private JButton derecha, izquierda, cerrar, abrir, abajoCodo, abajoHombro, arribaCodo, arribaHombro, generar, automatico,borrar;
    private JPanel panel;
    // private String der, izq, cer, abri, abC, abH, arC, arH;
    private OutputStream ouput;
    SerialPort serialport;
    private String puerto;
    private int time, dataRate;
    private boolean con, gem;
    String sec;
    LinkedList<Lista> lista = new LinkedList<>();

    public Interfaces() {
        super("Control");
        gem = false;
        sec = "";
        setLayout(null);
        puerto = "COM3";//puerto a donde esta conectado el arduino
        time = 2000;
        con = false;
        dataRate = 9600;
        ouput = null;
        panel = new JPanel(null);
        borrar = new JButton("Borrar secuencia");//boton para borrar secuencia grabada
        derecha = new JButton("Derecha");//boton para girar a la derecha
        generar = new JButton("Generar secuencia");//boton para crear la secuencia que va a guardar
        izquierda = new JButton("Izquierda");//boton para girar a la izquierda
        arribaHombro = new JButton("Subir Hombro");//boton para subir el hombro
        arribaCodo = new JButton("Subir Codo");//boton para subir el codo
        abajoCodo = new JButton("Bajar codo");//boton para bajar el codo
        abajoHombro = new JButton("Bajar Hombro");//boton para bajar el hombro
        cerrar = new JButton("Cerrar");//boton para cerrar la pinza
        abrir = new JButton("Abrir ");//boton para abrir la pinza
        automatico = new JButton("R. secuencia");//botono para repetir la secuencia
        
        //----------------------------------colores de los botones-----------------------------------
        borrar.setBackground(Color.red);
        derecha.setBackground(Color.GREEN);
        izquierda.setBackground(Color.GREEN);
        arribaHombro.setBackground(Color.BLUE);
        arribaCodo.setBackground(Color.GRAY);
        abajoCodo.setBackground(Color.GRAY);
        abajoHombro.setBackground(Color.BLUE);
        cerrar.setBackground(Color.cyan);
        abrir.setBackground(Color.CYAN);
        automatico.setBackground(Color.pink);
        generar.setBackground(Color.MAGENTA);
        
//---------------------posicion de los botones-----------------------
        derecha.setBounds(250, 150, 150, 50);
        izquierda.setBounds(50, 150, 150, 50);
        arribaHombro.setBounds(50, 75, 150, 50);
        arribaCodo.setBounds(250, 75, 150, 50);
        abajoHombro.setBounds(50, 225, 150, 50);
        abajoCodo.setBounds(250, 225, 150, 50);
        abrir.setBounds(50, 300, 150, 50);
        cerrar.setBounds(250, 300, 150, 50);
        automatico.setBounds(50, 15, 150, 50);
        generar.setBounds(250, 15, 150, 50);
        borrar.setBounds(175, 400, 150, 50);
      
        //--------------agrergar los botones al panel----------------
        add(derecha);
        add(izquierda);
        add(arribaCodo);
        add(arribaHombro);
        add(abajoCodo);
        add(abajoHombro);
        add(abrir);
        add(cerrar);
        add(panel);
        add(automatico);
        add(generar);
        add(borrar);
       //----------------------------------------------------- 
        
        Conection();
        
       //------------Accion a realizar cada boton---------------- 
        arribaHombro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "1";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("1");
                }
            }
        });
        abajoHombro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "2";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("2");
                }

            }
        });
        derecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "3";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("3");
                }
            }
        });
        izquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "4";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("4");
                }
            }
        });
        arribaCodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "5";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("5");
                }
            }
        });
        abajoCodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "6";
                    lista.add(new Lista(sec));
                } else {
                    enviarDatos("6");
                }
            }
        });
        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "7";
                    lista.add(new Lista(sec));                    
                } else {
                    enviarDatos("7");
                }
            }
        });
        cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    sec = "8";
                    lista.add(new Lista(sec));                   
                } else {
                    enviarDatos("8");
                }
            }
        });
        automatico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gem == true) {
                    enviarDatos("9");
                    for (int i = 0; i < lista.size(); i++) {
                        //System.out.println(lista.get(i).secuencia);
                        switch(Integer.parseInt(lista.get(i).secuencia)){
                            case 1:
                                JOptionPane.showMessageDialog(null, "arriba hombro");
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "abajo hombro");
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "derecha");
                                break;
                            case 4:
                                JOptionPane.showMessageDialog(null, "izquierda");
                                break;
                            case 5:
                                JOptionPane.showMessageDialog(null, "arriba codo");
                                break;
                            case 6:
                                JOptionPane.showMessageDialog(null, "abajo codo");
                                break;
                            case 7:
                                JOptionPane.showMessageDialog(null, "abrir");
                                break;
                            case 8:
                                JOptionPane.showMessageDialog(null, "cerrar");
                                break;
                        }
                        enviarDatos(lista.get(i).secuencia);
                        
                    }
                  
                } else {
                    enviarDatos("9");
                }
            }
        });
        generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gem = true;
                JOptionPane.showMessageDialog(null, "Presione los botones para generar secuencia");
            }
        });
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gem = false;
                lista.clear();
            }
        });
    }

    public boolean Conection() {

        CommPortIdentifier puertoId = null;
        Enumeration puertoEnum = CommPortIdentifier.getPortIdentifiers();
        while (puertoEnum.hasMoreElements()) {
            CommPortIdentifier ActualPuerto = (CommPortIdentifier) puertoEnum
                    .nextElement();
            if (puerto.equals(ActualPuerto.getName())) {
                puertoId = ActualPuerto;
                break;
            }
        }
        if (puertoId == null) {
            mostrarError("No se puede conectar ");
            System.exit(ERROR);
        }
        try {
            serialport = (SerialPort) puertoId.open(this.getClass().getName(), time);
            //parametros puerto serie
            serialport.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            ouput = serialport.getOutputStream();
        } catch (Exception e) {
            mostrarError(e.getMessage());
            System.exit(ERROR);
        }

        return con;
    }

    private void enviarDatos(String Datos) {
        try {
            ouput.write(Datos.getBytes());
        } catch (Exception e) {
            mostrarError("Error");
            System.exit(ERROR);
        }
    }

    public void mostrarError(String err) {
        JOptionPane.showMessageDialog(this, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        Interfaces obj = new Interfaces();
        obj.setDefaultCloseOperation(EXIT_ON_CLOSE);
        obj.setSize(450, 600);
        obj.setLocation(450, 250);
        obj.setResizable(false);
        obj.setVisible(true);
    }
}
