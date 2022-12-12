package com.mike.todolist.context;

public class DefaultSecurityContext implements SecurityContext {
    private UserRepresentation userRepresentation;


    public DefaultSecurityContext(UserRepresentation userRepresentation) {
        this.userRepresentation = userRepresentation;
    }

    public UserRepresentation getUserRepresentation() {
        return userRepresentation;
    }

    public void setUserRepresentation(UserRepresentation userRepresentation) {
        this.userRepresentation = userRepresentation;
    }
}
