package com.telematika.info;

import com.telematika.info.Adapter.GetData;
import com.telematika.info.Adapter.GetDataAlat;

public interface SelectListener {
    void onItemClicked(GetData getData);
    void onItemClicked(GetDataAlat getDataAlat);

}
