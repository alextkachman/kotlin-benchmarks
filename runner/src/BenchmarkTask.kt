package org.mbte.kotlin.benchmarks.runner

import kotlin.util.measureTimeMillis
import java.util.HashMap
import com.google.common.collect.Maps
import com.google.common.collect.Multimaps
import com.google.common.base.Supplier
import com.google.common.collect.Multimap

public abstract class BenchmarkTask(val name: String) {
    protected abstract fun createForLanguage(language: Language): Runnable

    protected open fun configure() {}

    protected val stat: Multimap<Language,Long> =
        Multimaps.newListMultimap(Maps.newEnumMap<Language,List<Long>>(javaClass<Language>()), object : Supplier<List<Long>> {
            public override fun get(): List<Long>? = arrayList()
        })

    public fun execute(times: Int) {
        configure()

        for (i in 1..times) {
            for (lang in Language.values()) {
                val time = measureTimeMillis {
                    createForLanguage(lang).run()
                }
                stat.put(lang, time)
                System.out.println("$i $lang $name $time")
            }
        }
    }
}
