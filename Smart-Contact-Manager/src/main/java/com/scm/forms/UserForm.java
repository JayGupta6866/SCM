package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Name is a required field")
    @Size(min = 3, message = "Minimimum 3 chars required")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "Minimum 8 chars required")
    private String password;

    @Size(min = 6, max = 12, message = "Invalid Phone Number")
    private String phoneNumber;

    private String about;

}
