pluginManagement {
    repositories {
//        maven { url== uri("https://maven.aliyun.com/repository/google") }
//        maven { url == uri("https://maven.aliyun.com/repository/jcenter") }
//        maven { url == uri("https://maven.aliyun.com/nexus/content/groups/public") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven { url == uri("https://maven.aliyun.com/repository/google") }
//        maven { url == uri("https://maven.aliyun.com/repository/jcenter") }
//        maven { url == uri("https://maven.aliyun.com/nexus/content/groups/public") }
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Outfit"
include(":app")
 