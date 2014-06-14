import java.util.*;

public class Hero {
	
	private String name; //heron nimi
	private String id; //heron id
	
	//luo heron
	public Hero(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	//palauttaa heron kaikki skillit
	public ArrayList<Skill> annaKaikkiSkillit(){
		return DotaBuddy.skillsOfAHeroByID(id);
	}

	//palauttaa heron merkkijonoesityksen
	public String toString() {
		return "Hero [Name=" + name + ", ID=" + id + "]";
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

}
