package vagrant.impl.cli.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CvsParser implements CliParser<Collection<Map<String,String>>>{

    @Override
    public Collection<Map<String, String>> parse(String out) {
        String[] lines = out.split("\r?\n");
        String firstKey = null;
        Map<String, String> map = null;
        Collection<Map<String, String>> ret = new ArrayList<Map<String,String>>();
        for (String line : lines) {
            String[] tokens = line.split(",");
            String name = tokens[1];
            String key = tokens[2];
            String value = tokens[3];
            if (firstKey == null) {
                firstKey = key;
            }
            if (firstKey.equals(key)) {
                if (map != null) {
                    ret.add(map);
                }
                map = new HashMap<String, String>();
                map.put("machine", name);
            }
            map.put(key, value);
        }
        if (map != null) {
            ret.add(map);
        }
        return ret;
    }
    
}
