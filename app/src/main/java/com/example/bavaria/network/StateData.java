package com.example.bavaria.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StateData<T> {
    @NonNull
    private DataStatus status;

    @Nullable
    private T data;

    @Nullable
    private Throwable error;

    @Nullable
    private String Problem;

//    @Nullable
//    public String getProblem() {
//        this.data = null;
//        this.error = null;
//        return Problem;
//    }
//
//    public StateData<String> setProblem(@Nullable String problem) {
//        Problem = problem;
//        return null;
//    }

    public StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
        this.Problem = null;
    }

    public StateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        this.Problem = null;
        return this;
    }

    public StateData<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        this.Problem = null;
        return this;
    }

    public StateData<T> error(@NonNull Throwable error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public StateData<T> Problem(String error) {
        this.status = DataStatus.Problem;
        this.data = null;
        this.error = null;
        this.Problem = error;
        return this;
    }

    public StateData<T> complete() {
        this.status = DataStatus.COMPLETE;
        return this;
    }

    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE,Problem
    }
}