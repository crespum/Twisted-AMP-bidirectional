package client.java;

import com.twistedmatrix.amp.*;
import com.twistedmatrix.internet.*;

public class ClientAMP extends AMP {
	Reactor _reactor = null;

	public ClientAMP(Reactor reactor) {
		_reactor = reactor;

		/** Define a local method that might be called remotely. */
		localCommand("Substraction", new Commands.SubstractionCommand());
	}


	/** Methods that might be called remotely must be public */
	public Commands.SubstractionResp substraction(int a, int b) {
		int res = a - b;
		System.out.println("I did a substraction: " + a + " - " + b + " = " + res);

		Commands.SubstractionResp sr = new Commands.SubstractionResp();
		sr.total = res;

		return sr;
	}

	/**
	 * Class that handles the results of a command invoked remotely. The
	 * callback method is called with a populated response class. Returned
	 * object is handed to chained callback if it exists.
	 */
	class SumRes implements Deferred.Callback<Commands.SumResp> {
		public Object callback(Commands.SumResp retval) {

			System.out.println("Remote did a sum: 5 + 3 = "
					+ retval.getResponse());
			return null;
		}
	}

	/**
	 * Class that handles the problem if a remote command goes awry. The
	 * callback method is called with a populated Failure class. Returned object
	 * is handed to chained errback if it exists.
	 */
	class SumErr implements Deferred.Callback<Deferred.Failure> {
		public Object callback(Deferred.Failure err) {

			// Class tc = err.trap(Exception.class);
			// System.out.println("error: " + err.get());
			err.get().printStackTrace();
			System.exit(0);

			return null;
		}
	}

	/**
	 * The example the client and server use the same method and classes to
	 * exchange data, but the client initiates this process upon connection
	 */
	@Override
	public void connectionMade() {
		System.out.println("connected");

		Commands.SumParams rp = new Commands.SumParams(5, 3);
		Commands.SumResp cr = new Commands.SumResp();

		System.out.println("Asking remote for a sum");
		AMP.RemoteCommand<Commands.SumResp> remote = new RemoteCommand<Commands.SumResp>(
				"Sum", rp, cr);
		Deferred dfd = remote.callRemote();
		dfd.addCallback(new SumRes());
		dfd.addErrback(new SumErr());
	}

	@Override
	public void connectionLost(Throwable reason) {
		System.out.println("Connection lost: " + reason);
	}

	public static void main(String[] args) throws Throwable {
		final Reactor reactor = Reactor.get();
		reactor.connectTCP("127.0.0.1", 1234, new ClientFactory() {
			public IProtocol buildProtocol(Object addr) {
				return new ClientAMP(reactor);
			}

			public void clientConnectionFailed(IConnector connector,
					Throwable reason) {
				System.out.println("Connection failed: " + reason);
				System.exit(0);
			}

			@Override
			public void startedConnecting(IConnector connector) {
				System.out.println("Connecting");
			}

			@Override
			public void clientConnectionLost(IConnector connector,
					Throwable reason) {
				System.out.println("Connection lost: " + reason);
			}
		});

		reactor.run();
	}
}
