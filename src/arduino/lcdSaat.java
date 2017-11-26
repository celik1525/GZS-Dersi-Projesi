/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino;

import static arduino.arduinoBaglan.chosenPort;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ruhi ÇELİK
 */
public class lcdSaat extends JFrame {
    JButton connect;
    JComboBox<String> potlist;
    
   JPanel top;
   int x;
   
   public lcdSaat(){
     setTitle("LCD ekranında saat gösterilmesi");
        
    setSize(600,100);
        setLayout(new BorderLayout());
          potlist=new JComboBox<String>();
          connect=new JButton("BAĞLAN");
        top=new JPanel();
        top.add(potlist);
        top.add(connect);
        add(top,BorderLayout.NORTH);
        SerialPort[] portnames=SerialPort.getCommPorts();
        for (int i=0;i<portnames.length;i++){
            potlist.addItem(portnames[i].getSystemPortName());
                   
        }
        connect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { 
            if (connect.getText().equals("BAĞLAN")){
                chosenPort=SerialPort.getCommPort(potlist.getSelectedItem().toString());
                chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                if (chosenPort.openPort()){
                    connect.setText("BAĞLANTIYI KES");
                    potlist.setEnabled(false);
                }
                Thread thr=new Thread(){
                @Override
                public void run(){
                    try{Thread.sleep(100);}catch(Exception e){}
                PrintWriter output=new PrintWriter(chosenPort.getOutputStream());
                while(true){
                    output.print(new SimpleDateFormat("hh:mm:ss a             mmmmmmmm dd,yyyy").format(new Date()));
                    output.flush();
                    try{Thread.sleep(100);}catch(Exception e){}
                   }
                    
                }
                }; thr.start();
            }else{
                chosenPort.closePort();
                potlist.setEnabled(true);
                connect.setText("BAĞLAN");
                         }
            }
        });
        
        setVisible(true);
    }
    
}