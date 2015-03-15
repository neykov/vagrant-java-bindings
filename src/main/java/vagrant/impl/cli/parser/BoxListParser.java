package vagrant.impl.cli.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import vagrant.api.domain.Box;

public class BoxListParser implements CliParser<Collection<Box>> {

    @Override
    public Collection<Box> parse(String out) {
        Collection<Map<String, String>> list = new CvsParser().parse(out);
        Collection<Box> boxes = new ArrayList<Box>();
        for (Map<String, String> item : list) {
            boxes.add(new Box(item.get("box-name"), item.get("box-version"), item.get("box-provider")));
        }
        return boxes;
    }

}
