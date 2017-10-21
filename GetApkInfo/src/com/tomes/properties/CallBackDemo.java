package com.tomes.properties;


/**非常简单的回调demo
 * @author Administrator
 *
 */
public class CallBackDemo {

	public static void main(String[] args) {
		System.out.println("开始-》");
		//callback
		callback(new Tomes());
		System.out.println("end-》");
	}
	
	private static void callback(Buyer buyer) {
		buyer.buy();
	}
}

class Tomes implements Buyer{
	@Override
	public void buy() {
		System.out.println("Tomes have buy");
	}
}
interface Buyer{
	void buy();
}