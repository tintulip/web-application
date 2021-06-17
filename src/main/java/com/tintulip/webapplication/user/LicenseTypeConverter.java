package com.tintulip.webapplication.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LicenseTypeConverter implements AttributeConverter <LicenseType, String> {


    @Override
    public String convertToDatabaseColumn(LicenseType licenseType) {
        if (licenseType == null) {
            return null;
        }
        return licenseType.getDisplayLicense();
    }

    @Override
    public LicenseType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return LicenseType.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
