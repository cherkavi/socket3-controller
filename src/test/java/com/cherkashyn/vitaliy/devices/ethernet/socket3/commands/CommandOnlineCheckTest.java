package com.cherkashyn.vitaliy.devices.ethernet.socket3.commands;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.Test;

import com.cherkashyn.vitaliy.devices.ethernet.socket3.TestConstants;

public class CommandOnlineCheckTest implements TestConstants{
	
	@Test
	public void test() throws UnknownHostException, IOException{
		CommandOnlineCheck command=new CommandOnlineCheck();
		Assert.assertTrue(command.execute(new Socket(host, port)));
	}
}
