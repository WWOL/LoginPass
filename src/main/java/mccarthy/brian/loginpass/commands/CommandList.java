package mccarthy.brian.loginpass.commands;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

/**
 * List of all commands, used for registering with the system
 * @author Brian McCarthy
 *
 */
public class CommandList implements CommandListener {

	@Command(aliases = {"loginpass", "lp"},
			description = "LoginPass base command",
			permissions = {"loginpass"},
			toolTip = "/loginpass <login <pass> | create <pass> <pass> | change <pass> <pass> [player] | setip <player> <ip>>",
			version = 2,
			min = 2,
			max = 3)
	public void warnCommand(MessageReceiver caller, String[] parameters) {
		//new WarnCommand().execute(caller, parameters);
	}
	
	@Command(aliases = {"login"},
			description = "Login using password",
			permissions = {"loginpass.login"},
			toolTip = "/loginpass login <pass>",
			version = 2,
			parent = "loginpass",
			min = 1,
			max = 1)
	public void loginCommand(MessageReceiver caller, String[] parameters) {
		new LoginCommand().execute(caller, parameters);
	}
	
	@Command(aliases = {"create", "register"},
			description = "Create a pass to login with",
			permissions = {"loginpass.create"},
			toolTip = "/loginpass create <pass> <pass>",
			version = 2,
			parent = "loginpass",
			min = 2,
			max = 2)
	public void createPassCommand(MessageReceiver caller, String[] parameters) {
		new CreatePassCommand().execute(caller, parameters);
	}
	
	@Command(aliases = {"change"},
			description = "Change a users (or your own) password",
			permissions = {"loginpass.change"},
			toolTip = "/loginpass change <pass> <pass> [player]",
			version = 2,
			parent = "loginpass",
			min = 2,
			max = 3)
	public void changePassCommand(MessageReceiver caller, String[] parameters) {
		new ChangePassCommand().execute(caller, parameters);
	}
	
	@Command(aliases = {"setip", "ip"},
			description = "Set an ip for a player",
			permissions = {"loginpass.setip"},
			toolTip = "/loginpass setip <player> <ip>",
			version = 2,
			parent = "loginpass",
			min = 2,
			max = 2)
	public void setIpCommand(MessageReceiver caller, String[] parameters) {
		new SetIpCommand().execute(caller, parameters);
	}
	
}