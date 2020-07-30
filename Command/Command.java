import java.util.HashMap;

// Command Interface
interface Command {
    void execute();
}

// The Invoker class 
class ExecuteOperation {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    
    public void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }
}

// The Receiver class
class BankOperation {
    public void printMoveOfFunds() {
        System.out.println("Printing statements on the movement of funds...");
    }

    public void updateExchangeRates() {
        System.out.println("Updating exchange rates for the current day...");
    }

    public void doCreditAnalysis() {
        System.out.println("Doing credit analysis...");
    }
}

// Concrete Command 1
class PrintMoveOfFundsCommand implements Command {
    private final BankOperation queue;

    public PrintMoveOfFundsCommand(BankOperation queue) {
        this.queue = queue;
    }

    @Override // Command
    public void execute() {
        queue.printMoveOfFunds();
    }
}

// ConcreteCommand 2
class UpdateExchangeRatesCommand implements Command {
    private final BankOperation queue;

    public UpdateExchangeRatesCommand(BankOperation queue) {
        this.queue = queue;
    }

    @Override // Command
    public void execute() {
        queue.updateExchangeRates();
    }
}

// ConcreteCommand 3
class DoCreditAnalysis implements Command {
    private final BankOperation queue;

    public DoCreditAnalysis(BankOperation queue) {
        this.queue = queue;
    }

    @Override // Command
    public void execute() {
        queue.doCreditAnalysis();
    }
}