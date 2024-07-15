package org.betonquest.betonquest.quest.event.burn;

import org.betonquest.betonquest.Instruction;
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.api.quest.event.EventFactory;
import org.betonquest.betonquest.exceptions.InstructionParseException;
import org.betonquest.betonquest.instruction.variable.VariableNumber;
import org.betonquest.betonquest.quest.PrimaryServerThreadData;
import org.betonquest.betonquest.quest.event.OnlineProfileRequiredEvent;
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent;

/**
 * Factory to create burn events from {@link Instruction}s.
 */
public class BurnEventFactory implements EventFactory {
    /**
     * Logger factory to create a logger for events.
     */
    private final BetonQuestLoggerFactory loggerFactory;

    /**
     * Data for primary server thread access.
     */
    private final PrimaryServerThreadData data;

    /**
     * Create the brun event factory.
     *
     * @param loggerFactory logger factory to use
     * @param data          the data for primary server thread access
     */
    public BurnEventFactory(final BetonQuestLoggerFactory loggerFactory, final PrimaryServerThreadData data) {
        this.loggerFactory = loggerFactory;
        this.data = data;
    }

    @Override
    public Event parseEvent(final Instruction instruction) throws InstructionParseException {
        final VariableNumber duration = instruction.getVarNum(instruction.getOptionalArgument("duration")
                .orElseThrow(() -> new InstructionParseException("Missing duration!")));
        return new PrimaryServerThreadEvent(
                new OnlineProfileRequiredEvent(
                        loggerFactory.create(BurnEvent.class), new BurnEvent(duration), instruction.getPackage()),
                data);
    }
}
