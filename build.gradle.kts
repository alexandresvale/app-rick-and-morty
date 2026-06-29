// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kover)
    alias(libs.plugins.detekt)
}

subprojects {
    if (this.buildFile.exists()) {
        apply(plugin = "io.gitlab.arturbosch.detekt")
        
        detekt {
            buildUponDefaultConfig = true
            autoCorrect = true
            config.setFrom(files("${rootProject.projectDir}/config/detekt/detekt.yml"))
        }
        
        dependencies {
            add("detektPlugins", "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
        }
    }
}

dependencies {
    subprojects.filter { it.buildFile.exists() }.forEach { proj ->
        println("🔥 Kover agregando: ${proj.path}")
        kover(proj)
    }
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "*Activity",
                    "*ScreenKt*",
                    "*Screen*"
                )
            }
        }
        verify {
            rule {
                minBound(60)
            }
        }
    }
}