package com.elong.nb.common.util;

import java.net.InetAddress;

public class HostNameUtil {

	public static String getHostNameForLiunx() {  
        try {  
            return (InetAddress.getLocalHost()).getHostName();  
        } catch (Exception uhe) {  
            return "UnknownHost";  
        }  
    }  
}
