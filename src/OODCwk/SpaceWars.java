package OODCwk;

import java.io.*;
import java.util.ArrayList;

import static OODCwk.BattleType.*;

/**
 * This class implements the behaviour expected from a SWAT
 * system as required for 6COM1037 - Nov 2021
 *
 * @author/ Shehiryar & Wojtek
 */

public class SpaceWars implements SWAT, Serializable {
    // Fields
    private final String admiral;
    private int warChest = 0;
    private ArrayList<Force> forces;
    private ArrayList<String> UFF, ASF;
    private ArrayList<Battle> Battles;
//**************** SWAT **************************  

    /**
     * Constructor requires the name of the admiral
     *
     * @param admiral the name of the admiral
     */
    public SpaceWars(String admiral) {
        this.admiral = admiral;
        this.warChest = 1000;
        setupForces();
        setupBattles();
    }
    /**
     * Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     *
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Star Fleet,(or, "No forces" if Star Fleet is empty)
     **/
    public String toString()
    {
        String s = " ";
        s += "Admiral: " + admiral + "\n";
        s += "War chest: " + warChest + "\n";
        s += "Defeated: " + isDefeated() + "\n";
        s += "Star Fleet: " + getASFleet() + "\n";
        return s;
    }

    /**
     * returns the number of bit coins in the war chest
     *
     * @return the number of bit coins in the war chest
     */

