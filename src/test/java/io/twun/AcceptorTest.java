package io.twun;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class AcceptorTest extends Assert {

	@Test
	public void learningTest() throws IOException {
		Acceptor.listen( 2020, new EventHandler() ).interrupt();
	}

}
