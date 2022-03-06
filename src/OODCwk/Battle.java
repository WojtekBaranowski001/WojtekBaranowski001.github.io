package OODCwk;

import java.io.Serializable;

public class Battle implements Serializable {
    private int battleNumber;
    private BattleType type;
    private String enemyName;
    private int enemyStrength;
    private int gains;
    private int losses;

    public Battle(int battleNumber, BattleType type, String enemyName, int enemyStrength, int losses, int gains) {
        this.battleNumber = battleNumber;
        this.type = type;
        this.enemyName = enemyName;
        this.enemyStrength = enemyStrength;
        this.gains = gains;
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "battleNumber" + battleNumber +
                ", type" + type +
                ", enemyName'" + enemyName + '\'' +
                ", enemyStrength" + enemyStrength +
                ", gains" + gains +
                ", losses" + losses +
                '}';
    }

    public int getId() {
        return battleNumber;
    }

    public BattleType getType() {
        return type;
    }

    public int getLosses() {
        return losses;
    }

    public int getGains() {
        return gains;
    }

    public int getEnemyStrength() {
        return enemyStrength;
    }
}
