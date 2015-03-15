package vagrant.impl.cli.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import vagrant.api.domain.Machine;
import vagrant.api.domain.MachineState;

public class AllStatusParser implements CliParser<Collection<Machine>> {
    private File path;
    public AllStatusParser(File path) {
        this.path = path;
    }


    @Override
    public Collection<Machine> parse(String out) {
        Collection<Map<String, String>> parsed = new CvsParser().parse(out);
        Collection<Machine> ret = new ArrayList<Machine>();
        for (Map<String, String> m : parsed) {
            String name = m.get("machine");
            Machine machine = new Machine();
            machine.setId(path.getName() + "/" + name);
            machine.setName(name);
            machine.setStatus("running".equals(m.get("state")) ? MachineState.RUNNING : null);
            machine.setPath(path);
        }
        return ret;
    }

}
