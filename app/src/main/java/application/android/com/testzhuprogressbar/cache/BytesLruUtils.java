package application.android.com.testzhuprogressbar.cache;

import android.util.LruCache;

/**
 * Created by jinliang on 17-10-16.
 */

public class BytesLruUtils {

    private static final String TAG = "TextDownload";
    private LruCache<String, byte[]> lruCache;

    public BytesLruUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 16);
        lruCache = new LruCache<String,byte[]>(cacheSize){
            @Override
            protected int sizeOf(String key, byte[] value) {
                return super.sizeOf(key, value);
            }
        };
    }

    public void addBytesToMemory(String key, byte[] bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            lruCache.put(key, bitmap);
        }
    }

    // 从缓存中得到Bitmap对象
    public byte[] getBitmapFromMemCache(String key) {
        return lruCache.get(key);
    }

    // 从缓存中删除指定的Bitmap
    public void removeBitmapFromMemory(String key) {
        lruCache.remove(key);
    }


}
