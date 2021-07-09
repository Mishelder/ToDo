package com.miaskor.util;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class ControllersURIKeys {
    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String CSS_LOADER = "/css";
    public static final String SAVE_TASK = "/saveTask";
    public static final String LOGOUT = "/logout";
    public static final String FLIP_RIGHT = "/flip/right";
    public static final String FLIP_LEFT = "/flip/left";
    public static final String TODO = "/todo";
    public static final String FETCH = "/fetch";
    public static final String SAVE = "/save";
    public static final String DELETE = "/delete";
    public static final String UPDATE_DONE = "/updateDone";
    public static final String UPDATE = "/update";

    public static Set<String> getAllFeasibleURL() {
        return Set.of(LOGIN, REGISTRATION, CSS_LOADER, SAVE_TASK,
                LOGOUT, FLIP_RIGHT, FLIP_LEFT, TODO, FETCH,SAVE,
                DELETE,UPDATE_DONE,UPDATE);
    }

    public static Set<String> getAllPublicURL() {
        return Set.of(LOGIN, REGISTRATION, CSS_LOADER);
    }
}
