package com.elong.nb.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesUtils {
	
	public static Properties loadProperties(String contentRoot, String filePath) {
		String fileDir = contentRoot + filePath;
		Properties props = new Properties();
		FileInputStream istream = null;
		try {
			istream = new FileInputStream(fileDir);
			props.load(istream);
		} catch (IOException e) {
			System.out.println("[读取Properties文件异常]" + "IO异常：" + fileDir);
			e.printStackTrace();
		} finally {
			if (istream != null) {
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

	public static Properties loadProperties(String fileName) {
		return loadProperties(getClassesPath(), fileName);
	}

	/**
	 * 
	 * 生成AOS要求的value为$$key形式的properties文件
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void generateAOSPropertiesFile() throws FileNotFoundException, IOException {
		// 遍历文件夹路径
		String filePath = "/Users/user/Desktop/properties";
		// 获取所有文件名
		List<String> fileList = getFiles(filePath);
		// 遍历所有properties文件
		for (String fileName : fileList) {
			Properties properties = new Properties();
			properties.load(new FileInputStream(fileName));
			Enumeration enumeration = properties.propertyNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				properties.setProperty(key, "$$" + key);
			}
			// 输出
			properties.store(new FileOutputStream(fileName), "修改时间：" + new Date());
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(PropertiesUtils.class.getResource("/").getPath());
		generateAOSPropertiesFile();
	}

	/**
	 * 
	 * 递归查找根目录下所有文件
	 *
	 * @param path
	 * @return
	 */
	private static List<String> getFiles(String path) {
		List<String> fileList = new ArrayList<String>();
		File root = new File(path);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			} else {
				fileList.add(file.getAbsolutePath());
			}
		}
		return fileList;
	}
}
