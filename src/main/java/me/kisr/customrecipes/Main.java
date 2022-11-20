package me.kisr.customrecipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    FileConfiguration config = getConfig();

    public static char convert(int number) {
        switch (number) {
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'D';
            case 5:
                return 'E';
            case 6:
                return 'F';
            case 7:
                return 'G';
            case 8:
                return 'H';
            case 9:
                return 'I';
        }
        return 0;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("Loading recipes");
        for (String s : config.getStringList("recipe-list")) {

            NamespacedKey key = new NamespacedKey(this, s);

            ItemStack item = new ItemStack(Material.valueOf(config.getString("recipes." + s + ".output.material")), config.getInt("recipes." + s + ".output.amount"));

            ShapedRecipe recipe = new ShapedRecipe(key, item);

            recipe.shape("ABC", "DEF", "GHI");

            for (int i = 1; i < 10; i++) {
                recipe.setIngredient(convert(i), Material.valueOf(config.getString("recipes." + s + ".ingredients." + i)));
            }
            getServer().addRecipe(recipe);
        }
        getLogger().info("Finished loading the recipies");
    }
}
