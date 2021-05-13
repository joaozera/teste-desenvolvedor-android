package com.example.testeimply;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bebidas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bebidas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;
    private List<Dados> Dados;
    private ItemAdapter itemadap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView re;

    public Bebidas() {
        // Required empty public constructor
    }

    public Bebidas(List<Dados> dados) {
        this.Dados = dados;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bebidas newInstance(String param1, String param2) {
        Bebidas fragment = new Bebidas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_bebidas, container, true);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        re = view.findViewById(R.id.recycler);
        GridLayoutManager gm = new GridLayoutManager(this.getContext(),3);
        re.setLayoutManager(gm);
        itemadap = new ItemAdapter(Dados,getActivity());
         Toast.makeText(this.getContext(),Dados.get(0).toString(),Toast.LENGTH_LONG).show();
        if (itemadap == null) {
            Toast.makeText(this.getContext(), "Teste", Toast.LENGTH_LONG).show();
            //Toast.makeText(this,"teste",Toast.LENGTH_LONG).show;
        }
        re.setAdapter(itemadap);
        itemadap.notifyDataSetChanged();
        super.onViewCreated(view, savedInstanceState);
    }
}