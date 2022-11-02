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
package iot.technology.client.toolkit.common.constants;

import iot.technology.client.toolkit.common.utils.StringUtils;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * @author mushuwei
 */
public class PubData implements Serializable {

	private String topic;

	private int qos;

	private String payload;

	public static PubData validate(String data) {
		ResourceBundle bundle = ResourceBundle.getBundle(StorageConstants.LANG_MESSAGES);
		PubData pubData = new PubData();
		String topic = "";
		int qos = 0;
		if (StringUtils.isBlank(data)) {
			throw new IllegalArgumentException(bundle.getString("param.error"));
		}
		if (!data.contains("=")) {
			throw new IllegalArgumentException(bundle.getString("param.error"));
		}
		int equalIndex = data.indexOf("=");
		String topicAndQos = data.substring(0, equalIndex);
		if (StringUtils.isBlank(topicAndQos)) {
			throw new IllegalArgumentException(bundle.getString("param.error"));
		}
		if (!topicAndQos.contains(":")) {
			topic = topicAndQos;
		} else {
			int divide = topicAndQos.indexOf(":");
			String qosStr = topicAndQos.substring(divide + 1);
			String topicStr = topicAndQos.substring(0, divide);
			if (StringUtils.isBlank(topicStr)) {
				throw new IllegalArgumentException(bundle.getString("param.error"));
			}
			topic = topicStr;
			if (StringUtils.isBlank(qosStr) || !StringUtils.isNumeric(qosStr)) {
				throw new IllegalArgumentException(bundle.getString("param.error"));
			}
			Integer qosValue = Integer.parseInt(qosStr);
			if (qosValue.equals(0)
					|| qosValue.equals(1)
					|| qosValue.equals(2)) {
				qos = qosValue;
			} else {
				throw new IllegalArgumentException(bundle.getString("mqtt.qos.error"));
			}
		}
		String payload = data.substring(equalIndex + 1);
		if (StringUtils.isBlank(payload)) {
			throw new IllegalArgumentException(bundle.getString("param.error"));
		}
		pubData.setTopic(topic);
		pubData.setQos(qos);
		pubData.setPayload(payload);
		return pubData;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
}