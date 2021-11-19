package com.amarillo.canineworld;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllProducts extends RecyclerView.Adapter<AllProducts.ProductSeller> implements Filterable {

   private Context context;
   public ArrayList<ModelProductos> productList, filterList;
   private FiltroPorductos filter;

   public AllProducts(Context context, ArrayList<ModelProductos> productList) {
      this.context = context;
      this.productList = productList;
   }

   @NonNull
   @Override
   public ProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view  = LayoutInflater.from(context).inflate(R.layout.activity_all_products,parent,false);
      return new ProductSeller(view) ;
   }

   @Override
   public void onBindViewHolder(@NonNull ProductSeller holder, int position) {
      ModelProductos modelProductos = productList.get(position);
      String id = modelProductos.getProductId();
      final String uid = modelProductos.getUid();
      String discountAvailable = modelProductos.getDiscountAvailable();
      String discountPrice = modelProductos.getProductDescPrecio();
      String descuentoPorc = modelProductos.getProductDescPor();
      String category = modelProductos.getProductCategoria();
      String descripcion = modelProductos.getProductDescripcion();
      String precio = modelProductos.getProductValor();
      String titulo = modelProductos.getProductTitle();
      String timestap = modelProductos.getTimestamp();

      holder.titleNote.setText(titulo);
      holder.valorNote.setText(precio);
      holder.descuentoNote.setText(descuentoPorc);

      if(discountAvailable.equals("true")){
         holder.valorNote.setVisibility(View.VISIBLE);
         holder.descuentoNote.setVisibility(View.VISIBLE);
      }else{
         holder.valorNote.setVisibility(View.GONE);
         holder.descuentoNote.setVisibility(View.GONE);
      }

   }

   @Override
   public int getItemCount() {
      return productList.size();
   }

   @Override
   public Filter getFilter() {
      if(filter == null){
         filter = new FiltroPorductos(this,filterList);
      }
      return filter;
   }

   class ProductSeller extends RecyclerView.ViewHolder{

      private ImageView productoIcon;
      private TextView descuentoNote, titleNote, valorNote;

      public ProductSeller(@NonNull View itemView) {
         super(itemView);

         productoIcon = itemView.findViewById(R.id.productIcon);
         descuentoNote = itemView.findViewById(R.id.descuentoNote);
         titleNote = itemView.findViewById(R.id.titleNote);
         valorNote = itemView.findViewById(R.id.valorNote);

      }
   }
}