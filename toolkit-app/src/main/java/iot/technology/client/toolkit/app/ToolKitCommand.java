package iot.technology.client.toolkit.app;

import iot.technology.client.toolkit.app.config.LogLevelConfig;
import iot.technology.client.toolkit.coap.command.CoapCommand;
import iot.technology.client.toolkit.mqtt.command.MqttCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * @author mushuwei
 */
@CommandLine.Command(
		name = "toolkit",
		version = "0.0.1",
		header = "IoT Client Toolkit CLI",
		optionListHeading = "%nOptions are:%n",
		requiredOptionMarker = '*',
		description = "A handy @|fg(red),bold toolkit|@ for IoT developers and learners.",
		footerHeading = "%nCopyright (c) 2019-2022, IoT Technology",
		footer = "%nDeveloped by James mu",
		mixinStandardHelpOptions = true,
		subcommands = {
				CoapCommand.class,
				MqttCommand.class
		})
public class ToolKitCommand implements Callable<Integer> {
	final Integer SUCCESS = 0;


	public static void main(String[] args) {
		LogLevelConfig.setLogLevel();
		int exitStatus = new CommandLine(new ToolKitCommand())
				.setCaseInsensitiveEnumValuesAllowed(true)
				.execute("coap", "get", "coap://coap.me/sink");
		System.exit(exitStatus);
	}

	public Integer call() throws Exception {
		System.out.println("A handy toolkit for IoT developers and learners.");
		return SUCCESS;
	}
}
