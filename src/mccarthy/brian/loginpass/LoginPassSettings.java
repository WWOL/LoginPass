package mccarthy.brian.loginpass;

import net.canarymod.Logman;

/**
 * Class full of static properties
 * @author Brian McCarthy
 *
 */
public class LoginPassSettings {
    public static boolean BLOCK_ATTACK = true;
    public static boolean BLOCK_DAMAGE = true;
    public static boolean BLOCK_MOVEMENT = true;
    public static boolean BLOCK_CHAT = true;
    public static boolean BLOCK_ITEM_DROP = true;
    public static boolean BLOCK_ITEM_PICKUP = true;
    public static boolean BLOCK_COMMAND = true;
    public static boolean BLOCK_LEFTCLICK = true;
    public static boolean BLOCK_RIGHTCLICK = true;
    
    public static String DIGEST_TYPE = "SHA-1";
    
    public static boolean CHECK_IP = false;
    
    public static void load() {
        writeDefaults();
        BLOCK_ATTACK = LoginPass.props.getBoolean("attack", true);
        BLOCK_DAMAGE = LoginPass.props.getBoolean("damage", true);
        BLOCK_MOVEMENT = LoginPass.props.getBoolean("movement", true);
        BLOCK_CHAT = LoginPass.props.getBoolean("chat", true);
        BLOCK_ITEM_DROP = LoginPass.props.getBoolean("item_drop", true);
        BLOCK_ITEM_PICKUP = LoginPass.props.getBoolean("item_pickup", true);
        BLOCK_COMMAND = LoginPass.props.getBoolean("command", true);
        BLOCK_LEFTCLICK = LoginPass.props.getBoolean("left_click", true);
        BLOCK_RIGHTCLICK = LoginPass.props.getBoolean("right_click", true);
        
        DIGEST_TYPE = LoginPass.props.getString("digest_type", "SHA-1");
        
        CHECK_IP = LoginPass.props.getBoolean("check_ip", false);
    }
    private static void writeDefaults() {
        if (!LoginPass.props.containsKey("attack")) {
            LoginPass.props.setBoolean("attack", true);
        }
        if (!LoginPass.props.containsKey("damage")) {
            LoginPass.props.setBoolean("damage", true);
        }
        if (!LoginPass.props.containsKey("movement")) {
            LoginPass.props.setBoolean("movement", true);
        }
        if (!LoginPass.props.containsKey("chat")) {
            LoginPass.props.setBoolean("chat", true);
        }
        if (!LoginPass.props.containsKey("item_drop")) {
            LoginPass.props.setBoolean("item_drop", true);
        }
        if (!LoginPass.props.containsKey("item_pickup")) {
            LoginPass.props.setBoolean("item_pickup", true);
        }
        if (!LoginPass.props.containsKey("command")) {
            LoginPass.props.setBoolean("command", true);
        }
        if (!LoginPass.props.containsKey("left_click")) {
            LoginPass.props.setBoolean("left_click", true);
        }
        if (!LoginPass.props.containsKey("right_click")) {
            LoginPass.props.setBoolean("right_click", true);
        }
        if (!LoginPass.props.containsKey("digest_type")) {
            LoginPass.props.setString("digest_type", "SHA-1");
        }
        if (!LoginPass.props.containsKey("check_ip")) {
            LoginPass.props.setBoolean("check_ip", false);
        }
        
        try {
            LoginPass.props.save();
        } catch (Exception e) {
            Logman.logWarning(LoginPass.SPRE + "Exception while saving defaults to file.");
        }
    }
}
