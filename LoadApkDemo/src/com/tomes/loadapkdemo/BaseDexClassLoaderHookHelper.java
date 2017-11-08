package com.tomes.loadapkdemo;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

/**
 * @author Tomes
 * 
 */
public class BaseDexClassLoaderHookHelper {
	/**保守型的
	 * @param cl
	 * @param apkFile
	 * @param optDexFile
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static void patchClassLoader(ClassLoader cl, File apkFile,
			File optDexFile) throws Exception {
		//---- 获取 BaseDexClassLoader : pathList
		 Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
		 pathListField.setAccessible(true);
		 	//获取到对象DexPathList pathList
		 Object pathList = pathListField.get(cl);
		 
		//----- 获取 PathList: Element[] dexElements
		 Class<?> DexPathListClass = pathList.getClass();
		 Field dexElementsField = DexPathListClass.getDeclaredField("dexElements");
		 dexElementsField.setAccessible(true);
		 //获取到对象Element[] dexElements
		 Object[] dexElements = (Object[]) dexElementsField.get(pathList);
		 
		//----- Element 类型
		 Class<?> type = dexElements.getClass().getComponentType();
		// 创建一个数组, 用来替换原始的数组
		Object[] newElements=(Object[]) Array.newInstance(type, dexElements.length+1);
		
		// 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
		Constructor<?> constructor = type.getConstructor(File.class,boolean.class,File.class,DexFile.class);
		Object newInstance = constructor.newInstance(apkFile,false,apkFile,DexFile.loadDex(apkFile.getCanonicalPath(), optDexFile.getAbsolutePath(), 0));
		Object[] toAdd=new Object[]{newInstance};
        // 把原始的elements复制进去
		System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
		//插件的那个element复制进去
		System.arraycopy(toAdd, 0, newElements, dexElements.length, toAdd.length);
        // 替换dexElementsField里的dexElements
		dexElementsField.set(pathList, newElements);
	}
}
