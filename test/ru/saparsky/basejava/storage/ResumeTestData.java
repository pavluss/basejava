package ru.saparsky.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.saparsky.basejava.model.*;

import java.time.LocalDate;

public class ResumeTestData {

    @Test
    void showResume() {
        Resume r = new Resume("Name 1");
        r.setContact(ContactType.PHONE, "+79001234567");
        r.setContact(ContactType.SKYPE, "skype");
        r.setContact(ContactType.EMAIL, "mail@mail.com");
        r.setContact(ContactType.LINKEDIN, "linkedin");
        r.setSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        r.setSection(SectionType.PERSONAL, new TextSection("Personal 1"));
        r.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement 1", "Achievement 2", "Achievement 3"));
        r.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Spring"));
        r.setSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization(
                        "Organization 1",
                        "https://organization1.com",
                        "Title organization 1",
                        "Description organization 1",
                        LocalDate.of(2023, 6, 10),
                        LocalDate.of(2023, 10, 10))
        ));
        r.setSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization(
                        "Institute",
                        null,
                        "Title Institute",
                        null,
                        LocalDate.of(2018, 6, 10),
                        LocalDate.of(2021, 10, 10))
        ));

        System.out.println(r.getFullName());
        for (ContactType contactType : ContactType.values()) {
            System.out.println(contactType.getTitle() + ": " + r.getContact(contactType));
        }
        for (SectionType sectionType : SectionType.values()) {
            System.out.println(sectionType.getTitle() + ": " + r.getSection(sectionType));
        }

    }
}
