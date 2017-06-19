package com.chillax_li.util;

import java.io.*;

/**
 * Created by rover12421 on 10/27/15.
 */
public class StreamForwarder extends Thread {
    private final InputStream mIn;
    private final OutputStream mOut;

    public StreamForwarder(InputStream in, OutputStream out) {
        mIn = in;
        mOut = out;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(mIn));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(mOut));
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void Input2Output(InputStream mIn, OutputStream mOut){
    	try {
    		BufferedReader in = new BufferedReader(
                    new InputStreamReader(mIn));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(mOut));
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
