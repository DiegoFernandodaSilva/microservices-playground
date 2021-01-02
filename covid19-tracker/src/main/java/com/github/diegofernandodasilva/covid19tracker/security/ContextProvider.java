package com.github.diegofernandodasilva.covid19tracker.security;

public interface ContextProvider<T> {

    T get();

}
