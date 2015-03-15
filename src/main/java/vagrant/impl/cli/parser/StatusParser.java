package vagrant.impl.cli.parser;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import vagrant.api.domain.Machine;
import vagrant.api.domain.MachineState;

public class StatusParser implements CliParser<Machine> {
    private File path;
    public StatusParser(File path) {
        this.path = path;
    }

    @Override
    public Machine parse(String out) {
        Collection<Map<String, String>> parsed = new CvsParser().parse(out);
        Map<String, String> m = parsed.iterator().next();
        String name = m.get("machine");
        Machine machine = new Machine();
        machine.setId(path.getName() + "/" + name);
        machine.setName(name);
        machine.setStatus("running".equals(m.get("state")) ? MachineState.RUNNING : null);
        machine.setPath(path);
        return machine;
    }

}
