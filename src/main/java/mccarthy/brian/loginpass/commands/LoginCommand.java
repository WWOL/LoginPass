package mccarthy.brian.loginpass.commands;

import mccarthy.brian.loginpass.LoginPass;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * loginpass login command
 * @author Brian McCarthy
 *
 */
public class LoginCommand {

	public void execute(MessageReceiver caller, String[] parameters) {
		if (!(caller instanceof Player)) {
			caller.notice("Only players may login!");
			return;
		}
		if (!LoginPass.getInstance().getActions().hasSavedPass(caller.getName())) {
			caller.notice("Please setup a password before logging in.");
			caller.message("Use \"/loginpass create <pass> <pass>\" to do so.");
			return;
		}
		boolean success = LoginPass.getInstance().getActions().login(caller.getName(), parameters[0]);
		if (success) {
			caller.message("Congratulations you are now logged in.");
		} else {
			caller.notice("Could not log in. Please try again.");
		}
		
	}

}