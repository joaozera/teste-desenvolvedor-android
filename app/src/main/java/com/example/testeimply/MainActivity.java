package com.example.testeimply;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getDados();
    }

    public void createtab(List<Dados> bebidas, List<Dados> comida) {
        ViewPager2 viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),getLifecycle());
        Bebidas b = new Bebidas(bebidas);
        adapter.addFragment(b);

        adapter.addFragment(new Lanches(comida));
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Bebidas");
                    }
                    if (position == 1) tab.setText("Lanches");
                }
        ).attach();

    }



public void getDados() {
    Call<JsonObject> DadosResponse = RetrofitClientInstance.getInterface().DadosResponse();
    DadosResponse.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if (response.isSuccessful()) {

                try {
                    JSONObject resposta = new JSONObject(new Gson().toJson(response.body()));
                    tratarJson(resposta);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {

            }

        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {

        }
    });

}
public void tratarJson(JSONObject jsonResposta) throws JSONException {
        List<Dados> bebidas = new ArrayList<>();
        List<Dados> comida = new ArrayList<>();
        JSONArray teste = jsonResposta.getJSONObject("result").getJSONArray("rows");
        for (int x=0;x<teste.length();x++) {
            Dados dado = new Dados();
            dado.setdsc_produto(teste.getJSONObject(x).getString("dsc_produto"));
            dado.setValor(teste.getJSONObject(x).getDouble("valor"));
            dado.setdsc_produto_cat(teste.getJSONObject(x).getString("dsc_produto_cat"));
            dado.setimagem(teste.getJSONObject(x).getString("imagem"));
            String u = dado.getdsc_produto_cat();
            if (u.equals("Bebidas")) {
                bebidas.add(dado);
            } else
                comida.add(dado);
        }
        createtab(bebidas,comida);
}
}