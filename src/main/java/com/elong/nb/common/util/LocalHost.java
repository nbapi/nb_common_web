package com.elong.nb.common.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;

import sun.management.VMManagement;

public class LocalHost {
	 private static NetworkInterface localNetworkInterface;
	    private static String hostName;
	    private static String localIPAddress = "";
	    
	    private static String macAddress="";
	    private static int jvmPID;
	    
	    static {
	        try {
	            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	            InetAddress address;
	            while (interfaces.hasMoreElements()) {
	                NetworkInterface networkInterface = interfaces.nextElement();
	                if (!networkInterface.getName().equals("lo")) {
	                    localNetworkInterface = networkInterface;
	                    Enumeration<InetAddress> enumeration = localNetworkInterface.getInetAddresses();
	                    while (enumeration.hasMoreElements()) {
	                        address = enumeration.nextElement();
	                        if (address instanceof Inet4Address) {
	                            localIPAddress = address.getHostAddress();
	                            break;
	                        }
	                    }
	                    if (!enumeration.hasMoreElements()) {
	                        if (StringUtils.isBlank(localIPAddress)) {
	                            try {
	                                localIPAddress = InetAddress.getLocalHost().getHostAddress();
	                            } catch (UnknownHostException e) {
	                            }
	                        }
	                    }
	                    break;
	                }
	            }

	        } catch (SocketException e) {
	        }

	        try {
	            // 多数情况是因为本机ping计算机名 无法正确获取本机, 可以采用修改hosts的方式添加进去
	            hostName = InetAddress.getLocalHost().getHostName();
	        } catch (UnknownHostException e) {
	            if (StringUtils.isBlank(hostName)) {
	                hostName = "error_" + localIPAddress;
	            }
	        }
	        
	        try {
				NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
				byte[] macBytes = networkInterface.getHardwareAddress();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < macBytes.length; i++) {
					sb.append(String.format("%02X%s", macBytes[i], i < macBytes.length - 1 ? "-" : ""));
				}
				macAddress= sb.toString();
			} catch (Exception e) {
				macAddress="";
			}
	        
	        try {
				RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
				Field jvm = runtime.getClass().getDeclaredField("jvm");
				jvm.setAccessible(true);
				@SuppressWarnings("restriction")
				VMManagement mgmt = (VMManagement) jvm.get(runtime);
				Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
				pidMethod.setAccessible(true);
				jvmPID = (Integer) pidMethod.invoke(mgmt);
				
			} catch (Exception e) {
				jvmPID=-1;
			}
	    }

	    /**
	     * 取本机的网卡接口
	     *
	     * @return LocalNetworkInterface
	     */
	    public static NetworkInterface getLocalNetworkInterface() {
	        return localNetworkInterface;
	    }

	    /**
	     * 取本机的机器名称
	     *
	     * @return MachineName
	     */
	    public static String getMachineName() {
	        return hostName;
	    }

	    /**
	     * 获取本机ip
	     *
	     * @return LocalIP
	     */
	    public static String getLocalIP() {
	        return localIPAddress;
	    }
	    /**
		 * 获取localhost MAC地址
		 * @return 
		 * @throws Exception
		 */
		public static String getMACAddress() {
			return macAddress;
		}

		/**
		 * 获取当前JVM 的进程ID
		 * @return
		 */
		public static int getJVMPid() {
			return jvmPID;
		}
	    
}
