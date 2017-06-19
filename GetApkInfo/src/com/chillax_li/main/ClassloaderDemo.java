package com.chillax_li.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**测试classLoader效用
 * @author Tomes
 *
 */
public class ClassloaderDemo {
	public static void main(String[] args) {
		m2();
	}
	
	
	private static void m1() {
		MyClassLoader myClassLoader = new MyClassLoader();
		try {
			//该处填写的name为class完整的名字（包括包名）才能找到该类，如：TD就只会找全名为TD的类，自然找不到
			//当寻找不存在工程里或者不存在导入并加入路径的jar里的class时，会找不到class
			Class<?> class1 = myClassLoader.loadClass("com.rover12421.ijm.updatepacktool.TD");
			System.out.println(class1.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void m2() {
		MyClassLoader myClassLoader = new MyClassLoader();
		try {
			//不存在导入并加入路径的jar里的class时，会找不到class，此时代码加载jar后，才能找到。该处表示加载 libs 下面的 IjmUpdatePackTools_fat.jar
			URL[] urls = new URL[] {new URL("file:libs/IjmUpdatePackTools_fat.jar")};
			URLClassLoader loader = new URLClassLoader(urls, myClassLoader.getParent());
			//这里不能使用myClassLoader，他会找不到com.rover12421.ijm.updatepacktool.TD，只能用加载了IjmUpdatePackTools_fat.jar
			//的loader，才能找到com.rover12421.ijm.updatepacktool.TD
			Class<?> class1 = loader.loadClass("com.rover12421.ijm.updatepacktool.TD");
			System.out.println(class1.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
