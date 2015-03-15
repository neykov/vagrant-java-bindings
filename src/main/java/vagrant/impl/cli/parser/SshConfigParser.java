package vagrant.impl.cli.parser;

import java.util.HashMap;
import java.util.Map;

import vagrant.api.domain.SshConfig;

public class SshConfigParser implements CliParser<Map<String, SshConfig>> {

    @Override
    public Map<String, SshConfig> parse(String out) {
        String[] lines = out.split("\r?\n");
        Map<String, SshConfig> ret = new HashMap<String, SshConfig>();
        SshConfig config = null;
        for (String line : lines) {
            if (line.startsWith("Host")) {
                config = new SshConfig();
                String machineName = line.substring("Host".length() + 1);
                ret.put(machineName, config);
            } else {
                String s = line.trim();
                if (s.startsWith("HostName")) {
                    config.setHostName(s.substring("HostName".length() + 1));
                } else if (s.startsWith("User ")) {
                    config.setUser(s.substring("User ".length()));
                } else if (s.startsWith("Port")) {
                    config.setPort(Integer.parseInt(s.substring("Port".length() + 1)));
                } else if (s.startsWith("IdentityFile")) {
                    config.setIdentityFile(s.substring("IdentityFile".length() + 1));
                }
            }
        }
        return ret;
    }

}
