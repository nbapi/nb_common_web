package com.elong.nb.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertiesUtils {
	public static Properties loadProperties(String contentRoot,String filePath){
		String fileDir = contentRoot + filePath;
		Properties props = new Properties();
		FileInputStream istream = null;
		try {
			istream = new FileInputStream(fileDir);
			props.load(istream);
		} catch (IOException e) {
			System.out.println("[读取Properties文件异常]"+"IO异常："+fileDir);
			e.printStackTrace();
		}finally{
			if(istream != null){
				try {
					istream.close();
					istream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	private static String getClassesPath() {
		return PropertiesUtils.class.getResource("/").getPath();
	}
	public static Properties loadProperties(String fileName){
		return loadProperties(getClassesPath(),fileName);
	}
	public static void main(String[] args) {
		System.out.println(PropertiesUtils.class.getResource("/").getPath());
	}
}
