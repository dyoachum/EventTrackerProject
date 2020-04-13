package com.skilldistillery.warzone.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.warzone.entities.Player;
import com.skilldistillery.warzone.services.PlayerService;

@RestController
@RequestMapping("api")
public class PlayerController {
	@Autowired
	private PlayerService playSvc;

	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("players")
	public List<Player> showPlayers() {
		return playSvc.findAll();
	}

	@GetMapping("players/{id}")
	public Player showById(@PathVariable("id") int id, HttpServletResponse responce) {
		Player player = playSvc.findByID(id);
		if (player == null) {
			responce.setStatus(404);
		}
		return player;
	}

	@PostMapping("players")
	@ResponseBody
	public Player addPlayer(@RequestBody Player player, HttpServletRequest request, HttpServletResponse response) {
		try {
			Player addPlayer = playSvc.createPlayer(player);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(player.getId());
			String location = url.toString();
			response.addHeader("Location", location);
			return addPlayer;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
	}
	@PutMapping("players/{id}")
	public Player updatePlayer(@PathVariable("id") int id, @RequestBody Player player,
			HttpServletResponse resp) {
		try {
			player = playSvc.updatePlayer(id, player);
			if (player == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			player = null;
		}
		return player;
	}
	@DeleteMapping("players/{id}")
	public void deletePlayer(@PathVariable("id") int id, HttpServletResponse response) {
		try {
			if (playSvc.deleteById(id)) {
				response.setStatus(204);

			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(409);
		}
	}
}
