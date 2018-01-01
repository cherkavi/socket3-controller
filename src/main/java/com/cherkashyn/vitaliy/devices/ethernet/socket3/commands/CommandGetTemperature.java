package com.cherkashyn.vitaliy.devices.ethernet.socket3.commands;

import java.io.IOException;
import java.net.Socket;
import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;

public class CommandGetTemperature {
	private final static byte[] request=new byte[]{0x42};
	private final static long delay=100L;
	
	public byte execute(Socket socket) throws IOException{
		byte[] response=CommandUtils.doRequest(socket, request, delay);
		if(response.length!=2){
			System.err.println(MessageFormat.format("Can't recognize command: {0} {1}", response.length, ArrayUtils.toString(response)));
			throw new IOException();
		}
		return response[1];
	}
}
