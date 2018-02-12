package com.elong.nb.common.util;

import java.util.Random;

public class RandomJudgeUtil {
	
	/**
	 * 百分之i的概率为true
	 * @param i
	 * @return
	 */
	public static boolean judge(int i) {
		Random r = new Random();
		if (r.nextInt(100) < i)
			return true;
		return false;
	}
}
