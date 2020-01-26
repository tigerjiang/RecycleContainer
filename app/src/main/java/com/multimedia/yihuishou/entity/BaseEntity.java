package com.multimedia.yihuishou.entity;

import java.io.Serializable;

public abstract class BaseEntity  implements Serializable {


    public abstract String getTitle();


    public abstract String getUrl();


    public abstract String  getSubtitle();


    public abstract String getDesc();

    public abstract boolean isChecked();
}
