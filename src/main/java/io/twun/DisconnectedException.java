package io.twun;

import java.io.IOException;

public class DisconnectedException extends IOException {

	private static final long serialVersionUID = 1L;

	public DisconnectedException() {
		// Default constructor.
	}

	public DisconnectedException( String message ) {
		super( message );
	}

	public DisconnectedException( String message, Throwable cause ) {
		super( message, cause );
	}

	public DisconnectedException( Throwable cause ) {
		super( cause );
	}

}
