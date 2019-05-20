package com.qry.base.application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.ActivityCompat;

import com.qry.base.util.L;
import com.qry.base.util.LvUtils;
import com.qry.base.util.SpKeys;
import com.qry.base.util.SpUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends MultiDexApplication {

    // 质检Ip配置
    public static String serverIp = "192.168.0.121";//  质检服务器IP
    public static int serverPort = 8080;// 质检服务器端口
    public static String serverAppName = "DadeMes";// 质检服务器应用的名称
    //模具Ip配置
    public static String serverMujuIp = "192.168.0.121";//  模具服务器IP
    public static int serverMujuPort = 8080;// 模具服务器端口
    public static String serverMujuAppName = "device-mgr";//模具 服务器应用的名称

    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    public static Map<String, Object> DataCacheList = new HashMap<>();// 用于整个application中的某些数据的缓存
    private static OkHttpClient okHttpClient;
    @SuppressLint("StaticFieldLeak")
    private static MyApplication instance;//  application 的当前实例引用
    private boolean baseSettingChanged;// 基础 Qc设置是否变化
    private boolean baseSettingMouldChanged;// 基础 mould设置是否变化
    private boolean baseSettingMianMouldChanged;// 基础 mould设置是否变化

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity 获取读取权限
     */
    public static void verifyStoragePermissions(Context activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions((Activity) activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        mContext = instance.getApplicationContext();
        okHttpClient = createHttpClient();
        LvUtils.init(this);
        // 初始化设置
        initSetting();
    }

    //获取保存的token
    public static String getMouldUserToken() {
        return Objects.requireNonNull(SpUtils.get(instance, SpKeys.MouldUserToken, "")).toString();

    }


    public static synchronized MyApplication getInstance() {
        return instance;
    }

    /*实始化参数*/
    private void initSetting() {
        serverIp = Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerIp, "192.168.0.121")).toString();
        serverPort = Integer.parseInt(Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerPort, 8080)).toString());
        serverAppName = Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerAppName, "DadeMes")).toString();


        serverMujuIp = Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerMujuIp, "192.168.0.121")).toString();
        serverMujuPort = Integer.parseInt(Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerMujuPort, 8080)).toString());
        serverMujuAppName = Objects.requireNonNull(SpUtils.get(instance, SpKeys.ServerMujuAppName, "device-mgr")).toString();


    }


    /*更新质检服务器的相关设置*/
    public void setSeverSetting(String ip, int port, String appName) {
        serverIp = ip;
        serverPort = port;
        serverAppName = appName;
        baseSettingChanged = true;
    }

    /*更新模具服务器的相关设置*/
    public void setMujuSeverSetting(String ip, int port, String appName) {
        serverMujuIp = ip;
        serverMujuPort = port;
        serverMujuAppName = appName;
        baseSettingMouldChanged = true;
        baseSettingMianMouldChanged = true;
    }


    /*取得服务器的base url*/
    public String getBaseUrl() {
        return getQcBaseUrl();

    }

    //获取质检的BaseUrl
    public String getQcBaseUrl() {
        String tmpIp = serverIp;
        if (!StringUtils.startsWith(tmpIp, "http")) {
            tmpIp = "http://" + serverIp;
        }
        return tmpIp + ":" + serverPort + "/" + serverAppName + "/";
    }

    //获取模具的BaseUrl
    public String getMujuBaseUrl() {
        String temMujuIp = serverMujuIp;
        if (!StringUtils.startsWith(temMujuIp, "http")) {
            temMujuIp = "http://" + serverMujuIp;
        }
        return temMujuIp + ":" + serverMujuPort + "/" + serverMujuAppName + "/";
    }

    //配置图片的地址
    public String getMouldImagewUrl() {
        String temMujuIp = serverMujuIp;
        if (!StringUtils.startsWith(temMujuIp, "http")) {
            temMujuIp = "http://" + serverMujuIp;
        }
        return temMujuIp + ":" + serverMujuPort + "/" + serverMujuAppName;
    }

    public static OkHttpClient getHttpClient() {
        return okHttpClient;
    }

    public boolean isBaseSettingChanged() {
        boolean result = baseSettingChanged;
        baseSettingChanged = false;
        return result;

    }

    //当模具地址变换的时候
    public boolean isBaseSettingMouldChanged() {
        boolean result = baseSettingMouldChanged;
        baseSettingMouldChanged = false;
        return result;

    }

    //当模具地址变换的时候
    public boolean isBaseSettingMianMouldChanged() {
        boolean result = baseSettingMianMouldChanged;
        baseSettingMianMouldChanged = false;
        return result;

    }


    /**
     * 得到需要请求的header
     *
     * @param
     * @return
     * @author penghj
     * @Date 2018/7/25 17:41
     */
    private static OkHttpClient createHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                L.d("API", "retrofitBack = " + message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new MouldeLoginIntercepter())//登录拦截器
                .addInterceptor(loggingInterceptor)//打印返回的数据
                .addInterceptor(new Interceptor() {// 调试时的日志显示
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();

                        long t1 = System.nanoTime();
                        L.d("API", String.format("Sending %s", request.url()));
                        if (request.body() != null && request.body().contentType() != null) {
                            L.d("API", "body-type:" + request.body().contentType());
                            L.d("API", "body-content-length:" + request.body().contentLength());
                        }
                        Response response = chain.proceed(request);

                        long t2 = System.nanoTime();
                        L.d("API", String.format(Locale.CHINA, "Received for %s in %.1fms%n%s%n",
                                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                        return response;

                    }
                }) // 可以这里进行日志的记录
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("x-requested-with", "XMLHttpRequest")
                                .build();
                        return chain.proceed(request);
                    }
                })

                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .cookieJar(new CookieJar() {// 保存cookie中的 sessionId
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                //其他配置
                .build();
    }


}
