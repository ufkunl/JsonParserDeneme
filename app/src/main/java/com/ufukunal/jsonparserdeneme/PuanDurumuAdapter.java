package com.ufukunal.jsonparserdeneme;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Ufuk UNAL on 26.04.2017.
 */

public class PuanDurumuAdapter extends RecyclerView.Adapter<PuanDurumuAdapter.PuanDurumuHolder>{

    private Context mContext;
    private List<PuanDurumu> mList;

    public PuanDurumuAdapter(Context context, List<PuanDurumu> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public PuanDurumuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View v = layoutInflater.inflate(R.layout.puan_durumu_row,null);
        return new PuanDurumuHolder(v);
    }

    @Override
    public void onBindViewHolder(PuanDurumuHolder holder, int position) {
        PuanDurumu puanDurumu = mList.get(position);
        holder.bindHolder(puanDurumu);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    boolean renk = false;

    public class PuanDurumuHolder extends RecyclerView.ViewHolder{

        TextView takimAdi,o,g,b,m,a,y,p,Av,E;

        public PuanDurumuHolder(View itemView) {
            super(itemView);

            takimAdi = (TextView)itemView.findViewById(R.id.takimAdi);
            o = (TextView)itemView.findViewById(R.id.oynananMac);
            g = (TextView)itemView.findViewById(R.id.galibiyet);
            b = (TextView)itemView.findViewById(R.id.beraberlik);
            m = (TextView)itemView.findViewById(R.id.maglubiyet);
            a = (TextView)itemView.findViewById(R.id.atilanGol);
            y = (TextView)itemView.findViewById(R.id.yenilenGol);
            p = (TextView)itemView.findViewById(R.id.Puan);
            Av = (TextView)itemView.findViewById(R.id.avaraj);
            E = (TextView)itemView.findViewById(R.id.E);
        }

        void bindHolder(PuanDurumu puanDurumu){

            if(renk==true) {
                itemView.setBackgroundColor(Color.WHITE);
            }
            renk = !renk;
            takimAdi.setText(puanDurumu.getTakımAdi());
            o.setText(puanDurumu.getOynananMac());
            g.setText(puanDurumu.getGalibiyet());
            b.setText(puanDurumu.getBeraberlik());
            m.setText(puanDurumu.getMaglubiyet());
            a.setText(puanDurumu.getAtilanGol());
            y.setText(puanDurumu.getYenilenGol());
            p.setText(puanDurumu.getPuan());
            Av.setText(puanDurumu.getAvaraj());
            E.setText(puanDurumu.getE());
        }
    }

}
