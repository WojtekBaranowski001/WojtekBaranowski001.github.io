package OODCwk;

import java.io.Serializable;

public class Force implements Serializable {
    private String referenceCode;
    private String fullName;
    private int battleStrength;
    private int activationFee;
    private int strikers;
    private int laserCannons;
    private int photonTorpedoes;
    private boolean cloaking;
    private ForceType type;
    private ForceState state;

    public Force(String referenceCode, String fullName, int activationFee, int strikers, int laserCannons, int photonTorpedoes, int battleStrength, boolean cloaking, ForceType type) {
        this.referenceCode = referenceCode;
        this.fullName = fullName;
        this.activationFee = activationFee;
        this.strikers = strikers;
        this.laserCannons = laserCannons;
        this.photonTorpedoes = photonTorpedoes;
        this.battleStrength = battleStrength;
        this.cloaking = cloaking;
        this.type = type;
        this.state = ForceState.DOCKED;
    }

    @Override
    public String toString() {
        return "Force{" +
                "referenceCode" + referenceCode + '\'' +
                ", fullName" + fullName + '\'' +
                ", activationFee" + activationFee +
                ", battleStrength" + battleStrength +
                ", strikers" + strikers +
                ", laserCannons" + laserCannons +
                ", photonTorpedoes" + photonTorpedoes +
                ", cloaking" + cloaking +
                ", type" + type +
                ", state" + state +
                '}';
    }

    public String getReferenceCode()
    {
        return referenceCode;
    }

    public void updateState(ForceState state) {
        this.state = state;
    }

    public int getActivationFee() {
        return activationFee;
    }

    public ForceType getType() {
        return type;
    }

    public boolean canCloak() {
        return cloaking;
    }

    public int getStrength() {
        return battleStrength;
    }
}
