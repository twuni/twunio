package io.twun;

import java.nio.channels.SocketChannel;

public class SimpleConnectionFactory implements ConnectionFactory {

	public static SimpleConnectionFactory getInstance() {
		return INSTANCE;
	}

	private static final SimpleConnectionFactory INSTANCE = new SimpleConnectionFactory();

	@Override
	public Connection createConnection( SocketChannel channel, Dispatcher dispatcher, EventHandler eventHandler ) {
		return new SimpleConnection( channel, dispatcher, eventHandler );
	}

}