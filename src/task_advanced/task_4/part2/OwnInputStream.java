package task_advanced.task_4.part2;

import java.io.IOException;
import java.io.InputStream;

class OwnInputStream extends InputStream {

    @Override
    public int read() throws IOException {
        char symbol = (char) System.in.read();
        return 1;
    }
}
