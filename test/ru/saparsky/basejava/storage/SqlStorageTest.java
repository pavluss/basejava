package ru.saparsky.basejava.storage;

import ru.saparsky.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest{

    protected SqlStorageTest() {
        super(Config.get().getSqlStorage());
    }
}
