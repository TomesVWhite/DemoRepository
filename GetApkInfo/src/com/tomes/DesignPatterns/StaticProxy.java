package com.tomes.DesignPatterns;

/**java设计模式之代理模式，静态代理
 * @author Tomes
 *
 */
public class StaticProxy {

	public static void main(String[] args) {
		//4、我想听歌，而且是听周杰伦唱一首歌
		ZhouJieLun jieLun=new ZhouJieLun();
		//但我没他的电话，怎么办？找他经纪人帮忙联系让他来唱歌。
		singerProxy proxy=new singerProxy(jieLun);
		//通过经纪人的联系，老周开始唱歌了。
		proxy.sing();
	}
}

/**1、歌手接口
 * @author Administrator
 *
 */
interface Singer{
	/**
	 * 唱歌这个服务
	 */
	void sing();
}

/**2、业务接口实现，周杰伦也是歌手，也会唱歌
 * @author Administrator
 *
 */
class ZhouJieLun implements Singer{

	@Override
	public void sing() {
		System.out.println("周杰伦开始唱歌了。");
	}
}

/**3、代理类，可以通过找经纪人让他来请歌手唱歌。
 * @author Administrator
 *
 */
class singerProxy implements Singer{
	
	/**
	 * 经纪人代理的周杰伦，只有他才拥有他的电话，别人找不到周杰伦。同时他手下有很多歌手，你想要哪个给你找哪个。
	 */
	private Singer singer;
	
	 /**  
     * 重写默认构造函数 
     * @param accountImpl :真正要执行业务的对象 
     */    
    public singerProxy(Singer singer) {    
        this.singer =singer;    
    }  
    
	@Override
	public void sing() {
		//你点谁的歌，经纪人就让哪个歌手唱歌。
		singer.sing();
	}
	
}
