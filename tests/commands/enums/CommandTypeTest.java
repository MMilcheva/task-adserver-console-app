package commands.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

      @Test
    void matchValuePerString() {
          assertSame(CommandType.CREATETASK, CommandType.valueOf("CREATETASK"));
    }
}