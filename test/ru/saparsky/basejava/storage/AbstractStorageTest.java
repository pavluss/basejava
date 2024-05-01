package ru.saparsky.basejava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.saparsky.basejava.exception.ExistStorageException;
import ru.saparsky.basejava.exception.NotExistStorageException;
import ru.saparsky.basejava.model.Resume;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void size() {
        //given
        Resume resume1 = ResumeTestData.getResume("uuid1", "name1");
        Resume resume2 = ResumeTestData.getResume("uuid2", "name2");
        //when
        storage.save(resume1);
        storage.save(resume2);
        //then
        assertEquals(2, storage.size());
    }

    @Test
    void get() {
        //given
        Resume resumeToSave = ResumeTestData.getResume("uuid1", "name1");
        storage.save(resumeToSave);
        //when
        Resume obtainedResume = storage.get(resumeToSave.getUuid());
        //then
        assertNotNull(obtainedResume);
        assertEquals(resumeToSave, obtainedResume);
    }

    @Test
    public void getNotExist() {
        //given
        //when
        //then
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void clear() {
        //given
        Resume resumeToSave1 = ResumeTestData.getResume("uuid1", "name1");
        Resume resumeToSave2 = ResumeTestData.getResume("uuid2", "name2");
        storage.save(resumeToSave1);
        storage.save(resumeToSave2);
        //when
        storage.clear();
        //then
        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        //given
        Resume resumeToSave = ResumeTestData.getResume("uuid1", "name1");
        //when
        storage.save(resumeToSave);
        //then
        assertEquals(1, storage.size());
        assertEquals(resumeToSave, storage.get(resumeToSave.getUuid()));
    }

    @Test
    void saveExist() {
        //given
        Resume resumeToSave = ResumeTestData.getResume("uuid1", "name1");
        storage.save(resumeToSave);
        //when
        assertThrows(ExistStorageException.class, () -> storage.save(resumeToSave));
        //then
        assertEquals(1, storage.size());
        assertEquals(resumeToSave, storage.get(resumeToSave.getUuid()));
    }

    @Test
    void delete() {
        //given
        Resume resumeToSave = ResumeTestData.getResume("uuid1", "name1");
        storage.save(resumeToSave);
        //when
        storage.delete(resumeToSave.getUuid());
        //then
        assertEquals(0, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.get(resumeToSave.getUuid()));
    }

    @Test
    void deleteNotExist() {
        //given
        //when
        //then
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    void update() {
        //given
        Resume resumeToSave = ResumeTestData.getResume("uuid1", "name1");
        storage.save(resumeToSave);
        //when
        Resume resumeToUpdate = ResumeTestData.getResume("uuid1", "new name");
        storage.update(resumeToUpdate);
        //then
        assertNotSame(resumeToSave, resumeToUpdate);
        assertEquals(storage.get(resumeToSave.getUuid()).getFullName(), resumeToUpdate.getFullName());
    }

    @Test
    void updateNotExist() {
        //given
        //when
        Resume resumeToUpdate = ResumeTestData.getResume("uuid1", "name1");
        //then
        assertThrows(NotExistStorageException.class, () -> storage.update(resumeToUpdate));
    }

    @Test
    void getAll() {
        //given
        Resume resumeToSave1 = ResumeTestData.getResume("uuid1", "name1");
        Resume resumeToSave2 = ResumeTestData.getResume("uuid2", "name2");
        Resume resumeToSave3 = ResumeTestData.getResume("uuid3", "name3");
        storage.save(resumeToSave1);
        storage.save(resumeToSave2);
        storage.save(resumeToSave3);
        //when
        List<Resume> obtainedResumes = storage.getAllSorted();
        //then
        assertEquals(3, obtainedResumes.size());
        assertEquals(resumeToSave1, obtainedResumes.get(0));
        assertEquals(resumeToSave2, obtainedResumes.get(1));
        assertEquals(resumeToSave3, obtainedResumes.get(2));
    }


}