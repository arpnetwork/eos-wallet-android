package org.arpnetwork.eoswallet.net;

import org.arpnetwork.eoswallet.net.callbck.JsonConvert;
import com.lzy.okgo.OkGo;
import org.arpnetwork.eoswallet.net.callbck.JsonCallback;
import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.model.Response;

import java.util.Map;

/**
 * Created by pocketEos on 2018/4/2.
 * app网络请求管理类
 */
public class HttpUtils {
    /**
     * Gets requets.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param map      the map
     * @param callback the callback
     */
    public static <T> void getRequets(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        OkGo.<T>get(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }

    public static <T> T getRequetsSync(String url, Object tag, Map<String, String> map, Class<T> cls) throws Exception {
        Call<T> call = OkGo.<T>get(url)
                .tag(tag)
                .params(map)
                .converter(new JsonConvert<T>(cls))//
                .adapt();
        Response<T> response = call.execute();
        return response.body();
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param map      the map
     * @param callback the callback
     */
    public static <T> void postRequest(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }

    /**
     * Post request.
     *
     * @param <T>      the type parameter
     * @param url      the url
     * @param tag      the tag
     * @param parms    the parms
     * @param callback the callback
     */
    public static <T> void postRequest(String url, Object tag, String parms, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .upJson(parms)
                .execute(callback);
    }

    public static <T> T postRequestSync(String url, Object tag, Map<String, String> map, Class<T> cls) throws Exception {
        Call<T> call = OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .converter(new JsonConvert<T>(cls))//
                .adapt();

        Response<T> response = call.execute();
        return response.body();
    }

    public static <T> T postRequestSync(String url, Object tag, String parms, Class<T> cls) throws Exception {
        Call<T> call = OkGo.<T>post(url)
                .tag(tag)
                .upJson(parms)
                .converter(new JsonConvert<T>(cls))//
                .adapt();

        Response<T> response = call.execute();
        return response.body();
    }

    public static void cancel(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    public static void cancelAll() {
        OkGo.getInstance().cancelAll();
    }
}
