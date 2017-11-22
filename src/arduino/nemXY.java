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
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Ruhi ÇELİK
 */
public class nemXY extends JFrame{
    
    JButton connect;
    JComboBox<String> potlist;
   
   JPanel top;
   int x;
   XYSeries series;
    public nemXY(){
    setTitle("Nem Okumaları");
        
    setSize(600,400);
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
          series=new XYSeries("Nem okumaları");
        XYSeriesCollection dataSet=new XYSeriesCollection(series);
        JFreeChart chart=ChartFactory.createXYLineChart("NEM okumalar", "Zaman(saniye)", "ADC Okumaları", dataSet);
        add(new ChartPanel(chart),BorderLayout.CENTER);
        
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
                    Scanner scn=new Scanner(chosenPort.getInputStream());
                    
                    while (scn.hasNextLine()){
                        try{
                        String line=scn.nextLine();   //
                        String[] kelime=line.split(":");
        String ilk=kelime[0];
        String iki=kelime[1];
                        int numara=Integer.parseInt(iki);
        if (ilk.equals("Nem"))
        {
                        series.add(x++,numara);
                        repaint();
        }  }catch(Exception e){
                            
                            }}
                    scn.close();
                }
                }; thr.start();
            }else{
                chosenPort.closePort();
                potlist.setEnabled(true);
                connect.setText("BAĞLAN");
                series.clear();
                x=0;
            }
            }
        });
        
        setVisible(true);
    }
    
}
    


