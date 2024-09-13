package br.com.taskmate.model.user.enums;

// Enum that represents the user role
public enum UserRole {

    CLIENT("client"),
    WORKER("worker"),
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
