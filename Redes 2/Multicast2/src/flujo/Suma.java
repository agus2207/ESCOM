/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flujo;

/**
 *
 * @author Maurcio10
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Suma extends Remote {
     public ArrayList<byte[]> archivo(String file,int a,int b) throws RemoteException;
}