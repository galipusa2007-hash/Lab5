package ru.itmo.galiya.command;

import ru.itmo.galiya.interpreter.Command;
import ru.itmo.galiya.interpreter.CommandInterpreter;
import ru.itmo.galiya.interpreter.Environment;

public class ExitCommand extends Command {

    public ExitCommand(CommandInterpreter interpreter) {
        super("exit", "exit - выйти из системы", false);
    }
    @Override
    public void execute(Environment env, String[] args){
        System.exit(0);
    }
}
