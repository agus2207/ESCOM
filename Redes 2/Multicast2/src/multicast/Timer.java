/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Maurcio10
 */
public class Timer extends Thread{
    private final CMulticastThread client;
    
    public Timer(CMulticastThread client) {
        this.client = client;
    }
    
    @Override
    public void run(){
        for(;;){
            ArrayList<Pair<Integer,String>> list = client.getList();
            for(int i = 0; i<list.size();i++){
                if(!client.getVivos()[list.get(i).getKey() - 9000]) client.eraseNode(i);
            }
            client.restartVivos();
            try {
                java.lang.Thread.sleep(11000);
            }
            catch(Exception e) { }
        }
    }
}
