package ru.bs.joker.testmoxy.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.bs.joker.testmoxy.R;
import ru.bs.joker.testmoxy.model.data.Datum;
import ru.bs.joker.testmoxy.presenter.MainPresenterImpl;
import ru.bs.joker.testmoxy.view.adapters.RecyclerViewAdapter;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.button)
    Button searchButton;

    private Unbinder mUnbinder;

    private RecyclerViewAdapter adapter;

    @InjectPresenter
    MainPresenterImpl mMainPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getId();
                Log.d("MainActivity", "Pos: " + v.getId());
            }
        });

        searchButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mMainPresenterImpl.onStartButtonClick();
            }
        });
    }

    @Override
    public void showData(List<Datum> list) {
        adapter.setList(list, getApplicationContext(), new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void itemViewOnClick(View v, int position) {
                mMainPresenterImpl.onClickPosition(position);
            }
        });
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        makeToast(getString(R.string.empty_list));
    }

    @Override
    public void showClickedItem(String name) {
        makeToast(name);
    }

    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
