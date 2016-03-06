package vagrant.impl.cli.parser;

import vagrant.api.domain.MachineState;

public class MachineStateParser {
   public MachineState parse(String state) {
      if ("not_created".equals(state)) {
         return MachineState.POWER_OFF;
      } else if ("poweroff".equals(state)) {
         return MachineState.POWER_OFF;
      } else if ("running".equals(state)) {
         return MachineState.RUNNING;
      } else if("saved".equals(state)) {
         return MachineState.SAVED;
      } else {
         return null; // unknown
         // https://github.com/mitchellh/vagrant/blob/3f8274f5259a380ed60af2841c9392286f7c9c52/templates/locales/en.yml#L1563
         // includes:
         // * paused
         // * stopping
         // * saving
         // * stuck
         // * aborted
         // * gurumeditation
         // * inaccessible
      }
   }
}
