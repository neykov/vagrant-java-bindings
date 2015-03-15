package vagrant.api;

import java.util.Collection;

import vagrant.api.domain.Box;
import vagrant.api.option.BoxAddOptions;

public interface BoxApi {
    public void add(String name, BoxAddOptions options);
    public Collection<Box> list();
}
