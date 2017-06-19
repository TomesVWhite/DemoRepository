package com.tomes.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;
import java.util.Set;

/*
 * Properties:属性集合类。是一个可以和IO流相结合使用的集合类。
 * Properties 可保存在流中或从流中加载。属性列表中每个键及其对应值都是一个字符串。 
 * 
 * 是Hashtable的子类，说明是一个Map集合。
 */
public class Demo {

	public static void main(String[] args) throws IOException {
		// m2();
//		m3();
		m4();
	}

	/*
	 * 我有一个文本文件(prop.txt)，我知道数据是键值对形式的，但是不知道内容是什么。
	 * 请写一个程序判断是否有“fruit”这样的键存在，如果有就改变其实为”orange”
	 * 
	 * 分析：
	 * 		A:把文件中的数据加载到集合中(b,c完全没有必要去执行)
	 * 		B:遍历集合，获取得到每一个键
	 * 		C:判断键是否有为"fruit"的，如果有就修改其值为"orange"
	 * 		D:把集合中的数据重新存储到文件中
	 */
	private static void m4() throws IOException {
		//没有prop.txt的时候就先建一个，给一个前置条件
//		storeDemo();
		//
		Properties properties=new Properties();
		Reader reader=new FileReader("prop.txt");
		// 把文件中的数据加载到集合中
		properties.load(reader);
		reader.close();
		/*Set<String> set = properties.stringPropertyNames();
		for (String key : set) {
			if(key.equals("fruit")){
				properties.setProperty("fruit", "orange");
				break;
			}
		}*/
		properties.setProperty("fruit", "orange");
		Writer writer=new FileWriter("prop.txt");
		// 把集合中的数据重新存储到文件中
		properties.store(writer, null);
		writer.close();
	}

	private static void m3() throws IOException {
		loadOrStore();
	}

	/*
	 * 这里的集合必须是Properties集合： public void load(Reader reader):把文件中的数据读取到集合中
	 * public void store(Writer writer,String comments):把集合中的数据存储到文件
	 * 
	 * 单机版游戏： 进度保存和加载。 三国群英传，三国志，仙剑奇侠传...
	 * 
	 * 吕布=1 方天画戟=1
	 */
	private static void loadOrStore() throws IOException {

		storeDemo();
		// loadDemo();
	}

	private static void loadDemo() throws IOException {
		Properties properties = new Properties();
		// public void load(Reader reader):把文件中的数据读取到集合中
		// 注意：这个文件的数据必须是键值对形式
		Reader reader = new FileReader("prop.txt");
		properties.load(reader);
		reader.close();
		System.out.println("properties:" + properties);
	}

	private static void storeDemo() throws IOException {
		Properties properties = new Properties();
		properties.setProperty("fruit", "apple");
		properties.setProperty("computer", "hp");
		properties.setProperty("mobilePhone", "iphone");
		// public void store(Writer writer,String comments):把集合中的数据存储到文件
		Writer writer = new FileWriter("prop.txt");
		// comments:在生成的文件里位于第一行注释，如：#我就是这么屌，不要批注时置为null
		properties.store(writer, null);
		writer.close();

	}

	private static void m1() {
		// 作为Map集合的使用
		// 下面这种用法是错误的，一定要看API，如果没有<>，就说明该类不是一个泛型类,在使用的时候就不能加泛型
		// Properties<String, String> prop = new Properties<String, String>();
		Properties properties = new Properties();
		// 添加元素
		properties.put("fruit", "apple");
		properties.put("computer", "hp");
		properties.put("mobilePhone", "iphone");
		// 遍历集合
		Set<Object> set = properties.keySet();
		for (Object object : set) {
			Object value = properties.get(object);
			System.out.println(object + "=" + value);
		}
	}

	/*
	 * 特殊功能： public Object setProperty(String key,String value)：添加元素 public
	 * String getProperty(String key):获取元素 public Set<String>
	 * stringPropertyNames():获取所有的键的集合
	 */
	private static void m2() {
		// 创建集合对象
		Properties properties = new Properties();
		// 添加元素
		properties.setProperty("fruit", "apple");
		properties.setProperty("computer", "hp");
		properties.setProperty("mobilePhone", "iphone");
		// public Set<String> stringPropertyNames():获取所有的键的集合
		Set<String> set = properties.stringPropertyNames();
		for (String key : set) {
			String value = properties.getProperty(key);
			System.out.println(key + "=" + value);
		}
	}
}
