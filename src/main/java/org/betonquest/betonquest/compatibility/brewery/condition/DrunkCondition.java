package org.betonquest.betonquest.compatibility.brewery.condition;

import com.dre.brewery.BPlayer;
import com.dre.brewery.api.BreweryApi;
import org.betonquest.betonquest.api.profile.OnlineProfile;
import org.betonquest.betonquest.api.quest.QuestException;
import org.betonquest.betonquest.api.quest.condition.online.OnlineCondition;
import org.betonquest.betonquest.instruction.variable.VariableNumber;

/**
 * If a player has reached a certain level of drunkenness.
 */
public class DrunkCondition implements OnlineCondition {
    /**
     * The {@link VariableNumber} for the drunkenness level.
     */
    private final VariableNumber drunkVar;

    /**
     * Create a new Drunk Condition.
     *
     * @param drunkVar the {@link VariableNumber} for the drunkenness level.
     */
    public DrunkCondition(final VariableNumber drunkVar) {
        this.drunkVar = drunkVar;
    }

    @Override
    public boolean check(final OnlineProfile profile) throws QuestException {
        final int drunkenness = drunkVar.getValue(profile).intValue();
        if (drunkenness < 0 || drunkenness > 100) {
            throw new QuestException("Drunkenness can only be between 0 and 100!");
        }

        final BPlayer bPlayer = BreweryApi.getBPlayer(profile.getPlayer());
        return bPlayer != null && bPlayer.getDrunkeness() >= drunkenness;
    }
}
