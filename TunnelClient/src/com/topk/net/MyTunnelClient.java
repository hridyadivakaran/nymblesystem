/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topk.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harris Bastin
 */
public class MyTunnelClient implements Runnable {

    private Object object = null;
    private Object respondObj = null;
    private boolean isRunning = false;

    public MyTunnelClient(Object object) {
        isRunning = true;
        this.object = object;
    }
    //   final String URL = "http://localhost:8084/TunnelWeb/TunnelServlet";
    final String URL = "http://localhost:8084/TunnelWeb/ObjectTunnelServlet";

    @Override
    public void run() {

        try {
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/octect-stream");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(object);
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = urlConnection.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            respondObj = ois.readObject();
            System.out.println(respondObj);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyTunnelClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyTunnelClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyTunnelClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Object getResult() {
        return respondObj;
    }
    /*  public void sendData() throws MalformedURLException, IOException {
    URL url = new URL(URL);
    URLConnection urlConnection = url.openConnection();
    urlConnection.setRequestProperty("Content-Type", "application/octect-stream");
    urlConnection.setDoOutput(true);
    urlConnection.setDoInput(true);        
    OutputStream outputStream = urlConnection.getOutputStream();   
    outputStream.write("hello This is a client Msg".getBytes());
    outputStream.flush();
    outputStream.close();
    InputStream inputStream = urlConnection.getInputStream();
    byte[] data = new byte[inputStream.available()];
    inputStream.read(data);
    System.out.println(new String(data));
    
    }
     */
    /*
    public void sendObject() throws MalformedURLException, IOException, ClassNotFoundException {
    URL url = new URL(URL);
    URLConnection urlConnection = url.openConnection();
    urlConnection.setRequestProperty("Content-Type", "application/octect-stream");
    urlConnection.setDoOutput(true);
    urlConnection.setDoInput(true);        
    OutputStream outputStream = urlConnection.getOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
    oos.writeObject("Top K test content ");
    outputStream.flush();
    outputStream.close();
    InputStream inputStream = urlConnection.getInputStream();
    ObjectInputStream ois = new ObjectInputStream(inputStream);
    Object readObject = ois.readObject(); 
    System.out.println(readObject);
    
    }
     */
}
