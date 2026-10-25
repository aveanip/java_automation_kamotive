package models.Spaces;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.OrganizationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SpacesRequestBodyModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("iconName")
    private String iconName;

    @JsonProperty("organization")
    private OrganizationModel organization;

    @JsonProperty("status")
    private SpaceStatusModel status;
}
