import org.mbte.kotlin.benchmarks.runner.BenchmarkTask
import kotlin.concurrent.execute
import org.mbte.kotlin.benchmarks.runner.StandardTask

public class Test : StandardTask("Test") {
    protected override fun configure() {
        java(javaClass<org.mbte.kotlin.benchmarks.java.Test>())
        kotlin {{
            Thread.sleep(100)
        }}
    }
}

fun main(args: Array<String>) {
    Test().execute(10)
}