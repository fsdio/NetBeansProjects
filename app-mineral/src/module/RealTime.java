/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import java.text.SimpleDateFormat;
import javax.swing.JLabel;

/**
 *
 * @author fsdio
 */
public class RealTime {
    public void DateTime(JLabel valueDate, JLabel valueTime){
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    String tanggal = new SimpleDateFormat("EEEE, dd MMMM Y").format(new java.util.Date (EpochTime.currentTime()));
                    String jam = new SimpleDateFormat("KK:mm:ss a").format(new java.util.Date (EpochTime.currentTime()));
                    valueDate.setText(String.valueOf(tanggal));
                    valueTime.setText(String.valueOf(jam));
                }
            }
            
        }.start();
    }
    
    public String getTanggal(Long time){
        String tanggal = new SimpleDateFormat("EEEE, dd MMMM Y KK:mm:ss").format(new java.util.Date (time));
        return tanggal;
    }
}
