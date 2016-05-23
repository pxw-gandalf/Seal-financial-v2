#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <ctype.h>

//导入日志头文件
#include <android/log.h>

//导入md5头文件
#include "md5c.h"
#include "cn_knet_seal_financial_api_KnetFinancialHttpApi.h"

//修改日志tag中的值
#define LOG_TAG "seal-financial"
//日志显示的等级
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO   , LOG_TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN   , LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , LOG_TAG, __VA_ARGS__)

//jstring to char*
char * jstringTostring(JNIEnv* env, jstring jstr) {
	char* rtn = NULL;
	jclass clsstring = (*env)->FindClass(env,"java/lang/String");
	jstring strencode = (*env)->NewStringUTF(env,"utf-8");
	jmethodID mid = (*env)->GetMethodID(env,clsstring, "getBytes","(Ljava/lang/String;)[B");
	jbyteArray barr = (jbyteArray) (*env)->CallObjectMethod(env,jstr, mid,strencode);
	jsize alen = (*env)->GetArrayLength(env,barr);
	jbyte* ba = (*env)->GetByteArrayElements(env,barr, JNI_FALSE);
	if (alen > 0) {
		rtn = (char*) malloc(alen + 1);
		memcpy(rtn, ba, alen);
		rtn[alen] = 0;
	}
	(*env)->ReleaseByteArrayElements(env,barr, ba, 0);
	return rtn;
}
char * jstringTostring(JNIEnv* env, jstring jstr);

/*char **/ void getmd5(char* str,char* des);
char * strToUpper(char *str);
void strToLower(char *str);

jstring Java_cn_knet_seal_financial_api_KnetFinancialHttpApi_ct( JNIEnv* env,jobject thiz, jstring token, jstring t, jstring p)
{
	char* tokenstr = jstringTostring(env,token);
	char* tstr = jstringTostring(env,t);
	char* pstr = jstringTostring(env,p);
	char md5c[33]={0};
	char c_char[20480]="";
	strcat(c_char,tokenstr);
	strcat(c_char,tstr);
	strcat(c_char,pstr);
	getmd5(c_char,md5c);
	strToLower(md5c);
	jstring result = (*env)->NewStringUTF(env,md5c);
	return result;
}
jstring Java_cn_knet_seal_financial_api_KnetFinancialHttpApi_pt( JNIEnv* env,jobject thiz, jstring u, jstring t, jstring p)
{
	char* ustr = jstringTostring(env,u);
	char* tstr = jstringTostring(env,t);
	char* pstr = jstringTostring(env,p);
	char md5c[33]={0};
	char c_char[20480]="";
	strcat(c_char,ustr);
	strcat(c_char,pstr);
	strcat(c_char,tstr);
	getmd5(c_char,md5c);
	strToLower(md5c);
	jstring result = (*env)->NewStringUTF(env,md5c);
	return result;
}
jstring Java_cn_knet_seal_financial_api_KnetFinancialHttpApi_cpt( JNIEnv* env,jobject thiz, jstring token, jstring t, jstring p, jstring np)
{
	char* tokenstr = jstringTostring(env,token);
	char* tstr = jstringTostring(env,t);
	char* pstr = jstringTostring(env,p);
	char* npstr = jstringTostring(env,np);
	char md5c[33]={0};
	char c_char[20480]="";
	strcat(c_char,tokenstr);
	strcat(c_char,pstr);
	strcat(c_char,npstr);
	strcat(c_char,tstr);
	getmd5(c_char,md5c);
	strToLower(md5c);
	jstring result = (*env)->NewStringUTF(env,md5c);
	return result;
}
/*char**/void  getmd5(char* str,char* destination) {

    MD5_CTX context = { 0 };
    MD5Init(&context);
    MD5Update(&context, str, strlen(str));
    unsigned char dest[16] = { 0 };
    MD5Final(dest, &context);

    int i;
//    char destination[32]={0};
//    char* destination = (char*) malloc(2048);
    for (i = 0; i < 16; i++) {
        sprintf(destination, "%s%02x", destination, dest[i]);
    }
//    LOGI("%s", destination);
//    return destination;
}

char * strToUpper(char *str)
{
     while(*str=toupper(*str))
        str++;
}
void strToLower(char *str)
{
	int i;
	for(i=0;i< strlen(str);i++)
	{
		str[i] = tolower(str[i]);
	}
}
//available
//jstring Java_cn_knet_seal_sdk_openapi_SealLogoApi_md5(JNIEnv* env,
//        jclass clazz, jstring jInfo) {
//    char* jstr = jstringTostring(env, jInfo);
//
//    MD5_CTX context = { 0 };
//    MD5Init(&context);
//    MD5Update(&context, jstr, strlen(jstr));
//    unsigned char dest[16] = { 0 };
//    MD5Final(dest, &context);
//
//    int i;
//    char destination[32]={0};
//    for (i = 0; i < 16; i++) {
//        sprintf(destination, "%s%02x", destination, dest[i]);
//    }
//    LOGI("%s", destination);
//    return (*env)->NewStringUTF(env, destination);
//}

