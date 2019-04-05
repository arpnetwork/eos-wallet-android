package org.arpnetwork.eoswallet.app;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;

import org.arpnetwork.eoswallet.blockchain.EosDataManger;
import org.arpnetwork.eoswallet.blockchain.wallet.EosWalletManager;
import org.arpnetwork.eoswallet.util.PreferenceManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class EOSWalletApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EosWalletManager.init(getApplicationContext());
        EosDataManger.init(getApplicationContext());
        PreferenceManager.init(getApplicationContext());

        initOkGo();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        EosDataManger.fini();
        EosWalletManager.fini();
        PreferenceManager.fini();

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void initOkGo() {
        HttpHeaders headers = new HttpHeaders();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //超时时间设置
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        try {
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getAssets().open("server.cer"));
            builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        //配置https的域名匹配规则，使用不当会导致https握手失败
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

        // 其他统一的配置
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);              //全局公共头
//                .addCommonParams(httpParams);                       //全局公共参数

    }
}
