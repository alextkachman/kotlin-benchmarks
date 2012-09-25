package org.mbte.kotlin.benchmarks.runner

import com.google.common.base.Supplier
import com.google.common.collect.Maps

public abstract class StandardTask(name: String): BenchmarkTask(name) {
    protected val byLanguage: MutableMap<Language,Supplier<Runnable>> = Maps.newEnumMap<Language,Supplier<Runnable>>(javaClass<Language>())

    protected override fun createForLanguage(language: Language): Runnable  = byLanguage[language]!!.get()!!

    public fun java(klass: Class<out Runnable>) {
        byLanguage[Language.JAVA] = supplier(klass)
    }

    public fun kotlin(klass: Class<Runnable>) {
        byLanguage[Language.KOTLIN] = supplier(klass)
    }

    public fun kotlin(factory: ()->(()->Unit)) {
        byLanguage[Language.KOTLIN] = supplier(factory)
    }

    private fun supplier(klass: Class<out Runnable>) = object:  Supplier<Runnable> {
        public override fun get(): Runnable = klass.newInstance()
    }

    private fun supplier(factory: ()->(()->Unit)) = object:  Supplier<Runnable> {
        public override fun get(): Runnable = runnable(factory())
    }
}
