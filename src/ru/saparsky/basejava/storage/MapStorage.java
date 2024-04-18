package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getIndex(String uuid) {
        return storage.get(uuid) != null ? uuid : null;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.replace((String) index, r);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((String) index);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove((String) index);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
