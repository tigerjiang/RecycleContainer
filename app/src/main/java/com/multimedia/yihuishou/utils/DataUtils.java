package com.multimedia.yihuishou.utils;

import com.multimedia.yihuishou.entity.ResidentEntity;
import com.multimedia.yihuishou.entity.UserEntity;
import com.multimedia.yihuishou.log.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    private static final String TAG = "DataUtils";

    public static Map<String, ResidentEntity> sCacheResidentMap = new HashMap<>();

    public static Map<String, UserEntity> sCacheUser = new HashMap<>();


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


    public static void storeUser(String userName, UserEntity userEntity) {
        if (!sCacheUser.containsKey(userName)) {
            sCacheUser.put(userName, userEntity);
        } else {
            final UserEntity entity = sCacheUser.get(userName);
            if (!entity.equals(userEntity)) {
                sCacheUser.put(userName, userEntity);
            } else {
                LogUtils.d(TAG, " storeResident : resident has exist !!" + userEntity.toString());
            }
        }
    }

    public static UserEntity getCacheUser(String userName) {
        return sCacheUser.get(userName);
    }
}
