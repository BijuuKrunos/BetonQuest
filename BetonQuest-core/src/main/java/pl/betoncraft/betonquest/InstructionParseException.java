/*
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2016  Jakub "Co0sh" Sapalski
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
package pl.betoncraft.betonquest;

/**
 * Exception thrown when the instruction string has a wrong format.
 * @deprecated Use the {@link pl.betoncraft.betonquest.exceptions.InstructionParseException} instead, this will be removed in the near future
 */
public class InstructionParseException extends pl.betoncraft.betonquest.exceptions.InstructionParseException{

    private static final long serialVersionUID = 7487088647464022627L;

    /**
     * {@link Exception#Exception(String)}
     */
    public InstructionParseException(final String message) {
        super(message);
    }

    /**
     * {@link Exception#Exception(String, Throwable)}
     */
    public InstructionParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@link Exception#Exception(Throwable)}
     */
    public InstructionParseException(final Throwable cause) {
        super(cause);
    }
}
