package com.cherkashyn.vitaliy.devices.ethernet.socket3.commands;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.lang3.ArrayUtils;

public class CommandOnlineCheck {
	private final static byte[] commandRequest=new byte[]{0x01};
	private final static byte[] expectedResponse=new byte[]{0x01};
	
	public boolean execute(Socket socket) throws IOException{
		byte[] response=CommandUtils.doRequest(socket, commandRequest);
		ArrayUtils.isSameLength(expectedResponse, response);
		return CommandUtils.isArrayEquals(expectedResponse, response);
	}
	
}
