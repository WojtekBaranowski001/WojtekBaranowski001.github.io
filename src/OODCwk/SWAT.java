package OODCwk;

import java.io.Serializable;

/**
 * This interface specifies the behaviour expected from the SWAT
 * system as required for 6COM1037 - Nov 2021
 *
 * @author A.A.Marczyk
 * @version 25/10/2021
 */


public interface SWAT extends Serializable {
    //**************** SWAT **************************

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
    String toString();

    /**
     * returns the number of bit coins in the war chest
     *
     * @return the number of bit coins in the war chest
     */
    int getWarchest();

    /**
     * returns true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     *
     * @return true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     */
    boolean isDefeated();

    /**
     * Returns true if force is in the United Forces Fleet(UFF), else false
     *
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    boolean isInUFFleet(String ref);

    /**
     * Returns a String representation of all forces in the United Forces Fleet(UFF)
     *
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    String getUFFleet();

    /**
     * Returns details of the force in the game with the given reference code
     *
     * @return details of the force in the game with the given reference code
     **/
    String getForceDetails(String ref);


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
    int activateForce(String ref);


    /**
     * Returns true if the force with the reference code is in
     * the active Star Fleet(ASF), false otherwise.
     *
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    boolean isInASFleet(String ref);


    /**
     * Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     *
     * @param ref is the reference code of the force
     **/
    void recallForce(String ref);


    /**
     * Returns a String representation of the forces in the active
     * Star Fleet(ASF), or the message "No forces activated"
     *
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    String getASFleet();


//**********************Battles************************* 

    /**
     * returns true if the number represents a battle
     *
     * @param num is the number of the required battle
     * @return true if the number represents a battle
     **/
    boolean isBattle(int num);

    /**
     * Provides a String representation of a battle given by
     * the battle number
     *
     * @param num the number of the battle
     * @return returns a String representation of a battle given by
     * the battle number
     **/
    String getBattle(int num);


    /**
     * Provides a String representation of all battles
     *
     * @return returns a String representation of all battles
     **/
    String getAllBattles();


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
    int doBattle(int battleNo);

    //These methods are not needed until Task 4.5
    // ***************   file write/read  *********************

    /**
     * Writes whole game to the specified file
     *
     * @param fname name of file storing requests
     */
    void saveGame(String fname);

    /**
     * reads all information about the game from the specified file
     * and returns a SpaceWars object
     *
     * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
     */
    SpaceWars restoreGame(String fname);
}
