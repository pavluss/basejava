package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getIndex(String uuid) {
        return uuid;
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
        return storage.containsKey((String) index);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
