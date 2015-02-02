package mccarthy.brian.loginpass.commands;

import mccarthy.brian.loginpass.LoginPass;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * loginpass create command
 * @author Brian McCarthy
 *
 */
public class ChangePassCommand {

	public void execute(MessageReceiver caller, String[] parameters) {
		if (!(caller instanceof Player) && parameters.length != 3) {
			caller.notice("When not a player please specify a player argument.");
			return;
		}
		String target;
		if (parameters.length == 3) {
			if (caller.hasPermission("loginpass.change.other")) {
				target = parameters[2];
			} else {
				caller.notice("You do not have permission to change others passwords!");
				return;
			}
		} else {
			target = caller.getName();
		}
		if (caller instanceof Player) {
			if (!LoginPass.getInstance().getActions().isLoggedIn(caller.getName())) {
				caller.notice("You must be logged in to do that!");
				return;
			}	
		}
		if (!parameters[0].equals(parameters[1])) {
			caller.notice("Pass' do not match! Please try again.");
			return;
		}
		LoginPass.getInstance().getActions().setPass(target, parameters[0]);
		caller.message("Changed pass for " + target + ".");
	}

}