plugins {
   id("com.android.application")
    kotlin("android")
}

apply{
    from("../basic.gradle")
}

android {
    compileSdk =32
    defaultConfig {
        applicationId = "com.navigation.in"
        minSdk = 21
        targetSdk =32
        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
    }
//
//    signingConfigs {
//        create("keyStore") {
//            keyAlias = "component"
//            keyPassword = "123456"
//            storeFile = file("xx.keystore")
//            storePassword = "123456"
//        }
//    }
    buildTypes {
//        val signConfig = signingConfigs.getByName("keyStore")
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig = signConfig
//            buildConfigField( "String", "BASE_URL", "https://www.wanandroid.com")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig = signConfig

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding.isEnabled=true

    // 输出类型
    android.applicationVariants.all {
        // 编译类型
        val buildType = this.buildType.name
        outputs.all {
            // 判断是否是输出 apk 类型
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "KOTLIN_DSL_V${defaultConfig.versionName}_$buildType.apk"
            }
        }
    }
}



dependencies {
    implementation(project( ":base"))
    implementation(project( ":home"))
    implementation(project(":square"))
    implementation(project( ":mine"))

}


