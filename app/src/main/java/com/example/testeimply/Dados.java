package com.example.testeimply;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

public class Dados {
    private double valor;
    private String dsc_produto_cat;
    private String dsc_produto;
    private String imagem;
    private byte[] imagedec;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getdsc_produto() {
        return dsc_produto;
    }
    public void setdsc_produto(String dsc_produto) {
        this.dsc_produto = dsc_produto;
    }
    public String getdsc_produto_cat() {
        return dsc_produto_cat;
    }
    public void setdsc_produto_cat(String dsc_produto_cat) {
        this.dsc_produto_cat = dsc_produto_cat;
    }
    public Bitmap getimagem() {
        return BitmapFactory.decodeByteArray(this.imagedec, 0, this.imagedec.length);
    }
    public void setimagem(String imagem) {
        final String imagemcodificada = imagem.substring(imagem.lastIndexOf(",")  + 1);
        this.imagem = imagemcodificada;
        final byte[] decodedBytes = Base64.decode(imagemcodificada, Base64.DEFAULT);
        this.imagedec = decodedBytes;
    }

}
