package org.betonquest.betonquest.api.feature;

import org.betonquest.betonquest.conversation.ConversationData;
import org.betonquest.betonquest.feature.QuestCanceler;
import org.betonquest.betonquest.feature.QuestCompass;
import org.betonquest.betonquest.id.CompassID;
import org.betonquest.betonquest.id.ConversationID;
import org.betonquest.betonquest.id.QuestCancelerID;
import org.betonquest.betonquest.quest.registry.QuestRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * The Feature logic.
 */
public final class FeatureAPI {

    /**
     * Quest Registry providing processors.
     */
    private final QuestRegistry questRegistry;

    /**
     * Create a new Feature API.
     *
     * @param questRegistry the registry containing processors
     */
    public FeatureAPI(final QuestRegistry questRegistry) {
        this.questRegistry = questRegistry;
    }

    /**
     * Gets stored Conversation Data.
     * <p>
     * The conversation data can be null if there was an error loading it.
     *
     * @param conversationID package name, dot and name of the conversation
     * @return ConversationData object for this conversation or null if it does
     * not exist
     */
    @Nullable
    public ConversationData getConversation(final ConversationID conversationID) {
        return questRegistry.conversations().getValues().get(conversationID);
    }

    /**
     * Get the loaded Quest Canceler.
     *
     * @return quest cancelers in a new map
     */
    public Map<QuestCancelerID, QuestCanceler> getCancelers() {
        return new HashMap<>(questRegistry.cancelers().getValues());
    }

    /**
     * Gets stored Quest Canceler.
     * <p>
     * The canceler data can be null if there was an error loading it.
     *
     * @param cancelerID package name, dot and name of the conversation
     * @return QuestCanceler or null if it does not exist
     */
    @Nullable
    public QuestCanceler getCanceler(final QuestCancelerID cancelerID) {
        return questRegistry.cancelers().getValues().get(cancelerID);
    }

    /**
     * Get the loaded Compasses.
     *
     * @return compasses in a new map
     */
    public Map<CompassID, QuestCompass> getCompasses() {
        return new HashMap<>(questRegistry.compasses().getValues());
    }
}
