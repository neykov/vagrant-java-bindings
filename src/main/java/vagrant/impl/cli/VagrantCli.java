package vagrant.impl.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import vagrant.api.CommandIOListener;

public class VagrantCli {
    private static final Logger LOG = Logger.getLogger(VagrantCli.class.getName());

    private final File path;
    private final List<String> args;
    private final CommandIOListener ioListener;

    public VagrantCli(File path, CommandIOListener ioListener) {
        if (!path.exists()) {
            throw new IllegalStateException("Vagrant machine path " + path + " doesn't exist.");
        }
        this.path = path;
        this.ioListener = ioListener;
        this.args = new ArrayList<String>();
        this.args.add("vagrant");
    }

    public VagrantCli arg(String arg) {
        args.add(arg);
        return this;
    }

    public VagrantCli machineReadable() {
        return arg("--machine-readable");
    }

    public String execute() {
        synchronized (getClass()) {
            return executeSequential();
        }
    }

    public String executeSequential() {
        long start = System.currentTimeMillis();
        String logArgs = args.toString();
        LOG.fine(start + " - VagrantCli: execute " + logArgs);
        ioListener.onInput(logArgs);
        ioListener.onInput(null);
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
            long end = System.currentTimeMillis();
            LOG.fine(end + " (" + (end-start) + ") - \n" + out);
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Failed while executing " + logArgs, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while executing " + logArgs, e);
        }
    }

    public String readFully(InputStream in) throws IOException {
        Reader r = new InputStreamReader(in);
        StringBuilder str = new StringBuilder();
        char[] buff = new char[4096];
        int read;
        try {
            while ((read = r.read(buff, 0, buff.length)) != -1) {
                String output = new String(buff, 0, read);
                str.append(output);
                ioListener.onOutput(output);
            }
        } finally {
            ioListener.onOutput(null);
        }
        r.close();
        return str.toString();
    }
}
