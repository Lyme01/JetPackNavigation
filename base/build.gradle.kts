@Suppress("DSL_SCOPE_VIOLATION")//处理alias报错
plugins {
    id ("com.android.library")
    alias(libs.plugins.ksp)
}
apply {
    from( "../basic.gradle")
}



dependencies {

    api(libs.android.material)
    api(libs.android.constraintlayout)
    api(libs.androidx.lifecycle.viewmodel)
    api(libs.junit4)
    api(libs.androidx.test.ext)
    api(libs.androidx.test.espresso.core)
    api(libs.kotlinx.coroutines.core)
    api(libs.retrofit)
    api(libs.okhttp.logging)
    api(libs.gson)
    api(libs.persistentCookieJar)
    api(libs.androidx.navigation.fragment)
    api(libs.androidx.navigation.ui)
    api(libs.permissionx)
    api(libs.mmkv)
//    api(libs.glide.glide)
//    ksp(libs.glide.compiler)












//    api 'com.google.android.material:material:1.5.0'
//    api 'androidx.constraintlayout:constraintlayout:2.1.3'
//    api 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
//    testApi 'junit:junit:4.+'
//    androidTestApi 'androidx.test.ext:junit:1.1.3'
//    androidTestApi 'androidx.test.espresso:espresso-core:3.4.0'
//
//    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
//
//    api 'com.squareup.retrofit2:converter-gson:2.9.0'
//    api 'com.squareup.okhttp3:logging-interceptor:4.9.3'
//    api 'com.google.code.gson:gson:2.9.0'
//    api "com.github.franmontiel:PersistentCookieJar:v1.0.1"
//
//    def navigation_version = "2.5.3"
//    api "androidx.navigation:navigation-fragment-ktx:$navigation_version"
//    api "androidx.navigation:navigation-ui-ktx:$navigation_version"
//
//    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7'
//
//    //mmkv
//    api 'com.tencent:mmkv-static:1.2.10'
//
////    def refresh_version = "2.0.3"
////    api "com.scwang.smart:refresh-layout-kernel:$refresh_version"      //核心必须依赖
////    api "com.scwang.smart:refresh-footer-classics:$refresh_version"    //经典加载
////    api "com.scwang.smart:refresh-header-material:$refresh_version"     //谷歌刷新头
//
//
//    api 'com.guolindev.permissionx:permissionx:1.6.0'
//
//    api 'com.github.bumptech.glide:glide:4.12.0'
//    kapt 'com.github.bumptech.glide:compiler:4.12.0'
////    api  'com.github.liangjingkanji:BRV:1.3.54'



//    // 下拉刷新
//    api ("com.google.accompanist:accompanist-swiperefresh:0.21.5-rc")
//    api 'androidx.paging:paging-compose:1.0.0-alpha14'
//    api "androidx.paging:paging-runtime-ktx:3.1.1"
}