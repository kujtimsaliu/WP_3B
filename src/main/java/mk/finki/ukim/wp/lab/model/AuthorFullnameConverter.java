package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {

    @Override
    public String convertToDatabaseColumn(AuthorFullname attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getName() + " " + attribute.getSurname();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        String[] parts = dbData.split(" ");
        return new AuthorFullname(parts[0], parts[1]);
    }
}