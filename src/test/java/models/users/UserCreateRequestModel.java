package models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor // Пустой бланк для Jackson
@AllArgsConstructor // Полный пакет для Builder'а
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // Не отправляем пустые (null) поля
public class UserCreateRequestModel {

    private String login;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String organizationId;
}