package mccarthy.brian.loginpass;

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
    public final static String VER = "1.0";
    public final static String SPRE = "[" + NAME + "] ";
    public final static String PRE = TextFormat.Blue + SPRE + TextFormat.Gold;
    //public final static String CONFIG = "plugins" + File.separator + "config" + File.separator + NAME + File.separator;
    public final static String CONFIG = "plugins/config/"+ NAME + "/";
    public static ConfigurationFile props;
    public static ConfigurationFile passes;
    LoginPassListener listener = new LoginPassListener();
//new ConnectionHook(
    //TODO This is a mess.
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
        
        /*File file = new File(CONFIG);
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Could not create configuration dir!");
        }
        File file2 = new File(CONFIG + "passes.txt");
        File file3 = new File(CONFIG + NAME + ".properties");
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (!file3.exists()) {
                file3.createNewFile();
            }
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Could not create configuration files!");
        }*/
        try {
            props = new ConfigurationFile(CONFIG + NAME + ".properties", true);
            passes = new ConfigurationFile(CONFIG + "passes.txt", true);
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Could not create configuration file!");
            e.printStackTrace();
        }
        LoginPassSettings.load();
    }

    public void disable() {
        LOG.info(SPRE + NAME + " by " + AUTHOR + " Ver:" + VER + " disabled!");
    }

}
