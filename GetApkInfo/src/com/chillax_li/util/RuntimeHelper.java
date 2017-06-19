package com.chillax_li.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Created by rover12421 on 10/27/15.
 */
public class RuntimeHelper {
    public static void exec(OutputStream outputStream, String... cmd) throws RuntimeHelperException {
        try {
            Process ps = Runtime.getRuntime().exec(cmd);

            StreamForwarder.Input2Output(ps.getInputStream(), outputStream);
            StreamForwarder.Input2Output(ps.getErrorStream(), outputStream);
 
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
    public static void exec(String... cmd) throws RuntimeHelperException {
        try {
            Process ps = Runtime.getRuntime().exec(cmd);

            new StreamForwarder(ps.getInputStream(), System.err).start();
            new StreamForwarder(ps.getErrorStream(), System.err).start();
            if (ps.waitFor() != 0) {
                throw new RuntimeHelperException(
                        "could not exec command: " + Arrays.toString(cmd));
            }
        } catch (IOException ex) {
            throw new RuntimeHelperException(
                    "could not exec command: " + Arrays.toString(cmd), ex);
        } catch (InterruptedException ex) {
            throw new RuntimeHelperException(
                    "could not exec command: " + Arrays.toString(cmd), ex);
        }
    }
    
    public static void exec(boolean showLog, String... cmd) throws RuntimeHelperException {
        try {
            Process ps = Runtime.getRuntime().exec(cmd);         
            if (doWaitFor(ps, null, System.err) != 0) {
                throw new RuntimeHelperException(
                        "could not exec command: " + Arrays.toString(cmd));
            }
        } catch (IOException ex) {
            throw new RuntimeHelperException(
                    "could not exec command: " + Arrays.toString(cmd), ex);
        } 
    }
    
    public static int doWaitFor(Process p, OutputStream inputForward, OutputStream errorForward) {  
        int exitValue = -1; // returned to caller when p is finished  
        try {  
      
            InputStream in = p.getInputStream();
            InputStream err = p.getErrorStream();
            boolean finished = false; // Set to true when p is finished  
      
            while(!finished){  
                try {  
                	BufferedWriter inputWriter = null;
                	BufferedWriter errorWriter = null;
                	if(inputForward != null){
                		inputWriter = new BufferedWriter(new OutputStreamWriter(inputForward));	
                	}
                	if(errorForward != null){
                		errorWriter = new BufferedWriter(new OutputStreamWriter(errorForward));
                	}
                	 
                	 
                    while( in.available() > 0) {  
                        // Print the output of our system call  
               
                        Character c = new Character( (char)in.read()); 
                     
                        
                        //System.out.print( c);
                        if(inputWriter != null)
                            inputWriter.write(c);
                    }  
                    while( err.available() > 0) {  
                        // Print the output of our system call  
                        Character c = new Character( (char)err.read()); 
                        //System.out.print( c);
                        if(errorWriter != null)
                            errorWriter.write(c);
                    }  
      
                    // Ask the process for its exitValue. If the process  
                    // is not finished, an IllegalThreadStateException  
                    // is thrown. If it is finished, we fall through and  
                    // the variable finished is set to true.  
                    exitValue = p.exitValue();
                    finished = true;  
                }  
                catch (IllegalThreadStateException e) {  
                    // Process is not finished yet;  
                    // Sleep a little to save on CPU cycles  
                    Thread.currentThread();
                   
                }  
            }  
        }  
        catch (Exception e) {  
            // unexpected exception! print it out for debugging...  
            System.err.println( "doWaitFor();: unexpected exception - " +   e.getMessage());
        }  
      
        // return completion status to caller  
        return exitValue;  
    }  
}
