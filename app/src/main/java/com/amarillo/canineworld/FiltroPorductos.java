package com.amarillo.canineworld;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.Locale;

public class FiltroPorductos extends Filter {

    private AllProducts allProducts;
    private ArrayList<ModelProductos> filterList;

    public FiltroPorductos(AllProducts allProducts, ArrayList<ModelProductos> filterList) {
        this.allProducts = allProducts;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelProductos> filterdModels = new ArrayList<>();
            for(int i=0;i<filterdModels.size();i++){
                if(filterList.get(i).getProductTitle().toUpperCase().contains(constraint) ||
                        filterList.get(i).getProductCategoria().toUpperCase().contains(constraint)
                ){
                    filterdModels.add(filterList.get(i));
                }
            }
            results.count = filterdModels.size();
            results.values = filterdModels;
        }else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        allProducts.productList = (ArrayList<ModelProductos>)  results.values;

        allProducts.notifyDataSetChanged();
    }
}
