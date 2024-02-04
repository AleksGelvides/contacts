package org.example.contacts.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
