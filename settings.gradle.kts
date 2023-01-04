dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven( url = "https://www.jitpack.io" )
        maven( url= "http://maven.aliyun.com/nexus/content/groups/public/"){
            isAllowInsecureProtocol = true
        }
        maven( url= "https://maven.aliyun.com/repository/google" ){
            isAllowInsecureProtocol = true
        }
        maven( url= "https://maven.aliyun.com/repository/jcenter"){
            isAllowInsecureProtocol = true
        }
    }
    versionCatalogs {
        create("libs") {
            from(files("/libs.versions.toml"))
        }
    }
}
rootProject.name = "BaseNavigation"
include (":app")
include (":app:base")
include (":base")
include (":home")
include (":mine")
include (":square")
