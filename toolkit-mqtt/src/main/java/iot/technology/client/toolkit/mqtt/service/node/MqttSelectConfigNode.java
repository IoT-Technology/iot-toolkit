package iot.technology.client.toolkit.mqtt.service.node;

import iot.technology.client.toolkit.common.constants.GlobalConstants;
import iot.technology.client.toolkit.common.constants.MqttSettingsCodeEnum;
import iot.technology.client.toolkit.common.constants.NodeTypeEnum;
import iot.technology.client.toolkit.common.constants.StorageConstants;
import iot.technology.client.toolkit.common.rule.NodeContext;
import iot.technology.client.toolkit.common.rule.TkNode;
import iot.technology.client.toolkit.common.utils.ColorUtils;
import iot.technology.client.toolkit.common.utils.JsonUtils;
import iot.technology.client.toolkit.mqtt.config.MqttSettings;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mushuwei
 */
public class MqttSelectConfigNode implements TkNode {

	ResourceBundle bundle = ResourceBundle.getBundle(StorageConstants.LANG_MESSAGES);

	@Override
	public void prePrompt(NodeContext context) {
		if (!context.getPromptData().isEmpty()) {
			List<String> configList = context.getPromptData();
			Stream.iterate(0, i -> i + 1).limit(configList.size()).forEach(i -> {
				MqttSettings settings = JsonUtils.jsonToObject(configList.get(i), MqttSettings.class);
				System.out.format(ColorUtils.greenItalic(i + "   : " + Objects.requireNonNull(settings).getName()) + "%n");
			});
		}
		System.out.format(ColorUtils.greenItalic("new" + ":" + bundle.getString("mqtt.new.config.desc")) + "%n");
	}

	@Override
	public boolean check(NodeContext context) {
		List<String> configList = context.getPromptData();
		List<String> indexList = Stream.iterate(0, i -> i + 1)
				.limit(configList.size())
				.map(String::valueOf)
				.collect(Collectors.toList());
		boolean matchIndex = indexList.stream().anyMatch(index -> index.equals(context.getData()));
		if (matchIndex && context.getData().equals("new")) {
			return true;
		}
		System.out.format(ColorUtils.redError(bundle.getString("param.error")));
		return false;
	}

	@Override
	public String nodePrompt() {
		return bundle.getString(MqttSettingsCodeEnum.SELECT_CONFIG.getCode() + GlobalConstants.promptSuffix) +
				GlobalConstants.promptSeparator;
	}

	@Override
	public String nextNode(NodeContext context) {
		if (context.getData().equals("new")) {
			return MqttSettingsCodeEnum.SETTINGS_NAME.getCode();
		}
		if (context.getType().equals(NodeTypeEnum.MQTT_PUBLISH.getType())) {
			return MqttSettingsCodeEnum.PUBLISH_MESSAGE.getCode();
		}
		return MqttSettingsCodeEnum.SUBSCRIBE_MESSAGE.getCode();
	}

	@Override
	public String getValue(NodeContext context) {
		if (context.getData().equals("new")) {
			return context.getData();
		}
		return context.getPromptData().get(Integer.parseInt(context.getData()));
	}
}