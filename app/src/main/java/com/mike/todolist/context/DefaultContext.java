package com.mike.todolist.context;

import lombok.Getter;

@Getter
public class DefaultContext implements ApplicationContext {
    private SecurityContext securityContext;

    public DefaultContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }
}
