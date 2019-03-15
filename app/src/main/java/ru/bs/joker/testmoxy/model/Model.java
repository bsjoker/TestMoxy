package ru.bs.joker.testmoxy.model;

import io.reactivex.Observable;
import ru.bs.joker.testmoxy.model.data.FillData;

public interface Model {
    Observable<FillData> getDataList();
}
