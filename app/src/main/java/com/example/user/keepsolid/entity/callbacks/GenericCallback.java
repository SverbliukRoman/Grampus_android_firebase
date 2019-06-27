package com.example.user.keepsolid.entity.callbacks;

/**
 * Created by Anton Popov on 10-Aug-18.
 */

public interface GenericCallback<R, E> extends SimpleGenericCallback<R> {
    void onError(E error);
}
