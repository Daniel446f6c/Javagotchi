package com.danield.javagotchi.entities;

import com.danield.javagotchi.game.CombatSystem;
import com.danield.javagotchi.utils.AnsiColor;
import com.danield.javagotchi.utils.GameUtils;

import java.util.ArrayList;

public abstract class PlayableEntity {

    protected ArrayList<ConsumableItem> inventory = new ArrayList<>();
    protected String name;
    protected NatureElement element;
    protected int health = 200;
    protected int strength = 40;
    protected int defense = 10;
    protected int level = 1;
    protected int energy = 100;
    protected int coins = 0;
    protected boolean isNPC;

    public PlayableEntity(String name, boolean isNPC) {
        this.name = name;
        this.isNPC = isNPC;
    }

    public PlayableEntity(String name, int health, int strength, int defense, boolean isNPC) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.isNPC = isNPC;
    }

    public abstract String getColoredName();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (element) {
            case FIRE -> stringBuilder.append(AnsiColor.RED.getCode());
            case WATER -> stringBuilder.append(AnsiColor.CYAN.getCode());
            case EARTH -> stringBuilder.append(AnsiColor.GREEN.getCode());
            case WIND -> stringBuilder.append(AnsiColor.YELLOW.getCode());
        }
        stringBuilder.append(" %s | %s | HP: %s | Str: %s | Def: %s | Energy: %s | Coins: %s".formatted(name, element, health, strength, defense, energy, coins));
        stringBuilder.append(AnsiColor.RESET.getCode());
        return stringBuilder.toString();
    }

    public NatureElement getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public int getEnergy() {
        return energy;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<ConsumableItem> getInventory() {
        return inventory;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addToInventory(ConsumableItem item) {
        inventory.add(item);
    }

    public boolean isAlive() {
        return health > 0 && energy > 0;
    }

    public boolean isNPC() {
        return isNPC;
    }

    public void attack(PlayableEntity target) {
        int dmg = CombatSystem.evaluateDamage(this, target);
        GameUtils.animateOutput("%s attacks %s dealing %s damage ".formatted(this.getColoredName(), target.getColoredName(), dmg), 20);
        target.setHealth(target.getHealth() - dmg);
        reduceEnergy(target, dmg);
    }

    public void loot(PlayableEntity target) {
        int targetCoins = target.getCoins();
        coins += targetCoins;
        GameUtils.animateOutput("\n%s loots %s coins from %s\n".formatted(this.getColoredName(), targetCoins, target.getColoredName()), 20, 120);
    }

    public void died(PlayableEntity killer) {
        GameUtils.animateOutput("\n%s killed %s\n".formatted(killer.getColoredName(), this.getColoredName()), 20, 120);
    }

    private void reduceEnergy(PlayableEntity target, int dmg) {
        int energyReduction = Math.round(dmg*0.33f);
        GameUtils.animateOutput("and reducing its energy by %s.\n".formatted(energyReduction), 20, 120);
        target.setEnergy(target.getEnergy() - energyReduction);
        if (target.getDefense() >= 15) {
            GameUtils.animateOutput("\nDue to the enemies huge defense your energy reduced!\n", 20, 120);
            energy -= 10;
        }
    }

}