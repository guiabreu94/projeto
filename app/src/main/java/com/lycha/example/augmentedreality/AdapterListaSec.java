package com.lycha.example.augmentedreality;

/**
 * Created by user on 09/09/2017.
 */

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by candidobugarin on 03/09/17.
 */

public class AdapterListaSec extends BaseAdapter {

    private final List<ListSec> cursos;
    private final Activity act;

    public AdapterListaSec(List<ListSec> cursos, Activity act) {
        this.cursos = cursos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_tabela_sec, parent, false);
        ListSec curso = cursos.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.lista_curso_personalizada_nome);

        nome.setText(curso.nome);

        return view;
    }
}
