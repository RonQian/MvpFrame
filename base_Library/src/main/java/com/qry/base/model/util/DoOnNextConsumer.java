package com.qry.base.model.util;


import com.qry.base.model.dto.ApiResponseDto;

import io.reactivex.functions.Consumer;


public class DoOnNextConsumer implements Consumer<Object> {
    @SuppressWarnings("unchecked")
    @Override
    public void accept(Object response) throws Exception {

        ApiResponseDto<Object> result = (ApiResponseDto<Object>) response;
        // 对结果进行预处理，比如对处理结果进行处理
        if (result.getState() != 1)
            throw new ApiException("server error:" + result.getMsg());
        if (result.getData() == null)
            throw new ApiException("未查询到值");
    }
}
