package iot.technology.client.toolkit.nb.service.processor.mobile;

import iot.technology.client.toolkit.common.rule.ProcessContext;
import iot.technology.client.toolkit.common.rule.TkProcessor;
import iot.technology.client.toolkit.common.utils.ColorUtils;
import iot.technology.client.toolkit.nb.service.mobile.MobileDeviceService;
import iot.technology.client.toolkit.nb.service.mobile.domain.BaseMobileResponse;
import iot.technology.client.toolkit.nb.service.mobile.domain.MobileConfigDomain;
import iot.technology.client.toolkit.nb.service.processor.MobProcessContext;

import java.util.List;

/**
 * @author mushuwei
 */
public class MobDelDeviceByImeiProcessor implements TkProcessor {

	private final MobileDeviceService mobileDeviceService = new MobileDeviceService();

	@Override
	public boolean supports(ProcessContext context) {
		return (context.getData().startsWith("del") || context.getData().startsWith("delete"));
	}

	@Override
	public void handle(ProcessContext context) {
		List<String> arguArgs = List.of(context.getData().split(" "));
		if (arguArgs.size() != 2) {
			System.out.format(ColorUtils.blackBold("argument:%s is illegal"), context.getData());
			System.out.format(" " + "%n");
			return;
		}
		String imei = arguArgs.get(1);
		MobProcessContext mobProcessContext = (MobProcessContext) context;
		MobileConfigDomain mobileConfigDomain = mobProcessContext.getMobileConfigDomain();
		BaseMobileResponse baseMobileResponse = mobileDeviceService.delDeviceByImei(mobileConfigDomain, imei);
		if (baseMobileResponse.isSuccess()) {
			System.out.format(ColorUtils.blackBold("imei:%s delete success"), imei);
			System.out.format(" " + "%n");
		}
	}
}