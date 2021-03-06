package org.springside.modules.utils.base;

/**
 * 关于Properties的工具类
 * 
 * 1. Boolean.readBoolean(name) 不够用, 进行扩展. 其他Integer.read()等都没问题.
 * 
 * 2. 简单的合并系统变量(-D)，环境变量 和默认值，以系统变量优先，在未引入Commons Config时使用.
 * 
 * @author calvin
 */
public class PropertiesUtil {

	/////////// Boolean.readBoolean()扩展 ///////////////

	/**
	 * 读取Boolean类型的系统变量，为空时返回null，而不是false.
	 */
	public static Boolean readBoolean(String name) {
		String stringResult = System.getProperty(name);
		if (stringResult != null) {
			return Boolean.valueOf(stringResult);
		}
		return null; //NOSONAR
	}

	/**
	 * 读取Boolean类型的系统变量，为空时返回默认值，因为默认值为Boolean，返回的也是Boolean
	 */
	public static Boolean readBoolean(String name, Boolean defaultVal) {
		String stringResult = System.getProperty(name);
		if (stringResult != null) {
			return Boolean.parseBoolean(stringResult);
		} else {
			return defaultVal;
		}
	}

	/**
	 * 读取Boolean类型的系统变量，为空时返回默认值。因为默认值为Boolean，返回的也是boolean
	 */
	public static boolean readBoolean(String name, boolean defaultVal) {
		String stringResult = System.getProperty(name);
		if (stringResult != null) {
			return Boolean.parseBoolean(stringResult);
		} else {
			return defaultVal;
		}
	}

	/////////// 简单的合并系统变量(-D)，环境变量 和默认值，以系统变量优先.///////////////

	/**
	 * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
	 */
	public static String readString(String propertyName, String envName, String defaultValue) {
		checkEnvName(envName);
		String propertyValue = System.getProperty(propertyName);
		if (propertyValue != null) {
			return propertyValue;
		} else {
			propertyValue = System.getenv(envName);
			return propertyValue != null ? propertyValue : defaultValue;
		}
	}

	/**
	 * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
	 */
	public static Integer readInt(String propertyName, String envName, Integer defaultValue) {
		checkEnvName(envName);
		Integer propertyValue = NumberUtil.toIntObject(System.getProperty(propertyName));
		if (propertyValue != null) {
			return propertyValue;
		} else {
			propertyValue = NumberUtil.toIntObject(System.getenv(envName));
			return propertyValue != null ? propertyValue : defaultValue;
		}
	}

	/**
	 * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
	 */
	public static Long readLong(String propertyName, String envName, Long defaultValue) {
		checkEnvName(envName);
		Long propertyValue = NumberUtil.toLongObject(System.getProperty(propertyName));
		if (propertyValue != null) {
			return propertyValue;
		} else {
			propertyValue = NumberUtil.toLongObject(System.getenv(envName));
			return propertyValue != null ? propertyValue : defaultValue;
		}
	}

	/**
	 * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
	 */
	public static Double readDouble(String propertyName, String envName, Double defaultValue) {
		checkEnvName(envName);
		Double propertyValue = NumberUtil.toDoubleObject(System.getProperty(propertyName));
		if (propertyValue != null) {
			return propertyValue;
		} else {
			propertyValue = NumberUtil.toDoubleObject(System.getenv(envName));
			return propertyValue != null ? propertyValue : defaultValue;
		}
	}

	/**
	 * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
	 */
	public static Boolean readBoolean(String propertyName, String envName, Boolean defaultValue) {
		checkEnvName(envName);
		Boolean propertyValue = toBooleanObject(System.getProperty(propertyName));
		if (propertyValue != null) {
			return propertyValue;
		} else {
			propertyValue = toBooleanObject(System.getenv(envName));
			return propertyValue != null ? propertyValue : defaultValue;
		}
	}

	private static Boolean toBooleanObject(String str) {
		if (str == null) {
			return null;
		} else {
			return Boolean.valueOf(str);
		}
	}

	/**
	 * 检查环境变量名不能有'.'，在linux下不支持
	 */
	private static void checkEnvName(String envName) {
		if (envName == null || envName.indexOf('.') != -1) {
			throw new IllegalArgumentException("envName " + envName + " has dot which is not valid");
		}
	}
}
