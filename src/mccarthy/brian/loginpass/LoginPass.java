package mccarthy.brian.loginpass;

import java.io.File;
import java.util.logging.Logger;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.TextFormat;
import net.canarymod.config.ConfigurationFile;
import net.canarymod.hook.Hook.Type;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.Priority;

/**
 * 
 * @author Brian McCarthy
 *
 */
public class LoginPass extends Plugin {
    public final static Logger LOG = Logger.getLogger("Minecraft");
    public final static String NAME = "LoginPass";
    public final static String AUTHOR = "WWOL";
    public final static String VER = "1.2";
    public final static String SPRE = "[" + NAME + "] ";
    public final static String PRE = TextFormat.Blue + SPRE + TextFormat.Gold;
    public final static String CONFIG = "plugins" + File.separator + "config" + File.separator + NAME + File.separator;
    public static ConfigurationFile props;
    public static ConfigurationFile passes;
    public static ConfigurationFile ips;
    LoginPassListener listener = new LoginPassListener();

    public void enable() {
        LOG.info(SPRE + NAME + " by " + AUTHOR + " Ver:" + VER + " enabled!");
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.COMMAND);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.PLAYER_MOVE);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.CHAT);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.ITEM_DROP);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.ITEM_PICK_UP);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.PLAYER_CONNECT);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.BLOCK_LEFTCLICKED);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.BLOCK_RIGHTCLICKED);
        Canary.hooks().registerListener(listener, this, Priority.NORMAL, Type.LOGINCHECKS);

        try {
            props = new ConfigurationFile(CONFIG + NAME + ".properties");
            passes = new ConfigurationFile(CONFIG + "passes.txt");
            ips = new ConfigurationFile(CONFIG + "ips.txt");
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Could not create configuration file!");
            e.printStackTrace();
        }
        LoginPassSettings.load();
    }

    public void disable() {
        LOG.info(SPRE + NAME + " by " + AUTHOR + " Ver:" + VER + " disabled!");
        try {
            props.save();
            passes.save();
            ips.save();
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Could not save file!");
            e.printStackTrace();
        }

    }

}
