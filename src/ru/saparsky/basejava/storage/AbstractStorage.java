package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.exception.ExistStorageException;
import ru.saparsky.basejava.exception.NotExistStorageException;
import ru.saparsky.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {

    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract T getIndex(String uuid);

    protected abstract void doUpdate(Resume r, T index);

    protected abstract void doSave(Resume r, T index);

    protected abstract Resume doGet(T index);

    protected abstract void doDelete(T index);

    protected abstract boolean isExist(T index);

    protected abstract List<Resume> doCopyAll();


    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        T index = getExistIndex(r.getUuid());
        doUpdate(r, index);
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        T index = getNotExistIndex(r.getUuid());
        doSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        T index = getExistIndex(uuid);
        return doGet(index);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        T index = getExistIndex(uuid);
        doDelete(index);
    }

    private T getExistIndex(String uuid) {
        T index = getIndex(uuid);
        if (!isExist(index)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private T getNotExistIndex(String uuid) {
        T index = getIndex(uuid);
        if (isExist(index)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> result = doCopyAll();
        result.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return result;
    }

}
