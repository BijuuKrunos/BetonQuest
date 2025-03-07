package org.betonquest.betonquest.quest.variable.name;

import org.betonquest.betonquest.api.quest.variable.PlayerVariable;
import org.betonquest.betonquest.api.quest.variable.PlayerVariableFactory;
import org.betonquest.betonquest.data.PlayerDataStorage;
import org.betonquest.betonquest.instruction.Instruction;

/**
 * Factory to create {@link NpcNameVariable}s from {@link Instruction}s.
 */
public class NpcNameVariableFactory implements PlayerVariableFactory {

    /**
     * Storage for player data.
     */
    private final PlayerDataStorage dataStorage;

    /**
     * Create a NpcName variable factory.
     *
     * @param dataStorage the storage providing player data
     */
    public NpcNameVariableFactory(final PlayerDataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public PlayerVariable parsePlayer(final Instruction instruction) {
        return new NpcNameVariable(dataStorage);
    }
}
