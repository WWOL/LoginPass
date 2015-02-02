package mccarthy.brian.loginpass.commands;

import mccarthy.brian.loginpass.LoginPass;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * loginpass create command
 * @author Brian McCarthy
 *
 */
public class CreatePassCommand {

	public void execute(MessageReceiver caller, String[] parameters) {
		if (!(caller instanceof Player)) {
			caller.notice("Only players may create a pass!");
			if (caller.hasPermission("loginpass.change.other")) {
				caller.message("However, you have permission to change other players passwords.");
				caller.message("Use \"/loginpass change <pass> <pass> >player>\" to do so.");
			}
			return;
		}
		if (LoginPass.getInstance().getActions().hasSavedPass(caller.getName())) {
			caller.notice("You already have a pass set. Please login instead.");
			caller.message("Use \"/loginpass change <pass> <pass> <player>\" to do so.");
			return;
		}
		if (!parameters[0].equals(parameters[1])) {
			caller.notice("Pass' do not match! Please try again.");
			return;
		}
		LoginPass.getInstance().getActions().setPass(caller.getName(), parameters[0]);
		LoginPass.getInstance().getActions().login(caller.getName(), parameters[0]);
		caller.message("Logged in successfully.");
	}

}