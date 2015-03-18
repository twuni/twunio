package io.twun.auth;

import java.util.HashMap;
import java.util.Map;

public class SimpleAuthenticator implements Authenticator {

	private final Map<CharSequence, CharSequence> credentials = new HashMap<CharSequence, CharSequence>();

	@Override
	public void checkCredential( CharSequence identity, CharSequence secret ) {
		CharSequence p = credentials.get( identity );
		if( p == null ) {
			throw new UnknownIdentityException();
		}
		if( !p.equals( secret ) ) {
			throw new AuthenticationException();
		}
	}

	public void put( CharSequence identity, CharSequence secret ) {
		credentials.put( identity, secret );
	}

}
