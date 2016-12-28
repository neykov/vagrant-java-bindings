package vagrant.impl.cli.parser;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import vagrant.api.domain.Machine;

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
        machine.setName(name);
        machine.setStatus(new MachineStateParser().parse(m.get("state")));
        machine.setPath(path);
        return machine;
    }

}
