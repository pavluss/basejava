package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.exception.ExistStorageException;
import ru.saparsky.basejava.exception.NotExistStorageException;
import ru.saparsky.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);

    protected abstract void doUpdate(Resume r, Object index);

    protected abstract void doSave(Resume r, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract boolean isExist(Object index);

    protected abstract List<Resume> doCopyAll();


    @Override
    public void update(Resume r) {
        Object index = getExistIndex(r.getUuid());
        doUpdate(r, index);
    }

    @Override
    public void save(Resume r) {
        Object index = getNotExistIndex(r.getUuid());
        doSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getExistIndex(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getExistIndex(uuid);
        doDelete(index);
    }

    private Object getExistIndex(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistIndex(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    public List<Resume> getAllSorted() {
        List<Resume> result = doCopyAll();
        result.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return result;
    }

}
