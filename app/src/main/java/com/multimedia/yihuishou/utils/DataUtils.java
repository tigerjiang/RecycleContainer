package com.multimedia.yihuishou.utils;

import com.multimedia.yihuishou.entity.ResidentEntity;
import com.multimedia.yihuishou.log.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    private static final String TAG = "DataUtils";

    public static Map<String, ResidentEntity> sCacheResidentMap = new HashMap<>();


    public static void storeResident(String cardId, ResidentEntity residentEntity) {
        if (!sCacheResidentMap.containsKey(cardId)) {
            sCacheResidentMap.put(cardId, residentEntity);
        } else {
            final ResidentEntity entity = sCacheResidentMap.get(cardId);
            if (!entity.equals(residentEntity)) {
                sCacheResidentMap.put(cardId, residentEntity);
            } else {
                LogUtils.d(TAG, " storeResident : resident has exist !!" + residentEntity.toString());
            }
        }
    }

    public static ResidentEntity getCacheResident(String cardId) {
        return sCacheResidentMap.get(cardId);
    }
}
