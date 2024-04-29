/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasikaryawan;

/**
 *
 * @author fsdio
 */
public class myProfile {
    private static String username;
    
    public static void setUsername(String user){
        myProfile.username = user;
    }
    
    public static String getUser(){
        return myProfile.username;
    }
}
