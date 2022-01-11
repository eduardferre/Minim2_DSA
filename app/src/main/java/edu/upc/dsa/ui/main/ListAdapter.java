package edu.upc.dsa.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.R;
import edu.upc.dsa.models.Repos;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Repos> dades;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Repos> reposList, Context context) {
        this.dades = reposList;
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.activity_repos_list, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindData(dades.get(position));
    }

    @Override
    public int getItemCount() {
        return dades.size();
    }

    public void setItems(List<Repos> items){
        dades=items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView repoName;
        TextView codeLanguage;

        MyViewHolder(View itemView){
            super(itemView);
            repoName=itemView.findViewById(R.id.repoTitle);
            codeLanguage=itemView.findViewById(R.id.repoLanguage);
        }

        void bindData(final Repos repo){
            repoName.setText(repo.getNameRepo());
            codeLanguage.setText(repo.getCodeLanguage());
        }

    }
}
