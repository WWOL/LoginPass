package mccarthy.brian.loginpass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Actions that are used across multiple commands / listeners
 * @author Brian McCarthy
 *
 */
public class LoginPassActions {

	private PropertiesFile passFile;
	private PropertiesFile ipFile;
	private List<String> authedPlayers;
	private MessageDigest md;

	public LoginPassActions() {
		passFile = Configuration.getPluginConfig(LoginPass.getInstance(), "Passwords");
		ipFile = Configuration.getPluginConfig(LoginPass.getInstance(), "IPs");
		
		passFile.addHeaderLines("These passwords are stored hashed with the digest specified in "
				+ "the settings file.");
		passFile.addHeaderLines("You can not undo this to recover a users password.");
		passFile.addHeaderLines("To reset a password for a user delete the property here or use "
				+ "the change command");

		ipFile.addHeaderLines("Add ip address in a comma seperate list with player name as key");
		
		authedPlayers = new ArrayList<String>();

		try {
			md = MessageDigest.getInstance(LoginPassSettings.digestType);
		} catch (Exception e) {
			try {
				md = MessageDigest.getInstance("SHA-1");
				LoginPass.getInstance().getLogman().warn("Unknown digest type. Using SHA-1.");
				LoginPass.getInstance().getLogman().warn("If this is incorrect change it now as "
						+ "users passwords will be invalidated when changing digest type");
			} catch (NoSuchAlgorithmException e1) {
				throw new RuntimeException("Unable to get any message digest instance!");
			}
		}
	}

	public boolean hasSavedPass(String player) {
		return passFile.containsKey(player);
	}

	public boolean isLoggedIn(String player) {
		return authedPlayers.contains(player);
	}

	public boolean login(String player, String pass) {
		String savedHash = passFile.getString(player);
		String passHash = toHex(hash(player, pass));
		if (passHash.equals(savedHash)) {
			authedPlayers.add(player);
			return true;
		}
		return false;
	}

	public void logout(String player) {
		authedPlayers.remove(player);
	}
	
	public void setPass(String player, String pass) {
		String passHash = toHex(hash(player, pass));
		passFile.setString(player, passHash);
		passFile.save();
	}

	public byte[] hash(String player, String pass) {
		byte[] b1 = md.digest(player.getBytes());
		byte[] b2 = md.digest(pass.getBytes());
		byte[] b3 = new byte[b1.length + b2.length];
		for (int i = 0; i < b1.length; i++) {
			b3[i] = b1[i];
		}
		for (int i = 0; i < b2.length; i++) {
			b3[b1.length + i] = b2[i];
		}
		return md.digest(b3);
	}

	public String toHex(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			sb.append(hex[(b & 0xf0) >> 4]);
			sb.append(hex[b & 0x0f]);
		}
		return sb.toString();
	}

	public boolean ipMatches(String name, String ip) {
		String ips = ipFile.getString(name, "");
		if (ips.equals("")) {
			if (!LoginPassSettings.strictIpCheck) {
				return true;
			}
		}
		for (String currIp : ips.split(",")) {
			if (currIp.trim().equals("")) {
				continue;
			}
			if (currIp.equals("*") || currIp.equals(ip)) {
				return true;
			} 
			String currPart = "";
			for (String part : ip.split("\\.")) {
				currPart += part;
				if (currIp.equals(currPart + ".*")) {
					return true;
				}
				currPart += ".";
			}
		}
		return false;
	}

}