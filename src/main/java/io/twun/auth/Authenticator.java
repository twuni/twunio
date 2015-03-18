package io.twun.auth;

public interface Authenticator {

	public void checkCredential( CharSequence identity, CharSequence secret );

}
