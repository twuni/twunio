package io.twun;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Transporter {

	public static class State {

		public final Map<CharSequence, Queue> pendingSend = new HashMap<CharSequence, Queue>();

	}

	private static Logger defaultLogger() {
		return new Logger( Transporter.class.getName() );
	}

	private static void send( Writable target, Object object ) throws IOException {
		if( target.write( object.toString().getBytes() ) < 0 ) {
			throw new IOException( "Packet not sent." );
		}
	}

	private final Logger log;
	private final Map<CharSequence, Writable> targets = new HashMap<CharSequence, Writable>();

	private final State state = new State();

	public Transporter() {
		this( defaultLogger() );
	}

	public Transporter( Logger logger ) {
		log = logger;
	}

	public void available( Writable target, CharSequence targetID ) {
		available( target, targetID, null );
	}

	public void available( Writable target, CharSequence targetID, Queue sent ) {
		targets.put( targetID, target );
		flush( targetID, sent );
	}

	private void enqueue( Object packet, CharSequence targetID ) {

		Queue queue = state.pendingSend.get( targetID );

		if( queue == null ) {
			queue = new Queue( targetID );
			state.pendingSend.put( targetID, queue );
		}

		queue.add( packet );

	}

	public void flush( CharSequence targetID ) {
		flush( targetID, null );
	}

	public void flush( CharSequence targetID, Queue sent ) {
		Writable target = targets.get( targetID );
		if( target != null ) {
			Queue queue = state.pendingSend.get( targetID );
			if( queue != null ) {
				synchronized( queue ) {
					Iterator<Object> it = queue.iterator();
					while( it.hasNext() ) {
						Object packet = it.next();
						try {
							send( target, packet );
							if( sent != null ) {
								sent.add( packet );
							}
							it.remove();
						} catch( IOException exception ) {
							log.info( "DELAY %s", packet );
						} catch( BufferOverflowException exception ) {
							log.info( "DELAY %s", packet );
						}
					}
				}
			}
		}
	}

	private void restore( Collection<Queue> pendingSend ) {
		for( Queue queue : pendingSend ) {
			state.pendingSend.put( queue.id(), queue );
		}
	}

	public void restore( State state ) {
		restore( state.pendingSend.values() );
	}

	public State save() {
		return state;
	}

	public void transport( Object packet, CharSequence targetID ) {
		transport( packet, targetID, null );
	}

	public void transport( Object packet, CharSequence targetID, Queue sent ) {
		enqueue( packet, targetID );
		flush( targetID, sent );
	}

	public void unavailable( CharSequence targetID ) {
		targets.remove( targetID );
	}

}
