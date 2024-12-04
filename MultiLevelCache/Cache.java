package MultiLevelCache;

public interface Cache {
    public String get(String key);
    public void put(String key, String value);
    public int getLevel();
    public int getCapacity();
    public int getUsage();
    public int getReadTime();
    public int getWriteTime();
}
