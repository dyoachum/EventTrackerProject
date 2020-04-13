package com.skilldistillery.warzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.warzone.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
