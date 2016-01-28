/**
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2015  Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.conditions;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.Debug;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Checks the amount of points on scoreboards.
 * 
 * @author Jakub Sapalski
 */
public class ScoreboardCondition extends Condition {

    private final String objective;
    private final int count;
    
    public ScoreboardCondition(String packName, String instructions)
            throws InstructionParseException {
        super(packName, instructions);
        String[] parts = instructions.split(" ");
        if (parts.length < 3) {
            throw new InstructionParseException("Not enough arguments");
        }
        objective = parts[1];
        try {
            count = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InstructionParseException("Could not parse point amount");
        }
    }

    @Override
    public boolean check(String playerID) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective obj = board.getObjective(objective);
        if (obj == null) {
            Debug.error("Scoreboard objective " + objective + " does not exist!");
            return false;
        }
        Score score = obj.getScore(PlayerConverter.getName(playerID));
        return score.getScore() >= count;
    }

}
