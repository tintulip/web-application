package com.tintulip.webapplication.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class LicenseTypeConverterTest {

    @Test
    public void shouldReturnNullWhenLicenseTypeIsNull(){
        LicenseType licenseType = null;
        LicenseTypeConverter converter = new LicenseTypeConverter();
        String displayLicense = converter.convertToDatabaseColumn(licenseType);
        assertNull(displayLicense);

    }

    @Test
    public void shouldReturnArtisticWhenLicenseTypeIsArtistic(){
        LicenseType licenseType = LicenseType.ARTISTIC;
        LicenseTypeConverter converter = new LicenseTypeConverter();
        String displayLicense = converter.convertToDatabaseColumn(licenseType);
        assertEquals(displayLicense,"Artistic");

    }

    @Test
    public void shouldReturnNullWhenDataIsNull(){
        String dbValue = null;
        LicenseTypeConverter converter = new LicenseTypeConverter();
        LicenseType licenseType = converter.convertToEntityAttribute(dbValue);
        assertNull(licenseType);
    }


    @Test
    public void shouldReturnValueOfDataWhenDataIsCreative(){
        String dbValue = "Creative";
        LicenseTypeConverter converter = new LicenseTypeConverter();
        LicenseType licenseType = converter.convertToEntityAttribute(dbValue);
        assertEquals(licenseType,LicenseType.CREATIVE);
    }

}
