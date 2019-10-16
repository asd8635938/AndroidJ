package com.example.jy.jieyou.manager;


import com.alibaba.fastjson.JSON;
import com.example.jy.jieyou.response.Response;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.xutils.x.http;

/**
 * Created by DELL on 2018/12/24.
 */

public class NetManager {

    public static int Net_TimeOut = 15000;

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_post(String url, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        } else {
            string = JSON.toJSONString(body);
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(string);
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_post_file(String url, String name, ArrayList<String> mFile, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        } else {
            string = JSON.toJSONString(body);
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }

        requestParams.setAsJsonContent(true);
        requestParams.setMultipart(true);

        List<KeyValue> list = new ArrayList<>();
        if (mFile != null && mFile.size() != 0) {
            for (int i = 0; i < mFile.size(); i++) {
                File file = new File(mFile.get(i));
                list.add(new KeyValue(name, file));
            }
        }
        list.add(new KeyValue("data", string));
        MultipartBody mBoby = new MultipartBody(list, "UTF-8");
        requestParams.setRequestBody(mBoby);

        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_postId(String url, String id, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        requestParams.addBodyParameter("id", id);
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_get(String url, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        } else {
            string = JSON.toJSONString(body);
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        requestParams.setAsJsonContent(true);
        requestParams.setBodyContent(string);
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        System.out.println("requestParams = " + requestParams);
        final String finalString = string;
        http().get(requestParams, new Callback.GroupCallback.CommonCallback<String>() {

            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final Callback.CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_String(String url, String head, String displayOrder, String ismSearchCriteria, String pageInfoRequest, final onNetCallbackListener callbackListener) {
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        requestParams.addBodyParameter("displayOrder", displayOrder);
        requestParams.addBodyParameter("ismSearchCriteria", ismSearchCriteria);
        requestParams.addBodyParameter("pageInfoRequest", pageInfoRequest);
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_post_map(String url, Map<String, Object> map, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        for (String key : map.keySet()) {
            requestParams.addBodyParameter(key, map.get(key) + "");
        }
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }

    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_get_list(String url, Map<String, Object> map, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        if (map != null && map.size() != 0) {
            for (String key : map.keySet()) {
                requestParams.addBodyParameter(key, map.get(key) + "");
            }
        }
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }


    /**
     * 统一请求框架最基础方法，更换第三方可以修改这里
     *
     * @param url              请求网址
     * @param head             请求头
     * @param body             请求体
     * @param callbackListener 请求回掉
     * @return
     */
    public static void http_post_list(String url, Map<String, Object> map, String head, Object body, final onNetCallbackListener callbackListener) {
        String string = "";
        if (body instanceof String) {
            string = (String) body;
        }
        RequestParams requestParams = new RequestParams(url);
        if (!head.isEmpty()) {
            requestParams.addHeader("", head);
        }
        for (String key : map.keySet()) {
            requestParams.addBodyParameter(key, map.get(key) + "");
        }
        requestParams.setConnectTimeout(Net_TimeOut);
        requestParams.setReadTimeout(Net_TimeOut);
        final String finalString = string;
        http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String s) {
                Response mResponse = JSON.parseObject(s, Response.class);
                if (callbackListener != null) {
                    callbackListener.onSuccess(finalString, s);
                }
            }

            @Override
            public void onError(final Throwable throwable, final boolean b) {
                if (callbackListener != null) {
                    callbackListener.onError(throwable);
                }
            }

            @Override
            public void onCancelled(final CancelledException e) {
                if (callbackListener != null) {
                    callbackListener.onCancelled();
                }
            }

            @Override
            public void onFinished() {
                if (callbackListener != null) {
                    callbackListener.onFinished();
                }
            }
        });
    }
}
