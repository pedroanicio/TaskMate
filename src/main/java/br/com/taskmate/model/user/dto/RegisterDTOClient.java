package br.com.taskmate.model.user.dto;

import br.com.taskmate.model.user.enums.UserRole;

public record RegisterDTOClient(String username, String firstName, String lastName, String email, String password, Integer age, String phone, UserRole role) {
}
