import java.util.concurrent.*;
import java.util.*;

class ResultTask implements Callable<String> {
    private final String file;
    ResultTask(String file) { this.file = file; }
    public String call() throws Exception {
        if (file.contains("missing") || file.contains("corrupt"))
            throw new Exception("Invalid file: " + file);
        return file + " processed";
    }
}
public class ResultProcessor {
    public static void main(String[] args) throws InterruptedException {
        List<String> files = Arrays.asList("script1.txt", "script2.txt", "corrupt3.txt",
                "script4.txt", "missing5.txt", "script6.txt");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = new ArrayList<>();
        for (String f : files) futures.add(pool.submit(new ResultTask(f)));
        int success = 0, failure = 0;
        for (Future<String> f : futures) {
            try {
                System.out.println("Success: " + f.get());
                success++;
            } catch (ExecutionException e) {
                System.out.println("Failure: " + e.getCause().getMessage());
                failure++;
            }
        }
        pool.shutdown();
        System.out.println("Summary: " + success + " successful, " + failure + " failed.");
    }
}
