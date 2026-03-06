package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandArgsException;
import ru.itmo.galiya.interpreter.CommandInterpreter;
import ru.itmo.galiya.interpreter.Environment;

public class HelpCommand extends Command {
    private final CommandInterpreter interpreter;
    public HelpCommand(CommandInterpreter interpreter) {
        this.interpreter = interpreter;
    }
    Override
    public String getName() {
        return "help";
    }
    Override
    public String getName() {
        return "help - показать список всех команд в системе";
    }
    Override
    public void execute(Environment env, String[] args) {
        System.out.println("Доступные команды:");
    }
}
