package dev.floffah.skillpaths.gui;

import java.util.List;

public class HeadInfo {
    //https://minecraft-heads.com/custom-heads/humans/10414
    public static final HeadInfo cyanBack = new HeadInfo("&b&lBack", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjc2OGVkYzI4ODUzYzQyNDRkYmM2ZWViNjNiZDQ5ZWQ1NjhjYTIyYTg1MmEwYTU3OGIyZjJmOWZhYmU3MCJ9fX0=");

    public List<String> lore = null;
    public String name;
    public String texture;

    public HeadInfo(String headname, List<String> headlore, String headtexture) {
        name = headname;
        lore = headlore;
        texture = headtexture;
    }

    public HeadInfo(String headname, String headtexture) {
        name = headname;
        texture = headtexture;
    }
}
