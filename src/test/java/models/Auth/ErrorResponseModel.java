package models.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public class ErrorResponseModel {

        @JsonProperty("status")
        private Integer status;

        @JsonProperty("message")
        private String message;
    }

