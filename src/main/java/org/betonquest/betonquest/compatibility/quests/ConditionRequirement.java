package org.betonquest.betonquest.compatibility.quests;

import me.pikamug.quests.module.BukkitCustomRequirement;
import org.betonquest.betonquest.api.logger.BetonQuestLogger;
import org.betonquest.betonquest.api.profile.OnlineProfile;
import org.betonquest.betonquest.api.quest.QuestException;
import org.betonquest.betonquest.api.quest.QuestTypeAPI;
import org.betonquest.betonquest.id.ConditionID;
import org.betonquest.betonquest.util.PlayerConverter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

/**
 * Requires the player to meet specified condition.
 */
@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
public class ConditionRequirement extends BukkitCustomRequirement {
    /**
     * Custom {@link BetonQuestLogger} instance for this class.
     */
    private final BetonQuestLogger log;

    /**
     * Quest Type API.
     */
    private final QuestTypeAPI questTypeAPI;

    /**
     * Create a new 'Quests' Condition Requirement.
     *
     * @param log          the custom logger
     * @param questTypeAPI the Quest Type API
     */
    public ConditionRequirement(final BetonQuestLogger log, final QuestTypeAPI questTypeAPI) {
        super();
        this.log = log;
        this.questTypeAPI = questTypeAPI;
        setName("BetonQuest condition");
        setAuthor("BetonQuest");
        addStringPrompt("Condition", "Specify BetonQuest condition name (with the package, like: package.condition)", null);
    }

    @Override
    public boolean testRequirement(final UUID uuid, final Map<String, Object> dataMap) {
        final Object object = dataMap.get("Condition");
        if (object == null) {
            log.warn("Error while checking quest requirement - Missing Condition Object");
            return false;
        }
        final String string = object.toString();
        try {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                log.warn("Error while running quest reward - Player with UUID '" + uuid + "' not found.");
                return false;
            }
            final OnlineProfile onlineProfile = PlayerConverter.getID(player);
            final ConditionID condition = new ConditionID(null, string);
            return questTypeAPI.condition(onlineProfile, condition);
        } catch (final QuestException e) {
            log.warn("Error while checking quest requirement - BetonQuest condition '" + string + "' not found: " + e.getMessage(), e);
            return false;
        }
    }
}
