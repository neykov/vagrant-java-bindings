package vagrant.impl.cli.parser;

public interface CliParser<T> {
    T parse(String out);
}
