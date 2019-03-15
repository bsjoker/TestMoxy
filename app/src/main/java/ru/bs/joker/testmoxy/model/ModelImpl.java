package ru.bs.joker.testmoxy.model;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observable;
import ru.bs.joker.testmoxy.model.api.ApiInterface;
import ru.bs.joker.testmoxy.model.api.ApiModule;
import ru.bs.joker.testmoxy.model.data.FillData;

public class ModelImpl implements Model {
    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<FillData> getDataList() {
        return apiInterface.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
