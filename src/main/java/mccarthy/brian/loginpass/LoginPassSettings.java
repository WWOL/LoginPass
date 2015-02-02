package mccarthy.brian.loginpass;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Contain and load various properties
 * @author Brian McCarthy
 *
 */
public class LoginPassSettings {

	private static PropertiesFile props;
	
	public static String digestType = "SHA-1";
	public static boolean checkIp = false;
	public static boolean strictIpCheck = false;
	
	public static boolean denyAllPermissions = false;
	public static boolean denyAttack = true;
	public static boolean denyChat = false;
	public static boolean denyCommand = true;
	public static boolean denyDamage = true;
	public static boolean denyItemDrop = true;
	public static boolean denyItemPickup = true;
	public static boolean denyLeftClick = true;
	public static boolean denyMovement = true;
	public static boolean denyRightClick = true;
	
	public static void setup() {
		props = Configuration.getPluginConfig(LoginPass.getInstance(), "Settings");
		if (props.getBoolean("useDefaults", false)) {
			// Do not load these values from the properties file, use defaults
			return;
		}
		digestType = props.getString("digestType", digestType);
		checkIp = props.getBoolean("checkIp", checkIp);
		strictIpCheck = props.getBoolean("strictIpCheck", strictIpCheck);
		if (strictIpCheck && !checkIp) {
			LoginPass.getInstance().getLogman().warn("checkIp should be true if strictIpCheck is "
					+ "true, else strictIpCheck does nothing!");
		}
		
		denyAllPermissions = props.getBoolean("denyAllPermissions", denyAllPermissions);
		denyAttack = props.getBoolean("denyAttack", denyAttack);
		denyChat = props.getBoolean("denyChat", denyChat);
		denyCommand = props.getBoolean("denyCommand", denyCommand);
		denyDamage = props.getBoolean("denyDamage", denyDamage);
		denyItemDrop = props.getBoolean("denyItemDrop", denyItemDrop);
		denyItemPickup = props.getBoolean("denyItemPickup", denyItemPickup);
		denyLeftClick = props.getBoolean("denyLeftClick", denyLeftClick);
		denyMovement = props.getBoolean("denyMovement", denyMovement);
		denyRightClick = props.getBoolean("denyRightClick", denyRightClick);
		
		props.save();
	}
}
