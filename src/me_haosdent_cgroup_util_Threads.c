#include <jni.h>
#include <syscall.h>
#include "me_haosdent_cgroup_util_Threads.h"

JNIEXPORT jint JNICALL
Java_me_haosdent_cgroup_util_Threads_getThreadId(JNIEnv *env, jclass obj) {
  jint tid = syscall(__NR_gettid);
  return tid;
}



















