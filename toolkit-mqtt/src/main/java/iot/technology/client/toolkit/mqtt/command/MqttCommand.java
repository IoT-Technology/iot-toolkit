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
package iot.technology.client.toolkit.mqtt.command;

import iot.technology.client.toolkit.common.constants.ExitCodeEnum;
import iot.technology.client.toolkit.common.constants.HelpVersionGroup;
import iot.technology.client.toolkit.mqtt.command.sub.MqttCallCommand;
import iot.technology.client.toolkit.mqtt.command.sub.MqttDescribeCommand;
import iot.technology.client.toolkit.mqtt.command.sub.MqttPublishCommand;
import iot.technology.client.toolkit.mqtt.command.sub.MqttSubscribeCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * @author mushuwei
 */
@CommandLine.Command(
		name = "mqtt",
		requiredOptionMarker = '*',
		header = "@|fg(Cyan),bold ${bundle:mqtt.header}|@",
		description = "@|fg(Cyan),italic ${bundle:mqtt.description}|@",
		optionListHeading = "%n${bundle:general.option}:%n",
		subcommands = {
				MqttCallCommand.class,
				MqttDescribeCommand.class,
				MqttPublishCommand.class,
				MqttSubscribeCommand.class
		},
		footerHeading = "%nCopyright (c) 2019-2022, ${bundle:general.copyright}",
		footer = "%nDeveloped by mushuwei",
		versionProvider = iot.technology.client.toolkit.common.constants.VersionInfo.class)
public class MqttCommand implements Callable<Integer> {

	@CommandLine.ArgGroup
	HelpVersionGroup helpVersionGroup;

	@Override
	public Integer call() {
		return ExitCodeEnum.SUCCESS.getValue();
	}
}
