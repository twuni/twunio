package io.twun;

import java.nio.channels.SocketChannel;

public interface ConnectionFactory {

	public Connection createConnection( SocketChannel channel, Dispatcher dispatcher, EventHandler eventHandler );

}
