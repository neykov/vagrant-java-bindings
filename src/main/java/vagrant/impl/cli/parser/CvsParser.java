package vagrant.impl.cli.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CvsParser implements CliParser<Collection<Map<String,String>>>{

    @Override
    public Collection<Map<String, String>> parse(String out) {
        String[] lines = out.split("\r?\n");
        String prevName = null;
        Set<String> seenKeys = new HashSet<String>();
        Map<String, String> map = null;
        Collection<Map<String, String>> ret = new ArrayList<Map<String,String>>();
        for (String line : lines) {
            String[] tokens = line.split(",", 4);
            String name = tokens[1];
            String key = tokens[2];
            String value = tokens[3];
            if (prevName == null || !prevName.equals(name) || seenKeys.contains(key)) {
                if (map != null) {
                    ret.add(map);
                }
                map = new HashMap<String, String>();
                if (!name.isEmpty()) {
                   map.put("machine", name);
                }
                seenKeys.clear();
            }
            map.put(key, value.replace("%!(VAGRANT_COMMA)", ","));
            prevName = name;
            seenKeys.add(key);
        }
        if (map != null) {
            ret.add(map);
        }
        return ret;
    }
    
}
