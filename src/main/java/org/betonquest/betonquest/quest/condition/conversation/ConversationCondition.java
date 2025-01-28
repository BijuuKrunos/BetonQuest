package org.betonquest.betonquest.quest.condition.conversation;

import org.betonquest.betonquest.api.feature.FeatureAPI;
import org.betonquest.betonquest.api.profile.Profile;
import org.betonquest.betonquest.api.quest.QuestException;
import org.betonquest.betonquest.api.quest.condition.PlayerCondition;
import org.betonquest.betonquest.conversation.ConversationData;
import org.betonquest.betonquest.id.ConversationID;

/**
 * Checks if the conversation with player has at least one possible option.
 */
public class ConversationCondition implements PlayerCondition {

    /**
     * Feature API.
     */
    private final FeatureAPI featureAPI;

    /**
     * The conversation to check.
     */
    private final ConversationID conversationID;

    /**
     * Creates a new ConversationCondition.
     *
     * @param featureAPI     the feature API
     * @param conversationID the conversation to check
     */
    public ConversationCondition(final FeatureAPI featureAPI, final ConversationID conversationID) {
        this.featureAPI = featureAPI;
        this.conversationID = conversationID;
    }

    @Override
    public boolean check(final Profile profile) throws QuestException {
        final ConversationData conversation = featureAPI.getConversation(conversationID);
        if (conversation == null) {
            throw new QuestException("Tried to check conversation '" + conversationID.getFullID()
                    + "' but it is not loaded! Ensure it was loaded without errors.");
        }
        try {
            return conversation.isReady(profile);
        } catch (final QuestException e) {
            throw new QuestException(e.getMessage(), e);
        }
    }
}
