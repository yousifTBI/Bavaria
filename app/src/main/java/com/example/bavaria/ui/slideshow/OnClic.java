package com.example.bavaria.ui.slideshow;

import android.view.View;

import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

public interface OnClic {
    void getPos(int postion);
    void updateQuantity(View v, int postionList, ItemsModel item, int AdabterPos);
}
