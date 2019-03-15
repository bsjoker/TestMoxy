package ru.bs.joker.testmoxy.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.bs.joker.testmoxy.model.Model;
import ru.bs.joker.testmoxy.model.ModelImpl;

import ru.bs.joker.testmoxy.model.data.Datum;
import ru.bs.joker.testmoxy.model.data.FillData;
import ru.bs.joker.testmoxy.view.MainView;

@InjectViewState
public class MainPresenterImpl extends MvpPresenter<MainView> implements MainPresenter {
    private Model model = new ModelImpl();
    private List<Datum> listForView = new ArrayList<>();

    @Override
    public void onStartButtonClick() {

        model.getDataList()
                .subscribe(new Observer<FillData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FillData allData) {
                        if (allData != null){
                            listForView.clear();
                            for (String item: allData.getView()){
                                for (Datum itemToAdd:allData.getData()){
                                    if(itemToAdd.getName().contains(item)){
                                        listForView.add(itemToAdd);
                                    }
                                }
                            }
                            getViewState().showData(listForView);
                        } else {
                            getViewState().showEmptyList();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClickPosition(int position) {
        getViewState().showClickedItem(listForView.get(position).getName());
    }
}
