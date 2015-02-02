package mccarthy.brian.loginpass;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.player.BlockLeftClickHook;
import net.canarymod.hook.player.BlockPlaceHook;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.hook.player.ItemDropHook;
import net.canarymod.hook.player.ItemPickupHook;
import net.canarymod.hook.player.PlayerMoveHook;
import net.canarymod.hook.player.PreConnectionHook;
import net.canarymod.hook.system.PermissionCheckHook;
import net.canarymod.plugin.PluginListener;

/**
 * Main listener class
 * @author Brian McCarthy
 *
 */
public class LoginPassListener implements PluginListener {

	@HookHandler
	public void onPreConnect(PreConnectionHook hook) {
		if (LoginPassSettings.checkIp) {
			String ip = hook.getIp();
			LoginPass.getInstance().getActions().ipMatches(hook.getName(), ip);
		}
	}
	
	@HookHandler
	public void onDisconncet(DisconnectionHook hook) {
		LoginPass.getInstance().getActions().logout(hook.getPlayer().getName());
	}

	@HookHandler
	public void onPermCheck(PermissionCheckHook hook) {
		if (LoginPassSettings.denyAllPermissions) {
			if (hook.getSubject() instanceof Player) {
				if (!LoginPass.getInstance().getActions().isLoggedIn(hook.getSubject().getName())) {
					// Do not message player as permission checks are called often and this would
					// spam the user
					hook.setResult(false);
				}
			}
		}
	}

	@HookHandler
	public void onEntityDamage(DamageHook hook) {
		if (LoginPassSettings.denyAttack) {
			if (hook.getAttacker() instanceof Player) {
				Player p = (Player) hook.getAttacker();
				if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
					p.notice("Attacking is disabled when not logged in.");
					hook.setCanceled();
				}
			}
		}
		if (LoginPassSettings.denyDamage) {
			if (hook.getDefender() instanceof Player) {
				Player p = (Player) hook.getDefender();
				if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
					p.notice("Taking damage is disabled when not logged in.");
					hook.setCanceled();
				}
			}
		}
	}	

	@HookHandler
	public void onChat(ChatHook hook) {
		if (LoginPassSettings.denyChat) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Chatting is disabled when not logged in.");
				hook.setCanceled();
			}
		}
		String msg = hook.getMessage().toLowerCase(); 
		if (msg.startsWith("loginpass") || msg.startsWith("lp")) {
			hook.getPlayer().notice("Please start your messages with something other than \"loginpass\" or \"lp\"!");
			hook.getPlayer().message("This is to make sure you don't accidently enter your pass in chat.");
			hook.setCanceled();
		}
	}
	
	@HookHandler
	public void onCommand(PlayerCommandHook hook) {
		if (LoginPassSettings.denyCommand) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				String cmd = hook.getCommand()[0]; 
				if (!cmd.equals("/loginpass") && !cmd.equals("/lp")) {
					p.notice("Using commands is disabled when not logged in.");
					hook.setCanceled();
				}
			}

		}
	}
	
	@HookHandler
	public void onItemDrop(ItemDropHook hook) {
		if (LoginPassSettings.denyItemDrop) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Dropping items is disabled when not logged in.");
				hook.setCanceled();
			}

		}
	}
	
	@HookHandler
	public void onItemPickup(ItemPickupHook hook) {
		if (LoginPassSettings.denyItemPickup) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Picking up items is disabled when not logged in.");
				hook.setCanceled();
			}

		}
	}
	
	@HookHandler
	public void onBlockLeftClick(BlockLeftClickHook hook) {
		if (LoginPassSettings.denyLeftClick) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Destroying blocks is disabled when not logged in.");
				hook.setCanceled();
			}

		}
	}
	
	@HookHandler
	public void onPlayerMove(PlayerMoveHook hook) {
		if (LoginPassSettings.denyMovement) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Movement is disabled when not logged in.");
				hook.setCanceled();
			}

		}
	}

	@HookHandler
	public void onRightClick(BlockPlaceHook hook) {
		if (LoginPassSettings.denyRightClick) {
			Player p = (Player) hook.getPlayer();
			if (!LoginPass.getInstance().getActions().isLoggedIn(p.getName())) {
				p.notice("Destroying blocks is disabled when not logged in.");
				hook.setCanceled();
			}

		}
	}
	
}