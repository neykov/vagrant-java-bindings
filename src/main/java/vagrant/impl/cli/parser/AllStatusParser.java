package vagrant.impl.cli.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import vagrant.api.domain.Machine;

public class AllStatusParser implements CliParser<Collection<Machine>> {
    private File path;
    public AllStatusParser(File path) {
        this.path = path;
    }


    @Override
    public Collection<Machine> parse(String out) {
        Collection<Map<String, String>> parsed = new CvsParser().parse(out);
        Collection<Machine> ret = new ArrayList<Machine>();
        MachineStateParser stateParser = new MachineStateParser();
        for (Map<String, String> m : parsed) {
            String name = m.get("machine");
            String state = m.get("state");
            if (name == null || state == null) continue; // skip metadata, ui
            Machine machine = new Machine();
            machine.setName(name);
            machine.setStatus(stateParser.parse(state));
            machine.setPath(path);
            ret.add(machine);
        }
        return ret;
    }

}
