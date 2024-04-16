package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void size() {
        //given
        Resume resume1 = new Resume("uuid1");
        Resume resume2 = new Resume("uuid2");
        //when
        storage.save(resume1);
        storage.save(resume2);
        //then
        assertEquals(2, storage.size());
    }

    @Test
    void get() {
        //given
        Resume resumeToSave = new Resume("uuid1");
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
        Resume resumeToSave1 = new Resume("uuid1");
        Resume resumeToSave2 = new Resume("uuid2");
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
        Resume resumeToSave = new Resume("uuid1");
        //when
        storage.save(resumeToSave);
        //then
        assertEquals(1, storage.size());
        assertEquals(resumeToSave, storage.get(resumeToSave.getUuid()));
    }

    @Test
    void saveExist() {
        //given
        Resume resumeToSave = new Resume("uuid1");
        storage.save(resumeToSave);
        //when
        assertThrows(ExistStorageException.class, () -> storage.save(resumeToSave));
        //then
        assertEquals(1, storage.size());
        assertEquals(resumeToSave, storage.get(resumeToSave.getUuid()));
    }

    @Test
    void saveStorageFull() {
        //given
        //when
        assertDoesNotThrow(() -> {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        }, "Storage is full ahead of time");
        //then
        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void delete() {
        //given
        Resume resumeToSave = new Resume("uuid1");
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
        Resume resumeToSave = new Resume("uuid1");
        storage.save(resumeToSave);
        //when
        Resume resumeToUpdate = new Resume("uuid1");
        storage.update(resumeToUpdate);
        //then
        assertNotSame(resumeToSave, resumeToUpdate);
    }

    @Test
    void updateNotExist() {
        //given
        //when
        Resume resumeToUpdate = new Resume("uuid1");
        //then
        assertThrows(NotExistStorageException.class, () -> storage.update(resumeToUpdate));
    }

    @Test
    void getAll() {
        //given
        Resume resumeToSave1 = new Resume("uuid1");
        Resume resumeToSave2 = new Resume("uuid2");
        Resume resumeToSave3 = new Resume("uuid3");
        storage.save(resumeToSave1);
        storage.save(resumeToSave2);
        storage.save(resumeToSave3);
        //when
        Resume[] obtainedResumes = storage.getAll();
        //then
        assertEquals(3, obtainedResumes.length);
        assertEquals(resumeToSave1, obtainedResumes[0]);
        assertEquals(resumeToSave2, obtainedResumes[1]);
        assertEquals(resumeToSave3, obtainedResumes[2]);
    }



}