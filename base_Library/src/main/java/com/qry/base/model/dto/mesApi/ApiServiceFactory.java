package com.qry.base.model.dto.mesApi;


import android.annotation.SuppressLint;

import com.qry.base.application.MyApplication;
import com.qry.base.model.util.CustEnumToInt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiServiceFactory {

    private static Retrofit retrofit;// 设置单例

    @SuppressLint("SimpleDateFormat")
    private static Retrofit getInstance() {
        if (retrofit == null || MyApplication.getInstance().isBaseSettingChanged()) {
   /*         Gson gson = new GsonBuilder()//配置你的Gson
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();*/

            // 配置jackson
            ObjectMapper objectMapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(Enum.class, new CustEnumToInt());
            objectMapper.registerModule(module);

            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);



            retrofit = new Retrofit.Builder()
                    .client(MyApplication.getHttpClient())
                    .baseUrl(MyApplication.getInstance().getBaseUrl()) // 设置网络请求的Url地址
//                    .addConverterFactory(GsonConverterFactory.create(gson)) // 设置数据解析器
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))// jackson 数据解析
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                    .build();
        }
        return retrofit;
    }

    public static <T> T GetService(Class<T> clazz) {
        return getInstance().create(clazz);
    }

    /*取得各种API*/

    /*用户相关*/
    public static UserApi getUserApi() {
        return GetService(UserApi.class);
    }
//
//    /*质检模板*/
//    public static QcTemplateApi getQcTemplateApi() {
//        return GetService(QcTemplateApi.class);
//    }
//
//    /*收货单相关*/
//    public static ReceiveOrderApi getReceiveOrderApi() {
//        return GetService(ReceiveOrderApi.class);
//    }
//
//    /*质检相关的API*/
//    public static QcApi getQcApi() {
//        return GetService(QcApi.class);
//    }
//
//    /*我的任务相关API*/
//    public static QcTaskApi getQcTaskFormApi() {
//        return GetService(QcTaskApi.class);
//    }
//
//    /*工单相关的API*/
//    public static WorkOrderApi getWorkOrderApi() {
//        return GetService(WorkOrderApi.class);
//    }
//
//    /* 质检报告相关API*/
//    public static QcReportApi getQcReportApi() {
//        return GetService(QcReportApi.class);
//    }
//
//    /*得到发货单相关API*/
//    public static InvoiceApi getInvoiceApi() {
//        return GetService(InvoiceApi.class);
//    }

}
