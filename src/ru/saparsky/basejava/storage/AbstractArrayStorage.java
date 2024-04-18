package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.exception.StorageException;
import ru.saparsky.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Object getIndex(String uuid);
    protected abstract void insertElement(Resume r, int index);
    protected abstract void deleteElement(int index);

    public int size() {
        return size;
    }

    public void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", r.getUuid());
        } else {
            insertElement(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doDelete(Object index) {
        deleteElement((Integer) index);
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
