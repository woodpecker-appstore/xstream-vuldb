package me.gv7.woodpecker.vuldb;

import me.gv7.woodpecker.plugin.IPayloadGenerator;
import me.gv7.woodpecker.plugin.IPluginHelper;
import me.gv7.woodpecker.plugin.IVulPlugin;
import me.gv7.woodpecker.plugin.IVulPluginCallbacks;
import me.gv7.woodpecker.vuldb.payload.DnslogPayloadGenerator;
import me.gv7.woodpecker.vuldb.payload.YsoserialPayloadGenerator;

import java.util.ArrayList;
import java.util.List;

public class XStreamVulPlugin implements IVulPlugin {
    public static IVulPluginCallbacks callbacks;
    public static IPluginHelper pluginHelper;

    public void VulPluginMain(IVulPluginCallbacks iVulPluginCallbacks) {
        this.callbacks = iVulPluginCallbacks;
        this.pluginHelper = iVulPluginCallbacks.getPluginHelper();
        callbacks.setVulPluginName("XStream RCE exploit");
        callbacks.setVulPluginVersion("0.1.0");
        callbacks.setVulPluginAuthor("woodpecker-org");
        callbacks.setVulName("XStream反序列化payload生成插件");
        callbacks.setVulCVSS(9.8);
        callbacks.setVulAuthor("");
        callbacks.setVulSeverity("high");
        callbacks.setVulScope("<= 1.4.17");
        callbacks.setVulDescription("");
        callbacks.setVulCategory("综合");
        callbacks.setVulProduct("xstream");
        List<IPayloadGenerator> payloadGeneratorList = new ArrayList<IPayloadGenerator>();
        payloadGeneratorList.add(new DnslogPayloadGenerator());
        payloadGeneratorList.add(new YsoserialPayloadGenerator());
        callbacks.registerPayloadGenerator(payloadGeneratorList);
    }
}
