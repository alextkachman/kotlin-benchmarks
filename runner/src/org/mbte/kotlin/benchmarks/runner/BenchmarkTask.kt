package org.mbte.kotlin.benchmarks.runner

import kotlin.util.measureTimeMillis
import java.util.HashMap
import com.google.common.collect.Maps
import com.google.common.collect.Multimaps
import com.google.common.base.Supplier
import com.google.common.collect.Multimap
import com.google.common.collect.ArrayListMultimap

public abstract class BenchmarkTask(val name: String) {
    protected abstract fun createForLanguage(language: Language): Runnable

    protected open fun configure() {}

    protected val stat: Multimap<Language,Long> = ArrayListMultimap.create<Language,Long>()

    public fun execute(times: Int) {
        configure()

        for (i in 1..times) {
            for (lang in Language.values()) {
                System.out.println("Running $name::$lang (pass $i)\n")
                val time = measureTimeMillis {
                    createForLanguage(lang).run()
                }
                stat.put(lang, time)
                System.out.println("\nCompleted $name::$lang  (pass $i) time: ${time}ms\n")
            }
        }
    }
}
