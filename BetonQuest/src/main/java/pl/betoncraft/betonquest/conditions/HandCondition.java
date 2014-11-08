/**
 * 
 */
package pl.betoncraft.betonquest.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.core.Condition;

/**
 * Having item in inventory condition, instrucion string: "hand type:DIAMOND_SWORD enchants:DAMAGE_ALL:3,KNOCKBACK:1 name:Siekacz"
 * @author Co0sh
 */
public class HandCondition extends Condition {
	
	private Material type;
	private byte data = 0;
	private Map<Enchantment,Integer> enchants = new HashMap<Enchantment,Integer>();
	private List<String> lore = new ArrayList<String>();
	private String name;
	private boolean inverted = false;

	/**
	 * Constructor method
	 * @param playerID
	 * @param instructions
	 */
	public HandCondition(String playerID, String instructions) {
		super(playerID, instructions);
		String[] parts = instructions.split(" ");
		for (String part : parts) {
			if (part.contains("type:")) {
				type = Material.valueOf(part.substring(5));
			} else if (part.contains("data:")) {
				data = Byte.valueOf(part.substring(5));
			} else if (part.contains("enchants:")) {
				for (String enchant : part.substring(9).split(",")) {
					enchants.put(Enchantment.getByName(enchant.split(":")[0]), Integer.decode(enchant.split(":")[1]));
				}
			} else if (part.contains("lore:")) {
				for (String loreLine : part.substring(5).split(";")) {
					lore.add(loreLine.replaceAll("_", " "));
				}
			} else if (part.contains("name:")) {
				name = part.substring(5).replaceAll("_", " ");
			} else if (part.equalsIgnoreCase("--inverted")) {
				inverted = true;
			}
		}
		if (type == null) {
			BetonQuest.getInstance().getLogger().severe("Material not defined in: " + instructions);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isMet() {
		ItemStack item = Bukkit.getPlayer(playerID).getItemInHand();
		if (item == null) {
			return inverted;
		}
		if (!(item.getType().equals(type) && item.getData().getData() == data)) {
			return inverted;
		}
		if (name != null && !(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(name))) {
			return inverted;
		}
		if (lore.size() > 0 && !(item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().equals(lore))) {
			return inverted;
		}
		if (enchants.size() > 0 && enchants.equals(item.getEnchantments())) {
			return inverted;
		}
		return !inverted;
	}

}