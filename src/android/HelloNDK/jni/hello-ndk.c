#include <jni.h>
#include <string.h>
#include <android/log.h>

#define DEBUG_TAG "Hello_NDK"

void Java_com_example_hellondk_MainActivity_OlaLog(JNIEnv * env, jobject this, jstring log)
{
    jboolean copy;
    const char * cstring = (*env)->GetStringUTFChars(env, log, &copy);
    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "%s", cstring);
    (*env)->ReleaseStringUTFChars(env, log, cstring);
}
