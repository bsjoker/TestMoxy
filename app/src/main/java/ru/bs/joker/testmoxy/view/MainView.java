package ru.bs.joker.testmoxy.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.bs.joker.testmoxy.model.data.Datum;

public interface MainView extends MvpView {
    void showData(List<Datum> list);

    void showError(String error);

    void showEmptyList();

    void showClickedItem(String name);
}
