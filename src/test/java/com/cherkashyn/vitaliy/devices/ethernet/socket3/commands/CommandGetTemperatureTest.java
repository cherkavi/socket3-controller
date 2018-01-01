package com.cherkashyn.vitaliy.devices.ethernet.socket3.commands;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cherkashyn.vitaliy.devices.ethernet.socket3.TestConstants;

public class CommandGetTemperatureTest implements TestConstants{
	
	private Socket socket;
	
	@Before
	public void init() throws UnknownHostException, IOException{
		socket=new Socket(host, port);
	}
	
	@After
	public void destroy() throws IOException{
		this.socket.close();
	}
	
	@Test
	public void test1() throws UnknownHostException, IOException{
		CommandGetTemperature command=new CommandGetTemperature();
		byte value=command.execute(this.socket);
		System.out.println(MessageFormat.format("Temperature: {0}",value));
		Assert.assertTrue(value>0);
	}
	
	@Test
	public void test2() throws UnknownHostException, IOException{
		CommandGetTemperature command=new CommandGetTemperature();
		int stressIterationCount=50;
		for(int index=0;index<stressIterationCount; index++){
			byte value=command.execute(this.socket);
			System.out.println(MessageFormat.format("Temperature: {0}/{1} : {2}",index, stressIterationCount, value));
			Assert.assertTrue(value>0);
		}
	}
}
