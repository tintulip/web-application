package com.tintulip.webapplication.user;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class TestUser {

    private String email;

    @Enumerated(EnumType.STRING)
    @Convert(converter = LicenseTypeConverter.class)
    private LicenseType licenseType;

    private String reason;
    @Id @Type(type = "pg-uuid")
    private UUID id;
    private Timestamp createdAt;

}
