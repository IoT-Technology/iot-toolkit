/*
 * Copyright © 2019-2025 The Toolkit Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iot.technology.client.toolkit.common.utils;

import iot.technology.client.toolkit.common.constants.StorageConstants;
import iot.technology.client.toolkit.common.constants.TopicAndQos;
import iot.technology.client.toolkit.common.rule.TkNode;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author mushuwei
 */
public class ObjectUtils {

	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			return arrayEquals(o1, o2);
		}
		return false;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	private static boolean arrayEquals(Object o1, Object o2) {
		if (o1 instanceof Object[] && o2 instanceof Object[]) {
			return Arrays.equals((Object[]) o1, (Object[]) o2);
		}
		if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
			return Arrays.equals((boolean[]) o1, (boolean[]) o2);
		}
		if (o1 instanceof byte[] && o2 instanceof byte[]) {
			return Arrays.equals((byte[]) o1, (byte[]) o2);
		}
		if (o1 instanceof char[] && o2 instanceof char[]) {
			return Arrays.equals((char[]) o1, (char[]) o2);
		}
		if (o1 instanceof double[] && o2 instanceof double[]) {
			return Arrays.equals((double[]) o1, (double[]) o2);
		}
		if (o1 instanceof float[] && o2 instanceof float[]) {
			return Arrays.equals((float[]) o1, (float[]) o2);
		}
		if (o1 instanceof int[] && o2 instanceof int[]) {
			return Arrays.equals((int[]) o1, (int[]) o2);
		}
		if (o1 instanceof long[] && o2 instanceof long[]) {
			return Arrays.equals((long[]) o1, (long[]) o2);
		}
		if (o1 instanceof short[] && o2 instanceof short[]) {
			return Arrays.equals((short[]) o1, (short[]) o2);
		}
		return false;
	}

	public static Object setValue(Object obj, String propName, String value) {
		try {
			Field f = obj.getClass().getDeclaredField(propName);
			f.setAccessible(true);
			f.set(obj, value);
		} catch (Exception e) {
			return null;
		}
		return obj;
	}

	public static boolean topicAndQosValidator(String topicAndQos, TopicAndQos domain) {
		ResourceBundle bundle = ResourceBundle.getBundle(StorageConstants.LANG_MESSAGES);
		if (!topicAndQos.contains(":")) {
			domain.setTopic(topicAndQos);
			return true;
		}
		int divide = topicAndQos.indexOf(":");
		String qosStr = topicAndQos.substring(divide + 1);
		String topicStr = topicAndQos.substring(0, divide);
		if (StringUtils.isBlank(topicStr)) {
			System.out.format(ColorUtils.redError(bundle.getString("param.error")));
			return false;
		}
		domain.setTopic(topicStr);
		if (StringUtils.isBlank(qosStr) || !StringUtils.isNumeric(qosStr)) {
			System.out.format(ColorUtils.redError(bundle.getString("param.error")));
			return false;
		}
		int qosValue = Integer.parseInt(qosStr) < 0 || Integer.parseInt(qosStr) > 2 ? 0 : Integer.parseInt(qosStr);
		domain.setQos(qosValue);
		return true;
	}

	public static TkNode initComponent(String node) {
		TkNode tkNode = null;
		if (node != null) {
			try {
				Class<?> componentClazz = Class.forName(node);
				tkNode = (TkNode) (componentClazz.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return tkNode;
	}
}
