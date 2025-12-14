package com.huytd.ansinhso.dto.request;

import com.huytd.ansinhso.constant.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTouristPlaceRequest {

    @NotBlank(message = "Display name is required")
    private String displayName;

    private String formattedAddress;

    @Valid
    @NotNull(message = "Location is required")
    private LocationDto location;

    @NotBlank(message = "Category is required")
    private String category;

    private String internationalPhoneNumber;

    private String websiteUri;

    private String editorialSummary;

    private String thumbnail;

    private Status status;

    @Valid
    private RegularOpeningHoursDto regularOpeningHours;

    private List<String> photos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationDto {
        @NotNull(message = "Latitude is required")
        @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
        private Double latitude;

        @NotNull(message = "Longitude is required")
        @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
        private Double longitude;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegularOpeningHoursDto {
        @Valid
        @NotEmpty(message = "At least one opening hour is required")
        private List<OpeningHourDto> weekdayDescriptions;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OpeningHourDto {
        @NotBlank(message = "Day is required")
        private String day;

        @Min(value = 0, message = "Open hour must be between 0 and 23")
        @Max(value = 23, message = "Open hour must be between 0 and 23")
        private Integer openHour;

        @Min(value = 0, message = "Open minute must be between 0 and 59")
        @Max(value = 59, message = "Open minute must be between 0 and 59")
        private Integer openMinute;

        @Min(value = 0, message = "Close hour must be between 0 and 23")
        @Max(value = 23, message = "Close hour must be between 0 and 23")
        private Integer closeHour;

        @Min(value = 0, message = "Close minute must be between 0 and 59")
        @Max(value = 59, message = "Close minute must be between 0 and 59")
        private Integer closeMinute;

        @NotNull(message = "isClose field is required")
        private Boolean isClose;
    }
}
