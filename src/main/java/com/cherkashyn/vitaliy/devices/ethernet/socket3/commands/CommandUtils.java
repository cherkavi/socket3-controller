package com.cherkashyn.vitaliy.devices.ethernet.socket3.commands;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.ArrayUtils;


public class CommandUtils {
	public static int limit=200;
	
	private CommandUtils(){
	}
	
	public static boolean isArrayEquals(byte[] first, byte[] second){
		if(first==null && second==null){
			return true;
		}
		if(first==null || second==null){
			return false;
		}
		if(!ArrayUtils.isSameLength(first, second)){
			return false;
		}
		for(int index=0;index<first.length;index++){
			if(first[index]!=second[index]){
				return false;
			}
		}
		return true;
	}

	public static byte[] doRequest(InputStream inputStream, 
			   OutputStream outputStream, 
			   byte[] request) throws IOException{
		return doRequest(inputStream, outputStream, request, -1);
	}
	public static byte[] doRequest(InputStream inputStream, 
								   OutputStream outputStream, 
								   byte[] request,
								   long milisecondsDelay) throws IOException{
		DataInputStream is=new DataInputStream(inputStream);
		DataOutputStream out=new DataOutputStream(outputStream);
		// write request
		out.write(request);
		if(milisecondsDelay>0){
			try {
				TimeUnit.MILLISECONDS.sleep(milisecondsDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// get response
		ByteArrayOutputStream bos=new ByteArrayOutputStream(limit);
		try{
			while(is.available()>0){
				bos.write(is.readByte());
			}
			if(limit==bos.size()){
				throw new IOException("response is too big");
			}
		}catch(EOFException ex){
			// end of file
		}catch(IOException ex){
			throw ex;
		}
		return bos.toByteArray();
	}
	
	public static byte[] doRequest(Socket socket, byte[] request) throws IOException{
		return doRequest(socket.getInputStream(), socket.getOutputStream(), request);
	}

	public static byte[] doRequest(Socket socket, byte[] request, long miliseconds) throws IOException{
		return doRequest(socket.getInputStream(), socket.getOutputStream(), request, miliseconds);
	}
}
