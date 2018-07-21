package me.ollethunberg.scoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable() {
		
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = s.registerNewObjective("dummy", "dummy");
		
		Team creator = s.registerNewTeam("creator");

		//CAN ONLY BE UP TO 16 CHARACTERS COLORS COUNT.
		
		o.setDisplayName("§kYes§r§d§l Welcome! §r§kYes");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore("§aOnline: §r" + Bukkit.getServer().getOnlinePlayers().size()).setScore(5);
		if(Bukkit.getIp() != null) {
			o.getScore("§cWebsite: §r" + Bukkit.getIp()).setScore(4);
		}
		o.getScore("§eWorld: §r" + player.getWorld().getName()).setScore(3);
		String string = Bukkit.getBukkitVersion();
		String version = string.substring(0, 6);
		o.getScore("§eVersion: §r" + version).setScore(2);
		
		if(player.getWorld().getDifficulty() == Difficulty.PEACEFUL) {
			o.getScore("§eDifficulty: §aPeaceful").setScore(1);
		}else if(player.getWorld().getDifficulty() == Difficulty.EASY) {
			o.getScore("§eDifficulty: §2Easy").setScore(1);
		}else if(player.getWorld().getDifficulty() == Difficulty.NORMAL) {
			o.getScore("§eDifficulty: §bNormal").setScore(1);
		}else if(player.getWorld().getDifficulty() == Difficulty.HARD) {
			o.getScore("§eDifficulty: §4Hard").setScore(1);
		}
		if(player.getName().equalsIgnoreCase("OlleThunberg")) {
			creator.setPrefix("§9§lCREATOR §r");
			String dn = player.getDisplayName();
			player.setDisplayName("§9§lCREATOR §r" + dn);
			creator.addPlayer(player);

		}
		
		player.setScoreboard(s);
	}

}
