package com.qry.base.model;


import com.qry.base.model.dto.ApiResponseDto;
import com.qry.base.model.util.DoOnNextConsumer;
import com.qry.base.model.util.SimpleDealApiResponseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*暂时没有用*/
public abstract class BaseService {


    /**
     * @描述： 自定义处理定阅
     * @返回：
     * @作者：phj
     * @日期： 2018-08-01 16:58:34
     */
    protected <T> Observable<T> dealResult(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new DoOnNextConsumer());
    }

    //简单处理结果
    protected <T> void simpleDealResult(Observable<ApiResponseDto<T>> observable, ICommServiceCallBack callBack) {
        dealResult(observable)
                .subscribe(new SimpleDealApiResponseObserver<T>(callBack));
    }


}
