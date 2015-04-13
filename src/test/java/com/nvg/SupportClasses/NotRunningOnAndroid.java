package com.nvg.SupportClasses;

import com.nvg.SupportClasses.ConditionalIgnoreRule.IgnoreCondition;

public class NotRunningOnAndroid implements IgnoreCondition {

	@Override
	public boolean isSatisfied() {
		return !System.getProperty("platform", "ios").startsWith("android");
	}
	
}
