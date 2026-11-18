import java.io.*;
import java.nio.file.*;

public class BufferBenchmark {
    static long countUnbuffered(Path path) throws IOException {
        long lines = 0;
        try (InputStream in = new FileInputStream(path.toFile())) {
            int b; while ((b = in.read()) != -1) if (b == '\n') lines++;
        }
        return lines;
    }
    static long countBuffered(Path path) throws IOException {
        long lines = 0;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            int b; while ((b = in.read()) != -1) if (b == '\n') lines++;
        }
        return lines;
    }
    public static void main(String[] args) throws Exception {
        Path file = Paths.get("attendance.log");
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            for (int i = 1; i <= 100000; i++) w.write("Attendance record " + i + "\n");
        }
        long start = System.nanoTime(); long a = countUnbuffered(file); long t1 = System.nanoTime() - start;
        start = System.nanoTime(); long b = countBuffered(file); long t2 = System.nanoTime() - start;
        System.out.println("Unbuffered lines: " + a + ", time: " + t1 / 1_000_000.0 + " ms");
        System.out.println("Buffered lines: " + b + ", time: " + t2 / 1_000_000.0 + " ms");
        System.out.println("Counts match: " + (a == b));
    }
}
