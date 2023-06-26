package org.example.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private String username;
  private String phone;
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate dateOfBirth;
  private String address;
}
