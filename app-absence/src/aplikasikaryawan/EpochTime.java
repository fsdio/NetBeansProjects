/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikaryawan;

import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fsdio
 */
public class EpochTime {
    
    public static long getWorkOfDay(String value){
        ZoneId zoneIdDefault = ZoneId.systemDefault();
        ZoneId zoneId = ZoneId.of(String.valueOf(zoneIdDefault));
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime startTime = now.withHour(8).withMinute(0).withSecond(0);
        ZonedDateTime endTime = now.withHour(17).withMinute(0).withSecond(0);
        if (now.isAfter(endTime)) { endTime = endTime.plusDays(1);}
        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();
        long startEpoch = (startInstant.getEpochSecond()*1000);
        long endEpoch = (endInstant.getEpochSecond()*1000);
        
        if ("START".equals(value)) {
            return startEpoch;
        } else {
            return endEpoch;
        }
    }
    
    public static long getOfDay(String value){
        ZoneId zoneIdDefault = ZoneId.systemDefault();
        ZoneId zoneId = ZoneId.of(String.valueOf(zoneIdDefault));
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime startTime = now.withHour(0).withMinute(0).withSecond(0);
        ZonedDateTime endTime = now.withHour(23).withMinute(59).withSecond(59);
        if (now.isAfter(endTime)) { endTime = endTime.plusDays(1);}
        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();
        long startEpoch = (startInstant.getEpochSecond()*1000);
        long endEpoch = (endInstant.getEpochSecond()*1000);
        
        if ("START".equals(value)) {
            return startEpoch;
        } else {
            return endEpoch;
        }
    }
    
    public static long currentTime(){
        ZoneId zoneIdDefault = ZoneId.systemDefault();
        ZoneId zoneId = ZoneId.of(String.valueOf(zoneIdDefault));
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        Instant getTime = now.toInstant();
        long epoch = (getTime.getEpochSecond()*1000);
        return epoch;
    }
    
    public static long getDateEpoch(String tanggal) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = sdf.parse(tanggal);// the date instance
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] time = { calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH)};
        
        ZoneId zoneIdDefault = ZoneId.systemDefault();
        ZoneId zoneId = ZoneId.of(String.valueOf(zoneIdDefault));
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime fixTime = now.withYear(time[0]).withMonth(time[1]).withDayOfMonth(time[2]).withHour(0).withMinute(0).withSecond(0);
        Instant getTime = fixTime.toInstant();
        
        long epochTime = (getTime.getEpochSecond()*1000);
        return epochTime;
    }
    
    public static long getMonthEpoch(Date date, int value){

        // Membuat objek Calendar dan mengatur tanggal sesuai dengan epoch
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Mendapatkan awal bulan
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Long awalBulan = calendar.getTimeInMillis();

        // Mendapatkan akhir bulan
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Long akhirBulan = calendar.getTimeInMillis();

        if(value == 0){
            return awalBulan;
        }else if(value == 1){
            return akhirBulan;
        }
        return 0;
    }
    
    public static String convertDateEpoch(JDateChooser Chooserdate) throws ParseException{
        
        Calendar calendar = Calendar.getInstance();
        
        // Ubah menjadi date yang ditentukan
        calendar.setTime(Chooserdate.getDate());

        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    
    }
    
    public static void main(String[] args) throws ParseException {
        System.out.println(getWorkOfDay("END".toUpperCase()));
        System.out.println(getWorkOfDay("START".toUpperCase()));
        
        System.out.println(getOfDay("END".toUpperCase()));
        System.out.println(getOfDay("START".toUpperCase()));
        
        System.out.println(currentTime());
        
        String tanggal = "08/18/2023";
        System.out.println(getDateEpoch(tanggal));

        Date date = new Date();
        System.out.println(getMonthEpoch(date, 0));
        System.out.println(getMonthEpoch(date, 1));
    }
}
