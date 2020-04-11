package com.skilldistillery.warzone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
	// FIELDS
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String player;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return player;
		}
		public void setName(String name) {
			this.player = name;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((player == null) ? 0 : player.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Player other = (Player) obj;
			if (id != other.id)
				return false;
			if (player == null) {
				if (other.player != null)
					return false;
			} else if (!player.equals(other.player))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "player [id=" + id + ", name=" + player + "]";
		}
		public Player() {
			super();
		}
		public Player(int id, String name) {
			super();
			this.id = id;
			this.player = name;
		}
}
