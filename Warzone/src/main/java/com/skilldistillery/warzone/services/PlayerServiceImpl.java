package com.skilldistillery.warzone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.warzone.entities.Player;
import com.skilldistillery.warzone.repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{
	@Autowired
	private PlayerRepository playRepo;

	@Override
	public List<Player> findAll() {
		
		return playRepo.findAll();
	}

	@Override
	public Player findByID(int id) {
		Optional<Player> opPlayer = playRepo.findById(id);
		Player player = null;
		if (opPlayer.isPresent()) {
			player = opPlayer.get();
		} else {			
			return null;
		}
		return player;
	}

	@Override
	public Player createPlayer(Player player) {

		try{
			player = playRepo.saveAndFlush(player);
			return player;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Player updatePlayer(int id, Player player) {
		Player managedPlayer = null;
	
		Optional<Player> opPlayer = playRepo.findById(id);
		if(opPlayer.isPresent()) {
			managedPlayer = opPlayer.get();
			managedPlayer.setId(player.getId());
			managedPlayer.setName(player.getName());
			managedPlayer.setKillAmount(player.getKillAmount());
			return playRepo.saveAndFlush(managedPlayer);
		}
		return managedPlayer;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<Player> opPlayer = playRepo.findById(id);
		if (opPlayer.isPresent()) {
			playRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
