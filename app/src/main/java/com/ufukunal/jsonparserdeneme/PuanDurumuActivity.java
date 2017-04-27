package com.ufukunal.jsonparserdeneme;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.DocumentsContract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ufukunal.jsonparserdeneme.R.id.none;

public class PuanDurumuActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private List<PuanDurumu> liste,l2;
    private RecyclerView recPuanDurumu;
    private Document document;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan_durumu);

        recPuanDurumu = (RecyclerView)findViewById(R.id.PuanDurumu);
        recPuanDurumu.setLayoutManager(new LinearLayoutManager(PuanDurumuActivity.this));

        new puanDurumuGetir().execute();
    }

    private class puanDurumuGetir extends AsyncTask<Void, Void, Void> {

        public puanDurumuGetir() {
            liste = new ArrayList<>();
            l2 = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                document = Jsoup.connect("http://www.tffistanbul.org/puantajvefikstur.aspx?ligid=1&haftaid=22").timeout(0).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void veriCek(String url, List<PuanDurumu> l1) {

            Elements takimAdlari = document.select(url + "td:nth-child(2)");
            Elements oynananMaclar = document.select(url + "td:nth-child(3)");
            Elements galibiyetler = document.select(url + "td:nth-child(4) ");
            Elements beraberlikler = document.select(url + "td:nth-child(5)");
            Elements maglubiyetler = document.select(url + "td:nth-child(6)");
            Elements atilanGoller = document.select(url + "td:nth-child(7)");
            Elements yenilenGoller = document.select(url + "td:nth-child(8)");
            Elements puanlar = document.select(url + "td:nth-child(9)");
            Elements avarajlar = document.select(url + "td:nth-child(10)");

            for (int i = 0; i < takimAdlari.size(); i++) {
                PuanDurumu p = new PuanDurumu();
                p.setTakımAdi(takimAdlari.get(i).text());
                p.setOynananMac(oynananMaclar.get(i).text());
                p.setGalibiyet(galibiyetler.get(i).text());
                p.setBeraberlik(beraberlikler.get(i).text());
                p.setMaglubiyet(maglubiyetler.get(i).text());
                p.setAtilanGol(atilanGoller.get(i).text());
                p.setYenilenGol(yenilenGoller.get(i).text());
                p.setPuan(puanlar.get(i).text());
                p.setAvaraj(avarajlar.get(i).text());
                p.setE("0");
                l1.add(p);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(PuanDurumuActivity.this);
            progressDialog.setTitle("Bilgilendirme");
            progressDialog.setMessage("Yükleniyor...");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            PuanDurumu p;
            veriCek("#aspnetForm > div.container.maincontainer > div > div.col-md-9.mainleftcol > center > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table > tbody > tr > td > div:nth-child(1) > table > tbody > tr > ", liste);
            veriCek("#aspnetForm > div.container.maincontainer > div > div.col-md-9.mainleftcol > center > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table > tbody > tr > td  > table > tbody > tr > ", l2);
            int a = 1;
            for (int i = 0; i < l2.size(); i++) {
                p = l2.get(i);
                liste.add(a, p);
                a = a + 2;
            }

            PuanDurumuAdapter adapter = new PuanDurumuAdapter(PuanDurumuActivity.this,liste);
            recPuanDurumu.setAdapter(adapter);
            progressDialog.dismiss();
        }
    }

}
