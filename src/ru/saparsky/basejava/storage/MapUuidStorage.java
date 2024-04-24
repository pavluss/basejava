package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, String index) {
        storage.replace(index, r);
    }

    @Override
    protected void doSave(Resume r, String index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(String index) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(String index) {
        storage.remove(index);
    }

    @Override
    protected boolean isExist(String index) {
        return storage.containsKey(index);
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
