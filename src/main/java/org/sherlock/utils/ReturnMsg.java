package org.sherlock.utils;

import net.bytebuddy.asm.Advice;

public class ReturnMsg<T> {
    private T data;

    public ReturnMsg() {}

    public ReturnMsg(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