    public int getWarchest()
    {
        return warChest;
    }
    /**
     * returns true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     *
     * @return true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     */
    public boolean isDefeated()
    {
        return warChest < 0 && ASF.isEmpty();
    }
    /**
     * returns the force with the given reference code
     *
     * @param ref is the reference code of the force
     * @return the force with the given reference code or null if no such force
     */
    private Force forceObjective(String ref) {
        for (Force f : forces) {
            if (f.getReferenceCode().equals(ref)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Returns true if force is in the United Forces Fleet(UFF), else false
     *
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref)
    {
        return UFF.contains(ref);
    }

    /**
     * Returns a String representation of all forces in the United Forces Fleet(UFF)
     *
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet()
    {
        if (UFF.size() == 0) return "No forces";

        StringBuilder s = new StringBuilder();
        for (String forceRef : UFF) {
            s.append(getForceDetails(forceRef)).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns details of the force in the game with the given reference code
     *
     * @return details of the force in the game with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        Force force = forceObjective(ref);

        if (force == null) return "No such force available";
        return force.toString();
    }


    // ***************** active Star Fleet Forces ************************

    /**
     * Allows a force to be activated into the active Star Fleet(ASF), but
     * only if there are enough resources for the activation fee.The force's
     * state is set to "active"
     *
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF
     * 2 if not enough money, -1 if no such force
     **/
    public int activateForce(String ref)
    {
        Force force = forceObjective(ref);

            if (force == null)
                return -1;
                if (!isInUFFleet(ref))
                    return 1;
                    if (force.getActivationFee() > warChest)
                        return 2;

            warChest -= force.getActivationFee();
            UFF.remove(ref);
            ASF.add(ref);
            force.updateState(ForceState.ACTIVE);

         return 0;
    }

    /**
     * Returns true if the force with the reference code is in
     * the active Star Fleet(ASF), false otherwise.
     *
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        return ASF.contains(ref);
    }

    /**
     * Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     *
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        Force force = forceObjective(ref);

        if (force != null && isInASFleet(ref))
        {
            ASF.remove(ref);
            UFF.add(ref);
            force.updateState(ForceState.DOCKED);
            warChest += (force.getActivationFee() / 2);
        }
    }

    /**
     * Returns a String representation of the forces in the active
     * Star Fleet(ASF), or the message "No forces activated"
     *
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        if (ASF.size() == 0)
            return "No forces activated";

        StringBuilder s = new StringBuilder();

        for (String forceRef : ASF) {
            s.append(getForceDetails(forceRef)).append("\n");
        }
        return s.toString();
    }

//**********************Battles************************* 

    /**
     * Returns the battle with the given reference code
     *
     * @param id is the reference code of the battle
     * @return the battle with the given reference code
     **/
    private Battle getBattleObj(int id)
    {
        for (Battle b : Battles) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    /**
     * returns true if the number represents a battle
     *
     * @param num is the number of the required battle
     * @return true if the number represents a battle
     **/
    public boolean isBattle(int num)
    {
        return getBattleObj(num) != null;
    }

    /**
     * Provides a String representation of a battle given by
     * the battle number
     *
     * @param num the number of the battle
     * @return returns a String representation of a battle given by
     * the battle number
     **/

    public String getBattle(int num)
    {
        Battle battle = getBattleObj(num);

        if (battle == null) return "No such battle";
            return battle.toString();
    }

    /**
     * Provides a String representation of all battles
     *
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        StringBuilder s = new StringBuilder();
        for (Battle battle : Battles) {
            s.append(battle.toString()).append("\n");
        }
        return s.toString();
    }

    private ArrayList<Force> teamForces(BattleType battleType) {
        ArrayList<Force> forces = new ArrayList<>();
        switch (battleType) {
            case AMBUSH:
                for (String forceRef : ASF) {
                    Force force = forceObjective(forceRef);
                    ForceType type = force.getType();
                    // Only Wings and Warbirds with cloaking can ambush
                    if (type == ForceType.WING || (type == ForceType.WARBIRD && force.canCloak())) {
                        forces.add(force);
                    }
                }
                break;
            case FIGHT:
                for (String forceRef : ASF) {
                    Force force = forceObjective(forceRef);
                    ForceType type = force.getType();
                    // Only Warbirds and Starships can fight
                    if (type == ForceType.WARBIRD || type == ForceType.STARSHIP) {
                        forces.add(force);
                    }
                }
                break;
            case SKIRMISH:
                for (String forceRef : ASF) {
                    Force force = forceObjective(forceRef);
                    ForceType type = force.getType();
                    // Only Wings and Starships can skirmish
                    if (type == ForceType.WING || type == ForceType.STARSHIP) {
                        forces.add(force);
                    }
                }
                break;
        }
        return forces;
    }
    /**
     * Retrieves the battle represented by the battle number.Finds
     * a force from the active Star Fleet which can engage in the battle.The
     * results of battle will be one of the following:
     * 0 - OODCwk.Battle won, battle gains added to the warchest,
     * 1 - OODCwk.Battle lost as no suitable force available, battle losses
     * deducted from warchest
     * 2 - OODCwk.Battle lost on battle strength , battle losses
     * deducted from warchest and force destroyed
     * 3 - If a battle is lost and admiral completely defeated (no resources and
     * no forces to recall)
     * -1 - no such battle
     *
     * @param battleNo is the number of the battle
     * @return an int showing the result of the battle
     */

    public int doBattle(int battleNo)
    {
        Battle battle = getBattleObj(battleNo);

        if (battle == null)
            return -1;

        ArrayList<Force> compatibleForces = teamForces(battle.getType());
        // force is found to engage in battle

        if (compatibleForces.isEmpty()) {
            warChest -= battle.getLosses();
            if (ASF.isEmpty() && warChest <= 0)
                return 3;

            return 1;
        } // Battle lost since there are no suitable forces available

        Force strongestForce = bestForce(compatibleForces);
        // finds strongest force

        if (strongestForce.getStrength() < battle.getEnemyStrength()) {
            strongestForce.updateState(ForceState.DESTROYED);
            ASF.remove(strongestForce.getReferenceCode());
            warChest -= battle.getLosses();
            if (ASF.isEmpty() && warChest <= 0) return 3;
            return 2;
        } // battle strength is the cause for battle lost


        warChest += battle.getGains();
        return 0;
        // battle won
    }

    /**
     * Finds the force with the highest strength in the list of forces
     *
     * @param forces is the list of forces to search
     * @return the force with the highest strength
     */

    private Force bestForce(ArrayList<Force> forces)
    {
        Force strongestForce = null;
        for (Force force : forces)
        {
            if (strongestForce == null || force.getStrength() > strongestForce.getStrength()) {
                strongestForce = force;
            }
        }
        return strongestForce;
    }

    //**********************private methods*****************************
    private void setupForces() {
        forces = new ArrayList<>();

        UFF = new ArrayList<>();
        ASF = new ArrayList<>();

        this.forces.add(new Force("IW1", "Twisters", 200, 10, 0, 0, 200, false, ForceType.WING));
        this.forces.add(new Force("SS2", "Enterprise", 300, 0, 10, 20, 200, false, ForceType.STARSHIP));
        this.forces.add(new Force("WB3", "Droop", 300, 0, 0, 0, 100, false, ForceType.WARBIRD));
        this.forces.add(new Force("IW4", "Wingers", 200, 20, 0, 0, 400, false, ForceType.WING));
        this.forces.add(new Force("WB5", "Hang", 400, 0, 0, 0, 300, true, ForceType.WARBIRD));
        this.forces.add(new Force("SS6", "Voyager", 450, 0, 15, 10, 200, false, ForceType.STARSHIP));
        this.forces.add(new Force("SS7", "Explorer", 120, 0, 4, 5, 65, false, ForceType.STARSHIP));
        this.forces.add(new Force("WB9", "Hover", 300, 0, 0, 0, 400, false, ForceType.WARBIRD));
        this.forces.add(new Force("IW10", "Flyers", 200, 5, 0, 0, 100, false, ForceType.WING));

        for (Force f : forces) {
            UFF.add(f.getReferenceCode());
        }
    }

    private void setupBattles() {
        this.Battles = new ArrayList<>();

        this.Battles.add(new Battle(1, FIGHT, "Borg", 200, 300, 100));
        this.Battles.add(new Battle(2, SKIRMISH, "Kardassians", 700, 200, 120));
        this.Battles.add(new Battle(3, AMBUSH, "Ferengi", 100, 400, 150));
        this.Battles.add(new Battle(4, FIGHT, "Ewoks", 600, 600, 200));
        this.Battles.add(new Battle(5, AMBUSH, "Borg", 500, 400, 90));
        this.Battles.add(new Battle(6, SKIRMISH, "Groaners", 150, 100, 100));
        this.Battles.add(new Battle(7, FIGHT, "Borg", 150, 500, 300));
        this.Battles.add(new Battle(8, AMBUSH, "Wailers", 300, 300, 300));

    }

    //*******************************************************************************
    //*******************************************************************************

//These methods are not needed until Task 4.5
//     ***************   file write/read  *********************

    /**
     * Writes whole game to the specified file
     *
     * @param fname name of file storing requests
     */
    public void saveGame(String fname) //Object serialisation is used here
    {
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        }
        catch (IOException e)
        {
            System.out.println("There was an error while saving the game!");
        }
    }

    /**
     * reads all information about the game from the specified file
     * and returns a SpaceWars object
     *
     * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
     */
    public SpaceWars restoreGame(String fname) //Object serialisation is used here
    {
        SpaceWars game = null;
        try
        {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (SpaceWars) ois.readObject();
            ois.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("There was an error while restoring the game!");
        }
        return game;
    }
}



