package com.example.sefe.myapplication.Binders;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.adapters.DetailsAdapater;
import com.example.sefe.myapplication.interfaces.DataboundAdapter;
import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.model.DetailsModel;
import com.example.sefe.myapplication.viewmodels.InspectionViewModel;

import io.reactivex.functions.Consumer;

/**
 * Created by sefe on 2/28/2017.
 */

public class CustomBinders {

    @BindingAdapter("bind:listItems")
    public static void bindItemsToList(ListView view, ObservableArrayList<String> items)
    {
        view.setAdapter(new ArrayAdapter<String>(view.getContext(), R.layout.item_string,R.id.txtItem,items));
    }

    @BindingAdapter({"bind:list", "bind:itemProvider"})
    public static void bindIntemsFromRepo(ListView view, ObservableArrayList<DetailsModel> items, IDetailsRepository repo)
    {
        view.setAdapter(new DataboundAdapter<DetailsModel>(view.getContext(),items,R.layout.details_item_layout));
    }

    @BindingAdapter("bind:onEndReached")
    public static void addOnScrollEndListener(final ListView view, final InspectionViewModel viewmodel)
    {
        view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int lastOffset = 2;
                boolean isShowingLastIndexedElement = view.getLastVisiblePosition() == view.getAdapter().getCount() -lastOffset;
                if (isShowingLastIndexedElement && oldScrollY < scrollY) {
                        viewmodel.IsEndShowing.set(true);
                }
                else{
                    viewmodel.IsEndShowing.set(false);
                }
            }
        });
    }


    @BindingAdapter("bind:onSearch")
    public static void onSearch(SearchView view, final InspectionViewModel vm)
    {
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                vm.SearchText(newText);
                return true;
            }
        });
        //view.setOnItemClickListener(click);
    }

    @BindingAdapter("bind:listItemClick")
    public static void bindListItemClick(ListView view, AdapterView.OnItemClickListener click)
    {
        view.setOnItemClickListener(click);
    }
}

