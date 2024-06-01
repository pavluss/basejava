package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.model.ContactType;
import ru.saparsky.basejava.model.Resume;

public class ResumeTestData {

    public static Resume getResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        r.addContact(ContactType.PHONE, "+79001234567");
        r.addContact(ContactType.SKYPE, "skype");
        r.addContact(ContactType.EMAIL, "mail@mail.com");
        r.addContact(ContactType.LINKEDIN, "linkedin");
 /*       r.addSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        r.addSection(SectionType.PERSONAL, new TextSection("Personal 1"));
        r.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement 1", "Achievement 2", "Achievement 3"));
        r.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Spring"));
        r.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization(
                        "Organization 1",
                        "https://organization1.com",
                        new Organization.Position(
                                "Title organization 1",
                                "Description organization 1",
                                2023, Month.SEPTEMBER),
                        new Organization.Position(
                                "Title organization 1",
                                "Description organization 1",
                                2022, Month.APRIL,
                                2023, Month.MAY)
                )));
        r.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization(
                        "Institute",
                        null,
                        new Organization.Position(
                                "Title Institute",
                                null,
                                2021, Month.SEPTEMBER,
                                2023, Month.JANUARY
                        ))));
*/
        return r;
    }

}
