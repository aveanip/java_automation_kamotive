package models.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ActionResponse {
        private Boolean success;
        private String errorCode;
        private String errorMessage;
        private Metadata metadata;
}
