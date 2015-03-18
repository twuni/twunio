package io.twun;

import io.twun.util.Base64;

import java.io.IOException;

public class EventHandler {

	private static Logger defaultLogger() {
		return new Logger( EventHandler.class.getName() );
	}

	private final Logger log;

	public EventHandler() {
		this( defaultLogger() );
	}

	public EventHandler( Logger logger ) {
		log = logger;
	}

	public void onConnected( Connection connection ) {
		log.debug( "CONNECT C/%s", connection.id() );
	}

	protected void onData( Connection connection, byte [] data ) {
		log.debug( "DATA C/%s %s", connection.id(), new String( Base64.encode( data ) ) );
	}

	public void onDisconnected( Connection connection ) {
		log.debug( "DISCONNECT C/%s", connection.id() );
		connection.cleanup();
	}

	public void onException( Throwable exception ) {
		log.error( "ERROR T/%s %s", "onException", exception.getClass().getSimpleName(), exception.getLocalizedMessage() );
	}

	public final void onReadRequested( Connection connection ) {
		try {
			onData( connection, connection.read() );
		} catch( DisconnectedException exception ) {
			onDisconnected( connection );
		} catch( IOException exception ) {
			onException( exception );
		}
	}

	public final void onWriteRequested( Connection connection ) {
		try {
			connection.flush();
		} catch( IOException exception ) {
			onException( exception );
		}
	}

}
