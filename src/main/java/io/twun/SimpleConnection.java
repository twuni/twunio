package io.twun;

import java.nio.channels.SocketChannel;

public class SimpleConnection extends Connection {

	public SimpleConnection( SocketChannel client, Dispatcher dispatcher, EventHandler eventHandler ) {
		super( client, dispatcher, eventHandler );
	}

	public SimpleConnection( SocketChannel client, Dispatcher dispatcher, EventHandler eventHandler, int inputBufferSize, int outputBufferSize ) {
		super( client, dispatcher, eventHandler, inputBufferSize, outputBufferSize );
	}

	public SimpleConnection( SocketChannel client, Dispatcher dispatcher, EventHandler eventHandler, int inputBufferSize, int outputBufferSize, Logger logger ) {
		super( client, dispatcher, eventHandler, inputBufferSize, outputBufferSize, logger );
	}

	@Override
	public Object state() {
		return this;
	}

}