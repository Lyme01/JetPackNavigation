// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/groups/public")
        maven("http://maven.aliyun.com/nexus/content/groups/public/")  {
            isAllowInsecureProtocol = true
          }
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/jcenter")
        maven("https://s01.oss.sonatype.org/content/groups/public")
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}



tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}