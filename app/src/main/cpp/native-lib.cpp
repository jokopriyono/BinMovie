#include <jni.h>
#include <string>

extern "C" jstring
Java_com_bin_movie_BinMovieApp_baseUrl(
        JNIEnv *env,
        jobject
) {
    std::string baseUrl = "https://api.themoviedb.org/3/"; /* dev */
//    std::string baseUrl = "https://api.themoviedb.org/3/"; /* staging */
//    std::string baseUrl = "https://api.themoviedb.org/3/"; /* release */
    return env->NewStringUTF(baseUrl.c_str());
}