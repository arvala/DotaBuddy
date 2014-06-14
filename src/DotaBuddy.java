import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
public class DotaBuddy {

	private static Connection kanta = null;
	
	public static Connection alusta() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			kanta = DriverManager.getConnection("jdbc:postgresql:Dota2Database", "postgres", "admin");
		}
		catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
		return kanta;
	}
		
	public static void sulje() {
		try {
			kanta.close();
		}
		catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
	
	public static void main(String[] args){
		alusta();
		ArrayList<Skill> skill = new ArrayList<Skill>();
		
		skill = skillsOfAHeroByName("Meepo");
		for(int i = 0; i < skill.size(); i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = skillsOfAHeroByID("156");
		for(int i = 0; i < skill.size(); i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = allSkillsOfAHeroByName("Drow Ranger");
		for(int i = 0; i < skill.size(); i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = allSkillsOfAHeroByID("361");
		for(int i = 0; i < skill.size(); i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		ArrayList<Hero> hero = new ArrayList<Hero>();
		hero = annaSTR();
		for(int i = 0; i < 6; i++){
			System.out.println(hero.get(i).toString());
		}
		System.out.println("-------------------------");
		
		hero = annaAGI();
		for(int i = 0; i < 6; i++){
			System.out.println(hero.get(i).toString());
		}
		System.out.println("-------------------------");
		
		hero = annaINT();
		for(int i = 0; i < 6; i++){
			System.out.println(hero.get(i).toString());
		}
		System.out.println("-------------------------");
		
		ArrayList<Item> item = new ArrayList<Item>();
		item = annaITEM();
		for(int i = 0; i < 6; i++){
			System.out.println(item.get(i).toString());
		}
		System.out.println("-------------------------");
		
		hero = annaHerotSkillinFunktionMukaan("CC");
		for(int i = 0; i < 6; i++){
			System.out.println(hero.get(i).toString());
		}
		System.out.println("-------------------------");
		
		item = annaItemitSkillinFunktionMukaan("SUMMON");
		for(int i = 0; i < item.size(); i++){
			System.out.println(item.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = skillCdLongerThan(25);
		for(int i = 0; i < 6; i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = skillCdShorterThan(14);
		for(int i = 0; i < 6; i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = skillManacostSmallerThan(20);
		for(int i = 0; i < 6; i++){
			System.out.println(skill.get(i).toString());
		}
		System.out.println("-------------------------");
		
		skill = skillManacostBiggerThan(100);
		for(int i = 0; i < 6; i++){
			System.out.println(skill.get(i).toString());
		}
		
		sulje();
		
	}

/**
 * Metodi hakee tietokannasta sy�tteen� saamansa nimen mukaisen heron kaikki ensimm�isen tason skillit ArrayListina.
 * @param heroName
 * @return ArrayList<Skill> 
 */
	public static ArrayList<Skill> skillsOfAHeroByName(String heroName){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT SKILL.Name, SKILL.ID, UserID, Cooldown, Manacost, Function FROM SKILL, SKILLUSER WHERE SKILLUSER.Name = '"+heroName+"' AND SKILLUSER.ID = SKILL.UserID AND SKILL.ID LIKE '%1';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){

				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));

			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;

	}

	/**
	 * Metodi hakee tietokannasta sy�tteen� saamansa ID:n mukaisen heron kaikki ensimm�isen tason skillit ArrayListina.
	 * @param heroID
	 * @return ArrayList<Skill>
	 */
	public static ArrayList<Skill> skillsOfAHeroByID(String heroID){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT SKILL.Name, SKILL.ID, UserID, Cooldown, Manacost, Function FROM SKILL, SKILLUSER WHERE SKILLUSER.ID = '"+heroID+"' AND SKILLUSER.ID = SKILL.UserID AND SKILL.ID LIKE '%1';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));
			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;
	}

	/**
	 * Metodi hakee tietokannasta sy�tteen� saamansa nimen mukaisen heron kaikki skillit ArrayListina.
	 * @param heroID
	 * @return ArrayList<Skill>
	 */
	public static ArrayList<Skill> allSkillsOfAHeroByName(String heroName){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT SKILL.Name, SKILL.ID, UserID, Cooldown, Manacost, Function FROM SKILL, SKILLUSER WHERE SKILLUSER.Name = '"+heroName+"' AND SKILLUSER.ID = UserID;";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){

				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));

			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;
	}

	/**
	 * Metodi hakee tietokannasta sy�tteen� saamansa ID:n mukaisen heron kaikki skillit ArrayListina.
	 * @param heroID
	 * @return ArrayList<Skill>
	 */
	public static ArrayList<Skill> allSkillsOfAHeroByID(String heroID){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT SKILL.Name, SKILL.ID, UserID, Manacost, Cooldown, Function FROM SKILL, SKILLUSER WHERE SKILLUSER.ID = '"+heroID+"' AND SKILLUSER.ID = UserID;";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){

				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));

			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;
	}

	/**
	 * Metodi hakee tietokannasta kaikki Strength-tyypin herojen nimet ja ID:t ArrayListina.
	 * @return ArrayList<Hero>
	 */
	public static ArrayList<Hero> annaSTR(){
		ArrayList<Hero> strHerot = new ArrayList<Hero>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID FROM SKILLUSER WHERE ID LIKE '1__';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				strHerot.add(new Hero(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return strHerot;
	}

	/**
	 * Metodi hakee tietokannasta kaikki Agility-tyypin herojen nimet ja ID:t ArrayListina.
	 * @return ArrayList<Hero>
	 */
	public static ArrayList<Hero> annaAGI(){
		ArrayList<Hero> agiHerot = new ArrayList<Hero>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID FROM SKILLUSER WHERE ID LIKE '2__';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				agiHerot.add(new Hero(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return agiHerot;
	}

	/**
	 * Metodi hakee tietokannasta kaikki Intelligence-tyypin herojen nimet ja ID:t ArrayListina.
	 * @return ArrayList<Hero>
	 */
	public static ArrayList<Hero> annaINT(){
		ArrayList<Hero> intHerot = new ArrayList<Hero>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID FROM SKILLUSER WHERE ID LIKE '3__';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				intHerot.add(new Hero(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return intHerot;
	}

	/**
	 * Metodi hakee tietokannasta kaikki Itemien nimet ja ID:t ArrayListina.
	 * @return ArrayList<Hero>
	 */
	public static ArrayList<Item> annaITEM(){
		ArrayList<Item> itemit = new ArrayList<Item>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID FROM SKILLUSER WHERE ID LIKE '4__';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				itemit.add(new Item(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return itemit;
	}

	/**
	 * Metodi hakee tietokannasta kaikki tietyn funktion(CC, SUMMON, DAMAGE, UTILITY) omaavan skillin omaavat herot ArrayListina.
	 * @param funktio
	 * @return ArrayList<Hero>
	 */
	public static ArrayList<Hero> annaHerotSkillinFunktionMukaan(String funktio){
		ArrayList<Hero> herot = new ArrayList<Hero>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT DISTINCT SKILLUSER.Name, SKILLUSER.ID FROM SKILLUSER, SKILL WHERE SKILL.Function='" + funktio  
					+ "' AND SKILL.UserID=SKILLUSER.ID AND SKILLUSER.ID NOT LIKE '4%';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				herot.add(new Hero(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return herot;
	}

	/**
	 * Metodi hakee tietokannasta kaikki tietyn funktion(CC, SUMMON, DAMAGE, UTILITY) omaavan skillin omaavat itemit ArrayListina.
	 * @param funktio
	 * @return ArrayList<Item>
	 */
	public static ArrayList<Item> annaItemitSkillinFunktionMukaan(String funktio){
		ArrayList<Item> itemit = new ArrayList<Item>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT DISTINCT SKILLUSER.Name, SKILLUSER.ID FROM SKILLUSER, SKILL WHERE SKILL.Function='" + funktio 
					+ "' AND SKILL.UserID=SKILLUSER.ID AND SKILLUSER.ID LIKE '4%';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				itemit.add(new Item(tulos.getString("Name"), tulos.getString("ID")));
			}
			tulos.close();
			lause.close();
		}
		catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return itemit;
	}

	/**
	 * Metodi hakee tietokannasta kaikki skillit, joiden cooldown on pidempi kuin sy�te, ArrayListina.
	 * @param x
	 * @return ArrayList<Skill>
	 */
	public static ArrayList<Skill> skillCdLongerThan(int x){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID, UserID, Cooldown, Manacost, Function FROM SKILL WHERE Cooldown >'"+ x+"';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));
			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;
	}

	/**
	 * Metodi hakee tietokannasta kaikki skillit, joiden cooldown on lyhyempi kuin sy�te, ArrayListina.
	 * @param x
	 * @return ArrayList<Skill>
	 */
	public static ArrayList<Skill> skillCdShorterThan(int x){
		ArrayList<Skill> skill = new ArrayList<Skill>();
		try{
			Statement lause = kanta.createStatement();
			String kysely = "SELECT Name, ID, UserID, Cooldown, Manacost, Function FROM SKILL WHERE Cooldown <'" +x+"';";
			ResultSet tulos = lause.executeQuery(kysely);
			while(tulos.next()){
				skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));
			}
			tulos.close();
			lause.close();

		}catch (Exception e){
			System.out.println("Virhe: " + e);
		}
		return skill;
	}

	/**
	 * Metodi hakee tietokannasta kaikki skillit, joiden manacost on pienempi kuin sy�te, ArrayListina.
	 * @param x
	 * @return ArrayList<Skill>
	 */
		public static ArrayList<Skill> skillManacostSmallerThan(int x){
			ArrayList<Skill> skill = new ArrayList<Skill>();
			try{
				Statement lause = kanta.createStatement();
				String kysely = "SELECT Name, ID, UserID, Cooldown, Manacost, Function FROM SKILL WHERE Manacost <'"+ x+"';";
				ResultSet tulos = lause.executeQuery(kysely);
				while(tulos.next()){
					skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));
				}
				tulos.close();
				lause.close();

			}catch (Exception e){
				System.out.println("Virhe: " + e);
			}
			return skill;

		}

		/**
		 * Metodi hakee tietokannasta kaikki skillit, joiden manacost on suurempi kuin sy�te, ArrayListina.
		 * @param x
		 * @return ArrayList<Skill>
		 */
		public static ArrayList<Skill> skillManacostBiggerThan(int x){
			ArrayList<Skill> skill = new ArrayList<Skill>();
			try{
				Statement lause = kanta.createStatement();
				String kysely = "SELECT Name, ID, UserID, Cooldown, Manacost, Function FROM SKILL WHERE Manacost >'"+ x +"';";
				ResultSet tulos = lause.executeQuery(kysely);
				while(tulos.next()){
					skill.add(new Skill(tulos.getString("Name"), tulos.getString("ID"), tulos.getString("UserID"), tulos.getDouble("Manacost"), tulos.getDouble("Cooldown"), tulos.getString("Function")));
				}
				tulos.close();
				lause.close();

			}catch (Exception e){
				System.out.println("Virhe: " + e);
			}
			return skill;
		}
	}

