package com.example.figalitaho.bilkentlive;

import android.os.StrictMode;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 2.1
 * ==========================================================
 * This class makes a secure fast and reliable connection
 * to the offerings page
 * ==========================================================
 * Date: 28/4/2015
 *
 * */

public class HttpsClient{
    String httpsUrl;
    URL url;
    HttpsURLConnection con;


    public HttpsClient(String URL){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            httpsUrl = URL;
            url = new URL(httpsUrl);
            con = (HttpsURLConnection)url.openConnection();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printHttpsCert(){
        if(con!=null){
            try {
                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : "
                            + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : "
                            + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String returnContent(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if(con!=null){
            String input = "";
            String thisString;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((thisString =br.readLine()) != null){
                    input += thisString;
                }
                br.close();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
            return input;
        }
        else
            return null;
    }
    public String getOfferings(){
        String nextLine;
        int counter = 0;
        String websiteContents = "";
        //writes as long as it's not null in a new file or even in console
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while( (nextLine = in.readLine()) != null ){
                websiteContents += nextLine+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return websiteContents;
    }
}