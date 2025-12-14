package com.huytd.ansinhso.entity;

import com.huytd.ansinhso.constant.Status;
import com.huytd.ansinhso.entity.converter.LocationConverter;
import com.huytd.ansinhso.entity.converter.RegularOpeningHoursConverter;
import com.huytd.ansinhso.entity.converter.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "tourist_places")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TouristPlace extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "formatted_address")
    private String formattedAddress;

    @Column(name = "location")
    @Convert(converter = LocationConverter.class)
    private Location location;

    @Column(name = "category")
    private String category;

    @Column(name = "international_phone_number")
    private String internationalPhoneNumber;

    @Column(name = "website_uri")
    private String websiteUri;

    @Column(name = "editorial_summary")
    private String editorialSummary;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private Status status = Status.DRAFT;

    @Column(name = "regular_opening_hours")
    @Convert(converter = RegularOpeningHoursConverter.class)
    private RegularOpeningHours regularOpeningHours;

    @Column(name = "photos")
    @Convert(converter = StringListConverter.class)
    private List<String> photos;
}
