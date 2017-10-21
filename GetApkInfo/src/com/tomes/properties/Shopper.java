package com.tomes.properties;
/**简单的回调demo
 * @author Tomes
 *
 */
public class Shopper{
	public static void main(String[] args) {
		Star star=new Star();
		star.singForShopper(new IProxy() {	
			@Override
			public void sing() {
				System.out.println("我想听七里香");
				
			}
		});
	}
}

class Star {
	
	public void singForShopper(IProxy proxy){
		System.out.println("请开始点歌");
		proxy.sing();
		System.out.println("马上开唱");
	}
}

interface IProxy {

	void sing();
}
