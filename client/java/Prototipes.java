package client.java;

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
public class Prototipes {
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
	
	/**
	 * Sum command responses
	 */
	public static class SubstractionResp {
		public Integer total;

		public int getResponse() {
			return total;
		}
	}

	/**
	 * Sum command parameters
	 */
	public static class SubstractionParams {
		public Integer a = 0;
		public Integer b = 0;

		public SubstractionParams(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		// Method for debug
		public String getArg() {
			return "Substraction: " + a + " - " + b;
		}
	}	
}