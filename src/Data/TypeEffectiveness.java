package Data;
import java.util.*;

public class TypeEffectiveness {
	
	//Water-Type Pokemon Type Advantages & Disadvantages
	List<String> water_supereffective = List.of("Fire");
	List<String> water_normaleffect = List.of("Grass");
	List<String> water_lesseffective = List.of("Rock");
	List<String> water_noeffect = List.of("Water");
	
	//Fire-Type Pokemon Type Advantages & Disadvantages
	List<String> fire_supereffective = List.of("Grass");
	List<String> fire_normaleffect = List.of("Water, Rock");
	List<String> fire_noeffect = List.of("Fire");
	
	//Grass-Type Pokemon Type Advantages & Disadvantages
	List<String> grass_supereffective = List.of("Water");
	List<String> grass_normaleffect = List.of("Fire");
	List<String> grass_lesseffective = List.of("Rock");
	List<String> grass_noeffect = List.of("Grass");
	
	//Rock-Type Pokemon Type Advantages & Disadvantages
	List<String> rock_supereffective = List.of("Fire");
	List<String> rock_normaleffect = List.of("Water,Grass");
	List<String> rock_noeffect = List.of("Rock");
	
	public TypeEffectiveness() {
		
	}
	
	//Putting the Attack Move Type to compare with the Defender Move Type
	public double type_comparision(int dmg, String attackertype, String defender1type, String defender2type) {
		if (attackertype.equals("Water")) {
			dmg = (int) watereffect(dmg, defender1type, defender2type);
		}
		if (attackertype.equals("Fire")) {
			dmg = (int) fireeffect(dmg, defender1type, defender2type);
		}
		if (attackertype.equals("Grass")) {
			dmg = (int) grasseffect(dmg, defender1type, defender2type);
		}
		if (attackertype.equals("Rock")) {
			dmg = (int) rockeffect(dmg, defender1type, defender2type);
		}
			return dmg;
		}
	
	//Comparing Water-Type with Defender Move Type
		public double watereffect(int dmg, String defender1type, String defender2type) {
			if (water_supereffective.contains(defender1type) || water_supereffective.contains(defender2type)){
				dmg *= 2; //Damage dealt is greatly increased
				System.out.println("Super Effective! Damage Dealt: "+ dmg);
			}
			else if (water_normaleffect.contains(defender1type) || water_normaleffect.contains(defender2type)) {
				dmg *= 1.5; //Damage dealt is slightly increased
				System.out.println("Damage Dealt: "+ dmg);
			}
			else if (water_lesseffective.contains(defender1type) || water_lesseffective.contains(defender2type)) {
				dmg *= 1; //Damage dealt is remains the same
				System.out.println("Not very effective... Damage Dealt: "+ dmg);
			}
			else if (water_noeffect.contains(defender1type) || water_noeffect.contains(defender2type)) {
				dmg /= 2; //Damage dealt is slightly decreased
				System.out.println("The attack had minimal effect! Damage Dealt: "+ dmg);
			}
				return dmg;
		}
		
		//Comparing Fire-Type with Defender Move Type
		public double fireeffect(int dmg, String defender1type, String defender2type) {
			if (fire_supereffective.contains(defender1type) || fire_supereffective.contains(defender2type)) {
				dmg *= 2; //Damage dealt is greatly increased
				System.out.println("Super Effective! Damage Dealt: "+ dmg);
			}
			else if (fire_normaleffect.contains(defender1type) || fire_normaleffect.contains(defender2type)) {
				dmg *= 1.5; //Damage dealt is slightly increased
				System.out.println("Damage Dealt: "+ dmg);
			}
			else if (fire_noeffect.contains(defender1type) || fire_noeffect.contains(defender2type)) {
				dmg /= 2; //Damage dealt is slightly decreased
				System.out.println("The attack had minimal effect! Damage Dealt: "+ dmg);
			}
				return dmg;
		}
		
		//Comparing Grass-Type with Defender Move Type
		public double grasseffect(int dmg, String defender1type, String defender2type) {
			if (grass_supereffective.contains(defender1type) || grass_supereffective.contains(defender2type)) {
				dmg *= 2; //Damage dealt is greatly increased
				System.out.println("Super Effective! Damage Dealt: "+ dmg);
			}
			else if (grass_normaleffect.contains(defender1type) || grass_normaleffect.contains(defender2type)) {
				dmg *= 1.5; //Damage dealt is slightly increased
				System.out.println("Damage Dealt: "+ dmg);
			}
			else if (grass_lesseffective.contains(defender1type) || grass_lesseffective.contains(defender2type)) {
				dmg *= 1; //Damage dealt is remains the same
				System.out.println("Not very effective... Damage Dealt: "+ dmg);
			}
			else if (grass_noeffect.contains(defender1type) || grass_noeffect.contains(defender2type)) {
				dmg /= 2; //Damage dealt is slightly decreased
				System.out.println("The attack had minimal effect! Damage Dealt: "+ dmg);
			}
				return dmg;
		}
		
		//Comparing Rock-Type with Defender Move Type
		public double rockeffect(int dmg, String defender1type, String defender2type) {
			if (rock_supereffective.contains(defender1type) || rock_supereffective.contains(defender2type)) {
				dmg *= 2; //Damage dealt is greatly increased
				System.out.println("Super Effective! Damage Dealt: "+ dmg);
			}
			else if (rock_normaleffect.contains(defender1type) || rock_normaleffect.contains(defender2type)) {
				dmg *= 1.5; //Damage dealt is slightly increased
				System.out.println("Damage Dealt: "+ dmg);
			}
			else if (rock_noeffect.contains(defender1type) || rock_noeffect.contains(defender2type)) {
				dmg /= 2; //Damage dealt is slightly decreased
				System.out.println("The attack had minimal effect! Damage Dealt: "+ dmg);
			}
				return dmg;
		}
}