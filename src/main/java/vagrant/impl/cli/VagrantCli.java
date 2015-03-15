package vagrant.impl.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class VagrantCli {
    private File path;
    private List<String> args;

    public VagrantCli(File path) {
        if (!path.exists()) {
            throw new IllegalStateException("Vagrant machine path " + path + " doesn't exist.");
        }
        this.path = path;
        args = new ArrayList<String>();
        args.add("vagrant");
    }
    
    public VagrantCli arg(String arg) {
        args.add(arg);
        return this;
    }
    
    public VagrantCli machineReadable() {
        return arg("--machine-readable");
    }
    
    public String execute() {
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.directory(path);
        pb.redirectErrorStream();
        try {
            Process p = pb.start();
            p.getOutputStream().close();
            String out = readFully(p.getInputStream());
            if (p.waitFor() != 0) {
                throw new IllegalStateException("Invaid return code " + p.exitValue() + ".\n" + out);
            }
            return out;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFully(InputStream in) throws IOException {
        Reader r = new InputStreamReader(in);
        StringBuilder str = new StringBuilder();
        char[] buff = new char[4096];
        int read;
        while ((read = r.read(buff, 0, buff.length)) != -1) {
            str.append(buff, 0, read);
        }
        r.close();
        return str.toString();
    }
}
