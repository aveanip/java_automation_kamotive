package models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateResponseModel {
    private Boolean success;
    private String errorCode;
    private String errorMessage;
    private Metadata metadata;
}
