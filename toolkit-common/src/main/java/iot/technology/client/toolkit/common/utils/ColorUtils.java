/*
 * Copyright © 2019-2022 The Toolkit Authors
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

import picocli.CommandLine;

/**
 * @author mushuwei
 */
public class ColorUtils {

	public static String greenItalic(String text) {
		return colorItalic(text, "green");
	}


	public static String blackFaint(String text) {
		return CommandLine.Help.Ansi.AUTO.string("@|faint,black" + " " + text + "|@");
	}

	public static String blackBold(String text) {
		return CommandLine.Help.Ansi.AUTO.string("@|faint,bold" + " " + text + "|@");
	}

	public static String colorItalic(String text, String color) {
		return CommandLine.Help.Ansi.AUTO.string("@|italic," + color + " " + text + "|@");
	}
}