package models.Spaces;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpacesResponseModel {

    @JsonProperty("id")
    private String id;

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

    @JsonProperty("createDate")
    private String createDate;

    @JsonProperty("updateDate")
    private String updateDate;
}
