package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.vuldb.XStreamVulPlugin;

public class WoodpeckerPluginManager implements IPluginManager {
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks pluginManagerCallbacks) {
        pluginManagerCallbacks.registerVulPlugin(new XStreamVulPlugin());
    }
}
