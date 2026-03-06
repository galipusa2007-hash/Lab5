package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandInterpreter;
import ru.itmo.galiya.interpreter.Environment;

public class HelpCommand extends Command {

    private final CommandInterpreter interpreter;

    public HelpCommand(CommandInterpreter interpreter) {
        super("help", "help - показать список всех команд в системе", false);
        this.interpreter = interpreter;
    }

    @Override
    public void execute(Environment env, String[] args) {
        System.out.println("Доступные команды:");
        for (Command command : interpreter.getCommands().values()) {
            System.out.println(command.getName());
        }
    }
}
