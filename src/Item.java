import java.util.*;

public class Item {

	private String name; //itemin nimi
	private String id; //itemin ID
	
	//luo itemin
	public Item(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	//palauttaa itemin kaikki skillit
	public ArrayList<Skill> annaKaikkiSkillit(){
		return DotaBuddy.skillsOfAHeroByID(id);
	}
	
	//lis‰‰ tietokantaan t‰m‰n Skilluser-olion
	public void lisaaItemTietokantaan(){
		DotaBuddy.addSkilluser(this);
	}
	
	//palauttaa itemin merkkijonoesityksen
	public String toString() {
		return "Item [Name=" + name + ", ID=" + id + "]";
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
