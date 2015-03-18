package io.twun.auth;

import io.twun.Logger;

public class AutomaticAuthenticator extends SimpleAuthenticator {

	private static Logger defaultLogger() {
		return new Logger( AutomaticAuthenticator.class.getName() );
	}

	private final Logger log;

	public AutomaticAuthenticator() {
		this( defaultLogger() );
	}

	public AutomaticAuthenticator( Logger logger ) {
		log = logger;
	}

	@Override
	public void checkCredential( CharSequence identity, CharSequence secret ) {
		try {
			super.checkCredential( identity, secret );
		} catch( UnknownIdentityException exception ) {
			log.info( "CREATE USER %s", identity );
			put( identity, secret );
		}
	}

}
