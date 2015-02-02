package mccarthy.brian.loginpass;

import mccarthy.brian.loginpass.commands.CommandList;
import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.plugin.Plugin;

/**
 * Main plugin class
 * @author Brian McCarthy
 *
 */
public class LoginPass extends Plugin {

	private static LoginPass INSTANCE;
	private LoginPassActions actions;
	
	public LoginPass() {
		INSTANCE = this;
	}
	
	@Override
	public boolean enable() {
		actions = new LoginPassActions();
		LoginPassSettings.setup();
		
		Canary.hooks().registerListener(new LoginPassListener(), this);
		try {
			Canary.commands().registerCommands(new CommandList(), this, false);
		} catch (CommandDependencyException e) {
			getLogman().warn("Could not load " + getName() + " because of a command dependency exception!");
			return false;
		}
		return true;
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

	public static LoginPass getInstance() {
		return INSTANCE;
	}

	public LoginPassActions getActions() {
		return actions;
	}
	
}