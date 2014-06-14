import java.util.*;

public class Skill {
	
	private String name; //skillin nimi
	private String id; //skillin ID
	private String userId; //skillin käyttäjän ID
	private Double manacost; //skillin manacost
	private Double cooldown; //skillin cooldown
	private String function; //skillin funktio/toiminto
	
	//luo skillin
	public Skill(String name, String id, String userId, Double manacost, Double cooldown, String function) {
		this.name = name;
		this.id = id;
		this.userId = userId;
		this.manacost = manacost;
		this.cooldown = cooldown;
		this.function = function;
	}
	
	//palauttaa skillin merkkijonoesityksen
	public String toString() {
		return "Skill [Name=" + name + ", ID=" + id + ", UserID=" + userId + ", Manacost=" + manacost + ", Cooldown=" + cooldown + ", Function=" + function + "]";
	}

	//getterit ja setterit
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getManacost() {
		return manacost;
	}

	public void setManacost(Double manacost) {
		this.manacost = manacost;
	}

	public Double getCooldown() {
		return cooldown;
	}

	public void setCooldown(Double cooldown) {
		this.cooldown = cooldown;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

}
