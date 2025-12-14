package com.huytd.ansinhso.dto.response;

import com.huytd.ansinhso.constant.Status;
import com.huytd.ansinhso.entity.Location;
import com.huytd.ansinhso.entity.RegularOpeningHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristPlaceResponse {
    private String id;
    private String displayName;
    private String formattedAddress;
    private Location location;
    private String category;
    private String internationalPhoneNumber;
    private String websiteUri;
    private String editorialSummary;
    private String thumbnail;
    private Status status;
    private RegularOpeningHours regularOpeningHours;
    private List<String> photos;
}
