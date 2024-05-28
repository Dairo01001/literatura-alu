package com.dairodev.literatura.services;

public interface IParseData {
    <T> T getData(String json, Class<T> c);
}
