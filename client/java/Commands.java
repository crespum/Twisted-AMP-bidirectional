package client.java;

import com.twistedmatrix.amp.LocalCommand;

/**
 * This class contains all the prototipes of the commands that can be executed
 * either on the server or on the client side. Each command is divided into two
 * public (and static for readability) classes containing the params and the
 * response that generate those params.
 * 	 
 * All command classes and all its variables must be public.
 * 
 * @author xabicrespog@gmail.com
 *
 */
public class Commands {
	// Sum command will be executed on the server side
	// -----------------------------------------------
	/**
	 * Sum command responses
	 */
	public static class SumResp {
		public Integer total;

		public int getResponse() {
			return total;
		}
	}

	/**
	 * Sum command parameters
	 */
	public static class SumParams {
		public Integer a = 0;
		public Integer b = 0;

		public SumParams(int a, int b) {
			this.a = a;
			this.b = b;
		}

		// Method for debug
		public String getArgs() {
			return "Sum: " + a + " + " + b;
		}
	}
	
	// Substraction command will be executed on the server side
	// -----------------------------------------------	
	/**
	 * Substraction command responses
	 */
	public static class SubstractionResp {
		public Integer total;

		public int getResponse() {
			return total;
		}
	}

	/**
	 * This class contains the name and parameters of a local command. It
	 * defines the name, the parameter names in order, and parameter classes.
	 * Class variables must be public and match local command arguments.
	 */
	public static class SubstractionCommand extends LocalCommand {
		public int a;
		public int b;

		public SubstractionCommand() {
			super("substraction", new String[] { "a", "b" });
		}
	}
}