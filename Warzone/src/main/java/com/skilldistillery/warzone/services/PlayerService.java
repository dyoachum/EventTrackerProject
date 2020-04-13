package com.skilldistillery.warzone.services;

import java.util.List;

import com.skilldistillery.warzone.entities.Player;

public interface PlayerService {
	List<Player> findAll();
		
		Player findByID(int id);
	
		Player createPlayer(Player player);
		
		Player updatePlayer(int id, Player player);
		
		boolean deleteById(int id);

}
