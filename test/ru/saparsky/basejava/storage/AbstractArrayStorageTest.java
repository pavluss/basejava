package ru.saparsky.basejava.storage;

import org.junit.jupiter.api.Test;
import ru.saparsky.basejava.exception.StorageException;
import ru.saparsky.basejava.model.Resume;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveStorageFull() {
        //given
        //when
        assertDoesNotThrow(() -> {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("name" + i));
            }
        }, "Storage is full ahead of time");
        //then
        assertThrows(StorageException.class, () -> storage.save(new Resume("Storage full")));
    }
}
